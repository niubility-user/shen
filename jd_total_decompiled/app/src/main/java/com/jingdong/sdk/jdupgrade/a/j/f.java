package com.jingdong.sdk.jdupgrade.a.j;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;
import com.jingdong.sdk.jdupgrade.inner.ui.UpgradeAPKProvider;
import java.io.File;

/* loaded from: classes7.dex */
public class f {
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Intent a(String str) {
        Uri parse;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                String str2 = com.jingdong.sdk.jdupgrade.a.c.j().getPackageName() + ".installProvider";
                h.a("InstallUtil", "authority:" + str2 + ", apk path:" + str);
                parse = FileProvider.getUriForFile(com.jingdong.sdk.jdupgrade.a.c.j(), str2, new File(str));
            } catch (Throwable th) {
                th = th;
                parse = null;
            }
            try {
                intent.addFlags(3);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                h.b("InstallUtil", "install provider not found");
                try {
                    String a = a(UpgradeAPKProvider.class);
                    if (!TextUtils.isEmpty(a)) {
                        h.a("InstallUtil", "find app self define authority:" + a + ", apk path:" + str);
                        parse = FileProvider.getUriForFile(com.jingdong.sdk.jdupgrade.a.c.j(), a, new File(str));
                        intent.addFlags(3);
                    }
                    if (parse != null) {
                    }
                } catch (Throwable unused) {
                    return null;
                }
            }
        } else {
            parse = Uri.parse("file://" + str);
        }
        if (parse != null) {
            return null;
        }
        intent.setDataAndType(parse, "application/vnd.android.package-archive");
        return intent;
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
