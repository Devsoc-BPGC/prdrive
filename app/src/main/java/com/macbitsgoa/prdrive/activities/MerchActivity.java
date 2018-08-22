package com.macbitsgoa.prdrive.activities;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.MerchModel;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.MerchAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;


public class MerchActivity extends AppCompatActivity {

    RecyclerView rv;
    androidx.appcompat.widget.Toolbar toolbar;
    Button finishbtn;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String hostelName = getIntent().getExtras().getString("hostel");
        Toast.makeText(this,"Hostel name : " + hostelName,Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_merch);
        setSupportActionBar(toolbar);                                              //Setting up the Finish order button.


        finishbtn = findViewById(R.id.finish);
        toolbar = findViewById(R.id.toolbar);
        rv=findViewById(R.id.merchrv);
        rv.setLayoutManager(new LinearLayoutManager(MerchActivity.this));
        rv.setHasFixedSize(false);
        MerchAdapter merchAdapter = new MerchAdapter(MerchActivity.this);
        rv.setAdapter(merchAdapter);

        finishbtn.setOnClickListener(view -> {
            for (int i = 0; i < merchModelList.size(); i++) {
                Log.e("DATA IN THE LIST",merchModelList.get(i).getMerchName()+" "+merchModelList.get(i).getMerchDesc()+" "+merchModelList.get(i).getMerchSize()+" "+merchModelList.get(i).getMerchQty());
                //Log to ensure that the merchModelList going to the next activity is populated.
            }

        });
    }
}



