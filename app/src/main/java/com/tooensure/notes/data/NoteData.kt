package com.tooensure.notes.data

import com.tooensure.notes.model.Note

class NoteDataSource()
{
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Have A nice day", description = "Wake up and smile"),
            Note(title = "Finish project", description = "Metro Transit project must complete"),
            Note(title = "Exercise", description = "Take a walk"),
        )
    }
}