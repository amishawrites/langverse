package com.example.translate.classes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.translate.R
import com.example.translate.databinding.ActivitySpanishBinding
import java.io.InputStream
import java.util.*


class SpanishActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpanishBinding
    private lateinit var adapter : MyAdapter

    // List of Spanish and English words in different list
    val WORDS: MutableList<String> = ArrayList()
    val ENGLISH_WORDS: MutableList<String> = ArrayList()

    // Set the default image to empty oval
    private var currentImage : Int = R.drawable.oval
    // counter for checking/unchecking empty oval
    private var switcherPressedCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpanishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Layout Manager
        val layoutManager = LinearLayoutManager(this)
        binding.myRecyclerview.setLayoutManager(layoutManager)

        // Add divider
        val divider = DividerItemDecoration(
            applicationContext, layoutManager.orientation
        )
        binding.myRecyclerview.addItemDecoration(divider)

        // Bind adapter to recycler view
        adapter = MyAdapter()
        binding.myRecyclerview.setAdapter(adapter)


        readWords()
    }

    val startForUpdateResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result : ActivityResult ->

            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                val guessStatus = intent?.getStringExtra("guessStatus").toString().trim()
                val position    = intent?.getIntExtra("position", -2)

                if(guessStatus.equals("correct")) {
                    currentImage = R.drawable.correct
                } else if (guessStatus.equals("wrong")) {
                    currentImage = R.drawable.cross
                } else {
                    currentImage = R.drawable.oval
                }


                //adapter.notifyItemChanged(currentImage)
            }
        }


    // View Holder
    inner class MyViewHolder(val itemView : View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener  {


        init {
            itemView.findViewById<View>(R.id.item_constraintLayout)
                .setOnClickListener(this)
            itemView.findViewById<View>(R.id.item_constraintLayout)
                .setOnLongClickListener(this)

            // Allow for checking correct word
            itemView.findViewById<View>(R.id.status_imageView)
                .setOnClickListener {

                    switcherPressedCount +=1

                    // if count is even, imageview = correct
                    if(switcherPressedCount % 2 == 0) {
                        itemView.findViewById<ImageView>(R.id.status_imageView)
                            .setImageResource(R.drawable.correct_small)
                    }
                    else {
                        itemView.findViewById<ImageView>(R.id.status_imageView)
                            .setImageResource(R.drawable.oval)
                    }
                    Log.i("COUNTER", "Counter: ${switcherPressedCount}")
                }
        }

        fun setWord(text : String, image: Int) {
            itemView.findViewById<TextView>(R.id.item_textview)
                .setText(text)
            itemView.findViewById<ImageView>(R.id.status_imageView)
                .setImageResource(currentImage)
        }

        fun setCheckedImage(image: Int) {
            itemView.findViewById<ImageView>(R.id.status_imageView)
                .setImageResource(currentImage)
        }





        // Listener
        override fun onClick(view: View?) {


            if (view != null) {
                val intent = Intent(view.context, WordActivitySpanish::class.java)
                // test using startActivity
                //startActivity(intent)

                // Pass in the spanish word & english translation
                val spanishWord = WORDS[adapterPosition]
                val englishWord = ENGLISH_WORDS[adapterPosition]


                intent.putExtra("spanishWord", spanishWord)
                intent.putExtra("englishWord", englishWord)

                //startActivity(intent)
                startForUpdateResult.launch((intent))
            }

        }

        // Long listener
        override fun onLongClick(view: View?): Boolean {
            if (view != null) {
                val builder = AlertDialog.Builder(view!!.context)
                builder
                    .setTitle("English Translation:")
                    .setMessage(ENGLISH_WORDS[adapterPosition])
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            }
            return true
        }
    }


    // Adapter
    inner class MyAdapter() : RecyclerView.Adapter<MyViewHolder>()  {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val word = WORDS[position]

            // Set word and completion status
            holder.setWord(word, currentImage)

            Log.i("RV_WORD", "loaded ${WORDS[position]}")
            Log.i("RV_IMAGE", "loaded ${currentImage}")
        }

        override fun getItemCount(): Int {
            return WORDS.size;
            return currentImage
        }

    }


    // Create Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Options for the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.top_scroll_menu_item) {
            binding.myRecyclerview.getLayoutManager()?.scrollToPosition(0);
            return true
        } else if (item.itemId == R.id.bottom_scroll_menu_item) {
            binding.myRecyclerview.getLayoutManager()?.scrollToPosition(WORDS.size-1)
            return true
        } else if (item.itemId == R.id.random_scroll_menu_item) {
            val randomWord = WORDS.random()
            val randomWordIndex = WORDS.indexOf(randomWord)
            binding.myRecyclerview.getLayoutManager()?.scrollToPosition(randomWordIndex)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun readWords() {
        // create input for text file
        val input : InputStream = getAssets().open("words/words.txt")

        // Setup scanner
        val scanner = Scanner(input)
        while (scanner.hasNextLine()) {
            var spanishWord  = scanner.next()
            var spPronoun    = scanner.next()
            var englishWord  = scanner.next()


            // Add spanish words to an array list
            WORDS.add(spanishWord + " " + spPronoun)

            // Add english words to a different array list
            ENGLISH_WORDS.add(englishWord)
        }
    }

}