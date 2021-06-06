package com.dicoding.proyekakhir.retrofit

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.converter.gson.GsonConverterFactory.create
import okhttp3.OkHttpClient.Builder as OkHttpClient
import retrofit2.Retrofit.Builder as Retrofit

class TMDBClient {

    companion object {
        fun getService(): TMDBService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(Level.BODY)
            val okHttpClient = OkHttpClient()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            Retrofit()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(create())
                .client(okHttpClient)
                .build().also {
                    return it.create(TMDBService::class.java)
                }
        }
    }

}