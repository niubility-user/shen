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
public class p6 {
    public static p6 a;

    public static p6 b() {
        if (a == null) {
            a = new p6();
        }
        return a;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:5|6|7|(18:9|11|12|(2:14|(14:16|17|18|19|(13:49|50|52|53|(1:55)(2:57|58)|56|23|(1:25)|(1:27)|(1:29)|(1:31)|(1:33)|(1:(2:36|(2:38|(1:42)(2:40|41))(2:43|44))(2:45|46))(2:47|48))|21|22|23|(0)|(0)|(0)|(0)|(0)|(0)(0)))|65|17|18|19|(0)|21|22|23|(0)|(0)|(0)|(0)|(0)|(0)(0))|68|67|18|19|(0)|21|22|23|(0)|(0)|(0)|(0)|(0)|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int a(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        int i2;
        boolean z4;
        LocationManager locationManager;
        WifiManager wifiManager;
        if (context == null) {
            return -1;
        }
        boolean c2 = c(context);
        boolean z5 = false;
        try {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        } catch (Throwable unused) {
        }
        if (wifiManager != null) {
            z = wifiManager.isWifiEnabled();
            if (Build.VERSION.SDK_INT >= 18) {
                if (wifiManager.isScanAlwaysAvailable()) {
                    z2 = true;
                    z3 = true;
                    locationManager = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                    if (locationManager != null) {
                        try {
                            i2 = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
                        } catch (Throwable unused2) {
                            i2 = 0;
                        }
                        try {
                            boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
                            List<String> allProviders = locationManager.getAllProviders();
                            if (allProviders != null) {
                                z5 = allProviders.contains("gps");
                            }
                            z4 = z5;
                            z5 = isProviderEnabled;
                        } catch (Exception unused3) {
                        }
                        int i3 = !c2;
                        if (!z) {
                            i3 += 2;
                        }
                        if (!z5) {
                            i3 += 4;
                        }
                        if (!z3) {
                            i3 += 8;
                        }
                        if (!z4) {
                            i3 += 16;
                        }
                        if (!z2) {
                            i3 += 32;
                        }
                        return i2 == 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i3 : i3 + 512 : i3 + 256 : i3 + 128 : i3 + 64;
                    }
                    i2 = 0;
                    z4 = false;
                    int i32 = !c2;
                    if (!z) {
                    }
                    if (!z5) {
                    }
                    if (!z3) {
                    }
                    if (!z4) {
                    }
                    if (!z2) {
                    }
                    if (i2 == 0) {
                    }
                }
            }
            z2 = false;
            z3 = true;
            locationManager = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
            if (locationManager != null) {
            }
            i2 = 0;
            z4 = false;
            int i322 = !c2;
            if (!z) {
            }
            if (!z5) {
            }
            if (!z3) {
            }
            if (!z4) {
            }
            if (!z2) {
            }
            if (i2 == 0) {
            }
        }
        z = false;
        z2 = false;
        z3 = false;
        locationManager = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        if (locationManager != null) {
        }
        i2 = 0;
        z4 = false;
        int i3222 = !c2;
        if (!z) {
        }
        if (!z5) {
        }
        if (!z3) {
        }
        if (!z4) {
        }
        if (!z2) {
        }
        if (i2 == 0) {
        }
    }

    public boolean c(Context context) {
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
