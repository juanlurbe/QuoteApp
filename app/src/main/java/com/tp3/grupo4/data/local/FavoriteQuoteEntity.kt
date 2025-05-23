package com.tp3.grupo4.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quotes")
data class FavoriteQuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val phrase: String,
    val author: String,
    val category: String
)
