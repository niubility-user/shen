package com.xiaomi.push;

/* loaded from: classes11.dex */
public class l2 implements g.j.a.a.a.a {
    private g.j.a.a.a.a a;
    private g.j.a.a.a.a b;

    public l2(g.j.a.a.a.a aVar, g.j.a.a.a.a aVar2) {
        this.a = null;
        this.b = null;
        this.a = aVar;
        this.b = aVar2;
    }

    @Override // g.j.a.a.a.a
    public void a(String str, Throwable th) {
        g.j.a.a.a.a aVar = this.a;
        if (aVar != null) {
            aVar.a(str, th);
        }
        g.j.a.a.a.a aVar2 = this.b;
        if (aVar2 != null) {
            aVar2.a(str, th);
        }
    }

    @Override // g.j.a.a.a.a
    public void log(String str) {
        g.j.a.a.a.a aVar = this.a;
        if (aVar != null) {
            aVar.log(str);
        }
        g.j.a.a.a.a aVar2 = this.b;
        if (aVar2 != null) {
            aVar2.log(str);
        }
    }
}
