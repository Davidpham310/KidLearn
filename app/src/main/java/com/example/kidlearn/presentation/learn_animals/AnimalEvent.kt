package com.example.kidlearn.presentation.learn_animals

sealed class AnimalEvent {
    data class PlayAudio(val text: String) : AnimalEvent()
    data class SelectAnimal(val id: String) : AnimalEvent()
}