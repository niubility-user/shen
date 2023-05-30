package com.jingdong.manto.sdk.thread;

import android.os.Debug;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes16.dex */
public final class c implements Runnable {
    long a;
    long b;

    /* renamed from: c  reason: collision with root package name */
    final Thread f14197c;
    final Runnable d;

    /* renamed from: e  reason: collision with root package name */
    final Object f14198e;

    /* renamed from: f  reason: collision with root package name */
    final a f14199f;

    /* renamed from: g  reason: collision with root package name */
    long f14200g;

    /* renamed from: h  reason: collision with root package name */
    long f14201h;

    /* renamed from: i  reason: collision with root package name */
    long f14202i;

    /* renamed from: j  reason: collision with root package name */
    float f14203j = -1.0f;

    /* loaded from: classes16.dex */
    public interface a {
        void a(Runnable runnable, c cVar);

        void a(Runnable runnable, Thread thread, long j2, long j3, float f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Thread thread, Handler handler, Runnable runnable, Object obj, a aVar) {
        int indexOf;
        this.f14197c = thread;
        if (thread != null) {
            thread.getName();
            thread.getId();
            thread.getPriority();
        }
        this.d = runnable;
        String name = runnable.getClass().getName();
        String obj2 = runnable.toString();
        if (!TextUtils.isEmpty(obj2) && (indexOf = obj2.indexOf(124)) > 0) {
            String str = name + CartConstant.KEY_YB_INFO_LINK + obj2.substring(indexOf + 1);
        }
        this.f14198e = obj;
        this.f14199f = aVar;
        System.currentTimeMillis();
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuilder sb = new StringBuilder("/proc/self/task/");
        sb.append(Process.myTid());
        sb.append("/stat");
        this.b = System.currentTimeMillis();
        this.f14200g = Debug.threadCpuTimeNanos();
        this.f14201h = -1L;
        this.f14202i = -1L;
        this.d.run();
        this.f14201h = (-1) - this.f14201h;
        this.f14202i = (-1) - this.f14202i;
        long currentTimeMillis = System.currentTimeMillis();
        this.a = currentTimeMillis;
        this.b = currentTimeMillis - this.b;
        this.f14200g = (Debug.threadCpuTimeNanos() - this.f14200g) / 1000000;
        long j2 = this.f14202i;
        if (j2 != 0) {
            this.f14203j = ((float) (this.f14201h * 100)) / ((float) j2);
        }
        a aVar = this.f14199f;
        if (aVar != null) {
            aVar.a(this.d, this);
            this.f14199f.a(this, this.f14197c, this.b, this.f14200g, this.f14203j);
        }
    }
}
