package com.xiaomi.push;

import java.util.Map;

/* loaded from: classes11.dex */
public class p5 implements Cloneable {

    /* renamed from: k */
    public static String f18933k;

    /* renamed from: g */
    private String f18934g;

    /* renamed from: h */
    private int f18935h;

    /* renamed from: i */
    private boolean f18936i = o5.p;

    /* renamed from: j */
    private String f18937j;

    public p5(Map<String, Integer> map, int i2, String str, s5 s5Var) {
        d(map, i2, str, s5Var);
    }

    public static final String b() {
        String str = f18933k;
        return str != null ? str : b.c() ? "sandbox.xmpush.xiaomi.com" : b.d() ? "10.38.162.35" : "app.chat.xiaomi.net";
    }

    public static final void c(String str) {
        if (b.d()) {
            return;
        }
        f18933k = str;
    }

    private void d(Map<String, Integer> map, int i2, String str, s5 s5Var) {
        this.f18935h = i2;
    }

    public int a() {
        return this.f18935h;
    }

    public void e(boolean z) {
        this.f18936i = z;
    }

    public boolean f() {
        return this.f18936i;
    }

    public byte[] g() {
        throw null;
    }

    public String h() {
        return this.f18937j;
    }

    public void i(String str) {
        this.f18937j = str;
    }

    public String j() {
        if (this.f18934g == null) {
            this.f18934g = b();
        }
        return this.f18934g;
    }

    public void k(String str) {
        this.f18934g = str;
    }
}
