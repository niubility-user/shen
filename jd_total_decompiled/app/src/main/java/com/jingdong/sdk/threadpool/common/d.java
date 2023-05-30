package com.jingdong.sdk.threadpool.common;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes10.dex */
public class d implements ThreadFactory {

    /* renamed from: g  reason: collision with root package name */
    private volatile int f15547g;

    /* renamed from: h  reason: collision with root package name */
    private String f15548h;

    /* renamed from: i  reason: collision with root package name */
    private int f15549i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f15550j;

    public d(String str, int i2, boolean z) {
        this.f15547g = 0;
        this.f15548h = "DefaultThreadFactory";
        this.f15549i = 5;
        this.f15550j = true;
        if (str != null) {
            String trim = str.trim();
            if (trim.length() > 0) {
                this.f15548h = trim;
            }
        }
        if (i2 < 1 || i2 > 10) {
            this.f15549i = 5;
        }
        this.f15549i = i2;
        this.f15550j = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        this.f15547g++;
        Thread thread = new Thread(runnable, this.f15548h + "-" + this.f15547g);
        thread.setPriority(this.f15549i);
        thread.setDaemon(this.f15550j);
        return thread;
    }

    public d(String str, int i2) {
        this(str, i2, true);
    }
}
