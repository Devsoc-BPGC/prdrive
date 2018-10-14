package com.macbitsgoa.prdrive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.viewholders.CheckoutViewHolder;

import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

import androidx.recyclerview.widget.RecyclerView;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutViewHolder> {
    private Context ctx;

    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newView = layoutInflater.inflate(R.layout.item_format_checkout,parent,false);
        return new CheckoutViewHolder(newView,ctx);
    }

    @Override
    public void onBindViewHolder(CheckoutViewHolder holder, int position) {
        if(!(merchModelList.get(position).getMerchSize1().equalsIgnoreCase("None"))&&!(merchModelList.get(position).getMerchSize2().equalsIgnoreCase("None"))&&!(merchModelList.get(position).getMerchSize3().equalsIgnoreCase("None"))) {
            holder.name.setText("Name: "+merchModelList.get(position).getMerchName());
            holder.size1.setText("Size1: "+merchModelList.get(position).getMerchSize1());
            holder.size2.setText("Size2: "+merchModelList.get(position).getMerchSize2());
            holder.size3.setText("Size3: "+merchModelList.get(position).getMerchSize3());

        }

    }

    @Override
    public int getItemCount() {
        return merchModelList.size();
    }
}
