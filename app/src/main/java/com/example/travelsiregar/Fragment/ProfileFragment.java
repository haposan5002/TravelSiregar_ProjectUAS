package com.example.travelsiregar.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.os.Looper;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelsiregar.R;
import com.example.travelsiregar.Adapter.RecommendedAdapter;
import com.example.travelsiregar.Adapter.WishlistAdapter;
import com.example.travelsiregar.Domain.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Random;

public class ProfileFragment extends Fragment {

    private TextView quoteTextView, textEmptyWishlist;
    private RecyclerView wishlistRecyclerView, recommendationRecyclerView;
    private FirebaseDatabase database;

    private MapView mapView;

    private ArrayList<Item> wishlistItems = new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable quoteRunnable;

    private String[] quotes = {
            "\"Traveling – it leaves you speechless, then turns you into a storyteller.\" – Ibn Battuta",
            "\"Not all those who wander are lost.\" – J.R.R. Tolkien",
            "\"The world is a book, and those who do not travel read only one page.\" – Saint Augustine",
            "\"Life is short and the world is wide.\" – Unknown",
            "\"Take only memories, leave only footprints.\" – Chief Seattle"
    };

    private Random random = new Random();

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Configuration.getInstance().load(
                requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        quoteTextView = view.findViewById(R.id.travelQuote);
        wishlistRecyclerView = view.findViewById(R.id.wishlistRecyclerView);
        recommendationRecyclerView = view.findViewById(R.id.recommendationRecyclerView);
        textEmptyWishlist = view.findViewById(R.id.textEmptyWishlist);
        mapView = view.findViewById(R.id.osmMap);

        database = FirebaseDatabase.getInstance();

        setupQuoteRotation();
        setupWishlist();
        loadRecommendationsFromFirebase();
        setupMap();

        return view;
    }

    private void setupQuoteRotation() {
        quoteRunnable = new Runnable() {
            @Override
            public void run() {
                if (quoteTextView != null) {
                    int randomIndex = random.nextInt(quotes.length);
                    quoteTextView.setText(quotes[randomIndex]);
                    handler.postDelayed(this, 5000);
                }
            }
        };
        handler.post(quoteRunnable);
    }

    private void setupWishlist() {
        wishlistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wishlistItems.clear(); // Simulasi kosong, bisa diganti dengan load data

        updateWishlistUI();
    }

    private void updateWishlistUI() {
        if (wishlistItems.isEmpty()) {
            textEmptyWishlist.setVisibility(View.VISIBLE);
            wishlistRecyclerView.setVisibility(View.GONE);
        } else {
            textEmptyWishlist.setVisibility(View.GONE);
            wishlistRecyclerView.setVisibility(View.VISIBLE);

            WishlistAdapter adapter = new WishlistAdapter(wishlistItems);
            wishlistRecyclerView.setAdapter(adapter);
        }
    }

    private void loadRecommendationsFromFirebase() {
        recommendationRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        DatabaseReference ref = database.getReference("Item");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> recommendedList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) recommendedList.add(item);
                }
                if (!recommendedList.isEmpty()) {
                    RecommendedAdapter adapter = new RecommendedAdapter(recommendedList);
                    recommendationRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void setupMap() {
        mapView.setMultiTouchControls(true);
        IMapController mapController = mapView.getController();
        mapController.setZoom(12.0);
        GeoPoint jakarta = new GeoPoint(-6.200000, 106.816666);
        mapController.setCenter(jakarta);

        loadMarkersFromFirebase();
    }

    private void loadMarkersFromFirebase() {
        DatabaseReference ref = database.getReference("destinasi_favorit");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Double lat = snap.child("latitude").getValue(Double.class);
                    Double lon = snap.child("longitude").getValue(Double.class);
                    String nama = snap.child("nama").getValue(String.class);

                    if (lat != null && lon != null && nama != null) {
                        GeoPoint point = new GeoPoint(lat, lon);
                        Marker marker = new Marker(mapView);
                        marker.setPosition(point);
                        marker.setTitle(nama);
                        marker.setOnMarkerClickListener((m, mapView) -> {
                            Toast.makeText(getContext(), "Destinasi: " + m.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;
                        });
                        mapView.getOverlays().add(marker);
                    }
                }
                mapView.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Gagal mengambil destinasi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(quoteRunnable);
        mapView.onDetach();
    }
}
