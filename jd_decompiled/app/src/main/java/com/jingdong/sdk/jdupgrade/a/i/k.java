package com.jingdong.sdk.jdupgrade.a.i;

import com.google.common.net.HttpHeaders;
import com.jingdong.sdk.jdupgrade.DownloadView;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;
import com.jingdong.sdk.jdupgrade.inner.ui.DownloadService;

/* loaded from: classes7.dex */
public final class k extends j<com.jingdong.sdk.jdupgrade.a.h.f> {

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.sdk.jdupgrade.a.h.f f15095j;

    /* renamed from: k  reason: collision with root package name */
    private volatile boolean f15096k = false;

    public k(boolean z) {
        a(z);
    }

    public void a(UpgradeEventListener upgradeEventListener) {
        if (!this.f15096k && !DownloadService.e() && !DownloadView.isDownloading()) {
            this.f15091e = upgradeEventListener;
            this.f15096k = true;
            a(b.NORMAL);
            h();
            return;
        }
        if (upgradeEventListener != null) {
            try {
                upgradeEventListener.onMessage("9", "is downloading.");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        com.jingdong.sdk.jdupgrade.a.j.h.c(HttpHeaders.UPGRADE, "Task has started, do nothing.");
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.a
    public void a(com.jingdong.sdk.jdupgrade.a.h.f fVar) {
        this.f15095j = fVar;
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.a
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void b(Boolean bool) {
        if (bool != null) {
            bool.booleanValue();
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.j
    public void g() {
        super.g();
        this.f15096k = false;
        com.jingdong.sdk.jdupgrade.a.j.h.c(HttpHeaders.UPGRADE, "Task has finished.");
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.a
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public com.jingdong.sdk.jdupgrade.a.h.f a() {
        return this.f15095j;
    }
}
