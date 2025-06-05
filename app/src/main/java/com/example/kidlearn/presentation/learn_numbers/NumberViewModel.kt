package com.example.kidlearn.presentation.learn_numbers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidlearn.domain.usecase.GetAllNumberUseCase
import com.example.kidlearn.domain.usecase.GetNumberByIdUseCase
import com.example.kidlearn.presentation.learn_letters.LetterEvent
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor(
    private val getNumberByIdUseCase: GetNumberByIdUseCase,
    private val getAllNumbersUseCase: GetAllNumberUseCase,
) : ViewModel() {

    var onSpeak: ((String) -> Unit)? = null

    private val _state = mutableStateOf(NumberState())
    val state: State<NumberState> = _state

    fun fetchNumber(id: String) {
        viewModelScope.launch {
            try {
                val result = getNumberByIdUseCase(id)
                _state.value = _state.value.copy(currentNumber = result)
            } catch (e: Exception) {
                _state.value = _state.value.copy(errorMessage = "Lỗi tải chữ số")
            }
        }
    }

    fun uploadNumbersToFirestoreSequentially() {
        val db = FirebaseFirestore.getInstance()
        val numbers = (1..20).map { number ->
            val paddedId = number.toString().padStart(2, '0') // "01", "02", ..., "20"
            mapOf(
                "id" to paddedId,
                "value" to number.toString(),
                "imageRes" to "number_$number",
                "textToRead" to "Số $number"
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            for (numberMap in numbers) {
                val documentId = numberMap["id"]!!
                try {
                    db.collection("numbers")
                        .document(documentId)
                        .set(numberMap)
                        .await() // Đảm bảo tuần tự
                    println("✅ Đã thêm số: $documentId")
                } catch (e: Exception) {
                    println("❌ Lỗi khi thêm $documentId: ${e.message}")
                }
            }
        }
    }



    fun fetchAllNumber() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = getAllNumbersUseCase()
                _state.value = _state.value.copy(
                    values = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    errorMessage = "Lỗi tải danh sách chữ số",
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: NumberEvent) {
        when (event) {
            is NumberEvent.SelectNumber -> fetchNumber(event.id)
            is NumberEvent.PlayAudio -> onSpeak?.invoke(event.text)
        }
    }
}