package ru.plodushcheva.notesapp.common.data.converter

import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.common.data.db.NoteDB

class NoteDBConverter {

    fun convert(note: NoteItem): NoteDB =
        NoteDB(
            id = note.id,
            title = note.title,
            body = note.body,
            image = note.imageUri,
            isFavorite = note.isFavorite
        )
}