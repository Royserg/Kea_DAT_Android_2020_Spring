package com.example.mynotebook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;
import com.example.mynotebook.models.Note;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NotesAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        viewModel.getNotes().observe(this, notes -> {
            adapter.setNotes(notes);
        });


        /* === Swipe Listener sort of === */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                adapter.deleteNote(index);
        }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener((position, note) -> {
            Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
            intent.putExtra(AddEditNoteActivity.EXTRA_POSITION, position);
            intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.id);
            intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.title);
            intent.putExtra(AddEditNoteActivity.EXTRA_CONTENT, note.content);
            startActivityForResult(intent, EDIT_NOTE_REQUEST);
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_btn) {
            openAddNoteActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAddNoteActivity() {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String content = data.getStringExtra(AddEditNoteActivity.EXTRA_CONTENT);

            Note note = new Note(title, content);
            viewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            int position = data.getIntExtra(AddEditNoteActivity.EXTRA_POSITION, -1);
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String content = data.getStringExtra(AddEditNoteActivity.EXTRA_CONTENT);

            Note note = new Note(title, content);
            note.setId(id);
            viewModel.update(position, note);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
