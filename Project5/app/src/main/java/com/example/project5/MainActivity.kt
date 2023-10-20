package com.example.project5

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.example.project5.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import okhttp3.Headers
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.util.Locale

lateinit var binding: ActivityMainBinding

fun Calendar.getDateString(): String {
    // we create simpleDateFormat object that's yyyy-MM-dd
    val date = SimpleDateFormat("yyyy-MM-dd")
    // we take date, insert the current date into it, and convert it to a string
    return date.format(this.time)
}

class MainActivity : AppCompatActivity() {
    private val cal = Calendar.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkRequest(date = cal.getDateString())
        btnListener()
    }

    private fun networkRequest(date: String) {
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["hd"] = "true"
        params["date"] = date
        params["api_key"] = "placeholder"
        client["https://api.nasa.gov/planetary/apod", params, object :
        JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val imageUrl = json.jsonObject.optString("url")
                val title = json.jsonObject.optString("title")
                val date = json.jsonObject.optString("date")
                val copyright = json.jsonObject.optString("copyright")
                val text = "Title: $title\nDate: $date\nCopyright: $copyright"
                updateGui(imageUrl, text)
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

    private fun updateGui(imageUrl: String, desc: String) {
        Log.d("IMAGE_URL", imageUrl)
        Picasso.get().load(imageUrl).fit().centerInside().into(binding.imageView);
        binding.desc.text = desc
    }

    private fun btnListener() {
        binding.button.setOnClickListener {
            cal.add(Calendar.DAY_OF_YEAR, -1)
            val date = cal.getDateString()
            networkRequest(date)
        }
    }

}