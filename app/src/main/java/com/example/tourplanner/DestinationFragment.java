package com.example.tourplanner;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DestinationFragment extends Fragment {

    private static final String TAG = "TourPlanner";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_TYPED_DESTINATION = "typedDestination";

    public interface OnTripAddedListener {
        void onTripAdded(String category, String place, String activities);
    }

    private String category;
    private String typedDestination;

    public static DestinationFragment newInstance(String category, String typedDestination) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        args.putString(ARG_TYPED_DESTINATION, typedDestination);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "DestinationFragment onCreate()");
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY, "Adventure");
            typedDestination = getArguments().getString(ARG_TYPED_DESTINATION, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "DestinationFragment onCreateView()");
        return inflater.inflate(R.layout.fragment_destination, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView image = view.findViewById(R.id.destinationImage);
        TextView categoryText = view.findViewById(R.id.tvCategory);
        TextView placeText = view.findViewById(R.id.tvPlace);
        TextView descriptionText = view.findViewById(R.id.tvDescription);
        TextView activitiesText = view.findViewById(R.id.tvActivities);
        Button addButton = view.findViewById(R.id.btnAddToTrip);

        DestinationData data = DestinationData.forCategory(category);

        categoryText.setText(data.category.toUpperCase());
        placeText.setText(typedDestination.isEmpty() ? data.place : typedDestination);
        descriptionText.setText(data.description);
        activitiesText.setText(data.activities);
        image.setImageResource(data.imageRes);

        addButton.setOnClickListener(v -> {
            Log.d(TAG, "Add to Trip clicked for " + data.place);
            Activity activity = getActivity();
            if (activity instanceof OnTripAddedListener) {
                ((OnTripAddedListener) activity).onTripAdded(
                        data.category,
                        typedDestination.isEmpty() ? data.place : typedDestination,
                        data.activities
                );
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "DestinationFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "DestinationFragment onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "DestinationFragment onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "DestinationFragment onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "DestinationFragment onDestroyView()");
    }
}
