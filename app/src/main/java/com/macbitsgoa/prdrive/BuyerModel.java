package com.macbitsgoa.prdrive;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import static com.macbitsgoa.prdrive.StaticHelperClass.merchModelList;

public class BuyerModel extends RealmObject {

    @PrimaryKey
    public int roomNo;
    public String buyerId;
    public String hostelName;
    public String sign;
    public String merchIdquantity1="0";
    public String merchIdsize1="none";
    public String merchIdquantity2="0";
    public String merchIdsize2="none";
    public String merchIdquantity3="0";
    public String merchIdsize3="none";
    public String merchId1quantity1="0";
    public String merchId1size1="none";
    public String merchId1quantity2="0";
    public String merchId1size2="none";
    public String merchId1quantity3="0";
    public String merchId1size3="none";
    public String sellerId;
    public String timeStamp;

    public BuyerModel () {
    }

    public BuyerModel (int roomNo, String hostelName, String sign, String sellerId, String buyerId) {
        int j=0, k=0;
        this.buyerId = buyerId;
        this.roomNo = roomNo;
        this.hostelName = hostelName;
        this.sign = sign;
        this.sellerId = sellerId;
        for(int i=0; i<merchModelList.size(); i++){
            if(merchModelList.get(i).getMerchId()=="merchId0"){
                if(j==0) {
                    merchIdquantity1 = "1";
                    merchIdsize1 = merchModelList.get(i).getMerchSize();
                }
                else{
                    if(j==1) {
                        merchIdquantity2 = "1";
                        merchIdsize2 = merchModelList.get(i).getMerchSize();
                    }
                    else{
                        merchIdquantity3 = "1";
                        merchIdsize3 = merchModelList.get(i).getMerchSize();
                    }
                }
                j++;
            }
            else{
                if(k==0) {
                    merchId1quantity1 = "1";
                    merchId1size1 = merchModelList.get(i).getMerchSize();
                }
                else{
                    if(k==1) {
                        merchId1quantity2 = "1";
                        merchId1size2 = merchModelList.get(i).getMerchSize();
                    }
                    else{
                        merchId1quantity3 = "1";
                        merchId1size3 = merchModelList.get(i).getMerchSize();
                    }
                }
                k++;
            }
        }
        Long tsLong = System.currentTimeMillis()/1000;
        this.timeStamp = tsLong.toString();
    }
}
