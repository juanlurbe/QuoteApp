package com.tp3.grupo4.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp3.grupo4.data.local.FavoriteQuoteEntity
import com.tp3.grupo4.domain.usecase.FavoriteQuoteUseCase
import com.tp3.grupo4.domain.usecase.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val favoriteQuoteUseCase: FavoriteQuoteUseCase
) : ViewModel() {

    var phrase = mutableStateOf("")

    var author = mutableStateOf("")

    var category = mutableStateOf("")

    var isLoading = mutableStateOf(false)

    fun loadRandomQuote() {
        viewModelScope.launch {
            isLoading.value = true
            val quote = getRandomQuoteUseCase()
            phrase.value = quote?.phrase ?: "No se pudo cargar la frase"
            author.value = quote?.author ?: ""
            category.value = quote?.category ?: ""

            isLoading.value = false
        }
    }

    fun saveCurrentQuoteAsFavorite() {
        val current = phrase.value
        if (current.isBlank()) return

        val favorite = FavoriteQuoteEntity(
            phrase = phrase.value,
            author = author.value,
            category = category.value
        )

        viewModelScope.launch {
            favoriteQuoteUseCase.addToFavorites(favorite)
        }
    }
}