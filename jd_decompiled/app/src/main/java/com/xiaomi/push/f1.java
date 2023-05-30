package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class f1 {

    /* renamed from: h */
    protected static Context f18601h;

    /* renamed from: i */
    private static f1 f18602i;

    /* renamed from: j */
    private static a f18603j;

    /* renamed from: k */
    private static String f18604k;
    protected final Map<String, c1> a;
    private e1 b;

    /* renamed from: c */
    protected b f18606c;
    private String d;

    /* renamed from: e */
    private long f18607e;

    /* renamed from: f */
    private long f18608f;

    /* renamed from: g */
    protected static final Map<String, b1> f18600g = new HashMap();

    /* renamed from: l */
    protected static boolean f18605l = false;

    /* loaded from: classes11.dex */
    public interface a {
        f1 a(Context context, e1 e1Var, b bVar, String str);
    }

    /* loaded from: classes11.dex */
    public interface b {
        String a(String str);
    }

    public f1(Context context, e1 e1Var, b bVar, String str) {
        this(context, e1Var, bVar, str, null, null);
    }

    protected f1(Context context, e1 e1Var, b bVar, String str, String str2, String str3) {
        this.a = new HashMap();
        this.d = "0";
        this.f18607e = 0L;
        this.f18608f = 0L;
        this.f18606c = bVar;
        this.b = e1Var == null ? new g1(this) : e1Var;
        this.d = str;
        f18604k = str2 == null ? context.getPackageName() : str2;
        if (str3 != null) {
            return;
        }
        E();
    }

    private String D() {
        return "host_fallbacks";
    }

    private String E() {
        try {
            PackageInfo packageInfo = f18601h.getPackageManager().getPackageInfo(f18601h.getPackageName(), 16384);
            return packageInfo != null ? packageInfo.versionName : "0";
        } catch (Exception unused) {
            return "0";
        }
    }

    public static synchronized f1 c() {
        f1 f1Var;
        synchronized (f1.class) {
            f1Var = f18602i;
            if (f1Var == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
        }
        return f1Var;
    }

    public static String d() {
        NetworkInfo activeNetworkInfo;
        Context context = f18601h;
        if (context == null) {
            return "unknown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI-UNKNOWN";
            }
            return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    static String e(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i2 = 0; i2 < bytes.length; i2++) {
                byte b2 = bytes[i2];
                int i3 = b2 & 240;
                if (i3 != 240) {
                    bytes[i2] = (byte) (((b2 & 15) ^ ((byte) (((b2 >> 4) + length) & 15))) | i3);
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    private ArrayList<b1> g(ArrayList<String> arrayList) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        C();
        synchronized (this.a) {
            o();
            for (String str : this.a.keySet()) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        Map<String, b1> map = f18600g;
        synchronized (map) {
            for (Object obj : map.values().toArray()) {
                b1 b1Var = (b1) obj;
                if (!b1Var.u()) {
                    f18600g.remove(b1Var.d);
                }
            }
        }
        if (!arrayList.contains(r())) {
            arrayList.add(r());
        }
        ArrayList<b1> arrayList2 = new ArrayList<>(arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(null);
        }
        try {
            String str2 = j0.s(f18601h) ? "wifi" : "wap";
            String f2 = f(arrayList, str2, this.d, true);
            if (!TextUtils.isEmpty(f2)) {
                JSONObject jSONObject3 = new JSONObject(f2);
                g.j.a.a.a.c.y(f2);
                if ("OK".equalsIgnoreCase(jSONObject3.getString("S"))) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("R");
                    String string = jSONObject4.getString("province");
                    String string2 = jSONObject4.getString("city");
                    String string3 = jSONObject4.getString("isp");
                    String string4 = jSONObject4.getString("ip");
                    String string5 = jSONObject4.getString("country");
                    JSONObject jSONObject5 = jSONObject4.getJSONObject(str2);
                    g.j.a.a.a.c.B("get bucket: net=" + string3 + ", hosts=" + jSONObject5.toString());
                    int i3 = 0;
                    while (i3 < arrayList.size()) {
                        String str3 = arrayList.get(i3);
                        JSONArray optJSONArray = jSONObject5.optJSONArray(str3);
                        if (optJSONArray == null) {
                            g.j.a.a.a.c.o("no bucket found for " + str3);
                            jSONObject = jSONObject5;
                        } else {
                            b1 b1Var2 = new b1(str3);
                            int i4 = 0;
                            while (i4 < optJSONArray.length()) {
                                String string6 = optJSONArray.getString(i4);
                                if (TextUtils.isEmpty(string6)) {
                                    jSONObject2 = jSONObject5;
                                } else {
                                    jSONObject2 = jSONObject5;
                                    b1Var2.i(new k1(string6, optJSONArray.length() - i4));
                                }
                                i4++;
                                jSONObject5 = jSONObject2;
                            }
                            jSONObject = jSONObject5;
                            arrayList2.set(i3, b1Var2);
                            b1Var2.f18467i = string5;
                            b1Var2.f18463e = string;
                            b1Var2.f18465g = string3;
                            b1Var2.f18466h = string4;
                            b1Var2.f18464f = string2;
                            if (jSONObject4.has("stat-percent")) {
                                b1Var2.g(jSONObject4.getDouble("stat-percent"));
                            }
                            if (jSONObject4.has("stat-domain")) {
                                b1Var2.r(jSONObject4.getString("stat-domain"));
                            }
                            if (jSONObject4.has(RemoteMessageConst.TTL)) {
                                b1Var2.h(jSONObject4.getInt(RemoteMessageConst.TTL) * 1000);
                            }
                            l(b1Var2.b());
                        }
                        i3++;
                        jSONObject5 = jSONObject;
                    }
                    JSONObject optJSONObject = jSONObject4.optJSONObject("reserved");
                    if (optJSONObject != null) {
                        long j2 = Final.SEV_DAY;
                        if (jSONObject4.has("reserved-ttl")) {
                            j2 = jSONObject4.getInt("reserved-ttl") * 1000;
                        }
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(next);
                            if (optJSONArray2 == null) {
                                g.j.a.a.a.c.o("no bucket found for " + next);
                            } else {
                                b1 b1Var3 = new b1(next);
                                b1Var3.h(j2);
                                for (int i5 = 0; i5 < optJSONArray2.length(); i5++) {
                                    String string7 = optJSONArray2.getString(i5);
                                    if (!TextUtils.isEmpty(string7)) {
                                        b1Var3.i(new k1(string7, optJSONArray2.length() - i5));
                                    }
                                }
                                Map<String, b1> map2 = f18600g;
                                synchronized (map2) {
                                    if (this.b.a(next)) {
                                        map2.put(next, b1Var3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("failed to get bucket " + e2.getMessage());
        }
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            b1 b1Var4 = arrayList2.get(i6);
            if (b1Var4 != null) {
                m(arrayList.get(i6), b1Var4);
            }
        }
        w();
        return arrayList2;
    }

    public static synchronized void j(Context context, e1 e1Var, b bVar, String str, String str2, String str3) {
        synchronized (f1.class) {
            Context applicationContext = context.getApplicationContext();
            f18601h = applicationContext;
            if (applicationContext == null) {
                f18601h = context;
            }
            if (f18602i == null) {
                a aVar = f18603j;
                if (aVar == null) {
                    f18602i = new f1(context, e1Var, bVar, str, str2, str3);
                } else {
                    f18602i = aVar.a(context, e1Var, bVar, str);
                }
            }
        }
    }

    public static synchronized void k(a aVar) {
        synchronized (f1.class) {
            f18603j = aVar;
            f18602i = null;
        }
    }

    public static void n(String str, String str2) {
        Map<String, b1> map = f18600g;
        b1 b1Var = map.get(str);
        synchronized (map) {
            if (b1Var == null) {
                b1 b1Var2 = new b1(str);
                b1Var2.h(Final.SEV_DAY);
                b1Var2.j(str2);
                map.put(str, b1Var2);
            } else {
                b1Var.j(str2);
            }
        }
    }

    private byte[] p() {
        return o0.c(f18601h.getPackageName() + "_key_salt");
    }

    protected b1 A(String str) {
        if (System.currentTimeMillis() - this.f18608f > this.f18607e * 60 * 1000) {
            this.f18608f = System.currentTimeMillis();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(str);
            b1 b1Var = g(arrayList).get(0);
            if (b1Var != null) {
                this.f18607e = 0L;
                return b1Var;
            }
            long j2 = this.f18607e;
            if (j2 < 15) {
                this.f18607e = j2 + 1;
                return null;
            }
            return null;
        }
        return null;
    }

    protected String B() {
        if ("com.xiaomi.xmsf".equals(f18604k)) {
            return f18604k;
        }
        return f18604k + ":pushservice";
    }

    public void C() {
        String next;
        synchronized (this.a) {
            Iterator<c1> it = this.a.values().iterator();
            while (it.hasNext()) {
                it.next().g(true);
            }
            while (true) {
                for (boolean z = false; !z; z = true) {
                    Iterator<String> it2 = this.a.keySet().iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (this.a.get(next).d().isEmpty()) {
                            break;
                        }
                    }
                }
                this.a.remove(next);
            }
        }
    }

    public b1 a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty");
        }
        return b(new URL(str).getHost(), true);
    }

    public b1 b(String str, boolean z) {
        b1 A;
        g.j.a.a.a.c.A("HostManager", "-->getFallbacksByHost(): host=", str, ", fetchRemoteIfNeed=", Boolean.valueOf(z));
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        if (this.b.a(str)) {
            b1 u = u(str);
            return (u == null || !u.u()) ? (z && j0.p(f18601h) && (A = A(str)) != null) ? A : new h1(this, str, u) : u;
        }
        return null;
    }

    public String f(ArrayList<String> arrayList, String str, String str2, boolean z) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<i0> arrayList3 = new ArrayList();
        arrayList3.add(new g0("type", str));
        if (str.equals("wap")) {
            arrayList3.add(new g0("conpt", e(j0.g(f18601h))));
        }
        if (z) {
            arrayList3.add(new g0("reserved", "1"));
        }
        arrayList3.add(new g0("uuid", str2));
        arrayList3.add(new g0(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, p0.d(arrayList, DYConstants.DY_REGEX_COMMA)));
        arrayList3.add(new g0("countrycode", com.xiaomi.push.service.b.a(f18601h).f()));
        arrayList3.add(new g0("push_sdk_vc", String.valueOf(50300)));
        String r = r();
        b1 u = u(r);
        String format = String.format(Locale.US, "https://%1$s/gslb/?ver=5.0", r);
        if (u == null) {
            arrayList2.add(format);
            Map<String, b1> map = f18600g;
            synchronized (map) {
                b1 b1Var = map.get(r);
                if (b1Var != null) {
                    Iterator<String> it = b1Var.e(true).iterator();
                    while (it.hasNext()) {
                        arrayList2.add(String.format(Locale.US, "https://%1$s/gslb/?ver=5.0", it.next()));
                    }
                }
            }
        } else {
            arrayList2 = u.d(format);
        }
        Iterator<String> it2 = arrayList2.iterator();
        IOException e2 = null;
        while (it2.hasNext()) {
            Uri.Builder buildUpon = Uri.parse(it2.next()).buildUpon();
            for (i0 i0Var : arrayList3) {
                buildUpon.appendQueryParameter(i0Var.a(), i0Var.b());
            }
            try {
                b bVar = this.f18606c;
                return bVar == null ? j0.h(f18601h, new URL(buildUpon.toString())) : bVar.a(buildUpon.toString());
            } catch (IOException e3) {
                e2 = e3;
            }
        }
        if (e2 == null) {
            return null;
        }
        g.j.a.a.a.c.o("network exception: " + e2.getMessage());
        throw e2;
    }

    protected JSONObject h() {
        JSONObject jSONObject;
        synchronized (this.a) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            JSONArray jSONArray = new JSONArray();
            Iterator<c1> it = this.a.values().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().e());
            }
            jSONObject.put("data", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<b1> it2 = f18600g.values().iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().f());
            }
            jSONObject.put("reserved", jSONArray2);
        }
        return jSONObject;
    }

    public void i() {
        synchronized (this.a) {
            this.a.clear();
        }
    }

    public void l(String str) {
    }

    public void m(String str, b1 b1Var) {
        if (TextUtils.isEmpty(str) || b1Var == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + b1Var);
        } else if (this.b.a(str)) {
            synchronized (this.a) {
                o();
                if (this.a.containsKey(str)) {
                    this.a.get(str).f(b1Var);
                } else {
                    c1 c1Var = new c1(str);
                    c1Var.f(b1Var);
                    this.a.put(str, c1Var);
                }
            }
        }
    }

    protected boolean o() {
        synchronized (this.a) {
            if (f18605l) {
                return true;
            }
            f18605l = true;
            this.a.clear();
            String y = y();
            if (TextUtils.isEmpty(y)) {
                return false;
            }
            t(y);
            g.j.a.a.a.c.y("loading the new hosts succeed");
            return true;
        }
    }

    public b1 q(String str) {
        return b(str, true);
    }

    protected String r() {
        return "resolver.msg.xiaomi.net";
    }

    public void s() {
        ArrayList<String> arrayList;
        synchronized (this.a) {
            o();
            arrayList = new ArrayList<>(this.a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                c1 c1Var = this.a.get(arrayList.get(size));
                if (c1Var != null && c1Var.a() != null) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList<b1> g2 = g(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (g2.get(i2) != null) {
                m(arrayList.get(i2), g2.get(i2));
            }
        }
    }

    protected void t(String str) {
        synchronized (this.a) {
            this.a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    c1 c1Var = new c1();
                    c1Var.b(optJSONArray.getJSONObject(i2));
                    this.a.put(c1Var.c(), c1Var);
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i3);
                    String optString = jSONObject2.optString("host");
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            b1 b1Var = new b1(optString);
                            b1Var.a(jSONObject2);
                            f18600g.put(b1Var.d, b1Var);
                            g.j.a.a.a.c.o("load local reserved host for " + b1Var.d);
                        } catch (JSONException unused) {
                            g.j.a.a.a.c.o("parse reserved host fail.");
                        }
                    }
                }
            }
        }
    }

    protected b1 u(String str) {
        c1 c1Var;
        b1 a2;
        synchronized (this.a) {
            o();
            c1Var = this.a.get(str);
        }
        if (c1Var == null || (a2 = c1Var.a()) == null) {
            return null;
        }
        return a2;
    }

    public String v() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.a) {
            for (Map.Entry<String, c1> entry : this.a.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":\n");
                sb.append(entry.getValue().toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return sb.toString();
    }

    public void w() {
        FileOutputStream fileOutputStream;
        Closeable closeable;
        BufferedOutputStream bufferedOutputStream;
        Exception e2;
        synchronized (this.a) {
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                try {
                    String jSONObject = h().toString();
                    g.j.a.a.a.c.y("persist host fallbacks = " + jSONObject);
                    if (TextUtils.isEmpty(jSONObject)) {
                        fileOutputStream = null;
                    } else {
                        fileOutputStream = f18601h.openFileOutput(D(), 0);
                        try {
                            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                        } catch (Exception e3) {
                            e = e3;
                            bufferedOutputStream = null;
                            e2 = e;
                            g.j.a.a.a.c.o("persist bucket failure: " + e2.getMessage());
                            u9.b(bufferedOutputStream);
                            u9.b(fileOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            closeable = null;
                            th = th;
                            u9.b(closeable);
                            u9.b(fileOutputStream);
                            throw th;
                        }
                        try {
                            bufferedOutputStream.write(y5.c(p(), jSONObject.getBytes(StandardCharsets.UTF_8)));
                            bufferedOutputStream.flush();
                            bufferedOutputStream2 = bufferedOutputStream;
                        } catch (Exception e4) {
                            e2 = e4;
                            g.j.a.a.a.c.o("persist bucket failure: " + e2.getMessage());
                            u9.b(bufferedOutputStream);
                            u9.b(fileOutputStream);
                        }
                    }
                    u9.b(bufferedOutputStream2);
                } catch (Exception e5) {
                    e = e5;
                    fileOutputStream = null;
                    bufferedOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                    closeable = null;
                }
                u9.b(fileOutputStream);
            } catch (Throwable th3) {
                th = th3;
                u9.b(closeable);
                u9.b(fileOutputStream);
                throw th;
            }
        }
    }

    public b1 x(String str) {
        b1 b1Var;
        Map<String, b1> map = f18600g;
        synchronized (map) {
            b1Var = map.get(str);
        }
        return b1Var;
    }

    protected String y() {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        File file;
        try {
            file = new File(f18601h.getFilesDir(), D());
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
            fileInputStream = null;
        }
        if (!file.isFile()) {
            u9.b(null);
            u9.b(null);
            return null;
        }
        fileInputStream = new FileInputStream(file);
        try {
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                String str = new String(y5.b(p(), u9.g(bufferedInputStream)), StandardCharsets.UTF_8);
                g.j.a.a.a.c.y("load host fallbacks = " + str);
                return str;
            } catch (Throwable th2) {
                th = th2;
                try {
                    g.j.a.a.a.c.o("load host exception " + th.getMessage());
                    return null;
                } finally {
                    u9.b(bufferedInputStream);
                    u9.b(fileInputStream);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }

    public void z() {
        String B = B();
        try {
            File file = new File(f18601h.getFilesDir(), B);
            if (file.exists()) {
                boolean delete = file.delete();
                StringBuilder sb = new StringBuilder();
                sb.append("Delete old host fallbacks file ");
                sb.append(B);
                sb.append(delete ? " successful." : " failed.");
                g.j.a.a.a.c.o(sb.toString());
            } else {
                g.j.a.a.a.c.y("Old host fallbacks file " + B + " does not exist.");
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Delete old host fallbacks file " + B + " error: " + e2.getMessage());
        }
    }
}
