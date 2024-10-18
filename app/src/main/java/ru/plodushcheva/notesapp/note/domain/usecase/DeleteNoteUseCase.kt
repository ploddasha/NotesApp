package ru.plodushcheva.notesapp.note.domain.usecase

import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(noteId: Int) =
        noteRepository.delete(noteId)
}