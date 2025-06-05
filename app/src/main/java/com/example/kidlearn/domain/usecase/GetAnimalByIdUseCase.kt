package com.example.kidlearn.domain.usecase

import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.AnimalRepository

class GetAnimalByIdUseCase(
    private val repository: AnimalRepository
) {
    suspend operator fun invoke(id: String): LearnableItem.Animal? {
        return repository.getAnimal(id)
    }
}