package com.jingdong.sdk.jdupgrade.a.i;

import com.jingdong.sdk.jdupgrade.UpgradeEventListener;

/* loaded from: classes7.dex */
public abstract class i {
    private String a;
    public j b;

    /* renamed from: c */
    com.jingdong.sdk.jdupgrade.a.h.f f15087c;

    public i(String str) {
        this.a = str;
    }

    public String a() {
        return this.a;
    }

    public void a(j jVar) {
        this.b = jVar;
        this.f15087c = (com.jingdong.sdk.jdupgrade.a.h.f) jVar.a();
        com.jingdong.sdk.jdupgrade.a.j.h.a("Task", this.a + " executing in " + Thread.currentThread().getName());
    }

    public void a(String str) {
        com.jingdong.sdk.jdupgrade.a.j.h.c("Task", this.a + " onMessage: " + str);
        try {
            UpgradeEventListener upgradeEventListener = this.b.f15091e;
            if (upgradeEventListener != null) {
                upgradeEventListener.onMessage("100", str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public abstract i b();
}
