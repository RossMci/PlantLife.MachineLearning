package com.example.android.plantlifeapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class botanyDatabase {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refSnap = database.getReference();


    public void writeNewSnaps(String name, String description, String image, String origin, String scientificName, String species, String type) {
        refSnap=database.getReference().child("mySnaps");
        Botany botanyValue = new Botany( name,  description,  image,  origin,scientificName,  species,  type);
        refSnap.child("mySnaps").setValue(user);
    }
}
