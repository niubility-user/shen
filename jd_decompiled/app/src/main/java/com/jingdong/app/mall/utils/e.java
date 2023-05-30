package com.jingdong.app.mall.utils;

import android.content.Context;
import com.jingdong.app.mall.JDApp;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class e {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final String[] b = {"com.jd.lib.virtualhuman", "com.jd.lib.DiscoverAttention", "com.jd.lib.mystreet", "com.jd.lib.orderinfocard", "com.jd.lib.goodstuff", "com.jd.lib.meme", "com.jd.lib.quickpass", "com.jd.lib.jdcustomchannel", "com.jd.lib.matrixar", "com.jd.lib.threedproduct", "com.jd.lib.sonicredpacket"};

    /* renamed from: c  reason: collision with root package name */
    private static boolean f11783c = false;

    public static void a() {
        if (f11783c) {
            c();
        }
        f11783c = true;
    }

    private static void b(String str) {
        if (AuraBundleConfig.getInstance().isBundlePrepared(str)) {
            return;
        }
        Context applicationContext = JDApp.getInstance().getApplicationContext();
        IAuraInstallManager iAuraInstallManager = (IAuraInstallManager) AuraServiceLoader.get(applicationContext, IAuraInstallManager.class);
        AuraInstallRequest build = new AuraInstallRequest.Builder().setBundleName(str).setDownloadType(1).build();
        try {
            JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "GoogleChannelPreload", "preloadSwitch", "1").equals("2");
        } catch (Throwable th) {
            OKLog.e("BundleLoadUtil", "error:" + th.getCause());
        }
        iAuraInstallManager.startInstall(applicationContext, build);
    }

    public static void c() {
        if (a.getAndSet(true)) {
            return;
        }
        for (String str : b) {
            b(str);
        }
    }
}
