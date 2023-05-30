package com.jd.lib.cashier.sdk.h.g;

import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.a.e.a {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f3558c;

    public b(String str, String str2, String str3) {
        this.a = "";
        this.b = "";
        this.f3558c = "";
        this.a = str;
        this.b = str2;
        this.f3558c = str3;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.MEDIADATA_NETWORK_ERROR;
    }
}
