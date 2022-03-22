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
        return  root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}