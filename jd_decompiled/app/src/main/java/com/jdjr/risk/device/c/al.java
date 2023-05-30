package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes18.dex */
public class al {
    public static String a() {
        try {
            return BaseInfo.getGateway() + "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context) {
        try {
            String wifiBSSID = BaseInfo.getWifiBSSID(context);
            return wifiBSSID == null ? "" : wifiBSSID;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        try {
            return BaseInfo.getNetmask() + "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            String wifiSSID = BaseInfo.getWifiSSID(context);
            return wifiSSID == null ? "" : wifiSSID;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int c(Context context) {
        try {
            return BaseInfo.getWifiRssi(context);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String c() {
        try {
            return BaseInfo.getNetworkType();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d(Context context) {
        try {
            List<String> wifiSSIDList = BaseInfo.getWifiSSIDList(context);
            if (wifiSSIDList == null || wifiSSIDList.size() <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (String str : wifiSSIDList) {
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int e(Context context) {
        try {
            return BaseInfo.getWifiLinkSpeed(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String f(Context context) {
        try {
            return BaseInfo.getNetworkOperatorName(context);
        } catch (Throwable unused) {
            return "";
        }
    }
}
