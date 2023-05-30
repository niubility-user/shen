package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.List;

/* loaded from: classes.dex */
public class b4 {
    public static b4 a = new b4();

    public static b4 c() {
        return a;
    }

    public final int a(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return i2 != 3 ? 0 : 512;
                }
                return 256;
            }
            return 128;
        }
        return 64;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int b(Context context) {
        boolean z;
        boolean z2;
        WifiManager wifiManager;
        boolean z3 = true;
        try {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        } catch (Throwable unused) {
        }
        if (wifiManager != null) {
            z = wifiManager.isWifiEnabled();
            if (Build.VERSION.SDK_INT >= 18) {
                if (wifiManager.isScanAlwaysAvailable()) {
                    z2 = true;
                    int i2 = z ? 0 : 2;
                    if (!z3) {
                        i2 += 8;
                    }
                    return !z2 ? i2 + 32 : i2;
                }
            }
            z2 = false;
            if (z) {
            }
            if (!z3) {
            }
            if (!z2) {
            }
        }
        z = false;
        z2 = false;
        z3 = false;
        if (z) {
        }
        if (!z3) {
        }
        if (!z2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    @SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int d(Context context) {
        int i2;
        boolean z;
        LocationManager locationManager;
        boolean isProviderEnabled;
        List<String> allProviders;
        if (context == null) {
            return -1;
        }
        boolean e2 = e(context);
        boolean z2 = false;
        try {
            locationManager = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        } catch (Exception unused) {
        }
        if (locationManager != null) {
            try {
                i2 = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
            } catch (Throwable unused2) {
                i2 = 0;
            }
            try {
                isProviderEnabled = locationManager.isProviderEnabled("gps");
                allProviders = locationManager.getAllProviders();
            } catch (Exception unused3) {
            }
            if (allProviders != null) {
                z = allProviders.contains("gps");
                z2 = isProviderEnabled;
                int b = b(context);
                if (!e2) {
                }
                if (!z2) {
                }
                if (!z) {
                }
                return b + a(i2);
            }
            z2 = isProviderEnabled;
            z = false;
            int b2 = b(context);
            if (!e2) {
                b2++;
            }
            if (!z2) {
                b2 += 4;
            }
            if (!z) {
                b2 += 16;
            }
            return b2 + a(i2);
        }
        i2 = 0;
        z = false;
        int b22 = b(context);
        if (!e2) {
        }
        if (!z2) {
        }
        if (!z) {
        }
        return b22 + a(i2);
    }

    public boolean e(Context context) {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        } catch (Exception unused) {
        }
        if (telephonyManager == null) {
            return false;
        }
        return telephonyManager.getSimState() == 5;
    }
}
