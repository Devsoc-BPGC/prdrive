package com.macbitsgoa.prdrive;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class PrDrive extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
