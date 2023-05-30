package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressAllInfo implements Serializable {
    public boolean Flag;
    public String Message;
    public String NotifyMessage;
    public AddressCheckResultMap addressCheckResultMap;
    public boolean addressCheckStructUpgrade;
    private Coordinate clientRegion;
    public int code;
    private RealRegion realRegion;

    public Coordinate getClientRegion() {
        Coordinate coordinate = this.clientRegion;
        return coordinate == null ? new Coordinate() : coordinate;
    }

    public String getMessage() {
        return TextUtils.isEmpty(this.Message) ? "" : this.Message;
    }

    public String getNotifyMessage() {
        return TextUtils.isEmpty(this.NotifyMessage) ? "" : this.NotifyMessage;
    }

    public RealRegion getRealRegion() {
        RealRegion realRegion = this.realRegion;
        return realRegion == null ? new RealRegion() : realRegion;
    }

    public void setClientRegion(Coordinate coordinate) {
        this.clientRegion = coordinate;
    }

    public void setRealRegion(RealRegion realRegion) {
        this.realRegion = realRegion;
    }
}
