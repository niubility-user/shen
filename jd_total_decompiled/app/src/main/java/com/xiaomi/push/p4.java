package com.xiaomi.push;

import java.util.Map;

/* loaded from: classes11.dex */
public class p4 implements n4 {
    private n4 a;

    /* loaded from: classes11.dex */
    private static class a {
        private static p4 a = new p4();
    }

    private p4() {
    }

    public static p4 b() {
        return a.a;
    }

    @Override // com.xiaomi.push.n4
    public void a(m4 m4Var) {
        n4 n4Var = this.a;
        if (n4Var != null) {
            n4Var.a(m4Var);
        }
    }

    @Override // com.xiaomi.push.n4
    public void a(String str, Map<String, Object> map) {
        n4 n4Var = this.a;
        if (n4Var != null) {
            n4Var.a(str, map);
        }
    }
}
