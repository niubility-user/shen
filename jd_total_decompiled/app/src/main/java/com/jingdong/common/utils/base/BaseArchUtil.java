package com.jingdong.common.utils.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class BaseArchUtil {
    static final String EVENT_ID_STARTUP = "Startup_Source";
    public static final String MTA_KEY_PAGE_ROLLBACK = "ma_is_rollback";
    private static final String TAG = "BaseArchUtil";

    private static void appendIntentMsg(JSONObject jSONObject, Intent intent, boolean z) {
        String str = "selectorBundleExtra";
        String str2 = "selectorIntentData";
        String str3 = "";
        try {
            if (intent == null) {
                if (!z) {
                    jSONObject.put("intentStr", "");
                }
                if (!z) {
                    str2 = "intentData";
                }
                jSONObject.put(str2, "");
                if (!z) {
                    str = "bundleExtra";
                }
                jSONObject.put(str, "");
                return;
            }
            if (!z) {
                jSONObject.put("intentStr", intent.toString());
            }
            Uri data = intent.getData();
            if (!z) {
                str2 = "intentData";
            }
            jSONObject.put(str2, data == null ? "" : Uri.decode(data.toString()));
            Bundle extras = intent.getExtras();
            if (!z) {
                str = "bundleExtra";
            }
            if (extras != null) {
                str3 = getBundleStr(intent.getExtras());
            }
            jSONObject.put(str, str3);
        } catch (Throwable unused) {
        }
    }

    private static String getBundleStr(Bundle bundle) {
        int i2;
        StringBuilder sb = new StringBuilder();
        if (bundle != null) {
            sb.append("{");
            for (String str : bundle.keySet()) {
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                Object obj = bundle.get(str);
                if (obj != null && (obj instanceof Bundle)) {
                    sb.append(getBundleStr((Bundle) obj));
                } else {
                    sb.append("\"");
                    sb.append(obj.toString());
                    sb.append("\"");
                }
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            int length = sb.length();
            if (length > 1 && sb.lastIndexOf(DYConstants.DY_REGEX_COMMA) == (i2 = length - 1)) {
                sb.setLength(i2);
            }
            sb.append("}");
        }
        return sb.toString();
    }

    public static String getReferer(Activity activity) {
        if (activity == null) {
            return "";
        }
        ComponentName callingActivity = activity.getCallingActivity();
        String packageName = callingActivity != null ? callingActivity.getPackageName() : "";
        if (TextUtils.isEmpty(packageName)) {
            try {
                Field declaredField = Activity.class.getDeclaredField("mReferrer");
                declaredField.setAccessible(true);
                packageName = (String) declaredField.get(activity);
            } catch (Throwable unused) {
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "getReferer()::" + packageName);
        }
        return packageName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0018, code lost:
        r0 = "1";
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (((com.jingdong.cleanmvp.ui.BaseFragment) r4).getManualResume() != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        if (((com.jingdong.common.BaseActivity) r4).getManualResume() != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r0 = "0";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getRollbackPVTag(Object obj) {
        String str = "";
        if (obj == null) {
            return "";
        }
        if (!(obj instanceof BaseActivity)) {
            if (obj instanceof BaseFragment) {
            }
        }
        OKLog.d(TAG, obj.toString() + " getRollbackPVTag::" + str);
        return str;
    }

    public static void sendStartUpMta(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            if (!"1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_PARAM_THIRD_START_UP, "enable", "1"))) {
                if (OKLog.D) {
                    OKLog.d(TAG, "sendStartUpMta mobile config disable,then return!");
                    return;
                }
                return;
            }
            String referer = getReferer(activity);
            if (BaseInfo.getAppPackageName().equals(referer)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "sendStartUpMta referer equals jd packagename,then return!");
                    return;
                }
                return;
            }
            String canonicalName = activity.getClass().getCanonicalName();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("packagename", referer);
            jSONObject.put("page_name", canonicalName);
            Intent intent = activity.getIntent();
            appendIntentMsg(jSONObject, intent, false);
            appendIntentMsg(jSONObject, intent == null ? null : intent.getSelector(), true);
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), EVENT_ID_STARTUP, "", "", canonicalName, "", jSONObject.toString(), null);
            if (OKLog.D) {
                OKLog.d(TAG, "sendStartUpMta successfully send Startup_Source mta!");
            }
        } catch (Throwable unused) {
        }
    }
}
