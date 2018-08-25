package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.R;

import static com.macbitsgoa.prdrive.StaticHelperClass.hostelname;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;
import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;

import androidx.appcompat.app.AppCompatActivity;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText scanEdt;
    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("hostel").child(hostelname);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanEdt = findViewById(R.id.scanedt);
        Button scanBtn = findViewById(R.id.scanbtn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            databaseReference.child("idno").child("buyerId").setValue(scanContent);
            databaseReference.child("idno").child("sellerId").setValue(sellerId);
            int roomNo = Integer.parseInt(scanEdt.getText().toString());
            databaseReference.child("idno").child("room").setValue(roomNo);
            for (int i=0; i<merchModelList.size(); i++) {
                if (Integer.parseInt(merchModelList.get(i).getMerchQty()) != 0) {
                    databaseReference.child("idno").child("merch" + i).child("size").setValue(merchModelList.get(i).getMerchSize());
                    databaseReference.child("idno").child("merch" + i).child("qty").setValue(merchModelList.get(i).getMerchQty());
                }
            }
            Toast.makeText(this, "Order done", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ScanActivity.this, MerchActivity.class));
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View view) {
        int roomNo = Integer.parseInt(scanEdt.getText().toString());
        if(roomNo>99 && roomNo<=400) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else {
            Toast.makeText(this, "Please enter proper room no", Toast.LENGTH_SHORT).show();
        }
    }
}

