package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.NumberRepository

class GetNumberByIdUseCase(
    private val repository: NumberRepository
) {
    suspend operator fun invoke(id: String): LearnableItem.Number? {
        return repository.getNumber(id)
    }
}