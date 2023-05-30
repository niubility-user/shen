package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class o1 implements t5, c6 {
    private Context a;

    public o1(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.t5
    public void a(g6 g6Var) {
        y1.a(this.a);
    }

    @Override // com.xiaomi.push.c6
    /* renamed from: a */
    public boolean mo104a(g6 g6Var) {
        return true;
    }

    @Override // com.xiaomi.push.t5
    public void b(e5 e5Var) {
        if (e5Var != null && e5Var.a() == 0 && "PING".equals(e5Var.e())) {
            y1.h(this.a);
        } else {
            y1.a(this.a);
        }
    }
}
