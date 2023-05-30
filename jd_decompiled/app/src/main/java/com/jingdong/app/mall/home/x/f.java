package com.jingdong.app.mall.home.x;

import android.os.SystemClock;

/* loaded from: classes4.dex */
public class f {
    private long a;
    private long b;

    public long a(long j2, long j3, long j4) {
        if (!b(j3)) {
            return j2 + (j4 - SystemClock.elapsedRealtime());
        }
        return this.a;
    }

    public boolean b(long j2) {
        return this.b == j2;
    }

    public void c(long j2, long j3) {
        this.a = j2;
        this.b = j3;
    }
}
