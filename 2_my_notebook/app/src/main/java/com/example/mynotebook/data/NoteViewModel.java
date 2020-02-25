package com.example.mynotebook.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynotebook.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends ViewModel {

    private MutableLiveData<List<Note>> notes;

    public LiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }

    public void insert(Note note) {
        List<Note> savedNotes = notes.getValue();

        List<Note> clonedNotes = new ArrayList<>(savedNotes);
        clonedNotes.add(note);

        notes.setValue(clonedNotes);
    }


    public void update(Note note) {
        List<Note> savedNotes = notes.getValue();

        List<Note> clonedNotes = new ArrayList<>(savedNotes);

        for (int i = 0; i < clonedNotes.size(); i++) {
            if (clonedNotes.get(i).id == note.id) {
                clonedNotes.set(i, note);
                break;
            }
        }
        notes.setValue(clonedNotes);
    }

    public void deleteOne(Note note) {
        List<Note> savedNotes = notes.getValue();
        List<Note> clonedNotes = new ArrayList<>(savedNotes);
        clonedNotes.remove(note);

        notes.setValue(clonedNotes);
    }

    public Note getNote(int id) {
        List<Note> savedNotes = notes.getValue();
        for (Note savedNote : savedNotes) {
            if (savedNote.id == id) return savedNote;
        }
        return new Note("No note found", "No note found");
    }

    /**
     * Load dummy data to the notes LiveData list
     */
    private void loadNotes() {
        List<Note> notesList = new ArrayList<>();
        notesList.add(new Note("First note", "The content number 1"));
        notesList.add(new Note("Second note", "The content number 2"));
        notesList.add(new Note("Third note", "The content number 3"));
        notesList.add(new Note("Forth note", "The content number 4"));
        notesList.add(new Note("Fifth note", "The content number 5"));

        notes.setValue(notesList);
    }


}
