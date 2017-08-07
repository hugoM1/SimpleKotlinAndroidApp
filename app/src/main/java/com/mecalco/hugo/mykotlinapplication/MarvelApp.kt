package com.mecalco.hugo.mykotlinapplication

import android.app.Application
import com.mecalco.hugo.mykotlinapplication.service.MarvelApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author by hugo on 8/7/17.
 */
class MarvelApp : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var api: MarvelApi
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(MarvelApi::class.java)
    }
}