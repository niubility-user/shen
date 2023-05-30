package c.t.m.g;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b2 {
    public static volatile String a = "209";
    public static volatile String b = "fc_sdk";

    /* renamed from: c  reason: collision with root package name */
    public static final Comparator<Object> f316c = new a();

    /* loaded from: classes.dex */
    public static class a implements Comparator<Object> {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            ScanResult scanResult = (ScanResult) obj;
            ScanResult scanResult2 = (ScanResult) obj2;
            int i2 = Build.VERSION.SDK_INT >= 17 ? (int) (((scanResult2.timestamp / 1000) / 1000) - ((scanResult.timestamp / 1000) / 1000)) : 0;
            return i2 == 0 ? scanResult2.level - scanResult.level : i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v38 */
    public static String a(x xVar, Location location, List<ScanResult> list, List<y> list2, boolean z) {
        int size;
        StringBuilder sb;
        StringBuilder sb2;
        int i2;
        if (list != null) {
            try {
                size = list.size();
            } catch (Throwable unused) {
                return "";
            }
        } else {
            size = 0;
        }
        int size2 = list2 != null ? list2.size() : 0;
        if (xVar == null || location == null || (size == 0 && size2 == 0)) {
            return "";
        }
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder();
        StringBuilder sb6 = new StringBuilder("0,");
        Locale locale = Locale.ENGLISH;
        sb3.append(String.format(locale, "%.6f", Double.valueOf(location.getLatitude())));
        sb3.append(DYConstants.DY_REGEX_COMMA);
        sb3.append(String.format(locale, "%.6f", Double.valueOf(location.getLongitude())));
        sb3.append(DYConstants.DY_REGEX_COMMA);
        try {
            sb3.append((int) location.getAltitude());
            sb3.append(DYConstants.DY_REGEX_COMMA);
            sb3.append((int) location.getAccuracy());
            sb3.append(DYConstants.DY_REGEX_COMMA);
            sb3.append((int) location.getBearing());
            sb3.append(DYConstants.DY_REGEX_COMMA);
            sb3.append(String.format(locale, "%.1f", Float.valueOf(location.getSpeed())));
            sb3.append(DYConstants.DY_REGEX_COMMA);
            sb3.append(location.getTime());
            if (!t2.d(list2)) {
                int i3 = 0;
                while (i3 < list2.size()) {
                    y yVar = list2.get(i3);
                    sb4.append(i3 == 0 ? "" : ";");
                    sb4.append(yVar.a);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    sb4.append(yVar.b);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    sb4.append(yVar.f759c);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    StringBuilder sb7 = sb3;
                    sb4.append(yVar.f760e);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    sb4.append(yVar.d);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    if (i3 == 0) {
                        f2.a(z);
                        i2 = z;
                    } else {
                        i2 = -1;
                    }
                    sb4.append(i2);
                    sb4.append(DYConstants.DY_REGEX_COMMA);
                    sb4.append(yVar.f761f);
                    i3++;
                    sb3 = sb7;
                }
            }
            StringBuilder sb8 = sb3;
            if (list != null && list.size() > 0) {
                Object[] array = list.toArray();
                Arrays.sort(array, f316c);
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                int i4 = 0;
                while (i4 < array.length) {
                    ScanResult scanResult = (ScanResult) array[i4];
                    Object[] objArr = array;
                    sb5.append(i4 == 0 ? "" : ContainerUtils.FIELD_DELIMITER);
                    sb5.append(scanResult.BSSID.replaceAll(":", "").toLowerCase());
                    sb5.append(ContainerUtils.FIELD_DELIMITER);
                    sb5.append(scanResult.level);
                    StringBuilder sb9 = sb8;
                    if (Build.VERSION.SDK_INT >= 17) {
                        sb = sb4;
                        sb2 = sb5;
                        long j2 = scanResult.timestamp;
                        int i5 = j2 > 0 ? (int) (elapsedRealtime - ((j2 / 1000) / 1000)) : -1;
                        sb6.append(i4 == 0 ? "" : ContainerUtils.FIELD_DELIMITER);
                        sb6.append(i5);
                    } else {
                        sb = sb4;
                        sb2 = sb5;
                    }
                    i4++;
                    sb4 = sb;
                    sb5 = sb2;
                    sb8 = sb9;
                    array = objArr;
                }
            }
            StringBuilder sb10 = sb4;
            StringBuilder sb11 = sb8;
            StringBuilder sb12 = sb5;
            StringBuilder sb13 = new StringBuilder();
            String c2 = f3.c();
            if (c2 != null && c2.length() > 5) {
                JSONObject jSONObject = new JSONObject(c2);
                sb13.append(jSONObject.optString(Constant.KEY_MAC).replaceAll(":", "").toLowerCase());
                sb13.append(ContainerUtils.FIELD_DELIMITER);
                sb13.append(jSONObject.optString("ssid"));
                sb13.append(ContainerUtils.FIELD_DELIMITER);
                sb13.append(jSONObject.optString("rssi"));
                if (sb13.length() < 5) {
                    sb13.setLength(0);
                }
            }
            String str = xVar.c().replaceAll("[| _]", "") + CartConstant.KEY_YB_INFO_LINK + xVar.d();
            String str2 = xVar.e().replaceAll("[| _]", "") + CartConstant.KEY_YB_INFO_LINK + xVar.f().replaceAll("[| _]", "");
            StringBuilder sb14 = new StringBuilder();
            SimpleDateFormat c3 = w4.c("yyyy-MM-dd HH:mm:ss");
            sb14.append("[");
            sb14.append(c3.format(new Date()));
            sb14.append("]:");
            sb14.append(xVar.g());
            sb14.append("|");
            sb14.append(a);
            sb14.append("|");
            sb14.append(xVar.a());
            sb14.append("|");
            sb14.append(a2.d ? x2.a() : "");
            sb14.append("|");
            sb14.append(a2.d ? x2.b() : "");
            sb14.append("|||||||");
            sb14.append((CharSequence) sb11);
            sb14.append("||");
            sb14.append((CharSequence) sb10);
            sb14.append("|");
            sb14.append((CharSequence) sb12);
            sb14.append("||||||||||||||||");
            sb14.append(b);
            sb14.append("||||");
            sb14.append((CharSequence) sb13);
            sb14.append("||");
            sb14.append(a2.d ? x2.c().replaceAll(":", "").toLowerCase() : "");
            sb14.append("|");
            sb14.append(str);
            sb14.append("|||");
            sb14.append(str2);
            sb14.append("||||||||||||");
            sb14.append(sb6.toString());
            sb14.append("|");
            return sb14.toString();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static void b(String str) {
        b = str;
    }

    public static void c(String str) {
        a = str;
    }
}
