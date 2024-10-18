package ru.plodushcheva.notesapp.main.presentation

data class MainState(
    val navigationOptions: List<NavigationOption>,
    val selectedNavOption: NavigationOption?,
)