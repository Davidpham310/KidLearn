package com.example.kidlearn.presentation.learn_letters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidlearn.domain.model.Letter
import com.example.kidlearn.domain.usecase.GetLetterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LetterViewModel @Inject constructor(
    private val getLetterByIdUseCase: GetLetterByIdUseCase
) : ViewModel() {

    private val _letter = mutableStateOf<Letter?>(null)
    val letter: State<Letter?> = _letter

    fun fetchLetter(id: String) {
        viewModelScope.launch {
            val result = getLetterByIdUseCase(id)
            _letter.value = result
        }
    }

    fun onEvent(event: LetterEvent) {
        when (event) {
            is LetterEvent.PlayAudio -> {
                // TODO: phát âm thanh bằng MediaPlayer hoặc ExoPlayer
            }
        }
    }
}
