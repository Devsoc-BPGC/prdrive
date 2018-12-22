package com.macbitsgoa.prdrive;

public class DonationModel {

    private String title;
    private String desc;


    public DonationModel(String title, String desc)
    {
        this.title = title;
        this.desc = desc;
    }


//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
