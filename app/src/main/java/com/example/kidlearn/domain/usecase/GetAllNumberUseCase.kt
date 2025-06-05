package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.NumberRepository

class GetAllNumberUseCase(
    private val repository: NumberRepository
) {
    suspend operator fun invoke(): List<LearnableItem> {
        return repository.getAllNumbers()
    }
}