package com.tp3.grupo4.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: FavoriteQuoteEntity)

    @Delete
    suspend fun deleteQuote(quote: FavoriteQuoteEntity)

    @Query("SELECT * FROM favorite_quotes")
    fun getAllQuotes(): Flow<List<FavoriteQuoteEntity>>
}