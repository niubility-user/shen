package com.huawei.hms.activity.internal;

import android.content.Intent;

/* loaded from: classes12.dex */
public class BusResponseResult {
    private Intent a;
    private int b;

    public int getCode() {
        return this.b;
    }

    public Intent getIntent() {
        return this.a;
    }

    public void setCode(int i2) {
        this.b = i2;
    }

    public void setIntent(Intent intent) {
        this.a = intent;
    }
}
