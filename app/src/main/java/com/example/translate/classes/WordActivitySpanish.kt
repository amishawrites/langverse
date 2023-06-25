package com.example.translate.classes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.translate.R
import com.example.translate.databinding.ActivityWordBinding

class WordActivitySpanish : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWordBinding
    private var currentImage : Int = R.drawable.question_mark

    // -1 means "not set"
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        // Get values from MainActivity
        val spanishWord:String = intent
            .getStringExtra("spanishWord").toString()
        val englishWord:String = intent
            .getStringExtra("englishWord").toString()


        // Set textview equal to Spanish word clicked from MainActivity
        binding.spanishWordTextview.setText(spanishWord)

        binding.correctIncorrectImageView.setImageResource(currentImage)
        binding.checkAnswerButton.setOnClickListener{
            val guess        = binding.engTranslationEdittext.getText().toString().trim()
            val correctGuess = binding.engTranslationEdittext.getText()
                .toString().trim().equals(englishWord)
            val wrongGuess   = !binding.engTranslationEdittext.getText()
                .toString().trim().equals(englishWord)


            if (correctGuess) {
                if(currentImage != R.drawable.correct) {
                    currentImage = R.drawable.correct
                    binding.correctIncorrectImageView.setImageResource(currentImage)
                }
            } else if (wrongGuess) {
                if (currentImage != R.drawable.cross) {
                    currentImage  = R.drawable.cross
                    binding.correctIncorrectImageView.setImageResource(currentImage)
                }
            } else if (guess.isEmpty()) {
                // Error message
                Toast.makeText(
                    applicationContext, "You must enter an English word!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    override fun onBackPressed() {
        val guessStatus = ""

        if (currentImage == R.drawable.correct_small) {
            val guessStatus = "correct"
        } else if (currentImage == R.drawable.cross_small){
            val guessStatus = "wrong"
        } else {
            val guessStatus = ""
        }

        // create intent
        val intent = Intent()

        intent.putExtra("guessStatus", guessStatus)
        intent.putExtra("position", position)
        setResult(RESULT_OK, intent)

        super.onBackPressed()
    }


    override fun onClick(v: View?) {

    }
}