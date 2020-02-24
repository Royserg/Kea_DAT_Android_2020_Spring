package com.example.mynotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;

public class AddEditNoteFragment extends Fragment {

//    public static final String EXTRA_ID = "com.example.mynotebook.ui.EXTRA_ID";
//    public static final String EXTRA_TITLE = "com.example.mynotebook.ui.EXTRA_TITLE";
//    public static final String EXTRA_CONTENT = "com.example.mynotebook.ui.EXTRA_CONTENT";
//    public static final String EXTRA_POSITION = "com.example.mynotebook.ui.EXTRA_POSITION";

    private EditText editTextTitle;
    private EditText editTextContent;
    private NoteViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_edit_note, container, false);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Add Note");
        return v;
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.addEditNoteFragment);
        if (item != null) {
            item.setVisible(false);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
        // Set note to the fields if exists
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();

        if (title.trim().isEmpty() || content.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert title and content", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();


//        int id = getIntent().getIntExtra(EXTRA_ID, -1);
//        if (id != -1) {
//            data.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, -1));
//            data.putExtra(EXTRA_ID, id);
//        }
    }

}
