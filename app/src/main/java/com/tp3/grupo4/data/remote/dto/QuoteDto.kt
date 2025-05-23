package com.tp3.grupo4.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("quote") val phrase: String,
    @SerializedName("author") val author: String,
    @SerializedName("category") val category: String
)
