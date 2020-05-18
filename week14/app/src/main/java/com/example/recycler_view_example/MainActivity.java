package com.example.recycler_view_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.recycler_view_example.adapter.MyRecycleViewAdapter;
import com.example.recycler_view_example.auth.FirebaseManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConstraintLayout authView;
    private ConstraintLayout contentView;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseManager firebaseManager;

    private TextView emailInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseManager = new FirebaseManager(this);

        authView = findViewById(R.id.auth_view);
        contentView = findViewById(R.id.content_view);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecycleViewAdapter(getList()));

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
    }

    public void showContent() {
        authView.setVisibility(View.INVISIBLE);
        contentView.setVisibility(View.VISIBLE);
    }

    public void hideContent() {
        authView.setVisibility(View.VISIBLE);
        contentView.setVisibility(authView.INVISIBLE);
    }


    public void handleSignUp(View v) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            firebaseManager.signUp(email, password);
        }
    }

    public void handleSignIn(View v) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            firebaseManager.signIn(email, password);
        }
    }

    public void handleSignOut(View v) {
        firebaseManager.signOut();
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Hhuehue");
        list.add("Another thing");
        list.add("Extra string");
        list.add("Another");

        return list;
    }

}
