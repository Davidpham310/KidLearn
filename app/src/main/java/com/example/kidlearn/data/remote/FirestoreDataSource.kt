package com.example.kidlearn.data.remote

import android.util.Log
import com.example.kidlearn.domain.model.LearnableItem
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreDataSource {
    private val db = Firebase.firestore

    suspend fun getAllLetters(): List<LearnableItem.Letter> {
        return try {
            val snapshot = db.collection("letters").get().await()
            snapshot.map { doc ->
                val letter = doc.toObject(LearnableItem.Letter::class.java)
                letter.copy(id = doc.id)
            }
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching letters", e)
            emptyList()
        }
    }

    suspend fun getAllNumbers(): List<LearnableItem.Number> {
        return try {
            val snapshot = db.collection("numbers").get().await()
            snapshot.map { doc ->
                val number = doc.toObject(LearnableItem.Number::class.java)
                number.copy(id = doc.id)
            }
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching numbers", e)
            emptyList()
        }
    }

    suspend fun getAllAnimals(): List<LearnableItem.Animal> {
        return try {
            val snapshot = db.collection("animals").get().await()
            snapshot.map { doc ->
                val animal = doc.toObject(LearnableItem.Animal::class.java)
                animal.copy(id = doc.id)
            }
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching animals", e)
            emptyList()
        }
    }

    // Hàm tổng hợp lấy tất cả các loại về 1 list LearnableItem
    suspend fun getAllLearnableItems(): List<LearnableItem> {
        val letters = getAllLetters()
        val numbers = getAllNumbers()
        val animals = getAllAnimals()

        return letters + numbers + animals
    }


    suspend fun getLetter(letterId: String): LearnableItem.Letter? {
        return try {
            val snapshot = db.collection("letters").document(letterId).get().await()
            val letter = snapshot.toObject(LearnableItem.Letter::class.java)
            Log.d("FirestoreDataSource", "Fetched letter: $letter")
            letter
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching letter", e)
            null
        }
    }

    suspend fun getNumber(numberId: String): LearnableItem.Number? {
        return try {
            val snapshot = db.collection("numbers").document(numberId).get().await()
            val number = snapshot.toObject(LearnableItem.Number::class.java)
            Log.d("FirestoreDataSource", "Fetched number: $number")
            number
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching number", e)
            null
        }
    }

    suspend fun getAnimal(animalId: String): LearnableItem.Animal? {
        return try {
            val snapshot = db.collection("animals").document(animalId).get().await()
            val animal = snapshot.toObject(LearnableItem.Animal::class.java)
            Log.d("FirestoreDataSource", "Fetched animal: $animal")
            animal
        } catch (e: Exception) {
            Log.e("FirestoreDataSource", "Error fetching animal", e)
            null
        }
    }

}