package ru.plodushcheva.notesapp.note.domain.usecase

import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository

class GetNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(noteId: Int): NoteItem =
        noteRepository.get(noteId)
}