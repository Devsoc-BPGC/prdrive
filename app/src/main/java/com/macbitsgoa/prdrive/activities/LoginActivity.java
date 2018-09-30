package com.macbitsgoa.prdrive.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.prdrive.BuildConfig;
import com.macbitsgoa.prdrive.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText pass;
    EditText user;
    EditText prdrive_id;
    String seller_user;
    String seller_pass;
    String seller_prdriveId;
    Button login_btn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        pass = findViewById(R.id.pass);
        user = findViewById(R.id.user);
        prdrive_id = findViewById(R.id.prdrive_id);
        login_btn = findViewById(R.id.login_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(BuildConfig.BUILD_TYPE).child("main").child("sellers");
        databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for (DataSnapshot child: dataSnapshot.getChildren()){
                 seller_pass = child.child("password").getValue(String.class);
                 seller_user = child.child("username").getValue(String.class);
                 seller_prdriveId = child.child("prdriveId").getValue(String.class);
         }

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {
                    //NOt used as of now
         }
     });



      login_btn.setOnClickListener(view -> {
          if (seller_pass.equals(pass.getText().toString()) && seller_user.equals(user.getText().toString()) && seller_prdriveId.equals(prdrive_id.getText().toString()))
          {
              SharedPreferences sharedPreferences = getSharedPreferences("UserLog",Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPreferences.edit();

              editor.putString("Username",user.getText().toString());
              editor.putString("Password",pass.getText().toString());
              editor.putString("Prdrive_Id",prdrive_id.getText().toString());
              editor.commit();

              Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
              startActivity(intent);
              finish();
          }
          else
              Toast.makeText(LoginActivity.this,"Please Enter Correct Credentials",Toast.LENGTH_LONG).show();

      });

    }


}
