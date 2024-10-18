package ru.plodushcheva.notesapp.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDB::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}