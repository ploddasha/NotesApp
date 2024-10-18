package ru.plodushcheva.notesapp.notes.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.plodushcheva.notesapp.R
import ru.plodushcheva.notesapp.common.data.NoteItem

@Composable
fun AllNotesContentComponent(
    noteItems: List<NoteItem>,
    onItemClicked: (noteId: Int?) -> Unit

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(noteItems) { note ->
            NoteItem(
                note,
                onItemClicked = { onItemClicked(note.id) }
            )
        }
    }

}

@Composable
fun AddNoteFloatingButton(
    onItemClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onItemClicked()
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_note))
    }
}