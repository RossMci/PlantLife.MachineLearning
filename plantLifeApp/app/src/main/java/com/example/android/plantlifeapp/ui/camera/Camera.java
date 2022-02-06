package com.example.android.plantlifeapp.ui.camera;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.plantlifeapp.CamreaActivity2;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.cameraAdapter;
import com.example.android.plantlifeapp.databinding.FragmentCameraBinding;


public class Camera extends Fragment implements View.OnClickListener {
    private FragmentCameraBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;
    ActivityResultLauncher<Intent> img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        img= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                {
//                    @Override
//                public void onActivity(ActivityResult activity){
//
//        }
//                });
//
//        binding = FragmentCameraBinding.inflate(getLayoutInflater());
//        View root =binding.getRoot();
//
//        GridView gridView = root.findViewById(R.id.Grid);
//        gridView.setAdapter(new cameraAdapter(getActivity().getApplicationContext()));
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent= new Intent(getActivity().getApplicationContext(), CamreaActivity2.class);
//                intent.putExtra("GlideImage123",position);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GridView gridView = root.findViewById(R.id.Grid);
        gridView.setAdapter(new cameraAdapter(getActivity().getApplicationContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity().getApplicationContext(), CamreaActivity2.class);
                intent.putExtra("GlideImage123",position);
                startActivity(intent);
            }
        });
        Button camreaButton = (Button) root.findViewById(R.id.CamreaButton);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

    }

}