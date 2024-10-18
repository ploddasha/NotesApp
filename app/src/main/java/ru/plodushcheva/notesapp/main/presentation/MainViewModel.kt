package ru.plodushcheva.notesapp.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val router: MainRouter,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState(
        NavigationOption.entries,
        NavigationOption.NOTES,
    ))
    val state: StateFlow<MainState> = _state

    fun handleOpenedScreen(option: NavigationOption?) {
        _state.value = _state.value.copy(selectedNavOption = option)
    }
}