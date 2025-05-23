package com.tp3.grupo4.di

import android.content.Context
import androidx.room.Room
import com.tp3.grupo4.data.local.LocalDataSource
import com.tp3.grupo4.data.local.QuoteDao
import com.tp3.grupo4.data.local.QuoteDatabase
import com.tp3.grupo4.domain.usecase.FavoriteQuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quote_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideQuoteDao(database: QuoteDatabase): QuoteDao {
        return database.quoteDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteQuoteUseCase(
        localDataSource: LocalDataSource
    ): FavoriteQuoteUseCase {
        return FavoriteQuoteUseCase(localDataSource)
    }

}

