package com.macbitsgoa.prdrive.activities;

import android.app.Activity;
import android.os.Bundle;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.ResidentAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ResidentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        RecyclerView rv;

        rv = findViewById(R.id.list_rv);
        rv.setLayoutManager(new LinearLayoutManager(ResidentActivity.this));
        rv.setHasFixedSize(false);
        ResidentAdapter residentAdapter = new ResidentAdapter(ResidentActivity.this);
        rv.setAdapter(residentAdapter);


    }
}