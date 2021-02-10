package com.example.weather2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

        EditText editText;
        TextView resultTextView;
        String cityName;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                editText = findViewById(R.id.editTextText);
                resultTextView = findViewById(R.id.resultTextView);
        }

        public void getWeather(View view) {

                cityName = editText.getText().toString();

                try {
                        DownloadTask task = new DownloadTask();
                        String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
                        task.execute("https://openweathermap.org/data/2.5/weather?q=" + cityName
                                        + "&appid=439d4b804bc8187953eb36d2a8c26a02");

                        // Hide Keyboard after pressing Button
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                }
        }

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
                                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                                return null;
                        }
                }

                @Override
                protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        try {
                                JSONObject jsonObject = new JSONObject(s);

                                String weatherInfo = jsonObject.getString("weather");

                                Log.i("Weather content", weatherInfo);

                                JSONArray jsonArray = new JSONArray(weatherInfo);

                                String message = "";

                                for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonPart = jsonArray.getJSONObject(i);

                                        String main = jsonPart.getString("main");
                                        String description = jsonPart.getString("description");

                                        if (!main.equals("") && !description.equals("")) {
                                                message += main + ": " + description + "\r\n";
                                        }
                                }
                                if (!message.equals("")) {

                                        resultTextView.setText(cityName.toUpperCase() + "\n" + message);
                                } else {
                                        Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT)
                                                        .show();
                                }

                        } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                        }
                }
        }

}

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"package="com.example.weather2"><uses-permission android:name="android.permission.INTERNET"/><application
        android:allowBackup="true"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/AppTheme"><activity android:name=".MainActivity"><intent-filter><action android:name="android.intent.action.MAIN"/>

<category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest>

<?xml version="1.0"encoding="utf-8"?><androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<ImageView
        android:id="@+id/imageView"android:layout_width="0dp"android:layout_height="0dp"android:scaleType="centerCrop"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"app:srcCompat="@drawable/bg"/>

<TextView
        android:id="@+id/textView"android:layout_width="266dp"android:layout_height="59dp"android:gravity="center"android:text="Enter a City"android:textAlignment="center"android:textColor="#0000ff"android:textSize="37dp"app:layout_constraintBottom_toBottomOf="@+id/imageView"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintHorizontal_bias="0.496"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"app:layout_constraintVertical_bias="0.135"/>

<EditText
        android:id="@+id/editTextText"android:layout_width="228dp"android:layout_height="56dp"android:ems="10"android:inputType="textPersonName"

android:textSize="35dp"android:textAlignment="center"android:gravity="center"android:textColor="#C60707"app:layout_constraintBottom_toBottomOf="@+id/imageView"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintHorizontal_bias="0.442"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/textView"app:layout_constraintVertical_bias="0.04"/>

<Button
        android:id="@+id/button"android:onClick="getWeather"android:layout_width="135dp"android:layout_height="62dp"android:background="#789592"android:text="Check It"android:textSize="20dp"app:layout_constraintBottom_toBottomOf="@+id/imageView"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/editTextText"app:layout_constraintVertical_bias="0.038"/>

<
TextView android:id="@+id/resultTextView"android:layout_width="408dp"android:layout_height="305dp"android:textSize="20dp"android:textStyle="bold"android:textColor="#154917"app:layout_constraintBottom_toBottomOf="parent"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintHorizontal_bias="1.0"app:layout_constraintStart_toEndOf="@+id/imageView"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toTopOf="parent"app:layout_constraintVertical_bias="0.741"/></androidx.constraintlayout.widget.ConstraintLayout
>