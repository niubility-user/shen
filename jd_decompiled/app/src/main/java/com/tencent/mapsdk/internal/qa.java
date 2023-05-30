package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.tencent.mapsdk.internal.ya;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class qa implements pa {
    private static final HashSet<String> Y = new HashSet<>();
    private static final ya.k<b> Z;
    private static final AtomicInteger a0;
    private static final Map<String, Map<String, d>> b0;
    private static Pair<String, StringBuilder> c0;

    /* loaded from: classes9.dex */
    public static class a implements ya.i<b> {
        @Override // com.tencent.mapsdk.internal.ya.i
        /* renamed from: b */
        public b a() {
            return new b();
        }
    }

    /* loaded from: classes9.dex */
    public static class b implements ya.l {
        private int a = 3;
        private String b = la.d;

        public b a(int i2) {
            qa.a0.incrementAndGet();
            this.a = i2;
            return this;
        }

        public b a(String str) {
            qa.a0.incrementAndGet();
            this.b = str;
            return this;
        }

        @Override // com.tencent.mapsdk.internal.ya.l
        public ya.p a() {
            return ya.p.a();
        }

        public void a(Object... objArr) {
            qa.a0.incrementAndGet();
            qa.b(this.a, this.b, objArr);
            qa.Z.a(this);
        }
    }

    /* loaded from: classes9.dex */
    public interface c {
        void a(String str);
    }

    /* loaded from: classes9.dex */
    public static class d {
        private String b;

        /* renamed from: c */
        private String f17042c;

        /* renamed from: e */
        private c f17043e;

        /* renamed from: f */
        private Map<String, Object> f17044f;

        /* renamed from: g */
        private String f17045g;

        /* renamed from: h */
        private String f17046h;
        public List<Long> d = new CopyOnWriteArrayList();
        private AtomicInteger a = new AtomicInteger(0);

        public d(String str, String str2) {
            this.b = str;
            this.f17042c = str2;
        }

        public void a() {
            this.a.set(0);
            this.d.clear();
            this.f17043e = null;
            Map<String, Object> map = this.f17044f;
            if (map != null) {
                map.clear();
            }
        }

        public void a(String str, Object obj) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (this.f17044f == null) {
                this.f17044f = new Hashtable();
            }
            this.f17044f.put(str, obj);
        }

        public boolean a(String str) {
            return this.f17042c.equals(str);
        }

        public Object b(String str) {
            Map<String, Object> map = this.f17044f;
            if (map != null) {
                return map.get(str);
            }
            return null;
        }

        public String b() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (!TextUtils.isEmpty(this.f17046h)) {
                sb.append(this.f17046h);
            }
            boolean equals = this.b.equals(this.f17042c);
            sb.append("|");
            if (equals) {
                str = this.b;
            } else {
                sb.append(this.b);
                sb.append("|");
                str = this.f17042c;
            }
            sb.append(str);
            if (!TextUtils.isEmpty(this.f17045g)) {
                sb.append("|");
                sb.append(this.f17045g);
            }
            sb.append("]");
            return sb.toString();
        }

        public String toString() {
            return "TraceInfo{id='" + this.f17042c + "', values=" + this.f17044f + '}';
        }
    }

    static {
        f(la.p);
        f(la.b);
        f(la.r);
        f(la.t);
        Z = ya.b(30, new a());
        a0 = new AtomicInteger();
        b0 = Collections.synchronizedMap(new Hashtable());
    }

    public static int a(String str, String str2, int i2) {
        return a(str, c(str), str2, i2);
    }

    public static int a(String str, String str2, String str3, int i2) {
        int i3 = -1;
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                Object b2 = dVar.b(str3);
                i3 = 1;
                if (b2 instanceof AtomicInteger) {
                    if (i2 < 1) {
                        i2 = 1;
                    }
                    AtomicInteger atomicInteger = (AtomicInteger) b2;
                    int i4 = atomicInteger.get() + i2;
                    atomicInteger.set(i4);
                    return i4;
                }
                dVar.a(str3, new AtomicInteger(1));
            }
            return i3;
        }
        return -1;
    }

    private static long a(d dVar) {
        long j2;
        long j3 = -1;
        if (dVar != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (dVar.d.size() > 0) {
                j3 = currentTimeMillis - dVar.d.get(0).longValue();
                j2 = currentTimeMillis - dVar.d.get(r4.size() - 1).longValue();
            } else {
                j2 = -1;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(dVar.b());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (dVar.a.get() != 0) {
                sb.append("idx:");
                sb.append(dVar.a.get());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            if (j3 > 0) {
                sb.append("ut:");
                sb.append(j3);
                sb.append("ms\n");
            }
            if (j2 > 0) {
                sb.append("it:");
                sb.append(j2);
                sb.append("ms\n");
            }
            if (dVar.f17044f != null && !dVar.f17044f.isEmpty()) {
                sb.append("val:");
                sb.append(dVar.f17044f);
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            String sb2 = sb.toString();
            ma.c(la.d, sb2);
            if (dVar.f17043e != null) {
                dVar.f17043e.a(sb2);
            }
            a(sb2);
        }
        return j3;
    }

    public static long a(String str, String str2, Object obj) {
        if (e(str)) {
            return a(str, c(str), "", str2, obj);
        }
        return 0L;
    }

    private static long a(String str, String str2, String str3, String str4, Object obj) {
        if (e(str)) {
            a(str, str2, str4, obj);
            d a2 = a(str, str2);
            if (a2 != null) {
                a2.f17046h = "Log";
                a2.a.incrementAndGet();
                a2.f17045g = str3;
                a2.d.add(Long.valueOf(System.currentTimeMillis()));
            }
            return a(a2);
        }
        return 0L;
    }

    public static b a(int i2) {
        a0.incrementAndGet();
        b a2 = Z.a();
        a2.a = i2;
        return a2;
    }

    private static d a(String str, String str2) {
        Map<String, d> map = b0.get(str);
        d dVar = map != null ? map.get(str2) : null;
        if (dVar == null || !dVar.a(str2)) {
            return null;
        }
        return dVar;
    }

    public static String a(Context context, String str) {
        if (TextUtils.isEmpty(str) || li.d) {
            return str;
        }
        String str2 = null;
        List<String> g2 = fa.g(new File(ma.a(), "kv"));
        if (g2 == null || g2.isEmpty() || TextUtils.isEmpty(g2.get(0))) {
            try {
                str2 = ra.a(b7.t() + li.f16854j + li.f16853i + li.f16848c + li.b);
                ma.d("kv", str2);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            }
        } else {
            str2 = g2.get(0);
        }
        try {
            return !TextUtils.isEmpty(str2) ? ra.b(str2, str) : "";
        } catch (GeneralSecurityException unused2) {
            return "";
        }
    }

    private static void a(String str) {
        String d2 = oa.d();
        Pair<String, StringBuilder> pair = c0;
        if (pair != null && !((String) pair.first).equals(d2)) {
            c();
        }
        if (c0 == null) {
            c0 = new Pair<>(d2, new StringBuilder());
        }
        Pair<String, StringBuilder> pair2 = c0;
        StringBuilder sb = (StringBuilder) pair2.second;
        synchronized (pair2) {
            sb.append(System.currentTimeMillis());
            sb.append(" : ");
            sb.append(str);
        }
        if (sb.length() >= 10240) {
            ma.d(la.d, sb.toString());
            c0 = null;
        }
    }

    public static void a(String str, c cVar) {
        if (e(str)) {
            a(str, c(str), "", cVar);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (e(str)) {
            a(str, str2, str3, (c) null);
        }
    }

    public static void a(String str, String str2, String str3, c cVar) {
        d dVar;
        if (e(str)) {
            Map<String, Map<String, d>> map = b0;
            Map<String, d> map2 = map.get(str);
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                dVar = new d(str, str2);
                hashMap.put(str2, dVar);
                map.put(str, hashMap);
            } else {
                dVar = map2.get(str2);
                if (dVar == null) {
                    dVar = new d(str, str2);
                    map2.put(str2, dVar);
                } else {
                    dVar.a();
                }
            }
            dVar.f17042c = str2;
            dVar.f17043e = cVar;
            dVar.d.add(Long.valueOf(System.currentTimeMillis()));
            dVar.f17046h = "Begin";
            a(dVar);
        }
    }

    public static void a(String str, String str2, String str3, Object obj) {
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                dVar.a(str3, obj);
                dVar.f17046h = "Set";
                a(dVar.b() + ":" + str3 + "=>" + obj + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
    }

    public static void a(Throwable th) {
        if (th != null) {
            StringBuilder sb = new StringBuilder();
            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                do {
                    th.printStackTrace(printWriter);
                    th = th.getCause();
                } while (th != null);
                printWriter.close();
                sb.append(stringWriter.toString());
                sb.append("\n =============== ");
            } catch (Throwable unused) {
            }
            ma.d("CRASH", sb.toString());
        }
    }

    public static void a(Object... objArr) {
        if (d()) {
            a0.incrementAndGet();
            b(3, la.d, objArr);
        }
    }

    public static int b(String str, String str2, String str3) {
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                Object b2 = dVar.b(str3);
                if (b2 instanceof AtomicInteger) {
                    AtomicInteger atomicInteger = (AtomicInteger) b2;
                    int decrementAndGet = atomicInteger.decrementAndGet();
                    if (decrementAndGet < 0) {
                        atomicInteger.set(0);
                        return 0;
                    }
                    return decrementAndGet;
                }
            }
            return -1;
        }
        return -1;
    }

    private static String b(String str) {
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x005c, code lost:
        r4 = r9.getParameterTypes();
     */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x010b A[LOOP:2: B:139:0x0109->B:140:0x010b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(int r12, java.lang.String r13, java.lang.Object... r14) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.qa.b(int, java.lang.String, java.lang.Object[]):void");
    }

    public static void b(String str, String str2, Object obj) {
        if (e(str)) {
            a(str, c(str), str2, obj);
        }
    }

    public static boolean b(String str, String str2) {
        return a(str, str2) != null;
    }

    public static int c(String str, String str2, String str3) {
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                Object b2 = dVar.b(str3);
                if (b2 instanceof AtomicInteger) {
                    return ((AtomicInteger) b2).get();
                }
            }
            return 0;
        }
        return 0;
    }

    private static String c(String str) {
        return str;
    }

    private static void c() {
        Object obj;
        Pair<String, StringBuilder> pair = c0;
        if (pair == null || (obj = pair.second) == null || ((StringBuilder) obj).length() == 0) {
            return;
        }
        String d2 = oa.d();
        Pair<String, StringBuilder> pair2 = c0;
        StringBuilder sb = (StringBuilder) pair2.second;
        String str = la.d;
        if (!((String) pair2.first).equals(d2)) {
            str = "TT-" + ((String) c0.first);
        }
        synchronized (c0) {
            sb.append("\n ============= \n");
        }
        ma.d(str, sb.toString());
        c0 = null;
    }

    public static void c(String str, String str2) {
        if (e(str)) {
            a(str, str2, "", (c) null);
        }
    }

    public static Object d(String str, String str2, String str3) {
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                return dVar.b(str3);
            }
            return null;
        }
        return null;
    }

    public static void d(String str, String str2) {
        if (e(str)) {
            a(str, c(str), str2, (c) null);
        }
    }

    private static boolean d() {
        return ma.d(la.d);
    }

    public static boolean d(String str) {
        return b(str, c(str));
    }

    public static int e(String str, String str2) {
        if (e(str)) {
            return b(str, c(str), str2);
        }
        return -1;
    }

    public static int e(String str, String str2, String str3) {
        if (e(str)) {
            Map<String, d> map = b0.get(str);
            d dVar = map != null ? map.get(str2) : null;
            if (dVar != null) {
                Object b2 = dVar.b(str3);
                if (b2 instanceof AtomicInteger) {
                    return ((AtomicInteger) b2).incrementAndGet();
                }
                dVar.a(str3, new AtomicInteger(1));
                return 1;
            }
            return -1;
        }
        return -1;
    }

    public static void e() {
        c();
    }

    private static boolean e(String str) {
        return ma.d(la.d) && !Y.contains(str);
    }

    public static long f(String str, String str2) {
        if (e(str)) {
            d a2 = a(str, str2);
            if (a2 != null) {
                a2.f17046h = "End";
            }
            long a3 = a(a2);
            if (a3 != -1) {
                b0.remove(str);
            }
            return a3;
        }
        return 0L;
    }

    public static void f(String str) {
        Y.add(str);
    }

    public static int g(String str, String str2) {
        if (e(str)) {
            return c(str, c(str), str2);
        }
        return 0;
    }

    public static b g(String str) {
        a0.incrementAndGet();
        b a2 = Z.a();
        a2.b = str;
        return a2;
    }

    public static Object h(String str, String str2) {
        if (e(str)) {
            return d(str, c(str), str2);
        }
        return null;
    }

    public static void h(String str) {
        if (e(str)) {
            a(str, c(str), "", (c) null);
        }
    }

    public static int i(String str, String str2) {
        if (e(str)) {
            return e(str, c(str), str2);
        }
        return -1;
    }

    public static long i(String str) {
        if (e(str)) {
            return f(str, c(str));
        }
        return 0L;
    }

    public static long j(String str) {
        if (e(str)) {
            return j(str, c(str));
        }
        return 0L;
    }

    public static long j(String str, String str2) {
        if (e(str)) {
            return a(str, c(str), str2, "", null);
        }
        return 0L;
    }
}
