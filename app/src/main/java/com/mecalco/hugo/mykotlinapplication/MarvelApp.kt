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
        lateinit var mRetrofit: Retrofit
        lateinit var mAPI: MarvelApi
    }

    override fun onCreate() {
        super.onCreate()

        mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mAPI = mRetrofit.create(MarvelApi::class.java)
    }

}