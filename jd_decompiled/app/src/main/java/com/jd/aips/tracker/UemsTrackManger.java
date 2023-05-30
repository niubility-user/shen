package com.jd.aips.tracker;

import android.content.Context;
import android.text.TextUtils;
import com.jd.aips.common.utils.EnvUtils;
import com.jd.aips.common.utils.FsBaseInfoUtils;
import com.jd.aips.tracker.UemsPolicyManager;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class UemsTrackManger {
    private static String a = "";
    private static JSONObject b;

    public static String a() {
        if (TextUtils.isEmpty(a)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("s_terminal", "android_mobile");
                jSONObject.put("s_system_version", FsBaseInfoUtils.getAndroidSDKVersion());
                jSONObject.put("s_model", FsBaseInfoUtils.getModel());
                jSONObject.put("s_manufacture", FsBaseInfoUtils.getManufacture());
                String[] supportedABIs = FsBaseInfoUtils.getSupportedABIs();
                jSONObject.put("s_ABIS", (supportedABIs == null || supportedABIs.length <= 0) ? "" : d.a(DYConstants.DY_REGEX_COMMA, supportedABIs));
                jSONObject.put("s_env", EnvUtils.getEnvType());
                a = jSONObject.toString();
            } catch (Exception unused) {
            }
        }
        return a;
    }

    public static void uploadTrack(Context context, String str, JSONObject jSONObject, String str2, String str3) {
        uploadTrack(context, str, jSONObject, str2, str3, null);
    }

    public static void uploadTrack(Context context, String str, JSONObject jSONObject, String str2, String str3, String str4) {
        UemsPolicyManager uemsPolicyManager;
        try {
            uemsPolicyManager = UemsPolicyManager.Builder.a;
            if (uemsPolicyManager.a()) {
                UemsTrackerUploader.b(context, str, jSONObject, str2, str3, str4);
            }
        } catch (Throwable unused) {
        }
    }

    public static JSONObject a(Context context) {
        if (b == null) {
            try {
                b = new JSONObject();
                String str = FsBaseInfoUtils.getBrand() + ":" + FsBaseInfoUtils.getModel();
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                b.put(CustomThemeConstance.NAVI_MODEL, str);
                b.put(HybridSDK.APP_VERSION, str2);
                b.put(HybridSDK.OS_VERSION, FsBaseInfoUtils.getAndroidVersion());
                JSONObject jSONObject = b;
                String[] supportedABIs = FsBaseInfoUtils.getSupportedABIs();
                jSONObject.put("cpuabi", (supportedABIs == null || supportedABIs.length <= 0) ? "" : d.a(DYConstants.DY_REGEX_COMMA, supportedABIs));
                return b;
            } catch (Exception unused) {
            }
        }
        return b;
    }
}
