package com.macbitsgoa.prdrive;

import android.net.Uri;

public class MerchModel {

   private String merchName;
   private String merchId;
   private String merchDesc;
   private String merchSize;
   private String merchQty;
   private Uri merchUri;

    public MerchModel (String merchName, String merchDesc, Uri merchUri ,String merchSize, String merchQty, String merchId) {
          this.merchName = merchName;
          this.merchDesc = merchDesc;
          this.merchUri = merchUri;
          this.merchSize = merchSize;
          this.merchQty = merchQty;
          this.merchId = merchId;
    }

    public String getMerchName() {
        return merchName;
    }

    public String getMerchId() {
        return merchId;
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

    public String getMerchSize() {
        return merchSize;
    }

    public void setMerchSize(String merchSize) {
        this.merchSize = merchSize;
    }

    public String getMerchQty() {
        return merchQty;
    }

    public void setMerchQty(String merchQty) {
        this.merchQty = merchQty;
    }

}
