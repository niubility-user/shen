package com.jingdong.common.entity;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class EasyBuyDeleteAddress implements Serializable {
    public String NotifyMessage;
    public int addressLimit;
    private ArrayList<NewEasyBuyAddress> addressList;
    public int code;
    public Boolean result;
    public String shipMessage;

    public ArrayList<NewEasyBuyAddress> getAddressList() {
        ArrayList<NewEasyBuyAddress> arrayList = this.addressList;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public String getNotifyMessage() {
        return TextUtils.isEmpty(this.NotifyMessage) ? "" : this.NotifyMessage;
    }

    public Boolean getResult() {
        Boolean bool = this.result;
        return bool == null ? Boolean.FALSE : bool;
    }

    public String getShipMessage() {
        return TextUtils.isEmpty(this.shipMessage) ? "" : this.shipMessage;
    }

    public void setAddressList(ArrayList<NewEasyBuyAddress> arrayList) {
        this.addressList = arrayList;
    }
}
