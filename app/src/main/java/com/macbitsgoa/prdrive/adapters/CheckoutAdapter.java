package com.macbitsgoa.prdrive.adapters;

import android.content.Context;
import android.util.Log;
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
        int i=0;
        if(!(merchModelList.get(position).getMerchSize().equalsIgnoreCase("None"))&&!(merchModelList.get(position).getMerchQty().equalsIgnoreCase("0"))) {
            holder.name.setText("Name: "+merchModelList.get(position).getMerchName());
            holder.size.setText("Size: "+merchModelList.get(position).getMerchSize());
            holder.qty.setText("Qty: "+merchModelList.get(position).getMerchQty());
            i++;

        }
        else {
            if(i==0)
            {
             position--;
            }
        }

    }

    @Override
    public int getItemCount() {
        return merchModelList.size();
    }
}
