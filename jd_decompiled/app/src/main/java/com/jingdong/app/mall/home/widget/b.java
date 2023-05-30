package com.jingdong.app.mall.home.widget;

import android.view.View;
import com.jingdong.app.mall.home.r.e.d;

/* loaded from: classes4.dex */
public interface b {
    View getContentView();

    void onPreInitView(d dVar, boolean z);

    void onReleaseView();

    void onUseView();

    void onViewBind(d dVar);

    void onViewRecycle();
}
