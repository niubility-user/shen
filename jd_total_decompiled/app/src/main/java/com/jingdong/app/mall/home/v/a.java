package com.jingdong.app.mall.home.v;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes4.dex */
public class a {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled() && c(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static boolean b(Context context, String str) {
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        String androidIdWithAOPBySecure = BaseInfo.getAndroidIdWithAOPBySecure(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        if (androidIdWithAOPBySecure != null) {
            simpleStringSplitter.setString(androidIdWithAOPBySecure);
            while (simpleStringSplitter.hasNext()) {
                if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean c(Context context) {
        Intent intent = new Intent("android.accessibilityservice.AccessibilityService");
        intent.addCategory("android.accessibilityservice.category.FEEDBACK_SPOKEN");
        boolean z = false;
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && queryIntentServices.size() > 0 && Build.VERSION.SDK_INT >= 26) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                z |= b(context, resolveInfo.serviceInfo.packageName + "/" + resolveInfo.serviceInfo.name);
            }
        }
        return z;
    }
}
