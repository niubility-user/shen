package com.jd.lib.cashier.sdk.core.aac;

import androidx.annotation.NonNull;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes14.dex */
public abstract class b {
    private LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> a;

    @NonNull
    public LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> a() {
        if (this.a == null) {
            this.a = new LinkedBlockingQueue<>(6);
        }
        return this.a;
    }

    public abstract void b();
}
