package com.jingdong.app.mall.bundle.mobileConfig.c;

/* loaded from: classes12.dex */
public class e {
    private long a = 60000;
    private long b;

    public boolean a() {
        if (this.b == 0 || System.currentTimeMillis() - this.b > this.a) {
            this.b = System.currentTimeMillis();
            com.jingdong.app.mall.bundle.mobileConfig.b.a("timer ok");
            return true;
        }
        com.jingdong.app.mall.bundle.mobileConfig.b.b("timer is not ok! interval:" + (System.currentTimeMillis() - this.b));
        return false;
    }
}
