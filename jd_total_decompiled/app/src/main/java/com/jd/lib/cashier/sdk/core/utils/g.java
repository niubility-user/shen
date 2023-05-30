package com.jd.lib.cashier.sdk.core.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

/* loaded from: classes14.dex */
public class g {
    public static boolean a(Context context) {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        try {
            if (Build.VERSION.SDK_INT >= 16 && (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) != null && accessibilityManager.isEnabled() && (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) != null && !enabledAccessibilityServiceList.isEmpty()) {
                if (accessibilityManager.isTouchExplorationEnabled()) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
