package com.example.travelsiregar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelsiregar.Activity.DetailActivity2;
import com.example.travelsiregar.Domain.Item;
import com.example.travelsiregar.databinding.ViewholderWishlistBinding;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.Viewholder> {

    private ArrayList<Item> items;
    private Context context;
    private ViewholderWishlistBinding binding;

    public WishlistAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public WishlistAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderWishlistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.Viewholder holder, int position) {
        Item item = items.get(position);

        binding.titleTxt.setText(item.getTitle());
        binding.priceTxt.setText("Rp" + item.getPrice());
        binding.addressTxt.setText(item.getAddress());
        binding.scoreTxt.setText(String.valueOf(item.getScore()));

        Glide.with(context)
                .load(item.getPic())
                .into(binding.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity2.class);
            intent.putExtra("object", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderWishlistBinding binding) {
            super(binding.getRoot());
        }
    }
}
