package ru.plodushcheva.notesapp.notes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.common.data.converter.NoteItemConverter
import ru.plodushcheva.notesapp.common.data.db.NoteDao
import ru.plodushcheva.notesapp.notes.domain.repository.AllNotesRepository

class AllNotesRepositoryImpl(
    private val dao: NoteDao,
    private val noteItemConverter: NoteItemConverter
): AllNotesRepository {

    override suspend fun getAll(): Flow<List<NoteItem>> {
        return dao.getAllNotes().map { noteList ->
            noteList.map { noteItemConverter.convert(it) }
        }
    }
}