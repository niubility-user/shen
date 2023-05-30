package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ToAddressBody implements Serializable {
    public String addressDetail;
    private AddressParamer addressParamer;
    public String areaName;
    public String cityName;
    public int coord_type;
    public String fullName;
    public String id;
    public boolean isForeignOverSea;
    public boolean isGangAoTai;
    public double latitude;
    public double longitude;
    public int pageSize;
    public String provinceName;
    public String shortAddress;
    public String shortTitle;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ToAddressBody) {
            return TextUtils.equals(((ToAddressBody) obj).id, this.id);
        }
        return false;
    }

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

    public AddressParamer getAddressParamer() {
        AddressParamer addressParamer = this.addressParamer;
        return addressParamer == null ? new AddressParamer() : addressParamer;
    }

    public String getFullName() {
        return TextUtils.isEmpty(this.fullName) ? "" : this.fullName;
    }

    public int getPageSize() {
        int i2 = this.pageSize;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public void setAddressParamer(AddressParamer addressParamer) {
        this.addressParamer = addressParamer;
    }
}
