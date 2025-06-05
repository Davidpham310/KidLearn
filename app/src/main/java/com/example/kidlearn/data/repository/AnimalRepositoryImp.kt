package com.example.kidlearn.data.repository

import com.example.kidlearn.data.remote.FirestoreDataSource
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.AnimalRepository

class AnimalRepositoryImp(
    private val firestoreDataSource: FirestoreDataSource
) : AnimalRepository {
    override suspend fun getAllAnimals(): List<LearnableItem.Animal> {
        return firestoreDataSource.getAllAnimals()
    }

    override suspend fun getAnimal(animalId: String): LearnableItem.Animal? {
        return firestoreDataSource.getAnimal(animalId)
    }

}