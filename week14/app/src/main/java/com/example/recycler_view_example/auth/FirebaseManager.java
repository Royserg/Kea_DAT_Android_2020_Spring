package com.example.recycler_view_example.auth;

import androidx.annotation.NonNull;

import com.example.recycler_view_example.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseManager {

    private MainActivity activity;
    private FirebaseAuth auth;

    public FirebaseManager(MainActivity activity) {
        this.activity = activity;
        this.auth = FirebaseAuth.getInstance();
        setupAuthStateListener();
    }

    private void setupAuthStateListener() {
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    System.out.println("Signed Out from FB");
                    activity.hideContent();
                }
            }
        });
    }


    public void signUp(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        System.out.println("SignUp SUCCESS " + task.getResult().getUser().getEmail());
                    } else {
                        System.out.println("SignUp FAILED " + task.getException());
                    }
                }
            });
    }

    public void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Hide auth view and show hidden content
                        activity.showContent();
                        System.out.println("Login SUCCESS " + task.getResult().getUser().getEmail());
                    } else {
                        System.out.println("LOGIN FAILED " + task.getException());
                    }
                }
            });
    }

    public void signOut() {
        auth.signOut();
    }
}
