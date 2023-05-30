package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;

/* loaded from: classes8.dex */
public abstract class g<V extends View> extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k */
    private View f15247k;

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        V u = u(context);
        this.f15247k = u;
        this.a = u;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        return super.q(str, str2, str3) || v(str, str2);
    }

    public abstract V u(Context context);

    public abstract boolean v(String str, String str2);
}
