package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class CartTable {
    public int buyCount;
    public String name;
    public long packId;
    public long productCode;
    public String sourceType;
    public String sourceValue;

    public CartTable(String str, long j2, int i2, String str2, String str3) {
        this.productCode = j2;
        this.name = str;
        this.buyCount = i2;
        this.sourceType = str2;
        this.sourceValue = str3;
    }

    public CartTable(String str, long j2, int i2, String str2, String str3, long j3) {
        this.productCode = j2;
        this.name = str;
        this.buyCount = i2;
        this.sourceType = str2;
        this.sourceValue = str3;
        this.packId = j3;
    }

    public CartTable() {
    }
}
