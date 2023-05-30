package com.jingdong.manto.widget.input.z;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes16.dex */
public class f implements ViewTreeObserver.OnGlobalLayoutListener {
    private View a;
    private a b;

    /* renamed from: c  reason: collision with root package name */
    private int f14512c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f14513e;

    /* loaded from: classes16.dex */
    public interface a {
        void a();
    }

    private f(View view, a aVar) {
        this.a = view;
        this.b = aVar;
    }

    public static void a(View view, a aVar) {
        f fVar = new f(view, aVar);
        fVar.a.getViewTreeObserver().addOnGlobalLayoutListener(fVar);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        if (!this.f14513e) {
            this.f14513e = true;
        } else if (this.a.getWidth() == this.f14512c && this.a.getHeight() == this.d) {
            return;
        } else {
            this.b.a();
        }
        this.f14512c = this.a.getWidth();
        this.d = this.a.getHeight();
    }
}
