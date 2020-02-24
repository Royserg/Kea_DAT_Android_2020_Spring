package com.example.mynotebook.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;
import com.example.mynotebook.databinding.FragmentNoteListBinding;
import com.example.mynotebook.models.Note;


public class NoteListFragment extends Fragment {

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

        adapter = new NotesAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
        viewModel.getNotes().observe(getViewLifecycleOwner(), notes -> adapter.setNotes(notes));
    }
}
