package com.example.android.plantlifeapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.plantlifeapp.Bakery;
//import com.example.android.plantlifeapp.MyAdapter;
import com.example.android.plantlifeapp.Botany;
import com.example.android.plantlifeapp.DbAdapter;
import com.example.android.plantlifeapp.List;
import com.example.android.plantlifeapp.MainActivity;
import com.example.android.plantlifeapp.MyAdapter;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    DbAdapter adapterDb;
    private List list;
    private  ArrayList<Bakery> bakery=new ArrayList<>();
    private ArrayList<Botany> botany= new ArrayList<>();
    DatabaseReference Pbase;
    DatabaseReference ref;
    String TAG;
    com.example.android.plantlifeapp.MyAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ref=FirebaseDatabase.getInstance().getReference();
        Pbase = ref.child("Plant");

        FirebaseRecyclerOptions<Botany> options
                = new FirebaseRecyclerOptions.Builder<Botany>()
                .setQuery(Pbase, Botany.class)
                .build();

        examplePlantsRecyclerView = (RecyclerView) root.findViewById(R.id.examplePlantsRecyclerView);
        examplePlantsRecyclerView.setNestedScrollingEnabled(false);
        examplePlantsRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));
        examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        setBakeryRecipes();
        adapter = new com.example.android.plantlifeapp.MyAdapter(getActivity().getApplicationContext(),botany);

        adapter = new MyAdapter(getActivity().getApplicationContext(),botany);
        adapterDb = new DbAdapter(getActivity().getApplicationContext(),options,botany);

        callMYAdapter(Pbase,root);

//        ((MainActivity) getActivity()).callMYAdapter(Pbase);

        return root;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
    void onTextClick(Bakery data) {
        // Now you can do however you want with the data here...
        Toast.makeText(getActivity(), "Got: " + data, Toast.LENGTH_SHORT).show();
    }

    public void callMYAdapter(DatabaseReference pbase, View root){
        FirebaseRecyclerOptions<Botany> options
                = new FirebaseRecyclerOptions.Builder<Botany>()
                .setQuery(pbase, Botany.class)
                .build();
        this.examplePlantsRecyclerView = root.findViewById(R.id.examplePlantsRecyclerView);
        this.examplePlantsRecyclerView.setNestedScrollingEnabled(false);
        this.examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        this.setBakeryRecipes();
//        this.adapter = new MyAdapter(this, bakery,botany,options);
        adapterDb = new DbAdapter(getActivity().getApplicationContext(),options, botany);
        this.examplePlantsRecyclerView.setAdapter(adapterDb);
    }


    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    public void onStart() {
        super.onStart();
        adapterDb.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    public void onStop() {
        super.onStop();
        adapterDb.stopListening();
    }


}