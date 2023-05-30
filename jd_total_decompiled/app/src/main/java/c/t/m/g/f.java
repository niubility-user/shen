package c.t.m.g;

import android.annotation.SuppressLint;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class f extends e0 {

    /* renamed from: n */
    public static volatile f f403n;
    public static volatile long o;
    public static volatile CellLocation p;

    /* renamed from: k */
    public List<NeighboringCellInfo> f411k;

    /* renamed from: l */
    public List<f> f412l;
    public a a = a.NONE;
    public int b = R2.attr.blendSrc;

    /* renamed from: c */
    public int f404c = 0;
    public int d = 0;

    /* renamed from: e */
    public int f405e = 0;

    /* renamed from: f */
    public long f406f = 0;

    /* renamed from: g */
    public int f407g = Integer.MAX_VALUE;

    /* renamed from: h */
    public int f408h = Integer.MAX_VALUE;

    /* renamed from: j */
    public boolean f410j = false;

    /* renamed from: i */
    public final long f409i = System.currentTimeMillis();

    /* renamed from: m */
    public List<String> f413m = new ArrayList();

    /* loaded from: classes.dex */
    public enum a {
        NONE,
        GSM,
        CDMA,
        WCDMA,
        LTE,
        NR,
        TEMP6,
        TEMP7,
        NOSIM
    }

    public static int a(int i2) {
        if (i2 < -140 || i2 > -40) {
            return -1;
        }
        return i2;
    }

    @SuppressLint({"InlinedApi"})
    public static int b(CellIdentityNr cellIdentityNr) {
        Integer num;
        if (cellIdentityNr == null) {
            return Integer.MAX_VALUE;
        }
        int tac = cellIdentityNr.getTac();
        if (tac < 0 || tac >= 65535) {
            try {
                Method method = cellIdentityNr.getClass().getMethod("getHwTac", new Class[0]);
                return (method == null || (num = (Integer) method.invoke(cellIdentityNr, new Object[0])) == null) ? tac : num.intValue();
            } catch (Throwable unused) {
                return tac;
            }
        }
        return tac;
    }

    public static synchronized f c() {
        synchronized (f.class) {
            if (System.currentTimeMillis() - o >= 29000 || f403n == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder("use cached cell Info , ");
            sb.append(System.currentTimeMillis() - o);
            sb.append(",29000");
            return f403n;
        }
    }

    public static f d(y4 y4Var) {
        f c2 = c();
        if (c2 != null) {
            return c2;
        }
        f g2 = g(y4Var, s5.b(y4Var));
        if (g2 == null || !g2.q()) {
            g2 = f(y4Var, s5.k(y4Var), null);
        }
        i(g2, System.currentTimeMillis());
        return g2;
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x0162  */
    @SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static f e(y4 y4Var, CellInfo cellInfo) {
        if (cellInfo == null || y4Var == null) {
            return null;
        }
        f c2 = c();
        if (c2 != null) {
            return c2;
        }
        TelephonyManager l2 = y4Var.l();
        f fVar = new f();
        int i2 = -88;
        if (cellInfo instanceof CellInfoCdma) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
            a aVar = a.CDMA;
            fVar.a = aVar;
            fVar.h(l2, aVar);
            fVar.f404c = cellIdentity.getSystemId();
            fVar.d = cellIdentity.getNetworkId();
            fVar.f406f = cellIdentity.getBasestationId();
            fVar.f407g = cellIdentity.getLatitude();
            fVar.f408h = cellIdentity.getLongitude();
            int dbm = cellInfoCdma.getCellSignalStrength().getDbm();
            if (dbm > -110 && dbm < -40) {
                i2 = dbm;
            }
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            fVar.a = a.GSM;
            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
            fVar.d = cellIdentity2.getLac();
            fVar.f406f = cellIdentity2.getCid();
            fVar.b = cellIdentity2.getMcc();
            fVar.f404c = cellIdentity2.getMnc();
            int dbm2 = cellInfoGsm.getCellSignalStrength().getDbm();
            if (dbm2 > -110 && dbm2 < -40) {
                i2 = dbm2;
            }
        } else if (cellInfo instanceof CellInfoWcdma) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            fVar.a = a.WCDMA;
            CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
            fVar.d = cellIdentity3.getLac();
            fVar.f406f = cellIdentity3.getCid();
            fVar.b = cellIdentity3.getMcc();
            fVar.f404c = cellIdentity3.getMnc();
            int dbm3 = cellInfoWcdma.getCellSignalStrength().getDbm();
            if (dbm3 > -110 && dbm3 < -40) {
                i2 = dbm3;
            }
        } else if (!(cellInfo instanceof CellInfoLte)) {
            if (cellInfo instanceof CellInfoNr) {
                CellInfoNr cellInfoNr = (CellInfoNr) cellInfo;
                fVar.a = a.NR;
                CellIdentityNr cellIdentityNr = (CellIdentityNr) cellInfoNr.getCellIdentity();
                try {
                    fVar.f404c = Integer.parseInt(cellIdentityNr.getMncString());
                    fVar.b = Integer.parseInt(cellIdentityNr.getMccString());
                } catch (Throwable unused) {
                }
                fVar.d = b(cellIdentityNr);
                fVar.f406f = cellIdentityNr.getNci();
                fVar.f405e = a(cellInfoNr.getCellSignalStrength().getDbm());
            }
            fVar.f410j = fVar.p();
            if (fVar.b == 460 && fVar.f404c == Integer.MAX_VALUE) {
                fVar.f404c = 0;
            }
            if (!p6.b().c(y4Var.a)) {
                fVar.a = a.NOSIM;
            }
            fVar.f413m.add(fVar.l());
            i(fVar, System.currentTimeMillis());
            return fVar;
        } else {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            fVar.a = a.LTE;
            CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
            fVar.d = cellIdentity4.getTac();
            fVar.f406f = cellIdentity4.getCi();
            fVar.b = cellIdentity4.getMcc();
            fVar.f404c = cellIdentity4.getMnc();
            int dbm4 = cellInfoLte.getCellSignalStrength().getDbm();
            if (dbm4 > -110 && dbm4 < -40) {
                i2 = dbm4;
            }
        }
        fVar.f405e = i2;
        fVar.f410j = fVar.p();
        if (fVar.b == 460) {
            fVar.f404c = 0;
        }
        if (!p6.b().c(y4Var.a)) {
        }
        fVar.f413m.add(fVar.l());
        i(fVar, System.currentTimeMillis());
        return fVar;
    }

    public static f f(y4 y4Var, CellLocation cellLocation, SignalStrength signalStrength) {
        int gsmSignalStrength;
        if (!y4Var.q() || cellLocation == null) {
            return null;
        }
        f c2 = c();
        if (c2 != null) {
            return c2;
        }
        TelephonyManager l2 = y4Var.l();
        f fVar = new f();
        try {
            if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                a aVar = a.CDMA;
                fVar.a = aVar;
                fVar.h(l2, aVar);
                fVar.f404c = cdmaCellLocation.getSystemId();
                fVar.d = cdmaCellLocation.getNetworkId();
                fVar.f406f = cdmaCellLocation.getBaseStationId();
                fVar.f407g = cdmaCellLocation.getBaseStationLatitude();
                fVar.f408h = cdmaCellLocation.getBaseStationLongitude();
                if (signalStrength != null) {
                    gsmSignalStrength = signalStrength.getCdmaDbm();
                    fVar.f405e = gsmSignalStrength;
                }
                fVar.f405e = -1;
            } else {
                a aVar2 = a.GSM;
                fVar.a = aVar2;
                fVar.h(l2, aVar2);
                fVar.d = ((GsmCellLocation) cellLocation).getLac();
                fVar.f406f = r2.getCid();
                if (signalStrength == null) {
                    fVar.f405e = -1;
                } else {
                    gsmSignalStrength = (signalStrength.getGsmSignalStrength() * 2) - 113;
                    fVar.f405e = gsmSignalStrength;
                }
            }
            s5.c(y4Var, l2, cellLocation, fVar.f406f);
        } catch (Throwable unused) {
        }
        if (fVar.p()) {
            fVar.f410j = true;
        }
        if (!p6.b().c(y4Var.a)) {
            fVar.a = a.NOSIM;
        }
        fVar.f413m.add(fVar.l());
        i(fVar, System.currentTimeMillis());
        return fVar;
    }

    @SuppressLint({"NewApi"})
    public static f g(y4 y4Var, List<CellInfo> list) {
        if (list == null || y4Var == null || list.size() == 0) {
            return new f();
        }
        f c2 = c();
        if (c2 != null) {
            return c2;
        }
        ArrayList arrayList = new ArrayList();
        f fVar = new f();
        boolean z = true;
        for (CellInfo cellInfo : list) {
            if (cellInfo != null && cellInfo.isRegistered()) {
                f e2 = e(y4Var, cellInfo);
                if (e2.p()) {
                    fVar.f413m.add(e2.l());
                    if (z) {
                        z = false;
                        e2.f410j = true;
                        fVar = e2;
                    } else {
                        arrayList.add(e2);
                    }
                } else {
                    o4.o("Cells", "invalid!" + e2.r());
                }
            }
        }
        fVar.f412l = arrayList;
        TelephonyManager l2 = y4Var.l();
        CellLocation k2 = s5.k(y4Var);
        p = k2;
        s5.c(y4Var, l2, k2, fVar.f406f);
        i(fVar, System.currentTimeMillis());
        return fVar;
    }

    public static synchronized void i(f fVar, long j2) {
        synchronized (f.class) {
            o = j2;
            f403n = fVar;
            if (j2 == 0) {
                p = null;
            }
            new StringBuilder("updateCellInfo,").append(o);
        }
    }

    public final void h(TelephonyManager telephonyManager, a aVar) {
        String networkOperator = BaseInfo.getNetworkOperator();
        int i2 = R2.attr.blendSrc;
        if (networkOperator != null && networkOperator.length() >= 5) {
            try {
                int parseInt = Integer.parseInt(networkOperator.substring(0, 3));
                try {
                    int parseInt2 = Integer.parseInt(networkOperator.substring(3, 5));
                    boolean z = parseInt == 460 && parseInt2 == 3;
                    if (z) {
                        try {
                            if (aVar != a.CDMA && networkOperator.length() == 11) {
                                Integer.parseInt(networkOperator.substring(9, 11));
                            }
                        } catch (Throwable th) {
                            th = th;
                            r1 = parseInt2;
                            i2 = parseInt;
                            StringBuilder sb = new StringBuilder();
                            sb.append(networkOperator);
                            sb.append(th.toString());
                            if (i2 > 0) {
                                return;
                            }
                            return;
                        }
                    }
                    r1 = z ? 0 : parseInt2;
                    i2 = parseInt;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (i2 > 0 || r1 < 0) {
            return;
        }
        this.b = i2;
        this.f404c = r1;
    }

    public synchronized void j(List<NeighboringCellInfo> list) {
        if (list != null) {
            this.f411k = Collections.unmodifiableList(list);
        } else {
            this.f411k = Collections.emptyList();
        }
    }

    public boolean k(long j2) {
        return System.currentTimeMillis() - this.f409i < j2;
    }

    public String l() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(this.f404c);
        sb.append(this.d);
        sb.append(this.f406f);
        return sb.toString();
    }

    public List<f> m() {
        if (this.f412l == null) {
            this.f412l = Collections.emptyList();
        }
        return this.f412l;
    }

    public synchronized List<NeighboringCellInfo> n() {
        if (this.f411k == null) {
            this.f411k = Collections.emptyList();
        }
        return this.f411k;
    }

    public long o() {
        return this.f409i;
    }

    public boolean p() {
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.a == a.CDMA) {
            int i6 = this.b;
            if (i6 >= 0 && (i2 = this.f404c) >= 0 && i6 != 535 && i2 != 535 && (i3 = this.d) >= 0 && i3 != 65535) {
                long j2 = this.f406f;
                if (j2 != 65535 && j2 > 0) {
                    return true;
                }
            }
            return false;
        }
        int i7 = this.b;
        if (i7 >= 0 && (i4 = this.f404c) >= 0 && i7 != 535 && i4 != 535 && (i5 = this.d) >= 0 && i5 != 65535 && i5 != 25840) {
            long j3 = this.f406f;
            if (j3 != 65535 && j3 != 268435455 && j3 != 2147483647L && j3 != 50594049 && j3 != 8 && j3 != 10 && j3 != 33 && j3 > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean q() {
        return this.f410j;
    }

    public String r() {
        return this.b + DYConstants.DY_REGEX_COMMA + this.f404c + DYConstants.DY_REGEX_COMMA + this.d + DYConstants.DY_REGEX_COMMA + this.f406f + DYConstants.DY_REGEX_COMMA + this.f405e;
    }

    public String toString() {
        return "TxCellInfo [PhoneType=" + this.a + ", MCC=" + this.b + ", MNC=" + this.f404c + ", LAC=" + this.d + ", CID=" + this.f406f + ", RSSI=" + this.f405e + ", LAT=" + this.f407g + ", LNG=" + this.f408h + ", mTime=" + this.f409i + "]";
    }
}
