package com.tp3.grupo4.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val quoteDao: QuoteDao
) {

    suspend fun saveQuote(quote: FavoriteQuoteEntity) {
        quoteDao.insertQuote(quote)
    }

    suspend fun deleteQuote(quote: FavoriteQuoteEntity) {
        quoteDao.deleteQuote(quote)
    }

    suspend fun updateQuote(quote: FavoriteQuoteEntity) {
        quoteDao.updateQuote(quote)
    }

    fun getAllQuotes(): Flow<List<FavoriteQuoteEntity>> {
        return quoteDao.getAllQuotes()
    }
}