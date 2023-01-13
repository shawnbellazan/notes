package com.tooensure.notes.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tooensure.notes.R
import com.tooensure.notes.components.NoteButton
import com.tooensure.notes.components.NoteInputText
import com.tooensure.notes.data.NoteDataSource
import com.tooensure.notes.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit

)
{
    var title = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }



    Column(
        modifier = Modifier.padding(6.dp)
    ) {
        // Top Navigation
        TopAppBar(
            modifier = Modifier,
            title = {
                Text(text = stringResource(id = R.string.note_screen))
            }, actions = { 
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notify Icon")
            },
        )
        val context = LocalContext.current
        // Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            NoteInputText(
                modifier = Modifier.padding(10.dp),
                text = title.value,
                label = "title",
                onTextChanged = {
                    if(it.all
                        {char ->
                            char.isLetter() || char.isWhitespace()
                        
                        })
                    {
                        title.value = it
                    }
                })

            NoteInputText(
                text = description.value,
                label = "description",
                onTextChanged = {
                    if(it.all
                        {char ->
                            char.isLetter() || char.isWhitespace()

                        })
                    {
                        description.value = it
                    }
                })

            NoteButton(text = "save", onClick = {
                if (title.value.isNotEmpty() && description.value.isNotEmpty())
                {
                    // save/add to list
                    onAddNote(
                        Note(
                            title = title.value,
                            description = description.value
                        )
                    )
                    title.value = ""
                    description.value = ""
                    Toast.makeText(
                        context,
                        "Note Added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn()
        {
            items(notes) {note ->
                NoteRow(
                    note = note,
                    onNoteClick = {
                        onRemoveNote(it)
                    })

            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit
)
{
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Text(
                text = note.title,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview()
{
    NoteScreen(
        notes = NoteDataSource().loadNotes(),
        onAddNote = { },
        onRemoveNote = { }
    )
}