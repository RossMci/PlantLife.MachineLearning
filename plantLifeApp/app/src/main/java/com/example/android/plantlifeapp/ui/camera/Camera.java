package com.example.android.plantlifeapp.ui.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.plantlifeapp.CamreaActivity2;
import com.example.android.plantlifeapp.DbAdapter;
import com.example.android.plantlifeapp.FlowerML;
import com.example.android.plantlifeapp.MainActivity;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.cameraAdapter;
import com.example.android.plantlifeapp.databinding.FragmentCameraBinding;


public class  Camera extends Fragment   {
    private FragmentCameraBinding binding;
    public static final int CAMERA_ACTION_CODE=1;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> requestPermissionLauncher;

    String currentPhotoPath;
    ActivityResultLauncher<Intent> img;
    Button camreaButton;
    ImageView camreaDisplayImageView;

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


//        this.setBakeryRecipes();
//        this.adapter = new MyAdapter(this, bakery,botany,options);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Toast.makeText(getActivity(), "use the button to start identifying plants",
                Toast.LENGTH_LONG).show();

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
         camreaButton = (Button) root.findViewById(R.id.CamreaButton);
         camreaDisplayImageView = root.findViewById(R.id.camreaDisplayImageView);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

                Bundle bundle = result.getData().getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
            Toast.makeText(getActivity(), "Identifying plant type",
                    Toast.LENGTH_LONG).show();
//             FlowerML.identifyFlower();
                camreaDisplayImageView.setImageBitmap(bitmap);
            Toast.makeText(getActivity(), "Successful identification of a Plant",
                    Toast.LENGTH_LONG).show();

        });

       requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activityResultLauncher.launch(intent);
                    } else {
                        Toast.makeText(getActivity(), "There is no app that support this action",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        camreaButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                        }
                        else
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activityResultLauncher.launch(intent);

                        }

                    }
                });

        camreaButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                        }
                        else
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activityResultLauncher.launch(intent);

                        }

                    }
                });


        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    public void onClick(View v) {
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CAMERA_ACTION_CODE)
//        {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText( getActivity(),"camera permission granted", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                activityResultLauncher.launch(intent);
//            }
//            else
//            {
//                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }

    public void writeNewUser(Bitmap bitmap) {
//        User user = new User(name, email);
//
//        mDatabase.child("users").child(userId).setValue(user);
    }


}