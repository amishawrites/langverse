package com.example.translate.classes


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.example.translate.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener();

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.action_logout) {
            logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this@MainActivity, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun clickListener() {
        val finnishActivity = findViewById<CardView>(R.id.cardView);
        val spanishActivity = findViewById<CardView>(R.id.cardView2);
        val frenchActivity = findViewById<CardView>(R.id.cardView5);

        finnishActivity.setOnClickListener {
            openFinnishActivity()
        }
        spanishActivity.setOnClickListener {
            openSpanishActivity()
        }
        frenchActivity.setOnClickListener {
            openFrenchActivity()
        }

    }

    private fun openFinnishActivity() {
        startActivity(Intent(this@MainActivity, FinnishActivity::class.java))
    }
    private fun openSpanishActivity(){
        startActivity(Intent(this@MainActivity, SpanishActivity::class.java))
    }
    private fun openFrenchActivity(){
        startActivity(Intent(this@MainActivity, FrenchActivity::class.java))
    }
}