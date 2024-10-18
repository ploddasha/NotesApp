package ru.plodushcheva.notesapp.navigation.featurerotuer

import ru.plodushcheva.notesapp.navigation.GlobalRouter
import ru.plodushcheva.notesapp.note.NoteRoute
import ru.plodushcheva.notesapp.notes.pesentation.AllNotesRouter

class AllNotesRouterImpl(private val globalRouter: GlobalRouter) : AllNotesRouter {

    override fun openNote(noteId: Int) {
        globalRouter.open(NoteRoute(noteId))
    }
}