package ru.plodushcheva.notesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.plodushcheva.notesapp.common.data.db.dataBaseModule
import ru.plodushcheva.notesapp.main.di.mainModule
import ru.plodushcheva.notesapp.navigation.navigationModule
import ru.plodushcheva.notesapp.note.di.noteModel
import ru.plodushcheva.notesapp.notes.di.allNotesModule

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@NotesApplication)
            modules(
                navigationModule,
                mainModule,
                allNotesModule,
                noteModel,
                dataBaseModule
            )
        }
    }
}