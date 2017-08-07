package com.mecalco.hugo.mykotlinapplication

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mecalco.hugo.mykotlinapplication.model.Characters
import com.mecalco.hugo.mykotlinapplication.service.MarvelApiService
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        var TAG: String = "MainActivity"
        lateinit var apiService: MarvelApiService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = MarvelApiService(MarvelApp.api)

        RetrieveCharactersTask().execute("")

    }

    class RetrieveCharactersTask : AsyncTask<String, Void, Response<Characters>?>() {
        override fun doInBackground(vararg p0: String?): Response<Characters>? {
            return apiService.getCharacters()
        }

        override fun onPostExecute(result: Response<Characters>?) {
            Log.wtf(TAG, result?.body()?.data?.results.toString())
            for (item in result?.body()?.data?.results!!) {
                Log.wtf(TAG, item.name)
            }
        }
    }

}
