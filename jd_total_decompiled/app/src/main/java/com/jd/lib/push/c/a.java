package com.jd.lib.push.c;

import android.content.Context;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes16.dex */
public abstract class a {
    protected int a;
    protected boolean d = true;
    protected k b = new k();

    /* renamed from: c */
    protected Context f5659c = JdSdk.getInstance().getApplicationContext();

    public a(int i2) {
        this.a = i2;
    }

    public void a(String str) {
        com.jd.lib.push.a.b(this.a, str);
    }

    public abstract void b(Context context);

    public abstract String c();

    public boolean d() {
        return this.d;
    }

    public abstract void e(Context context, int i2);

    public abstract void f();
}
