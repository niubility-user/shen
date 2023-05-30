package com.jingdong.app.mall.home;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class MonitorTouchEventRelativeLayout extends RelativeLayout {

    /* renamed from: j  reason: collision with root package name */
    private static MotionEvent f8550j;

    /* renamed from: g  reason: collision with root package name */
    private boolean f8551g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f8552h;

    /* renamed from: i  reason: collision with root package name */
    private MotionEvent f8553i;

    public MonitorTouchEventRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8551g = false;
        this.f8552h = true;
        this.f8553i = null;
    }

    public static MotionEvent a() {
        return f8550j;
    }

    private void b() {
        com.jingdong.app.mall.home.p.b.d.c.g().n();
    }

    private void c() {
        com.jingdong.app.mall.home.p.b.d.c.g().q();
    }

    public void d(boolean z) {
        float f2;
        float f3;
        this.f8552h = z;
        if (z) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        long uptimeMillis2 = SystemClock.uptimeMillis();
        MotionEvent motionEvent = this.f8553i;
        if (motionEvent != null) {
            f2 = motionEvent.getX();
            f3 = this.f8553i.getY();
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        dispatchTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis2, 3, f2, f3, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (Throwable th) {
            l.j(th);
        }
        if (com.jingdong.app.mall.home.state.dark.a.h()) {
            canvas.drawColor(com.jingdong.app.mall.home.state.dark.a.f());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (Log.D) {
            Log.i("MonitorTouchEventRelativeLayout", "expandXView-mOnDispatchTouchEventListener == null:" + this.f8551g);
        }
        boolean z = false;
        try {
            z = super.dispatchTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            b();
        } else if (action == 1 || action == 3) {
            c();
        }
        if (this.f8551g) {
            return true;
        }
        return z;
    }

    public void e(boolean z) {
        this.f8551g = z;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            i.l(true);
        } else if (action == 1 || action == 3 || action == 4) {
            i.l(false);
        }
        f8550j = motionEvent;
        if (this.f8552h) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f8553i = motionEvent;
        if (this.f8552h) {
            super.onTouchEvent(motionEvent);
            return this.f8551g;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
    }

    public MonitorTouchEventRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f8551g = false;
        this.f8552h = true;
        this.f8553i = null;
    }
}
