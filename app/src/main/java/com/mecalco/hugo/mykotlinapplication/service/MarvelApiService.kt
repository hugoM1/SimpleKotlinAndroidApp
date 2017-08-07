package com.mecalco.hugo.mykotlinapplication.service

import com.mecalco.hugo.mykotlinapplication.BuildConfig
import com.mecalco.hugo.mykotlinapplication.model.Characters
import retrofit2.Response

/**
 * @author by hugo on 8/7/17.
 */

class MarvelApiService(val mMarvelApi: MarvelApi) {

    fun getCharacters(): Response<Characters>? {
        return mMarvelApi.getCharactersList(BuildConfig.PUBLIC_KEY, BuildConfig.LIMIT).execute()
    }

}