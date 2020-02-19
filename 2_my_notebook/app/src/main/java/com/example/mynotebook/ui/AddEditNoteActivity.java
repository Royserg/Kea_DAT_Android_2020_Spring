package com.example.mynotebook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotebook.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.mynotebook.ui.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.mynotebook.ui.EXTRA_TITLE";
    public static final String EXTRA_CONTENT = "com.example.mynotebook.ui.EXTRA_CONTENT";
    public static final String EXTRA_POSITION = "com.example.mynotebook.ui.EXTRA_POSITION";

    private EditText editTextTitle;
    private EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextContent.setText(intent.getStringExtra(EXTRA_CONTENT));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();

        if (title.trim().isEmpty() || content.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and content", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_CONTENT, content);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, -1));
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
