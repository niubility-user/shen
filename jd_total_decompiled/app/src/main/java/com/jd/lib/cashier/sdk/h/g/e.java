package com.jd.lib.cashier.sdk.h.g;

import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class e extends com.jd.lib.cashier.sdk.d.a.e.a {
    public boolean a;
    public String b;

    public e(boolean z, String str, String str2) {
        this.a = z;
        this.b = str;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.MEDIADATA_INTERNAL_ERROR;
    }
}
