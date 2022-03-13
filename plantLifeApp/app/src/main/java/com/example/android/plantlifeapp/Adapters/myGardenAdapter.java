package com.example.android.plantlifeapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.plantlifeapp.Botany;
import com.example.android.plantlifeapp.DbAdapter;
import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.descriptionActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myGardenAdapter extends FirebaseRecyclerAdapter<
        Botany, myGardenAdapter.gardenViewholder> {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    Context context;

    private ArrayList<Botany> mySnapList= new ArrayList<>();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myGardenAdapter(@NonNull FirebaseRecyclerOptions<Botany> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myGardenAdapter.gardenViewholder holder, int position, @NonNull Botany model) {

    }

    @NonNull
    @Override
    public myGardenAdapter.gardenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new myGardenAdapter.gardenViewholder(view);
    }

    public class gardenViewholder extends RecyclerView.ViewHolder {

        private TextView name;
        private  TextView ScientificName;
        private ImageView imageView;
        public gardenViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameTextView);
            ScientificName=itemView.findViewById(R.id.scientificNameTextView);
            imageView=itemView.findViewById(R.id.itemimageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), descriptionActivity.class);

                    intent.putParcelableArrayListExtra("mySnapList",mySnapList);
                    intent.putExtra("pos",getAdapterPosition());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("position", (Bundle) position);

                    // Ross if you have ingredients and steps you coudl also add those to teh intent using putParecelabelArrayExtra(...)
                    context.startActivity(intent);
                }
            });

////            delete.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    int position = getAdapterPosition();
////                    ref.addValueEventListener(new ValueEventListener() {
////                        @Override
////                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                            if (dataSnapshot.hasChildren()) {
////                                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
////                                firstChild.getRef().removeValue();
////                            }
////                        }
////
////                        @Override
////                        public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                        }
////                    });
//                }
//            });
        }
    }
}
