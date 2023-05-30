package com.jd.lib.un.basewidget.widget.simple.b;

/* loaded from: classes16.dex */
public class a implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public long f5778g;

    /* renamed from: h  reason: collision with root package name */
    private Runnable f5779h;

    public a(Runnable runnable, long j2) {
        this.f5779h = null;
        this.f5779h = runnable;
        this.f5778g = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Runnable runnable = this.f5779h;
            if (runnable != null) {
                runnable.run();
                this.f5779h = null;
            }
        } catch (Throwable th) {
            if (th instanceof NoClassDefFoundError) {
                return;
            }
            th.printStackTrace();
        }
    }
}
