package com.example.lostandfound2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Initiate Variables
    Button createNew;
    Button showAll;
    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Link variables to buttons on main screen
        createNew = findViewById(R.id.createNew2);
        showAll = findViewById(R.id.showAll);
        showMap = findViewById(R.id.showMap);

        createNew.setOnClickListener(view -> startActivity(new Intent
                (MainActivity.this, SaveItemActivity.class)));
        showAll.setOnClickListener(view -> startActivity(new Intent
                (MainActivity.this, recyclerItems.class)));
        showMap.setOnClickListener(view -> startActivity(new Intent
                (MainActivity.this, MapsActivity.class)));
    }
}