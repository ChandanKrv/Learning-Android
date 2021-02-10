package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        int activePlayer = 0;
        // 0 for Yellow , 1 for Red, 2 for Empty
        int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
        // 0
        int[][] winnigPositions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                        { 0, 4, 8 }, { 2, 4, 6 } };

        boolean gameActive = true;

        public void dropIn(View view) {
                ImageView tapped = (ImageView) view;
                int tappedTrack = Integer.parseInt(tapped.getTag().toString());

                if (gameState[tappedTrack] == 2 && gameActive) {
                        gameState[tappedTrack] = activePlayer;
                        // INDEX 0-8 Position Where Tapped 0 or 1
                        if (activePlayer == 0) {
                                tapped.setTranslationY(-1500);
                                tapped.setImageResource(R.drawable.yellow);
                                tapped.animate().translationYBy(1500).rotation(3600).setDuration(400);
                                activePlayer = 1;
                        } else {
                                tapped.setTranslationY(1500);
                                tapped.setImageResource(R.drawable.red);
                                tapped.animate().translationYBy(-1500).rotation(3600).setDuration(400);
                                activePlayer = 0;
                        }
                        for (int[] winnigPositions : winnigPositions) {
                                if (gameState[winnigPositions[0]] == gameState[winnigPositions[1]]
                                                && gameState[winnigPositions[1]] == gameState[winnigPositions[2]]
                                                && gameState[winnigPositions[0]] != 2) {
                                        String winner = "";
                                        gameActive = false;
                                        if (activePlayer == 1)
                                                winner = "Yellow";
                                        else
                                                winner = "Red";

                                        // Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();

                                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                                        winnerTextView.setText(winner + " has won!");

                                        playAgainButton.setVisibility(View.VISIBLE);

                                        winnerTextView.setVisibility(View.VISIBLE);
                                }
                        }
                } else {
                        Toast.makeText(this, "Already Occupied", Toast.LENGTH_SHORT).show();
                }
        }

        public void playAgain(View view) {
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                playAgainButton.setVisibility(View.INVISIBLE);

                winnerTextView.setVisibility(View.INVISIBLE);

                GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

                for (int i = 0; i < gridLayout.getChildCount(); i++) {

                        ImageView counter = (ImageView) gridLayout.getChildAt(i);

                        counter.setImageDrawable(null);
                }

                for (int i = 0; i < gameState.length; i++) {
                        gameState[i] = 2;
                }
                activePlayer = 0;
                // 0 for Yellow , 1 for Red, 2 for Empty

                // int[][] winnigPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4,
                // 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

                gameActive = true;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}