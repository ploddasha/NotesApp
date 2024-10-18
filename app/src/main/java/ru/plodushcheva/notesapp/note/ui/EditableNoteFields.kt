package ru.plodushcheva.notesapp.note.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.plodushcheva.notesapp.R

@Composable
fun EditableNoteFields(
    title: String,
    body: String,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit
) {
    Column {
        NoteText(
            text = title,
            hint = stringResource(R.string.title),
            onValueChange = onTitleChange,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            isHintVisible = title.isEmpty()
        )
        Spacer(Modifier.height(16.dp))
        NoteText(
            text = body,
            hint = stringResource(R.string.body),
            onValueChange = onBodyChange,
            textStyle = MaterialTheme.typography.bodyLarge,
            isHintVisible = body.isEmpty()
        )
    }
}


@Composable
fun NoteText(
    text: String,
    hint: String,
    isHintVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    singleLine: Boolean = false
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle,
        modifier = Modifier
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = Color.DarkGray
                )
            }
            innerTextField()
        }
    )
}
