package com.example.mynotebook.ui;

import com.example.mynotebook.models.Note;

public interface NoteClickListener {
    void noteClicked(Note note);
    void deleteNote(Note note);
}
