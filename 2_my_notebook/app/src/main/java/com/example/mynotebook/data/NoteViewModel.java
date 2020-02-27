package com.example.mynotebook.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynotebook.models.Note;
import com.example.mynotebook.repositories.NoteRepository;
import java.util.List;

public class NoteViewModel extends ViewModel {

    private NoteRepository noteRepository = new NoteRepository();

    public LiveData<List<Note>> getNotes() {
        return noteRepository.notesListening();
    }


    public void insert(Note note) {
        noteRepository.addNote(note);
    }


    public void update(Note note) {
        noteRepository.editNote(note);
    }

    public void deleteOne(String id) {
        noteRepository.deleteNote(id);
    }

}
