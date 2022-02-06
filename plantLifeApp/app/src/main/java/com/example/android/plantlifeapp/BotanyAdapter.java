package com.example.android.plantlifeapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BotanyAdapter extends FirebaseRecyclerAdapter<
        Botany, BotanyAdapter.botanyViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BotanyAdapter(@NonNull FirebaseRecyclerOptions<Botany> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull botanyViewholder holder, int position, @NonNull Botany model) {

    }

    @NonNull
    @Override
    public botanyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class botanyViewholder extends RecyclerView.ViewHolder {
        public botanyViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
