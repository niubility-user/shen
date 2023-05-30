package com.jingdong.common.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class CheckAccessibilityService {
    public static final String ACCESSIBILITY_APP_NAME = "accessibilityAppName";
    public static final String ACCESSIBILITY_EVENT_TYPES = "accessibilityEventTypes";
    public static final String ACCESSIBILITY_FEEDBACK_TYPE = "accessibilityFeedbackType";
    public static final String ACCESSIBILITY_FLAGS = "accessibilityFlags";
    public static final String ACCESSIBILITY_SERVICE_ID = "accessibilityServiceId";
    public static final String CAN_REQUEST_TOUCH_EXPLORATION_MODE = "canRequestTouchExplorationMode";
    public static final String CAN_RETRIEVE_WINDOW_CONTENT = "canRetrieveWindowContent";
    public static final String NOTIFICATION_TIMEOUT = "notificationTimeout";

    public static boolean buildVersionHeightJellyBean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    private static String getAccessibilityServiceName(Context context, AccessibilityServiceInfo accessibilityServiceInfo) throws PackageManager.NameNotFoundException {
        if (context != null && accessibilityServiceInfo != null) {
            try {
                String id = accessibilityServiceInfo.getId();
                return TextUtils.isEmpty(id) ? "" : context.getPackageManager().getPackageInfo(id.substring(0, id.lastIndexOf("/")), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static String getEnableAccessibilityServiceAppName(Context context) throws PackageManager.NameNotFoundException {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        if (!buildVersionHeightJellyBean() || (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) == null || !accessibilityManager.isEnabled() || (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) == null || enabledAccessibilityServiceList.isEmpty()) {
            return null;
        }
        return getAccessibilityServiceName(context, enabledAccessibilityServiceList.get(0));
    }

    public static String[] getInstallAccessibilityServiceAppName(Context context) throws PackageManager.NameNotFoundException {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> installedAccessibilityServiceList;
        if (!buildVersionHeightJellyBean() || (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) == null || !accessibilityManager.isEnabled() || (installedAccessibilityServiceList = accessibilityManager.getInstalledAccessibilityServiceList()) == null || installedAccessibilityServiceList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[installedAccessibilityServiceList.size()];
        for (AccessibilityServiceInfo accessibilityServiceInfo : installedAccessibilityServiceList) {
            strArr[installedAccessibilityServiceList.indexOf(accessibilityServiceInfo)] = getAccessibilityServiceName(context, accessibilityServiceInfo);
        }
        return strArr;
    }

    public static Map getRunningAccessibilityServiceInfo(Context context) throws PackageManager.NameNotFoundException {
        if (buildVersionHeightJellyBean()) {
            HashMap hashMap = new HashMap();
            AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
            if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
                if (enabledAccessibilityServiceList != null && !enabledAccessibilityServiceList.isEmpty()) {
                    AccessibilityServiceInfo accessibilityServiceInfo = enabledAccessibilityServiceList.get(0);
                    hashMap.put("accessibilityServiceId", accessibilityServiceInfo.getId());
                    hashMap.put("accessibilityAppName", getAccessibilityServiceName(context, accessibilityServiceInfo));
                    hashMap.put("accessibilityEventTypes", Integer.toString(accessibilityServiceInfo.eventTypes));
                    hashMap.put("accessibilityFeedbackType", Integer.toString(accessibilityServiceInfo.feedbackType));
                    hashMap.put("accessibilityFlags", Integer.toString(accessibilityServiceInfo.flags));
                    hashMap.put("canRetrieveWindowContent", Boolean.toString(accessibilityServiceInfo.getCanRetrieveWindowContent()));
                    hashMap.put("notificationTimeout", Long.toString(accessibilityServiceInfo.notificationTimeout));
                    hashMap.put("canRequestTouchExplorationMode", Boolean.toString(accessibilityManager.isTouchExplorationEnabled()));
                    return hashMap;
                }
                hashMap.clear();
                return hashMap;
            }
            hashMap.clear();
            return hashMap;
        }
        return null;
    }

    public static boolean isEnableAccessibilityService(Context context) {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        return buildVersionHeightJellyBean() && (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) != null && accessibilityManager.isEnabled() && (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) != null && !enabledAccessibilityServiceList.isEmpty() && accessibilityManager.isTouchExplorationEnabled();
    }
}
