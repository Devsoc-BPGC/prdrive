package com.macbitsgoa.prdrive.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.adapters.DonationAdapter;
import com.macbitsgoa.prdrive.adapters.MerchAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

import static com.macbitsgoa.prdrive.StaticHelperClass.finishbtn;
public class MerchActivity extends AppCompatActivity {

    // TODO: add necessary arguments to be passed via intent
    public static void launchMerch(Context context) {
        Intent intent = new Intent(context, MerchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.widget.Toolbar toolbar;
        RecyclerView Tees_rv;                            //recyclerview for tees
        RecyclerView Donation_rv;
        setContentView(R.layout.activity_merch);
                                                  //Setting up the Finish order button.
        finishbtn = findViewById(R.id.finish);
        finishbtn.setVisibility(View.INVISIBLE);

        final int[] flag = {0};
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Merch Activity");
        setSupportActionBar(toolbar);

        Tees_rv=findViewById(R.id.merchrv);
        Tees_rv.setLayoutManager(new LinearLayoutManager(MerchActivity.this));
        Tees_rv.setHasFixedSize(false);
        Tees_rv.setNestedScrollingEnabled(false);
        MerchAdapter merchAdapter = new MerchAdapter(MerchActivity.this);
        Tees_rv.setAdapter(merchAdapter);

        //Donation rv
        Donation_rv=findViewById(R.id.merchrv2);
        Donation_rv.setLayoutManager(new LinearLayoutManager(MerchActivity.this));
        Donation_rv.setHasFixedSize(true);
        Donation_rv.setNestedScrollingEnabled(false);

        DonationAdapter donationAdapter = new DonationAdapter(MerchActivity.this);
        Donation_rv.setAdapter(donationAdapter);



        finishbtn.setOnClickListener(view -> {
        for (int i = 0; i < merchModelList.size(); i++) {


            if((merchModelList.get(i).getMerchSize1().equals("none"))||(merchModelList.get(i).getMerchSize2().equals("none"))||(merchModelList.get(i).getMerchSize3().equals("none")))
            {
                flag[0] = 1;
                break;
            }
            //Log to ensure that the merchModelList going to the next activity is populated.
        }

        if(flag[0] == 0 )
            finishbtn.setVisibility(View.VISIBLE);


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
