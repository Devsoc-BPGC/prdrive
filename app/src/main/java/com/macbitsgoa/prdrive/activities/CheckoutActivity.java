package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.CheckoutAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Toolbar toolbar = null;
        RecyclerView rv_checkout;
        Button editbtn;
        Button confirmbtn;
       // EditText room;
        setSupportActionBar(toolbar);

        for (int i = 0; i < merchModelList.size(); i++) {
            Log.e("DATA CHECKOUT ACTIVITY", merchModelList.get(i).getMerchName() + " " + merchModelList.get(i).getMerchDesc() + " " + merchModelList.get(i).getMerchSize1() + " " + merchModelList.get(i).getMerchSize2()+" "+merchModelList.get(i).getMerchSize3());
            //to check if the data has reached the CheckoutActivity.
        }

        rv_checkout = findViewById(R.id.rv_checkout);
        editbtn = findViewById(R.id.edit);
        confirmbtn = findViewById(R.id.confirm);
        rv_checkout.setLayoutManager(new LinearLayoutManager(CheckoutActivity.this));
        rv_checkout.setHasFixedSize(false);
        CheckoutAdapter checkoutAdapter = new CheckoutAdapter();
        rv_checkout.setAdapter(checkoutAdapter);

        confirmbtn.setOnClickListener(view -> {
            Toast.makeText(CheckoutActivity.this, "Order is confirmed", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(CheckoutActivity.this, ScanActivity.class);
            startActivity(intent1);
            finish();
        });

        editbtn.setOnClickListener(view -> {
           Intent intent = new Intent(CheckoutActivity.this, MerchActivity.class);
           startActivity(intent);
           finish();
       });

    }
}