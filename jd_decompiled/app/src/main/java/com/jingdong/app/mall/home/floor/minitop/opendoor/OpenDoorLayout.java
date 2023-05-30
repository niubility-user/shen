package com.jingdong.app.mall.home.floor.minitop.opendoor;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.n.h.e;

/* loaded from: classes4.dex */
public class OpenDoorLayout extends RelativeLayout {

    /* renamed from: g */
    private Paint f9631g;

    /* renamed from: h */
    private int[] f9632h;

    /* renamed from: i */
    private float[] f9633i;

    /* renamed from: j */
    private Rect f9634j;

    /* renamed from: k */
    private final OpenDoorView f9635k;

    /* renamed from: l */
    private int f9636l;

    /* renamed from: m */
    private final int f9637m;

    /* renamed from: n */
    private final int f9638n;
    private int o;
    private ValueAnimator p;
    private ValueAnimator q;
    private com.jingdong.app.mall.home.r.d.b r;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
            OpenDoorLayout.this = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            OpenDoorLayout.this.f9634j.set(OpenDoorLayout.this.f9636l - intValue, 0, OpenDoorLayout.this.f9636l - intValue, 0);
            com.jingdong.app.mall.home.floor.minitop.opendoor.a.n().o(intValue);
            e.f(OpenDoorLayout.this.f9635k, true, OpenDoorLayout.this.f9634j, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.r.a.d {
        b() {
            OpenDoorLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.r.a.d
        protected void onEnd(Animator animator, boolean z) {
            OpenDoorLayout.this.f9635k.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
            OpenDoorLayout.this = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            OpenDoorLayout.this.f9634j.set(OpenDoorLayout.this.f9636l - intValue, 0, OpenDoorLayout.this.f9636l - intValue, 0);
            com.jingdong.app.mall.home.floor.minitop.opendoor.a.n().o(intValue);
            e.f(OpenDoorLayout.this.f9635k, true, OpenDoorLayout.this.f9634j, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.r.a.d {
        d() {
            OpenDoorLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.r.a.d
        protected void onEnd(Animator animator, boolean z) {
            com.jingdong.app.mall.home.l.a c2;
            com.jingdong.app.mall.home.floor.minitop.opendoor.a.n().c(true);
            if (OpenDoorLayout.this.r == null || (c2 = com.jingdong.app.mall.home.l.b.b().c(OpenDoorLayout.this.r.b)) == null) {
                return;
            }
            c2.c(101, OpenDoorLayout.this.r.b, 200L);
        }
    }

    private void f(int i2) {
        this.f9631g.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, this.f9632h, this.f9633i, Shader.TileMode.CLAMP));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int width = getWidth();
        if (this.o != width) {
            this.o = width;
            f(width);
        }
        canvas.drawRect(0.0f, 0.0f, width, getHeight(), this.f9631g);
    }

    public void e() {
        ValueAnimator valueAnimator = this.p;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.q != null) {
            return;
        }
        this.f9635k.b(false);
        ValueAnimator ofInt = ValueAnimator.ofInt(this.f9636l, 0);
        this.q = ofInt;
        ofInt.addUpdateListener(new c());
        this.q.addListener(new d());
        this.q.setInterpolator(new AccelerateInterpolator());
        this.q.setDuration(500L);
        this.q.start();
    }

    public void g() {
        int i2 = this.f9637m >> 1;
        this.f9636l = i2;
        ValueAnimator ofInt = ValueAnimator.ofInt(0, i2);
        this.p = ofInt;
        ofInt.addUpdateListener(new a());
        this.p.addListener(new b());
        this.p.setInterpolator(new AccelerateInterpolator());
        this.p.setDuration(500L);
        this.p.start();
    }

    public void h(com.jingdong.app.mall.home.r.d.b bVar) {
        this.f9635k.c(bVar);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(this.f9638n, 1073741824));
    }
}
