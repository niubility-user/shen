package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class z3 {
    public static volatile boolean a = false;
    public static volatile String b = "";

    /* renamed from: c  reason: collision with root package name */
    public static volatile String f798c = "";
    public static volatile String d = "";

    /* renamed from: e  reason: collision with root package name */
    public static volatile boolean f799e = true;

    /* renamed from: f  reason: collision with root package name */
    public static final byte[] f800f = new byte[0];

    /* renamed from: g  reason: collision with root package name */
    public static HashMap<String, Object> f801g = new HashMap<>();

    @Deprecated
    public static String a() {
        return "";
    }

    @Deprecated
    public static String b(Context context) {
        try {
            CharSequence loadLabel = context.getApplicationInfo().loadLabel(context.getPackageManager());
            String replaceAll = loadLabel == null ? "UNKNOWN" : loadLabel.toString().replaceAll(CartConstant.KEY_YB_INFO_LINK, "");
            String replaceAll2 = context.getPackageName().replaceAll(CartConstant.KEY_YB_INFO_LINK, "");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
            return replaceAll + CartConstant.KEY_YB_INFO_LINK + replaceAll2 + CartConstant.KEY_YB_INFO_LINK + packageInfo.versionCode + CartConstant.KEY_YB_INFO_LINK + packageInfo.versionName;
        } catch (Throwable unused) {
            return "UNKNOWN AppInfo";
        }
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "0123456789ABCDEF" : str;
    }

    public static synchronized void d(boolean z) {
        synchronized (z3.class) {
            a = z;
        }
    }

    public static synchronized Object e(String str) {
        Object obj;
        synchronized (z3.class) {
            obj = f801g.get(str);
            if (obj == null && (obj = y3.a().getSystemService(str)) != null) {
                f801g.put(str, obj);
            }
        }
        return obj;
    }

    public static String f() {
        return b(y3.a());
    }

    @Deprecated
    public static String g(Context context) {
        try {
            return context.getPackageName();
        } catch (Throwable unused) {
            return "UNKNOWN";
        }
    }

    public static String h() {
        Context a2 = y3.a();
        CharSequence loadLabel = a2.getApplicationInfo().loadLabel(a2.getPackageManager());
        return loadLabel == null ? "UNKNOWN" : loadLabel.toString();
    }

    public static String i() {
        return g(y3.a());
    }

    public static String j() {
        Context a2 = y3.a();
        try {
            return a2.getPackageManager().getPackageInfo(a2.getPackageName(), 16384).versionName;
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static String k() {
        StringBuilder sb;
        String l2 = l();
        try {
            if (TextUtils.isEmpty(d)) {
                String e2 = r3.e(r3.a(), "loc_build_model_encr", "");
                d = e2;
                if (TextUtils.isEmpty(e2)) {
                    String a2 = o1.a(l2, MessageDigestAlgorithms.MD5);
                    d = a2;
                    if (a2.length() > 8) {
                        d = d.substring(0, 8);
                    }
                    r3.g(r3.a(), "loc_build_model_encr", d);
                }
            }
        } catch (Exception unused) {
        }
        if (f799e) {
            f799e = false;
            sb = new StringBuilder();
            sb.append(l2);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
        } else {
            sb = new StringBuilder(CartConstant.KEY_YB_INFO_LINK);
        }
        sb.append(d);
        return sb.toString();
    }

    public static String l() {
        t();
        if (TextUtils.isEmpty(f798c)) {
            String e2 = r3.e(r3.a(), "loc_build_model", "");
            f798c = e2;
            if (TextUtils.isEmpty(e2)) {
                f798c = BaseInfo.getDeviceModel();
                r3.g(r3.a(), "loc_build_model", f798c);
            }
        }
        return f798c;
    }

    @Deprecated
    public static String m() {
        return "";
    }

    @Deprecated
    public static String n() {
        return "";
    }

    @Deprecated
    public static String o() {
        return "";
    }

    public static String p() {
        if (t2.c(b)) {
            synchronized (f800f) {
                String str = (String) r3.c(r3.a(), "loc_id_LocCommId", "");
                b = str;
                if (t2.c(str)) {
                    long nanoTime = System.nanoTime();
                    long currentTimeMillis = System.currentTimeMillis();
                    long j2 = (currentTimeMillis * 1000000) + (nanoTime % 1000000);
                    b = o1.a(String.valueOf(j2), MessageDigestAlgorithms.MD5);
                    StringBuilder sb = new StringBuilder("generate LocCommId : ");
                    sb.append(nanoTime);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    sb.append(currentTimeMillis);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    sb.append(j2);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    sb.append(b);
                    r3.f(r3.a(), "loc_id_LocCommId", b);
                }
            }
        }
        return b;
    }

    @Deprecated
    public static String q() {
        return "";
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String r() {
        return "";
    }

    public static String s() {
        try {
            String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
            return replaceAll.substring(0, 5) + replaceAll.substring(8, 10) + replaceAll.substring(14, 16) + replaceAll.substring(16, 18) + replaceAll.substring(20, 25);
        } catch (Exception unused) {
            return "".length() > 16 ? "".substring(0, 16) : "";
        }
    }

    public static synchronized void t() {
        synchronized (z3.class) {
        }
    }

    public static synchronized boolean u() {
        boolean z;
        synchronized (z3.class) {
            z = a;
        }
        return z;
    }
}
