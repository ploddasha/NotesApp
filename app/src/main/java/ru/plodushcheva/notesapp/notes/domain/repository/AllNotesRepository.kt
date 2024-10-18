package ru.plodushcheva.notesapp.notes.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.plodushcheva.notesapp.common.data.NoteItem

interface AllNotesRepository {

    suspend fun getAll(): Flow<List<NoteItem>>
}