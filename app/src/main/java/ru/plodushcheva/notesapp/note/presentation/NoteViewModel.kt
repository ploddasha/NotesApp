package ru.plodushcheva.notesapp.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.note.domain.usecase.ChangeFavoriteStatusUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.DeleteNoteUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.GetNoteUseCase
import ru.plodushcheva.notesapp.note.domain.usecase.SaveNoteUseCase
import kotlin.coroutines.cancellation.CancellationException

class NoteViewModel(
    private val noteId: Int,
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val changeFavoriteStatusUseCase: ChangeFavoriteStatusUseCase,
    private val router: NoteRouter
) : ViewModel() {

    private val _state = MutableStateFlow<NoteState>(NoteState.Initial)
    val state: StateFlow<NoteState> = _state

    fun loadNote() {
        if (_state.value is NoteState.Content || _state.value is NoteState.Loading) {
            return
        }

        viewModelScope.launch {
            _state.value = NoteState.Loading

            try {
                val note = getNoteUseCase(noteId)
                _state.value = NoteState.Content(note)
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = NoteState.Failure(ex.message)
            }
        }
    }

    fun attachImage(imageUri: String) {
        _state.value = (state.value as? NoteState.Content)?.copy(
            noteItem = (state.value as? NoteState.Content)?.noteItem?.copy(imageUri = imageUri) ?: return
        )!!
    }

    fun toggleFavorite() {
        val currentState = _state.value
        if (currentState is NoteState.Content) {
            val isFavorite = currentState.noteItem.isFavorite

            val updatedNote = currentState.noteItem.copy(isFavorite = !isFavorite)
            _state.value = NoteState.Content(updatedNote)

            viewModelScope.launch {
                changeFavoriteStatusUseCase(noteId, !isFavorite)
            }
        }
    }


    fun saveNote(title: String, body: String, imageUri: String?) {
        val currentState = _state.value
        if (currentState is NoteState.Content) {
            val isFavorite = currentState.noteItem.isFavorite

            viewModelScope.launch {
                saveNoteUseCase(NoteItem(noteId, title, body, imageUri, isFavorite))
            }
        }


    }

    fun deleteNote() {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }

    fun goBack() {
        router.goBack()
    }
}