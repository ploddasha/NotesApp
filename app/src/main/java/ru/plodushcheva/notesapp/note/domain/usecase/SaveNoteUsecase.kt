package ru.plodushcheva.notesapp.note.domain.usecase

import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: NoteItem) =
        noteRepository.save(note)
}