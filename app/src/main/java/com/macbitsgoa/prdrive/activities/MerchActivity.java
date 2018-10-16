package com.macbitsgoa.prdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.MerchAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class MerchActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.widget.Toolbar toolbar = null;
        RecyclerView rv;
        Button finishbtn;
        setContentView(R.layout.activity_merch);
Log.e("MERCH ACTIVITY","HERE I AM ");
        int flag=0;                                          //Setting up the Finish order button.
        finishbtn = findViewById(R.id.finish);
        finishbtn.setVisibility(View.INVISIBLE);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Merch Activity");
        setSupportActionBar(toolbar);

        rv=findViewById(R.id.merchrv);
        rv.setLayoutManager(new LinearLayoutManager(MerchActivity.this));
        rv.setHasFixedSize(false);
        MerchAdapter merchAdapter = new MerchAdapter(MerchActivity.this);
        rv.setAdapter(merchAdapter);


        for (int i = 0; i < merchModelList.size(); i++) {

            if((merchModelList.get(i).getMerchSize1()=="None")||(merchModelList.get(i).getMerchSize2()=="None")||(merchModelList.get(i).getMerchSize3()=="None"))
            {
                flag = 1;
                break;
            }
            //Log to ensure that the merchModelList going to the next activity is populated.
        }

        if(flag == 0 )
            finishbtn.setVisibility(View.VISIBLE);


        finishbtn.setOnClickListener(view -> {
            for (int i = 0; i < merchModelList.size(); i++) {
                Log.e("DATA IN THE LIST",merchModelList.get(i).getMerchName()+" "+merchModelList.get(i).getMerchDesc()+" "+merchModelList.get(i).getMerchSize1()+" "+merchModelList.get(i).getMerchSize2()+" "+merchModelList.get(i).getMerchSize3());
                //Log to ensure that the merchModelList going to the next activity is populated.
            }
            Intent intent1 = new Intent(MerchActivity.this, CheckoutActivity.class);
            startActivity(intent1);
            finish();
            Log.e("FINISH PRESSED","FINISH");

        });
    }

}




