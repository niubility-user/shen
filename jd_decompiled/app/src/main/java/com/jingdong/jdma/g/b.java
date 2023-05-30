package com.jingdong.jdma.g;

import com.jingdong.jdma.common.utils.c;

/* loaded from: classes12.dex */
public class b {
    private long a = System.currentTimeMillis();
    private long b = System.currentTimeMillis();

    /* renamed from: c  reason: collision with root package name */
    private boolean f12756c = true;

    public void a() {
        this.f12756c = false;
        this.a = System.currentTimeMillis();
    }

    public boolean b() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f12756c) {
            long j2 = this.a;
            if (j2 > 0) {
                long j3 = c.o;
                if (currentTimeMillis - j2 > j3 && currentTimeMillis - this.b > j3 && j3 > 0) {
                    z = true;
                    this.f12756c = true;
                    this.b = currentTimeMillis;
                    return z;
                }
            }
        }
        z = false;
        this.f12756c = true;
        this.b = currentTimeMillis;
        return z;
    }
}
