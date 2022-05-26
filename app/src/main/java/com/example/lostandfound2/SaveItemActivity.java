package com.example.lostandfound2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.Toast;
import com.example.lostandfound2.model.AllItems;
import com.example.lostandfound2.data.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class SaveItemActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText itemName;
    EditText itemPhone;
    EditText itemDescription;
    EditText itemLocation;
    EditText itemDate;
    RadioButton lost;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_item);

         db = new DatabaseHelper(this);
         save = findViewById(R.id.save_Button);
         itemName = findViewById(R.id.item_Name);
         itemPhone = findViewById(R.id.item_Phone);
         itemDescription = findViewById(R.id.item_Description);
         itemLocation = findViewById(R.id.item_Location);
         itemDate = findViewById(R.id.item_Date);
         lost = findViewById(R.id.lostRadioButton);


        save.setOnClickListener(view -> {

            String name = itemName.getText().toString();
            String phone = itemPhone.getText().toString();
            String description = itemDescription.getText().toString();
            String location = itemLocation.getText().toString();
            String date = itemDate.getText().toString();
            String lostFound;

            if (lost.isChecked()) {
                lostFound = "Lost";
            }
            else {
                lostFound = "Found";
            }

            if(!NameCheck())
            {
                Toast.makeText(SaveItemActivity.this, "Name Must contain more then 2 letters.", Toast.LENGTH_SHORT).show();
            }
            else if(!PhoneCheck())
            {
                Toast.makeText(SaveItemActivity.this, "Phone number must contain at least 8 digits.", Toast.LENGTH_SHORT).show();
            }
            else if(!DescriptionCheck())
            {
                Toast.makeText(SaveItemActivity.this, "Description must contain at least 5 characters", Toast.LENGTH_SHORT).show();
            }
            else if(!DateCheck())
            {
                Toast.makeText(SaveItemActivity.this, "date needs to be in format DD/MM/YYYY", Toast.LENGTH_SHORT).show();
            }
            else if(!LocationCheck())
            {
                Toast.makeText(SaveItemActivity.this, "Location must contain at least 4 characters.", Toast.LENGTH_SHORT).show();
            }
            else
            {


            long result = db.insertItem(new AllItems(name, description, location, phone, date, lostFound));
            if (result > 0) {
                Toast.makeText(SaveItemActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SaveItemActivity.this, MainActivity.class));
            } else {
                Toast.makeText(SaveItemActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
            }}
        });
    }
        private boolean NameCheck()
        {
            String name = itemName.getText().toString();
            return name != null && name.trim().length() > 2;
        }

        private boolean PhoneCheck()
        {
            String phone = itemPhone.getText().toString();
            return phone != null && phone.trim().length() > 7 ;
        }

        private boolean DescriptionCheck()
        {
            String description = itemDescription.getText().toString();
            return description != null && description.trim().length() > 4;
        }

        private boolean DateCheck()
        {
            String date = itemDate.getText().toString();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                dateFormat.setLenient(false);
                dateFormat.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        private boolean LocationCheck()
        {
            String location = itemLocation.getText().toString();
            return location != null && location.trim().length() > 3;
        }
    public void checkButton (View view){



    }
    }

