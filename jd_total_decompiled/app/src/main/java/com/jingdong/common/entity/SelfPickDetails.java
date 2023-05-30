package com.jingdong.common.entity;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SelfPickDetails implements Serializable {
    public String pBUS;
    public String pDate;
    public String pLocation;
    public String pName;
    public String pPhone;
    public String pickSiteId;

    public String getPickSiteId() {
        return TextUtils.isEmpty(this.pickSiteId) ? "" : this.pickSiteId;
    }

    public String getpBUS() {
        return TextUtils.isEmpty(this.pBUS) ? "" : this.pBUS.trim();
    }

    public String getpDate() {
        return TextUtils.isEmpty(this.pDate) ? "" : this.pDate;
    }

    public String getpLocation() {
        return TextUtils.isEmpty(this.pLocation) ? "" : this.pLocation.trim();
    }

    public String getpName() {
        return TextUtils.isEmpty(this.pName) ? "" : this.pName;
    }

    public String getpPhone() {
        return TextUtils.isEmpty(this.pPhone) ? "" : this.pPhone.trim();
    }
}
