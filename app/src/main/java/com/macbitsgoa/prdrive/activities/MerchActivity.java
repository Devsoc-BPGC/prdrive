package com.macbitsgoa.prdrive.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.MerchAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;


public class MerchActivity extends AppCompatActivity {
    
   
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.widget.Toolbar toolbar = null;
        RecyclerView rv;
        Button finishbtn;
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
            Intent intent1 = new Intent(MerchActivity.this, CheckoutActivity.class);
            startActivity(intent1);
            finish();
            Log.e("FINISH PRESSED","FINISH");

        });
    }
}



