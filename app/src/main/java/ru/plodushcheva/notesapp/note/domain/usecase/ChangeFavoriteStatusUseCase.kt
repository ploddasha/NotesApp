package ru.plodushcheva.notesapp.note.domain.usecase

import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository

class ChangeFavoriteStatusUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(noteId: Int, isFavorite: Boolean) =
        noteRepository.changeFavoriteStatus(noteId, isFavorite)
}