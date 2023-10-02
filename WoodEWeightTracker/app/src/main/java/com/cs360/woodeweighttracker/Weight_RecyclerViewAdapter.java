package com.cs360.woodeweighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Weight_RecyclerViewAdapter extends RecyclerView.Adapter<Weight_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<WeightModel> weightModel;

    public Weight_RecyclerViewAdapter(Context context, ArrayList<WeightModel> weightModel) {
        this.context = context;
        this.weightModel = weightModel;
    }

    @NonNull
    @Override
    public Weight_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gives a look to each row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.weight_recycler_view_row, parent, false);
        return new Weight_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Weight_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigns values to the views
        holder.textViewDate.setText(weightModel.get(position).getWeightDate());
        holder.textViewWeight.setText(weightModel.get(position).getWeightNumber());
    }

    @Override
    public int getItemCount() {
        // finds out how many items
        return weightModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // similar to Oncreate

        TextView textViewWeight,textViewDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewWeight = itemView.findViewById(R.id.weightNumber);
            textViewDate = itemView.findViewById(R.id.weightDate);
        }
    }
}
