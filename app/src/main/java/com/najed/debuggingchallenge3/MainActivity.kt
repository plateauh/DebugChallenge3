package com.najed.debuggingchallenge3

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najed.debuggingchallenge3.api.APIClient
import com.najed.debuggingchallenge3.api.APIInterface
import com.najed.debuggingchallenge3.api.model.Definition
import com.najed.debuggingchallenge3.api.model.Entry
import com.najed.debuggingchallenge3.api.model.Meaning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var definitions: ArrayList<ArrayList<String>>
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var etWord: EditText
    private lateinit var btSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        definitions = arrayListOf()
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(definitions, this)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        etWord = findViewById(R.id.etWord)
        btSearch = findViewById(R.id.btSearch)
        btSearch.setOnClickListener {
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            if (activeNetwork?.isConnectedOrConnecting == true) {
                setDefinitions(etWord.text.toString())
                etWord.setText("")
            }
            else
                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun setDefinitions(word: String) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Entry?>? = apiInterface!!.getWord(word)
        call?.enqueue(object: Callback<Entry?> {

            override fun onResponse(call: Call<Entry?>, response: Response<Entry?>) {
                val tempArray = arrayListOf<String>()
                for (entry in response.body()!!) {
                    tempArray.add(entry.word)
                    for (meaning in entry.meanings)
                        for (definition in meaning.definitions)
                            tempArray.add(definition.definition)
                }
                definitions.add(tempArray)
                rvMain.adapter = RVAdapter(definitions, this@MainActivity)
            }

            override fun onFailure(call: Call<Entry?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error getting data ${t.message}", Toast.LENGTH_SHORT).show()
                call.cancel()
            }
        })
    }

    fun navigate(definition: ArrayList<String>) {
        val intent = Intent(this, WordDetailsActivity::class.java)
        intent.putExtra("defs", definition)
        startActivity(intent)
    }
}