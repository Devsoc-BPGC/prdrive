package com.macbitsgoa.prdrive.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.DonationModel;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.viewholders.DonationViewHolder;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.donationModelList;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class DonationAdapter extends RecyclerView.Adapter<DonationViewHolder> {


    Context ctx;
    public DonationAdapter(Context ctx) {

        this.ctx = ctx;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
                .child("main").child("orgInfo").child("prdrive-meta").child("prdrive1-001").child("donation");

        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationModelList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {                //traversing through each node of "merch".
                    String title = child.child("title").getValue(String.class);
                    String desc = child.child("desc").getValue(String.class);



                    donationModelList.add(new DonationModel(title, desc));
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @NonNull

    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donation_model, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {

        holder.title.setText(donationModelList.get(position).getTitle());
        holder.desc.setText(donationModelList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return donationModelList.size();
    }
}
