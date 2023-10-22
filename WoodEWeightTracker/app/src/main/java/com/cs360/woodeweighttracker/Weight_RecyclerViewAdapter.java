package com.cs360.woodeweighttracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Weight_RecyclerViewAdapter extends RecyclerView.Adapter<Weight_RecyclerViewAdapter.MyViewHolder> {

    static Context context;
    static ArrayList<String> date;
    static ArrayList<String> weight;
    LayoutInflater layoutInflater;

    public Weight_RecyclerViewAdapter(Context context, ArrayList<String> date, ArrayList<String> weight) {
        this.context = context;
        this.date = date;
        this.weight = weight;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.weight_recycler_view_row, parent, false);
        return new Weight_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewWeight.setText(String.valueOf(weight.get(position)));
        holder.textViewDate.setText(String.valueOf(date.get(position)));

    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewWeight,textViewDate;
        Button delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewWeight = itemView.findViewById(R.id.weightNumber);
            textViewDate = itemView.findViewById(R.id.weightDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("weight", weight.get(getAdapterPosition()));
                    intent.putExtra("date", date.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
