package com.tooensure.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tooensure.notes.data.NoteDataSource
import com.tooensure.notes.model.Note
import com.tooensure.notes.screen.NoteScreen
import com.tooensure.notes.ui.theme.NotesTheme
import com.tooensure.notes.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface() {
                    val noteViewModel :NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModel)

                }
            }
        }
    }
}


@Composable
fun NotesApp(noteViewModel: NoteViewModel)
{
    val noteList = NoteViewModel().getAllNotes()
    NoteScreen(
        notes = noteList,
        onAddNote = { note -> noteViewModel.addNote(note = note) },
        onRemoveNote = { note -> noteViewModel.removeNote(note = note) }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        Greeting("Android")
    }
}