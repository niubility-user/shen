package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.map.tools.net.NetUtil;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class b7 {
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static int D = 0;
    public static int E = 0;
    public static int F = 0;
    public static int G = 0;
    public static final int H = 1;
    public static final int I = 2;
    public static final int J = 3;
    public static int K = 0;
    public static int L = 0;
    public static int M = 0;
    public static int N = 0;
    public static int O = 0;
    private static final int P = 4000000;
    private static final int Q = 53500000;
    private static final int R = 73670000;
    private static final int S = 135100000;
    private static final int T = -85000000;
    private static final int U = 85000000;
    private static final int V = -180000000;
    private static final int W = 180000000;
    private static int X = 0;
    private static int Y = 0;
    private static float Z = 0.0f;
    public static final File a;
    private static final int a0 = 20;
    public static final File b;
    private static final double b0 = 6378137.0d;

    /* renamed from: c */
    public static final File f16292c;
    private static final double c0 = 4.007501668557849E7d;
    private static final String d = "Tencent";
    private static final double d0 = 0.017453292519943295d;

    /* renamed from: e */
    private static final String f16293e = "MapSDK";
    private static final double e0 = 2.68435456E8d;

    /* renamed from: f */
    private static final String f16294f = "Caches";

    /* renamed from: g */
    private static String f16295g = null;

    /* renamed from: h */
    private static String f16296h = null;

    /* renamed from: i */
    private static String f16297i = null;

    /* renamed from: j */
    private static b f16298j = null;

    /* renamed from: k */
    private static final String f16299k;

    /* renamed from: l */
    private static final String f16300l;

    /* renamed from: m */
    public static final String f16301m = "tencentmap/mapsdk_vector/";

    /* renamed from: n */
    private static String f16302n = null;
    private static String o = null;
    private static String p = null;
    private static String q = null;
    private static String r = null;
    private static String s = null;
    private static String t = null;
    private static int u = 0;
    private static String v = null;
    private static String w = null;
    private static float x = 0.0f;
    private static final int y = 100;
    public static final int z = -1;

    /* loaded from: classes9.dex */
    public static final class b {
        private final boolean a;

        private b(boolean z) {
            this.a = z;
        }

        public final String a() {
            return (b7.f16296h == null || b7.f16296h.equals(b7.f16295g)) ? b7.f16295g : b7.f16296h;
        }

        public final String b() {
            return this.a ? "" : b7.w;
        }

        public final String c() {
            return b7.o == null ? "" : b7.o;
        }

        public final float d() {
            return b7.Z;
        }

        public final float e() {
            return b7.x;
        }

        public final String f() {
            return (this.a || b7.v == null) ? "" : b7.v;
        }

        public final String g() {
            return (this.a || b7.r == null) ? "" : b7.r;
        }

        public final String h() {
            return (this.a || b7.q == null) ? "" : b7.q;
        }

        public final String i() {
            return b7.f16300l;
        }

        public final String j() {
            return b7.f16299k;
        }

        public final String k() {
            return (this.a || b7.s == null) ? "" : b7.s;
        }

        public final int l() {
            if (this.a) {
                return 0;
            }
            return b7.u;
        }

        public final String m() {
            return (b7.f16297i == null || TextUtils.isEmpty(b7.f16297i) || b7.f16297i.equals(b7.t)) ? b7.t == null ? "" : b7.t : b7.f16297i;
        }

        public final String n() {
            return (this.a || b7.p == null) ? "" : b7.p;
        }

        public final int o() {
            return b7.X;
        }

        public final String p() {
            return NetUtil.STR_UserAgent;
        }
    }

    static {
        File file = new File(Environment.getExternalStorageDirectory(), D());
        a = file;
        File file2 = new File(file, C());
        b = file2;
        f16292c = new File(file2, B());
        f16295g = null;
        f16296h = null;
        f16297i = null;
        String str = li.f16854j;
        f16299k = str;
        f16300l = str;
        f16302n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        t = null;
        u = 0;
        v = null;
        w = "undefined";
        x = 1.0f;
        D = 2;
        E = 0;
        F = 0;
        G = 0;
        K = 2;
        L = P;
        M = Q;
        N = R;
        O = S;
        X = 0;
        Y = 160;
        Z = 0.0f;
    }

    public static String A() {
        return J().h();
    }

    public static String B() {
        return f16294f;
    }

    public static String C() {
        return f16293e;
    }

    public static String D() {
        return d;
    }

    public static String E() {
        return J().i();
    }

    public static String F() {
        return J().j();
    }

    public static String G() {
        return J().k();
    }

    public static b H() {
        return new b(false);
    }

    public static int I() {
        return J().l();
    }

    private static b J() {
        b bVar = f16298j;
        return bVar != null ? bVar : new b(false);
    }

    public static String K() {
        return J().n();
    }

    public static int L() {
        return J().o();
    }

    public static String M() {
        return J().c();
    }

    public static String N() {
        return J().m();
    }

    public static String O() {
        return J().p();
    }

    public static void P() {
        f16298j = null;
        f16297i = null;
        f16296h = null;
    }

    public static double a(double d2, double d3) {
        return (d2 * 6.698324247899813d) / Math.cos(d3 * d0);
    }

    public static float a(int i2) {
        return i2 / 255.0f;
    }

    public static String a(String str, String str2) {
        String e2;
        if (e7.b(str)) {
            str = "";
        }
        if (e7.b(str2)) {
            str2 = "";
        }
        StringBuilder sb = new StringBuilder(256);
        sb.append("key=");
        sb.append(t());
        sb.append("&pid=");
        sb.append(N());
        sb.append("&key2=");
        sb.append(str);
        sb.append("&pid2=");
        sb.append(str2);
        sb.append("&psv=");
        sb.append(M());
        sb.append("&ver=");
        sb.append(E());
        sb.append("&pf=");
        sb.append(O());
        sb.append("&hm=");
        sb.append(y());
        sb.append("&suid=");
        sb.append(A());
        sb.append("&os=");
        sb.append(I());
        sb.append("&dip=");
        sb.append(K());
        sb.append("&nt=");
        sb.append(G());
        sb.append("&channel=1&output=json");
        if (!TextUtils.isEmpty(w)) {
            try {
                e2 = URLEncoder.encode(w, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                e2 = e(w);
            }
            sb.append("&cuid=");
            sb.append(e2);
            sb.append("&uuid=");
            sb.append(e2);
        }
        return sb.toString();
    }

    private static String a(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(Integer.toHexString(b2 & 255));
            sb.append(str);
        }
        return sb.toString();
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        X = displayMetrics.widthPixels * displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT < 24) {
            b(displayMetrics);
        } else {
            a(displayMetrics);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        fa.f16514e = f16292c;
        f16296h = str;
        f16297i = str2;
        w = str3;
        if (X == 0) {
            a(context);
        }
        if (v == null) {
            try {
                String c2 = f7.c();
                v = c2;
                String b2 = b(c2);
                v = b2;
                v = URLEncoder.encode(b2, "utf-8");
            } catch (Exception unused) {
            }
        }
        if (u == 0) {
            u = Build.VERSION.SDK_INT;
        }
        if (t == null) {
            try {
                String packageName = context.getPackageName();
                t = packageName;
                String b3 = b(packageName);
                t = b3;
                t = URLEncoder.encode(b3, "utf-8");
            } catch (Exception unused2) {
            }
        }
        if (f16302n == null) {
            try {
                String a2 = f7.a(context);
                f16302n = a2;
                String b4 = b(a2);
                f16302n = b4;
                f16302n = URLEncoder.encode(b4, "utf-8");
            } catch (Exception unused3) {
            }
        }
        if (o == null) {
            try {
                String b5 = f7.b(context);
                o = b5;
                String b6 = b(b5);
                o = b6;
                o = URLEncoder.encode(b6, "utf-8");
            } catch (Exception unused4) {
            }
        }
        if (p == null) {
            try {
                String g2 = f7.g(context);
                p = g2;
                String b7 = b(g2);
                p = b7;
                p = URLEncoder.encode(b7, "utf-8");
            } catch (Exception unused5) {
            }
        }
        if (q == null) {
            try {
                String h2 = f7.h(context);
                q = h2;
                String b8 = b(h2);
                q = b8;
                q = URLEncoder.encode(b8, "utf-8");
            } catch (Exception unused6) {
            }
        }
        if (TextUtils.isEmpty(r)) {
            try {
                String e2 = f7.e(context);
                r = e2;
                String b9 = b(e2);
                r = b9;
                r = URLEncoder.encode(b9, "utf-8");
            } catch (Exception unused7) {
            }
        }
        if (s == null) {
            try {
                String netTypeStr = NetUtil.getNetTypeStr(context);
                s = netTypeStr;
                String b10 = b(netTypeStr);
                s = b10;
                s = URLEncoder.encode(b10, "utf-8");
            } catch (Exception unused8) {
            }
        }
        if (f16295g == null) {
            try {
                String a3 = f7.a(context, "TencentMapSDK");
                f16295g = a3;
                f16295g = URLEncoder.encode(a3, "utf-8");
            } catch (Exception unused9) {
            }
        }
        if (x == 1.0f) {
            x = 320.0f / context.getResources().getDisplayMetrics().densityDpi;
        }
        Z = context.getResources().getDisplayMetrics().density;
    }

    private static void a(DisplayMetrics displayMetrics) {
        Y = displayMetrics.densityDpi;
        r();
    }

    public static void a(boolean z2) {
        f16298j = new b(z2);
    }

    public static boolean a(Context context, byte[] bArr, String str) {
        File fileStreamPath = context.getFileStreamPath("tecentmap");
        if (!fileStreamPath.exists()) {
            fileStreamPath.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(fileStreamPath.getPath());
        sb.append("/");
        sb.append(str);
        return ga.a(bArr, sb.toString(), false) != 0;
    }

    public static byte[] a(Context context, String str) {
        InputStream c2 = ga.c(context.getFileStreamPath("tecentmap").getPath() + "/" + str);
        byte[] b2 = ga.b(c2);
        ga.a((Closeable) c2);
        return b2;
    }

    public static String[] a(String str) {
        String[] strArr = new String[3];
        try {
            JSONObject jSONObject = new JSONObject(str);
            strArr[0] = jSONObject.optString("version");
            strArr[1] = jSONObject.optString("data");
            strArr[2] = jSONObject.optString("otherDistrict");
        } catch (JSONException unused) {
        }
        return strArr;
    }

    public static int b(String str, String str2) {
        if (e7.b(str2)) {
            return 1;
        }
        if (e7.b(str)) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length;
        if (length > split2.length) {
            length = split2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            String str3 = split2[i2];
            String str4 = split[i2];
            if (TextUtils.isEmpty(str3)) {
                str3 = "0";
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = "0";
            }
            if (Integer.valueOf(str3).intValue() < Integer.valueOf(str4).intValue()) {
                return 1;
            }
            if (Integer.valueOf(str3).intValue() > Integer.valueOf(str4).intValue()) {
                return -1;
            }
        }
        if (split.length > split2.length) {
            return 1;
        }
        return split.length == split2.length ? 0 : -1;
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            return URLEncoder.encode((applicationInfo != null ? applicationInfo.loadLabel(packageManager) : "can't find app name").toString(), "utf-8");
        } catch (Exception unused) {
            return "can't find app name";
        }
    }

    private static String b(String str) {
        return str == null ? "" : str.replace(ContainerUtils.FIELD_DELIMITER, "").replace("#", "").replace("?", "");
    }

    private static void b(DisplayMetrics displayMetrics) {
        Field field;
        try {
            field = displayMetrics.getClass().getField("densityDpi");
        } catch (NoSuchFieldException | SecurityException unused) {
            field = null;
        }
        if (field == null) {
            s();
            return;
        }
        try {
            Y = field.getInt(displayMetrics);
            r();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        }
    }

    public static void b(boolean z2) {
        int i2;
        if (z2) {
            N = R;
            O = S;
            M = Q;
            i2 = P;
        } else {
            N = -180000000;
            O = 180000000;
            M = 85000000;
            i2 = -85000000;
        }
        L = i2;
    }

    public static String c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest(), "");
        } catch (Exception unused) {
            return str;
        }
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder(256);
        if (!TextUtils.isEmpty(q)) {
            sb.append("&suid=");
            sb.append(A());
        }
        if (!TextUtils.isEmpty(r)) {
            sb.append("&duid=");
            sb.append(z());
        }
        if (!TextUtils.isEmpty(t)) {
            sb.append("&appid=");
            sb.append(N());
        }
        if (!TextUtils.isEmpty(f16299k)) {
            sb.append("&sdkver=");
            sb.append(F());
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append("&ui=");
            sb.append(str);
        }
        if (!TextUtils.isEmpty(w)) {
            sb.append("&appsuid=");
            try {
                sb.append(URLEncoder.encode(w, "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
                sb.append(e(w));
            }
            sb.append("&cuid=");
            sb.append(w);
        }
        sb.append("&api_key=" + t());
        return sb.toString();
    }

    private static String e(String str) {
        return Pattern.compile("[^a-zA-Z0-9]").matcher(str).replaceAll("").trim();
    }

    private static void r() {
        int i2;
        int i3 = Y;
        if (i3 <= 120) {
            i2 = 1;
        } else if (i3 <= 160) {
            i2 = 2;
        } else if (i3 > 240) {
            s();
            return;
        } else {
            i2 = 3;
        }
        K = i2;
    }

    private static void s() {
        int i2 = X;
        K = i2 > 153600 ? 3 : i2 < 153600 ? 1 : 2;
    }

    public static String t() {
        return J().a();
    }

    public static String u() {
        return J().b();
    }

    public static Date v() {
        return Calendar.getInstance().getTime();
    }

    public static float w() {
        return J().d();
    }

    public static float x() {
        return J().e();
    }

    public static String y() {
        return J().f();
    }

    public static String z() {
        return J().g();
    }
}
