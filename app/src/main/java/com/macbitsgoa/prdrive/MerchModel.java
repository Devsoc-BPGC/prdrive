package com.macbitsgoa.prdrive;

import android.widget.Spinner;

import com.facebook.drawee.view.SimpleDraweeView;

public class MerchModel {

    String merchName,merchDesc;
    SimpleDraweeView merchImage;
    Spinner merchSize,merchQty;

    public MerchModel (String merchName, String merchDesc, Spinner merchSize, Spinner merchQty, SimpleDraweeView merchImage ) {

          this.merchName = merchName;
          this.merchDesc = merchDesc;
          this.merchSize = merchSize;
          this.merchQty = merchQty;
          this.merchImage = merchImage;
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

    public SimpleDraweeView getMerchImage() {
        return merchImage;
    }

    public void setMerchImage(SimpleDraweeView merchImage) {
        this.merchImage = merchImage;
    }

    public Spinner getMerchSize() {
        return merchSize;
    }

    public void setMerchSize(Spinner merchSize) {
        this.merchSize = merchSize;
    }

    public Spinner getMerchQty() {
        return merchQty;
    }

    public void setMerchQty(Spinner merchQty) {
        this.merchQty = merchQty;
    }
}
