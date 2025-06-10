package com.example.travelsiregar.Activity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.travelsiregar.Domain.Item;
import com.example.travelsiregar.R;
import com.example.travelsiregar.databinding.ActivityDetail2Binding;

public class DetailActivity2 extends AppCompatActivity {
ActivityDetail2Binding binding;
private Item object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityDetail2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
        
        

    }

    private void setVariable() {
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("Rp"+object.getPrice());
        binding.backBtn.setOnClickListener(v->finish());
        binding.bedTxt.setText(""+object.getBed());
        binding.durationTxt.setText(object.getDuration());
        binding.distanceTxt.setText(object.getDistance());
        binding.descriptionTxt.setText((object.getDescription()));
        binding.addressTxt.setText(object.getAddress());
        binding.ratingBar.setRating((float)object.getScore());
        binding.ratingTxt.setText(object.getScore()+ "Rating");

        Glide.with(DetailActivity2.this)
                .load(object.getPic())
                .into(binding.pic);

        binding.addToCartBtn.setOnClickListener(v -> {
            Intent intent=new Intent(DetailActivity2.this,TicketActivity.class);
            intent.putExtra("object",object);
            startActivity(intent);
        });

    }

    private void getIntentExtra() {
        object=(Item) getIntent().getSerializableExtra("object");
    }
}