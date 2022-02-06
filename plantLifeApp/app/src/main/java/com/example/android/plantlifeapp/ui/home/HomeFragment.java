package com.example.android.plantlifeapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.plantlifeapp.Bakery;
//import com.example.android.plantlifeapp.MyAdapter;
import com.example.android.plantlifeapp.CamreaActivity2;
import com.example.android.plantlifeapp.List;
import com.example.android.plantlifeapp.MyAdapter;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.cameraAdapter;
import com.example.android.plantlifeapp.databinding.ActivityMainBinding;
import com.example.android.plantlifeapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List list;
    private  ArrayList<Bakery> bakery=new ArrayList<>();
    com.example.android.plantlifeapp.MyAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        examplePlantsRecyclerView = (RecyclerView) root.findViewById(R.id.examplePlantsRecyclerView);

        examplePlantsRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity().getApplicationContext()));
        examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setBakeryRecipes();
        adapter = new com.example.android.plantlifeapp.MyAdapter(getActivity().getApplicationContext(),bakery,list);


        examplePlantsRecyclerView.setAdapter(adapter);

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


    }

    void onTextClick(Bakery data) {
        // Now you can do however you want with the data here...
        Toast.makeText(getActivity(), "Got: " + data, Toast.LENGTH_SHORT).show();
    }
}