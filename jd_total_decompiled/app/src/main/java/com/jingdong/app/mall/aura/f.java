package com.jingdong.app.mall.aura;

import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;
import java.util.LinkedHashMap;

/* loaded from: classes19.dex */
public class f {
    public static void a(LinkedHashMap<String, String> linkedHashMap) {
        try {
            String bundleNameFromBundleId = AuraBundleInfos.getBundleNameFromBundleId(86);
            linkedHashMap.put("jdFlutterIsProvidedBundle", String.valueOf(AuraConfig.isBundleProvided(bundleNameFromBundleId)));
            linkedHashMap.put("jdFlutterBundlePrepared", String.valueOf(AuraConfig.isBundlePrepered(bundleNameFromBundleId)));
            linkedHashMap.put("jdFlutterNotPreparedBundles", String.valueOf(AuraConfig.getNotPreparedProvidedBundles(bundleNameFromBundleId)));
            linkedHashMap.put("isOpenAura", com.jingdong.app.mall.aura.internal.c.f().g() + "");
            linkedHashMap.put("aura switch", com.jingdong.app.mall.aura.internal.c.f().d());
            linkedHashMap.put("auraEngine state:", AuraState.formatAuraEngineState());
            linkedHashMap.put("auraUpdate state:", AuraState.formatAuraUpdateState());
            linkedHashMap.put("update first start:", e.f7928c + "");
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.e("AuraCrashHandle", th);
            }
        }
    }
}
