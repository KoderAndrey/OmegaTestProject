package com.omega.testproject.data

import com.omega.testproject.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
    private val adapterFactory: CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(adapterFactory)
        .build()
}

