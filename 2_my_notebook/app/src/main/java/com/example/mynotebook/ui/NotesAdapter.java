package com.example.mynotebook.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotebook.R;
import com.example.mynotebook.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public List<Note> notes = new ArrayList<>();
    private onItemClickListener listener;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.textView.setText(current.title);
        /* Delete button onClick listener - delete note */
        holder.deleteBtn.setOnClickListener(v -> {
            deleteNote(position);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void deleteNote(int index) {
        notes.remove(index);
        notifyItemRemoved(index);
    }

    /* === ViewHolder === */
    class NoteViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerView;
        TextView textView;
        ImageButton deleteBtn;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.note_row);
            textView = itemView.findViewById(R.id.note_row_text);
            deleteBtn = itemView.findViewById(R.id.note_row_delete_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(position, notes.get(position));
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position, Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
