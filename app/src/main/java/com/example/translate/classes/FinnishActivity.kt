package com.example.translate.classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.translate.R


class FinnishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finnish)

        //when the word card pressed
        findViewById<CardView>(R.id.word_card).setOnClickListener {
            onClickWords(it) //goto function onClickWord
        }

        //when the quiz card pressed
        findViewById<CardView>(R.id.quiz_card).setOnClickListener {
            onClickQuiz(it) //goto function onClickQuiz
        }
        //when the stats card pressed
        findViewById<CardView>(R.id.stats_card).setOnClickListener {
            onClickStats(it) //goto function onClickStats
        }

        //when the word button is pressed
        findViewById<Button>(R.id.word_button).setOnClickListener {
            onClickWords(it) //goto function onClickWord
        }

        //when the quiz button is pressed
        findViewById<Button>(R.id.quiz_button).setOnClickListener {
            onClickQuiz(it) //goto function onClickQuiz
        }

        //when the stats button pressed
        findViewById<Button>(R.id.stats_button).setOnClickListener {
            onClickStats(it)
        }


    }

    //Open the Quiz activity
    fun onClickQuiz(view: View) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)

    }

    //Open the Stats activity
    fun onClickStats(view: View) {
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)

    }

    //Open the Words activity
    fun onClickWords(view: View) {
        val intent = Intent(this, WordsActivity::class.java)
        startActivity(intent)

    }


}