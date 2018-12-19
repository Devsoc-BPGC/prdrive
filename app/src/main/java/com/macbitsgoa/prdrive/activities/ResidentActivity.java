package com.macbitsgoa.prdrive.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.ResidentAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ResidentActivity extends Activity {

    public static final String INTENT_KEY_HOSTEL_NAME = ResidentActivity.class.getName() + ".intent.hostelName";

    /**
     * Use this method to launch this activity correctly.
     *
     * @param parent     from which this is to be launched.
     * @param hostelName name of the target hostel.
     */
    public static void launchHostel(Context parent, String hostelName) {
        Intent intent = new Intent(parent, ResidentActivity.class);
        intent.putExtra(INTENT_KEY_HOSTEL_NAME, hostelName);
        parent.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        Intent source = getIntent();
        final String hostelName = source.getStringExtra(INTENT_KEY_HOSTEL_NAME);
        RecyclerView rv;
        rv = findViewById(R.id.list_rv);
        rv.setLayoutManager(new LinearLayoutManager(ResidentActivity.this));
        ResidentAdapter residentAdapter = new ResidentAdapter(hostelName);
        rv.setAdapter(residentAdapter);
    }
}
