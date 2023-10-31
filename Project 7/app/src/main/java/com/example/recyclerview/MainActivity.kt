package com.example.recyclerview

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.recyclerview.databinding.ActivityMainBinding
import okhttp3.Headers
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private val cal = Calendar.getInstance();
    private lateinit var binding: ActivityMainBinding
    val mutableList: MutableList<RecyclerCell> = mutableListOf()

    private fun Calendar.getDateString(): String {
        // we create simpleDateFormat object that's yyyy-MM-dd
        val date = SimpleDateFormat("yyyy-MM-dd")
        // we take date, insert the current date into it, and convert it to a string
        return date.format(this.time)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loopNetworkRequest(date = cal.getDateString())
    }

    private fun loopNetworkRequest(date: String) {
        for (i in 0 until 20) {
            cal.add(Calendar.DAY_OF_YEAR,-i)
            networkRequest(date = cal.getDateString())
        }
    }

    private fun runRecycler() {
        val adapter = RecyclerAdapter(mutableList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun networkRequest(date: String) {
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["hd"] = "true"
        params["date"] = date
        params["api_key"] = "axTMwgp4cVrCtV2X2UjwzLjogVsOULrbcvcgybOH"
        client["https://api.nasa.gov/planetary/apod", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val imageUrl = json.jsonObject.optString("url")
                val title = json.jsonObject.optString("title")
                val date = json.jsonObject.optString("date")
                val copyright = json.jsonObject.optString("copyright")

                val recyclerCell = RecyclerCell(imageUrl, "Title: $title", "Date: $date", "Copyright: $copyright")
                mutableList.add(recyclerCell)
                runRecycler()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                responseString: String?,
                throwable: Throwable?
            ) {
                // handle the failure and alert the user to retry
                Log.e("ERROR", responseString ?: "Null response occurred")
            }
        }]
    }
}