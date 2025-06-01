package com.example.kidlearn.presentation.learn_letters

sealed class LetterEvent {
    data class PlayAudio(val audioResId: Int) : LetterEvent()
}