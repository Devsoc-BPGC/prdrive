package com.macbitsgoa.prdrive.viewholders;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.prdrive.R;

import androidx.recyclerview.widget.RecyclerView;


public class MerchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  TextView merchName,merchDesc;
  Spinner merchSize,merchQty;
  SimpleDraweeView merchImage;

    public MerchViewHolder(View itemView) {
        super(itemView);

        merchName = itemView.findViewById(R.id.merchName);
        merchDesc = itemView.findViewById(R.id.merchDesc);
        merchImage= itemView.findViewById(R.id.merchImage);
        merchSize = itemView.findViewById(R.id.merchSize);
        merchQty  = itemView.findViewById(R.id.merchQty);

    }

    @Override
    public void onClick(View view) {

    }
}
