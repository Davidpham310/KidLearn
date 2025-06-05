package com.example.kidlearn.presentation.learn_numbers

import com.example.kidlearn.domain.model.LearnableItem


data class NumberState(
    val currentNumber: LearnableItem? = null,
    val values: List<LearnableItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)