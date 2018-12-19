package com.macbitsgoa.prdrive.viewholders;

import android.view.View;
import android.widget.TextView;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.Student;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.activities.MerchActivity.launchMerch;


public class ResidentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nameTv;
    public TextView roomNoTv;

    public ResidentViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.tv_name);
        roomNoTv = itemView.findViewById(R.id.tv_room_no);
    }

    public void populate(Student student) {
        nameTv.setText(student.name);
        roomNoTv.setText(student.roomNo);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        launchMerch(itemView.getContext());
    }
}
