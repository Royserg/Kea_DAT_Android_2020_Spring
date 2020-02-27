package com.example.mynotebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;
import com.example.mynotebook.databinding.FragmentAddEditNoteBinding;
import com.example.mynotebook.models.Note;

public class AddEditNoteFragment extends Fragment {

    private String noteId;
    FragmentAddEditNoteBinding binding;
    private NoteViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit_note, container, false);
        View v = binding.getRoot();

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveNote();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem addIcon = menu.findItem(R.id.addEditNoteFragment);
        MenuItem saveIcon = menu.findItem(R.id.save_note);
        if (addIcon != null) {
            addIcon.setVisible(false);
        }
        if (saveIcon != null) {
            saveIcon.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            // Close Fragment
            Navigation.findNavController(getActivity(), R.id.main_content).navigateUp();
        }
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
        // Set note to the fields if exists
        noteId = getArguments().getString(NoteListFragment.NOTE_ID_KEY, null);
        String title = getArguments().getString(NoteListFragment.NOTE_TITLE_KEY, null);
        String content = getArguments().getString(NoteListFragment.NOTE_CONTENT_KEY, null);

        if (noteId != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit Note");
            Note noteToUpdate = new Note(noteId, title, content);
            binding.setNote(noteToUpdate);
        } else {
            binding.setNote(new Note("", "", ""));
        }
    }

    private void saveNote() {
        String title = binding.editTextTitle.getText().toString();
        String content = binding.editTextContent.getText().toString();
        if (title.trim().isEmpty() || content.trim().isEmpty()) {
            return;
        }

        Note note = new Note("", title, content);

        if (noteId != null) {
            note.setId(noteId);
            viewModel.update(note);
            return;
        }
        viewModel.insert(note);
    }
}
