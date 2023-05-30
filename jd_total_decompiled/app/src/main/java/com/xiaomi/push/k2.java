package com.xiaomi.push;

import com.xiaomi.push.i2;
import com.xiaomi.push.l;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class k2 extends l.b {
    l.b a;
    final /* synthetic */ i2 b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k2(i2 i2Var) {
        this.b = i2Var;
    }

    @Override // com.xiaomi.push.l.b
    public void b() {
        i2.b bVar = (i2.b) this.b.a.peek();
        if (bVar == null || !bVar.d()) {
            return;
        }
        if (this.b.a.remove(bVar)) {
            this.a = bVar;
        }
        l.b bVar2 = this.a;
        if (bVar2 != null) {
            bVar2.b();
        }
    }

    @Override // com.xiaomi.push.l.b
    public void c() {
        l.b bVar = this.a;
        if (bVar != null) {
            bVar.c();
        }
    }
}
