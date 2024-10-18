package ru.plodushcheva.notesapp.notes.pesentation

import ru.plodushcheva.notesapp.common.data.NoteItem

sealed interface AllNotesState {

    data object Initial : AllNotesState
    data object Loading : AllNotesState
    data class Failure(val message: String?) : AllNotesState
    data object Empty : AllNotesState
    data class Content(val noteItems: List<NoteItem>) : AllNotesState
}