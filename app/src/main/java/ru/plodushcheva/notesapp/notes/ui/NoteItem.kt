package ru.plodushcheva.notesapp.notes.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.plodushcheva.notesapp.common.data.NoteItem

@Composable
fun NoteItem(
    noteItem: NoteItem,
    onItemClicked: () -> Unit
) {
    Column (
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClicked)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 2.dp,
                    color = if (noteItem.isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = noteItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(text = noteItem.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}
