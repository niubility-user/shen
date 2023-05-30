package com.jingdong.manto.q;

import android.view.View;
import com.jingdong.manto.q.g;

/* loaded from: classes16.dex */
class c implements g.a {
    int a;
    int b;

    /* renamed from: c  reason: collision with root package name */
    View f14011c;
    int d;

    /* renamed from: e  reason: collision with root package name */
    int f14012e;

    /* renamed from: f  reason: collision with root package name */
    private com.jingdong.manto.widget.input.i f14013f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(com.jingdong.manto.widget.input.i iVar) {
        this.f14013f = iVar;
    }

    @Override // com.jingdong.manto.q.g.a
    public final void a(int i2, int i3) {
        this.a = i2;
        this.b = i3;
        this.f14011c.setX(this.d + i2);
        this.f14011c.setY(this.f14012e + i3);
        this.f14013f.invalidate();
    }
}
