package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

        ImageView imageView;

        public void downloadImages(View view) throws ExecutionException, InterruptedException {
                ImageDownloader task = new ImageDownloader();
                Bitmap myImage;
                try {
                        myImage = task.execute("https://electrocse.com/images/underconstruction.jpg").get();

                        imageView.setImageBitmap(myImage);
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                imageView = findViewById(R.id.imageView);
        }

        public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

                @Override
                protected Bitmap doInBackground(String... urls) {
                        try {
                                URL url = new URL(urls[0]);

                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                                connection.connect();

                                InputStream in = connection.getInputStream();

                                Bitmap mybitmap = BitmapFactory.decodeStream(in);
                                return mybitmap;
                        } catch (MalformedURLException e) {
                                e.printStackTrace();

                                return null;
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        return null;
                }
        }
}





<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

</resources>






<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="257dp"
        android:layout_height="56dp"
        android:layout_marginTop="32dp"
        android:onClick="downloadImages"
        android:text="Download Image"
        app:layout_constraintBottom_toTopOf="@+id/imageView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="574dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.489"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.742" />
</androidx.constraintlayout.widget.ConstraintLayout>