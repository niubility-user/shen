package com.jd.lib.cashier.sdk.d.g.g;

import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes14.dex */
public class c extends com.jd.lib.cashier.sdk.d.f.c {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f3287c;
    public String d;

    public c() {
        this.a = "";
        this.b = "";
        this.f3287c = "";
        this.d = "";
    }

    @Override // com.jd.lib.cashier.sdk.d.f.c
    public String toString() {
        return "BasePayParam{from='" + this.a + "', backUrl='" + this.b + "', channelCode='" + this.f3287c + "', sdkToken='" + this.d + "'}";
    }

    public c(WeakReference<FragmentActivity> weakReference) {
        super(weakReference);
        this.a = "";
        this.b = "";
        this.f3287c = "";
        this.d = "";
    }
}
