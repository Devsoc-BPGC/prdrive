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
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class MerchAdapter extends RecyclerView.Adapter<MerchViewHolder> implements ValueEventListener{

    private Context ctx;
    private String size=null;                                                      //size and qty are to be obtained from user.
    private String qty=null;
    public MerchAdapter(Context ctx) {
        merchModelList = new ArrayList<>();
        this.ctx = ctx;
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("orgInfo").child("merch");
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(this);    //adding the firebase listener which currently points to "merch" in orgInfo.
    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        merchModelList = new ArrayList<>();
        for (DataSnapshot child: dataSnapshot.getChildren()){                //traversing through each node of "merch".
            String merchName = child.child("name").getValue(String.class);
            String merchDesc = child.child("desc").getValue(String.class);
            String merchUrl = child.child("imageURL").getValue(String.class);

             Uri merchUri;
             merchUri = Uri.parse(merchUrl);

            merchModelList.add(new MerchModel(merchName,merchDesc,merchUri,size,qty));
        }
        notifyDataSetChanged();
    }

    @Override
    public MerchViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View newView = layoutInflater.inflate(R.layout.item_merch_model,parent,false);
        return new MerchViewHolder(newView,ctx);
    }

    @Override
    public void onBindViewHolder(MerchViewHolder holder,int position) {

        holder.merchName.setText(merchModelList.get(position).getMerchName());
        holder.merchDesc.setText(merchModelList.get(position).getMerchDesc());
        holder.merchImage.setImageURI(merchModelList.get(position).getMerchUri());
        sizeQuantity(holder, position);
    }

    private void sizeQuantity(MerchViewHolder holder,int position) {

        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(ctx,R.array.Size,R.layout.support_simple_spinner_dropdown_item);
        adapterSize.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.merchSize.setAdapter(adapterSize);
       //spinner for size is setup with None as default selected.


        ArrayAdapter<CharSequence> adapterQty = ArrayAdapter.createFromResource(ctx,R.array.Quantity,R.layout.support_simple_spinner_dropdown_item);
        adapterQty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        holder.merchQty.setAdapter(adapterQty);
       //spinner for qty is setup with 0 as default selected.


        holder.merchSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           Toast.makeText(ctx,adapterView.getItemAtPosition(i)+ " is selected in Size",Toast.LENGTH_LONG).show();
           size=adapterView.getItemAtPosition(i).toString();
           merchModelList.get(position).setMerchSize(size);
          }
       //size is taken from user and inserted at the merchSize field of the object at index "position" of merchModelList.
         @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

             //This is method will be empty.
          }
        });


        holder.merchQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(ctx,adapterView.getItemAtPosition(i)+ " is selected in Quantity",Toast.LENGTH_LONG).show();
               qty= adapterView.getItemAtPosition(i).toString();
               merchModelList.get(position).setMerchQty(qty);

            }
            //qty is taken from user and inserted at the merchQty field of the object at index "position" of merchModelList

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                //This method will be empty.
            }
        });
    }

    @Override
    public int getItemCount() {
        return merchModelList.size();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e("TAG","fail "+databaseError.toString());
    }

}

