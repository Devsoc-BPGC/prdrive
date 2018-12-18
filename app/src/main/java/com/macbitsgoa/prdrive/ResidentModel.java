package com.macbitsgoa.prdrive;

public class ResidentModel {

    String name;
    String roomNo;

    public ResidentModel(String name, String roomNo)
    {
        this.name= name;
        this.roomNo=roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
