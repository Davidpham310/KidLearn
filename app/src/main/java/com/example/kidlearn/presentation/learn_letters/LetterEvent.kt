package com.example.kidlearn.presentation.learn_letters

sealed class LetterEvent {
    data class PlayAudio(val text: String) : LetterEvent()
    data class SelectLetter(val id: String) : LetterEvent()
}