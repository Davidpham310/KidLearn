package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.LetterRepository

class GetAllLettersUseCase(
    private val repository: LetterRepository
) {
    suspend operator fun invoke(): List<LearnableItem> {
        return repository.getAllLetters()
    }
}
