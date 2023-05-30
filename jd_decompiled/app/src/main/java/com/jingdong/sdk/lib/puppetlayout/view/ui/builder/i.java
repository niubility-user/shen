package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;
import com.jd.lib.un.basewidget.widget.banner.BannerView;
import com.jd.lib.un.basewidget.widget.banner.indicator.ClassicIndicator;

/* loaded from: classes8.dex */
public class i extends com.jingdong.sdk.lib.puppetlayout.h.a {
    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void e(Context context, View view) {
        ClassicIndicator classicIndicator = new ClassicIndicator(context);
        if (view instanceof BannerView) {
            classicIndicator.setBannerView((BannerView) view);
        }
        this.a = classicIndicator;
    }
}
