package com.macbitsgoa.prdrive;

import android.net.Uri;

import io.realm.RealmObject;

public class IdModel extends RealmObject {

    public String roomNo;
    public String buyerId;
    public String hostelName;
    public String name;

    public IdModel () {
    }

    public IdModel (String roomNo, String hostelName, String buyerId, String name) {
        this.roomNo = roomNo;
        this.hostelName = hostelName;
        this.buyerId = buyerId;
        this.name = name;
    }

    public String getBuyerId() { return buyerId; }

    public String getRoomNo() {
        return roomNo;
    }
    public String getName() {
        return name;
    }
    public String getHostelName() {
        return hostelName;
    }
}
