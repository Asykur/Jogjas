package com.example.jogjas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jogjas.R;
import com.example.jogjas.activity.DetailLocationActivity;
import com.example.jogjas.pojo.DataLocation;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DataLocation> dataList;
    private Context context;

    public DataAdapter(ArrayList<DataLocation> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_location,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DataLocation data = dataList.get(position);
        ViewHolder vh = (ViewHolder)holder;

        vh.tvLocName.setText(data.getNama_pariwisata());
        vh.tvLocStreet.setText(data.getAlamat_pariwisata());
        Glide.with(vh.cardView.getContext()).load(data.getGambar_pariwisata()).into(vh.imageView);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailLocationActivity.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList.size() == 0) ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView tvLocName,tvLocStreet;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardLocation);
            tvLocName = itemView.findViewById(R.id.tvLocName);
            tvLocStreet = itemView.findViewById(R.id.tvLocStreet);
            imageView = itemView.findViewById(R.id.imgLocation);
        }

    }
}
