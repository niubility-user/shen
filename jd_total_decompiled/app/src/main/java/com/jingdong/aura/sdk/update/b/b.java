package com.jingdong.aura.sdk.update.b;

/* loaded from: classes4.dex */
public final class b {
    public long a = 300000;
    private long b;

    public final boolean a() {
        if (this.b != 0 && System.currentTimeMillis() - this.b <= this.a) {
            return false;
        }
        this.b = System.currentTimeMillis();
        return true;
    }
}
