package com.jingdong.common.entity.settlement;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class RealRegion extends Coordinate {
    public String addressDetail;
    private AddressBase area;
    private AddressBase city;
    private AddressBase province;
    private AddressBase town;

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

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
