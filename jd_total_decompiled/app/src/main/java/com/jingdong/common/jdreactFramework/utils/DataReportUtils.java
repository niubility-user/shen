package com.jingdong.common.jdreactFramework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes5.dex */
public class DataReportUtils {
    public static final int NETWORN_2G = 2;
    public static final int NETWORN_3G = 3;
    public static final int NETWORN_4G = 4;
    public static final int NETWORN_5G = 5;
    public static final int NETWORN_MOBILE = 6;
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknown";
        }
    }

    public static int getNetworkState(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        NetworkInfo.State state2;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (networkInfo != null && (state2 = networkInfo.getState()) != null && (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING)) {
                    return 1;
                }
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null) {
                    NetworkInfo.State state3 = networkInfo2.getState();
                    String subtypeName = networkInfo2.getSubtypeName();
                    if (state3 != null && (state3 == NetworkInfo.State.CONNECTED || state3 == NetworkInfo.State.CONNECTING)) {
                        int subtype = activeNetworkInfo.getSubtype();
                        if (subtype != 20) {
                            switch (subtype) {
                                case 1:
                                case 2:
                                case 4:
                                case 7:
                                case 11:
                                    return 2;
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                case 12:
                                case 14:
                                case 15:
                                    return 3;
                                case 13:
                                    return 4;
                                default:
                                    return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? 3 : 6;
                            }
                        }
                        return 5;
                    }
                }
                NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(9);
                if (networkInfo3 != null && (state = networkInfo3.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
                    return 6;
                }
            }
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static String getNetworkStateStr(Context context) {
        switch (getNetworkState(context)) {
            case 0:
                return "NAN";
            case 1:
                return "wifi";
            case 2:
                return "2G";
            case 3:
                return "3G";
            case 4:
                return "4G";
            case 5:
                return "5G";
            case 6:
                return "NETWORN_MOBILE";
            default:
                return "unknown";
        }
    }
}
