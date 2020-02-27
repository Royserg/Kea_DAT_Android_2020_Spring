package com.example.mynotebook.repositories;


import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mynotebook.data.FirestoreLiveData;
import com.example.mynotebook.models.Note;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteRepository {

    private final String collectionName = "notes";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    FirestoreLiveData firestoreLiveData;

    public NoteRepository() {
//        firestoreLiveData =
    }

    public LiveData<List<Note>> notesListening() {
        return new FirestoreLiveData<>(db.collection(collectionName));
    }

    public void addNote(Note note) {
        DocumentReference docRef = db.collection(collectionName).document();
        Map<String, String> map = new HashMap<>();
        map.put("title", note.title);
        map.put("content", note.content);
        docRef.set(map);
    }

    public void deleteNote(String id) {
        DocumentReference docRef = db.collection(collectionName).document(id);
        docRef.delete();
    }

    public void editNote(Note note) {
        DocumentReference docRef = db.collection(collectionName).document(note.id);
        Map<String, String> map = new HashMap<>();
        map.put("title", note.title);
        map.put("content", note.content);
        docRef.set(map);
    }
}
