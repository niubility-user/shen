package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class CoordinateToRegion implements Serializable {
    private AddressRegion address;
    public String addressDetail;
    public AreaBeanVOData areaBeanVO;
    public int code;
    public int coord_type;
    public boolean isCloseLocateCity;
    public boolean isForeignOverSea;
    public boolean isGangAoTai;
    public double latitude;
    public double longitude;
    public String message;

    public AddressRegion getAddress() {
        AddressRegion addressRegion = this.address;
        return addressRegion == null ? new AddressRegion() : addressRegion;
    }

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

    public void setAddress(AddressRegion addressRegion) {
        this.address = addressRegion;
    }
}
