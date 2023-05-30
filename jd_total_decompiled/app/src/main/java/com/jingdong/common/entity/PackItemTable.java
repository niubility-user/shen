package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class PackItemTable {
    public static final String TB_CLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_CLOUMN_EXTEND = "extendProt";
    public static final String TB_CLOUMN_PACKS_CODE = "packId";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "name";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public static final String TB_PACK_ITEM = "PackItemTable";
    public int buyCount;
    public String name;
    public long packId;
    public long productCode;

    public PackItemTable(String str, long j2, int i2) {
        this.productCode = j2;
        this.name = str;
        this.buyCount = i2;
    }

    public PackItemTable(String str, long j2, int i2, long j3) {
        this.productCode = j2;
        this.name = str;
        this.buyCount = i2;
        this.packId = j3;
    }

    public PackItemTable() {
    }
}
