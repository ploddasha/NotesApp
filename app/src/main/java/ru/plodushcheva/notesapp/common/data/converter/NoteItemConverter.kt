package ru.plodushcheva.notesapp.common.data.converter

import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.common.data.db.NoteDB

class NoteItemConverter {

    fun convert(note: NoteDB): NoteItem =
        NoteItem(
            id = note.id,
            title = note.title,
            body = note.body,
            imageUri = note.image,
            isFavorite = note.isFavorite
        )
}