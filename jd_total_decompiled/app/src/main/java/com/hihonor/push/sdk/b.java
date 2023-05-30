package com.hihonor.push.sdk;

import android.content.Context;

/* loaded from: classes12.dex */
public class b {
    public static final b a = new b();

    public static b c() {
        return a;
    }

    public boolean a(Context context) {
        return f0.f1091e.c(context);
    }

    public void b(a<Void> aVar) {
        f0 f0Var = f0.f1091e;
        f0Var.b(new x(f0Var, aVar), aVar);
    }

    public void d(a<String> aVar) {
        f0 f0Var = f0.f1091e;
        f0Var.b(new v(f0Var, aVar, false), aVar);
    }

    public void e(Context context, boolean z) {
        f0 f0Var = f0.f1091e;
        v0 v0Var = new v0();
        v0Var.a = context.getApplicationContext();
        v0Var.b = z;
        h.b(new s(f0Var, v0Var));
    }
}
