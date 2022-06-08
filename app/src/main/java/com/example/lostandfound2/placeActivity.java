package com.example.lostandfound2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class placeActivity extends AppCompatActivity {
    String location;
    private static final String TAG = "Running ";
    //PlacesClient placesClient;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Places.initialize(getApplicationContext(),getString(R.string.placesAPI));

        PlacesClient placesClient = Places.createClient(getApplicationContext());


        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                //Toast.makeText(placeActivity.this, "Place: " + place.getName() + ", " + place.getId(), Toast.LENGTH_SHORT).show();
                location =place.getName();
                Double latitude = place.getLatLng().latitude;
                Double longitude = place.getLatLng().longitude;

                Intent intent = new Intent(placeActivity.this,SaveItemActivity.class);

                intent.putExtra("name", location);
                intent.putExtra("lat", latitude);
                intent.putExtra("long", longitude);
                setResult(RESULT_OK, intent);
                finish();

            }
            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }





}