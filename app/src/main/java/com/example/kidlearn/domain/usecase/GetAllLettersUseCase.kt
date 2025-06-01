package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.Letter
import com.example.kidlearn.domain.repository.LetterRepository

class GetAllLettersUseCase(
    private val repository: LetterRepository
) {
    suspend operator fun invoke(): List<Letter> {
        return repository.getAllLetters()
    }
}
