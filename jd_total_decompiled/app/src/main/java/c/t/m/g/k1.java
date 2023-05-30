package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.data.Constant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class k1 {
    public static boolean a;
    public static long b;

    /* renamed from: c */
    public static boolean f506c;
    public static volatile boolean d;

    /* renamed from: e */
    public static List<ScanResult> f507e;

    /* renamed from: f */
    public static long f508f;

    /* renamed from: g */
    public static final Comparator<ScanResult> f509g = new a();

    /* loaded from: classes.dex */
    public static class a implements Comparator<ScanResult> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public final int compare(ScanResult scanResult, ScanResult scanResult2) {
            return scanResult2.level - scanResult.level;
        }
    }

    public static int a(WifiManager wifiManager) {
        if (wifiManager != null) {
            try {
                return wifiManager.getWifiState();
            } catch (Throwable unused) {
            }
        }
        return 4;
    }

    public static String b(y4 y4Var) {
        String wifiBSSID;
        Context context = y4Var.a;
        if (context == null) {
            return "{}";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (wifiManager != null && connectivityManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (connectionInfo != null && networkInfo != null && networkInfo.isConnected() && (wifiBSSID = BaseInfo.getWifiBSSID()) != null && !wifiBSSID.equals(Constant.DEFAULT_BALANCE) && !wifiBSSID.equals("00-00-00-00-00-00") && !wifiBSSID.equals("00:00:00:00:00:00") && !wifiBSSID.equals("02:00:00:00:00:00")) {
                    int rssi = connectionInfo.getRssi();
                    String replace = BaseInfo.getWifiSSID().replace("\"", "").replace("|", "");
                    return "{\"mac\":\"" + wifiBSSID.replace(":", "") + "\",\"rssi\":" + rssi + ",\"ssid\":\"" + replace + "\"}";
                }
            }
        } catch (Exception unused) {
        }
        return "{}";
    }

    public static synchronized List<ScanResult> c(WifiManager wifiManager, boolean z) {
        List<ScanResult> list;
        synchronized (k1.class) {
            if (z) {
                list = null;
            } else {
                list = System.currentTimeMillis() - f508f < 20000 ? f507e : null;
                if (list != null) {
                    new StringBuilder("getScanResultsQuietly, cached wifi size: ").append(list.size());
                    return list;
                }
            }
            f507e = null;
            f508f = 0L;
            if (wifiManager != null) {
                try {
                    list = wifiManager.getScanResults();
                    f507e = list;
                    f508f = System.currentTimeMillis();
                    new StringBuilder("getScanResults systemapi wifi size:").append(list.size());
                    a = false;
                } catch (Exception unused) {
                    a = true;
                    o4.o("WIFI", "denied");
                }
            }
            if (list == null) {
                list = Collections.emptyList();
            }
            return list;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x0049 -> B:90:0x004e). Please submit an issue!!! */
    public static synchronized boolean d(WifiManager wifiManager) {
        boolean z;
        synchronized (k1.class) {
            z = false;
            if (wifiManager != null) {
                try {
                    new StringBuilder("is Single request: ").append(d);
                    if (d || System.currentTimeMillis() - b <= 3000) {
                        z = f506c;
                    } else {
                        z = wifiManager.startScan();
                        f506c = z;
                        b = System.currentTimeMillis();
                        new StringBuilder("startScan systemapi ").append(z ? "success" : JDReactConstant.FAILED);
                    }
                } catch (Exception unused) {
                    a = true;
                }
            }
        }
        return z;
    }

    public static boolean e(y4 y4Var) {
        try {
            WifiManager n2 = y4Var.n();
            if (n2 != null) {
                if (n2.isWifiEnabled()) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean f(y4 y4Var) {
        WifiManager n2 = y4Var.n();
        if (n2 != null) {
            try {
                int i2 = Build.VERSION.SDK_INT;
                if (i2 < 23 || Settings.Secure.getInt(y4Var.a.getContentResolver(), "location_mode") != 0) {
                    boolean isWifiEnabled = n2.isWifiEnabled();
                    return (isWifiEnabled || i2 < 18) ? isWifiEnabled : n2.isScanAlwaysAvailable();
                }
                return false;
            } catch (Throwable th) {
                if (th instanceof SecurityException) {
                    a = true;
                    return false;
                }
                return false;
            }
        }
        return false;
    }
}
