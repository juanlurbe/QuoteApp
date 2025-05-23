package com.tp3.grupo4.data.remote

import com.tp3.grupo4.data.mapper.toDomain
import com.tp3.grupo4.data.remote.api.QuoteApiService
import com.tp3.grupo4.domain.model.Quote
import javax.inject.Inject

class QuoteRemoteDataSource @Inject constructor(
    private val apiService: QuoteApiService
) {
    suspend fun getRandomQuote(): Quote? {
        val response = apiService.getQuotes()

        return if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            response.body()?.random()?.toDomain()
        } else {
            null
        }
    }
}