package com.example.android.plantlifeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CamreaActivity2 extends AppCompatActivity {
ImageView imageView;
private FileOutputStream fileOutputStream;
    private final static  int CODE= 100;

Button download,share;
private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camrea2);

        imageView = (ImageView)  findViewById(R.id.imageView2);
        int location= getIntent().getExtras().getInt("GlideImage123");
        cameraAdapter adapter= new cameraAdapter(this);
        Glide.with(this).load(adapter.ArrayImages[location]).into(imageView);

        download= findViewById(R.id.download);
        share= findViewById(R.id.share);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(CamreaActivity2.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED){

                       download();
                }

                else{
                    askPermission();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });


    }

    private void share() {
        StrictMode.VmPolicy.Builder builder =  new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        BitmapDrawable drawable = (BitmapDrawable)imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        File file = new File(getExternalCacheDir()+ "/" + getResources().getString(R.string.app_name)+".jpg");

         try{
               fileOutputStream = new FileOutputStream(file);
             bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
             fileOutputStream.flush();
             fileOutputStream.close();
             intent = new Intent(Intent.ACTION_SEND);
             intent.setType("image/*");

             intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);




         } catch (FileNotFoundException e){
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         startActivity(Intent.createChooser(intent,"share image"));

    }

    private void askPermission() {
        ActivityCompat.requestPermissions(CamreaActivity2.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
            CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(CODE== requestCode){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                download();
            }
            else{
                Toast.makeText(CamreaActivity2.this, "Permission needed", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    private void download() {
        File dir = new File(Environment.getExternalStorageDirectory(), "DownloadImage");
        if(!dir.exists()){
          dir.mkdir();
        }
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        File file = new File(dir, System.currentTimeMillis() + ".jpg");

        try {
             fileOutputStream= new FileOutputStream(file);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);

        Toast.makeText(CamreaActivity2.this, "Download complete", Toast.LENGTH_SHORT).show();
        try {
            fileOutputStream.flush();
            fileOutputStream.close();

            intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));
            sendBroadcast(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}