package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class t0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ a f1122g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f1123h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f1124i;

    public t0(o0 o0Var, a aVar, int i2, String str) {
        this.f1122g = aVar;
        this.f1123h = i2;
        this.f1124i = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = this.f1122g;
        if (aVar != null) {
            aVar.onFailure(this.f1123h, this.f1124i);
        }
    }
}
