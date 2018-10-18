package com.macbitsgoa.prdrive.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.MerchModel;
import com.macbitsgoa.prdrive.R;

import com.macbitsgoa.prdrive.viewholders.MerchViewHolder;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.finishbtn;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class MerchAdapter extends RecyclerView.Adapter<MerchViewHolder> implements ValueEventListener {

    private Context ctx;

    private String size1 = "none";                                                      //sizes are to be obtained from user.
    private String size2 = "none";
    private String size3 = "none";

    public MerchAdapter(Context ctx) {
        merchModelList = new ArrayList<>();
        this.ctx = ctx;


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE)
                .child("main").child("orgInfo").child("prdrive-meta").child("prdrive1-001").child("merch");

        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(this);
        //adding the firebase listener which currently points to "merch" in orgInfo.
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        merchModelList = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {                //traversing through each node of "merch".
            String merchName = child.child("name").getValue(String.class);
            String merchDesc = child.child("desc").getValue(String.class);
            String merchUrl = child.child("imageUrl").getValue(String.class);
            String merchId = child.child("merchId").getValue(String.class);

            Uri merchUri;
            merchUri = Uri.parse(merchUrl);
            merchModelList.add(new MerchModel(merchName, merchDesc, merchUri, size1, size2, size3, merchId));
            notifyDataSetChanged();
        }
    }

    @Override
    public MerchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newView = layoutInflater.inflate(R.layout.item_merch_model, parent, false);
        return new MerchViewHolder(newView, ctx);
    }

    @Override
    public void onBindViewHolder(MerchViewHolder holder, int position) {
        holder.merchName.setText(merchModelList.get(position).getMerchName());
        holder.merchDesc.setText(merchModelList.get(position).getMerchDesc());
        holder.merchImage.setImageURI(merchModelList.get(position).getMerchUri());
        sizeQuantity(holder, position);
    }

    private void sizeQuantity(MerchViewHolder holder, int position) {

        ArrayAdapter<CharSequence> adapterSize1 = ArrayAdapter.createFromResource(ctx, R.array.SIZE1, R.layout.support_simple_spinner_dropdown_item);
        adapterSize1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.merchSize1.setAdapter(adapterSize1);
        //spinner for size is setup with None as default selected.


        ArrayAdapter<CharSequence> adapterSize2 = ArrayAdapter.createFromResource(ctx, R.array.SIZE2, R.layout.support_simple_spinner_dropdown_item);
        adapterSize2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.merchSize2.setAdapter(adapterSize2);
        //spinner for qty is setup with 0 as default selected.

        ArrayAdapter<CharSequence> adapterSize3 = ArrayAdapter.createFromResource(ctx, R.array.SIZE3, R.layout.support_simple_spinner_dropdown_item);
        adapterSize3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.merchSize3.setAdapter(adapterSize3);


        holder.merchSize1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(ctx, adapterView.getItemAtPosition(i) + " is selected in Size 1", Toast.LENGTH_LONG).show();
                size1 = adapterView.getItemAtPosition(i).toString();
                merchModelList.get(position).setMerchSize1(size1);

                if(!size1.equalsIgnoreCase("none"))
                    finishbtn.setVisibility(View.VISIBLE);
            }

            //size is taken from user and inserted at the merchSize field of the object at index "position" of merchModelList.
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                merchModelList.get(position).setMerchSize1("none");
            }
        });

        holder.merchSize2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ctx, adapterView.getItemAtPosition(i) + " is selected in Size 2", Toast.LENGTH_LONG).show();
                size2 = adapterView.getItemAtPosition(i).toString();
                merchModelList.get(position).setMerchSize2(size2);

                if(!size2.equalsIgnoreCase("none"))
                    finishbtn.setVisibility(View.VISIBLE);

            }

            //qty is taken from user and inserted at the merchQty field of the object at index "position" of merchModelList
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                merchModelList.get(position).setMerchSize2("none");
            }
        });


        holder.merchSize3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ctx, adapterView.getItemAtPosition(i) + " is selected in Size 3", Toast.LENGTH_LONG).show();
                size3 = adapterView.getItemAtPosition(i).toString();
                merchModelList.get(position).setMerchSize3(size3);

                if(!size3.equalsIgnoreCase("none"))
                    finishbtn.setVisibility(View.VISIBLE);

                if(size1.equalsIgnoreCase("none")&&size2.equalsIgnoreCase("none")&&size3.equalsIgnoreCase("none"))
                    finishbtn.setVisibility(View.INVISIBLE);
            }

            //size is taken from user and inserted at the merchSize field of the object at index "position" of merchModelList.
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                merchModelList.get(position).setMerchSize3("none");

            }
        });

    }

    @Override
    public int getItemCount() {
        return merchModelList.size();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e("TAG", "fail " + databaseError.toString());
    }
}


