package com.example.translate.classes

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.translate.R

class FamilyActivity : AppCompatActivity() {

    private var mMediaPlayer: MediaPlayer? = null
    lateinit var mAudioManager: AudioManager


    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private val mOnAudioFocusChangeListener =
        AudioManager.OnAudioFocusChangeListener { focusChange ->
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
            ) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer!!.pause()
                mMediaPlayer!!.seekTo(0)
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer!!.start()
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list)

        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        var words: ArrayList<Word> = ArrayList<Word>()

        // Create a list of words
        words.add(Word("father", "père", R.drawable.family_father, R.raw.family_father))
        words.add(Word("mother", "mère", R.drawable.family_mother, R.raw.family_mother))
        words.add(Word("son", "fils", R.drawable.family_son, R.raw.family_son))
        words.add(Word("daughter", "fille", R.drawable.family_daughter, R.raw.family_daughter))
        words.add(
            Word(
                "older brother",
                "grand frère",
                R.drawable.family_older_brother,
                R.raw.family_older_brother
            )
        )
        words.add(
            Word(
                "younger brother ",
                "frère cadet",
                R.drawable.family_younger_brother,
                R.raw.family_younger_brother
            )
        )
        words.add(
            Word(
                "older sister",
                "sœur aînée",
                R.drawable.family_older_sister,
                R.raw.family_older_sister
            )
        )
        words.add(
            Word(
                "younger sister",
                "sœur cadette",
                R.drawable.family_younger_sister,
                R.raw.family_younger_sister
            )
        )
        words.add(
            Word(
                "grandmother",
                "grand-mère",
                R.drawable.family_grandmother,
                R.raw.family_grandmother
            )
        )
        words.add(
            Word(
                "grandfather",
                "grand-père",
                R.drawable.family_grandfather,
                R.raw.family_grandfather
            )
        )


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        var itemsAdapter: WordAdapter = WordAdapter(this, words, R.color.category_family)
        val listView: ListView = findViewById(R.id.list)
        listView.adapter = itemsAdapter



        listView.setOnItemClickListener { parent, view, position, id ->
            val word: Word = words.get(position)
            releaseMediaPlayer()

            // Request audio focus so in order to play the audio file. The app needs to play a
            // short audio file, so we will request audio focus with a short amount of time
            // with AUDIOFOCUS_GAIN_TRANSIENT.
            val result = mAudioManager.requestAudioFocus(
                mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            )

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // We have audio focus now.

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(applicationContext, word.getAudioResource())

                // Start the audio file
                mMediaPlayer?.start()
            }

            /**
             * This listener gets triggered when the {@link MediaPlayer} has completed
             * playing the audio file.
             */
            mMediaPlayer?.setOnCompletionListener {
                releaseMediaPlayer()
            }
        }
    }

    private fun releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer?.release()

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener)
        }
    }

    override fun onStop() {
        super.onStop()

        releaseMediaPlayer()
    }
}