package com.jingdong.sdk.jdupgrade.a.i;

import com.jingdong.sdk.jdupgrade.inner.ui.DownloadService;

/* loaded from: classes7.dex */
class f extends i {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f() {
        super("PackageDownloadTask");
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        com.jingdong.sdk.jdupgrade.a.j.j.f();
        if (!this.f15087c.a()) {
            com.jingdong.sdk.jdupgrade.a.j.h.a("", "start download service");
            DownloadService.a(this.f15087c, jVar);
            return;
        }
        jVar.a(h.MAIN);
        com.jingdong.sdk.jdupgrade.a.j.h.a("", "thread executing in " + Thread.currentThread().getName() + ", forceUpgrade, show dialog," + this.f15087c);
        com.jingdong.sdk.jdupgrade.inner.ui.c.a(this.f15087c, jVar.f15091e);
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        return null;
    }
}
