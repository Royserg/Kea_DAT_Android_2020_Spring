package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String operator;
    private int value1 = 0;
    private int value2 = 0;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_text_view);
    }

    public void onNumberClicked(View view) {
        Button b = (Button) view;
        String number = b.getText().toString();

        String current = (String) resultTextView.getText();

        resultTextView.setText(String.format("%s%s", current, number));
    }

    public void onClearClicker(View view) {
        value1 = 0;
        value2 = 0;
        resultTextView.setText("");
        resultTextView.setHint("Clean :)");
    }

    public void onOperatorClicked(View view) {
        Button b = (Button) view;
        operator = b.getText().toString();
        value1 = Integer.parseInt((String) resultTextView.getText());
        // reset text view
        resultTextView.setText("");
        resultTextView.setHint(value1 + " " + operator + " ");
    }

    public void onEqualClicked(View view) {
        // Save value2
        value2 = Integer.parseInt((String) resultTextView.getText());
        int result = 0;

        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "x":
                result = value1 * value2;
                break;
            case "/":
                if (value2 == 0) {
                    resultTextView.setText("");
                    resultTextView.setHint("That's illegal");
                    return;
                }
                result = value1 / value2;
                break;
            default:
                Log.d("MyApp", "I don't know");
                break;
        }
        // Show result
        resultTextView.setText(Integer.toString(result));
    }
}
