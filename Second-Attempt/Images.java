package com.example.images;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

        public void nextCat(View view) {

                // Log.i("info","Button Pressed");
                ImageView catImage1 = (ImageView) findViewById(R.id.catImageView);
                catImage1.setImageResource(R.drawable.cat2);
        }

        public void prevCat(View view) {
                ImageView catImage2 = (ImageView) findViewById(R.id.catImageView);
                catImage2.setImageResource(R.drawable.cat1);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}
