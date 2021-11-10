package com.najed.debuggingchallenge3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najed.debuggingchallenge3.api.model.Definition

class WordDetailsActivity : AppCompatActivity() {

    lateinit var wordTextView: TextView
    lateinit var definitionsRecyclerView: RecyclerView
    lateinit var definitions: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_details)
        definitions = intent.getStringArrayListExtra("defs") as ArrayList<String>

        wordTextView = findViewById(R.id.word_tv)
        wordTextView.text = definitions[0]
        definitions.removeFirst()

        definitionsRecyclerView = findViewById(R.id.defs_rv)
        definitionsRecyclerView.adapter = DefsAdapter(definitions)
        definitionsRecyclerView.layoutManager = LinearLayoutManager(this)

    }
}