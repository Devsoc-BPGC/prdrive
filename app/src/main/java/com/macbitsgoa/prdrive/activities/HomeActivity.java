package com.macbitsgoa.prdrive.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.HomeAdapter;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    private HomeAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String hostelNames[] = {"AH1", "AH2", "AH3", "AH4", "AH5", "AH6", "AH7",
                "AH8", "AH9", "CH1", "CH2", "CH3", "CH4", "CH5", "CH6", "CH7", "DH1", "DH2"};
        rv = findViewById(R.id.homerv);
        homeAdapter = new HomeAdapter(hostelNames);
        rv.setAdapter(homeAdapter);
        rv.setLayoutManager(new GridLayoutManager(this, span()));
        rv.setHasFixedSize(true);
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
