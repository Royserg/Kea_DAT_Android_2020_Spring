package com.example.fragments_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragments_app.MainActivity;
import com.example.fragments_app.R;
import com.example.fragments_app.models.Person;
import com.example.fragments_app.storage.People;

public class DetailsFragment extends Fragment {

    private int position;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView hobbyTextView;
    private Button backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        nameTextView = view.findViewById(R.id.name_text_view);
        ageTextView = view.findViewById(R.id.age_text_view);
        hobbyTextView = view.findViewById(R.id.hobby_text_view);
        backButton = view.findViewById(R.id.back_button);

        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }

        Person person = People.getPerson(position);
        nameTextView.setText(person.getName());
        ageTextView.setText(String.valueOf(person.getAge()));
        hobbyTextView.setText(person.getHobby());


        backButton.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            activity.displayFragment(new ListFragment());
        });

        return view;
    }
}
