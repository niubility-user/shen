package com.jingdong.sdk.perfmonitor;

import android.os.SystemClock;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.b.e;

/* loaded from: classes12.dex */
public class Trace {
    private e a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private OnTraceEvent f15277c;
    private long d;

    /* loaded from: classes12.dex */
    interface OnTraceEvent {
        void onTraceStart(long j2);

        void onTraceStop(long j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Trace(e eVar, String str, OnTraceEvent onTraceEvent) {
        this.a = eVar;
        this.b = str;
        this.f15277c = onTraceEvent;
    }

    public void start() {
        String str;
        if (this.a == null || (str = this.b) == null) {
            return;
        }
        OKLog.d("PerfMonitor", String.format("start custom trace: key = %s", str));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.d = elapsedRealtime;
        OnTraceEvent onTraceEvent = this.f15277c;
        if (onTraceEvent != null) {
            onTraceEvent.onTraceStart(elapsedRealtime);
        }
    }

    public void stop() {
        String str;
        if (this.a == null || (str = this.b) == null) {
            return;
        }
        OKLog.d("PerfMonitor", String.format("stop custom trace: key = %s", str));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        OnTraceEvent onTraceEvent = this.f15277c;
        if (onTraceEvent != null) {
            onTraceEvent.onTraceStop(elapsedRealtime);
        }
        this.a.m(this.b, elapsedRealtime - this.d);
    }
}
