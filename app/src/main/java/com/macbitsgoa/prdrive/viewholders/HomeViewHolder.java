package com.macbitsgoa.prdrive.viewholders;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.activities.MerchActivity;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView hostelName;
    private Context context;
    private Activity activity;
    public HomeViewHolder(View itemView, Context context) {
        super(itemView);
        CardView cardView;
        this.context = context;
        this.activity = (Activity) this.context;
        hostelName = itemView.findViewById(R.id.ifhometv);
        cardView = itemView.findViewById(R.id.ifhomecard);
        cardView.setOnClickListener(this);
    }

    public void populate(@NonNull final String str) {
        hostelName.setText(str);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ifhomecard :
                Intent intent = new Intent(context, MerchActivity.class);
                intent.putExtra("hostel",hostelName.getText().toString());
                context.startActivity(intent);
                activity.finish();
                break;
            default:
                Toast.makeText(context,"Please select Hostel",Toast.LENGTH_LONG).show();
                 break;
        }
    }
}
