package com.jingdong.aura.a.a;

import android.text.TextUtils;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.a.b.h;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.tencent.open.SocialConstants;
import com.vivo.push.PushClientConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private static a d;

    /* renamed from: e */
    private static final com.jingdong.aura.core.util.l.b f12069e = com.jingdong.aura.core.util.l.c.a("BundleInfoList");
    private final String a = a.class.getSimpleName();
    private List<C0391a> b;

    /* renamed from: c */
    private List<C0391a> f12070c;

    /* renamed from: com.jingdong.aura.a.a.a$a */
    /* loaded from: classes4.dex */
    public static class C0391a {
        public List<String> a;
        public List<String> b;

        /* renamed from: c */
        public List<C0392a> f12071c;
        public List<String> d;

        /* renamed from: e */
        public List<String> f12072e;

        /* renamed from: f */
        public String f12073f;

        /* renamed from: g */
        public boolean f12074g;

        /* renamed from: h */
        public long f12075h;

        /* renamed from: i */
        public String f12076i;

        /* renamed from: j */
        public long f12077j;

        /* renamed from: k */
        public String f12078k;

        /* renamed from: l */
        public String f12079l;

        /* renamed from: m */
        public String f12080m;

        /* renamed from: n */
        public int f12081n;
        public String o;
        public List<String> p;
        public List<String> q;
        public List<String> r;
        public List<String> s;

        /* renamed from: com.jingdong.aura.a.a.a$a$a */
        /* loaded from: classes4.dex */
        public static class C0392a {
            public String a;
            public String b;

            /* renamed from: c */
            public String f12082c;
            public String d;

            public C0392a(String str, String str2, String str3, String str4) {
                this.a = str;
                this.b = str2;
                this.f12082c = str3;
                this.d = str4;
            }

            public String toString() {
                return "SoInfo{name='" + this.a + "', path='" + this.b + "', size='" + this.f12082c + "', md5='" + this.d + "'}";
            }
        }
    }

    private a() {
    }

    public static synchronized List<C0391a.C0392a> a(String str, String str2, String str3) {
        synchronized (a.class) {
            ArrayList arrayList = new ArrayList();
            com.jingdong.aura.core.util.l.b bVar = f12069e;
            bVar.a("getSoInfo: location:" + str + " abi:" + str2 + " name:" + str3);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2)) {
                List<C0391a.C0392a> a = a(str, str3);
                if (!a.isEmpty()) {
                    bVar.a("soInfoList:" + a + " location:" + str);
                    return a;
                }
                List<String> d2 = c().d(str);
                if (d2 != null && !d2.isEmpty()) {
                    for (int i2 = 0; i2 < d2.size(); i2++) {
                        String str4 = d2.get(i2);
                        if (((h) com.jingdong.aura.a.b.k.b.b(str4)) != null) {
                            List<C0391a.C0392a> a2 = a(str4, str3);
                            if (!a2.isEmpty()) {
                                a.addAll(a2);
                            }
                        }
                    }
                    return a;
                }
                e.a(str, "getSoInfo", str, str3 + " dependencyBundle is empty", "getSoInfo", null);
                return a;
            }
            e.a(str, "getSoInfo", str, str3 + " param empty,location:" + str + " name:" + str3 + " abi:" + str2, "getSoInfo", null);
            return arrayList;
        }
    }

    public static synchronized a c() {
        a aVar;
        synchronized (a.class) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
                aVar = d;
            }
            return aVar;
        }
        return aVar;
    }

    public synchronized void b(List<C0391a> list) {
        if (list != null) {
            this.f12070c = list;
        }
    }

    public synchronized List<String> d(String str) {
        List<C0391a> list = this.b;
        if (list != null && list.size() != 0) {
            for (C0391a c0391a : this.b) {
                if (c0391a.f12073f.equals(str)) {
                    ArrayList arrayList = new ArrayList();
                    if (c0391a != null && c0391a.d != null) {
                        for (int i2 = 0; i2 < c0391a.d.size(); i2++) {
                            if (!TextUtils.isEmpty(c0391a.d.get(i2))) {
                                arrayList.add(c0391a.d.get(i2));
                            }
                        }
                    }
                    return arrayList;
                }
            }
            return null;
        }
        return null;
    }

    public synchronized List<String> e(String str) {
        List<C0391a> list = this.b;
        if (list != null && list.size() != 0) {
            for (C0391a c0391a : this.b) {
                if (c0391a.f12073f.equals(str)) {
                    return c0391a.f12072e;
                }
            }
            return null;
        }
        return null;
    }

    public C0391a f(String str) {
        List<C0391a> list = this.f12070c;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.f12070c.size(); i2++) {
                C0391a c0391a = this.f12070c.get(i2);
                if (c0391a.f12073f.equals(str)) {
                    return c0391a;
                }
            }
        }
        return null;
    }

    public String g(String str) {
        C0391a f2 = f(str);
        if (f2 == null) {
            return null;
        }
        return f2.f12079l;
    }

    public synchronized long h(String str) {
        List<C0391a> list = this.b;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                C0391a c0391a = this.b.get(i2);
                if (c0391a.f12073f.equals(str)) {
                    return c0391a.f12077j;
                }
            }
            return -1L;
        }
        return -1L;
    }

    public synchronized boolean i(String str) {
        if (str == null) {
            return false;
        }
        List<C0391a> list = this.b;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                C0391a c0391a = this.b.get(i2);
                if (c0391a.f12073f.equals(str)) {
                    return c0391a.f12081n == 2;
                }
            }
            return false;
        }
        return false;
    }

    public synchronized String b(String str) {
        List<C0391a> list = this.b;
        if (list == null || list.size() == 0) {
            return null;
        }
        if (str == null) {
            return null;
        }
        for (C0391a c0391a : this.b) {
            Iterator<String> it = c0391a.a.iterator();
            while (it.hasNext()) {
                if (it.next().equals(str)) {
                    return c0391a.f12073f;
                }
            }
            Iterator<String> it2 = c0391a.b.iterator();
            while (it2.hasNext()) {
                if (str.equals(it2.next())) {
                    return c0391a.f12073f;
                }
            }
        }
        return null;
    }

    public long c(String str) {
        C0391a a = a(str);
        if (a == null) {
            return 0L;
        }
        return a.f12075h;
    }

    private void c(List<C0391a> list) {
        if (list != null) {
            try {
                if (list.isEmpty()) {
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (C0391a c0391a : list) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(PushClientConstants.TAG_PKG_NAME, c0391a.f12073f);
                    jSONObject.put("verName", c0391a.f12076i);
                    jSONObject.put("verCode", c0391a.f12077j);
                    jSONObject.put("app", c0391a.f12078k);
                    jSONObject.put("bundleType", c0391a.f12081n);
                    jSONObject.put(ApkDownloadTable.FIELD_SIZE, c0391a.f12075h);
                    jSONObject.put("hasSO", c0391a.f12074g);
                    jSONObject.put("md5", c0391a.f12079l);
                    if (!TextUtils.isEmpty(c0391a.o)) {
                        jSONObject.put("downloadUrl", c0391a.o);
                    }
                    a(jSONObject, c0391a.p, "activity");
                    a(jSONObject, c0391a.q, "service");
                    a(jSONObject, c0391a.r, "provider");
                    a(jSONObject, c0391a.s, SocialConstants.PARAM_RECEIVER);
                    a(jSONObject, c0391a.b, "manualComponents");
                    a(jSONObject, c0391a.d, "dependency");
                    a(jSONObject, c0391a.f12072e, "auraDependentSo");
                    jSONArray.put(jSONObject);
                }
                String str = com.jingdong.aura.core.util.d.a().getAbsolutePath() + "/aura/updateAura.json";
                String jSONArray2 = jSONArray.toString();
                if (TextUtils.isEmpty(jSONArray2)) {
                    return;
                }
                com.jingdong.aura.core.util.d.a(new ByteArrayInputStream(jSONArray2.getBytes()), new File(str));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public synchronized List<Map<String, String>> d() {
        List<C0391a> list = this.f12070c;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (C0391a c0391a : this.f12070c) {
                if (c0391a.f12081n == 2) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("bundleName", c0391a.f12073f);
                    if (!TextUtils.isEmpty(c0391a.f12080m)) {
                        hashMap.put("md5", c0391a.f12080m);
                    } else {
                        hashMap.put("md5", c0391a.f12079l);
                    }
                    hashMap.put(ApkDownloadTable.FIELD_SIZE, c0391a.f12075h + "");
                    hashMap.put("versionCode", c0391a.f12077j + "");
                    hashMap.put("downloadUrl", c0391a.o);
                    arrayList.add(hashMap);
                }
            }
            return arrayList;
        }
        return null;
    }

    public synchronized List<C0391a> b() {
        List<C0391a> list = this.b;
        if (list != null && !list.isEmpty()) {
            return this.b;
        }
        return null;
    }

    private static synchronized List<C0391a.C0392a> a(String str, String str2) {
        ArrayList arrayList;
        synchronized (a.class) {
            arrayList = new ArrayList();
            List<C0391a.C0392a> list = c().a(str).f12071c;
            for (int i2 = 0; i2 < list.size(); i2++) {
                C0391a.C0392a c0392a = list.get(i2);
                if (!TextUtils.isEmpty(c0392a.b) && !TextUtils.isEmpty(c0392a.a) && c0392a.a.equals(str2)) {
                    arrayList.add(c0392a);
                }
            }
        }
        return arrayList;
    }

    public synchronized boolean a(List<C0391a> list) {
        boolean z;
        if (this.b == null && list != null) {
            this.b = list;
            z = true;
        }
        z = false;
        return z;
    }

    public synchronized List<String> a() {
        List<C0391a> list = this.b;
        if (list != null && !list.isEmpty()) {
            LinkedList linkedList = new LinkedList();
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                linkedList.add(this.b.get(i2).f12073f);
            }
            return linkedList;
        }
        return null;
    }

    public C0391a a(String str) {
        return a(this.b, str);
    }

    public synchronized C0391a a(List<C0391a> list, String str) {
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    C0391a c0391a = list.get(i2);
                    if (c0391a.f12073f.equals(str)) {
                        return c0391a;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public synchronized void a(C0391a c0391a) {
        if (c0391a == null) {
            return;
        }
        List<C0391a> b = c().b();
        if (b != null) {
            if (com.jingdong.aura.a.b.k.b.b(c0391a.f12073f) != null) {
                b = new ArrayList(b);
            }
            C0391a a = a(b, c0391a.f12073f);
            if (a != null) {
                if (c0391a.f12077j > a.f12077j) {
                    c0391a.f12081n = a.f12081n;
                    c0391a.o = a.o;
                    b.remove(a);
                    b.add(c0391a);
                    c(b);
                }
            } else {
                b.add(c0391a);
                c(b);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(c0391a);
            this.b = arrayList;
            c(arrayList);
        }
    }

    private static void a(JSONObject jSONObject, List<String> list, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str) || list == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        jSONObject.put(str, jSONArray);
    }
}
