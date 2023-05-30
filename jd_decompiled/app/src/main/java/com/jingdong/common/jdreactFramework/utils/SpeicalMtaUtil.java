package com.jingdong.common.jdreactFramework.utils;

import android.content.pm.PackageManager;
import android.os.Build;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;
import com.meizu.cloud.pushsdk.notification.model.NotificationStyle;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SpeicalMtaUtil {
    private static final String TAG = "SpeicalMtaUtil";
    private static final String baseUrl = "https://knicks.jd.com/log/server?t=ares_special_data&v=";

    private static String getVersionName() {
        String str;
        try {
            str = JDReactHelper.newInstance().getApplication().getPackageManager().getPackageInfo(JDReactHelper.newInstance().getApplication().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            str = "";
        }
        JLog.d(TAG, "getVersionName " + str);
        return str;
    }

    public static void sendSpecialMta(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ac", NetConfig.sAppCode);
            jSONObject.put("ver", getVersionName());
            jSONObject.put("ov", Build.VERSION.RELEASE);
            jSONObject.put(BrightRemindSetting.BRIGHT_REMIND, BaseInfo.getDeviceBrand());
            jSONObject.put("mn", str2);
            jSONObject.put("mv", str3);
            jSONObject.put(NotificationStyle.EXPANDABLE_IMAGE_URL, str);
            jSONObject.put("os", "android");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str4 = baseUrl + jSONObject.toString();
        JLog.d(TAG, "url = " + str4);
        try {
            new OkHttpClient().newCall(new Request.Builder().url(str4).build()).enqueue(new Callback() { // from class: com.jingdong.common.jdreactFramework.utils.SpeicalMtaUtil.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    JLog.d(SpeicalMtaUtil.TAG, "sendSpecialMta onFailure");
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    JLog.d(SpeicalMtaUtil.TAG, "sendSpecialMta onResponse");
                }
            });
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
