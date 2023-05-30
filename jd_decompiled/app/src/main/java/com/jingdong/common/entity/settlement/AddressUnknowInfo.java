package com.jingdong.common.entity.settlement;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressUnknowInfo implements Serializable {
    private AddressBase area;
    private AddressBase city;
    public boolean flag;
    public String message;
    public String noSelectedTipAddressMessage;
    private AddressBase province;
    private AddressBase town;

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

    public void setArea(AddressBase addressBase) {
        this.area = addressBase;
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
