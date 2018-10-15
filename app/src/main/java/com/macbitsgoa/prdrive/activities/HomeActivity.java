package com.macbitsgoa.prdrive.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.util.Log;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;

public class HomeActivity extends AppCompatActivity {

    static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String password;
        String prdrive_id;

        RecyclerView rv;
        rv = findViewById(R.id.homerv);
        HomeAdapter homeAdapter = new HomeAdapter();
        rv.setAdapter(homeAdapter);
        rv.setLayoutManager(new GridLayoutManager(this, span()));
        rv.setHasFixedSize(true);

        SharedPreferences sharedPreferences = getSharedPreferences("UserLog",Context.MODE_PRIVATE);
        username = sharedPreferences.getString("Username","N/A");
        password = sharedPreferences.getString("Password","N/A");
        prdrive_id = sharedPreferences.getString("Prdrive_Id","N/A");

        Log.e("FROM FILE","USERNAME "+username+"PASS "+password+"PRDRIVE "+prdrive_id);

        if(("N/A").equals(username) || ("N/A").equals(password) || ("N/A").equals(prdrive_id) )
        {
            sellerId = username;
            Intent i = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

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
