package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.push.a3;
import com.xiaomi.push.l;
import com.xiaomi.push.r9;
import com.xiaomi.push.service.z0;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a1 extends l.b {
    boolean a = false;
    final /* synthetic */ z0 b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a1(z0 z0Var) {
        this.b = z0Var;
    }

    @Override // com.xiaomi.push.l.b
    public void b() {
        try {
            a3 n2 = a3.n(Base64.decode(com.xiaomi.push.j1.f(r9.b(), "https://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
            if (n2 != null) {
                this.b.b = n2;
                this.a = true;
                this.b.p();
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("fetch config failure: " + e2.getMessage());
        }
    }

    @Override // com.xiaomi.push.l.b
    public void c() {
        List list;
        List list2;
        z0.a[] aVarArr;
        a3 a3Var;
        this.b.f19211c = null;
        if (this.a) {
            synchronized (this.b) {
                list = this.b.a;
                list2 = this.b.a;
                aVarArr = (z0.a[]) list.toArray(new z0.a[list2.size()]);
            }
            for (z0.a aVar : aVarArr) {
                a3Var = this.b.b;
                aVar.b(a3Var);
            }
        }
    }
}
