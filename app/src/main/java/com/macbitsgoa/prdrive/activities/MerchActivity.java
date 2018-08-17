package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.MerchModel;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.HomeAdapter;
import com.macbitsgoa.prdrive.adapters.MerchAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MerchActivity extends AppCompatActivity {

    RecyclerView rv;
    MerchAdapter merchAdapter;
    private DatabaseReference databaseReference;
    ArrayList<MerchModel> merchModel = new ArrayList<>();
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String hostelName = getIntent().getExtras().getString("hostel");
        Toast.makeText(this,"Hostel name : " + hostelName,Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_merch);
        rv=findViewById(R.id.merchrv);


        databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("hostel");
        databaseReference.keepSynced(true);



    }


}
