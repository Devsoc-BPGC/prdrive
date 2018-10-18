package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.IdModel;
import com.macbitsgoa.prdrive.R;

import static com.macbitsgoa.prdrive.StaticHelperClass.hostelname;
import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText scanEdt;
    private CheckBox comboCb;
    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("hostel");
    static private String Id;
    static private String Name;
    public Button scanBtn;
    public Button signBtn;
    static boolean combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        comboCb = findViewById(R.id.comboCB);

        scanEdt = findViewById(R.id.scanedt);
        signBtn = findViewById(R.id.signbtn);
        scanBtn = findViewById(R.id.scanbtn);
        Realm.init(ScanActivity.this);
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
        final int[] flag = {0};
        Realm db = Realm.getDefaultInstance();
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
                if(!scanEdt.getText().toString().isEmpty()) {
                    String roomNo1 = scanEdt.getText().toString();
                    if (isInteger(roomNo1)) {
                        int roomNo = Integer.parseInt(roomNo1);
                        if (roomNo > 99 && roomNo <= 400) {
                            if (comboCb.isChecked()) {
                                combo = true;
                            }
                            Toast.makeText(this, "" + scanEdt.getText().toString(), Toast.LENGTH_SHORT).show();
                            db.executeTransaction(realm -> {
                                IdModel model = db.where(IdModel.class).findAll().where()
                                        .equalTo("hostelName", hostelname).findAll().where()
                                        .equalTo("roomNo", scanEdt.getText().toString()).findFirst();
                                //Log.e("data", ""+model.getBuyerId());
                                assert model != null;
                                if(model != null) {
                                    Id = model.getBuyerId();
                                    Name = model.getName();
                                    flag[0] = 1;
                                }
                            });


                            /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
=======
                    int roomNo = Integer.parseInt(scanEdt.getText().toString());
                    //Name[0] = databaseReference.child(hostelname).child(scanEdt.getText().toString()).child("Name").toString();
                    if (roomNo > 99 && roomNo <= 400) {
                        if (comboCb.isChecked()){
                            combo = true;
                        }
                        Toast.makeText(this, ""+scanEdt.getText().toString(), Toast.LENGTH_SHORT).show();
                        db.executeTransaction(realm -> {
                            IdModel model = db.where(IdModel.class).findAll().where()
                                    .equalTo("hostelName", hostelname).findAll().where()
                                    .equalTo("roomNo", scanEdt.getText().toString()).findFirst();
                            //Log.e("data", ""+model.getBuyerId());
                            Id = model.getBuyerId();
                            Name = model.getName();
                        });
=======
>>>>>>> done
=======
>>>>>>> done
=======
>>>>>>> 2eccdda4c3f0d2c902a200e5d429c8cce3d713ea
                        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
>>>>>>> done
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot child: dataSnapshot.getChildren()) {
                                        //Log.e("database", "" + child.child("AH2").child("212").child("ID").
                                        //        getValue(String.class));

                                        Id = child.child(hostelname).child(scanEdt.getText().toString()).child("ID").
                                                getValue(String.class);
                                        Name = child.child(hostelname).child(scanEdt.getText().toString()).child("Name").
                                                getValue(String.class);
                                        //Log.e("database", Id);
                                        //Log.e("database", Name);
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e("database", String.valueOf(databaseError));
                            }
                        });*/
                            if (flag[0]==1) {
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(ScanActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(ScanActivity.this);
                                }
                                //Log.e("alert", Id + Name);
                                builder.setTitle("Buyer Details")
                                        .setMessage("Id: " + Id + '\n' + "Name: " + Name)
                                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                                            Intent intent = new Intent(ScanActivity.this, SignActivity.class);
                                            intent.putExtra("roomNo", roomNo);
                                            intent.putExtra("Id", Id);
                                            startActivity(intent);
                                            finish();
                                        })
                                        .setNegativeButton(android.R.string.no, (dialog, which) ->
                                        {
                                            scanEdt.setText(null);
                                            Toast.makeText(ScanActivity.this, "Please Enter A Room Number", Toast.LENGTH_SHORT).show();
                                        })
                                        .setNegativeButton(android.R.string.no, (dialog, which) ->
                                        {
                                            Toast.makeText(ScanActivity.this, "Please enter a room no", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                            else {
                                Toast.makeText(this, "please enter correct room number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(this, "Please Enter Correct Room Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(this, "room no does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Please Enter Room Number", Toast.LENGTH_SHORT).show();
                }
            break;

            default: Toast.makeText(this,"Please Select A Mode",Toast.LENGTH_LONG).show();
            break;

        }
    }

    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }


}

