package com.jingdong.app.mall;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.jingdong.app.mall.k.f;

/* loaded from: classes.dex */
interface IJDAppLikeInit extends f {
    void init(Application application);

    boolean initStatus();

    boolean isLazyInit();

    @Override // com.jingdong.app.mall.k.f
    /* synthetic */ void onBaseContextAttached(Context context);

    void onConfigurationChanged(Configuration configuration);

    @Override // com.jingdong.app.mall.k.f
    /* synthetic */ void onCreate();

    void reInit();
}
