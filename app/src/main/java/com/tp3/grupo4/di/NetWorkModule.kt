package com.tp3.grupo4.di


import com.tp3.grupo4.core.InterceptorCustom
import com.tp3.grupo4.data.remote.QuoteRemoteDataSource
import com.tp3.grupo4.data.remote.api.QuoteApiService
import com.tp3.grupo4.data.repository.QuoteRepositoryImpl
import com.tp3.grupo4.domain.repository.QuoteRepository
import com.tp3.grupo4.domain.usecase.GetRandomQuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(InterceptorCustom)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/") // <- Reemplazalo si hace falta
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiService(retrofit: Retrofit): QuoteApiService {
        return retrofit.create(QuoteApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuoteRemoteDataSource(apiService: QuoteApiService): QuoteRemoteDataSource {
        return QuoteRemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideQuoteRepository(remoteDataSource: QuoteRemoteDataSource): QuoteRepository {
        return QuoteRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetRandomQuoteUseCase(repository: QuoteRepository): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(repository)
    }
}