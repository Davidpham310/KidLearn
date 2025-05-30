package com.example.kidlearn.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.StartLoading -> startLoading()
        }
    }

    private fun startLoading() {
        viewModelScope.launch {
            val totalTime = 10_000L
            val steps = 100
            val delayPerStep = totalTime / steps

            repeat(steps) { i ->
                val progress = (i + 1) / steps.toFloat()
                _state.value = _state.value.copy(progress = progress)
                delay(delayPerStep)
            }
        }
    }
}
