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
    public String combo;
    public int isUploaded = 0;
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
    public String merchId2quantity1="0";
    public String merchId2size1="none";
    public String merchId2quantity2="0";
    public String merchId2size2="none";
    public String merchId2quantity3="0";
    public String merchId2size3="none";
    public String sellerId;
    public String timeStamp;

    public BuyerModel () {
    }
    public BuyerModel (int roomNo, String hostelName, String sign, String sellerId, String buyerId) {
        this.buyerId = buyerId;
        this.roomNo = roomNo;
        this.combo = combo;
        this.hostelName = hostelName;
        this.sign = sign;
        for(int i=0; i<merchModelList.size(); i++){
            if(merchModelList.get(i).getMerchId().equals("merchId0")){
                merchIdquantity1 = "1";
                merchIdsize1 = merchModelList.get(i).getMerchSize1();
                merchIdquantity2 = "1";
                merchIdsize2 = merchModelList.get(i).getMerchSize2();
                merchIdquantity3 = "1";
                merchIdsize3 = merchModelList.get(i).getMerchSize3();
            }
            else{
                if (merchModelList.get(i).getMerchId().equals("merchId1")) {
                    merchId1quantity1 = "1";
                    merchId1size1 = merchModelList.get(i).getMerchSize1();
                    merchId1quantity2 = "1";
                    merchId1size2 = merchModelList.get(i).getMerchSize2();
                    merchId1quantity3 = "1";
                    merchId1size3 = merchModelList.get(i).getMerchSize3();
                }
                else{
                    merchId2quantity1 = "1";
                    merchId2size1 = merchModelList.get(i).getMerchSize1();
                    merchId2quantity2 = "1";
                    merchId2size2 = merchModelList.get(i).getMerchSize2();
                    merchId2quantity3 = "1";
                    merchId2size3 = merchModelList.get(i).getMerchSize3();
                }
            }
        }
        Long tsLong = System.currentTimeMillis()/1000;
        this.timeStamp = tsLong.toString();
    }
}
