package ru.plodushcheva.notesapp.notes.pesentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.plodushcheva.notesapp.common.data.NoteItem
import ru.plodushcheva.notesapp.notes.domain.usecase.GetNotesUseCase
import kotlin.coroutines.cancellation.CancellationException

class AllNotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val router: AllNotesRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<AllNotesState>(AllNotesState.Initial)
    val state: StateFlow<AllNotesState> = _state

    private var allNotes: List<NoteItem> = emptyList()
    internal var searchQuery = MutableStateFlow("")
    internal var showFavorites = MutableStateFlow(false)


    fun loadNotes() {
        if (_state.value is AllNotesState.Content || _state.value is AllNotesState.Loading) {
            return
        }

        viewModelScope.launch {
            _state.value = AllNotesState.Loading

            try {
                getNotesUseCase.invoke().collectLatest { notes ->
                    allNotes = notes
                    if (notes.isEmpty()) {
                        _state.value = AllNotesState.Empty
                    } else {
                        _state.value = AllNotesState.Content(notes)
                    }
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = AllNotesState.Failure(ex.localizedMessage.orEmpty())
            }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
        applyFilters()
    }

    fun toggleShowFavorites() {
        showFavorites.value = !showFavorites.value
        applyFilters()
    }

    private fun applyFilters() {
        val filteredNotes = allNotes
            .filter { it.title.contains(searchQuery.value, ignoreCase = true) }
            .filter { !showFavorites.value || it.isFavorite }

        if (filteredNotes.isEmpty()) {
            _state.value = AllNotesState.Empty
        } else {
            _state.value = AllNotesState.Content(filteredNotes)
        }
    }


    fun openRouter(noteId: Int?) {
        if (noteId != null) {
            router.openNote(noteId)
        } else {
            router.openNote(0)
        }
    }
}