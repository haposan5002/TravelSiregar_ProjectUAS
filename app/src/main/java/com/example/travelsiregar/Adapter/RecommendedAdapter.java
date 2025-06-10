 package com.example.travelsiregar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelsiregar.Activity.DetailActivity2;
import com.example.travelsiregar.Domain.Item;
import com.example.travelsiregar.databinding.ViewholderPopularBinding;
import com.example.travelsiregar.databinding.ViewholderRecommendedBinding;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.Viewholder> {
     ArrayList<Item> items;
     Context context;
      ViewholderRecommendedBinding  binding;

    public RecommendedAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecommendedAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.Viewholder holder, int position) {

        binding.titleTxt.setText(items.get(position).getTitle());
        binding.priceTxt.setText("Rp"+items.get(position).getPrice());
        binding.addressTxt.setText(items.get(position).getAddress());
        binding.scoreTxt.setText("" + items.get(position).getScore());

        Glide.with(context)
                .load(items.get(position).getPic())
                .into(binding.pic);  // pastikan 'pic' adalah id dari ImageView di XML


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(context, DetailActivity2.class);
                    intent.putExtra("object",items.get(position));
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderRecommendedBinding binding) {
            super(binding.getRoot());
        }
    }
}
