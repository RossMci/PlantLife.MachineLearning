package com.example.android.plantlifeapp.ui.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.databinding.FragmentCameraBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class Camera extends Fragment {
    private FragmentCameraBinding binding;
    public static final int CAMERA_ACTION_CODE = 1;
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


    private Uri saveBitmapAsJpg(Bitmap bitmap) throws FileNotFoundException {

        File dir = Environment.getDownloadCacheDirectory();

        File file = new File(dir, System.currentTimeMillis() + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        return uri;
    }

    private String getPythonTestOutput() {
        Python python = Python.getInstance();
        PyObject pythonFile = python.getModule("predictplant");
        return pythonFile.callAttr("main", "pot.jpg").toString();
        //return pythonFile.callAttr("main", "/storage/emulated/0/Pictures/IMG_20220319_211355.JPG").toString();
    }


    private String getPythonOutput(Uri uri) {
        Python python = Python.getInstance();
        PyObject pythonFile = python.getModule("predictplant");
        return pythonFile.callAttr("main", uri.getPath()).toString();
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
            Log.d("predictionTest",getPythonTestOutput());
            try {
                Uri uri = saveBitmapAsJpg(bitmap);
                Log.d("Path",uri .getPath());
                String predictionResult = getPythonOutput(uri);
                Log.d("predictionResult",predictionResult);

                //String className =  predictionResult.split(",")[0]
                //String accuracy =  predictionResult.split(",")[1]
                camreaDisplayImageView.setImageBitmap(bitmap);
                Toast.makeText(getActivity(), predictionResult,
                        Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


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
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                        } else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activityResultLauncher.launch(intent);

                        }

                    }
                });

        camreaButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                        } else {
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