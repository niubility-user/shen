package com.jingdong.sdk.jdupgrade.a.i;

/* loaded from: classes7.dex */
class l extends i {
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f15097e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l() {
        super("UpgradeCheckTask");
        this.d = false;
        this.f15097e = false;
    }

    private boolean c(String str, String str2) {
        return (str + "(O\ufe4f0)" + str2).equals(com.jingdong.sdk.jdupgrade.a.j.k.a("USER_REJECT_VERSION", ""));
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        com.jingdong.sdk.jdupgrade.a.h.f fVar = this.f15087c;
        boolean z = fVar != null && fVar.b();
        this.d = z;
        if (z) {
            com.jingdong.sdk.jdupgrade.a.h.d dVar = this.f15087c.f15070c;
            boolean c2 = c(dVar.a, dVar.b);
            this.f15097e = c2;
            if (c2 && jVar.e() && com.jingdong.sdk.jdupgrade.a.c.O()) {
                a("this is unlimited check and callee ignoreUserRejectInUnlimitedCheck set is true,so ignore user's reject action");
                this.f15097e = false;
            }
        }
        com.jingdong.sdk.jdupgrade.a.j.h.a("", "hasUpgrade:" + this.d + ", isReject:" + this.f15097e);
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        String str;
        if (!this.d) {
            this.b.b(Boolean.FALSE);
            str = "no upgrade";
        } else if (b.NORMAL != this.b.c() || !this.f15097e) {
            return new e();
        } else {
            str = "is rejected";
        }
        a(str);
        return null;
    }
}
