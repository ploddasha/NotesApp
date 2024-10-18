package ru.plodushcheva.notesapp.main.presentation


import ru.plodushcheva.notesapp.notes.AllNotesRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    NOTES(AllNotesRoute::class)
}