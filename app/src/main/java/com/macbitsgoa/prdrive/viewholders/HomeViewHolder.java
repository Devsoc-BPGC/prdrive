package com.macbitsgoa.prdrive.viewholders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.activities.MerchActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import static com.macbitsgoa.prdrive.StaticHelperClass.hostelname;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView hostelName;
    private Context context;
    private Activity activity;
    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("orgInfo").child("prdrive-meta");


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
        final String[] killSwitch = new String[1];
        Log.e("data", databaseReference.getKey());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data1", dataSnapshot.getKey());
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.e("data1", child.getKey());
                    killSwitch[0] = Objects.requireNonNull(child.child("killSwitch").getValue(String.class));
                }
                if (killSwitch[0].equals("1")) {
                    Intent intent = new Intent(context, MerchActivity.class);
                    hostelname = hostelName.getText().toString();
                    context.startActivity(intent);
                    activity.finish();
                }
                else {
                    //activity.finish();
                    Toast.makeText(context, "not allowed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.e("data", ""+killSwitch[0]);

    }
}
