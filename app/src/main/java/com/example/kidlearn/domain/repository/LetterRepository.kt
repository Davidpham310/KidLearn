package com.example.kidlearn.domain.repository

import com.example.kidlearn.domain.model.LearnableItem

interface LetterRepository {
    suspend fun getAllLetters(): List<LearnableItem.Letter>
    suspend fun getLetter(letterId: String): LearnableItem.Letter?
}