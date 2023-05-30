package com.jd.lib.productdetail.core.entitys.shop;

/* loaded from: classes15.dex */
public class PdShopEventEntity {
    public static final int FROM_BELT = 2;
    public static final int FROM_BOTTOM = 1;
    public static final int FROM_FLOOR = 3;
    public boolean follow;
    public int from;
    public String goShopType;

    public PdShopEventEntity(boolean z, String str) {
        this(z, str, 0);
    }

    public PdShopEventEntity(boolean z, String str, int i2) {
        this.follow = z;
        this.goShopType = str;
        this.from = i2;
    }
}
