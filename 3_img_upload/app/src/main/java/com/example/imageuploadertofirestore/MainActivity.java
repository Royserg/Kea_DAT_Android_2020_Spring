package com.example.imageuploadertofirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imageuploadertofirestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /* Open gallery */
    public void gelleryBtnPressed(View v) {
        Toast.makeText(this, "Gallery Btn Pressed", Toast.LENGTH_SHORT).show();
    }

    public void uploadToFirestore(View v) {

    }

}
