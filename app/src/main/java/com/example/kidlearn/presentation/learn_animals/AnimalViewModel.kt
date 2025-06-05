package com.example.kidlearn.presentation.learn_animals

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidlearn.domain.usecase.GetAllAnimalUseCase
import com.example.kidlearn.domain.usecase.GetAllLettersUseCase
import com.example.kidlearn.domain.usecase.GetAnimalByIdUseCase
import com.example.kidlearn.domain.usecase.GetLetterByIdUseCase
import com.example.kidlearn.presentation.learn_letters.LetterEvent
import com.example.kidlearn.presentation.learn_letters.LetterState
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val getAnimalByIdUseCase: GetAnimalByIdUseCase,
    private val getAllAnimalsUseCase: GetAllAnimalUseCase,
) : ViewModel() {

    var onSpeak: ((String) -> Unit)? = null

    private val _state = mutableStateOf(AnimalState())
    val state: State<AnimalState> = _state

    fun fetchAnimal(id: String) {
        viewModelScope.launch {
            try {
                val result = getAnimalByIdUseCase(id)
                _state.value = _state.value.copy(currentAnimal = result)
            } catch (e: Exception) {
                _state.value = _state.value.copy(errorMessage = "Lỗi tải con vật")
            }
        }
    }

    fun fetchAllAnimals() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = getAllAnimalsUseCase()
                _state.value = _state.value.copy(
                    names = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    errorMessage = "Lỗi tải danh sách con vật",
                    isLoading = false
                )
            }
        }
    }

    fun uploadAnimalsToFirestoreSequentially() {
        val db = FirebaseFirestore.getInstance()
        val animals = (1..30).map { number ->
            val paddedId = number.toString().padStart(2, '0')
            mapOf(
                "id" to paddedId,
                "name" to "Con vật $number", // Bạn có thể thay bằng tên thật như "Mèo", "Chó", ...
                "imageRes" to "animal_$number", // Tên resource ảnh, ví dụ: animal_1, animal_2,...
                "textToRead" to "Con vật số $number"
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            for (animalMap in animals) {
                val documentId = animalMap["id"]!!
                try {
                    db.collection("animals")
                        .document(documentId)
                        .set(animalMap)
                        .await()
                    println("✅ Đã thêm con vật: $documentId")
                } catch (e: Exception) {
                    println("❌ Lỗi khi thêm $documentId: ${e.message}")
                }
            }
        }
    }


    fun onEvent(event: LetterEvent) {
        when (event) {
            is LetterEvent.SelectLetter -> fetchAnimal(event.id)
            is LetterEvent.PlayAudio -> onSpeak?.invoke(event.text)
        }
    }
}