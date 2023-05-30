package com.jd.lib.cashier.sdk.d.b;

/* loaded from: classes14.dex */
public enum b {
    FAILED(1000),
    SUC(2000);
    
    private int networkResultCode;

    b(int i2) {
        this.networkResultCode = i2;
    }

    public int getNetworkResultCode() {
        return this.networkResultCode;
    }

    public void setNetworkResultCode(int i2) {
        this.networkResultCode = i2;
    }
}
