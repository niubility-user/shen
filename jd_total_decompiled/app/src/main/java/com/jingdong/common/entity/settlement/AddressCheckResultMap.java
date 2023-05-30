package com.jingdong.common.entity.settlement;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressCheckResultMap implements Serializable {
    public String addressDetail;
    public AddressBase area;
    public AddressBase city;
    public int clickLeftType;
    public int clickRightType;
    public int coord_type;
    public double latitude;
    public String latitudeString;
    public double longitude;
    public String longitudeString;
    public String noSelectedTipAddressMessage;
    public AddressBase province;
    public String selectLeftMsg;
    public String selectMiddleMsg;
    public String selectRightMsg;
    public boolean sharedPrompt;
    public String titleMsg;
    public AddressBase town;
    public int type;

    public AddressBase getArea() {
        AddressBase addressBase = this.area;
        return addressBase == null ? new AddressBase() : addressBase;
    }

    public AddressBase getCity() {
        AddressBase addressBase = this.city;
        return addressBase == null ? new AddressBase() : addressBase;
    }

    public AddressBase getProvince() {
        AddressBase addressBase = this.province;
        return addressBase == null ? new AddressBase() : addressBase;
    }

    public AddressBase getTown() {
        AddressBase addressBase = this.town;
        return addressBase == null ? new AddressBase() : addressBase;
    }

    public void setCity(AddressBase addressBase) {
        this.city = addressBase;
    }

    public void setProvince(AddressBase addressBase) {
        this.province = addressBase;
    }

    public void setTown(AddressBase addressBase) {
        this.town = addressBase;
    }
}
