package c.t.m.g;

import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class v {

    /* renamed from: e  reason: collision with root package name */
    public static long f710e;
    public final b1 a;
    public final f b;

    /* renamed from: c  reason: collision with root package name */
    public final n f711c;
    public final List<w6> d;

    public v(b1 b1Var, f fVar, n nVar, List<w6> list) {
        this.a = b1Var;
        this.b = fVar;
        this.f711c = nVar;
        this.d = list;
    }

    @Nullable
    public b1 a() {
        return this.a;
    }

    public final b1 b(b1 b1Var) {
        ArrayList arrayList = new ArrayList(b1Var.a());
        Collections.sort(arrayList, k1.f509g);
        return new b1(arrayList, b1Var.d(), b1Var.e());
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0039 A[Catch: all -> 0x021f, TRY_ENTER, TryCatch #3 {all -> 0x021f, blocks: (B:5:0x000a, B:13:0x001c, B:16:0x002c, B:21:0x0039, B:22:0x003b, B:26:0x0057, B:29:0x005e, B:17:0x0031, B:8:0x0010, B:12:0x001a), top: B:74:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[Catch: all -> 0x021f, TRY_LEAVE, TryCatch #3 {all -> 0x021f, blocks: (B:5:0x000a, B:13:0x001c, B:16:0x002c, B:21:0x0039, B:22:0x003b, B:26:0x0057, B:29:0x005e, B:17:0x0031, B:8:0x0010, B:12:0x001a), top: B:74:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String c(int i2, int i3, String str, y4 y4Var, boolean z, boolean z2, boolean z3) {
        b1 e2;
        g5 c2;
        StringBuilder sb;
        if (y4Var == null) {
            return null;
        }
        try {
            f fVar = this.b;
            boolean z4 = true;
            if (fVar != null) {
                long j2 = f710e;
                long j3 = fVar.f406f;
                if (j2 == j3) {
                    z4 = false;
                }
                f710e = j3;
            }
            boolean i4 = y2.f().i("enable_wifi_native_sort");
            b1 b1Var = this.a;
            if (b1Var != null) {
                try {
                    e2 = i4 ? e(b1Var) : b(b1Var);
                } catch (Exception unused) {
                }
                if (e2 == null) {
                    e2 = this.a;
                }
                String i5 = u0.i(e2, i4);
                String g2 = u0.g(this.b, z4);
                String h2 = u0.h(this.f711c);
                String c3 = w6.c(this.d);
                if (z2) {
                    h2 = "{}";
                }
                c2 = y4Var.c();
                if (c2 != null) {
                    return null;
                }
                String u = c2.u();
                String a = c2.a();
                String s = c2.s();
                try {
                    int a2 = p6.b().a(y4Var.a);
                    String b = k1.b(y4Var);
                    try {
                        if (i5.equals("[]") && !b.equals("{}")) {
                            JSONObject jSONObject = new JSONObject(b);
                            if (Build.VERSION.SDK_INT >= 17 && i4) {
                                jSONObject.put("ts", 0);
                            }
                            jSONObject.remove("ssid");
                            i5 = "[" + jSONObject.toString() + "]";
                        }
                    } catch (Throwable unused2) {
                    }
                    String l2 = c2.l();
                    if (l2 != null) {
                        l2 = l2.replace("\"", "");
                    }
                    if (l2 != null) {
                        l2 = l2.replace("|", "");
                    }
                    String str2 = l2 + CartConstant.KEY_YB_INFO_LINK + c2.K();
                    int i6 = 203;
                    if (z3 && s != null) {
                        i6 = (Math.abs(s.hashCode()) % 1000) + 1001;
                    }
                    String str3 = "{\"version\":\"7.4.9.official_1\",\"address\":".concat(String.valueOf(i2)) + ",\"source\":" + i6 + ",\"access_token\":\"" + a + "\",\"lID\":\"" + f0.c("limei_prefs", "limei", "") + "\",\"device_id\":\"" + s + "\",\"app_name\":\"" + str + "\",\"app_label\":\"" + str2 + "\",\"bearing\":1";
                    if (i3 >= 0) {
                        str3 = str3 + ",\"control\":" + i3;
                    }
                    if (!TextUtils.isEmpty(c2.I())) {
                        str3 = str3 + ",\"smallappname\":\"" + c2.I() + "\"";
                    }
                    if (z) {
                        sb = new StringBuilder();
                        sb.append(str3);
                        sb.append(",\"detectgps\":1");
                    } else {
                        sb = new StringBuilder();
                        sb.append(str3);
                        sb.append(",\"detectgps\":0");
                    }
                    String str4 = ((sb.toString() + ",\"pstat\":" + a2) + ",\"wlan\":" + b) + ",\"attribute\":" + u + ",\"location\":" + h2 + ",\"cells\":" + g2 + ",\"wifis\":" + i5 + ",\"bles\":" + c3 + "}";
                    new StringBuilder("reqJson: ").append(str4);
                    return str4;
                } catch (Throwable unused3) {
                    return null;
                }
            }
            e2 = null;
            if (e2 == null) {
            }
            String i52 = u0.i(e2, i4);
            String g22 = u0.g(this.b, z4);
            String h22 = u0.h(this.f711c);
            String c32 = w6.c(this.d);
            if (z2) {
            }
            c2 = y4Var.c();
            if (c2 != null) {
            }
        } catch (Throwable unused4) {
            return null;
        }
    }

    public String d(int i2, String str, y4 y4Var, boolean z, boolean z2, boolean z3) {
        return c(i2, 0, str, y4Var, z, z2, z3);
    }

    @RequiresApi(api = 17)
    public final b1 e(b1 b1Var) {
        if (b1Var == null) {
            return null;
        }
        boolean z = Build.VERSION.SDK_INT >= 17;
        List<ScanResult> a = b1Var.a();
        if (a == null || a.size() == 0) {
            return null;
        }
        int[] iArr = new int[a.size()];
        int[] iArr2 = new int[a.size()];
        for (int i2 = 0; i2 < a.size(); i2++) {
            iArr[i2] = a.get(i2).level;
            iArr2[i2] = z ? (int) ((SystemClock.elapsedRealtime() / 1000) - ((a.get(i2).timestamp / 1000) / 1000)) : 0;
        }
        int[] fun_s = SoUtils.fun_s(iArr, iArr2, a.size(), z);
        ArrayList arrayList = new ArrayList();
        for (int i3 : fun_s) {
            arrayList.add(a.get(i3));
        }
        return new b1(arrayList, b1Var.d(), b1Var.e());
    }

    public boolean f() {
        return this.f711c != null;
    }
}
