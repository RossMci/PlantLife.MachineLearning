package com.example.android.plantlifeapp;

import android.graphics.Bitmap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class botanyDatabase {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refSnap = database.getReference().child("mySnaps");

    private String name;
    private String description;
    private String image;
    private String origin;
    private String scientificName;
    private String species;
    private String type;
    Botany bontany= new Botany(name,  description,  image,  origin,scientificName,  species,  type);



    public void writeNewSnaps(Botany botany) {
//        refSnap=database.getReference().child("mySnaps");
//        Botany botanyValue = new Botany( name,  description,  image,  origin,scientificName,  species,  type);
//        refSnap.child("mySnaps").setValue(botanyValue);
//
//               FirebaseFirestore.getInstance().collection("MySnaps")
//                .document()
//                .set(botany);


    }


    private void deleteMySnapPost(String name, String description, String image, String origin, String scientificName, String species, String type) {
        String childName= refSnap.getKey();
        Botany botanyValue = new Botany( name,  description,  image,  origin,scientificName,  species,  type);
        refSnap.child(name).removeValue();
    }


//    public void writeNewGarden(String name, String description, String image, String origin, String scientificName, String species, String type) {
//        refSnap=database.getReference().child("mySnaps");
//        Botany botanyValue = new Botany( name,  description,  image,  origin,scientificName,  species,  type);
//        refSnap.child("mySnaps").setValue(user);
//    }


    private void deleteGardenPost(String name, String description, String image, String origin, String scientificName, String species, String type) {
        String childName= refSnap.getKey();
        Botany botanyValue = new Botany( name,  description,  image,  origin,scientificName,  species,  type);
        refSnap.child(name).removeValue();
    }

//    private void saveRecipeInFirestore(Botany botany) {
//        FirebaseFirestore.getInstance().collection("Recipes")
//                .document()
//                .set(recipe);
//    }
}
