package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes11.dex */
public class z implements t {

    /* renamed from: i  reason: collision with root package name */
    private static volatile z f19333i;

    /* renamed from: g  reason: collision with root package name */
    private t f19334g;

    /* renamed from: h  reason: collision with root package name */
    private int f19335h = y.a;

    private z(Context context) {
        this.f19334g = y.a(context);
        g.j.a.a.a.c.o("create id manager is: " + this.f19335h);
    }

    public static z a(Context context) {
        if (f19333i == null) {
            synchronized (z.class) {
                if (f19333i == null) {
                    f19333i = new z(context.getApplicationContext());
                }
            }
        }
        return f19333i;
    }

    private String b(String str) {
        return str == null ? "" : str;
    }

    @Override // com.xiaomi.push.t
    public String a() {
        return b(this.f19334g.a());
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a */
    public boolean mo30a() {
        return this.f19334g.mo30a();
    }

    public void c() {
    }

    public void d(Map<String, String> map) {
        if (map == null) {
            return;
        }
        String e2 = e();
        if (!TextUtils.isEmpty(e2)) {
            map.put("udid", e2);
        }
        String a = a();
        if (!TextUtils.isEmpty(a)) {
            map.put("oaid", a);
        }
        String f2 = f();
        if (!TextUtils.isEmpty(f2)) {
            map.put("vaid", f2);
        }
        String g2 = g();
        if (!TextUtils.isEmpty(g2)) {
            map.put("aaid", g2);
        }
        map.put("oaid_type", String.valueOf(this.f19335h));
    }

    public String e() {
        return null;
    }

    public String f() {
        return null;
    }

    public String g() {
        return null;
    }
}
