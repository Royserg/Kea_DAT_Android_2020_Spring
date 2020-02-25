package com.example.mynotebook.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotebook.R;
import com.example.mynotebook.databinding.NoteRowBinding;
import com.example.mynotebook.models.Note;

import java.util.List;

public class NotesAdapter extends ListAdapter<Note, NotesAdapter.NoteViewHolder> implements NoteClickListener {
    private NoteCallback callback;
    private Context context;

    public NotesAdapter(Context context, NoteCallback callback) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.callback = callback;
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.title.equals(newItem.title) &&
                    oldItem.content.equals(newItem.content);
        }
    };

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
        Note current = getItem(position);
        holder.bind(current);
        holder.binding.setNoteClickListener(this);
    }

    /* Callback interface */
    public interface NoteCallback{
        void onDeletePressed(Note note);
        void onNotePressed(int id);
    }

    /* Behavior when note row is clicked */
    @Override
    public void noteClicked(Note note) {
//        Toast.makeText(context, note.title + " clicked", Toast.LENGTH_SHORT).show();
        callback.onNotePressed(note.id);
    }

    /* When trash button clicked */
    @Override
    public void deleteNote(Note note) {
        callback.onDeletePressed(note);
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
