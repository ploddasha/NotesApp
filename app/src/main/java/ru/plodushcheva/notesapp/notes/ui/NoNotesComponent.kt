package ru.plodushcheva.notesapp.notes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.plodushcheva.notesapp.R

@Composable
fun NoNotesComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image: Painter = painterResource(id = R.drawable.writing_woman)
        Image(
            painter = image,
            contentDescription = stringResource(id = R.string.no_notes),
            modifier = Modifier.padding(16.dp).border(BorderStroke(2.dp, MaterialTheme.colorScheme.scrim))
        )
        Text(
            text = stringResource(id = R.string.no_notes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}