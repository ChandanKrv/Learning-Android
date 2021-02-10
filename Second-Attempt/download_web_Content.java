package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

        public class DownloadTask extends AsyncTask<String, Void, String> {

                @Override
                protected String doInBackground(String... urls) {

                        String result = "";
                        URL url;
                        HttpURLConnection urlConnection = null;

                        try {
                                url = new URL(urls[0]);
                                urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream in = urlConnection.getInputStream();
                                InputStreamReader reader = new InputStreamReader(in);
                                int data = reader.read();

                                while (data != -1) {
                                        char current = (char) data;
                                        result += current;
                                        data = reader.read();
                                }
                                return result;

                        } catch (Exception e) {
                                e.printStackTrace();
                                return "Failed";
                        }
                }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                DownloadTask task = new DownloadTask();
                String result = null;
                try {
                        result = task.execute("https://www.brandcrowd.com").get();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                Log.i("Result", result);
        }
}

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"package="com.example.downloadingwebcontent">

<uses-permission android:name="android.permission.INTERNET"/><application
        android:allowBackup="true"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/AppTheme"><activity android:name=".MainActivity"><intent-filter><action android:name="android.intent.action.MAIN"/>

<category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest>

<?xml version="1.0"encoding="utf-8"?><androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<TextView
        android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Hello World!"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintLeft_toLeftOf="parent"app:layout_constraintRight_toRightOf="parent"app:layout_constraintTop_toTopOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout
>