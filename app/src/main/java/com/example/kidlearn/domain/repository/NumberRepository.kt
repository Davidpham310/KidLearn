package com.example.kidlearn.domain.repository

import com.example.kidlearn.domain.model.LearnableItem

interface NumberRepository {
    suspend fun getAllNumbers(): List<LearnableItem.Number>
    suspend fun getNumber(numberId: String): LearnableItem.Number?
}