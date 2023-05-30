package com.jingdong.manto.jsapi.coverview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import com.jingdong.manto.jsapi.container.MantoNativeContainerView;
import com.jingdong.manto.widget.e;
import com.jingdong.manto.widget.input.i;

/* loaded from: classes15.dex */
public class CoverViewContainer extends MantoNativeContainerView implements com.jingdong.manto.jsapi.container.a {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f13180c;
    private View d;

    /* renamed from: e  reason: collision with root package name */
    private float f13181e;

    /* renamed from: f  reason: collision with root package name */
    private float f13182f;

    /* renamed from: g  reason: collision with root package name */
    private int f13183g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f13184h;

    /* renamed from: i  reason: collision with root package name */
    private volatile boolean f13185i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f13186j;

    public CoverViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.f13184h = new Paint();
        this.f13185i = false;
        this.f13186j = false;
        a();
    }

    public CoverViewContainer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 0;
        this.f13184h = new Paint();
        this.f13185i = false;
        this.f13186j = false;
        a();
    }

    public CoverViewContainer(Context context, View view) {
        super(context);
        this.a = 0;
        this.f13184h = new Paint();
        this.f13185i = false;
        this.f13186j = false;
        this.d = view;
        super.addView(view, 0, new FrameLayout.LayoutParams(-1, -1));
        a();
    }

    public static RectF a(View view) {
        view.getLocationOnScreen(new int[2]);
        return new RectF(r0[0], r0[1], r0[0] + view.getWidth(), r0[1] + view.getHeight());
    }

    private void a() {
        this.f13184h.setStyle(Paint.Style.STROKE);
        this.f13184h.setAntiAlias(true);
        setWillNotDraw(false);
        this.a = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 > getChildCount() - 1) {
            i2 = getChildCount() - 1;
        }
        View view2 = this.d;
        if (view2 instanceof com.jingdong.manto.widget.c) {
            ((com.jingdong.manto.widget.c) view2).addView(view, i2 + 1);
        } else {
            super.addView(view, i2 + 1);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 > getChildCount() - 1) {
            i2 = getChildCount() - 1;
        }
        View view2 = this.d;
        if (view2 instanceof com.jingdong.manto.widget.c) {
            ((com.jingdong.manto.widget.c) view2).addView(view, i2 + 1, layoutParams);
        } else {
            super.addView(view, i2 + 1, layoutParams);
        }
    }

    public boolean b() {
        return this.f13185i;
    }

    @Keep
    public final <T extends View> T convertTo(Class<T> cls) {
        try {
            if (cls.isAssignableFrom(this.d.getClass())) {
                return (T) this.d;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View view;
        if (this.f13185i || (((view = this.d) != null && (view instanceof com.jingdong.manto.widget.c)) || (view != null && (view instanceof e)))) {
            if (getParent() instanceof i) {
                ((i) getParent()).a(this, motionEvent, true);
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f13186j) {
            if (actionMasked == 3 || actionMasked == 1) {
                this.f13180c = 0;
                this.b = 0;
                this.f13186j = false;
            }
            return true;
        }
        if (actionMasked == 0) {
            this.b = (int) motionEvent.getX();
            this.f13180c = (int) motionEvent.getY();
            this.f13186j = false;
        } else if (actionMasked == 2) {
            int abs = Math.abs(((int) motionEvent.getY()) - this.f13180c);
            Math.abs(((int) motionEvent.getX()) - this.b);
            if (abs > this.a) {
                this.f13186j = true;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                this.d.dispatchTouchEvent(obtain);
                obtain.recycle();
                if (getParent() instanceof i) {
                    ((i) getParent()).a(this, motionEvent, false);
                }
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Integer num = 1;
        float f2 = 0.0f;
        Integer num2 = this.f13182f > 0.0f ? num : null;
        if (num2 != null) {
            canvas.save();
            Path path = new Path();
            RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            float f3 = this.f13182f;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
            canvas.clipPath(path);
        }
        int i2 = this.f13183g;
        if (i2 != 0) {
            canvas.drawColor(i2);
        }
        float f4 = this.f13181e;
        if (f4 > 0.0f) {
            float f5 = f4 / 2.0f;
            RectF rectF2 = new RectF(f5, f5, getWidth() - f5, getHeight() - f5);
            float f6 = this.f13182f;
            canvas.drawRoundRect(rectF2, f6, f6, this.f13184h);
            if (num2 != null) {
                canvas.restore();
            }
            canvas.save();
            Path path2 = new Path();
            float f7 = this.f13182f;
            if (f7 > 0.0f) {
                float f8 = f7 - this.f13181e;
                if (f8 > 0.0f) {
                    f2 = f8;
                }
            }
            float f9 = this.f13181e;
            path2.addRoundRect(new RectF(f9, f9, getWidth() - this.f13181e, getHeight() - this.f13181e), f2, f2, Path.Direction.CW);
            canvas.clipPath(path2);
        } else {
            num = num2;
        }
        super.draw(canvas);
        if (num != null) {
            canvas.restore();
        }
    }

    @Override // com.jingdong.manto.jsapi.container.a
    public final void setBackGroundColor(int i2) {
        this.f13183g = i2;
    }

    @Override // com.jingdong.manto.jsapi.container.a
    public final void setBorderColor(int i2) {
        this.f13184h.setColor(i2);
    }

    @Override // com.jingdong.manto.jsapi.container.a
    public final void setBorderRadius(float f2) {
        this.f13182f = f2;
    }

    @Override // com.jingdong.manto.jsapi.container.a
    public final void setBorderWidth(float f2) {
        this.f13181e = f2;
        this.f13184h.setStrokeWidth(f2);
    }

    public void setDisableScroll(boolean z) {
        this.f13185i = z;
    }
}
