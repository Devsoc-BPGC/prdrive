package com.macbitsgoa.prdrive.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import com.macbitsgoa.prdrive.R;
import androidx.recyclerview.widget.RecyclerView;
import me.relex.photodraweeview.PhotoDraweeView;

public class MerchViewHolder extends RecyclerView.ViewHolder  {
  public TextView merchName;
  public TextView merchDesc;
  public Spinner merchSize,merchQty;
  public PhotoDraweeView merchImage;
  public Context context;

    public MerchViewHolder(View itemView,Context context) {
        super(itemView);
        this.context=context;
        merchName = itemView.findViewById(R.id.merchName);
        merchDesc = itemView.findViewById(R.id.merchDesc);
        merchImage= itemView.findViewById(R.id.merchImage);
        merchSize = itemView.findViewById(R.id.merchSize);
        merchQty  = itemView.findViewById(R.id.merchQty);
    }
}
