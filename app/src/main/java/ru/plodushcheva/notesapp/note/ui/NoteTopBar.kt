package ru.plodushcheva.notesapp.note.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.plodushcheva.notesapp.R

@Composable
fun NoteTopBar(
    isFavorite: Boolean,
    onBackClicked: () -> Unit,
    onToggleFavorite: () -> Unit,
    onAttachImageClicked: () -> Unit,
    onDeleteNoteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onBackClicked() },
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

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }

            IconButton(onClick = onAttachImageClicked) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = stringResource(id = R.string.attach_file))
            }

            IconButton(onClick = onDeleteNoteClicked) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete_note))
            }
        }
    }
}
