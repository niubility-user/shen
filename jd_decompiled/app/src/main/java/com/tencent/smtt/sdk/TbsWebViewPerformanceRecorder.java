package com.tencent.smtt.sdk;

/* loaded from: classes9.dex */
public class TbsWebViewPerformanceRecorder {
    private long a = 0;
    private long b = 0;

    /* renamed from: c  reason: collision with root package name */
    private long f17782c = 0;
    private long d = 0;

    /* renamed from: e  reason: collision with root package name */
    private long f17783e = 0;

    /* renamed from: f  reason: collision with root package name */
    private String f17784f = "unknown";

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.a = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2, String str) {
        this.d += j2;
        this.f17782c++;
        this.f17783e = j2;
        this.f17784f = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j2) {
        this.b = j2;
    }

    public long getAverageUrlLoadTime() {
        long j2 = this.f17782c;
        if (j2 == 0) {
            return 0L;
        }
        return this.d / j2;
    }

    public long getConstructTime() {
        return this.a;
    }

    public long getCoreInitTime() {
        return this.b;
    }

    public String getCurrentUrl() {
        return this.f17784f;
    }

    public long getCurrentUrlLoadTime() {
        return this.f17783e;
    }

    public String getLog() {
        return "TbsWebViewPerformanceRecorder{constructTime=" + this.a + ", coreInitTime=" + this.b + ", currentUrlLoadTime=" + this.f17783e + ", currentUrl='" + this.f17784f + "'}";
    }
}
