package com.macbitsgoa.prdrive.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Picture;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
import android.widget.Toast;

//import com.github.gcacace.signaturepad.utils.SvgBuilder;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.BuyerModel;
import com.macbitsgoa.prdrive.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import io.realm.Realm;

import static com.macbitsgoa.prdrive.StaticHelperClass.hostelname;
import static com.macbitsgoa.prdrive.StaticHelperClass.sellerId;
import static com.macbitsgoa.prdrive.activities.ScanActivity.combo;

public class SignActivity extends Activity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("prdrive-orders").child("prdrive1-001");
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_sign);

        SignaturePad mSignaturePad;
        Button mClearButton;
        Button mSaveButton;
        Button mCancelButton;

        mClearButton = findViewById(R.id.clear);
        mSaveButton = findViewById(R.id.save);
        mCancelButton = findViewById(R.id.cancel);

        mSignaturePad = findViewById(R.id.signature_pad);
        mSaveButton.setEnabled(false);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                mSaveButton.setEnabled(true);// Toast.makeText(SignActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mCancelButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignActivity.this, ScanActivity.class);
            startActivity(intent);
            finish();
        });

        mClearButton.setOnClickListener(view -> mSignaturePad.clear());
        Realm.init(SignActivity.this);

        ArrayList<BuyerModel> buyerList = new ArrayList<>(0);
        Realm db = Realm.getDefaultInstance();
        mSaveButton.setOnClickListener(view -> {
            Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
            String sign = getEncoded64ImageStringFromBitmap(signatureBitmap);
            //Log.e("database", "outside big if");
            if (addJpgSignatureToGallery(signatureBitmap)) {
                Toast.makeText(SignActivity.this, ""+sellerId, Toast.LENGTH_SHORT).show();

                buyerList.add(new BuyerModel(getIntent().getIntExtra("roomNo", 0), hostelname, sign,sellerId,
                        getIntent().getStringExtra("Id"), ""+combo));
                db.executeTransaction(
                        realm -> db.insertOrUpdate(buyerList.get(buyerList.size() - 1))
                );
                //Log.e("database", "outside connct");

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                //Log.e("database", "outside above if");
                if (connected) {
                    //Log.e("database", "inside if");
                    for (int i = (buyerList.size() - 1); i >= 0; i = (i - 1)) {
                        String key = databaseReference.push().getKey();
                        assert key != null;
                        databaseReference.child(key).child("buyerid").setValue(buyerList.get(i).buyerId);
                        databaseReference.child(key).child("hostel").setValue(buyerList.get(i).hostelName);
                        databaseReference.child(key).child("room").setValue(buyerList.get(i).roomNo);
                        databaseReference.child(key).child("sellerid").setValue(buyerList.get(i).sellerId);
                        databaseReference.child(key).child("timestamp").setValue(buyerList.get(i).timeStamp);
                        databaseReference.child(key).child("signatureString").setValue(buyerList.get(i).sign);
                        if ((buyerList.get(i).combo.equals("true") && !buyerList.get(i).merchIdsize1.equals("none") && !buyerList.get(i).merchIdsize2.equals("none") &&
                                !buyerList.get(i).merchIdsize3.equals("none")) ||
                                (buyerList.get(i).combo.equals("true") && !buyerList.get(i).merchId1size1.equals("none") && !buyerList.get(i).merchId1size2.equals("none") &&
                                        !buyerList.get(i).merchId1size3.equals("none")) ||
                                (buyerList.get(i).combo.equals("true") && !buyerList.get(i).merchId2size1.equals("none") && !buyerList.get(i).merchId2size2.equals("none") &&
                                        !buyerList.get(i).merchId2size3.equals("none"))){
                            databaseReference.child(key).child("combo").setValue("true");

                        }
                        else
                            databaseReference.child(key).child("combo").setValue("false");
                        //Log.e("data", ""+buyerList.get(i).merchIdsize1);
                        if (!buyerList.get(i).merchIdsize1.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId001").child("quantity").setValue(buyerList.get(i).merchIdquantity1);
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId001").child("size").setValue(buyerList.get(i).merchIdsize1);
                        }
                        //Log.e("data", ""+buyerList.get(i).merchIdsize2);
                        if (!buyerList.get(i).merchIdsize2.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId002").child("quantity").setValue(buyerList.get(i).merchIdquantity2);
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId002").child("size").setValue(buyerList.get(i).merchIdsize2);
                        }
                        //Log.e("data", ""+buyerList.get(i).merchIdsize3);
                        if (!buyerList.get(i).merchIdsize3.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId003").child("quantity").setValue(buyerList.get(i).merchIdquantity3);
                            databaseReference.child(key).child("ordersPlaced").child("merchId0")
                                    .child("merchId0OrderId003").child("size").setValue(buyerList.get(i).merchIdsize3);
                        }
                        if (!buyerList.get(i).merchId1size1.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId001").child("quantity").setValue(buyerList.get(i).merchId1quantity1);
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId001").child("size").setValue(buyerList.get(i).merchId1size1);
                        }
                        if (!buyerList.get(i).merchId1size2.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId002").child("quantity").setValue(buyerList.get(i).merchId1quantity2);
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId002").child("size").setValue(buyerList.get(i).merchId1size2);
                        }
                        if (!buyerList.get(i).merchId1size3.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId003").child("quantity").setValue(buyerList.get(i).merchId1quantity3);
                            databaseReference.child(key).child("ordersPlaced").child("merchId1")
                                    .child("merchId1OrderId003").child("size").setValue(buyerList.get(i).merchId1size3);
                        }
                        if (!buyerList.get(i).merchId2size1.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId001").child("quantity").setValue(buyerList.get(i).merchId2quantity1);
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId001").child("size").setValue(buyerList.get(i).merchId2size1);
                        }
                        if (!buyerList.get(i).merchId2size2.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId002").child("quantity").setValue(buyerList.get(i).merchId2quantity2);
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId002").child("size").setValue(buyerList.get(i).merchId2size2);
                        }
                        if (!buyerList.get(i).merchId2size3.equals("none")) {
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId003").child("quantity").setValue(buyerList.get(i).merchId2quantity3);
                            databaseReference.child(key).child("ordersPlaced").child("merchId2")
                                    .child("merchId2OrderId003").child("size").setValue(buyerList.get(i).merchId2size3);
                        }
                        buyerList.get(i).isUploaded = 1;
                        BuyerModel model = buyerList.get(i);
                        db.executeTransaction(realm -> db.insertOrUpdate(model));
                        buyerList.remove(i);
                  //      Log.e("database", "inside");
                    }
                }
                combo = false;
                //Log.e("database", String.valueOf(getIntent()));
                Intent intent = new Intent(SignActivity.this, MerchActivity.class);
                startActivity(intent);
                finish();

            /*} else {
                Toast.makeText(SignActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
            }*/
                //   if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                //      Toast.makeText(SignActivity.this, "SVG Signature saved", Toast.LENGTH_SHORT).show();
                // }// else {
                //  Toast.makeText(SignActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                //  }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SignActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                 //Toast.makeText(SignActivity.this,"Write to External Storage successful", Toast.LENGTH_SHORT).show();
            break;
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        /*if (!file.mkdirs()) {
            Log.e("Pictures", "Directory not created");
        }*/
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir(""), String.format(" ", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        SignActivity.this.sendBroadcast(mediaScanIntent);
    }



    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        Log.e("IMAGE STRING", imgString); //!!!DON'T COPY THIS DIRECTLY FROM LOGCAT FOR TESTING. MAKE AN EDIT-TEXT AND SETTEXT imgString.
        return imgString;

    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

 /*

    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}*/
 }