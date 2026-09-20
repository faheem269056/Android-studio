package com.example.tourplanner;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements DestinationFragment.OnTripAddedListener {

    private static final String TAG = "TourPlanner";
    public static final String CHANNEL_ID = "tourplanner_channel";

    private EditText travellerNameInput;
    private EditText destinationInput;
    private EditText daysInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity onCreate()");
        setContentView(R.layout.activity_main);

        travellerNameInput = findViewById(R.id.etTravellerName);
        destinationInput = findViewById(R.id.etDestination);
        daysInput = findViewById(R.id.etDays);

        createNotificationChannel();

        if (savedInstanceState == null) {
            showDestination("Adventure");
        }
    }


    public void onAdventureClicked(View view) {
        showDestination("Adventure");
    }

    public void onHeritageClicked(View view) {
        showDestination("Heritage");
    }

    public void onRelaxationClicked(View view) {
        showDestination("Relaxation");
    }

    public void showDestination(String category) {
        Log.d(TAG, "Loading DestinationFragment: " + category);

        DestinationFragment fragment = DestinationFragment.newInstance(
                category,
                destinationInput.getText().toString().trim()
        );

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                android.R.animator.fade_in,
                android.R.animator.fade_out
        );
        transaction.replace(R.id.destinationContainer, fragment, "destination");
        transaction.commit();
    }

    public String getTravellerName() {
        return travellerNameInput.getText().toString().trim();
    }

    public String getDestination() {
        return destinationInput.getText().toString().trim();
    }

    public String getDays() {
        return daysInput.getText().toString().trim();
    }

    @Override
    public void onTripAdded(String category, String place, String activities) {
        String name = getTravellerName();
        String destination = getDestination();
        String days = getDays();

        if (name.isEmpty()) {
            travellerNameInput.setError("Enter traveller name");
            travellerNameInput.requestFocus();
            return;
        }

        if (destination.isEmpty()) {
            destinationInput.setError("Enter destination");
            destinationInput.requestFocus();
            return;
        }

        if (days.isEmpty()) {
            daysInput.setError("Enter number of days");
            daysInput.requestFocus();
            return;
        }

        Log.d(TAG, "Trip added: " + place + " | Category: " + category);

        Intent intent = new Intent(this, ItineraryActivity.class);
        intent.putExtra("traveller", name);
        intent.putExtra("destination", destination);
        intent.putExtra("days", days);
        intent.putExtra("category", category);
        intent.putExtra("place", place);
        intent.putExtra("activities", activities);
        startActivity(intent);

        showTripAddedNotification(place);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "TourPlanner Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications when a destination is added to an itinerary.");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void showTripAddedNotification(String place) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    100
            );
            return;
        }

        android.app.Notification.Builder builder =
                new android.app.Notification.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Destination added")
                        .setContentText(place + " was added to your itinerary.")
                        .setAutoCancel(true);

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(101, builder.build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy()");
    }
}
