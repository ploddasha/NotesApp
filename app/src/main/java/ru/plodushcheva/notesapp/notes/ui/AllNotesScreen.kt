package ru.plodushcheva.notesapp.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.plodushcheva.notesapp.R
import ru.plodushcheva.notesapp.notes.pesentation.AllNotesState
import ru.plodushcheva.notesapp.notes.pesentation.AllNotesViewModel

@Composable
fun AllNotesScreen(
    allNotesViewModel: AllNotesViewModel
) {
    val allNotesState by allNotesViewModel.state.collectAsState()
    val showFavorites by allNotesViewModel.showFavorites.collectAsState()
    val searchQuery by allNotesViewModel.searchQuery.collectAsState()

    LaunchedEffect(Unit) {
        allNotesViewModel.loadNotes()
    }
    Scaffold(
        floatingActionButton = {
            AddNoteFloatingButton(
                onItemClicked = { allNotesViewModel.openRouter(null) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 32.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.all_notes_title),
                        style = MaterialTheme.typography.titleLarge,
                    )

                    IconButton(
                        onClick = {
                            allNotesViewModel.toggleShowFavorites()
                        }
                    ) {
                        Icon(
                            imageVector = if (showFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.show_favorites)
                        )
                    }
                }

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { allNotesViewModel.updateSearchQuery(it) },

                    label = { Text(stringResource(id = R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                )

                when (val state = allNotesState) {
                    is AllNotesState.Initial,
                    is AllNotesState.Loading -> LoadingComponent()

                    is AllNotesState.Failure -> ErrorComponent(
                        message = state.message ?: stringResource(id = R.string.error_unknown_error),
                        onRetry = { allNotesViewModel.loadNotes() },
                    )

                    is AllNotesState.Empty -> NoNotesComponent()

                    is AllNotesState.Content -> AllNotesContentComponent(
                        noteItems = state.noteItems,
                        onItemClicked = allNotesViewModel::openRouter,
                    )
                }
            }
        }
    )
}

