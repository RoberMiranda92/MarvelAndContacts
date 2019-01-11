package com.verse.app.verseapp.repository.remote

import com.verse.app.verseapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MarvelApiFactory {

    companion object {
        fun create(): MarvelApiService {
            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
            httpClient.addInterceptor(AuthInterceptor(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY))

            val retrofit: Retrofit = Retrofit.Builder()
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.SERVER_URL)
                .build()

            return retrofit.create(MarvelApiService::class.java)
        }
    }
}