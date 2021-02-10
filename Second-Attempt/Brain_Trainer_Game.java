package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

        Button goButton;
        int locationOfCorrectAns;
        ArrayList<Integer> answers = new ArrayList<Integer>();
        TextView resultTextView;
        int score = 0;
        int noOfQuestions = 0;
        TextView scoreTextView;

        Button button0;
        Button button1;
        Button button2;
        Button button3;
        TextView sumTextView;

        TextView timerTextView;
        Button playAgainButton;

        ConstraintLayout gameLayout;

        public void playAgain(View view) {

                score = 0;
                noOfQuestions = 0;
                timerTextView.setText("30s");
                resultTextView.setText("");

                scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));

                newQuestion();

                playAgainButton.setVisibility(View.INVISIBLE);

                new CountDownTimer(30100, 1000) {

                        @Override
                        public void onTick(long l) {
                                timerTextView.setText(String.valueOf(l / 1000 + "s"));
                        }

                        @Override
                        public void onFinish() {
                                resultTextView.setText("Time Up!");
                                playAgainButton.setVisibility(View.VISIBLE);

                        }
                }.start();

        }

        public void chooseAnswer(View view) {
                if (Integer.toString(locationOfCorrectAns).equals(view.getTag().toString())) {
                        resultTextView.setText("Correct");
                        score++;
                } else {
                        resultTextView.setText("Wrong");
                }
                noOfQuestions++;
                scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
                newQuestion();
        }

        public void start(View view) {

                goButton.setVisibility(View.INVISIBLE);
                gameLayout.setVisibility(View.VISIBLE);
                playAgain(findViewById(R.id.timerTextView));
        }

        public void newQuestion() {
                Random random = new Random();
                int a = random.nextInt(21);
                int b = random.nextInt(21);
                sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
                locationOfCorrectAns = random.nextInt(4);

                answers.clear();
                for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAns) {
                                answers.add(a + b);
                        } else {
                                int wrongAns = random.nextInt(41);

                                while (wrongAns == a + b) {
                                        wrongAns = random.nextInt(41);
                                }
                                answers.add(wrongAns);
                        }

                }
                button0.setText(Integer.toString(answers.get(0)));
                button1.setText(Integer.toString(answers.get(1)));
                button2.setText(Integer.toString(answers.get(2)));
                button3.setText(Integer.toString(answers.get(3)));

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                sumTextView = findViewById(R.id.sumTextView);
                button0 = findViewById(R.id.button0);
                button1 = findViewById(R.id.button1);
                button2 = findViewById(R.id.button2);
                button3 = findViewById(R.id.button3);

                resultTextView = findViewById(R.id.resultTextView);
                scoreTextView = findViewById(R.id.scoreTextView);
                gameLayout = findViewById(R.id.gameLayout);
                goButton = findViewById(R.id.goButton);

                goButton.setVisibility(View.VISIBLE);

                timerTextView = findViewById(R.id.timerTextView);

                playAgainButton = findViewById(R.id.playAgainButton);

                gameLayout.setVisibility(View.INVISIBLE);

        }
}

<?xml version="1.0"encoding="utf-8"?><androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<Button
        android:id="@+id/goButton"android:layout_width="143dp"android:layout_height="86dp"android:background="#30AC30"android:onClick="start"android:padding="20dp"android:text="Go!"android:textSize="40dp"android:visibility="invisible"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"app:layout_constraintVertical_bias="0.755"/>

<androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/gameLayout"android:layout_width="match_parent"android:layout_height="match_parent"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent">

<TextView
            android:id="@+id/sumTextView"android:layout_width="143dp"android:layout_height="75dp"android:layout_marginStart="8dp"android:layout_marginLeft="8dp"android:layout_marginTop="18dp"android:layout_marginEnd="4dp"android:layout_marginRight="4dp"android:layout_marginBottom="57dp"android:background="#0C0C0C"android:gravity="center"android:text="19 + 16"android:textAlignment="center"android:textColor="#FFEB3B"android:textSize="40dp"app:layout_constraintBottom_toTopOf="@+id/gridLayout"app:layout_constraintEnd_toStartOf="@+id/scoreTextView"app:layout_constraintStart_toEndOf="@+id/timerTextView"app:layout_constraintTop_toTopOf="parent"/>

<Button
            android:id="@+id/playAgainButton"android:layout_width="128dp"android:layout_height="56dp"android:layout_marginStart="142dp"android:layout_marginLeft="142dp"android:layout_marginTop="168dp"android:layout_marginEnd="141dp"android:layout_marginRight="141dp"android:background="#357525"android:onClick="playAgain"android:text="Play Again"android:textSize="17dp"android:visibility="invisible"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/resultTextView"/>

<TextView
            android:id="@+id/resultTextView"android:layout_width="166dp"android:layout_height="63dp"android:layout_marginStart="123dp"android:layout_marginLeft="123dp"android:layout_marginTop="31dp"android:layout_marginEnd="122dp"android:layout_marginRight="122dp"android:layout_marginBottom="168dp"android:gravity="center"android:text="Correct!"android:textAlignment="center"android:textColor="#0942D3"android:textSize="35dp"android:textStyle="bold"app:layout_constraintBottom_toTopOf="@+id/playAgainButton"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/gridLayout"/>

<TextView
            android:id="@+id/scoreTextView"android:layout_width="129dp"android:layout_height="72dp"android:layout_marginTop="18dp"android:layout_marginEnd="18dp"android:layout_marginRight="18dp"android:layout_marginBottom="60dp"android:background="#222425"android:gravity="center"android:padding="5dp"android:text="0/0"android:textAlignment="center"android:textColor="#00BCD4"android:textSize="40dp"app:layout_constraintBottom_toTopOf="@+id/gridLayout"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toEndOf="@+id/sumTextView"app:layout_constraintTop_toTopOf="parent"/>

<
TextView android:id="@+id/timerTextView"android:layout_width="81dp"android:layout_height="75dp"android:layout_marginStart="24dp"android:layout_marginLeft="24dp"android:layout_marginTop="16dp"android:layout_marginEnd="8dp"android:layout_marginRight="8dp"android:layout_marginBottom="59dp"android:background="#222425"android:gravity="center"android:padding="5dp"android:text="30s"android:textAlignment="center"android:textColor="#FF5722"android:textSize="40dp"app:layout_constraintBottom_toTopOf="@+id/gridLayout"app:layout_constraintEnd_toStartOf="@+id/sumTextView"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"/>

<
GridLayout android:id="@+id/gridLayout"android:layout_width="420dp"android:layout_height="0dp"android:layout_marginTop="57dp"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/sumTextView">

<
Button android:id="@+id/button0"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_row="0"android:layout_rowWeight="1"android:layout_column="0"android:layout_columnWeight="1"android:layout_gravity="fill"android:background="#FF5722"android:onClick="chooseAnswer"android:tag="0"android:text="14"android:textSize="80dp"app:layout_column="0"app:layout_row="0"/>

<
Button android:id="@+id/button1"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_row="0"android:layout_rowWeight="1"android:layout_column="1"android:layout_columnWeight="1"android:layout_gravity="fill"android:background="#3F51B5"android:onClick="chooseAnswer"android:tag="1"android:text="14"android:textSize="80dp"app:layout_column="0"app:layout_row="0"/>

<
Button android:id="@+id/button2"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_row="1"android:layout_rowWeight="1"android:layout_column="0"android:layout_columnWeight="1"android:layout_gravity="fill"android:background="#9C27B0"android:onClick="chooseAnswer"android:tag="2"android:text="14"android:textSize="80dp"app:layout_column="0"app:layout_row="0"/>

<
Button android:id="@+id/button3"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_row="1"android:layout_rowWeight="1"android:layout_column="1"android:layout_columnWeight="1"android:layout_gravity="fill"android:background="#E91E63"android:onClick="chooseAnswer"android:tag="3"android:text="14"android:textSize="80dp"app:layout_column="0"app:layout_row="0"/>

</GridLayout></androidx.constraintlayout.widget.ConstraintLayout>

</androidx.constraintlayout.widget.ConstraintLayout
>