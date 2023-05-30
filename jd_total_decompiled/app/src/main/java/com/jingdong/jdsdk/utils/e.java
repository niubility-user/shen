package com.jingdong.jdsdk.utils;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes14.dex */
public class e {

    /* loaded from: classes14.dex */
    class a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f12931g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f12932h;

        a(String str, boolean z) {
            this.f12931g = str;
            this.f12932h = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f12931g);
            thread.setDaemon(this.f12932h);
            return thread;
        }
    }

    public static ThreadFactory a(String str, boolean z) {
        return new a(str, z);
    }
}
