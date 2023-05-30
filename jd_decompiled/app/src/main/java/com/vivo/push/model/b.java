package com.vivo.push.model;

import android.text.TextUtils;

/* loaded from: classes11.dex */
public final class b {
    private String a;
    private String d;
    private long b = -1;

    /* renamed from: c  reason: collision with root package name */
    private int f18286c = -1;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18287e = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f18288f = false;

    public b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a = str;
            return;
        }
        throw new IllegalAccessError("PushPackageInfo need a non-null pkgName.");
    }

    public final String a() {
        return this.a;
    }

    public final long b() {
        return this.b;
    }

    public final boolean c() {
        return this.f18287e;
    }

    public final boolean d() {
        return this.f18288f;
    }

    public final String toString() {
        return "PushPackageInfo{mPackageName=" + this.a + ", mPushVersion=" + this.b + ", mPackageVersion=" + this.f18286c + ", mInBlackList=" + this.f18287e + ", mPushEnable=" + this.f18288f + "}";
    }

    public final void a(long j2) {
        this.b = j2;
    }

    public final void b(boolean z) {
        this.f18288f = z;
    }

    public final void a(boolean z) {
        this.f18287e = z;
    }

    public final void a(int i2) {
        this.f18286c = i2;
    }

    public final void a(String str) {
        this.d = str;
    }
}
