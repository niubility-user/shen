package com.jd.lib.babel.ifloor.entity;

/* loaded from: classes13.dex */
public abstract class BaseFloorModel {
    private int adapterPosition;
    private int floorWidth;
    public String fno;
    public String mid;

    public int getAdapterPosition() {
        return this.adapterPosition;
    }

    public int getFloorWidth() {
        return this.floorWidth;
    }

    public String getStyle(int i2) {
        return null;
    }

    public int getTotalCount() {
        return 1;
    }

    public abstract boolean handleData();

    public boolean isNestScrollStyle() {
        return false;
    }

    public void setAdapterPosition(int i2) {
        this.adapterPosition = i2;
    }

    public void setFloorWidth(int i2) {
        this.floorWidth = i2;
    }
}
