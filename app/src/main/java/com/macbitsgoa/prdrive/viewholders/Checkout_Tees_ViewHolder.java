package com.macbitsgoa.prdrive.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.macbitsgoa.prdrive.R;

import androidx.recyclerview.widget.RecyclerView;

public class CheckoutViewHolder extends RecyclerView.ViewHolder  {

   public  TextView name,size1,size2,size3;
   public  Context context;

     public CheckoutViewHolder(View newView, Context context) {
          super(newView);
          this.context = context;

          name = newView.findViewById(R.id.name);
          size1 = newView.findViewById(R.id.size1);
          size2 = newView.findViewById(R.id.size2);
          size3 = newView.findViewById(R.id.size3);

     }


}
