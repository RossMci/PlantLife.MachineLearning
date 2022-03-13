package com.example.android.plantlifeapp.ui.mySnaps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.databinding.FragmentMySnapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class mySnaps extends Fragment {
    private FragmentMySnapsBinding binding;
  private FloatingActionButton addSnapFloatingActionButton;

    public mySnaps() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMySnapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_snaps, container, false);
//        addSnapFloatingActionButton=root.findViewById(R.id.addSnapFloatingActionButton);
//        addSnapFloatingActionButton.setOnClickListener(
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
        return  root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}