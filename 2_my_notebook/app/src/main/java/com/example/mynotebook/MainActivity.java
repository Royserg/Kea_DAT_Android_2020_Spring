package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String SAVED_NOTE = "myapplication.saved_note";
    private EditText noteField;
    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteField = findViewById(R.id.note_text_field);
    }

    public void saveNote(View view) {
        note = noteField.getText().toString();
    }

    public void displayNotePage(View view) {
        Intent intent = new Intent(this, NoteDisplay.class);
        // Pass note
        intent.putExtra(SAVED_NOTE, note);
        startActivity(intent);
    }
}
