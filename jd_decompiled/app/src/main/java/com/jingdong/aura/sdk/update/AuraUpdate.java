package com.jingdong.aura.sdk.update;

import android.os.Handler;
import android.os.HandlerThread;
import com.jingdong.aura.sdk.update.b.f;
import com.jingdong.aura.sdk.update.b.g;
import com.jingdong.aura.sdk.update.b.l;
import com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class AuraUpdate {
    public static void init(AuraUpdateConfig auraUpdateConfig) {
        final a a = a.a();
        if (a.f12236f) {
            return;
        }
        a.a = auraUpdateConfig;
        l.a(auraUpdateConfig, "config is null");
        l.a(a.a.appKey, "appKey is null");
        l.a(a.a.appSecret, "appSecret is null");
        l.a(a.a.context, "context is null");
        l.a(a.a.bundleInfoProvider, "bundleInfoProvider is null");
        l.a(a.a.downloader, "downloader is null");
        com.jingdong.aura.sdk.update.b.b bVar = a.f12234e;
        AuraUpdateConfig auraUpdateConfig2 = a.a;
        bVar.a = auraUpdateConfig2.updateInteval;
        a.f12243m = new com.jingdong.aura.sdk.update.report.a(auraUpdateConfig2.reporter);
        AuraUpdateConfig auraUpdateConfig3 = a.a;
        a.f12242l = new com.jingdong.aura.sdk.update.downloader.a(auraUpdateConfig3.context, auraUpdateConfig3.downloader);
        a.f12241k = new File(a.a.context.getFilesDir(), "apkcenter");
        a.f12244n = new com.jingdong.aura.sdk.update.a.a();
        try {
            AuraUpdateConfig auraUpdateConfig4 = a.a;
            IPrivacyFieldProvider iPrivacyFieldProvider = auraUpdateConfig4.privacyFieldProvider;
            if (iPrivacyFieldProvider != null) {
                f.a(iPrivacyFieldProvider.getDensity(auraUpdateConfig4.context));
                AuraUpdateConfig auraUpdateConfig5 = a.a;
                f.b(auraUpdateConfig5.privacyFieldProvider.getScaleDensity(auraUpdateConfig5.context));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.jingdong.aura.sdk.provided.ui.a.a(a.a.toastUtils);
        a.f12238h = a.a.context.getSharedPreferences("aura_update_" + a.a.appVersionCode, 0);
        a.f12239i = a.a.context.getSharedPreferences("aura_update_config", 0);
        a.f12240j = a.a(a.a.appVersionCode);
        AuraUpdateConfig auraUpdateConfig6 = a.a;
        com.jingdong.aura.sdk.update.b.c.a(String.format("appId:%s, versionName:%s, versionCode:%s ,isAppUpgrade:%s", auraUpdateConfig6.appKey, auraUpdateConfig6.appVersionName, Integer.valueOf(auraUpdateConfig6.appVersionCode), Boolean.valueOf(a.f12240j)));
        if (a.f12240j) {
            com.jingdong.aura.sdk.update.b.c.b("App is upgrade, will clean download cache");
            g.a(a.f12241k);
        }
        a.f12239i.edit().putInt("last_version_code", a.a.appVersionCode).apply();
        a.e();
        ArrayList<AuraBundleResult> d = a.d();
        a.b = d;
        if (a.a.enableLog && d != null) {
            Iterator<AuraBundleResult> it = d.iterator();
            while (it.hasNext()) {
                com.jingdong.aura.sdk.update.b.c.a("\u89e3\u6790\u914d\u7f6e\u6587\u4ef6 getProvidedBundleResult : " + it.next().updateId);
            }
        }
        List<AuraBundleResult> a2 = a.a(a.b, a.c());
        a.f12235c = a2;
        if (a.a.enableLog && a2 != null) {
            Iterator<AuraBundleResult> it2 = a2.iterator();
            while (it2.hasNext()) {
                com.jingdong.aura.sdk.update.b.c.a("\u5408\u5e76\u5df2\u4e0b\u8f7d\u6587\u4ef6\u540e mergeBundleResult : " + it2.next().updateId);
            }
        }
        a.d = new com.jingdong.aura.sdk.update.updater.a();
        HandlerThread handlerThread = new HandlerThread("AuraUpdate");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        a.f12237g = handler;
        handler.post(new Runnable() { // from class: com.jingdong.aura.sdk.update.a.1
            @Override // java.lang.Runnable
            public final void run() {
                if (a.this.f12235c != null && a.this.f12235c.size() > 0) {
                    a.b(a.this);
                }
                a.this.b();
            }
        });
        List<AuraBundleResult> list = a.f12235c;
        if (list != null && list.size() > 0) {
            a.f12242l.a(a.f12235c);
        }
        a.f12236f = true;
    }

    public static void initAuraServiceLoader() {
        a.a();
        a.e();
    }

    public static void requestUpdateBundles() {
        a.a().b();
    }
}
