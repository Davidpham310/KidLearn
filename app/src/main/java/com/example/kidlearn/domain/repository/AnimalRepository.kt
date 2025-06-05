package com.example.kidlearn.domain.repository

import com.example.kidlearn.domain.model.LearnableItem

interface AnimalRepository {
    suspend fun getAllAnimals(): List<LearnableItem.Animal>
    suspend fun getAnimal(animalId: String): LearnableItem.Animal?
}