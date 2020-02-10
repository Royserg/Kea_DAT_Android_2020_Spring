package com.example.calculatorCompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        // Get intent that started this activity
        Intent intent = getIntent();
        String result = intent.getStringExtra(MainActivity.CALCULATION_RESULT);

        resultTextView = findViewById(R.id.result_text_view);
        resultTextView.setText(result);
    }

    public void openMemePage(View view) {
        Intent intent = new Intent(this, MemePage.class);
        startActivity(intent);
    }
}
