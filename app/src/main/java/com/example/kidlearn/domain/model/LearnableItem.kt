package com.example.kidlearn.domain.model

sealed class LearnableItem {
    abstract val id: String
    abstract val displayTitle: String
    abstract val imageRes: String
    abstract val textToRead: String

    data class Letter(
        override var id: String = "",
        var title: String = "",
        override var imageRes: String = "",
        override var textToRead: String = ""
    ) : LearnableItem() {
        override val displayTitle: String get() = title
    }

    data class Number(
        override var id: String = "",
        var value: String = "",
        override var imageRes: String = "",
        override var textToRead: String = ""
    ) : LearnableItem() {
        override val displayTitle: String get() = value
    }

    data class Animal(
        override var id: String = "",
        var name: String = "",
        override var imageRes: String = "",
        override var textToRead: String = ""
    ) : LearnableItem() {
        override val displayTitle: String get() = name
    }
}
