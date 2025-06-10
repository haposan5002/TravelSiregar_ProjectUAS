package com.example.travelsiregar.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelsiregar.Adapter.ItemAdapter;
import com.example.travelsiregar.Domain.Item;
import com.example.travelsiregar.R;

import java.util.ArrayList;

public class ExprorerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exprorer, container, false);

        recyclerView = view.findViewById(R.id.recyclerExploreList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Data dummy
        itemList = new ArrayList<>();
        itemList.add(new Item("Beach Paradise", "Beautiful beach with clear water"));
        itemList.add(new Item("Mountain Trek", "Exciting hiking experience"));
        itemList.add(new Item("City Tour", "Explore the vibrant city life"));

        adapter = new ItemAdapter(getContext(), itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
