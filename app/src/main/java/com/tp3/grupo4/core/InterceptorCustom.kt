package com.tp3.grupo4.core

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

object InterceptorCustom : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        Log.d("API_KEY", "Interceptando con API KEY: ${Config.apiKey}")
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", Config.apiKey)
            .build()

        return chain.proceed(request)
    }
}
