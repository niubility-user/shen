package com.jingdong.app.mall.home.floor.view.b;

/* loaded from: classes4.dex */
public class b {
    public int a;
    private c b;

    /* renamed from: c  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.e.d f9735c;

    public void a(com.jingdong.app.mall.home.r.e.d dVar, c cVar, int i2) {
        cVar.parseHeight(dVar, i2, false);
        dVar.u(i2);
        this.b = cVar;
        this.f9735c = dVar;
        this.a = i2;
    }

    public void b() {
        com.jingdong.app.mall.home.r.e.d dVar = this.f9735c;
        if (dVar != null) {
            dVar.v(this.a);
            c cVar = this.b;
            if (cVar != null) {
                cVar.parseHeight(this.f9735c, this.a, true);
            }
        }
    }
}
