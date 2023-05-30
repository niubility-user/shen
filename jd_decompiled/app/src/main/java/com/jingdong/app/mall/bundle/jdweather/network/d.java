package com.jingdong.app.mall.bundle.jdweather.network;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class d {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f8201c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private b f8202e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, String> f8203f;

    /* renamed from: g  reason: collision with root package name */
    private Map<String, String> f8204g;

    public Map<String, String> a() {
        if (this.f8203f == null) {
            this.f8203f = new HashMap();
        }
        return this.f8203f;
    }

    public Map<String, String> b() {
        if (this.f8204g == null) {
            this.f8204g = new HashMap();
        }
        return this.f8204g;
    }

    public int c() {
        return this.d;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public b f() {
        return this.f8202e;
    }

    public int g() {
        return this.f8201c;
    }

    public void h(String str, String str2) {
        if (this.f8203f == null) {
            this.f8203f = new HashMap();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f8203f.put(str, str2);
    }

    public void i(String str, String str2) {
        if (this.f8204g == null) {
            this.f8204g = new HashMap();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f8204g.put(str, str2);
    }

    public void j(int i2) {
        this.d = i2;
    }

    public void k(String str) {
        this.b = str;
    }

    public void l(b bVar) {
        if (bVar != null) {
            this.f8202e = bVar;
        }
    }

    public void m(int i2) {
        this.f8201c = i2;
    }
}
