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

   public Button scanBtn ;
   public Button signBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanEdt = findViewById(R.id.scanedt);
        signBtn = findViewById(R.id.signbtn);
        scanBtn = findViewById(R.id.scanbtn);
        signBtn.setOnClickListener(this);
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


        switch (view.getId())
        {
            case R.id.scanbtn :
               // Log.e("EDITTEXT",scanEdt.getText().toString()+ " ");
                                if(!scanEdt.getText().toString().isEmpty()) {
                                    int roomNo = Integer.parseInt(scanEdt.getText().toString());
                                    if (roomNo > 99 && roomNo <= 400) {
                                         IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                                         scanIntegrator.initiateScan();
                                    }
                                    else {
                                    Toast.makeText(this, "Please Enter Correct Room Number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                Toast.makeText(this, "Please Enter Room Number", Toast.LENGTH_SHORT).show();
                                }
                           // Log.e("BUTTON","SCANBTN PRESSED");
            break;

            case R.id.signbtn :

                Intent intent = new Intent(ScanActivity.this,PopActivity.class);
                startActivity(intent);
              //  Log.e("BUTTON","SIGN PRESSED");
            break;

            default: Toast.makeText(this,"Please Select A Mode",Toast.LENGTH_LONG).show();
            break;

        }
    }

}

