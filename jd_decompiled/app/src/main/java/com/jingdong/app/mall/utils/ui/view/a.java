package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Scroller;

/* loaded from: classes4.dex */
public class a {
    private c a;
    private GestureDetector b;

    /* renamed from: c  reason: collision with root package name */
    private Scroller f12003c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private float f12004e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f12005f;

    /* renamed from: g  reason: collision with root package name */
    private GestureDetector.SimpleOnGestureListener f12006g = new C0390a();

    /* renamed from: h  reason: collision with root package name */
    private Handler f12007h = new b();

    /* renamed from: com.jingdong.app.mall.utils.ui.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0390a extends GestureDetector.SimpleOnGestureListener {
        C0390a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            a.this.d = 0;
            a.this.f12003c.fling(0, a.this.d, 0, (int) (-f3), 0, 0, -2147483647, Integer.MAX_VALUE);
            a.this.m(0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return true;
        }
    }

    /* loaded from: classes4.dex */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a.this.f12003c.computeScrollOffset();
            int currY = a.this.f12003c.getCurrY();
            int i2 = a.this.d - currY;
            a.this.d = currY;
            if (i2 != 0) {
                a.this.a.c(i2);
            }
            if (Math.abs(currY - a.this.f12003c.getFinalY()) < 1) {
                a.this.f12003c.forceFinished(true);
            }
            if (!a.this.f12003c.isFinished()) {
                a.this.f12007h.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                a.this.j();
            } else {
                a.this.i();
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void a();

        void b();

        void c(int i2);

        void onFinished();
    }

    public a(Context context, c cVar) {
        GestureDetector gestureDetector = new GestureDetector(context, this.f12006g);
        this.b = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.f12003c = new Scroller(context);
        this.a = cVar;
    }

    private void h() {
        this.f12007h.removeMessages(0);
        this.f12007h.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.a.a();
        m(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i2) {
        h();
        this.f12007h.sendEmptyMessage(i2);
    }

    private void n() {
        if (this.f12005f) {
            return;
        }
        this.f12005f = true;
        this.a.b();
    }

    void i() {
        if (this.f12005f) {
            this.a.onFinished();
            this.f12005f = false;
        }
    }

    public boolean k(MotionEvent motionEvent) {
        int y;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f12004e = motionEvent.getY();
            this.f12003c.forceFinished(true);
            h();
        } else if (action == 2 && (y = (int) (motionEvent.getY() - this.f12004e)) != 0) {
            n();
            this.a.c(y);
            this.f12004e = motionEvent.getY();
        }
        if (!this.b.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            j();
        }
        return true;
    }

    public void l(int i2, int i3) {
        this.f12003c.forceFinished(true);
        this.d = 0;
        this.f12003c.startScroll(0, 0, 0, i2, i3 != 0 ? i3 : 400);
        m(0);
        n();
    }

    public void o() {
        this.f12003c.forceFinished(true);
    }
}
