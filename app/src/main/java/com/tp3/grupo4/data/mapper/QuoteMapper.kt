package com.tp3.grupo4.data.mapper

import com.tp3.grupo4.data.remote.dto.QuoteDto
import com.tp3.grupo4.domain.model.Quote

fun QuoteDto.toDomain(): Quote {
    return Quote(
        phrase = this.phrase,
        author = this.author,
        category = this.category
    )
}