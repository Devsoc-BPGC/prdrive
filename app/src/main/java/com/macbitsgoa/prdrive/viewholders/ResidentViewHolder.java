package com.macbitsgoa.prdrive.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.activities.MerchActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class ResidentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context ctx;
    public TextView name;
    public TextView roomNo;


    public ResidentViewHolder(@NonNull View itemView , Context ctx) {
        super(itemView);

        CardView cardView;
        this.ctx = ctx;
        name =itemView.findViewById(R.id.name);
        roomNo = itemView.findViewById(R.id.roomNo);
        cardView = itemView.findViewById(R.id.cv);
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            Intent i = new Intent(ctx,MerchActivity.class);
            ctx.startActivity(i);
    //take the activity to merchActivity
    }
}
