package com.tp3.grupo4.domain.repository

import com.tp3.grupo4.domain.model.Quote

interface QuoteRepository {
    suspend fun getRandomQuote(): Quote?
}