package com.jdjr.risk.device.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class ab {
    public static String a() {
        try {
            return BaseInfo.getNetworkTypeForDeviceFinger();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) ? DYConstants.DY_FALSE : activeNetworkInfo.getType() == 1 ? DYConstants.DY_TRUE : DYConstants.DY_FALSE;
            } catch (Exception unused) {
                return DYConstants.DY_FALSE;
            }
        }
        return DYConstants.DY_FALSE;
    }

    public static String b() {
        try {
            return BaseInfo.getSimOperatorName();
        } catch (Exception unused) {
            return "";
        }
    }
}
