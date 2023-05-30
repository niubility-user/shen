package com.cmic.sso.sdk.c.d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class b {
    private int a;
    private Map<String, List<String>> b;

    /* renamed from: c  reason: collision with root package name */
    private String f1024c;

    public b(int i2, Map<String, List<String>> map, String str) {
        this.a = i2;
        this.b = map;
        this.f1024c = str;
    }

    public int a() {
        return this.a;
    }

    public Map<String, List<String>> b() {
        Map<String, List<String>> map = this.b;
        return map == null ? new HashMap() : map;
    }

    public String c() {
        String str = this.f1024c;
        return str == null ? "" : str;
    }

    public boolean d() {
        int i2 = this.a;
        return i2 == 302 || i2 == 301;
    }
}
