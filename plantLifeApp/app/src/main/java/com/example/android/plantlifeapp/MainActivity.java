package com.example.android.plantlifeapp;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.plantlifeapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity    {

    private ActivityMainBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    private ArrayList<Bakery> bakery=new ArrayList<>();
    private List list;
    com.example.android.plantlifeapp.MyAdapter adapter;
    Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.camera, R.id.mySnapsFragment, R.id.mySnapGarden)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        examplePlantsRecyclerView = findViewById(R.id.examplePlantsRecyclerView);
        examplePlantsRecyclerView.setNestedScrollingEnabled(false);


        examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setBakeryRecipes();
        adapter = new MyAdapter(this, bakery,list);

        examplePlantsRecyclerView.setAdapter(adapter);

    }


    private void setBakeryRecipes() {
        bakery.add(new Bakery("Daisy","Bellis perennis","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));

    }





}