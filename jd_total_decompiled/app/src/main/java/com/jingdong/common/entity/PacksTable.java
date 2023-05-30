package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class PacksTable {
    public int buyCount;
    public int childCount;
    public String name;
    public long packId;
    public String sourceType;
    public String sourceValue;

    public PacksTable(long j2, String str, int i2, int i3, String str2, String str3) {
        this.childCount = i3;
        this.name = str;
        this.buyCount = i2;
        this.packId = j2;
        this.sourceType = str2;
        this.sourceValue = str3;
    }

    public PacksTable() {
    }
}
