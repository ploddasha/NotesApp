package ru.plodushcheva.notesapp.navigation.featurerotuer

import ru.plodushcheva.notesapp.navigation.GlobalRouter
import ru.plodushcheva.notesapp.note.presentation.NoteRouter

class NoteRouterImpl(private val globalRouter: GlobalRouter) : NoteRouter {

    override fun goBack() {
        globalRouter.pop()
    }
}