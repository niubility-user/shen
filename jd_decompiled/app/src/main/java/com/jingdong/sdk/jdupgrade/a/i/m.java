package com.jingdong.sdk.jdupgrade.a.i;

import com.jingdong.sdk.jdupgrade.RemindType;
import com.jingdong.sdk.jdupgrade.inner.ui.DownloadService;

/* loaded from: classes7.dex */
class m extends i {

    /* loaded from: classes7.dex */
    class a extends com.jingdong.sdk.jdupgrade.inner.ui.e {
        final /* synthetic */ j a;

        a(j jVar) {
            this.a = jVar;
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a() {
            com.jingdong.sdk.jdupgrade.a.j.j.f();
            if (m.this.f15087c.a()) {
                com.jingdong.sdk.jdupgrade.inner.ui.c.a(m.this.f15087c, this.a.f15091e);
            } else {
                DownloadService.a(m.this.f15087c, this.a);
            }
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a(boolean z) {
            if (z) {
                com.jingdong.sdk.jdupgrade.a.j.k.b("USER_REJECT_VERSION", m.this.f15087c.f15070c.a + "(O\ufe4f0)" + m.this.f15087c.f15070c.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m() {
        super("UpgradeRemindTask");
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        a aVar = new a(jVar);
        com.jingdong.sdk.jdupgrade.a.h.f fVar = this.f15087c;
        com.jingdong.sdk.jdupgrade.a.h.b bVar = fVar.d;
        String str = fVar.f15072f;
        boolean a2 = bVar.a();
        RemindType remindType = RemindType.UPGRADE_REMIND;
        com.jingdong.sdk.jdupgrade.a.h.f fVar2 = this.f15087c;
        com.jingdong.sdk.jdupgrade.inner.ui.c.a(bVar, str, aVar, a2, remindType, fVar2.f15073g, fVar2.a(), this.f15087c.a, jVar.f15091e, com.jingdong.sdk.jdupgrade.a.c.a(jVar));
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        return null;
    }
}
