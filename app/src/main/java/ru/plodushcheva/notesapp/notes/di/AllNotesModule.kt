package ru.plodushcheva.notesapp.notes.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.plodushcheva.notesapp.notes.domain.repository.AllNotesRepository
import ru.plodushcheva.notesapp.notes.domain.usecase.GetNotesUseCase
import ru.plodushcheva.notesapp.notes.data.repository.AllNotesRepositoryImpl
import ru.plodushcheva.notesapp.notes.pesentation.AllNotesViewModel
import ru.plodushcheva.notesapp.common.data.converter.NoteItemConverter


val allNotesModule = module {

    singleOf(::NoteItemConverter)

    singleOf(::AllNotesRepositoryImpl) bind AllNotesRepository::class

    factoryOf(::GetNotesUseCase)

    viewModelOf(::AllNotesViewModel)
}