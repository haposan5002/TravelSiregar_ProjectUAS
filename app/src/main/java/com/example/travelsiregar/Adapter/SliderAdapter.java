package com.example.travelsiregar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.travelsiregar.Domain.SliderItems;
import com.example.travelsiregar.R;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderVewholder> {
    private ArrayList<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();

        }
    };

    public SliderAdapter(ArrayList<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderVewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent .getContext();
        return new SliderVewholder(LayoutInflater.from(context).inflate(R.layout.slider_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderVewholder holder, int position) {
    holder.setImage(sliderItems.get(position ));
    if(position==sliderItems.size()-2){
         viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderVewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;

         public SliderVewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlider );
        }
        void setImage(SliderItems sliderItems){
            Glide.with(context)
                    .load(sliderItems.getUrl())
                    .into (imageView);

        }
    }
}
