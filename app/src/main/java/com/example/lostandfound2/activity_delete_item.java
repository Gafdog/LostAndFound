package com.example.lostandfound2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.example.lostandfound2.data.DatabaseHelper;
import com.example.lostandfound2.util.Util;

import java.text.SimpleDateFormat;

public class activity_delete_item extends AppCompatActivity {

    DatabaseHelper db;
    Button remove;
    TextView name;
    TextView phone;
    TextView description;
    TextView date;
    TextView location;
    TextView lostFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        db = new DatabaseHelper(this);
        db.getReadableDatabase();

        Intent intent = getIntent();
        int numb = intent.getIntExtra("item_id",0);
        String itemName = intent.getStringExtra(Util.NAME);
        String itemPhone = intent.getStringExtra(Util.PHONE);
        String itemDescription = intent.getStringExtra(Util.DESCRIPTION);
        String itemDate = intent.getStringExtra(Util.DATE);
        String itemLocation = intent.getStringExtra(Util.LOCATION);
        String itemLostFound = intent.getStringExtra(Util.LOSTFOUND);


        db = new DatabaseHelper(this);
        name = findViewById(R.id.display_Name);
        phone = findViewById(R.id.display_Phone);
        description= findViewById(R.id.display_Description);
        date = findViewById(R.id.display_Date);
        location = findViewById(R.id.display_Location);
        lostFound = findViewById(R.id.lostFound);
        remove = findViewById(R.id.remove);


        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar today = Calendar.getInstance();
        Date todayDate = today.getTime();
        Date newDate = null;
        try {
            newDate = SDF.parse(itemDate);
        } catch (ParseException e) {
            newDate = todayDate;
        }

        long time = Math.abs(todayDate.getTime() - newDate.getTime());
        int when = (int)TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);

        name.setText(itemName);
        phone.setText(itemPhone);
        description.setText(itemDescription);
        location.setText(itemLocation);
        lostFound.setText(itemLostFound);
        date.setText(when + " days ago");

               remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.DeleteItem(numb);
                finish();
            }
        });
    }
}