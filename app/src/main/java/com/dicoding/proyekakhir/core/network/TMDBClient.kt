package com.dicoding.proyekakhir.core.network

import com.dicoding.proyekakhir.core.Constant.BASE_URL
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.converter.gson.GsonConverterFactory.create
import okhttp3.OkHttpClient.Builder as OkHttpClient
import retrofit2.Retrofit.Builder as Retrofit

class TMDBClient {

    companion object {
        fun getService(): TMDBService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(BODY)
            val okHttpClient = OkHttpClient()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            Retrofit()
                .baseUrl(BASE_URL)
                .addConverterFactory(create())
                .client(okHttpClient)
                .build().also {
                    return it.create(TMDBService::class.java)
                }
        }
    }

}