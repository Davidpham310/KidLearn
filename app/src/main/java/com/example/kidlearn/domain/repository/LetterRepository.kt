package com.example.kidlearn.domain.repository

import com.example.kidlearn.domain.model.Letter

interface LetterRepository {
    suspend fun getAllLetters(): List<Letter>
    suspend fun getLetter(letterId: String): Letter?
}