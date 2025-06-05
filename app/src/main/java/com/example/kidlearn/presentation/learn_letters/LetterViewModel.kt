package com.example.kidlearn.presentation.learn_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidlearn.domain.model.LearnableItem
import com.example.kidlearn.domain.usecase.GetAllLettersUseCase
import com.example.kidlearn.domain.usecase.GetLetterByIdUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LetterViewModel @Inject constructor(
    private val getLetterByIdUseCase: GetLetterByIdUseCase,
    private val getAllLettersUseCase: GetAllLettersUseCase,
) : ViewModel() {

    var onSpeak: ((String) -> Unit)? = null

    private val _state = mutableStateOf(LetterState())
    val state: State<LetterState> = _state

    fun fetchLetter(id: String) {
        viewModelScope.launch {
            try {
                val result = getLetterByIdUseCase(id)
                _state.value = _state.value.copy(currentLetter = result)
            } catch (e: Exception) {
                _state.value = _state.value.copy(errorMessage = "Lỗi tải chữ cái")
            }
        }
    }

    fun fetchAllLetters() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = getAllLettersUseCase()
                _state.value = _state.value.copy(
                    letters = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    errorMessage = "Lỗi tải danh sách chữ cái",
                    isLoading = false
                )
            }
        }
    }

    fun uploadLettersToFirestore() {
        val db = FirebaseFirestore.getInstance()
        val letters = ('A'..'Z').map { char ->
            mapOf(
                "id" to char.toString(),                         // id: "A", "B", ...
                "title" to char.toString(),                      // title: "A", "B", ...
                "imageRes" to "letter_${char.lowercaseChar()}", // imageRes: "letter_a", ...
                "textToRead" to char.toString() // textToRead: "Chữ cái A", ...
            )
        }

        letters.forEach { letterMap ->
            val documentId = letterMap["id"]!! // dùng chính chữ cái làm document ID
            db.collection("letters")
                .document(documentId)
                .set(letterMap)
                .addOnSuccessListener {
                    println("✅ Đã thêm chữ cái: $documentId")
                }
                .addOnFailureListener { e ->
                    println("❌ Lỗi khi thêm $documentId: ${e.message}")
                }
        }
    }


    fun onEvent(event: LetterEvent) {
        when (event) {
            is LetterEvent.SelectLetter -> fetchLetter(event.id)
            is LetterEvent.PlayAudio -> onSpeak?.invoke(event.text)
        }
    }
}

