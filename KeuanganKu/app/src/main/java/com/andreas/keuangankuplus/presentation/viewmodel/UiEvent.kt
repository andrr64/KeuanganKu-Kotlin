package com.andreas.keuangankuplus.presentation.viewmodel

sealed class UiEvent {
    data class SaveResult(val success: Boolean, val message: String) : UiEvent()
}