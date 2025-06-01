package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.Letter
import com.example.kidlearn.domain.repository.LetterRepository
import javax.inject.Inject

class GetLetterByIdUseCase @Inject constructor(
    private val repository: LetterRepository
) {
    suspend operator fun invoke(id: String): Letter? {
        return repository.getLetter(id)
    }
}
