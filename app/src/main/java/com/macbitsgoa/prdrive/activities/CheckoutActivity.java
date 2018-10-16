package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.CheckoutAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CheckoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        RecyclerView rv_checkout;
        Toolbar toolbar = null;
        Button confirmbtn;
        setSupportActionBar(toolbar);


        rv_checkout = findViewById(R.id.rv_checkout);
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


    }
}