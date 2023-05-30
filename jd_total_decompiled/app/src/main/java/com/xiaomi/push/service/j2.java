package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes11.dex */
public class j2 {
    public static ComponentName a(Context context, Intent intent) {
        if (intent != null) {
            try {
                ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
                if (resolveActivity != null) {
                    return new ComponentName(resolveActivity.activityInfo.packageName, TextUtils.isEmpty(resolveActivity.activityInfo.targetActivity) ? resolveActivity.activityInfo.name : resolveActivity.activityInfo.targetActivity);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean b(Context context, ComponentName componentName) {
        try {
            new Intent().setComponent(componentName);
            context.getPackageManager().getActivityInfo(componentName, 128);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(Context context, String str) {
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(str, 4).services;
            if (serviceInfoArr != null) {
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            g.j.a.a.a.c.o("checkService " + e2);
            return false;
        }
    }

    public static boolean d(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(str);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (queryIntentServices != null) {
                return !queryIntentServices.isEmpty();
            }
            return false;
        } catch (Exception e2) {
            g.j.a.a.a.c.o("checkService action: " + str2 + ", " + e2);
            return false;
        }
    }

    public static boolean e(Context context, String str) {
        boolean z = false;
        try {
            List<ProviderInfo> queryContentProviders = context.getPackageManager().queryContentProviders(null, 0, 8);
            if (queryContentProviders != null && !queryContentProviders.isEmpty()) {
                for (ProviderInfo providerInfo : queryContentProviders) {
                    if (providerInfo.enabled && providerInfo.exported && providerInfo.authority.equals(str)) {
                        z = true;
                    }
                }
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("checkProvider " + e2);
        }
        return z;
    }

    public static boolean f(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(str);
            return packageManager.resolveActivity(intent, 65536) != null;
        } catch (Exception e2) {
            g.j.a.a.a.c.o("checkActivity action: " + str2 + ", " + e2);
            return false;
        }
    }
}
