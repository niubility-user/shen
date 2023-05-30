package com.jingdong.sdk.jdupgrade.a;

import com.jingdong.sdk.jdupgrade.UpgradeCallback;
import com.jingdong.sdk.jdupgrade.VersionInfo;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.i;
import com.jingdong.sdk.jdupgrade.a.j.j;

/* loaded from: classes7.dex */
public class a {
    private UpgradeCallback a;

    /* renamed from: com.jingdong.sdk.jdupgrade.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    class RunnableC0725a implements Runnable {
        RunnableC0725a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar;
            try {
                if (i.b()) {
                    com.jingdong.sdk.jdupgrade.a.h.f a = com.jingdong.sdk.jdupgrade.a.h.f.a(j.e());
                    if (a != null) {
                        new VersionInfo().state = Integer.valueOf(a.a.a()).intValue();
                        boolean b = a.b();
                        String str = a.b;
                        com.jingdong.sdk.jdupgrade.a.h.d dVar = a.f15070c;
                        String str2 = dVar != null ? dVar.a : null;
                        if (a.this.a != null) {
                            a.this.a.onChecked(b, str, str2);
                            return;
                        }
                        return;
                    }
                    h.b("HasNewVersion", "upgrade info null");
                    if (a.this.a == null) {
                        return;
                    }
                    aVar = a.this;
                } else {
                    h.b("HasNewVersion", "network not available");
                    if (a.this.a == null) {
                        return;
                    }
                    aVar = a.this;
                }
                aVar.a.onChecked(false, null, null);
            } catch (Throwable th) {
                th.printStackTrace();
                h.b("HasNewVersion", "error:" + th.getMessage());
                if (a.this.a != null) {
                    a.this.a.onChecked(false, null, null);
                }
            }
        }
    }

    public a(UpgradeCallback upgradeCallback) {
        this.a = upgradeCallback;
    }

    public void a() {
        if (this.a == null) {
            return;
        }
        j.c().execute(new RunnableC0725a());
    }
}
