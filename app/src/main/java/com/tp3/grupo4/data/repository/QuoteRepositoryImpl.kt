package com.tp3.grupo4.data.repository

import com.tp3.grupo4.data.remote.QuoteRemoteDataSource
import com.tp3.grupo4.domain.model.Quote
import com.tp3.grupo4.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: QuoteRemoteDataSource
) : QuoteRepository {

    override suspend fun getRandomQuote(): Quote? {
        return remoteDataSource.getRandomQuote()
    }
}