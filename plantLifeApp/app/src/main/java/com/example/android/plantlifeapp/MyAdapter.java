package com.example.android.plantlifeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.plantlifeapp.ui.plantDescription.descriptionFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends FirebaseRecyclerAdapter<Botany, MyAdapter.PlantHolder> {
//    RecyclerView.Adapter<MyAdapter.PlantHolder>
     ArrayList<Bakery> bakeryList= new ArrayList<>();
    ArrayList<Botany> botanyList= new ArrayList<>();
    Context context;
    Activity activity;
    private List list;
//    OnTextClickListener  listener;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private String TAG;



    public MyAdapter(Context context, ArrayList<Bakery> bakeryList, ArrayList<Botany> botanyList, FirebaseRecyclerOptions<Botany> options) {
        super(options);
        this.context = context;
        this.bakeryList = bakeryList;
        this.botanyList=botanyList;
//        this.activity = activity;
//        this.listener= listener;


    }


    public class PlantHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private  TextView description;
        private  ImageView imageView;
        private descriptionFragment descriptionFragment;


        public PlantHolder(final  View view){
            super(view);
            name=view.findViewById(R.id.nameTextView);
            description=view.findViewById(R.id.Label_descriptionTextView);
            imageView=view.findViewById(R.id.itemimageView);
//           Object position= bakeryList.get(getAdapterPosition());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), descriptionActivity.class);
//                    intent.putExtra("key",bakeryList.get(getAdapterPosition()));
                    intent.putParcelableArrayListExtra("Bakery",bakeryList);
                    intent.putParcelableArrayListExtra("botany",botanyList);
                    intent.putExtra("pos",getAdapterPosition());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("position", (Bundle) position);
                    
                    // Ross if you have ingredients and steps you coudl also add those to teh intent using putParecelabelArrayExtra(...)
                    context.startActivity(intent);
                }
            });

        }

    }

    @NonNull
    @Override
    public PlantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View valueView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new PlantHolder(valueView);
    }

//    @Override
//    public void onBindViewHolder(@NonNull PlantHolder holder, int position) {
//        String name = botanyList.get(position).getName();
//        String description= botanyList.get(position).getDescription();
//        String image= botanyList.get(position).getImage();
//
//        holder.name.setText(name);
//        holder.description.setText(description);
//
//        Glide.with(holder.imageView).load(botanyList.get(position).getImage()).into(holder.imageView);
//
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull PlantHolder holder, int position, @NonNull Botany model) {
        String name = botanyList.get(position).getName();
        String description= botanyList.get(position).getDescription();
        String image= botanyList.get(position).getImage();

        holder.name.setText(name);
        holder.description.setText(description);

        Glide.with(holder.imageView).load(botanyList.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return botanyList.size();
    }

    public void writeNewBotany(String bonId, String name, String description, String image, String origin, String scientificName, String species, String type) {
        Botany botany = new Botany( name,  description,  image,  origin,  scientificName,  species,  type);

        ref.child("Bontany").child(bonId).setValue(botany);
    }



    private void addBotanyEventListener(DatabaseReference ref) {
        ValueEventListener bontanyListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Botany bontany = dataSnapshot.getValue(Botany.class);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        ref.addValueEventListener(bontanyListener);
    }

}


