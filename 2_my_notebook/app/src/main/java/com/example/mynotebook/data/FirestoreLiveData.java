package com.example.mynotebook.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mynotebook.models.Note;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirestoreLiveData<T> extends LiveData<T> {

    private ListenerRegistration registration;
    private CollectionReference colRef;

    public FirestoreLiveData(CollectionReference colRef) {
        this.colRef = colRef;
    }

    /* Event Listener */
    private EventListener<QuerySnapshot> eventListener = (queryDocumentSnapshots, e) -> {
        if (e != null) {
            Log.i("MyApp", "Listen failed " + e);
            return;
        }

        if (queryDocumentSnapshots != null) {
            List<Note> itemList = new ArrayList<>();
            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                itemList.add(
                        new Note(snap.getId(), snap.get("title").toString(), snap.get("content").toString())
                );
            }
            setValue((T) itemList);
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        registration = colRef.addSnapshotListener(eventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasActiveObservers()) {
            registration.remove();
            registration = null;
        }
    }
}
