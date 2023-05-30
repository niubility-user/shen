package com.jingdong.common.entity.settlement;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CoordinateToAddress implements Serializable {
    private ArrayList<AddressParamer> addressList;
    public int code;
    public double latitude;
    public double longitude;
    public String noNearbyAddressPromptWords;
    public int pageSize;
    public AddressPositionInfo positionInfo;
    public String searchAddressPromptWords;
    public int totalNum;

    public ArrayList<AddressParamer> getAddressList() {
        ArrayList<AddressParamer> arrayList = this.addressList;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public void setAddressList(ArrayList<AddressParamer> arrayList) {
        this.addressList = arrayList;
    }
}
