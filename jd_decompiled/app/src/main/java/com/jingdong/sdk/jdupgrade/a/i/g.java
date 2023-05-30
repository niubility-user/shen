package com.jingdong.sdk.jdupgrade.a.i;

import android.text.TextUtils;
import com.jingdong.sdk.jdupgrade.UpgradeDialogPopupRequest;

/* loaded from: classes7.dex */
class g extends i {
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f15082e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f15083f;

    /* renamed from: g  reason: collision with root package name */
    private String f15084g;

    /* renamed from: h  reason: collision with root package name */
    private int f15085h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g() {
        super("PreDownloadCheckTask");
        this.d = false;
        this.f15082e = false;
        this.f15083f = false;
    }

    private boolean c() {
        String str;
        String str2;
        if (!this.b.e()) {
            UpgradeDialogPopupRequest r = com.jingdong.sdk.jdupgrade.a.c.r();
            if (r != null && !r.canPopupDownloadDialog()) {
                str = "You have disabled the popup of download dialog!";
            } else if (this.b.c().equals(b.FORCE)) {
                return true;
            } else {
                String a = com.jingdong.sdk.jdupgrade.a.j.k.a("UPGRADE_REMIND_VERSION", "");
                if (TextUtils.equals(this.f15084g, a)) {
                    long currentTimeMillis = System.currentTimeMillis() - com.jingdong.sdk.jdupgrade.a.j.k.a("UPGRADE_REMIND_TIME", 0L);
                    com.jingdong.sdk.jdupgrade.a.h.b bVar = this.f15087c.d;
                    if (currentTimeMillis < bVar.b) {
                        str = "in time interval";
                    } else {
                        int i2 = bVar.f15053c;
                        if (this.f15085h < i2) {
                            return true;
                        }
                        str = "popup times exceed, currentCount: " + this.f15085h + ", maxCount:" + i2;
                    }
                } else {
                    str2 = "version not equals , currentVersion:" + this.f15084g + ", lastVersion:" + a;
                }
            }
            a(str);
            return false;
        }
        str2 = "taskChain is unlimited";
        com.jingdong.sdk.jdupgrade.a.j.h.c("", str2);
        return true;
    }

    private boolean d() {
        String a = com.jingdong.sdk.jdupgrade.a.j.c.a(com.jingdong.sdk.jdupgrade.a.j.a.a((this.f15087c.f15070c.f15059c + com.jingdong.sdk.jdupgrade.a.c.c() + com.jingdong.sdk.jdupgrade.a.c.f()).getBytes(), com.jingdong.sdk.jdupgrade.a.c.e().getBytes(), com.jingdong.sdk.jdupgrade.a.d.a));
        String str = this.f15087c.f15070c.f15061f;
        boolean equalsIgnoreCase = a.equalsIgnoreCase(str);
        if (!equalsIgnoreCase) {
            a("sign is not valid, localSign:" + a + ", serverSign:" + str);
        }
        return equalsIgnoreCase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        this.f15084g = this.f15087c.f15070c.a + "(O\ufe4f0)" + this.f15087c.f15070c.b;
        boolean z = false;
        this.f15085h = com.jingdong.sdk.jdupgrade.a.j.k.a("UPGRADE_REMIND_COUNT", 0);
        if (com.jingdong.sdk.jdupgrade.a.c.M() && com.jingdong.sdk.jdupgrade.a.j.i.b() && com.jingdong.sdk.jdupgrade.a.j.i.c()) {
            z = true;
        }
        this.d = z;
        this.f15082e = c();
        this.f15083f = d();
        com.jingdong.sdk.jdupgrade.a.j.h.c("", "mode:" + jVar.c() + ", isAutoDownloadEnabled:" + this.d + ", isPopupEnabled:" + this.f15082e + ", isUrlSignValid:" + this.f15083f);
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        if (this.f15083f) {
            if (this.d) {
                return new f();
            }
            if (this.f15082e) {
                if (!this.f15087c.a()) {
                    com.jingdong.sdk.jdupgrade.a.j.k.b("UPGRADE_REMIND_VERSION", this.f15084g);
                    com.jingdong.sdk.jdupgrade.a.j.k.b("UPGRADE_REMIND_TIME", System.currentTimeMillis());
                    com.jingdong.sdk.jdupgrade.a.j.k.b("UPGRADE_REMIND_COUNT", this.f15085h + 1);
                }
                this.b.a(h.MAIN);
                return new m();
            }
            return null;
        }
        return null;
    }
}
