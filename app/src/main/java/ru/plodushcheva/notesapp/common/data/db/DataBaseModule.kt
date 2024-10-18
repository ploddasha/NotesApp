package ru.plodushcheva.notesapp.common.data.db

import org.koin.dsl.module
import androidx.room.Room

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    single {
        get<NoteDatabase>().noteDao()
    }
}
