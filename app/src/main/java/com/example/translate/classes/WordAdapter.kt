package com.example.translate.classes

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.translate.R

class WordAdapter : ArrayAdapter<Word> {

    private var backgroundColor: Int
    private var listItemView: View? = null

    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of {@link Word}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    constructor(
        context: Context,
        words: ArrayList<Word>,
        colorResourceId: Int
    ): super(context, 0, words){

        backgroundColor = colorResourceId

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        listItemView = convertView
        if(listItemView == null)
            listItemView = LayoutInflater.from(this@WordAdapter.getContext()).inflate(
                R.layout.list_view, parent, false)

        var currentPosition = getItem(position) as Word

        var deafultTextView = listItemView?.findViewById(R.id.default_text_view) as TextView
        deafultTextView.setText(currentPosition.getDefaultTranslation())


        var frenchTextView = listItemView?.findViewById(R.id.miwok_text_view) as TextView
        frenchTextView.setText(currentPosition.getFrenchTranslation())

        val textContainer = listItemView?.findViewById(R.id.text_container) as View
        var color: Int = ContextCompat.getColor(context, backgroundColor)
        textContainer.setBackgroundColor(color)

//        textContainer?.setOnClickListener{
//            var mediaPlayer = MediaPlayer.create(context, currentPosition!!.getAudioResource())
//            mediaPlayer.start()
//
//        }
        val iconImageView: ImageView? = listItemView?.findViewById(R.id.image_view)
        if (iconImageView != null) {
            if (currentPosition != null) {
                if (currentPosition.hasImage())
                    iconImageView.setImageResource(currentPosition.getImageResourceId())
                else
                    iconImageView.visibility = View.GONE
            }
        }

        // Return the whole list item layout (containing 2 TextViews and 1 ImageView) so that it can be shown in
        // the ListView.
        return listItemView!!
    }

}