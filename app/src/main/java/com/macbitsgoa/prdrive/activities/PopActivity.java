package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.macbitsgoa.prdrive.R;

import androidx.appcompat.app.AppCompatActivity;

public class PopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        TextView tv;
        Button btn;
        EditText edtId;

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        edtId = findViewById(R.id.edtId);

        DisplayMetrics  dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

        btn.setOnClickListener(view -> {
            if(!edtId.getText().toString().isEmpty())
            {
                Intent intent = new Intent(PopActivity.this,SignActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(PopActivity.this,"Please Enter Valid BITS ID",Toast.LENGTH_LONG).show();

        });


    }
}
