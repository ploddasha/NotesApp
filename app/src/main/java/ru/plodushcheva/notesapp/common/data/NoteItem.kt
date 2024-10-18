package ru.plodushcheva.notesapp.common.data

data class NoteItem(
    val id: Int,
    val title: String,
    val body: String,
    val imageUri: String? = null,
    val isFavorite: Boolean = false
)