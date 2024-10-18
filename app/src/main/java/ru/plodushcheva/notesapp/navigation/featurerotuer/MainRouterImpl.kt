package ru.plodushcheva.notesapp.navigation.featurerotuer

import ru.plodushcheva.notesapp.main.presentation.MainRouter
import ru.plodushcheva.notesapp.navigation.GlobalRouter
import ru.plodushcheva.notesapp.notes.AllNotesRoute


class MainRouterImpl(private val globalRouter: GlobalRouter) : MainRouter {

    override fun openNotes() {
        globalRouter.openPoppingAllPrevious(AllNotesRoute)
    }

}