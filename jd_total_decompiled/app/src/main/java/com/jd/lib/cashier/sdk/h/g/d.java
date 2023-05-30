package com.jd.lib.cashier.sdk.h.g;

import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class d extends com.jd.lib.cashier.sdk.d.a.e.a {
    public String a;

    public d(String str) {
        this.a = str;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.MEDIADATA_AUTH_FAILED;
    }
}
