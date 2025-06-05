package com.example.kidlearn.presentation.learn_letters

import com.example.kidlearn.domain.model.LearnableItem

data class LetterState(
    val currentLetter: LearnableItem? = null,
    val letters: List<LearnableItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
