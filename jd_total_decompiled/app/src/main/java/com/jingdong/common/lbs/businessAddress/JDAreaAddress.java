package com.jingdong.common.lbs.businessAddress;

/* loaded from: classes5.dex */
public class JDAreaAddress {
    private int id;
    private String name;
    private int parentId;
    private int sortId;

    public int getId() {
        return this.id;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public int getParentId() {
        return this.parentId;
    }

    public int getSortId() {
        return this.sortId;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParentId(int i2) {
        this.parentId = i2;
    }

    public void setSortId(int i2) {
        this.sortId = i2;
    }
}
