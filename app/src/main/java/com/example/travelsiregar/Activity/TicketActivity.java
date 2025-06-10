package com.example.travelsiregar.Activity;

import android.content.Intent;
import android.net.Uri;
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
import com.example.travelsiregar.databinding.ActivityTicketBinding;

public class TicketActivity extends AppCompatActivity {
    ActivityTicketBinding binding;
    private Item object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
        


    }

    private void setVariable() {
        Glide.with(TicketActivity.this)
                .load(object.getPic())
                .into(binding.pic);

        Glide.with(TicketActivity.this)
                .load(object.getTourGuidePic())
                .into(binding.profile);

                binding.backBtn.setOnClickListener(v->finish());
                binding.titleTxt.setText(object.getTitle());
                binding.durationTxt.setText(object.getDuration());
                binding.tourGuideNameTxt.setText(object.getTourGuideName());
                binding.timeTxt.setText(object.getTimeTour());
                binding.timeTxtTourGuideTxt.setText(object.getDateTour());

                binding.callBtn.setOnClickListener(v -> {
                    Intent sendIntent=new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"+object.getTourGuidePhone()));
                    sendIntent.putExtra("sms_body","type your messange");
                    startActivity(sendIntent);
                });

                binding.messageBtn.setOnClickListener(v -> {
                    String phone=object.getTourGuidePhone();
                    Intent intent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel","phone", null));
                    startActivity(intent);

                });

    }

    private void getIntentExtra() {
        object=(Item) getIntent().getSerializableExtra("object");

    }
}