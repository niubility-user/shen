package com.jingdong.jdma.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes12.dex */
public class h {
    private static int a(Context context) {
        ConnectivityManager connectivityManager;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e2) {
            e2.printStackTrace();
            connectivityManager = null;
        }
        if (connectivityManager != null && b(connectivityManager)) {
            return a(connectivityManager);
        }
        return 0;
    }

    public static String b(Context context) {
        return c(context);
    }

    @SuppressLint({"MissingPermission"})
    private static String c(Context context) {
        int networkTypeInt;
        int a = a(context);
        if (a == 1) {
            return "wifi";
        }
        if (a == 2) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
            try {
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 24) {
                    networkTypeInt = telephonyManager.getDataNetworkType();
                } else {
                    networkTypeInt = BaseInfo.getNetworkTypeInt();
                }
                return (4 == networkTypeInt || 1 == networkTypeInt || 2 == networkTypeInt || 7 == networkTypeInt || 11 == networkTypeInt) ? "2g" : 13 == networkTypeInt ? "4g" : (i2 < 29 || networkTypeInt != 20) ? (3 == networkTypeInt || 5 == networkTypeInt || 6 == networkTypeInt || 8 == networkTypeInt || 9 == networkTypeInt || 10 == networkTypeInt || 12 == networkTypeInt || 14 == networkTypeInt || 15 == networkTypeInt) ? "3g" : "UNKNOW" : "5g";
            } catch (Throwable th) {
                th.printStackTrace();
                return "UNKNOW";
            }
        }
        return "UNKNOW";
    }

    private static boolean b(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return false;
        }
        Network[] networkArr = null;
        NetworkInfo[] networkInfoArr = null;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                networkArr = connectivityManager.getAllNetworks();
            } catch (Throwable unused) {
            }
            if (networkArr != null) {
                for (Network network : networkArr) {
                    try {
                        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                        if (networkInfo != null && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                            return true;
                        }
                    } catch (Throwable unused2) {
                    }
                }
            }
        } else {
            try {
                networkInfoArr = connectivityManager.getAllNetworkInfo();
            } catch (Throwable unused3) {
            }
            if (networkInfoArr != null) {
                for (NetworkInfo networkInfo2 : networkInfoArr) {
                    if (networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int a(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return 0;
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = connectivityManager.getNetworkInfo(0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (networkInfo == null) {
            return 0;
        }
        NetworkInfo.State state = networkInfo.getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return 2;
        }
        try {
            networkInfo = connectivityManager.getNetworkInfo(1);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (networkInfo == null) {
            return 0;
        }
        NetworkInfo.State state2 = networkInfo.getState();
        return (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) ? 1 : 0;
    }
}
