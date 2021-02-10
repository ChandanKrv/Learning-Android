package com.example.hourscalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        public void calculateHours(View view) {

                EditText inputMinutes = findViewById(R.id.inputTextNumber);
                TextView outputHours = findViewById(R.id.outputTextView);

                String stringMinutes = inputMinutes.getText().toString();

                double dMinutes = Double.parseDouble(stringMinutes);

                double dHours = dMinutes / 60;

                String sHours = String.format("%.2f", dHours);

                outputHours.setText(sHours + " hrs");

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}