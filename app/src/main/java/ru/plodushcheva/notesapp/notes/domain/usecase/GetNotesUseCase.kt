package ru.plodushcheva.notesapp.notes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.notes.domain.repository.AllNotesRepository

class GetNotesUseCase(private val repository: AllNotesRepository) {

    suspend operator fun invoke(): Flow<List<NoteItem>> =
        repository.getAll()
}