package com.example.mynotebook.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotebook.R;
import com.example.mynotebook.data.NoteViewModel;
import com.example.mynotebook.databinding.NoteRowBinding;
import com.example.mynotebook.models.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> implements NoteClickListener {

    public Function<Note, Void> onDelete;
    public List<Note> notes = new ArrayList<>();
    private Context context;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public NotesAdapter(Context context) {
//        this.onDelete = onDelete;
        this.context = context;
//        ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
//        ViewModel viewModel = ViewModelProviders.of(context).get(NoteViewModel.class);
    }

    /* 3 methods to override from ViewHolder */
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteRow = inflater.inflate(R.layout.note_row, parent, false);

        return new NoteViewHolder(noteRow);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.bind(current);
        holder.binding.setNoteClickListener(this);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    /* Behavior when note row is clicked */
    @Override
    public void noteClicked(Note note) {
        Log.i("MyApp", "Note Clicked!!!");
        Toast.makeText(context, note.title + " clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteNote(Note note) {
        int index = notes.indexOf(note);
        notes.remove(note);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, notes.size());
    }


    /* === ViewHolder === */
    class NoteViewHolder extends RecyclerView.ViewHolder {

        private NoteRowBinding binding;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(Note note) {
            binding.setNote(note);
            binding.executePendingBindings();
        }
    }
}
