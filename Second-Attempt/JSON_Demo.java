package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                                return null;
                        }
                }

                @Override
                protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        try {
                                JSONObject jsonObject = new JSONObject(s);

                                String weatherInfo = jsonObject.getString("weather");

                                Toast.makeText(MainActivity.this, weatherInfo, Toast.LENGTH_LONG).show();

                                JSONArray jsonArray = new JSONArray(weatherInfo);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonPart = jsonArray.getJSONObject(i);

                                        Log.i("main", jsonPart.getString("main"));
                                        Log.i("description", jsonPart.getString("description"));
                                }

                        } catch (JSONException e) {
                                e.printStackTrace();
                        }
                }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                DownloadTask task = new DownloadTask();
                task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
        }
}

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"package="com.example.jsondemo"><uses-permission android:name="android.permission.INTERNET"/><application
        android:allowBackup="true"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/AppTheme"><activity android:name=".MainActivity"><intent-filter><action android:name="android.intent.action.MAIN"/>

<category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>