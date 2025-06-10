package com.example.travelsiregar.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.travelsiregar.Activity.MainActivity;
import com.example.travelsiregar.Adapter.CategoryAdapter;
import com.example.travelsiregar.Adapter.PopularAdapter;
import com.example.travelsiregar.Adapter.RecommendedAdapter;
import com.example.travelsiregar.Adapter.SliderAdapter;
import com.example.travelsiregar.Domain.Category;
import com.example.travelsiregar.Domain.Item;
import com.example.travelsiregar.Domain.Location;
import com.example.travelsiregar.Domain.SliderItems;
import com.example.travelsiregar.R;
import com.example.travelsiregar.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();

        // Setup RecyclerView Recommended di sini
        binding.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        // binding.recyclerViewRecommended.setAdapter(...);

        initLocations();
        initBanners();
        initCategory();
        initPopular();
        initRecommended();
        return binding.getRoot();
    }

private void initRecommended() {
    DatabaseReference myref=database.getReference("Item");
    ArrayList<Item> list=new ArrayList<>();
    myref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if  (snapshot.exists()) {
                for(DataSnapshot issuee:snapshot.getChildren()) {
                    list.add(issuee.getValue(Item.class));
                }

                if(!list.isEmpty()){
                    binding.recyclerViewRecommended.setLayoutManager(
                            new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                    RecyclerView.Adapter adapter=new RecommendedAdapter(list);
                    binding.recyclerViewRecommended.setAdapter(adapter);
                }
                binding.progressBarRecommended.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

private void initPopular() {
    DatabaseReference myref=database.getReference("Popular");
    ArrayList<Item > list=new ArrayList<>();
    myref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if  (snapshot.exists()) {
                for(DataSnapshot issuee:snapshot.getChildren()) {
                    list.add(issuee.getValue(Item.class));
                }

                if(!list.isEmpty()){
                    binding.recyclerViewPopular .setLayoutManager(
                            new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                    RecyclerView.Adapter adapter=new PopularAdapter(list);
                    binding.recyclerViewPopular .setAdapter(adapter);
                }
                binding.progressBarPopular.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

private void initCategory() {
    DatabaseReference myref=database.getReference("Category");
    ArrayList<Category> list=new ArrayList<>();
    myref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if  (snapshot.exists()) {
                for(DataSnapshot issuee:snapshot.getChildren()) {
                    list.add(issuee.getValue(Category.class));
                }

                if(!list.isEmpty()){
                    binding.recyclerViewCategory.setLayoutManager(
                            new LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false));

                    RecyclerView.Adapter adapter=new CategoryAdapter(list);
                    binding.recyclerViewCategory.setAdapter(adapter);
                }
                binding.progressBarCategory.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

private void initLocations() {
    DatabaseReference myref=database.getReference("Location");
    ArrayList<Location> list=new ArrayList<>();
    myref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (!isAdded()) return;  // Cek fragment masih attached
            if  (snapshot.exists()) {
                for(DataSnapshot issuee:snapshot.getChildren()) {
                    list.add(issuee.getValue(Location.class));
                }

                ArrayAdapter<Location> adapter=new ArrayAdapter<>(requireActivity(),
                        R.layout.sp_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.locationSp.setAdapter(adapter);


            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

private void banners(
        ArrayList<SliderItems> items
){
    binding.viewPager2.setAdapter(new SliderAdapter(items,binding.viewPager2));
    binding.viewPager2.setClipToPadding(false);
    binding.viewPager2.setClipChildren(false);
    binding.viewPager2.setOffscreenPageLimit(3);
    binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER );

    CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
    binding.viewPager2.setPageTransformer(compositePageTransformer);


}
private void initBanners(){
    DatabaseReference myRef=database.getReference("Banner");
    binding.progressBarBanner.setVisibility(View.VISIBLE);
    ArrayList<SliderItems> items=new ArrayList<>();
    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                for(DataSnapshot issuee:snapshot.getChildren()){
                    items.add(issuee.getValue(SliderItems.class));
                }
                banners(items);
                binding.progressBarBanner.setVisibility(View.GONE);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}

    }
