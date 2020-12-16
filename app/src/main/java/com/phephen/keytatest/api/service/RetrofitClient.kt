package com.phephen.keytatest.api.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Fendy Saputro on 16/12/2020.
 * vidis194@gmail.com
 */
object RetrofitClient {

    var BASE_URL = "https://test.keyta.id/"
    val getClient: ApiService
    get() {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiService::class.java)
    }

}