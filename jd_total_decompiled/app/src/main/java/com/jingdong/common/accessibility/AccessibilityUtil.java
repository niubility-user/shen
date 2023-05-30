package com.jingdong.common.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class AccessibilityUtil {
    private static final String TALKBACK_BAOYI_ACTIVITY_NAME = "com.bjbyhd.voiceback.activity.BoyhoodSettingActivity";
    private static final String TALKBACK_SETTING_ACTIVITY_NAME = "com.android.talkback.TalkBackPreferencesActivity";

    public static boolean accessibilityEnable(Context context) {
        if (CheckAccessibilityService.buildVersionHeightJellyBean()) {
            try {
                Iterator<AccessibilityServiceInfo> it = ((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(1).iterator();
                while (it.hasNext()) {
                    String settingsActivityName = it.next().getSettingsActivityName();
                    if (!TextUtils.isEmpty(settingsActivityName) && (settingsActivityName.equals(TALKBACK_SETTING_ACTIVITY_NAME) || settingsActivityName.equals(TALKBACK_BAOYI_ACTIVITY_NAME))) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
                return false;
            }
        }
        return false;
    }

    public static JSONObject getRunningAccessibilityServiceInfo(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (CheckAccessibilityService.buildVersionHeightJellyBean()) {
            try {
                jSONObject.put("enable", accessibilityEnable(context) ? "1" : "0");
                Map runningAccessibilityServiceInfo = CheckAccessibilityService.getRunningAccessibilityServiceInfo(context);
                if (runningAccessibilityServiceInfo != null) {
                    for (Map.Entry entry : runningAccessibilityServiceInfo.entrySet()) {
                        if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                            jSONObject.put((String) entry.getKey(), entry.getValue());
                        }
                    }
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
        return jSONObject;
    }
}
