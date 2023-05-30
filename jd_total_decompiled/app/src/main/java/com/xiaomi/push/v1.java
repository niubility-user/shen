package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class v1 implements r5 {

    /* renamed from: g  reason: collision with root package name */
    private Context f19277g;

    public v1(Context context) {
        this.f19277g = context;
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var) {
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, int i2, Exception exc) {
        p1.f(this.f19277g, o5Var.d(), i2);
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, Exception exc) {
    }

    @Override // com.xiaomi.push.r5
    public void b(o5 o5Var) {
        p1.c(this.f19277g);
    }
}
