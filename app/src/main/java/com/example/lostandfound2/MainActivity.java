package com.example.lostandfound2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Initiate Variables
    Button createNew;
    Button showAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Link variables to buttons on main screen
        createNew = findViewById(R.id.createNew);
        showAll = findViewById(R.id.showAll);

        createNew.setOnClickListener(view -> startActivity(new Intent
                (MainActivity.this, SaveItemActivity.class)));
        showAll.setOnClickListener(view -> startActivity(new Intent
                (MainActivity.this, recyclerItems.class)));
    }
}