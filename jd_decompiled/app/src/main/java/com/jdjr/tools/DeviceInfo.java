package com.jdjr.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.wangyin.platform.CryptoUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class DeviceInfo {
    private static final String TAG = "DeviceInfo";
    private static DeviceInfo instance;
    private static final Object lock = new Object();
    private final Context mContext;
    private final CryptoUtils mCryptoUtils;
    String mPackageName = null;
    String mLibVersion = null;
    String mDeviceInfo = null;
    String mAppVersionName = null;

    private DeviceInfo(Context context) {
        this.mContext = context;
        this.mCryptoUtils = CryptoUtils.newInstance(context);
    }

    public static void composeJson(JSONObject jSONObject, String... strArr) {
        try {
            if (strArr.length <= 0 || strArr.length % 2 != 0) {
                return;
            }
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                String str = strArr[i2];
                String str2 = strArr[i2 + 1];
                if (str2 != null && str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            }
        } catch (JSONException e2) {
            JDJRLog.e("DeviceInfo", "fillJSONIfValuesNotEmpty exception" + e2.getMessage());
        }
    }

    public static DeviceInfo newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DeviceInfo(context);
                }
            }
        }
        return instance;
    }

    public String getAppPackageName() {
        String str = this.mPackageName;
        if (str != null) {
            return str;
        }
        try {
            this.mPackageName = this.mContext.getPackageName();
        } catch (Exception unused) {
            this.mPackageName = "";
        }
        return this.mPackageName;
    }

    public String getAppVersionName() {
        String str = this.mAppVersionName;
        if (str != null) {
            return str;
        }
        try {
            this.mAppVersionName = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            JDJRLog.e("DeviceInfo", "Cannot get app version name");
        }
        return this.mAppVersionName;
    }

    public synchronized String getCurrentDID() {
        String str = this.mDeviceInfo;
        if (str != null) {
            return str;
        }
        CryptoUtils cryptoUtils = this.mCryptoUtils;
        if (cryptoUtils != null && cryptoUtils.getDeviceGUID() != null && this.mCryptoUtils.getDeviceGUID().length != 0) {
            String byte2hex = StringTools.byte2hex(this.mCryptoUtils.getDeviceGUID());
            this.mDeviceInfo = byte2hex;
            return byte2hex;
        }
        return null;
    }

    public String getDeviceType() {
        return BaseInfo.getDeviceModel();
    }

    public String getOS() {
        return "Android";
    }

    public String getOSVersion() {
        return BaseInfo.getAndroidVersion();
    }

    public String getSDKVersion() {
        String str = this.mLibVersion;
        if (str != null) {
            return str;
        }
        CryptoUtils cryptoUtils = this.mCryptoUtils;
        if (cryptoUtils == null) {
            return null;
        }
        String GetLibVersion = cryptoUtils.GetLibVersion();
        this.mLibVersion = GetLibVersion;
        return GetLibVersion;
    }
}
