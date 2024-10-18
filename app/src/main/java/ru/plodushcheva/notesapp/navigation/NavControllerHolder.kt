package ru.plodushcheva.notesapp.navigation

import androidx.navigation.NavController

interface NavControllerHolder {

    fun setNavController(navController: NavController)

    fun clearNavController()
}