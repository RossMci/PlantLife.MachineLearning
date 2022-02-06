package com.example.android.plantlifeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class descriptionActivity extends AppCompatActivity {
     ArrayList<Bakery> bakeryList=new ArrayList<>();
    private ImageView descriptionImageView;
    private TextView lableCommonNameextView;
    private TextView labaleNameTextView;
    private TextView plantTextView;
    private TextView commonNametextView;
    private TextView descriptionLableTextView;
    private TextView descriptionInfoTextView;
    Bakery plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Object position;
        String[] arrayImages;

         ImageView descriptionImageView= findViewById(R.id.descriptionImageView);
         TextView lableCommonNameextView= findViewById(R.id.lableCommonNameextView);
         TextView labaleNameTextView= findViewById(R.id.labaleNameTextView);
         TextView plantTextView= findViewById(R.id.plantTextView);
         TextView commonNametextView= findViewById(R.id.commonNametextView);
         TextView descriptionLableTextView= findViewById(R.id.descriptionLableTextView);
         TextView descriptionInfoTextView= findViewById(R.id.descriptionInfoTextView);

         if(getIntent() !=null) {
             bakeryList = getIntent().getParcelableArrayListExtra("bakeryList");
             position= getIntent().getBundleExtra("position");
             Glide.with(this).load(bakeryList[position]).centerCrop().into(descriptionImageView);

         }


    }
}