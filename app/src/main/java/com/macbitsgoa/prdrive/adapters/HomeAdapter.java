package com.macbitsgoa.prdrive.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.viewholders.HomeViewHolder;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

    String hostelNames[] = {"AH1", "AH2", "AH3", "AH4", "AH5", "AH6", "AH7",
            "AH8", "AH9", "CH1", "CH2", "CH3", "CH4", "CH5", "CH6", "CH7", "DH1", "DH2"};

    public HomeAdapter(String hostelnames[]) {
        hostelnames = hostelNames;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(View.inflate(parent.getContext(), R.layout.item_format_home,null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.populate(hostelNames[position]);
    }

    @Override
    public int getItemCount() {
        return hostelNames.length;
    }
}
