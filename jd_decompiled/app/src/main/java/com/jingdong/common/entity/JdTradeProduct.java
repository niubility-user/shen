package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class JdTradeProduct {
    protected int count;
    protected String sJdPrice;
    protected String sProdID;
    protected String sProdName;

    public void addCount() {
        this.count++;
    }

    public int getCount() {
        int i2 = this.count;
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public String getProdID() {
        String str = this.sProdID;
        return str == null ? "" : str;
    }

    public String getProdName() {
        String str = this.sProdName;
        return str == null ? "" : str;
    }

    public String getProdPrice() {
        String str = this.sJdPrice;
        return str == null ? "" : str;
    }
}
