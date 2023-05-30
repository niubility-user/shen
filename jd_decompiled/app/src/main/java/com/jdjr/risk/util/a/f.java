package com.jdjr.risk.util.a;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes18.dex */
public class f implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "jr-risk" + new Random().nextInt(99));
    }
}
