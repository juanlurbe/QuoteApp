package com.tp3.grupo4

import android.app.Application
import com.tp3.grupo4.core.Config
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Config.apiKey = getString(R.string.api_key)
        Config.baseUrl = getString(R.string.quotes_api_base_url)

    }
}