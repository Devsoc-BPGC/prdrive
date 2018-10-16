package com.macbitsgoa.prdrive;

import android.app.Application;

<<<<<<< e2231a7ffc7380f429623d7fd18b767555be85a4
import com.facebook.drawee.backends.pipeline.Fresco;
=======
>>>>>>> realm and firebase done
import com.google.firebase.database.FirebaseDatabase;

public class PrDrive extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
<<<<<<< e2231a7ffc7380f429623d7fd18b767555be85a4
        Fresco.initialize(this);
=======
>>>>>>> realm and firebase done
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
