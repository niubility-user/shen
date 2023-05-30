package com.jingdong.app.mall.k;

import android.content.Context;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.sdk.oklog.OKLogConfig;
import com.tencent.tencentmap.mapsdk.maps.TencentMapInitializer;

/* loaded from: classes4.dex */
public final class e extends a {
    @Override // com.jingdong.app.mall.k.a, com.jingdong.app.mall.k.f
    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        com.jingdong.app.mall.aura.e.j(a());
    }

    @Override // com.jingdong.app.mall.k.a, com.jingdong.app.mall.k.f
    public void onCreate() {
        super.onCreate();
        d();
        new OKLogConfig().setDebug(false).start();
        Log.init();
        h();
        b();
        f();
        JDLocationManager.getInstance().init();
        TencentMapInitializer.setAgreePrivacy(true);
        com.jingdong.sdk.deeplink.b.a().b(a().getApplicationContext());
        com.jingdong.app.mall.aura.i.f();
        DeepLinkSwitch.getInstance().setSwitchListener(com.jingdong.app.mall.aura.i.c());
        AuraBundleConfig.getInstance().setConfigListener(com.jingdong.app.mall.aura.e.d());
    }
}
