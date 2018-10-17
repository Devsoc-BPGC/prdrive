package com.macbitsgoa.prdrive;

import android.app.Application;

<<<<<<< HEAD
import com.facebook.drawee.backends.pipeline.Fresco;
=======
>>>>>>> 0b491d10ca8d35973adbd612cb4a99ae4de768ad
import com.google.firebase.database.FirebaseDatabase;

public class PrDrive extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
<<<<<<< HEAD
        Fresco.initialize(this);
=======
>>>>>>> 0b491d10ca8d35973adbd612cb4a99ae4de768ad
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
