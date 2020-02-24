/*
* NOT USED => Left for future implementation
*
*
* */

package com.example.mynotebook.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mynotebook.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        // Fill list with dummy data
        allNotes = fillNotes();
    }

    private MutableLiveData<List<Note>> fillNotes() {
        List<Note> dummyNotes = new ArrayList<>();
        dummyNotes.add(new Note("Note 1", "Content of note 1"));
        dummyNotes.add(new Note("Note 2", "Content of note 2"));
        dummyNotes.add(new Note("Note 3", "Content of note 3"));

        MutableLiveData<List<Note>> dummyList = new MutableLiveData<>();
        dummyList.setValue(dummyNotes);

        return dummyList;
    }

}
