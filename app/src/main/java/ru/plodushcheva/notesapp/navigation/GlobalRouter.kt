package ru.plodushcheva.notesapp.navigation

interface GlobalRouter {

    fun open(route: Any)

    fun openPoppingAllPrevious(route: Any)

    fun pop()
}