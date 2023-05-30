package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class e extends ProxyController {
    private void a(Executor executor, Runnable runnable) {
        try {
            Class<?> cls = Class.forName("androidx.webkit.internal.ProxyControllerImpl");
            Method declaredMethod = cls.getDeclaredMethod("getBoundaryInterface", new Class[0]);
            declaredMethod.setAccessible(true);
            Class.forName("org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface").getMethod("clearProxyOverride", Runnable.class, Executor.class).invoke(declaredMethod.invoke(cls.newInstance(), new Object[0]), runnable, executor);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String[][] a(List<ProxyConfig.ProxyRule> list) {
        String[][] strArr = (String[][]) Array.newInstance(String.class, list.size(), 2);
        for (int i2 = 0; i2 < list.size(); i2++) {
            strArr[i2][0] = list.get(i2).getSchemeFilter();
            strArr[i2][1] = list.get(i2).getUrl();
        }
        return strArr;
    }

    private void b(Executor executor, Runnable runnable) {
        try {
            DexLoader b = u.a().c().b();
            b.invokeMethod(b.newInstance("android.webview.chromium.tencent.TencentSupportProxyController", new Class[0], new Object[0]), "android.webview.chromium.tencent.TencentSupportProxyController", "clearProxyOverride", new Class[]{Runnable.class, Executor.class}, runnable, executor);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(String[][] strArr, String[] strArr2, Runnable runnable, Executor executor) {
        try {
            DexLoader b = u.a().c().b();
            b.invokeMethod(b.newInstance("android.webview.chromium.tencent.TencentSupportProxyController", new Class[0], new Object[0]), "android.webview.chromium.tencent.TencentSupportProxyController", "setProxyOverride", new Class[]{String[][].class, String[].class, Runnable.class, Executor.class, Boolean.TYPE}, strArr, strArr2, runnable, executor, Boolean.FALSE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(String[][] strArr, String[] strArr2, Runnable runnable, Executor executor) {
        try {
            Class<?> cls = Class.forName("androidx.webkit.internal.ProxyControllerImpl");
            Method declaredMethod = cls.getDeclaredMethod("getBoundaryInterface", new Class[0]);
            declaredMethod.setAccessible(true);
            Class.forName("org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface").getMethod("setProxyOverride", String[][].class, String[].class, Runnable.class, Executor.class).invoke(declaredMethod.invoke(cls.newInstance(), new Object[0]), strArr, strArr2, runnable, executor);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.tencent.smtt.sdk.ProxyController
    public void clearProxyOverride(Executor executor, Runnable runnable) {
        u a = u.a();
        if (a == null || !a.b()) {
            a(executor, runnable);
        } else {
            b(executor, runnable);
        }
    }

    @Override // com.tencent.smtt.sdk.ProxyController
    public void setProxyOverride(ProxyConfig proxyConfig, Executor executor, Runnable runnable) {
        String[][] a = a(proxyConfig.getProxyRules());
        String[] strArr = (String[]) proxyConfig.getBypassRules().toArray(new String[0]);
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            b(a, strArr, runnable, executor);
        } else {
            a(a, strArr, runnable, executor);
        }
    }
}
