package com.example.kidlearn.presentation.learn_numbers

sealed class NumberEvent {
    data class PlayAudio(val text: String) : NumberEvent()
    data class SelectNumber(val id: String) : NumberEvent()
}