package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.jingdong.app.mall.home.o.a.f;

/* loaded from: classes4.dex */
public class HomeSurfaceView extends SurfaceView {

    /* renamed from: g  reason: collision with root package name */
    private int f11029g;

    /* renamed from: h  reason: collision with root package name */
    private int f11030h;

    /* renamed from: i  reason: collision with root package name */
    private int f11031i;

    /* renamed from: j  reason: collision with root package name */
    private int f11032j;

    /* renamed from: k  reason: collision with root package name */
    private int f11033k;

    /* renamed from: l  reason: collision with root package name */
    private int f11034l;

    /* renamed from: m  reason: collision with root package name */
    private int f11035m;

    /* renamed from: n  reason: collision with root package name */
    private int f11036n;
    private int o;
    private ViewOutlineProvider p;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            String str = HomeSurfaceView.this.f11033k + "-" + HomeSurfaceView.this.f11034l + "-" + HomeSurfaceView.this.f11035m + "-" + HomeSurfaceView.this.f11036n;
            outline.setRoundRect(HomeSurfaceView.this.f11033k, HomeSurfaceView.this.f11034l, HomeSurfaceView.this.f11035m, HomeSurfaceView.this.f11036n, HomeSurfaceView.this.o);
        }
    }

    public HomeSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f();
    }

    private void f() {
        setBackgroundColor(0);
        if (f.M0()) {
            this.p = new a();
        }
    }

    public void g(int i2, int i3, int i4, int i5) {
        this.f11029g = (i4 - i2) >> 1;
        this.f11030h = (i5 - i3) >> 1;
        this.f11031i = i4;
        this.f11032j = i5;
        requestLayout();
    }

    public void h(int i2, int i3, int i4, int i5, int i6) {
        if (f.M0()) {
            int i7 = this.f11029g;
            this.f11033k = i2 + i7;
            int i8 = this.f11030h;
            this.f11034l = i3 + i8;
            this.f11035m = i4 - i7;
            this.f11036n = i5 - i8;
            this.o = i6;
            setOutlineProvider(this.p);
            setClipToOutline(true);
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (this.f11032j > 0 && (i4 = this.f11031i) > 0) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f11032j, 1073741824));
        } else {
            super.onMeasure(i2, i3);
        }
    }

    public HomeSurfaceView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        f();
    }
}
