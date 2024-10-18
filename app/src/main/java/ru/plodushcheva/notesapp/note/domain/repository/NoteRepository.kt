package ru.plodushcheva.notesapp.note.domain.repository

import ru.plodushcheva.notesapp.common.data.NoteItem

interface NoteRepository {

    suspend fun get(noteId: Int): NoteItem

    suspend fun save(note: NoteItem)

    suspend fun delete(noteId: Int)

    suspend fun changeFavoriteStatus(noteId: Int, isFavorite: Boolean)
}