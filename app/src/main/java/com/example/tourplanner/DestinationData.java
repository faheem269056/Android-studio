package com.example.tourplanner;

public class DestinationData {

    public final String category;
    public final String place;
    public final String description;
    public final String activities;
    public final int imageRes;

    public DestinationData(String category, String place, String description,
                           String activities, int imageRes) {
        this.category = category;
        this.place = place;
        this.description = description;
        this.activities = activities;
        this.imageRes = imageRes;
    }

    public static DestinationData forCategory(String category) {
        if ("Heritage".equalsIgnoreCase(category)) {
            return new DestinationData(
                    "Heritage",
                    "Hampi",
                    "Walk through ancient ruins, stone temples and royal monuments surrounded by a dramatic landscape.",
                    "• Explore Virupaksha Temple\n• Visit Vittala Temple & Stone Chariot\n• Watch the sunset at Hemakuta Hill",
                    R.drawable.img_heritage
            );
        }

        if ("Relaxation".equalsIgnoreCase(category)) {
            return new DestinationData(
                    "Relaxation",
                    "Goa",
                    "Unwind beside golden beaches with peaceful views, coastal cafés and slow-paced experiences.",
                    "• Relax at the beach\n• Enjoy a sunset cruise\n• Visit a quiet beach café",
                    R.drawable.img_relaxation
            );
        }

        return new DestinationData(
                "Adventure",
                "Manali",
                "A mountain escape filled with scenic valleys, thrilling outdoor activities and fresh Himalayan air.",
                "• Trek to a scenic viewpoint\n• Try river rafting\n• Explore Solang Valley",
                R.drawable.img_adventure
        );
    }
}
