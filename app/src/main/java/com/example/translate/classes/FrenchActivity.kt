package com.example.translate.classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.translate.R


class FrenchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_french)
        clickListener();
    }

    private fun clickListener() {
        val familyActivity = findViewById<CardView>(R.id.family);
        val colorsActivity = findViewById<CardView>(R.id.colors);
        val numbersActivity = findViewById<CardView>(R.id.numbers);
        val phraseActivity = findViewById<CardView>(R.id.phrases);

        familyActivity.setOnClickListener {
            openFamilyActivity()
        }
        colorsActivity.setOnClickListener {
            openColorsActivity()
        }
        numbersActivity.setOnClickListener {
            openNumbersActivity()
        }
        phraseActivity.setOnClickListener {
            openPhraseActivity()
        }

    }

    private fun openFamilyActivity() {
        startActivity(Intent(this@FrenchActivity, FamilyActivity::class.java))
    }

    private fun openColorsActivity() {
        startActivity(Intent(this@FrenchActivity, ColorsActivity::class.java))
    }

    private fun openNumbersActivity() {
        startActivity(Intent(this@FrenchActivity, NumbersActivity::class.java))
    }

    private fun openPhraseActivity() {
        startActivity(Intent(this@FrenchActivity, PhraseActivity::class.java))
    }
}