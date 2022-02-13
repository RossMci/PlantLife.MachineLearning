package com.example.android.plantlifeapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DbAdapter extends FirebaseRecyclerAdapter<
        Botany, DbAdapter.plantViewholder> {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
Context context;
    private ArrayList<Botany> botanyList= new ArrayList<>();
    private String TAG;
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */

    public DbAdapter(Context context, @NonNull FirebaseRecyclerOptions<Botany> options, ArrayList<Botany> botanyList) {
        super(options);
        this.context=context;
        this.botanyList=botanyList;
    }

    @Override
    protected void onBindViewHolder(@NonNull DbAdapter.plantViewholder holder, int position, @NonNull Botany model) {
        holder.name.setText(model.getName());
        holder.ScientificName.setText(model.getScientificName());
        Glide.with(holder.imageView).load(model.getImage()).into(holder.imageView);

    }

    @NonNull
    @Override
    public DbAdapter.plantViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new DbAdapter.plantViewholder(view);
    }


    private void addBotanyEventListener(DatabaseReference ref) {
        ValueEventListener bontanyListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                    Botany botany = dataSnapshot.getValue(Botany.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        ref.addValueEventListener(bontanyListener);
    }

     class plantViewholder extends RecyclerView.ViewHolder {
        private TextView name;
        private  TextView ScientificName;
        private ImageView imageView;
        public plantViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameTextView);
            ScientificName=itemView.findViewById(R.id.scientificNameTextView);
            imageView=itemView.findViewById(R.id.itemimageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), descriptionActivity.class);

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


}
