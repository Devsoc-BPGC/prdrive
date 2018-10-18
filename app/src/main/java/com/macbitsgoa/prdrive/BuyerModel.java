package com.macbitsgoa.prdrive;

import android.net.Uri;

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
    public String sellerId = StaticHelperClass.sellerId;
    public String timeStamp;

    public BuyerModel () {
    }

    public BuyerModel (int roomNo, String hostelName, String sign, String sellerId, String buyerId, String combo) {
        this.buyerId = buyerId;
        this.roomNo = roomNo;
        this.combo = combo;
        this.sellerId = sellerId;
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
                    if(merchModelList.get(i).getMerchId().equals("merchId2")) {
                        merchId2quantity1 = "1";
                        merchId2size1 = merchModelList.get(i).getMerchSize1();
                        merchId2quantity2 = "1";
                        merchId2size2 = merchModelList.get(i).getMerchSize2();
                        merchId2quantity3 = "1";
                        merchId2size3 = merchModelList.get(i).getMerchSize3();
                    }
                }
            }
        }
        Long tsLong = System.currentTimeMillis()/1000;
        this.timeStamp = tsLong.toString();
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getBuyerId() {
        return buyerId;
    }
    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
    public void setIsUploaded(int isUploaded) {
        this.isUploaded = isUploaded;
    }
    public String getHostelName() {
        return hostelName;
    }
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    public String getSign() { return sign; }
    public String getSellerId() {
        return sellerId;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
    public String getCombo() {
        return combo;
    }
    public void setCombo(String combo) {
        this.combo = combo;
    }
    public String getMerchIdquantity1() {
        return merchIdquantity1;
    }
    public void setMerchIdquantity1(String merchIdquantity1) {
        this.merchIdquantity1 = merchIdquantity1;
    }
    public String getMerchIdquantity2() {
        return merchIdquantity2;
    }
    public void setMerchIdquantity2(String merchIdquantity2) {
        this.merchIdquantity2 = merchIdquantity2;
    }
    public String getMerchIdquantity3() {
        return merchIdquantity3;
    }
    public void setMerchIdquantity3(String merchIdquantity3) {
        this.merchIdquantity3 = merchIdquantity3;
    }
    public String getMerchId1quantity1() {
        return merchId1quantity1;
    }
    public void setMerchId1quantity1(String merchId1quantity1) {
        this.merchId1quantity1 = merchId1quantity1;
    }
    public String getMerchId1quantity2() {
        return merchId1quantity2;
    }
    public void setMerchId1quantity2(String merchId1quantity2) {
        this.merchId1quantity2 = merchId1quantity2;
    }
    public String getMerchId1quantity3() {
        return merchId1quantity3;
    }
    public void setMerchId1quantity3(String merchId1quantity3) {
        this.merchId1quantity3 = merchId1quantity3;
    }
    public String getMerchId2quantity1() {
        return merchId2quantity1;
    }
    public void setMerchId2quantity1(String merchId2quantity1) {
        this.merchId2quantity1 = merchId2quantity1;
    }
    public String getMerchId2quantity2() {
        return merchId2quantity2;
    }
    public void setMerchId2quantity2(String merchId2quantity2) {
        this.merchId2quantity2 = merchId2quantity2;
    }
    public String getMerchId2quantity3() {
        return merchId2quantity3;
    }
    public void setMerchId2quantity3(String merchId2quantity3) {
        this.merchId2quantity3 = merchId2quantity3;
    }
    public String getMerchIdsize1() {
        return merchIdsize1;
    }
    public void setMerchIdsize1(String merchIdsize1) {
        this.merchIdsize1 = merchIdsize1;
    }
    public String getMerchIdsize2() {
        return merchIdsize2;
    }
    public void setMerchIdsize2(String merchIdsize2) {
        this.merchIdsize2 = merchIdsize2;
    }
    public String getMerchIdsize3() {
        return merchIdsize3;
    }
    public void setMerchIdsize3(String merchIdsize3) {
        this.merchIdsize3 = merchIdsize3;
    }
    public String getMerchId1size1() {
        return merchId1size1;
    }
    public void setMerchId1size1(String merchId1size1) {
        this.merchId1size1 = merchId1size1;
    }
    public String getMerchId1size2() {
        return merchId1size2;
    }
    public void setMerchId1size2(String merchId1size2) {
        this.merchId1size2 = merchId1size2;
    }
    public String getMerchId1size3() {
        return merchId1size3;
    }
    public void setMerchId1size3(String merchId1size3) {
        this.merchId1size3 = merchId1size3;
    }
    public String getMerchId2size1() {
        return merchId2size1;
    }
    public void setMerchId2size1(String merchId2size1) {
        this.merchId2size1 = merchId2size1;
    }
    public String getMerchId2size2() {
        return merchId2size2;
    }
    public void setMerchId2size2(String merchId2size2) {
        this.merchId2size2 = merchId2size2;
    }
    public String getMerchId2size3() {
        return merchId2size3;
    }
    public void setMerchId2size3(String merchId2size3) {
        this.merchId2size3 = merchId2size3;
    }

}
