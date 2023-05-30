package c.t.m.g;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;

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
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int a(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "gps"
            if (r10 != 0) goto L6
            r10 = -1
            return r10
        L6:
            boolean r1 = r9.c(r10)
            r2 = 1
            r3 = 0
            java.lang.String r4 = "wifi"
            java.lang.Object r4 = r10.getSystemService(r4)     // Catch: java.lang.Throwable -> L2c
            android.net.wifi.WifiManager r4 = (android.net.wifi.WifiManager) r4     // Catch: java.lang.Throwable -> L2c
            if (r4 == 0) goto L2c
            boolean r5 = r4.isWifiEnabled()     // Catch: java.lang.Throwable -> L2c
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L2d
            r7 = 18
            if (r6 < r7) goto L29
            boolean r4 = r4.isScanAlwaysAvailable()     // Catch: java.lang.Throwable -> L2d
            if (r4 == 0) goto L29
            r4 = 1
            goto L2a
        L29:
            r4 = 0
        L2a:
            r6 = 1
            goto L2f
        L2c:
            r5 = 0
        L2d:
            r4 = 0
            r6 = 0
        L2f:
            java.lang.String r7 = "location"
            java.lang.Object r7 = r10.getSystemService(r7)     // Catch: java.lang.Exception -> L58
            android.location.LocationManager r7 = (android.location.LocationManager) r7     // Catch: java.lang.Exception -> L58
            if (r7 == 0) goto L58
            android.content.ContentResolver r10 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L44
            java.lang.String r8 = "location_mode"
            int r10 = android.provider.Settings.Secure.getInt(r10, r8)     // Catch: java.lang.Throwable -> L44
            goto L45
        L44:
            r10 = 0
        L45:
            boolean r8 = r7.isProviderEnabled(r0)     // Catch: java.lang.Exception -> L59
            java.util.List r7 = r7.getAllProviders()     // Catch: java.lang.Exception -> L59
            if (r7 != 0) goto L50
            goto L55
        L50:
            boolean r0 = r7.contains(r0)     // Catch: java.lang.Exception -> L59
            r3 = r0
        L55:
            r0 = r3
            r3 = r8
            goto L5a
        L58:
            r10 = 0
        L59:
            r0 = 0
        L5a:
            r1 = r1 ^ r2
            if (r5 != 0) goto L5f
            int r1 = r1 + 2
        L5f:
            if (r3 != 0) goto L63
            int r1 = r1 + 4
        L63:
            if (r6 != 0) goto L67
            int r1 = r1 + 8
        L67:
            if (r0 != 0) goto L6b
            int r1 = r1 + 16
        L6b:
            if (r4 != 0) goto L6f
            int r1 = r1 + 32
        L6f:
            if (r10 == 0) goto L83
            if (r10 == r2) goto L80
            r0 = 2
            if (r10 == r0) goto L7d
            r0 = 3
            if (r10 == r0) goto L7a
            goto L85
        L7a:
            int r1 = r1 + 512
            goto L85
        L7d:
            int r1 = r1 + 256
            goto L85
        L80:
            int r1 = r1 + 128
            goto L85
        L83:
            int r1 = r1 + 64
        L85:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.p6.a(android.content.Context):int");
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
