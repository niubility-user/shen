package com.hihonor.push.sdk;

import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public final class d1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ j0 f1087g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Callable f1088h;

    public d1(j0 j0Var, Callable callable) {
        this.f1087g = j0Var;
        this.f1088h = callable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f1087g.b(this.f1088h.call());
        } catch (Exception e2) {
            this.f1087g.a(e2);
        }
    }
}
