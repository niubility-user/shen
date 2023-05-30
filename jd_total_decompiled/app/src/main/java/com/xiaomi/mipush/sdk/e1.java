package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.p7;
import com.xiaomi.push.q2;

/* loaded from: classes11.dex */
public class e1 implements q2 {
    private Context a;

    public e1(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.q2
    public String a() {
        return o0.c(this.a).t();
    }

    @Override // com.xiaomi.push.q2
    public void a(c8 c8Var, c7 c7Var, p7 p7Var) {
        f0.h(this.a).w(c8Var, c7Var, p7Var);
    }
}
