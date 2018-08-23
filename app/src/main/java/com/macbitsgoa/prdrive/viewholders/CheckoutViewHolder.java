package com.macbitsgoa.prdrive.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.macbitsgoa.prdrive.R;

import androidx.recyclerview.widget.RecyclerView;

public class CheckoutViewHolder extends RecyclerView.ViewHolder  {

   public  TextView name,size,qty;
   public  Context context;

     public CheckoutViewHolder(View newView, Context context) {
          super(newView);
          this.context = context;

          name = newView.findViewById(R.id.name);
          size = newView.findViewById(R.id.size);
          qty = newView.findViewById(R.id.qty);

     }


}
