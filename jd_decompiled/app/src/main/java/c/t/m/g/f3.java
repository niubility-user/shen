package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class f3 {
    public static HashMap a = new HashMap();

    public static int a(Context context) {
        try {
            return b((WifiManager) context.getSystemService("wifi"));
        } catch (Throwable unused) {
            return 4;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static int b(WifiManager wifiManager) {
        if (wifiManager != null) {
            try {
            } catch (Throwable unused) {
                return 4;
            }
        }
        return wifiManager.getWifiState();
    }

    @SuppressLint({"MissingPermission"})
    public static String c() {
        String wifiBSSID;
        int rssi;
        try {
            WifiInfo a2 = g4.a();
            if (a2 != null && (wifiBSSID = BaseInfo.getWifiBSSID()) != null && !wifiBSSID.equals(Constant.DEFAULT_BALANCE) && !wifiBSSID.equals("00-00-00-00-00-00") && !wifiBSSID.equals("00:00:00:00:00:00") && (rssi = a2.getRssi()) >= -100 && rssi <= -20) {
                return "{\"mac\":\"" + wifiBSSID + "\",\"rssi\":" + rssi + ",\"ssid\":\"" + BaseInfo.getWifiSSID().replace("\"", "").replace("|", "") + "\"}";
            }
        } catch (Throwable unused) {
        }
        return "{}";
    }

    public static boolean d(List<ScanResult> list) {
        if (list != null && list.size() > 1) {
            String str = list.get(0).BSSID;
            for (int i2 = 1; i2 < list.size(); i2++) {
                if (!str.equals(list.get(i2).BSSID)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static synchronized boolean e(List<ScanResult> list, List<ScanResult> list2) {
        synchronized (f3.class) {
            if (list == list2) {
                return true;
            }
            int size = list == null ? 0 : list.size();
            if (size != (list2 == null ? 0 : list2.size())) {
                return false;
            }
            if (size == 0) {
                return true;
            }
            try {
                HashMap hashMap = a;
                hashMap.clear();
                for (ScanResult scanResult : list) {
                    hashMap.put(scanResult.BSSID, Integer.valueOf(scanResult.level));
                }
                for (ScanResult scanResult2 : list2) {
                    if (!hashMap.containsKey(scanResult2.BSSID) || scanResult2.level != ((Integer) hashMap.get(scanResult2.BSSID)).intValue()) {
                        return false;
                    }
                }
            } catch (Throwable unused) {
            }
            return true;
        }
    }
}
