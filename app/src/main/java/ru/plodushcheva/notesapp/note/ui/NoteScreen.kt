package ru.plodushcheva.notesapp.note.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import ru.plodushcheva.notesapp.note.presentation.NoteState
import ru.plodushcheva.notesapp.note.presentation.NoteViewModel

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
) {
    val noteState by noteViewModel.state.collectAsState()

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                noteViewModel.attachImage(it.toString())
            }
        }
    )

    LaunchedEffect(Unit) {
        noteViewModel.loadNote()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { noteViewModel.goBack() },
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = stringResource(id = R.string.note),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        noteViewModel.toggleFavorite()
                    }
                ) {
                    val isFavorite = if (noteState is NoteState.Content) {
                        (noteState as NoteState.Content).noteItem.isFavorite
                    } else {
                        false
                    }

                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite)
                    )
                }

                IconButton(
                    onClick = {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = stringResource(id = R.string.attach_file))
                }

                IconButton(
                    onClick = {
                        noteViewModel.deleteNote()
                        noteViewModel.goBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delete_note)
                    )
                }
            }
        }

        when (val state = noteState) {
            is NoteState.Initial,
            is NoteState.Loading -> LoadingComponent()

            is NoteState.Failure -> ErrorComponent(
                message = state.message ?: stringResource(id = R.string.error_unknown_error),
                onRetry = {
                    noteViewModel.loadNote()
                }
            )

            is NoteState.Content -> NoteContentComponent(
                noteItem = state.noteItem,
                onSaveClicked = noteViewModel::saveNote,
                onBackClicked = noteViewModel::goBack
            )
        }
    }
}