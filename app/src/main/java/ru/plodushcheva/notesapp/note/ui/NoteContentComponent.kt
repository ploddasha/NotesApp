package ru.plodushcheva.notesapp.note.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import ru.plodushcheva.notesapp.R
import ru.plodushcheva.notesapp.common.data.NoteItem

@Composable
fun NoteContentComponent(
    noteItem: NoteItem,
    onSaveClicked: (String, String, String?) -> Unit,
    onBackClicked: () -> Unit
) {
    var title by remember { mutableStateOf(noteItem.title) }
    var body by remember { mutableStateOf(noteItem.body) }
    val imageUri by remember { mutableStateOf(noteItem.imageUri) }

    Scaffold(
        floatingActionButton = {
            SaveNoteFloatingButton(
                onItemClicked = {
                    onSaveClicked(title, body, imageUri)
                    onBackClicked()
                }
            )
        },
        modifier = Modifier.imePadding(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                EditableNoteFields(
                    title = title,
                    body = body,
                    onTitleChange = { title = it },
                    onBodyChange = { body = it }
                )
                DisplayImage(noteItem.imageUri)

            }
        }
    )

}

@Composable
fun DisplayImage(imageUri: String?) {
    imageUri?.let {
        Image(
            painter = rememberAsyncImagePainter(it),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}


@Composable
fun SaveNoteFloatingButton(
    onItemClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onItemClicked()
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(imageVector = Icons.Default.Done, contentDescription = stringResource(R.string.save_note))
    }
}