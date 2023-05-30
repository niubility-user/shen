package com.xiaomi.push;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class m5 extends Thread {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ l5 f18857g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m5(l5 l5Var, String str) {
        super(str);
        this.f18857g = l5Var;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        g5 g5Var;
        try {
            g5Var = this.f18857g.y;
            g5Var.c();
        } catch (Exception e2) {
            this.f18857g.Q(9, e2);
        }
    }
}
