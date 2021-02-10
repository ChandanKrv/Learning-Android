package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

        MediaPlayer mediaPlayer;

        AudioManager audioManager;

        public void play(View view) {
                mediaPlayer.start();
                Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show();
        }

        public void pause(View view) {
                mediaPlayer.pause();
                Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                int maxVolume = audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
                int currentVolume = audioManager.getStreamVolume(audioManager.STREAM_MUSIC);
                mediaPlayer = MediaPlayer.create(this, R.raw.meri_mehbooba);

                SeekBar volumeControl = (SeekBar) findViewById(R.id.volumesSeekBar);
                volumeControl.setMax(maxVolume);
                volumeControl.setProgress(currentVolume);
                volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                });

                final SeekBar scrubSeekBar = (SeekBar) findViewById(R.id.scrubSeekBar);
                scrubSeekBar.setMax(mediaPlayer.getDuration());
                scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                                // mediaPlayer.seekTo(i);

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                                // mediaPlayer.pause();
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                                // mediaPlayer.start();
                        }
                });
                new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                }, 0, 500); // less no of periods will make smother and battery consumptions

        }

}