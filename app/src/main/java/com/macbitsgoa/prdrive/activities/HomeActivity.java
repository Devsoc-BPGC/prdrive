package com.macbitsgoa.prdrive.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    private HomeAdapter homeAdapter;
    EditText homeEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeEdt = findViewById(R.id.homeedt);
        rv = findViewById(R.id.homerv);
        homeAdapter = new HomeAdapter();
        rv.setAdapter(homeAdapter);
        rv.setLayoutManager(new GridLayoutManager(this, span()));
        rv.setHasFixedSize(true);
        sellerId = homeEdt.getText().toString();
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
