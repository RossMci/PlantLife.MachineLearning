package com.example.android.plantlifeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class descriptionActivity extends AppCompatActivity {
     ArrayList<Botany> botanyList=new ArrayList<>();
    private ImageView descriptionImageView;
    private TextView lableCommonNameextView;
    private TextView labaleNameTextView;
    private TextView plantTextView;
    private TextView commonNametextView;
    private TextView descriptionLableTextView;
    private TextView descriptionInfoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int position;
        String[][] arrayImages = new String[0][];

         ImageView descriptionImageView= findViewById(R.id.descriptionImageView);
         TextView plantTextView= findViewById(R.id.plantTextView);
         TextView commonNametextView= findViewById(R.id.commonNametextView);
         TextView descriptionInfoTextView= findViewById(R.id.descriptionInfoTextView);
         TextView originTextView=findViewById(R.id.originTextView);
         TextView speciesTextView=findViewById(R.id.speciesTextView);
         TextView typeTextView=findViewById(R.id.typeTextView);
         if(getIntent() !=null) {
             botanyList = getIntent().getParcelableArrayListExtra("botany");
             position= getIntent().getExtras().getInt("pos");
             Glide.with(this).load(botanyList.get(position).getImage()).centerCrop().into(descriptionImageView);
             String name = botanyList.get(position).getName();
             String ScientificName= botanyList.get(position).getScientificName();
             String origin= botanyList.get(position).getOrigin();
             String species= botanyList.get(position).getSpecies();
             String type= botanyList.get(position).getType();
             String description= botanyList.get(position).getDescription();
//
             commonNametextView.setText(name);
             plantTextView.setText(ScientificName);
             originTextView.setText(origin);
             speciesTextView.setText(species);
             typeTextView.setText(type);
             descriptionInfoTextView.setText(description);

         }






    }
}