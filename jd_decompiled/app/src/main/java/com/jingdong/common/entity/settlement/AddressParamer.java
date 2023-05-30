package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressParamer implements Serializable {
    public String addressDetail;
    public String areaName;
    public String cityName;
    public String fullName;
    public String id;
    public boolean isForeignOverSea;
    public boolean isGangAoTai;
    public double latitude;
    public double longitude;
    public String provinceName;
    public String shortAddress;
    public String title;
    public String where;

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

    public String getAreaName() {
        return TextUtils.isEmpty(this.areaName) ? "" : this.areaName;
    }

    public String getCityName() {
        return TextUtils.isEmpty(this.cityName) ? "" : this.cityName;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProvinceName());
        sb.append(TextUtils.equals(getProvinceName(), getCityName()) ? "" : getCityName());
        sb.append(getAreaName());
        sb.append(getShortAddress());
        String sb2 = sb.toString();
        this.fullName = sb2;
        return TextUtils.isEmpty(sb2) ? "" : this.fullName;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.provinceName) ? "" : this.provinceName;
    }

    public String getShortAddress() {
        return TextUtils.isEmpty(this.shortAddress) ? "" : this.shortAddress;
    }

    public String getTitle() {
        return TextUtils.isEmpty(this.title) ? "" : this.title;
    }

    public String getWhere() {
        return TextUtils.isEmpty(this.where) ? "" : this.where;
    }
}
