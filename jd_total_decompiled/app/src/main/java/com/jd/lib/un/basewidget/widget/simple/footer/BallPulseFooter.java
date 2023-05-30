package com.jd.lib.un.basewidget.widget.simple.footer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal;
import com.jd.lib.un.basewidget.widget.simple.c.b;
import com.jd.lib.un.basewidget.widget.simple.c.f;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes16.dex */
public class BallPulseFooter extends AbsRefreshInternal implements b {

    /* renamed from: j  reason: collision with root package name */
    protected boolean f5781j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f5782k;

    /* renamed from: l  reason: collision with root package name */
    protected Paint f5783l;

    /* renamed from: m  reason: collision with root package name */
    protected int f5784m;

    /* renamed from: n  reason: collision with root package name */
    protected int f5785n;
    protected float o;
    protected float[] p;
    protected boolean q;
    protected ArrayList<ValueAnimator> r;
    protected Map<ValueAnimator, ValueAnimator.AnimatorUpdateListener> s;

    /* loaded from: classes16.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f5786g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f5787h;

        a(int i2, View view) {
            this.f5786g = i2;
            this.f5787h = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BallPulseFooter.this.p[this.f5786g] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f5787h.postInvalidate();
        }
    }

    public BallPulseFooter(@NonNull Context context) {
        this(context, null);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.b
    public boolean a(boolean z) {
        return false;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    @Deprecated
    public void c(@ColorInt int... iArr) {
        if (!this.f5782k && iArr.length > 1) {
            r(iArr[0]);
            this.f5782k = false;
        }
        if (this.f5781j) {
            return;
        }
        if (iArr.length > 1) {
            s(iArr[1]);
        } else if (iArr.length > 0) {
            s(ColorUtils.compositeColors(-1711276033, iArr[0]));
        }
        this.f5781j = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float f2 = this.o;
        float min = (Math.min(width, height) - (f2 * 2.0f)) / 6.0f;
        float f3 = 2.0f * min;
        float f4 = (width / 2) - (f2 + f3);
        float f5 = height / 2;
        for (int i2 = 0; i2 < 3; i2++) {
            canvas.save();
            float f6 = i2;
            canvas.translate((f3 * f6) + f4 + (this.o * f6), f5);
            float[] fArr = this.p;
            canvas.scale(fArr[i2], fArr[i2]);
            canvas.drawCircle(0.0f, 0.0f, min, this.f5783l);
            canvas.restore();
        }
        super.dispatchDraw(canvas);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    public void h(@NonNull f fVar, int i2, int i3) {
        if (this.q) {
            return;
        }
        for (int i4 = 0; i4 < this.r.size(); i4++) {
            ValueAnimator valueAnimator = this.r.get(i4);
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.s.get(valueAnimator);
            if (animatorUpdateListener != null) {
                valueAnimator.addUpdateListener(animatorUpdateListener);
            }
            valueAnimator.start();
        }
        this.q = true;
        this.f5783l.setColor(this.f5785n);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.r != null) {
            for (int i2 = 0; i2 < this.r.size(); i2++) {
                this.r.get(i2).cancel();
                this.r.get(i2).removeAllListeners();
                this.r.get(i2).removeAllUpdateListeners();
            }
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    public int q(@NonNull f fVar, boolean z) {
        ArrayList<ValueAnimator> arrayList = this.r;
        if (arrayList != null && this.q) {
            this.q = false;
            this.p = new float[]{1.0f, 1.0f, 1.0f};
            Iterator<ValueAnimator> it = arrayList.iterator();
            while (it.hasNext()) {
                ValueAnimator next = it.next();
                if (next != null) {
                    next.removeAllUpdateListeners();
                    next.end();
                }
            }
        }
        this.f5783l.setColor(this.f5784m);
        return 0;
    }

    public BallPulseFooter r(@ColorInt int i2) {
        this.f5785n = i2;
        this.f5782k = true;
        if (this.q) {
            this.f5783l.setColor(i2);
        }
        return this;
    }

    public BallPulseFooter s(@ColorInt int i2) {
        this.f5784m = i2;
        this.f5781j = true;
        if (!this.q) {
            this.f5783l.setColor(i2);
        }
        return this;
    }

    public BallPulseFooter(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BallPulseFooter(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.f5784m = -1118482;
        this.f5785n = -1615546;
        this.p = new float[]{1.0f, 1.0f, 1.0f};
        this.q = false;
        this.s = new HashMap();
        setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(60.0f));
        Paint paint = new Paint();
        this.f5783l = paint;
        paint.setColor(-1);
        this.f5783l.setStyle(Paint.Style.FILL);
        this.f5783l.setAntiAlias(true);
        this.f5776h = com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE;
        this.o = com.jd.lib.un.basewidget.widget.simple.e.a.a(4.0f);
        this.r = new ArrayList<>();
        int[] iArr = {120, 240, R2.attr.additionBottom};
        for (int i3 = 0; i3 < 3; i3++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.3f, 1.0f);
            ofFloat.setDuration(750L);
            ofFloat.setRepeatCount(-1);
            ofFloat.setTarget(Integer.valueOf(i3));
            ofFloat.setStartDelay(iArr[i3]);
            this.s.put(ofFloat, new a(i3, this));
            this.r.add(ofFloat);
        }
    }
}
