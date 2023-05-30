package com.jingdong.sdk.jdupgrade.a.i;

/* loaded from: classes7.dex */
public class c extends i {
    private boolean d;

    public c() {
        super("ConfigRequestTask");
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        if (com.jingdong.sdk.jdupgrade.a.j.i.b()) {
            com.jingdong.sdk.jdupgrade.a.h.f a = com.jingdong.sdk.jdupgrade.a.h.f.a(com.jingdong.sdk.jdupgrade.a.j.j.e());
            this.f15087c = a;
            if (a != null) {
                this.d = true;
                jVar.a((j) a);
            }
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        if (!this.d) {
            a("no upgrade info");
            return null;
        }
        if (this.f15087c.a()) {
            this.b.a(b.FORCE);
        }
        return new l();
    }
}
