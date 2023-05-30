package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;
import com.jd.lib.un.basewidget.widget.banner.BannerView;
import com.jd.lib.un.basewidget.widget.banner.page.PageView;
import com.jingdong.sdk.lib.puppetlayout.R;

/* loaded from: classes8.dex */
public class j extends com.jingdong.sdk.lib.puppetlayout.h.a {
    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void e(Context context, View view) {
        PageView pageView = new PageView(context);
        if (view instanceof BannerView) {
            view.setTag(R.id.com_jd_sdk_lib_puppetlayout_indicator_2, pageView);
        }
        this.a = pageView;
    }
}
