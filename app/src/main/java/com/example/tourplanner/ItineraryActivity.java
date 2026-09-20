package com.example.tourplanner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ItineraryActivity extends Activity {

    private static final String TAG = "TourPlanner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ItineraryActivity onCreate()");
        setContentView(R.layout.activity_itinerary);

        TextView greeting = findViewById(R.id.tvGreeting);
        TextView tripTitle = findViewById(R.id.tvTripTitle);
        TextView tripMeta = findViewById(R.id.tvTripMeta);
        TextView category = findViewById(R.id.tvItineraryCategory);
        TextView activities = findViewById(R.id.tvItineraryActivities);

        String traveller = getIntent().getStringExtra("traveller");
        String destination = getIntent().getStringExtra("destination");
        String days = getIntent().getStringExtra("days");
        String selectedCategory = getIntent().getStringExtra("category");
        String place = getIntent().getStringExtra("place");
        String activityList = getIntent().getStringExtra("activities");

        if (traveller == null) traveller = "Traveller";
        if (destination == null) destination = "Your destination";
        if (days == null) days = "1";
        if (selectedCategory == null) selectedCategory = "Adventure";
        if (place == null) place = destination;
        if (activityList == null) activityList = "Explore the destination and enjoy your trip.";

        greeting.setText("Hello, " + traveller + "!");
        tripTitle.setText(place);
        tripMeta.setText(days + (days.equals("1") ? " Day" : " Days") + "  •  " + destination);
        category.setText(selectedCategory + " itinerary");
        activities.setText(activityList);
    }

    public void closeItinerary(View view) {
        Log.d(TAG, "Back to Trip Planner clicked");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ItineraryActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ItineraryActivity onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ItineraryActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ItineraryActivity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ItineraryActivity onDestroy()");
    }
}
