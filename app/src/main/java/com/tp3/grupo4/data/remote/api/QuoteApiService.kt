package com.tp3.grupo4.data.remote.api

import com.tp3.grupo4.data.remote.dto.QuoteDto
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiService {
    @GET("v1/quotes")
    suspend fun getQuotes(): Response<List<QuoteDto>>
}