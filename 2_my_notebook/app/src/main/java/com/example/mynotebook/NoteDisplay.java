package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoteDisplay extends AppCompatActivity {

    private TextView displayNoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_display);

        displayNoteTextView = findViewById(R.id.display_note_text_view);

        Intent intent = getIntent();
        displayNoteTextView.setText(intent.getStringExtra(MainActivity.SAVED_NOTE));
    }


}
