package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.AnimalRepository

class GetAllAnimalUseCase(
    private val repository: AnimalRepository
) {
    suspend operator fun invoke(): List<LearnableItem> {
        return repository.getAllAnimals()
    }
}