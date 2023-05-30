package com.jingdong.app.mall.aura;

import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes19.dex */
public class p {
    public static Collection<Runnable> a = null;
    private static boolean b = true;

    public static void a() {
        if (UnAndroidUtils.getAndroidVersion() < 26) {
            b();
        }
    }

    private static void b() {
        try {
        } catch (Exception unused) {
            b = false;
        }
        if ("1".equals(JDMobileConfig.getInstance().getConfig("jdRecommend", "spSupportHook", "support", "0"))) {
            if (a == null && b) {
                boolean z = OKLog.D;
                a = (ConcurrentLinkedQueue) c("android.app.QueuedWork", "sPendingWorkFinishers");
            }
            Collection<Runnable> collection = a;
            if (collection != null) {
                collection.clear();
                if (OKLog.D) {
                    String str = "QuenedWorkProxy--\u751f\u6548-->" + a.size();
                }
            }
        }
    }

    private static Object c(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        Field declaredField = cls.getDeclaredField(str2);
        declaredField.setAccessible(true);
        return declaredField.get(cls);
    }
}
