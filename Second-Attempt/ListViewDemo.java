package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                ListView myListView = findViewById(R.id.myListView);

                final ArrayList<String> myFriends = new ArrayList<String>();
                // OR
                // final ArrayList<String> myFriends = new
                // ArrayList<String>(asList("Chandan","Candy"));

                myFriends.add("Chandan");
                myFriends.add("Candy");
                myFriends.add("CandyXT");
                myFriends.add("CandyAsus");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                                myFriends);

                myListView.setAdapter(arrayAdapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(MainActivity.this, "Tapped on " + myFriends.get(i), Toast.LENGTH_SHORT)
                                                .show();
                        }
                });

        }
}