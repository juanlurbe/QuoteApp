package com.tp3.grupo4.domain.usecase

import com.tp3.grupo4.data.local.FavoriteQuoteEntity
import com.tp3.grupo4.data.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteQuoteUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    suspend fun addToFavorites(quote: FavoriteQuoteEntity) {
        localDataSource.saveQuote(quote)
    }

    suspend fun removeFromFavorites(quote: FavoriteQuoteEntity) {
        localDataSource.deleteQuote(quote)
    }

    fun getAllFavorites(): Flow<List<FavoriteQuoteEntity>> {
        return localDataSource.getAllQuotes()
    }
}