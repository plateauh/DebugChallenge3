package com.najed.debuggingchallenge3.api

import com.najed.debuggingchallenge3.api.model.Entry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("api/v2/entries/en/{word}")
    fun getWord(@Path("word") word: String): Call<Entry?>?
}