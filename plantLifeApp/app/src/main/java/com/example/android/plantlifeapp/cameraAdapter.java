package com.example.android.plantlifeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class cameraAdapter extends BaseAdapter {

public Context context;

    public cameraAdapter(Context context) {
        this.context = context;
    }

    public String[]ArrayImages={
            "https://cdn.pixabay.com/photo/2012/03/01/00/55/garden-19830_960_720.jpg"
    };
    @Override
    public int getCount() {
        return ArrayImages.length;
    }

    @Override
    public Object getItem(int position) {
        return ArrayImages[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(context);
        Glide.with(context).load(ArrayImages[position]).centerCrop().into(imageView);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300,350));
        return imageView;
    }
}