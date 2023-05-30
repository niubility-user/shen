package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class vf implements View.OnTouchListener {
    private static final double A = 0.1d;
    private static final double B = 0.5d;
    private static final int C = 0;
    private static final int D = 1;
    private static final int E = 2;
    private static final int F = 4;
    private static final int G = 8;
    private static final float H = (float) Math.cos(0.0017453292780017621d);
    private static final int I = 10;
    private static final int J = 120;
    private static final int K = 50;
    private static final int t = 255;
    private static final int u = 5;
    private static final int v = 6;
    private static final double w = 2.5d;
    private static final double x = 0.5d;
    private static final double y = 0.003d;
    private static final double z = 0.001d;
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private long f17390c;
    private int d = 0;

    /* renamed from: e  reason: collision with root package name */
    private final PointF f17391e = new PointF();

    /* renamed from: f  reason: collision with root package name */
    private final PointF f17392f = new PointF();

    /* renamed from: g  reason: collision with root package name */
    private final PointF f17393g = new PointF();

    /* renamed from: h  reason: collision with root package name */
    private final PointF f17394h = new PointF();

    /* renamed from: i  reason: collision with root package name */
    private final PointF f17395i = new PointF();

    /* renamed from: j  reason: collision with root package name */
    private final PointF f17396j = new PointF();

    /* renamed from: k  reason: collision with root package name */
    private final PointF f17397k = new PointF();

    /* renamed from: l  reason: collision with root package name */
    private long f17398l = 0;

    /* renamed from: m  reason: collision with root package name */
    private final GestureDetector f17399m;

    /* renamed from: n  reason: collision with root package name */
    private final wf f17400n;
    private final e1 o;
    private final WeakReference<ej> p;
    private final b q;
    private Method r;
    private Method s;

    /* loaded from: classes9.dex */
    public class b extends GestureDetector.SimpleOnGestureListener {
        private PointF a;
        private boolean b;

        private b() {
            this.a = new PointF();
            this.b = true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                this.b = true;
                vf.this.f17399m.setIsLongpressEnabled(false);
                this.a.set(motionEvent.getX(), motionEvent.getY());
                vf.this.f17400n.c(x, y);
            } else if (action == 1) {
                if (this.b) {
                    vf.this.f17400n.onDoubleTap(x, y);
                }
                this.a.set(0.0f, 0.0f);
                vf.this.f17399m.setIsLongpressEnabled(true);
                vf.this.f17400n.a(x, y);
            } else if (action == 2) {
                PointF pointF = this.a;
                float f2 = y - pointF.y;
                if (Math.abs(x - pointF.x) > 10.0f || Math.abs(f2) > 10.0f) {
                    this.b = false;
                    vf.this.f17400n.d(x, y);
                }
                vf.this.f17399m.setIsLongpressEnabled(true);
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            vf.this.f17400n.onFling(f2, f3);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (vf.this.a) {
                return;
            }
            vf.this.f17400n.onLongPress(motionEvent.getX(), motionEvent.getY());
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (vf.this.p != null && vf.this.p.get() != null && ((ej) vf.this.p.get()).Y()) {
                double sqrt = Math.sqrt((f2 * f2) + (f3 * f3));
                if ((motionEvent != null && (motionEvent.getX() < 0.0f || motionEvent.getY() < 0.0f)) || ((motionEvent2 != null && (motionEvent2.getX() < 0.0f || motionEvent2.getY() < 0.0f)) || sqrt > 50.0d)) {
                    return true;
                }
            }
            vf.this.f17400n.onScroll(-f2, -f3);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            vf.this.f17400n.onSingleTap(motionEvent.getX(), motionEvent.getY());
            return true;
        }
    }

    public vf(ej ejVar) {
        b bVar = new b();
        this.q = bVar;
        this.p = new WeakReference<>(ejVar);
        GestureDetector gestureDetector = new GestureDetector(ejVar.getContext(), bVar);
        this.f17399m = gestureDetector;
        this.f17400n = new wf();
        this.o = ejVar.getMapContext();
        gestureDetector.setOnDoubleTapListener(bVar);
    }

    private void a(PointF pointF, PointF pointF2, MotionEvent motionEvent) {
        try {
            float x2 = motionEvent.getX(0);
            float x3 = motionEvent.getX(1);
            float y2 = motionEvent.getY(0);
            float y3 = motionEvent.getY(1);
            pointF.set(x2, y2);
            pointF2.set(x3, y3);
        } catch (Exception unused) {
        }
    }

    private boolean a() {
        PointF a2 = wa.a(this.f17394h, this.f17393g, this.f17392f, this.f17391e);
        if (a2 == null) {
            return false;
        }
        return a(a2.x, a2.y);
    }

    private boolean a(float f2, float f3) {
        e1 e1Var = this.o;
        int width = e1Var == null ? 0 : e1Var.e().width() / 2;
        e1 e1Var2 = this.o;
        int height = e1Var2 == null ? 0 : e1Var2.e().height() / 2;
        float width2 = this.o == null ? 0.0f : r3.e().width() / 3.0f;
        e1 e1Var3 = this.o;
        return Math.abs(f2 - ((float) width)) < width2 && Math.abs(f3 - ((float) height)) < (e1Var3 != null ? ((float) e1Var3.e().height()) / 3.0f : 0.0f);
    }

    private boolean b() {
        PointF pointF = this.f17393g;
        float f2 = pointF.x;
        PointF pointF2 = this.f17394h;
        double d = f2 - pointF2.x;
        double d2 = pointF.y - pointF2.y;
        Double.isNaN(d);
        Double.isNaN(d);
        Double.isNaN(d2);
        Double.isNaN(d2);
        return (d * d) + (d2 * d2) > 2500.0d;
    }

    private boolean b(float f2, float f3) {
        return a(f2, f3);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0499  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0147 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0220 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x025c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c() {
        /*
            Method dump skipped, instructions count: 1518
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.vf.c():void");
    }

    public void a(v4 v4Var) {
        synchronized (this.f17400n) {
            this.f17400n.a(v4Var);
        }
    }

    public void b(v4 v4Var) {
        synchronized (this.f17400n) {
            this.f17400n.b(v4Var);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        if (action == 5) {
                            this.f17398l = SystemClock.elapsedRealtime();
                            this.d = 0;
                            this.a = true;
                            this.f17390c = SystemClock.elapsedRealtime();
                            this.b = false;
                            a(this.f17393g, this.f17394h, motionEvent);
                            this.f17400n.b();
                            return true;
                        } else if (action == 6 && !this.b) {
                            this.b = true;
                            this.f17400n.d();
                            return true;
                        }
                    }
                } else if (this.a && !this.b) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - this.f17390c < 8) {
                        return true;
                    }
                    this.f17390c = elapsedRealtime;
                    a(this.f17391e, this.f17392f, motionEvent);
                    c();
                    return true;
                } else {
                    this.f17400n.b(motionEvent.getX(), motionEvent.getY());
                }
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - this.f17398l;
            if (this.d == 0 && elapsedRealtime2 > 0 && elapsedRealtime2 < 200 && b()) {
                this.f17400n.a();
            }
            this.f17400n.onUp(motionEvent.getX(), motionEvent.getY());
        } else {
            this.f17398l = 0L;
            this.a = false;
            this.f17400n.onDown(motionEvent.getX(), motionEvent.getY());
        }
        if (!this.a) {
            this.f17399m.onTouchEvent(motionEvent);
        }
        return true;
    }
}
