package com.macbitsgoa.prdrive.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.viewholders.ResidentViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentViewHolder> {
    Context context;

    public ResidentAdapter(Context ctx) {
        this.context = ctx;
    }

    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResidentViewHolder(View.inflate(parent.getContext(), R.layout.item_format_resident, null) ,
                parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return merchModelList.size();
    }
}
