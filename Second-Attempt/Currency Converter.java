package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        public void doConversion(View view) {
                // double dollar = findViewById(R.id.dollarValue);
                EditText editText = (EditText) findViewById(R.id.dollarValue);

                String stringDollar = editText.getText().toString();

                double doubleDollar = Double.parseDouble(stringDollar);

                double convertedStringToDoubleINR = doubleDollar * 74.72;

                String finalAmountInString = String.format("%.2f", convertedStringToDoubleINR);

                Toast.makeText(this, "$" + stringDollar + "= INR " + finalAmountInString, Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<ImageView
        android:id="@+id/imageView3"android:layout_width="match_parent"android:layout_height="353dp"android:layout_alignParentStart="true"android:layout_alignParentLeft="true"android:layout_alignParentTop="true"android:layout_alignParentEnd="true"android:layout_centerHorizontal="true"android:layout_marginStart="-9dp"android:layout_marginLeft="-9dp"android:layout_marginTop="-56dp"android:layout_marginEnd="5dp"android:foregroundGravity="center"app:srcCompat="@drawable/currency"android:layout_alignParentRight="true"android:layout_marginRight="5dp"/>

<TextView
        android:id="@+id/textView"android:layout_width="321dp"android:layout_height="wrap_content"android:layout_alignParentTop="true"android:layout_centerHorizontal="true"android:layout_marginTop="255dp"android:gravity="center"android:text="Dollar to INR Converter"android:textColor="#0000ff"android:textSize="29dp"/>

<EditText
        android:id="@+id/dollarValue"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_alignParentBottom="true"android:layout_centerHorizontal="true"android:layout_marginBottom="392dp"android:ems="10"android:gravity="center"android:hint="Enter Dollar Value"android:inputType="numberDecimal"android:text="1"/>

<Button
        android:id="@+id/button"android:layout_width="150dp"android:layout_height="wrap_content"android:layout_alignParentBottom="true"android:layout_centerInParent="true"android:layout_marginBottom="344dp"android:background="#46CC40"android:onClick="doConversion"android:text="Click me for INR"/>

</RelativeLayout
>