package com.example.android.plantlifeapp.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.android.plantlifeapp.MyAdapter;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.android.plantlifeapp.Botany;
import com.example.android.plantlifeapp.DbAdapter;
import com.example.android.plantlifeapp.MainActivity;
import com.example.android.plantlifeapp.MyAdapter;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.botanyDatabase;
import com.example.android.plantlifeapp.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView examplePlantsRecyclerView;
    DbAdapter adapterDb;
    private ArrayList<Botany> botany= new ArrayList<>();
    DatabaseReference Pbase;
    DatabaseReference ref;
    String TAG;
    TextView pythonTextView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ref=FirebaseDatabase.getInstance().getReference();

        Query queryRoom = FirebaseDatabase.getInstance().getReference().child("Plant").limitToLast(100);

        callMYAdapter(queryRoom,root);

//        botanyDatabase.writeNewSnaps();

//        camreaButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                        {
//                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
//                        }
//                        else
//                        {
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            activityResultLauncher.launch(intent);
//
//                        }
//
//                    }
//                });
//        pythonTextView=  root.findViewById(R.id.pythonTextView);
//        pythonTextView.setText(getPythonHelloWorld());

        return root;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        super.onCreate(savedInstanceState);
//        pythonTextView=  root.findViewById(R.id.pythonTextView);
//        pythonTextView.setText(getPythonHelloWorld());



    }


    private String getPythonHelloWorld() {
        Python python = Python.getInstance();
        PyObject pythonFile = python.getModule("helloworldscript");
        return pythonFile.callAttr("helloworld").toString();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void callMYAdapter(Query pbase, View root){
        FirebaseRecyclerOptions<Botany> options
                = new FirebaseRecyclerOptions.Builder<Botany>()
                .setQuery(pbase, Botany.class)
                .build();
        this.examplePlantsRecyclerView = root.findViewById(R.id.examplePlantsRecyclerView);
        this.examplePlantsRecyclerView.setNestedScrollingEnabled(false);
        this.examplePlantsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.examplePlantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
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