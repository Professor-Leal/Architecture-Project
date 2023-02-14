package com.rafaelleal.android.presentation_address.address

sealed class UiState< out R> {
    object Loading : UiState<Nothing>()
    object Waiting : UiState<Nothing>()
    data class Error<T : Any>(val errorMessage: String) : UiState<T>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}