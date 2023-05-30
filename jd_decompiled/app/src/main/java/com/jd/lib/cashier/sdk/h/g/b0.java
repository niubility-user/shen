package com.jd.lib.cashier.sdk.h.g;

import com.jd.lib.cashier.sdk.pay.bean.PublicGoodPlan;

/* loaded from: classes14.dex */
public class b0 extends com.jd.lib.cashier.sdk.d.a.e.a {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f3559c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f3560e;

    /* renamed from: f  reason: collision with root package name */
    public PublicGoodPlan f3561f;

    public b0(String str, String str2, PublicGoodPlan publicGoodPlan, String str3, String str4, String str5) {
        this.b = "";
        this.f3559c = "";
        this.d = "";
        this.a = str;
        this.f3560e = str2;
        this.f3561f = publicGoodPlan;
        this.b = str3;
        this.f3559c = str4;
        this.d = str5;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return 100000;
    }
}
