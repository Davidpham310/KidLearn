package com.example.kidlearn.presentation.learn_animals

import com.example.kidlearn.domain.model.LearnableItem

data class AnimalState(
    val currentAnimal: LearnableItem? = null,
    val names: List<LearnableItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)