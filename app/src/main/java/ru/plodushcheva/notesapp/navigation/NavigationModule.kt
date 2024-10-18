package ru.plodushcheva.notesapp.navigation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import ru.plodushcheva.notesapp.main.presentation.MainRouter
import ru.plodushcheva.notesapp.note.presentation.NoteRouter
import ru.plodushcheva.notesapp.notes.pesentation.AllNotesRouter
import ru.plodushcheva.notesapp.navigation.featurerotuer.MainRouterImpl
import ru.plodushcheva.notesapp.navigation.featurerotuer.NoteRouterImpl
import ru.plodushcheva.notesapp.navigation.featurerotuer.AllNotesRouterImpl


val navigationModule = module {
    singleOf(::GlobalRouterImpl) binds arrayOf(GlobalRouter::class, NavControllerHolder::class)

    singleOf(::MainRouterImpl) bind MainRouter::class
    singleOf(::AllNotesRouterImpl) bind AllNotesRouter::class
    singleOf(::NoteRouterImpl) bind NoteRouter::class
}