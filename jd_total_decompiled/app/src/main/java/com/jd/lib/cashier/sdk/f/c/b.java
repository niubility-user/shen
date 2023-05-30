package com.jd.lib.cashier.sdk.f.c;

import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.core.aac.b {
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f3346c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f3347e;

    /* renamed from: f  reason: collision with root package name */
    public String f3348f;

    /* renamed from: g  reason: collision with root package name */
    public String f3349g;

    /* renamed from: h  reason: collision with root package name */
    public String f3350h;

    /* renamed from: i  reason: collision with root package name */
    public String f3351i;

    /* renamed from: j  reason: collision with root package name */
    public String f3352j;

    /* renamed from: k  reason: collision with root package name */
    public String f3353k = "";

    /* renamed from: l  reason: collision with root package name */
    public String f3354l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f3355m;

    /* renamed from: n  reason: collision with root package name */
    private a f3356n;
    public CashierFriendPayEntity o;

    @Override // com.jd.lib.cashier.sdk.core.aac.b
    public void b() {
        a aVar = this.f3356n;
        if (aVar != null) {
            aVar.a();
            this.f3356n = null;
        }
    }

    public a c() {
        if (this.f3356n == null) {
            this.f3356n = new a();
        }
        return this.f3356n;
    }
}
