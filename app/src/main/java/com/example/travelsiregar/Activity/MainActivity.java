package com.example.travelsiregar.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travelsiregar.Fragment.BookmarkFragment;
import com.example.travelsiregar.Fragment.ExprorerFragment;
import com.example.travelsiregar.Fragment.HomeFragment;
import com.example.travelsiregar.Fragment.ProfileFragment;
import com.example.travelsiregar.R;
import com.example.travelsiregar.databinding.ActivityMainBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import androidx.activity.EdgeToEdge;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseDatabase database;
    private ChipNavigationBar bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            bottomNavigationView.setItemSelected(R.id.exprorer, true);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();




        }

        bottomNavigationView.setOnItemSelectedListener(id -> {
            Fragment selectedFragment = null;

            if (id == R.id.exprorer) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.favorites) {
                selectedFragment = new ExprorerFragment();
            } else if (id == R.id.bookmark) {
                selectedFragment = new BookmarkFragment();
            } else if (id == R.id.cart) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

        });
    }
}
