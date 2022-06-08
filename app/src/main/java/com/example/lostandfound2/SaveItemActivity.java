package com.example.lostandfound2;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import android.widget.Toast;

import com.example.lostandfound2.model.AllItems;
import com.example.lostandfound2.data.DatabaseHelper;
import com.google.android.gms.common.api.Status;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
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
    Button getLocation;
    LocationManager locationManager;
    LocationListener locationListener;
    String gps;
    Double latitude, longitude;

    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

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
        getLocation = findViewById(R.id.locationButton);






        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(@NonNull Location location) {
                gps = (location.toString());
                // added the following lines to get lat and long
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        };
        //List<Place.Field> autoComplete = Arrays.asList(Place.Field.ADDRESS_COMPONENTS);



        itemLocation.setOnClickListener(view -> {
            //when click get location
//            Intent placeIntent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,autoComplete).build(this);
//
//            startActivityForResult(placeIntent, AUTOCOMPLETE_REQUEST_CODE);

            Intent placeIntent = new Intent(SaveItemActivity.this, placeActivity.class);

            startActivity(placeIntent);

        });




        getLocation.setOnClickListener(view -> {
            //when click get location

            itemLocation.setText(gps);
            });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }

        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


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


            long result = db.insertItem(new AllItems(name, description, location, phone, date, lostFound, latitude, longitude));
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




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {

            Toast.makeText(this, resultCode,Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK && data != null) {
//                Place place = Autocomplete.getPlaceFromIntent(data);
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
               String locationName = data.getStringExtra("name");
               itemLocation.setText(locationName);
               latitude = data.getDoubleExtra("lat", 0);
               longitude = data.getDoubleExtra("long", 0);

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    }



