package com.example.fragments_app.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fragments_app.MainActivity;
import com.example.fragments_app.R;
import com.example.fragments_app.fragments.DetailsFragment;
import com.example.fragments_app.models.Person;
import com.example.fragments_app.storage.People;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    MainActivity activity;
    List<Person> people = People.getPeople();
    private static LayoutInflater inflater = null;

    public ListAdapter(MainActivity activity) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.list_item, null);
        }
        TextView textView = rowView.findViewById(R.id.item_text_view);

        Person person = people.get(position);

        textView.setText(person.getName());

        /* Display Details Fragment when Row pressed, passing `position` argument */
        rowView.setOnClickListener(view -> {
            Fragment DetailsFragment = new DetailsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            DetailsFragment.setArguments(args);

            activity.displayFragment(DetailsFragment);

        });
        return rowView;
    }
}
