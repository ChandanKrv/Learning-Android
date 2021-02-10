package com.example.table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ListView timesListView;

        public void generateTimesTable(int timesTablesNumber) {
                ArrayList<String> timesContent = new ArrayList<String>();
                for (int j = 1; j <= 50; j++) {
                        timesContent.add(Integer.toString(j * timesTablesNumber));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                                timesContent);
                timesListView.setAdapter(arrayAdapter);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                final SeekBar timesSeekBar = findViewById(R.id.mySeekBar);
                timesListView = findViewById(R.id.myListView);

                int max = 40;
                int startingPosition = 20;

                timesSeekBar.setMax(max);
                timesSeekBar.setProgress(startingPosition);
                generateTimesTable(startingPosition);
                timesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                int min = 1;
                                int timesTableNumber;
                                if (i < min) {
                                        timesTableNumber = min;
                                        timesSeekBar.setProgress(min); // Cann't Be seekbar move below min.
                                } else {
                                        timesTableNumber = i;
                                }

                                generateTimesTable(timesTableNumber);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                });

        }
}