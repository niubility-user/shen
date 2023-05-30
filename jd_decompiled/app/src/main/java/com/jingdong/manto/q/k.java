package com.jingdong.manto.q;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.q.n;

/* loaded from: classes16.dex */
public class k extends j {

    /* renamed from: f  reason: collision with root package name */
    n f14039f;

    /* renamed from: g  reason: collision with root package name */
    private String f14040g;

    /* loaded from: classes16.dex */
    class a implements n.g0 {
        final /* synthetic */ long a;

        a(long j2) {
            this.a = j2;
        }

        @Override // com.jingdong.manto.q.n.g0
        public void onReady() {
            k.this.f14039f.b(this);
            k.this.a.f();
            System.currentTimeMillis();
        }
    }

    public k(Context context, l lVar) {
        super(context, lVar);
    }

    @Override // com.jingdong.manto.q.j
    public final void a() {
        super.a();
        this.f14039f.i();
    }

    @Override // com.jingdong.manto.q.j
    public void a(String str, String str2, int[] iArr) {
        if (j.a(iArr, this.f14039f.hashCode())) {
            this.f14039f.a(str, str2, 0);
        }
    }

    @Override // com.jingdong.manto.q.j
    public boolean a(String str) {
        return TextUtils.equals(str, this.f14040g);
    }

    @Override // com.jingdong.manto.q.j
    public void b(String str) {
        if (this.f14040g == null) {
            this.f14040g = str;
            i().b(str);
            m();
            this.f14039f.a(new a(System.currentTimeMillis()));
        }
    }

    @Override // com.jingdong.manto.q.j
    public final void e() {
        super.e();
        this.f14039f.E();
    }

    @Override // com.jingdong.manto.q.j
    public final void f() {
        super.f();
        this.f14039f.F();
    }

    @Override // com.jingdong.manto.q.j
    public final void g() {
        super.g();
        this.f14039f.G();
    }

    @Override // com.jingdong.manto.q.j
    public View getContentView() {
        return i().l();
    }

    @Override // com.jingdong.manto.q.j
    public n i() {
        if (this.f14039f == null) {
            this.f14039f = this.a.e();
        }
        n nVar = this.f14039f;
        nVar.f14072j = this.a.a;
        return nVar;
    }

    @Override // com.jingdong.manto.q.j
    public String j() {
        return this.f14040g;
    }
}
