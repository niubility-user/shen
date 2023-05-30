package com.jdcn.fido.utils;

import android.content.Context;
import android.os.Build;
import com.jd.libs.hybrid.HybridSDK;
import com.jdcn.fido.BuildConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class DeviceInfo {
    private String osVersion;
    private String sdkVersion;

    public static String getDeviceInfo(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.SDK_INT);
                jSONObject.put(l4.f16791e, BuildConfig.fidoVersionName);
            } catch (Throwable unused) {
            }
        }
        return jSONObject.toString();
    }

    public static String getModel() {
        try {
            String deviceModel = BaseInfo.getDeviceModel();
            return deviceModel == null ? "" : deviceModel;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }
}
