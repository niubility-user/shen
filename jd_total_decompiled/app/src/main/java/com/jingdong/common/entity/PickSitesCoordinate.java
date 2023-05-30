package com.jingdong.common.entity;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class PickSitesCoordinate implements Serializable {
    public Double distanceNum = Double.valueOf(-1.0d);
    public long id;
    public double laty;
    public double lngx;

    public Double getDistanceNum() {
        Double d = this.distanceNum;
        return d == null ? Double.valueOf(-1.0d) : d;
    }
}
