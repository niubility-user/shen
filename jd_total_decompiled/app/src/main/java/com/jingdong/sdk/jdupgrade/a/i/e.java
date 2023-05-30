package com.jingdong.sdk.jdupgrade.a.i;

import android.text.TextUtils;
import com.jingdong.sdk.jdupgrade.UpgradeDialogPopupRequest;
import java.io.File;

/* loaded from: classes7.dex */
class e extends i {
    private boolean d;

    /* renamed from: e */
    private boolean f15078e;

    /* renamed from: f */
    private String f15079f;

    /* renamed from: g */
    private int f15080g;

    /* renamed from: h */
    private String f15081h;

    public e() {
        super("OldPackageCheckTask");
        this.d = false;
        this.f15078e = false;
    }

    private boolean c() {
        String str;
        if (TextUtils.isEmpty(this.f15079f)) {
            str = "no local apk path";
        } else {
            File file = new File(this.f15079f);
            if (file.isFile() && file.canRead()) {
                String a = com.jingdong.sdk.jdupgrade.a.j.d.a(file);
                String str2 = this.f15087c.f15070c.f15060e;
                com.jingdong.sdk.jdupgrade.a.j.h.c("", "filePath:" + this.f15079f + ", md5FromLocal:" + a + ", md5FromServer:" + str2);
                return a.equals(str2);
            }
            str = "local apk is illegal";
        }
        com.jingdong.sdk.jdupgrade.a.j.h.c("", str);
        return false;
    }

    private boolean d() {
        String str;
        String str2;
        if (!this.b.e()) {
            UpgradeDialogPopupRequest r = com.jingdong.sdk.jdupgrade.a.c.r();
            if (r != null && !r.canPopupInstallDialog()) {
                str = "You have disabled the popup of install dialog!";
            } else if (this.b.c().equals(b.FORCE)) {
                return true;
            } else {
                String a = com.jingdong.sdk.jdupgrade.a.j.k.a("INSTALL_REMIND_VERSION", "");
                if (TextUtils.equals(this.f15081h, a)) {
                    long currentTimeMillis = System.currentTimeMillis() - com.jingdong.sdk.jdupgrade.a.j.k.a("INSTALL_REMIND_TIME", 0L);
                    com.jingdong.sdk.jdupgrade.a.h.c cVar = this.f15087c.f15071e;
                    if (currentTimeMillis < cVar.b) {
                        str = "in time interval";
                    } else {
                        int i2 = cVar.f15053c;
                        if (this.f15080g < i2) {
                            return true;
                        }
                        str = "popup times exceed, currentCount: " + this.f15080g + ", maxCount:" + i2;
                    }
                } else {
                    str2 = "version not equals , currentVersion:" + this.f15081h + ", lastVersion:" + a;
                }
            }
            a(str);
            return false;
        }
        str2 = "taskChain is unlimited";
        com.jingdong.sdk.jdupgrade.a.j.h.c("", str2);
        return true;
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public void a(j jVar) {
        super.a(jVar);
        this.f15080g = com.jingdong.sdk.jdupgrade.a.j.k.a("INSTALL_REMIND_COUNT", 0);
        this.f15081h = this.f15087c.f15070c.a + "(O\ufe4f0)" + this.f15087c.f15070c.b;
        this.f15079f = com.jingdong.sdk.jdupgrade.a.j.k.a("LOCAL_APK_STORAGE_PATH", "");
        String a = com.jingdong.sdk.jdupgrade.a.j.k.a("LOCAL_LOADING_APK_STORAGE_PATH", "");
        if (!TextUtils.isEmpty(a) && !a.contains(this.f15087c.f15070c.f15060e)) {
            com.jingdong.sdk.jdupgrade.a.j.h.c("", "remove current loadingApk:" + a + ", target file:" + this.f15087c.f15070c.f15060e);
            com.jingdong.sdk.jdupgrade.a.j.d.a(a);
        }
        this.d = c();
        this.f15078e = d();
        com.jingdong.sdk.jdupgrade.a.j.h.c("", "mode:" + jVar.c() + ", isPopupEnabled:" + this.f15078e + ", isDownloadedApkValid:" + this.d);
    }

    @Override // com.jingdong.sdk.jdupgrade.a.i.i
    public i b() {
        if (!this.d) {
            com.jingdong.sdk.jdupgrade.a.j.d.a(this.f15079f);
            return new g();
        } else if (this.f15078e) {
            if (!this.f15087c.a()) {
                com.jingdong.sdk.jdupgrade.a.j.k.b("INSTALL_REMIND_VERSION", this.f15081h);
                com.jingdong.sdk.jdupgrade.a.j.k.b("INSTALL_REMIND_TIME", System.currentTimeMillis());
                com.jingdong.sdk.jdupgrade.a.j.k.b("INSTALL_REMIND_COUNT", this.f15080g + 1);
            }
            this.b.a(h.MAIN);
            return new d(this.f15079f);
        } else {
            return null;
        }
    }
}
