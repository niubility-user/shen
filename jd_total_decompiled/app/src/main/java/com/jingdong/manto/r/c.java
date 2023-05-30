package com.jingdong.manto.r;

import android.annotation.TargetApi;
import android.view.Choreographer;

@TargetApi(16)
/* loaded from: classes16.dex */
public final class c implements Choreographer.FrameCallback {
    private Choreographer a = Choreographer.getInstance();
    private boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public long f14130c = 200;
    private long d = 0;

    /* renamed from: e  reason: collision with root package name */
    private int f14131e = 0;

    /* renamed from: f  reason: collision with root package name */
    public a f14132f;

    /* loaded from: classes16.dex */
    public interface a {
        void a(double d);
    }

    public final void a() {
        if (this.b) {
            return;
        }
        this.b = true;
        this.a.postFrameCallback(this);
    }

    public final void b() {
        if (this.b) {
            this.b = false;
            this.d = 0L;
            this.f14131e = 0;
            this.a.removeFrameCallback(this);
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j2) {
        if (this.b) {
            long j3 = j2 / 1000000;
            long j4 = this.d;
            if (j4 > 0) {
                long j5 = j3 - j4;
                int i2 = this.f14131e + 1;
                this.f14131e = i2;
                if (j5 > this.f14130c) {
                    double d = i2 * 1000;
                    double d2 = j5;
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    double d3 = d / d2;
                    if (d3 >= 60.0d) {
                        d3 = 60.0d;
                    }
                    this.d = j3;
                    this.f14131e = 0;
                    a aVar = this.f14132f;
                    if (aVar != null) {
                        aVar.a(d3);
                    }
                }
            } else {
                this.d = j3;
            }
        }
        if (this.b) {
            this.a.postFrameCallback(this);
        }
    }
}
