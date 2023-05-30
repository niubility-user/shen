package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class t1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f19216g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f19217h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f19218i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ long f19219j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ int f19220k;

    /* renamed from: l  reason: collision with root package name */
    final /* synthetic */ long f19221l;

    /* renamed from: m  reason: collision with root package name */
    final /* synthetic */ int f19222m;

    /* renamed from: n  reason: collision with root package name */
    final /* synthetic */ String f19223n;
    final /* synthetic */ int o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t1(Context context, String str, boolean z, long j2, int i2, long j3, int i3, String str2, int i4) {
        this.f19216g = context;
        this.f19217h = str;
        this.f19218i = z;
        this.f19219j = j2;
        this.f19220k = i2;
        this.f19221l = j3;
        this.f19222m = i3;
        this.f19223n = str2;
        this.o = i4;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            s1.q(this.f19216g, this.f19217h, this.f19218i, this.f19219j, this.f19220k, this.f19221l, this.f19222m, this.f19223n, this.o);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("DisconnectStatsSP onDisconnection exception: " + e2.getMessage());
        }
    }
}
