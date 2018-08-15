package com.macbitsgoa.prdrive.viewholders;


import android.view.View;
import android.widget.TextView;

import com.macbitsgoa.prdrive.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    private TextView hostelName;

    public HomeViewHolder(View itemView) {
        super(itemView);
        hostelName = itemView.findViewById(R.id.ifhometv);
    }

    public void populate(@NonNull final String str) {
        hostelName.setText(str);
    }
}
