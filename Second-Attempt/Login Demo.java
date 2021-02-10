package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        public void login(View view) {
                EditText username = (EditText) findViewById(R.id.usernameEditText);
                EditText password = (EditText) findViewById(R.id.passwordEditText);
                Log.i("Info", "Button Pressed");
                Log.i("Username", username.getText().toString());
                Log.i("Password", password.getText().toString());

                Toast.makeText(this, "Hello " + username.getText().toString(), Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}

<?xml version="1.0"encoding="utf-8"?><androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<EditText
        android:id="@+id/usernameEditText"android:layout_width="0dp"android:layout_height="wrap_content"android:layout_marginStart="16dp"android:layout_marginLeft="16dp"android:layout_marginEnd="16dp"android:layout_marginRight="16dp"android:ems="10"android:hint="Username"android:inputType="textPersonName"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"tools:layout_editor_absoluteY="92dp"/>

<EditText
        android:id="@+id/passwordEditText"android:layout_width="0dp"android:layout_height="wrap_content"android:layout_marginStart="16dp"android:layout_marginLeft="16dp"android:layout_marginTop="16dp"android:layout_marginEnd="16dp"android:layout_marginRight="16dp"android:ems="10"android:hint="Password"android:inputType="textPassword"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/usernameEditText"/>

<Button
        android:id="@+id/loginButton"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_marginTop="24dp"android:onClick="login"android:text="Login"app:layout_constraintEnd_toEndOf="parent"app:layout_constraintStart_toStartOf="parent"app:layout_constraintTop_toBottomOf="@+id/passwordEditText"/></androidx.constraintlayout.widget.ConstraintLayout
>