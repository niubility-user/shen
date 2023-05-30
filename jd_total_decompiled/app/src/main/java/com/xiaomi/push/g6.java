package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes11.dex */
public abstract class g6 {

    /* renamed from: k */
    protected static final String f18648k = Locale.getDefault().getLanguage().toLowerCase();

    /* renamed from: l */
    private static String f18649l = null;

    /* renamed from: m */
    public static final DateFormat f18650m;

    /* renamed from: n */
    private static String f18651n;
    private static long o;
    private String a;
    private String b;

    /* renamed from: c */
    private String f18652c;
    private String d;

    /* renamed from: e */
    private String f18653e;

    /* renamed from: f */
    private String f18654f;

    /* renamed from: g */
    private List<d6> f18655g;

    /* renamed from: h */
    private final Map<String, Object> f18656h;

    /* renamed from: i */
    private k6 f18657i;

    /* renamed from: j */
    public long f18658j;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f18650m = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        f18651n = r6.a(5) + "-";
        o = 0L;
    }

    public g6() {
        this.a = f18649l;
        this.b = null;
        this.f18652c = null;
        this.d = null;
        this.f18653e = null;
        this.f18654f = null;
        this.f18655g = new CopyOnWriteArrayList();
        this.f18656h = new HashMap();
        this.f18657i = null;
    }

    public g6(Bundle bundle) {
        this.a = f18649l;
        this.b = null;
        this.f18652c = null;
        this.d = null;
        this.f18653e = null;
        this.f18654f = null;
        this.f18655g = new CopyOnWriteArrayList();
        this.f18656h = new HashMap();
        this.f18657i = null;
        this.f18652c = bundle.getString("ext_to");
        this.d = bundle.getString("ext_from");
        this.f18653e = bundle.getString("ext_chid");
        this.b = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f18655g = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                d6 c2 = d6.c((Bundle) parcelable);
                if (c2 != null) {
                    this.f18655g.add(c2);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.f18657i = new k6(bundle2);
        }
    }

    public static synchronized String k() {
        String sb;
        synchronized (g6.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f18651n);
            long j2 = o;
            o = 1 + j2;
            sb2.append(Long.toString(j2));
            sb = sb2.toString();
        }
        return sb;
    }

    public static String x() {
        return f18648k;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.a)) {
            bundle.putString("ext_ns", this.a);
        }
        if (!TextUtils.isEmpty(this.d)) {
            bundle.putString("ext_from", this.d);
        }
        if (!TextUtils.isEmpty(this.f18652c)) {
            bundle.putString("ext_to", this.f18652c);
        }
        if (!TextUtils.isEmpty(this.b)) {
            bundle.putString("ext_pkt_id", this.b);
        }
        if (!TextUtils.isEmpty(this.f18653e)) {
            bundle.putString("ext_chid", this.f18653e);
        }
        k6 k6Var = this.f18657i;
        if (k6Var != null) {
            bundle.putBundle("ext_ERROR", k6Var.a());
        }
        List<d6> list = this.f18655g;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i2 = 0;
            Iterator<d6> it = this.f18655g.iterator();
            while (it.hasNext()) {
                Bundle a = it.next().a();
                if (a != null) {
                    bundleArr[i2] = a;
                    i2++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    public d6 b(String str) {
        return c(str, null);
    }

    public d6 c(String str, String str2) {
        for (d6 d6Var : this.f18655g) {
            if (str2 == null || str2.equals(d6Var.j())) {
                if (str.equals(d6Var.e())) {
                    return d6Var;
                }
            }
        }
        return null;
    }

    public k6 d() {
        return this.f18657i;
    }

    public synchronized Object e(String str) {
        Map<String, Object> map = this.f18656h;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g6 g6Var = (g6) obj;
        k6 k6Var = this.f18657i;
        if (k6Var == null ? g6Var.f18657i == null : k6Var.equals(g6Var.f18657i)) {
            String str = this.d;
            if (str == null ? g6Var.d == null : str.equals(g6Var.d)) {
                if (this.f18655g.equals(g6Var.f18655g)) {
                    String str2 = this.b;
                    if (str2 == null ? g6Var.b == null : str2.equals(g6Var.b)) {
                        String str3 = this.f18653e;
                        if (str3 == null ? g6Var.f18653e == null : str3.equals(g6Var.f18653e)) {
                            Map<String, Object> map = this.f18656h;
                            if (map == null ? g6Var.f18656h == null : map.equals(g6Var.f18656h)) {
                                String str4 = this.f18652c;
                                if (str4 == null ? g6Var.f18652c == null : str4.equals(g6Var.f18652c)) {
                                    String str5 = this.a;
                                    String str6 = g6Var.a;
                                    if (str5 != null) {
                                        if (str5.equals(str6)) {
                                            return true;
                                        }
                                    } else if (str6 == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public abstract String f();

    public synchronized Collection<d6> g() {
        if (this.f18655g == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(this.f18655g));
    }

    public void h(d6 d6Var) {
        this.f18655g.add(d6Var);
    }

    public int hashCode() {
        String str = this.a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f18652c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.f18653e;
        int hashCode5 = (((((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.f18655g.hashCode()) * 31) + this.f18656h.hashCode()) * 31;
        k6 k6Var = this.f18657i;
        return hashCode5 + (k6Var != null ? k6Var.hashCode() : 0);
    }

    public void i(k6 k6Var) {
        this.f18657i = k6Var;
    }

    public synchronized Collection<String> j() {
        if (this.f18656h == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(this.f18656h.keySet()));
    }

    public String l() {
        if ("ID_NOT_AVAILABLE".equals(this.b)) {
            return null;
        }
        if (this.b == null) {
            this.b = k();
        }
        return this.b;
    }

    public String m() {
        return this.f18653e;
    }

    public void n(String str) {
        this.b = str;
    }

    public String o() {
        return this.f18652c;
    }

    public void p(String str) {
        this.f18653e = str;
    }

    public String q() {
        return this.d;
    }

    public void r(String str) {
        this.f18652c = str;
    }

    public String s() {
        return this.f18654f;
    }

    public void t(String str) {
        this.d = str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:36|37|38|40|41|(4:42|43|59|60)|62|63) */
    /* JADX WARN: Removed duplicated region for block: B:290:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0109 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0123 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0111 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized String u() {
        StringBuilder sb;
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        Exception e2;
        String str;
        sb = new StringBuilder();
        Iterator<d6> it = g().iterator();
        while (it.hasNext()) {
            sb.append(it.next().d());
        }
        Map<String, Object> map = this.f18656h;
        if (map != null && !map.isEmpty()) {
            sb.append(m0.g("PHByb3BlcnRpZXMgeG1sbnM9Imh0dHA6Ly93d3cuaml2ZXNvZnR3YXJlLmNvbS94bWxucy94bXBwL3Byb3BlcnRpZXMiPg=="));
            for (String str2 : j()) {
                Object e3 = e(str2);
                sb.append("<property>");
                sb.append("<name>");
                sb.append(r6.b(str2));
                sb.append("</name>");
                sb.append("<value type=\"");
                if (e3 instanceof Integer) {
                    sb.append("integer\">");
                    sb.append(e3);
                    str = "</value>";
                } else if (e3 instanceof Long) {
                    sb.append("long\">");
                    sb.append(e3);
                    str = "</value>";
                } else if (e3 instanceof Float) {
                    sb.append("float\">");
                    sb.append(e3);
                    str = "</value>";
                } else if (e3 instanceof Double) {
                    sb.append("double\">");
                    sb.append(e3);
                    str = "</value>";
                } else if (e3 instanceof Boolean) {
                    sb.append("boolean\">");
                    sb.append(e3);
                    str = "</value>";
                } else if (e3 instanceof String) {
                    sb.append("string\">");
                    sb.append(r6.b((String) e3));
                    str = "</value>";
                } else {
                    ObjectOutputStream objectOutputStream2 = null;
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        } catch (Exception e4) {
                            e = e4;
                            objectOutputStream = null;
                            e2 = e;
                            try {
                                e2.printStackTrace();
                                if (objectOutputStream != null) {
                                }
                                if (byteArrayOutputStream == null) {
                                }
                                byteArrayOutputStream.close();
                            } catch (Throwable th) {
                                th = th;
                                objectOutputStream2 = objectOutputStream;
                                if (objectOutputStream2 != null) {
                                    try {
                                        objectOutputStream2.close();
                                    } catch (Exception unused) {
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception unused2) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectOutputStream2 != null) {
                            }
                            if (byteArrayOutputStream != null) {
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        byteArrayOutputStream = null;
                        objectOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = null;
                    }
                    try {
                        objectOutputStream.writeObject(e3);
                        sb.append("java-object\">");
                        sb.append(r6.d(byteArrayOutputStream.toByteArray()));
                        sb.append("</value>");
                        try {
                            objectOutputStream.close();
                        } catch (Exception unused3) {
                        }
                    } catch (Exception e6) {
                        e2 = e6;
                        e2.printStackTrace();
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (Exception unused4) {
                            }
                        }
                        if (byteArrayOutputStream == null) {
                            sb.append("</property>");
                        }
                        byteArrayOutputStream.close();
                    }
                    byteArrayOutputStream.close();
                }
                sb.append(str);
                sb.append("</property>");
            }
            sb.append("</properties>");
        }
        return sb.toString();
    }

    public void v(String str) {
        this.f18654f = str;
    }

    public String w() {
        return this.a;
    }
}
