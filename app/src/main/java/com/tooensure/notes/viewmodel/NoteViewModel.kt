package com.tooensure.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.tooensure.notes.data.NoteDataSource
import com.tooensure.notes.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableListOf<Note>()


    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }
    fun addNote(note: Note)
    {
        noteList.add(note)
    }

    fun removeNote(note: Note)
    {
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note>
    {
        return noteList
    }
}