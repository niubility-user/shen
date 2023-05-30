package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class h1 extends b1 {

    /* renamed from: n  reason: collision with root package name */
    b1 f18682n;
    final /* synthetic */ b1 o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h1(f1 f1Var, String str, b1 b1Var) {
        super(str);
        this.o = b1Var;
        this.f18682n = b1Var;
        this.d = this.d;
        if (b1Var != null) {
            this.f18466h = b1Var.f18466h;
        }
    }

    @Override // com.xiaomi.push.b1
    public synchronized ArrayList<String> e(boolean z) {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        b1 b1Var = this.f18682n;
        if (b1Var != null) {
            arrayList.addAll(b1Var.e(true));
        }
        Map<String, b1> map = f1.f18600g;
        synchronized (map) {
            b1 b1Var2 = map.get(this.d);
            if (b1Var2 != null) {
                Iterator<String> it = b1Var2.e(true).iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (arrayList.indexOf(next) == -1) {
                        arrayList.add(next);
                    }
                }
                arrayList.remove(this.d);
                arrayList.add(this.d);
            }
        }
        return arrayList;
    }

    @Override // com.xiaomi.push.b1
    public synchronized void n(String str, a1 a1Var) {
        b1 b1Var = this.f18682n;
        if (b1Var != null) {
            b1Var.n(str, a1Var);
        }
    }

    @Override // com.xiaomi.push.b1
    public boolean u() {
        return false;
    }
}
