package com.jingdong.sdk.jdupgrade.a.j;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;

/* loaded from: classes7.dex */
public class f {
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent a(java.lang.String r8) {
        /*
            java.lang.String r0 = ", apk path:"
            java.lang.String r1 = "InstallUtil"
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "android.intent.action.VIEW"
            r2.<init>(r3)
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            r2.addFlags(r3)
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 0
            r5 = 24
            if (r3 < r5) goto La0
            r3 = 3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e
            r5.<init>()     // Catch: java.lang.Throwable -> L5e
            android.content.Context r6 = com.jingdong.sdk.jdupgrade.a.c.j()     // Catch: java.lang.Throwable -> L5e
            java.lang.String r6 = r6.getPackageName()     // Catch: java.lang.Throwable -> L5e
            r5.append(r6)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r6 = ".installProvider"
            r5.append(r6)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L5e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e
            r6.<init>()     // Catch: java.lang.Throwable -> L5e
            java.lang.String r7 = "authority:"
            r6.append(r7)     // Catch: java.lang.Throwable -> L5e
            r6.append(r5)     // Catch: java.lang.Throwable -> L5e
            r6.append(r0)     // Catch: java.lang.Throwable -> L5e
            r6.append(r8)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5e
            com.jingdong.sdk.jdupgrade.a.j.h.a(r1, r6)     // Catch: java.lang.Throwable -> L5e
            android.content.Context r6 = com.jingdong.sdk.jdupgrade.a.c.j()     // Catch: java.lang.Throwable -> L5e
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L5e
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L5e
            android.net.Uri r5 = androidx.core.content.FileProvider.getUriForFile(r6, r5, r7)     // Catch: java.lang.Throwable -> L5e
            r2.addFlags(r3)     // Catch: java.lang.Throwable -> L5c
            goto Lb5
        L5c:
            r6 = move-exception
            goto L60
        L5e:
            r6 = move-exception
            r5 = r4
        L60:
            r6.printStackTrace()
            java.lang.String r6 = "install provider not found"
            com.jingdong.sdk.jdupgrade.a.j.h.b(r1, r6)
            java.lang.Class<com.jingdong.sdk.jdupgrade.inner.ui.UpgradeAPKProvider> r6 = com.jingdong.sdk.jdupgrade.inner.ui.UpgradeAPKProvider.class
            java.lang.String r6 = a(r6)     // Catch: java.lang.Throwable -> L9f
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L9f
            if (r7 != 0) goto Lb5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9f
            r5.<init>()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r7 = "find app self define authority:"
            r5.append(r7)     // Catch: java.lang.Throwable -> L9f
            r5.append(r6)     // Catch: java.lang.Throwable -> L9f
            r5.append(r0)     // Catch: java.lang.Throwable -> L9f
            r5.append(r8)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L9f
            com.jingdong.sdk.jdupgrade.a.j.h.a(r1, r0)     // Catch: java.lang.Throwable -> L9f
            android.content.Context r0 = com.jingdong.sdk.jdupgrade.a.c.j()     // Catch: java.lang.Throwable -> L9f
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L9f
            r1.<init>(r8)     // Catch: java.lang.Throwable -> L9f
            android.net.Uri r5 = androidx.core.content.FileProvider.getUriForFile(r0, r6, r1)     // Catch: java.lang.Throwable -> L9f
            r2.addFlags(r3)     // Catch: java.lang.Throwable -> L9f
            goto Lb5
        L9f:
            return r4
        La0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "file://"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            android.net.Uri r5 = android.net.Uri.parse(r8)
        Lb5:
            if (r5 != 0) goto Lb8
            return r4
        Lb8:
            java.lang.String r8 = "application/vnd.android.package-archive"
            r2.setDataAndType(r5, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdupgrade.a.j.f.a(java.lang.String):android.content.Intent");
    }

    private static String a(Class<? extends ContentProvider> cls) {
        Context j2 = com.jingdong.sdk.jdupgrade.a.c.j();
        PackageManager packageManager = j2.getPackageManager();
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(j2.getPackageName(), 8);
                if (packageInfo != null) {
                    for (ProviderInfo providerInfo : packageInfo.providers) {
                        if (providerInfo.name.equals(cls.getName())) {
                            return providerInfo.authority;
                        }
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static void a(String str, UpgradeEventListener upgradeEventListener) {
        try {
            h.a("InstallUtil", "apk path:" + str);
            Intent a = a(str);
            if (a != null) {
                com.jingdong.sdk.jdupgrade.a.c.j().startActivity(a);
            } else if (upgradeEventListener != null) {
                upgradeEventListener.onMessage("8", "install intent null");
            }
        } catch (Throwable th) {
            th.printStackTrace();
            if (upgradeEventListener != null) {
                try {
                    upgradeEventListener.onMessage("8", "install intent error: " + th.getMessage());
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }
}
