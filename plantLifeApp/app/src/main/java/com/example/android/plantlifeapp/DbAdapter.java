package com.example.android.plantlifeapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbAdapter extends FirebaseRecyclerAdapter<
        Botany, DbAdapter.plantViewholder> {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
Context context;
    private ArrayList<Botany> botanyList= new ArrayList<Botany>();
    private ArrayList<Botany> botanyList2;
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
        ;



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
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Botany botany = ds.getValue(Botany.class);
                    botanyList.add(botany);
                }
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


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

            // A new comment has been added, add it to the displayed list
            Comment comment = dataSnapshot.getValue(Comment.class);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            Comment newComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            Comment movedComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
//            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//            Toast.makeText(mContext, "Failed to load comments.",
//                    Toast.LENGTH_SHORT).show();
        }
    };
//      ref.addChildEventListener(childEventListener);



     class plantViewholder extends RecyclerView.ViewHolder {
        private TextView name;
        private  TextView ScientificName;
        private ImageView imageView;
        private FloatingActionButton addSnapFloatingActionButton2;
        public plantViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameTextView);
            ScientificName=itemView.findViewById(R.id.scientificNameTextView);
            imageView=itemView.findViewById(R.id.itemimageView);
            addSnapFloatingActionButton2=itemView.findViewById(R.id.addSnapFloatingActionButton);

//            addSnapFloatingActionButton2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(view.getContext(), descriptionActivity.class);
//
//                    intent.putParcelableArrayListExtra("botany",botanyList);
//                    intent.putExtra("pos",getAdapterPosition());
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent.putExtra("position", (Bundle) position);
//
//                    // Ross if you have ingredients and steps you coudl also add those to teh intent using putParecelabelArrayExtra(...)
//                    context.startActivity(intent);
//                }
//            });
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

//            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {
//
//                @Override
//                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                    Toast.makeText(context, "on Move", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//
//                @Override
//                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                    Toast.makeText(context, "on Swiped ", Toast.LENGTH_SHORT).show();
//                    //Remove swiped item from list and notify the RecyclerView
//                    int position = viewHolder.getAdapterPosition();
////                    arrayList.remove(position);
////                    adapter.notifyDataSetChanged();
//
//                }
//            };

        }

    }


}
