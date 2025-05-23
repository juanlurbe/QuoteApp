package com.tp3.grupo4.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp3.grupo4.data.local.FavoriteQuoteEntity
import com.tp3.grupo4.domain.usecase.FavoriteQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteQuoteViewModel @Inject constructor(
    private val favoriteQuoteUseCase: FavoriteQuoteUseCase
) : ViewModel() {

    val favoriteQuotes = favoriteQuoteUseCase.getAllFavorites()
        .map { quotes -> quotes.sortedByDescending { it.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removeFavorite(quote: FavoriteQuoteEntity) {
        viewModelScope.launch {
            favoriteQuoteUseCase.removeFromFavorites(quote)
        }
    }
}