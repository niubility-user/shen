package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class m1 implements t5, c6 {
    private Context a;

    public m1(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.t5
    public void a(g6 g6Var) {
        y1.e(this.a);
    }

    @Override // com.xiaomi.push.c6
    /* renamed from: a  reason: collision with other method in class */
    public boolean mo104a(g6 g6Var) {
        return true;
    }

    @Override // com.xiaomi.push.t5
    public void b(e5 e5Var) {
        if (e5Var != null && e5Var.a() == 0 && "PING".equals(e5Var.e())) {
            y1.i(this.a);
        } else {
            y1.e(this.a);
        }
    }
}
