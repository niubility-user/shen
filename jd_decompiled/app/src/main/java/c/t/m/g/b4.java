package c.t.m.g;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int b(android.content.Context r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = "wifi"
            java.lang.Object r6 = r6.getSystemService(r2)     // Catch: java.lang.Throwable -> L21
            android.net.wifi.WifiManager r6 = (android.net.wifi.WifiManager) r6     // Catch: java.lang.Throwable -> L21
            if (r6 == 0) goto L21
            boolean r2 = r6.isWifiEnabled()     // Catch: java.lang.Throwable -> L21
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L22
            r4 = 18
            if (r3 < r4) goto L1f
            boolean r6 = r6.isScanAlwaysAvailable()     // Catch: java.lang.Throwable -> L22
            if (r6 == 0) goto L1f
            r6 = 1
            goto L24
        L1f:
            r6 = 0
            goto L24
        L21:
            r2 = 0
        L22:
            r6 = 0
            r0 = 0
        L24:
            if (r2 != 0) goto L27
            r1 = 2
        L27:
            if (r0 != 0) goto L2b
            int r1 = r1 + 8
        L2b:
            if (r6 != 0) goto L2f
            int r1 = r1 + 32
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.b4.b(android.content.Context):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int d(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "gps"
            if (r7 != 0) goto L6
            r7 = -1
            return r7
        L6:
            boolean r1 = r6.e(r7)
            r2 = 0
            java.lang.String r3 = "location"
            java.lang.Object r3 = r7.getSystemService(r3)     // Catch: java.lang.Exception -> L34
            android.location.LocationManager r3 = (android.location.LocationManager) r3     // Catch: java.lang.Exception -> L34
            if (r3 == 0) goto L34
            android.content.ContentResolver r4 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L20
            java.lang.String r5 = "location_mode"
            int r4 = android.provider.Settings.Secure.getInt(r4, r5)     // Catch: java.lang.Throwable -> L20
            goto L21
        L20:
            r4 = 0
        L21:
            boolean r5 = r3.isProviderEnabled(r0)     // Catch: java.lang.Exception -> L35
            java.util.List r3 = r3.getAllProviders()     // Catch: java.lang.Exception -> L35
            if (r3 != 0) goto L2d
            r2 = r5
            goto L35
        L2d:
            boolean r2 = r3.contains(r0)     // Catch: java.lang.Exception -> L35
            r0 = r2
            r2 = r5
            goto L36
        L34:
            r4 = 0
        L35:
            r0 = 0
        L36:
            int r7 = r6.b(r7)
            if (r1 != 0) goto L3e
            int r7 = r7 + 1
        L3e:
            if (r2 != 0) goto L42
            int r7 = r7 + 4
        L42:
            if (r0 != 0) goto L46
            int r7 = r7 + 16
        L46:
            int r0 = r6.a(r4)
            int r7 = r7 + r0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.b4.d(android.content.Context):int");
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
