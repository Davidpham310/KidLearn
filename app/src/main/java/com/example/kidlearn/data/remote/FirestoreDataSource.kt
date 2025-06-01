package com.example.kidlearn.data.remote

import android.util.Log
import com.example.kidlearn.domain.model.Letter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Lớp này có tác dụng truy xuất dữ liệu từ firestore và trả về danh sách các đối tượng [Letter]
 */
class FirestoreDataSource {
    private val db = Firebase.firestore

    suspend fun getAllLetters(): List<Letter> {
        return try {
            val snapshot = db.collection("letters").get().await()
            snapshot.map { it.toObject(Letter::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getLetter(letterId: String): Letter? {
        return try {
            val snapshot = db.collection("letters").document(letterId).get().await()
            val letter = snapshot.toObject(Letter::class.java)
            Log.d("FirestoreDataSource", "Fetched letter: $letter")
            letter
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching letter", e)
            null
        }
    }
}