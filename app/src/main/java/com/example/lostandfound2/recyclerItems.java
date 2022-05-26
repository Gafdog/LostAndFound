package com.example.lostandfound2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfound2.data.DatabaseHelper;
import com.example.lostandfound2.util.Util;

import java.util.ArrayList;

public class recyclerItems extends AppCompatActivity {

    DatabaseHelper db;
    ListView itemsListView;
    ArrayList itemsArray;
    RecyclerViewAdapter itemRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);

        db = new DatabaseHelper(this);

        itemsListView = findViewById(R.id.itemsListView);
        itemsArray = db.GetItems();
        itemRecycler = new RecyclerViewAdapter(this,itemsArray);
        itemsListView.setAdapter(itemRecycler);

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                items x = itemRecycler.getItem(i);

                Intent intent = new Intent(recyclerItems.this,activity_delete_item.class);
                intent.putExtra("item_id", x.getId());
                intent.putExtra("item_name", x.getName());
                intent.putExtra("item_Phone", x.getPhone());
                intent.putExtra("item_Description", x.getDescription());
                intent.putExtra("item_Date", x.getWhen_Found());
                intent.putExtra("item_Location", x.getLocation());
                intent.putExtra("item_lostFound", x.getLostFound());
                startActivity(intent);
                finish();
            }


        });


}}