package ru.plodushcheva.notesapp.note.data.repository

import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.common.data.converter.NoteDBConverter
import ru.plodushcheva.notesapp.common.data.converter.NoteItemConverter
import ru.plodushcheva.notesapp.common.data.db.NoteDB
import ru.plodushcheva.notesapp.common.data.db.NoteDao
import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val dao: NoteDao,
    private val noteDBConverter: NoteDBConverter,
    private val noteItemConverter: NoteItemConverter
) : NoteRepository {

    override suspend fun get(noteId: Int): NoteItem {
        val model = dao.getNoteById(noteId)
        return if (model != null) {
            noteItemConverter.convert(model)
        } else {
            NoteItem(id = noteId, title = "", body = "", imageUri = "", isFavorite = false)
        }
    }

    override suspend fun save(note: NoteItem) {
        dao.insertNote(noteDBConverter.convert(note))
    }

    override suspend fun delete(noteId: Int) {
        dao.deleteNoteById(noteId)
    }

    override suspend fun changeFavoriteStatus(noteId: Int, isFavorite: Boolean) {
        dao.updateFavoriteStatus(noteId, isFavorite)
    }
}