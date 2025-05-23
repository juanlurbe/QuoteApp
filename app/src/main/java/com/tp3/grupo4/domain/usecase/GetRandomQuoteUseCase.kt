package com.tp3.grupo4.domain.usecase

import com.tp3.grupo4.domain.model.Quote
import com.tp3.grupo4.domain.repository.QuoteRepository

class GetRandomQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): Quote? {
        return repository.getRandomQuote()
    }

}