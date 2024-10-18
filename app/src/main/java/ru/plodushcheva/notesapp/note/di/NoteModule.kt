package ru.plodushcheva.notesapp.note.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.plodushcheva.notesapp.note.domain.repository.NoteRepository
import ru.plodushcheva.notesapp.note.presentation.NoteViewModel
import ru.plodushcheva.notesapp.note.domain.usecase.GetNoteUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.SaveNoteUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.DeleteNoteUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.ChangeFavoriteStatusUseCase

import ru.plodushcheva.notesapp.note.data.repository.NoteRepositoryImpl
import ru.plodushcheva.notesapp.common.data.converter.NoteDBConverter
import ru.plodushcheva.notesapp.common.data.converter.NoteItemConverter


val noteModel = module {

    singleOf(::NoteItemConverter)
    singleOf(::NoteDBConverter)

    singleOf(::NoteRepositoryImpl) bind NoteRepository::class

    factoryOf(::GetNoteUseCase)
    factoryOf(::SaveNoteUseCase)
    factoryOf(::DeleteNoteUseCase)
    factoryOf(::ChangeFavoriteStatusUseCase)

    viewModel {(noteId: Int) ->
        NoteViewModel(noteId, get(), get(), get(), get(), get())
    }
}