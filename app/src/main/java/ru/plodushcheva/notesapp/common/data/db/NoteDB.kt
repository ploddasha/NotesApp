package ru.plodushcheva.notesapp.common.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDB(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val body: String,
    val image: String?,
    val isFavorite: Boolean = false
)