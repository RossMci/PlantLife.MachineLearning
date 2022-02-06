package com.example.android.plantlifeapp.ui.plantDescription;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.databinding.FragmentDescriptionBinding;
import com.example.android.plantlifeapp.databinding.FragmentMySnapsBinding;


public class descriptionFragment extends Fragment {
    private FragmentDescriptionBinding binding;


    public descriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_snaps, container, false);
        return  root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}