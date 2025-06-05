package com.example.kidlearn.data.repository

import com.example.kidlearn.data.remote.FirestoreDataSource
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.repository.LetterRepository

/**
 * Lớp này có tác dụng truy xuất dữ liệu từ firestore và trả về danh sách các đối tượng [Letter]
 */
class LetterRepositoryImpl(
    private val firestoreDataSource: FirestoreDataSource
) : LetterRepository {
    override suspend fun getAllLetters(): List<LearnableItem.Letter> {
        return firestoreDataSource.getAllLetters()
    }

    override suspend fun getLetter(letterId: String): LearnableItem.Letter? {
        return firestoreDataSource.getLetter(letterId)
    }
}