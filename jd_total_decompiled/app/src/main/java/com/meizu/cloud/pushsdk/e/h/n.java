package com.meizu.cloud.pushsdk.e.h;

import java.io.IOException;
import java.io.InterruptedIOException;

/* loaded from: classes14.dex */
public class n {
    private boolean a;
    private long b;

    public void a() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
