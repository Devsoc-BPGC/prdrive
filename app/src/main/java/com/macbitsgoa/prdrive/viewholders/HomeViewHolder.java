package com.macbitsgoa.prdrive.viewholders;

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

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.activities.ResidentActivity.launchHostel;

import static com.macbitsgoa.prdrive.StaticHelperClass.hostelname;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView hostelNameTv;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
            .child("main").child("orgInfo").child("prdrive-meta");
    private CardView cardView;


    public HomeViewHolder(View itemView) {
        super(itemView);
        hostelNameTv = itemView.findViewById(R.id.ifhometv);
        cardView = itemView.findViewById(R.id.ifhomecard);
    }

    public void populate(@NonNull final String hostel) {
        hostelNameTv.setText(hostel);
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String[] killSwitch = new String[1];
        Log.e("data", databaseReference.getKey());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data1", dataSnapshot.getKey());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.e("data1", child.getKey());
                    killSwitch[0] = Objects.requireNonNull(child.child("killSwitch").getValue(String.class));
                }
                if (killSwitch[0].equals("1")) {
                     hostelname = hostelNameTv.getText().toString();
                    launchHostel(itemView.getContext(), hostelname);
                    //activity.finish();
                } else {
                    //activity.finish();
                    Toast.makeText(itemView.getContext(), "not allowed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.e("data", "" + killSwitch[0]);

    }
}
