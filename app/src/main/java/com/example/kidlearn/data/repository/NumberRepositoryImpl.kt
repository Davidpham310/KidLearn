package com.example.kidlearn.data.repository

import com.example.kidlearn.data.remote.FirestoreDataSource
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.NumberRepository

class NumberRepositoryImpl(
    private val firestoreDataSource: FirestoreDataSource
) : NumberRepository {
    override suspend fun getAllNumbers(): List<LearnableItem.Number> {
       return firestoreDataSource.getAllNumbers()
    }

    override suspend fun getNumber(numberId: String): LearnableItem.Number? {
        return firestoreDataSource.getNumber(numberId)
    }

}