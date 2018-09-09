package com.macbitsgoa.prdrive.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Picture;
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
//import java.text.SimpleDateFormat;
//import java.util.Date;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class SignActivity extends Activity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
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
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                // Toast.makeText(SignActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
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

        mSaveButton.setOnClickListener(view -> {
            Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
            getEncoded64ImageStringFromBitmap(signatureBitmap);
            if (addJpgSignatureToGallery(signatureBitmap)) {
                Toast.makeText(SignActivity.this, "Signature saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
            }
            //   if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
            //      Toast.makeText(SignActivity.this, "SVG Signature saved", Toast.LENGTH_SHORT).show();
            // }// else {
            //  Toast.makeText(SignActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
            //  }
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
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("Pictures", "Directory not created");
        }
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
            File photo = new File(getAlbumStorageDir("Pictures"), String.format("PRDRIVE_SIGN_%d.jpg", System.currentTimeMillis()));
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

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("Pictures"), String.format("PR_DRIVE_%d.svg", System.currentTimeMillis()));
//          ByteArrayOutputStream baos = new ByteArrayOutputStream();
//          Bitmap bitmap = Bitmap.createBitmap(svgFile);
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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