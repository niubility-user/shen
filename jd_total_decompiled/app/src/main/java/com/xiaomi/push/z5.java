package com.xiaomi.push;

/* loaded from: classes11.dex */
public class z5 implements Runnable {

    /* renamed from: g */
    final /* synthetic */ String f19358g;

    public z5(v5 v5Var, String str) {
        this.f19358g = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        f1.c().b(this.f19358g, true);
    }
}
