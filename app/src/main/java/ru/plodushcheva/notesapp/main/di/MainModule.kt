package ru.plodushcheva.notesapp.main.di

import ru.plodushcheva.notesapp.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)

}