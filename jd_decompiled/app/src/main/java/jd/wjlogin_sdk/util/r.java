package jd.wjlogin_sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes.dex */
public class r {
    private static ConnectivityManager a;

    public static int a(Context context) {
        NetworkInfo activeNetworkInfo;
        TelephonyManager c2;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return -1;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return 0;
        }
        if (type != 0 || (c2 = c(context)) == null) {
            return -1;
        }
        switch (a(c2)) {
            case -1:
            case 0:
            default:
                return -1;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
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
            case 17:
                return 1;
            case 13:
            case 18:
                return 5;
        }
    }

    public static String b(Context context) {
        int a2 = a(context);
        return a2 != 5 ? a2 != -1 ? a2 != 0 ? a2 != 1 ? a2 != 2 ? "wifi" : "2g" : "3g" : "wifi" : "UNKNOWN" : "4g";
    }

    public static TelephonyManager c(Context context) {
        try {
            return (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        if (a == null) {
            if (context == null) {
                return true;
            }
            a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        ConnectivityManager connectivityManager = a;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) ? false : true;
    }

    public static int a(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return -1;
        }
        try {
            return BaseInfo.getNetworkTypeInt();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
