package ru.plodushcheva.notesapp.note.presentation

import ru.plodushcheva.notesapp.common.data.NoteItem

interface NoteState {

    data object Initial : NoteState
    data object Loading : NoteState
    data class Failure(val message: String?) : NoteState
    data class Content(val noteItem: NoteItem) : NoteState
}