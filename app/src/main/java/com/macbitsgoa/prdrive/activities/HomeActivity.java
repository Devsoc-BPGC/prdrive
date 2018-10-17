package com.macbitsgoa.prdrive.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.BuyerModel;
import com.macbitsgoa.prdrive.IdModel;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.HomeAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;

public class HomeActivity extends AppCompatActivity {

    static String username;
    ProgressBar progressBar;
    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("prdrive-orders").child("prdrive1-001");
    private DatabaseReference databaseReference1 = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("hostel").child("Students");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String password;
        String prdrive_id;
        Button logout_btn;
        Realm.init(HomeActivity.this);
        Realm db = Realm.getDefaultInstance();
        RecyclerView rv;
        progressBar = findViewById(R.id.item_format_archive_progressbar);
        rv = findViewById(R.id.homerv);
        logout_btn = findViewById(R.id.logout);

        HomeAdapter homeAdapter = new HomeAdapter();
        ArrayList<IdModel> idList = new ArrayList<>(0);
        ArrayList<BuyerModel> notUploadList = new ArrayList<>(0);

        SharedPreferences sharedPreferences = getSharedPreferences("UserLog",Context.MODE_PRIVATE);
        username = sharedPreferences.getString("Username","N/A");
        password = sharedPreferences.getString("Password","N/A");
        prdrive_id = sharedPreferences.getString("Prdrive_Id","N/A");

        Log.e("FROM FILE","USERNAME "+username+"PASS "+password+"PRDRIVE "+prdrive_id);

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        Log.e("database", "outside above if");
        final int[] size = new int[2];
        if(connected){
            Log.e("database", "inside if");
            db.executeTransaction(realm -> {
                size[0] = db.where(BuyerModel.class).findAll().where().equalTo("isUploaded", 0).findAll().size();
                size[1] = db.where(IdModel.class).findAll().size();
                notUploadList.addAll(db.where(BuyerModel.class).findAll().where().equalTo("isUploaded", 0).
                        findAll());

                Toast.makeText(this, ""+size[1], Toast.LENGTH_SHORT).show();
                //Log.e("database", "inside if1" + model);
                //Log.e("database", "inside if1" + size[1]);
                //Log.e("database", "inside if1" + size[0]);
            });
            if (size[0]!=0) {
                for (int i = (size[0] - 1); i >= 0; i = (i - 1)) {
                    String key = databaseReference.push().getKey();
                    assert key != null;
                    databaseReference.child(key).child("buyerid").setValue(notUploadList.get(i).buyerId);
                    databaseReference.child(key).child("hostel").setValue(notUploadList.get(i).hostelName);
                    databaseReference.child(key).child("room").setValue(notUploadList.get(i).roomNo);
                    databaseReference.child(key).child("sellerid").setValue(notUploadList.get(i).sellerId);
                    databaseReference.child(key).child("timestamp").setValue(notUploadList.get(i).timeStamp);
                    databaseReference.child(key).child("signatureString").setValue(notUploadList.get(i).sign);
                    if ((notUploadList.get(i).combo.equals("true") && !notUploadList.get(i).merchIdsize1.equals("none") && !notUploadList.get(i).merchIdsize1.equals("none") &&
                            !notUploadList.get(i).merchIdsize1.equals("none")) ||
                            (notUploadList.get(i).combo.equals("true") && !notUploadList.get(i).merchId1size1.equals("none") && !notUploadList.get(i).merchId1size1.equals("none") &&
                                    !notUploadList.get(i).merchId1size1.equals("none")) ||
                            (notUploadList.get(i).combo.equals("true") && !notUploadList.get(i).merchId2size1.equals("none") && !notUploadList.get(i).merchId2size1.equals("none") &&
                                    !notUploadList.get(i).merchId2size1.equals("none"))){
                        databaseReference.child(key).child("combo").setValue("true");
                    }
                    if (!notUploadList.get(i).merchIdsize1.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId001").child("quantity").setValue(notUploadList.get(i).merchIdquantity1);
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId001").child("size").setValue(notUploadList.get(i).merchIdsize1);
                    }
                    if (!notUploadList.get(i).merchIdsize2.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId002").child("quantity").setValue(notUploadList.get(i).merchIdquantity2);
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId002").child("size").setValue(notUploadList.get(i).merchIdsize2);
                    }
                    if (!notUploadList.get(i).merchIdsize3.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId003").child("quantity").setValue(notUploadList.get(i).merchIdquantity3);
                        databaseReference.child(key).child("ordersPlaced").child("merchId")
                                .child("merchIdOrderId003").child("size").setValue(notUploadList.get(i).merchIdsize3);
                    }
                    if (!notUploadList.get(i).merchId1size1.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId001").child("quantity").setValue(notUploadList.get(i).merchId1quantity1);
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId001").child("size").setValue(notUploadList.get(i).merchId1size1);
                    }
                    if (!notUploadList.get(i).merchId1size2.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId002").child("quantity").setValue(notUploadList.get(i).merchId1quantity2);
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId002").child("size").setValue(notUploadList.get(i).merchId1size2);
                    }
                    if (!notUploadList.get(i).merchId1size3.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId003").child("quantity").setValue(notUploadList.get(i).merchId1quantity3);
                        databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                .child("merchId2OrderId003").child("size").setValue(notUploadList.get(i).merchId1size3);
                    }
                    if (!notUploadList.get(i).merchId2size1.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId001").child("quantity").setValue(notUploadList.get(i).merchId2quantity1);
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId001").child("size").setValue(notUploadList.get(i).merchId2size1);
                    }
                    if (!notUploadList.get(i).merchId2size2.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId002").child("quantity").setValue(notUploadList.get(i).merchId2quantity2);
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId002").child("size").setValue(notUploadList.get(i).merchId2size2);
                    }
                    if (!notUploadList.get(i).merchId2size3.equals("none")) {
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId003").child("quantity").setValue(notUploadList.get(i).merchId2quantity3);
                        databaseReference.child(key).child("ordersPlaced").child("merchId3")
                                .child("merchId3OrderId003").child("size").setValue(notUploadList.get(i).merchId2size3);
                    }
                    notUploadList.get(i).isUploaded = 1;
                    BuyerModel model = notUploadList.get(i);
                    db.executeTransaction(realm -> db.insertOrUpdate(model));
                    notUploadList.remove(i);
                    Log.e("database", "inside");
                }
            }

            if (size[1]==0) {
                //Log.e("database", "inside if2");
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(HomeActivity.this, "Data downloading please wait", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.VISIBLE);
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            for (DataSnapshot children : child.getChildren()) {
                                idList.add(new IdModel(children.getKey(), child.getKey(), children.child("ID").getValue().toString(), children.child("Name").getValue()
                                        .toString()));
                                db.executeTransaction(realm -> {
                                    db.insertOrUpdate(idList.get(idList.size() - 1));
                                });
                            }
                        }
                        db.executeTransaction(realm -> {
                            int size1 = db.where(IdModel.class).findAll().size();
                            Toast.makeText(HomeActivity.this, ""+size1, Toast.LENGTH_SHORT).show();
                        });
                        progressBar.setProgress(100);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(HomeActivity.this, "Download not completed reopen app with better internet connection",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        rv.setAdapter(homeAdapter);
        rv.setLayoutManager(new GridLayoutManager(this, span()));
        rv.setHasFixedSize(true);

        if(("N/A").equals(username) || ("N/A").equals(password) || ("N/A").equals(prdrive_id) )
        {
            sellerId = username;
            Intent i = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        logout_btn.setOnClickListener(view -> {

            SharedPreferences sharedPreferences1 = this.getSharedPreferences("UserLog",Context.MODE_PRIVATE);
            sharedPreferences1.edit().clear().commit();

            Intent i = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(i);
            finish();

        });
    }

    private int span() {
        //Setup columns according to device screen
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        // Setting up grid
        final int num = 180;
        final float t = dpWidth / num;
        final float r = dpWidth % num;
        final int cols;
        cols = r < 0.1 * num
                ? (int) Math.ceil(dpWidth / num)
                : (int) t;

        return cols;
    }
}
