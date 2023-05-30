package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes.dex */
public class g4 {
    public static WifiInfo a() {
        return b(y3.a());
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static WifiInfo b(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) y3.a().getApplicationContext().getSystemService("wifi");
            ConnectivityManager connectivityManager = context == null ? null : (ConnectivityManager) context.getSystemService("connectivity");
            if (wifiManager != null && connectivityManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (connectionInfo != null && networkInfo != null && networkInfo.isConnected()) {
                    if (c(BaseInfo.getWifiBSSID())) {
                        return connectionInfo;
                    }
                    return null;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static boolean c(String str) {
        return (str == null || str.equals(Constant.DEFAULT_BALANCE) || str.equals("00-00-00-00-00-00") || str.equals("00:00:00:00:00:00")) ? false : true;
    }
}
