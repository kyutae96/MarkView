package com.marking.markview.builder

import com.example.markview.R
import com.marking.markview.`interface`.RetrofitAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api : RetrofitAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.25:5555/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitAPI::class.java)
    }
}

