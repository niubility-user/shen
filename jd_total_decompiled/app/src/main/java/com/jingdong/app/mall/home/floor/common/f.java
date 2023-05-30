package com.jingdong.app.mall.home.floor.common;

import android.graphics.Rect;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;

/* loaded from: classes4.dex */
public class f {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f9280c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    public Rect f9281e;

    /* renamed from: f  reason: collision with root package name */
    public Rect f9282f;

    /* renamed from: g  reason: collision with root package name */
    private int f9283g = d.f9279g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f9284g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f9285h;

        a(View view, View view2) {
            this.f9284g = view;
            this.f9285h = view2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            f.this.B(this.f9284g, this.f9285h);
        }
    }

    public f(int i2, int i3) {
        this.a = i2;
        this.f9280c = i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(View view, View view2) {
        if (view == null) {
            return;
        }
        if (this.f9281e != null) {
            view.setPadding(q(), s(), r(), p());
        }
        ViewGroup.LayoutParams j2 = j(view, view2);
        if (j2 == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(j2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) j2;
        marginLayoutParams.width = v();
        marginLayoutParams.height = h();
        if (this.f9282f != null) {
            marginLayoutParams.setMargins(l(), n(), m(), k());
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public static void H(f fVar, ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (fVar == null || !fVar.w() || marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.setMargins(fVar.l(), fVar.n(), fVar.m(), fVar.k());
    }

    public static void M(f fVar, View view) {
        if (fVar == null || !fVar.x() || view == null) {
            return;
        }
        view.setPadding(fVar.q(), fVar.s(), fVar.r(), fVar.p());
    }

    public static void O(TextView textView, int i2) {
        if (textView != null) {
            textView.setTextSize(0, d.d(i2));
        }
    }

    public static boolean b(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return false;
        }
        if (layoutParams.width == i2 && layoutParams.height == i3) {
            return false;
        }
        layoutParams.width = i2;
        layoutParams.height = i3;
        view.setLayoutParams(layoutParams);
        return true;
    }

    public static boolean c(View view, f fVar) {
        return d(view, fVar, false);
    }

    public static boolean d(View view, f fVar, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || fVar == null || (layoutParams = view.getLayoutParams()) == null) {
            return false;
        }
        if (fVar.t() == d.f9279g && layoutParams.width == fVar.v() && layoutParams.height == fVar.h() && !z) {
            return false;
        }
        fVar.N(d.f9279g);
        fVar.z(view);
        return true;
    }

    private ViewGroup.LayoutParams j(View view, View view2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            return layoutParams;
        }
        if (view2 instanceof RelativeLayout) {
            return new RelativeLayout.LayoutParams(-2, -2);
        }
        if (view2 instanceof LinearLayout) {
            return new LinearLayout.LayoutParams(-2, -2);
        }
        return view2 instanceof FrameLayout ? new FrameLayout.LayoutParams(-2, -2) : layoutParams;
    }

    public static void y(TextView textView, boolean z) {
        if (textView != null) {
            textView.setSingleLine(true);
            textView.setIncludeFontPadding(false);
            textView.getPaint().setFakeBoldText(z);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(17);
        }
    }

    public void A(View view, View view2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            B(view, view2);
        } else {
            com.jingdong.app.mall.home.o.a.f.E0(new a(view, view2));
        }
    }

    public void C(int i2) {
        this.f9280c = i2;
    }

    public void D(ViewGroup.LayoutParams layoutParams) {
        if (this.f9282f == null || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(l(), n(), m(), k());
    }

    public f E(int i2, int i3, int i4, int i5) {
        Rect rect = this.f9282f;
        if (rect == null) {
            this.f9282f = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        return this;
    }

    public f F(Rect rect) {
        this.f9282f = rect;
        return this;
    }

    public void G(Rect rect, ViewGroup.LayoutParams layoutParams) {
        this.f9282f = rect;
        D(layoutParams);
    }

    public void I(int i2) {
        this.d = i2;
    }

    public f J(int i2, int i3, int i4, int i5) {
        Rect rect = this.f9281e;
        if (rect == null) {
            this.f9281e = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        return this;
    }

    public f K(Rect rect) {
        this.f9281e = rect;
        return this;
    }

    public void L(Rect rect, View view) {
        this.f9281e = rect;
        P(view);
    }

    void N(int i2) {
        this.f9283g = i2;
    }

    public void P(View view) {
        if (view == null || this.f9281e == null) {
            return;
        }
        view.setPadding(q(), s(), r(), p());
    }

    public void Q(int i2) {
        this.a = i2;
    }

    public void R(int i2, int i3) {
        this.a = i2;
        this.f9280c = i3;
    }

    public int e() {
        return this.f9280c;
    }

    public Rect f(boolean z) {
        if (z && this.f9282f == null) {
            this.f9282f = new Rect(0, 0, 0, 0);
        }
        return this.f9282f;
    }

    public FrameLayout.LayoutParams g(@NonNull View view) {
        FrameLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof FrameLayout.LayoutParams) {
            layoutParams = (FrameLayout.LayoutParams) layoutParams2;
            layoutParams.width = v();
            layoutParams.height = h();
        } else {
            layoutParams = new FrameLayout.LayoutParams(v(), h());
        }
        H(this, layoutParams);
        M(this, view);
        N(d.f9279g);
        return layoutParams;
    }

    public int h() {
        int i2 = this.f9280c;
        return i2 > 0 ? d.d(i2) + this.d : i2;
    }

    public LinearLayout.LayoutParams i(@NonNull View view) {
        LinearLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof LinearLayout.LayoutParams) {
            layoutParams = (LinearLayout.LayoutParams) layoutParams2;
            layoutParams.width = v();
            layoutParams.height = h();
        } else {
            layoutParams = new LinearLayout.LayoutParams(v(), h());
        }
        H(this, layoutParams);
        M(this, view);
        N(d.f9279g);
        return layoutParams;
    }

    public int k() {
        Rect rect = this.f9282f;
        if (rect != null) {
            return d.d(rect.bottom);
        }
        return 0;
    }

    public int l() {
        Rect rect = this.f9282f;
        if (rect != null) {
            return d.d(rect.left);
        }
        return 0;
    }

    public int m() {
        Rect rect = this.f9282f;
        if (rect != null) {
            return d.d(rect.right);
        }
        return 0;
    }

    public int n() {
        Rect rect = this.f9282f;
        if (rect != null) {
            return d.d(rect.top);
        }
        return 0;
    }

    public int o() {
        return this.d;
    }

    public int p() {
        Rect rect = this.f9281e;
        if (rect != null) {
            return d.d(rect.bottom);
        }
        return 0;
    }

    public int q() {
        Rect rect = this.f9281e;
        if (rect != null) {
            return d.d(rect.left);
        }
        return 0;
    }

    public int r() {
        Rect rect = this.f9281e;
        if (rect != null) {
            return d.d(rect.right);
        }
        return 0;
    }

    public int s() {
        Rect rect = this.f9281e;
        if (rect != null) {
            return d.d(rect.top);
        }
        return 0;
    }

    int t() {
        return this.f9283g;
    }

    public RelativeLayout.LayoutParams u(@NonNull View view) {
        RelativeLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            layoutParams = (RelativeLayout.LayoutParams) layoutParams2;
            layoutParams.width = v();
            layoutParams.height = h();
        } else {
            layoutParams = new RelativeLayout.LayoutParams(v(), h());
        }
        H(this, layoutParams);
        M(this, view);
        N(d.f9279g);
        return layoutParams;
    }

    public int v() {
        int i2 = this.a;
        return i2 > 0 ? d.d(i2) + this.b : i2;
    }

    public boolean w() {
        return this.f9282f != null;
    }

    public boolean x() {
        return this.f9281e != null;
    }

    public void z(View view) {
        A(view, null);
    }
}
