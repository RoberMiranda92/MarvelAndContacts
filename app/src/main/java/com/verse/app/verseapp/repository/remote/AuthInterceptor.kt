package com.verse.app.verseapp.repository.remote

import com.verse.app.verseapp.repository.AuthHashGenerator
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthInterceptor(private var publicKey: String, private var privateKey: String) : Interceptor {

    private val TIMESTAMP_KEY = "ts"
    private val HASH_KEY = "hash"
    private val APIKEY_KEY = "apikey"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        var hash: String? = null
        try {
            hash = AuthHashGenerator.generateHash(timestamp, publicKey, privateKey)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        var request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(APIKEY_KEY, publicKey)
            .addQueryParameter(HASH_KEY, hash)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}