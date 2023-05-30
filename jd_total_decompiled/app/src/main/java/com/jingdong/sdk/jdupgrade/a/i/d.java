package com.jingdong.sdk.jdupgrade.a.i;

import com.jingdong.sdk.jdupgrade.RemindType;

/* loaded from: classes7.dex */
class d extends i {
    private String d;

    /* loaded from: classes7.dex */
    class a extends com.jingdong.sdk.jdupgrade.inner.ui.e {
        final /* synthetic */ j a;

        a(j jVar) {
            this.a = jVar;
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a() {
            com.jingdong.sdk.jdupgrade.a.j.f.a(d.this.d, this.a.f15091e);
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a(boolean z) {
            if (z) {
                com.jingdong.sdk.jdupgrade.a.j.k.b("USER_REJECT_VERSION", d.this.f15087c.f15070c.a + "(O\ufe4f0)" + d.this.f15087c.f15070c.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str) {
        super("InstallRemindTask");
        this.d = str;
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        a aVar = new a(jVar);
        com.jingdong.sdk.jdupgrade.a.h.f fVar = this.f15087c;
        com.jingdong.sdk.jdupgrade.a.h.c cVar = fVar.f15071e;
        String str = fVar.f15072f;
        boolean a2 = cVar.a();
        RemindType remindType = RemindType.INSTALL_REMIND;
        com.jingdong.sdk.jdupgrade.a.h.f fVar2 = this.f15087c;
        com.jingdong.sdk.jdupgrade.inner.ui.c.a(cVar, str, aVar, a2, remindType, fVar2.f15073g, fVar2.a(), this.f15087c.a, jVar.f15091e, com.jingdong.sdk.jdupgrade.a.c.a(jVar));
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        return null;
    }
}
