package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        TextView timerTextView;
        SeekBar timerSeekbar;
        Boolean counterIsActive = false;
        Button startButton;
        CountDownTimer countDownTimer;

        public void resetTimer() {
                timerTextView.setText("0:30");
                timerSeekbar.setProgress(30);
                timerSeekbar.setEnabled(true);
                countDownTimer.cancel();
                startButton.setText("START");
                counterIsActive = false;
        }

        public void buttonClicked(View view) {
                if (counterIsActive) {
                        resetTimer();
                } else {

                        counterIsActive = true;
                        timerSeekbar.setEnabled(false);
                        startButton.setText("STOP!");

                        countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                                @Override
                                public void onTick(long l) {
                                        updateTimer((int) l / 1000);
                                }

                                @Override
                                public void onFinish() {
                                        Toast.makeText(MainActivity.this, "Time Out", Toast.LENGTH_SHORT).show();
                                        resetTimer();
                                }
                        }.start();
                }
        }

        public void updateTimer(int secondsLeft) {

                int minutes = secondsLeft / 60;
                int seconds = secondsLeft - (minutes * 60);
                String secondString = Integer.toString(seconds);
                if (seconds <= 9) {
                        secondString = "0" + secondString;
                }
                timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                timerSeekbar = findViewById(R.id.seekBar);
                timerTextView = findViewById(R.id.countdownTextView);
                startButton = findViewById(R.id.goButton);

                timerSeekbar.setMax(600);
                timerSeekbar.setProgress(30);
                timerTextView.setText("0:30");
                timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                updateTimer(i);
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









<?xml version="1.0"encoding="utf-8"?><androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<SeekBar
        android:id="@+id/seekBar"android:layout_width="0dp"android:layout_height="wrap_content"android:layout_marginStart="10dp"android:layout_marginLeft="10dp"android:layout_marginTop="40dp"android:layout_marginEnd="10dp"android:layout_marginRight="10dp"android:layout_marginBottom="673dp"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintHorizontal_bias="0.0"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"app:layout_constraintVertical_bias="1.0"/>

<TextView
        android:id="@+id/countdownTextView"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="00:30"android:textSize="60dp"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"/>

<Button
        android:id="@+id/goButton"android:layout_width="150dp"android:layout_height="65dp"android:background="#f900"android:text="Start"android:textSize="26dp"android:textStyle="bold"android:textColor="#FABA6C"android:onClick="buttonClicked"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/countdownTextView"/></androidx.constraintlayout.widget.ConstraintLayout
>