package ru.plodushcheva.notesapp.note

import kotlinx.serialization.Serializable

@Serializable
data class NoteRoute(
    val noteId: Int,
)