package com.example.time_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                new CountDownTimer(10000, 1000) {
                        public void onTick(long millisecUntilDone) {
                                Toast.makeText(MainActivity.this,
                                                "Sec left " + String.valueOf(millisecUntilDone / 1000),
                                                Toast.LENGTH_SHORT).show();
                        }

                        public void onFinish() {
                                Toast.makeText(MainActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
                        }
                }.start();

                /*
                 * final Handler handler = new Handler();
                 * 
                 * Runnable runnable =new Runnable() {
                 * 
                 * @Override public void run() { Toast.makeText(MainActivity.this,
                 * "2 sec passed", Toast.LENGTH_SHORT).show(); handler.postDelayed(this,2000); }
                 * }; handler.post(runnable);
                 */
        }
}