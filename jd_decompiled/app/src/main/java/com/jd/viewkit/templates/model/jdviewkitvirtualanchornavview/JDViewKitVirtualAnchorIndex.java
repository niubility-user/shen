package com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview;

/* loaded from: classes18.dex */
public class JDViewKitVirtualAnchorIndex {
    private int beginFloor;
    private int endFloor;
    private String moduleId;

    public JDViewKitVirtualAnchorIndex(String str, int i2, int i3) {
        this.moduleId = str;
        this.beginFloor = i2;
        this.endFloor = i3;
    }

    public int getBeginFloor() {
        return this.beginFloor;
    }

    public int getEndFloor() {
        return this.endFloor;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setBeginFloor(int i2) {
        this.beginFloor = i2;
    }

    public void setEndFloor(int i2) {
        this.endFloor = i2;
    }
}
