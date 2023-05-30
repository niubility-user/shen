package com.jd.lib.cashier.sdk.freindpay.bean;

import android.content.Intent;

/* loaded from: classes14.dex */
public class ActivityResult {
    public Intent intent;
    public int requestCode;
    public int resultCode;

    public ActivityResult(Intent intent, int i2, int i3) {
        this.intent = intent;
        this.requestCode = i2;
        this.resultCode = i3;
    }
}
