package com.jingdong.manto.widget.input.autofill;

import com.jingdong.manto.q.n;
import com.jingdong.manto.widget.input.l;

/* loaded from: classes16.dex */
public final class b {
    final com.jingdong.manto.widget.input.z.d a;
    final f b;
    public n d;

    /* renamed from: e */
    int f14437e;

    /* renamed from: c */
    public final l.c f14436c = new a();

    /* renamed from: f */
    boolean f14438f = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements l.c {
        a() {
            b.this = r1;
        }

        @Override // com.jingdong.manto.widget.input.l.c
        public void a() {
            b bVar = b.this;
            bVar.f14437e = Integer.MIN_VALUE;
            bVar.a(3);
        }

        @Override // com.jingdong.manto.widget.input.l.c
        public void b() {
            b bVar = b.this;
            bVar.f14437e = Integer.MIN_VALUE;
            bVar.a(3);
        }
    }

    public b(com.jingdong.manto.widget.input.z.d dVar, f fVar) {
        this.a = dVar;
        this.b = fVar;
    }

    public final void a(int i2) {
        f fVar = this.b;
        if (fVar.s != null && fVar.r.isShowing() && 1 == i2 && this.f14438f) {
            this.f14438f = false;
        }
    }
}
