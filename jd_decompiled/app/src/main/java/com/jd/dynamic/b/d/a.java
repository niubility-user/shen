package com.jd.dynamic.b.d;

import com.jd.dynamic.b.a.b;
import com.jd.dynamic.lib.utils.m;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {

    /* renamed from: f */
    private static final Object f1726f = new Object();

    /* renamed from: g */
    private static HashMap<String, a> f1727g = new HashMap<>();
    public String a;
    public String b = "1";

    /* renamed from: c */
    public ArrayList<String> f1728c;
    public ArrayList<String> d;

    /* renamed from: e */
    public ArrayList<String> f1729e;

    public static void a() {
        synchronized (f1726f) {
            f1727g.clear();
        }
    }

    private void c() {
        JSONObject p = b.o().p(this.a);
        if (p != null) {
            this.b = p.optString("status");
            JSONObject optJSONObject = p.optJSONObject("exceptList");
            if (optJSONObject != null) {
                this.f1728c = m.P(optJSONObject.optJSONArray("1"));
                this.d = m.P(optJSONObject.optJSONArray("0"));
                this.f1729e = m.P(optJSONObject.optJSONArray("2"));
            }
        }
    }

    public static a f(String str) {
        a aVar = new a();
        aVar.a = str;
        aVar.c();
        return aVar;
    }

    public static a g(String str) {
        a aVar;
        if (!f1727g.containsKey(str)) {
            a f2 = f(str);
            synchronized (f1726f) {
                f1727g.put(str, f2);
            }
            return f2;
        }
        synchronized (f1726f) {
            aVar = f1727g.get(str);
            if (aVar != null) {
                aVar.c();
            }
        }
        return aVar;
    }

    public boolean b(String str) {
        ArrayList<String> arrayList = this.f1728c;
        return arrayList != null && arrayList.contains(str);
    }

    public boolean d(String str) {
        ArrayList<String> arrayList = this.d;
        return arrayList != null && arrayList.contains(str);
    }

    public boolean e(String str) {
        ArrayList<String> arrayList = this.f1729e;
        return arrayList != null && arrayList.contains(str);
    }
}
