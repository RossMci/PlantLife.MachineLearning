package com.example.android.plantlifeapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chaquo.python.PyObject;
import com.example.android.plantlifeapp.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;


public class MainActivity extends AppCompatActivity    {

    private ActivityMainBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    private ArrayList<Botany> botany= new ArrayList<>();
    com.example.android.plantlifeapp.MyAdapter adapter;
    DbAdapter adapterDb;
    Menu myMenu;
    DatabaseReference Pbase;
    DatabaseReference ref;
    String TAG;
    Context context;




    private void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

    private String getPythonHelloWorld() {
        Python python = Python.getInstance();
        PyObject pythonFile = python.getModule("predictplant");
        return pythonFile.callAttr("main", "pot.jpg").toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPython();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref=FirebaseDatabase.getInstance().getReference();
        Pbase =  ref.child("Plant");
        Query queryRoom = FirebaseDatabase.getInstance().getReference().child("Plant").limitToLast(100);

//        FirebaseRecyclerOptions<Botany> options
//                = new FirebaseRecyclerOptions.Builder<Botany>()
//                .setQuery(Pbase, Botany.class)
//                .build();

//        examplePlantsRecyclerView = findViewById(R.id.examplePlantsRecyclerView);
//        examplePlantsRecyclerView.setNestedScrollingEnabled(false);
//        examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
////        setBakeryRecipes();
//        adapter = new MyAdapter(this,botany);
//        adapterDb = new DbAdapter(this,options,botany);

//        callMYAdapter(queryRoom);
//        adapter = new MyAdapter(this, bakery,botany,options);
//        examplePlantsRecyclerView.setAdapter(adapter);
//        examplePlantsRecyclerView.setAdapter(adapterDb);





// Attach a listener to read the data at our posts reference
        queryRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Map :Value is: " + map);

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                       Botany data=ds.getValue(Botany.class);
                       botany.add(data);
                }
                Log.d(TAG,"ArrayList Value is"+botany);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

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



    public void callMYAdapter(Query queryRoom){
        FirebaseRecyclerOptions<Botany> options
                = new FirebaseRecyclerOptions.Builder<Botany>()
                .setQuery(queryRoom, Botany.class)
                .build();
        this.examplePlantsRecyclerView = findViewById(R.id.examplePlantsRecyclerView);
        this.examplePlantsRecyclerView.setNestedScrollingEnabled(false);
        this.examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        this.setBakeryRecipes();
//        this.adapter = new MyAdapter(this, bakery,botany,options);
        adapterDb = new DbAdapter(this,options, botany);
        this.examplePlantsRecyclerView.setAdapter(adapterDb);
    }

    public void setBakeryRecipes() {

        botany.add(new Botany("Rose","A Plant","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Rose","A Plant","https://cdn.pixabay.com/photo/2013/07/21/13/00/rose-165819_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Daisy","A Plant","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg","place","Rosa","Flower","Plant Type"));
        botany.add(new Botany("Daisy","A Plant","https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg","place","Rosa","Flower","Plant Type"));

    }


//
//
//    // Function to tell the app to start getting
//    // data from database on starting of the activity
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapterDb.startListening();
//    }
//
//    // Function to tell the app to stop getting
//    // data from database on stopping of the activity
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapterDb.stopListening();
//    }








}