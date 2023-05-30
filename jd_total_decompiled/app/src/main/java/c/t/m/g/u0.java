package c.t.m.g;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.tencent.map.geolocation.util.SoUtils;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class u0 {
    public static volatile long a;
    public static volatile long b;

    public static double a(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static double b(double d, double d2, double d3, double d4) {
        double a2 = a(d);
        double a3 = a(d3);
        double round = Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((a2 - a3) / 2.0d), 2.0d) + (Math.cos(a2) * Math.cos(a3) * Math.pow(Math.sin((a(d2) - a(d4)) / 2.0d), 2.0d)))) * 2.0d * 6378.137d * 10000.0d);
        Double.isNaN(round);
        return (round / 10000.0d) * 1000.0d;
    }

    public static double c(double d, int i2) {
        try {
            if (Double.isNaN(d)) {
                return 0.0d;
            }
            return BigDecimal.valueOf(d).setScale(i2, RoundingMode.HALF_DOWN).doubleValue();
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static int d(char c2) {
        int i2 = (c2 < 'A' || c2 > 'Z') ? 256 : c2 - 'A';
        if (c2 >= 'a' && c2 <= 'z') {
            i2 = (c2 - 'a') + 64;
        }
        return (c2 < '0' || c2 > '9') ? i2 : (c2 + '\u0080') - 48;
    }

    public static String e(int i2, int i3, int i4, long j2, int i5, int i6, int i7, long j3) {
        StringBuilder sb = new StringBuilder("{\"mcc\":");
        sb.append(i2);
        sb.append(",\"mnc\":");
        sb.append(i3);
        sb.append(",\"lac\":");
        sb.append(i4);
        sb.append(",\"cellid\":");
        sb.append(j2);
        sb.append(",\"rss\":");
        sb.append(i5);
        if (i6 != Integer.MAX_VALUE && i7 != Integer.MAX_VALUE) {
            sb.append(",\"stationLat\":");
            sb.append(String.format("%.6f", Float.valueOf(i6 / 14400.0f)));
            sb.append(",\"stationLng\":");
            sb.append(String.format("%.6f", Float.valueOf(i7 / 14400.0f)));
        }
        sb.append(",\"ts\":");
        sb.append(j3);
        sb.append("}");
        return sb.toString();
    }

    public static String f(int i2, int i3, int i4, long j2, int i5, int i6, int i7, boolean z, int i8, long j3) {
        StringBuilder sb = new StringBuilder("{\"mcc\":");
        sb.append(i2);
        sb.append(",\"mnc\":");
        sb.append(i3);
        sb.append(",\"lac\":");
        sb.append(i4);
        sb.append(",\"cellid\":");
        sb.append(j2);
        sb.append(",\"rss\":");
        sb.append(i5);
        sb.append(",\"seed\":");
        sb.append(z ? 1 : 0);
        sb.append(",\"networktype\":");
        sb.append(i8);
        if (i6 != Integer.MAX_VALUE && i7 != Integer.MAX_VALUE) {
            sb.append(",\"stationLat\":");
            sb.append(String.format("%.6f", Float.valueOf(i6 / 14400.0f)));
            sb.append(",\"stationLng\":");
            sb.append(String.format("%.6f", Float.valueOf(i7 / 14400.0f)));
        }
        sb.append(",\"ts\":");
        sb.append(j3);
        sb.append("}");
        return sb.toString();
    }

    public static String g(f fVar, boolean z) {
        if (fVar == null) {
            return "[]";
        }
        int i2 = fVar.b;
        int i3 = fVar.f404c;
        int ordinal = fVar.a.ordinal();
        ArrayList arrayList = new ArrayList();
        fVar.n();
        long currentTimeMillis = System.currentTimeMillis();
        if (s5.e(ordinal, i2, i3, fVar.d, fVar.f406f)) {
            arrayList.add(f(i2, i3, fVar.d, fVar.f406f, fVar.f405e, fVar.f407g, fVar.f408h, z, ordinal, (currentTimeMillis - fVar.o()) / 1000));
        } else {
            j("illeagal main cell! ", i2, i3, ordinal, fVar.d, fVar.f406f);
        }
        try {
            for (f fVar2 : fVar.m()) {
                arrayList.add(e(fVar2.b, fVar2.f404c, fVar2.d, fVar2.f406f, fVar2.f405e, fVar2.f407g, fVar2.f408h, (currentTimeMillis - fVar2.o()) / 1000));
            }
        } catch (Throwable unused) {
        }
        return "[" + h6.e(DYConstants.DY_REGEX_COMMA).a(arrayList) + "]";
    }

    public static String h(n nVar) {
        if (nVar == null) {
            return "{}";
        }
        Location location = nVar.a;
        StringBuilder sb = new StringBuilder();
        double c2 = c(location.getLatitude(), 6);
        double c3 = c(location.getLongitude(), 6);
        double c4 = c(location.getAltitude(), 1);
        double c5 = c(location.getAccuracy(), 1);
        double c6 = c(location.getBearing(), 1);
        double c7 = c(location.getSpeed(), 1);
        sb.append("{");
        sb.append("\"latitude\":");
        sb.append(c2);
        sb.append(",\"longitude\":");
        sb.append(c3);
        sb.append(",\"additional\":");
        sb.append("\"" + c4 + DYConstants.DY_REGEX_COMMA + c5 + DYConstants.DY_REGEX_COMMA + c6 + DYConstants.DY_REGEX_COMMA + c7 + DYConstants.DY_REGEX_COMMA + nVar.b + "\"");
        sb.append(",\"source\":");
        sb.append(nVar.f551f.ordinal());
        sb.append("}");
        return sb.toString();
    }

    public static String i(b1 b1Var, boolean z) {
        long j2;
        List<ScanResult> a2 = b1Var == null ? null : b1Var.a();
        if (a2 == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        if (a2.size() <= 0) {
            sb.append("]");
        } else {
            int i2 = 0;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j3 = elapsedRealtime / 1000;
            Iterator<ScanResult> it = a2.iterator();
            long j4 = Long.MAX_VALUE;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ScanResult next = it.next();
                if (l(next, a2.size())) {
                    if (i2 > 0) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    sb.append("{\"mac\":\"");
                    sb.append(next.BSSID.replace(":", ""));
                    sb.append("\",");
                    sb.append("\"rssi\":");
                    sb.append(next.level);
                    if (Build.VERSION.SDK_INT < 17 || !z) {
                        j2 = 1000;
                    } else {
                        long j5 = next.timestamp;
                        int i3 = j5 > 0 ? (int) (j3 - ((j5 / 1000) / 1000)) : -1;
                        sb.append(",\"ts\":");
                        if (i3 >= 1000) {
                            i3 = 1000;
                        }
                        sb.append(i3);
                        j2 = 1000;
                        long j6 = elapsedRealtime - (next.timestamp / 1000);
                        if (j6 < j4) {
                            j4 = j6;
                        }
                    }
                    sb.append("}");
                    i2++;
                }
            }
            sb.append("]");
            a = j4 != Long.MAX_VALUE ? System.currentTimeMillis() - j4 : 0L;
        }
        return sb.toString();
    }

    public static void j(String str, int i2, int i3, int i4, int i5, long j2) {
        StringBuilder sb = new StringBuilder("getCellInfoWithJsonFormat: ");
        sb.append(str);
        sb.append("isGsm=");
        sb.append(i4 == 1);
        sb.append(", mcc,mnc=");
        sb.append(i2);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append(i3);
        sb.append(", lac,cid=");
        sb.append(i5);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append(j2);
    }

    public static boolean k(Location location, double[] dArr) {
        int latitude = (int) (location.getLatitude() * 1000000.0d);
        int longitude = (int) (location.getLongitude() * 1000000.0d);
        String a2 = c1.a("tencent_loc_lib");
        int i2 = 0;
        for (int i3 = 0; i3 < a2.length(); i3++) {
            i2 += d(a2.charAt(i3));
        }
        double[] dArr2 = new double[2];
        StringBuilder sb = new StringBuilder("defelect gps:");
        sb.append(latitude);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append(longitude);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append(i2);
        try {
            SoUtils.fun_b(latitude ^ i2, longitude ^ i2, dArr2);
        } catch (UnsatisfiedLinkError unused) {
        }
        dArr[0] = dArr2[0];
        dArr[1] = dArr2[1];
        StringBuilder sb2 = new StringBuilder("defelect:");
        sb2.append(dArr[0]);
        sb2.append(DYConstants.DY_REGEX_COMMA);
        sb2.append(dArr[1]);
        sb2.append(",pos:");
        sb2.append(dArr2[0]);
        sb2.append(DYConstants.DY_REGEX_COMMA);
        sb2.append(dArr2[1]);
        return true;
    }

    public static boolean l(ScanResult scanResult, int i2) {
        return true;
    }

    public static boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean contains = str.contains(PdLVBody.LATITUDE);
            JSONArray optJSONArray = jSONObject.optJSONArray("cells");
            int length = optJSONArray == null ? 0 : optJSONArray.length();
            JSONArray optJSONArray2 = jSONObject.optJSONArray("wifis");
            int length2 = optJSONArray2 == null ? 0 : optJSONArray2.length();
            StringBuilder sb = new StringBuilder("req gwc:");
            sb.append(contains ? "1" : "0");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(length2);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(length);
            o4.o("LOC", sb.toString());
            return contains || length > 0 || length2 > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static byte[] n(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr2;
        }
    }
}
