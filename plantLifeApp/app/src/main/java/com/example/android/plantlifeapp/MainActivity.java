package com.example.android.plantlifeapp;

import android.os.Bundle;
import android.view.Menu;

import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity    {

    private ActivityMainBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    private ArrayList<Bakery> bakery=new ArrayList<>();
    private ArrayList<Botany> botany= new ArrayList<>();
    private List list;
    com.example.android.plantlifeapp.MyAdapter adapter;
    Menu myMenu;
    DatabaseReference Pbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pbase = FirebaseDatabase.getInstance().getReference();

        examplePlantsRecyclerView = findViewById(R.id.examplePlantsRecyclerView);
//        firbaseQuery();
        callMYAdapter(examplePlantsRecyclerView,options);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.camera, R.id.mySnapsFragment, R.id.mySnapGarden)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }



    public void callMYAdapter(RecyclerView examplePlantsRecyclerView, FirebaseRecyclerOptions<Botany> options){
        this.examplePlantsRecyclerView = findViewById(R.id.examplePlantsRecyclerView);
        this.examplePlantsRecyclerView.setNestedScrollingEnabled(false);
        this.examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setBakeryRecipes();
       this.adapter = new MyAdapter(this, bakery,botany,options);
        this.examplePlantsRecyclerView.setAdapter(adapter);
    }

    public void firbaseQuery(){
        FirebaseRecyclerOptions<Botany> options
                = new FirebaseRecyclerOptions.Builder<Botany>()
                .setQuery(Pbase, Botany.class)
                .build();
        callMYAdapter(examplePlantsRecyclerView,options);

    }
    private void setBakeryRecipes() {
        bakery.add(new Bakery("Daisy","Bellis perennis","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));
        bakery.add(new Bakery("Rose","Rosa","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg"));

        botany.add(new Botany("Rose","A Plant","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Rose","A Plant","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Daisy","A Plant","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Daisy","A Plant","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg","place","Rosa","Flower","Plant Type"));

    }


//    // Function to tell the app to start getting
//    // data from database on starting of the activity
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    // Function to tell the app to stop getting
//    // data from database on stopping of the activity
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }






}