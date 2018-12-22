package com.macbitsgoa.prdrive;

import android.widget.Button;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class StaticHelperClass extends AppCompatActivity{
    public static ArrayList<MerchModel> merchModelList = new ArrayList<>();
    public static String sellerId;
    public static String hostelname;
    public static Button finishbtn;

    public static ArrayList<DonationModel> donationModelList = new ArrayList<>();
}
