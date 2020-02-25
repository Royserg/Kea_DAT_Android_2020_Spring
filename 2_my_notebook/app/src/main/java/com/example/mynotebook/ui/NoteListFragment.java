package com.example.mynotebook.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;
import com.example.mynotebook.databinding.FragmentNoteListBinding;
import com.example.mynotebook.models.Note;

import java.util.List;


public class NoteListFragment extends Fragment implements NotesAdapter.NoteCallback {
    public static final String NOTE_ID_KEY = "com.example.mynotebook.ui.NOTE_ID_KEY";

    private FragmentNoteListBinding binding;
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NoteViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false);
        View v = binding.getRoot();

        recyclerView = binding.noteListRecyclerView;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NotesAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);

        viewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
            adapter.submitList(notes);
        });
    }

    @Override
    public void onDeletePressed(Note note) {
        viewModel.deleteOne(note);
    }

    @Override
    public void onNotePressed(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(NOTE_ID_KEY, id);
        NavController navcontroller = Navigation.findNavController(getActivity(), R.id.main_content);
        navcontroller.navigate(R.id.addEditNoteFragment, bundle);
    }
}
