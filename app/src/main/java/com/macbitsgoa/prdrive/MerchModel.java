package com.macbitsgoa.prdrive;

import android.net.Uri;

public class MerchModel {

   private String merchName;
   private String merchDesc;
   private String merchSize1;
   private String merchSize2;
   private String merchSize3;
   private Uri merchUri;

    public MerchModel (String merchName, String merchDesc, Uri merchUri ,String merchSize1, String merchSize2,String merchSize3) {

          this.merchName = merchName;
          this.merchDesc = merchDesc;
          this.merchUri = merchUri;
          this.merchSize1=merchSize1;
          this.merchSize2=merchSize2;
          this.merchSize3=merchSize3;
    }
    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getMerchDesc() {
        return merchDesc;
    }

    public void setMerchDesc(String merchDesc) {
        this.merchDesc = merchDesc;
    }

    public Uri getMerchUri() { return merchUri; }

    public String getMerchSize1() {
        return merchSize1;
    }
    public String getMerchSize2() {
        return merchSize2;
    }
    public String getMerchSize3() {
        return merchSize3;
    }

    public void setMerchSize1(String merchSize1) {
        this.merchSize1 = merchSize1;
    }

    public void setMerchSize2(String merchSize2) {
        this.merchSize2 = merchSize2;
    }

    public void setMerchSize3(String merchSize3) {
        this.merchSize3 = merchSize3;
    }

}
