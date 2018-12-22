package com.macbitsgoa.prdrive.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.macbitsgoa.prdrive.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public TextView desc;
    EditText donate_edt;
    Context ctx;

    public DonationViewHolder(@NonNull View itemView, Context ctx) {
        super(itemView);

        this.ctx = ctx;
        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);
        donate_edt =itemView.findViewById(R.id.donate_edt);
    }



    @Override
    public void onClick(View view) {

    }
}
