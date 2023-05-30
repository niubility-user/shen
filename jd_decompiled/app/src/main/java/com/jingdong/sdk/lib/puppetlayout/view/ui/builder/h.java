package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import com.jingdong.sdk.lib.puppetlayout.view.ui.ImageWidget;

/* loaded from: classes8.dex */
public class h extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private ImageWidget f15248k;

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        ImageWidget imageWidget = new ImageWidget(context);
        this.f15248k = imageWidget;
        this.a = imageWidget;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        if (super.q(str, str2, str3)) {
            return true;
        }
        str.hashCode();
        if (str.equals("src")) {
            this.f15248k.b(str2);
        } else if (str.equals("scale")) {
            this.f15248k.a(str2);
        }
        return true;
    }
}
