package c.t.m.g;

import android.annotation.SuppressLint;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import c.t.m.g.f;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class s5 {
    public static boolean a;

    public static int a(CellLocation cellLocation) {
        if (cellLocation instanceof CdmaCellLocation) {
            return ((CdmaCellLocation) cellLocation).getBaseStationId();
        }
        try {
            return ((GsmCellLocation) cellLocation).getCid();
        } catch (Exception unused) {
            return -1;
        }
    }

    @SuppressLint({"NewApi"})
    public static List<CellInfo> b(y4 y4Var) {
        try {
            List<CellInfo> allCellInfo = y4Var.l().getAllCellInfo();
            if (allCellInfo != null) {
                return allCellInfo;
            }
        } catch (Throwable unused) {
        }
        return new ArrayList();
    }

    public static void c(y4 y4Var, TelephonyManager telephonyManager, CellLocation cellLocation, long j2) {
        if (telephonyManager != null) {
            try {
                boolean z = false;
                if (telephonyManager.getSimState() == 5) {
                    boolean g2 = g(y4Var.a);
                    if (cellLocation == null && j2 < 0 && !g2) {
                        z = true;
                    }
                    a = z;
                }
                new StringBuilder("is cell permission denied ").append(a);
            } catch (Exception unused) {
                a = true;
            }
        }
    }

    public static boolean d(int i2) {
        return i2 == f.a.CDMA.ordinal();
    }

    public static boolean e(int i2, int i3, int i4, int i5, long j2) {
        int i6;
        int i7;
        return d(i2) ? i3 >= 0 && i4 >= 0 && i5 > 0 && i5 != Integer.MAX_VALUE && j2 > 0 && j2 < 65535 : (i3 < 0 || i4 < 0 || i5 < 0 || i5 == Integer.MAX_VALUE || j2 == 268435455 || j2 == 2147483647L || j2 == 50594049 || j2 == 65535 || j2 <= 0 || i6 == 0 || i7 <= 0) ? false : true;
    }

    public static boolean f(int i2, SignalStrength signalStrength, SignalStrength signalStrength2) {
        if (signalStrength == null || signalStrength2 == null) {
            return true;
        }
        int abs = Math.abs(j(i2, signalStrength, signalStrength2));
        return l(i2) ? abs > 3 : d(i2) && abs > 6;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x001d, code lost:
        if (android.provider.Settings.System.getInt(r5.getContentResolver(), "airplane_mode_on") == 1) goto L28;
     */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean g(android.content.Context r5) {
        /*
            r0 = 0
            r1 = 1
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L20
            r3 = 17
            java.lang.String r4 = "airplane_mode_on"
            if (r2 < r3) goto L15
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L20
            int r5 = android.provider.Settings.Global.getInt(r5, r4)     // Catch: java.lang.Throwable -> L20
            if (r5 != r1) goto L20
            goto L1f
        L15:
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L20
            int r5 = android.provider.Settings.System.getInt(r5, r4)     // Catch: java.lang.Throwable -> L20
            if (r5 != r1) goto L20
        L1f:
            r0 = 1
        L20:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.s5.g(android.content.Context):boolean");
    }

    public static boolean h(CellLocation cellLocation, CellLocation cellLocation2) {
        if (!o.f(cellLocation, cellLocation2) && cellLocation.getClass() == cellLocation2.getClass()) {
            return cellLocation instanceof GsmCellLocation ? ((GsmCellLocation) cellLocation).getCid() == ((GsmCellLocation) cellLocation2).getCid() : (cellLocation instanceof CdmaCellLocation) && ((CdmaCellLocation) cellLocation).getBaseStationId() == ((CdmaCellLocation) cellLocation2).getBaseStationId();
        }
        return false;
    }

    public static boolean i(f fVar) {
        if (o.b(fVar)) {
            return false;
        }
        return e(fVar.a.ordinal(), fVar.b, fVar.f404c, fVar.d, fVar.f406f);
    }

    public static int j(int i2, SignalStrength signalStrength, SignalStrength signalStrength2) {
        try {
            if (l(i2)) {
                return signalStrength.getGsmSignalStrength() - signalStrength2.getGsmSignalStrength();
            }
            if (d(i2)) {
                return signalStrength.getCdmaDbm() - signalStrength2.getCdmaDbm();
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static CellLocation k(y4 y4Var) {
        TelephonyManager l2 = y4Var.l();
        if (l2 != null) {
            try {
                return l2.getCellLocation();
            } catch (Exception unused) {
            }
        }
        return CellLocation.getEmpty();
    }

    public static boolean l(int i2) {
        return i2 != f.a.CDMA.ordinal();
    }
}
