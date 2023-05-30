package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.tencent.mapsdk.internal.qa;
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
    */
    private void c() {
        boolean z2;
        double abs;
        boolean z3;
        double abs2;
        boolean z4;
        double abs3;
        double abs4;
        boolean z5;
        double d;
        double d2;
        double d3;
        double d4;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        vf vfVar;
        PointF pointF = this.f17391e;
        float f2 = pointF.x;
        PointF pointF2 = this.f17393g;
        float f3 = f2 - pointF2.x;
        float f4 = pointF.y - pointF2.y;
        PointF pointF3 = this.f17392f;
        float f5 = pointF3.x;
        PointF pointF4 = this.f17394h;
        float f6 = f5 - pointF4.x;
        float f7 = pointF3.y - pointF4.y;
        double abs5 = Math.abs(f3);
        Double.isNaN(abs5);
        if (Math.abs(f4) > abs5 * 1.5d) {
            double abs6 = Math.abs(f6);
            Double.isNaN(abs6);
            if (Math.abs(f7) > abs6 * 1.5d) {
                z2 = true;
                abs = Math.abs(f3);
                Double.isNaN(abs);
                if (Math.abs(f4) > abs * 1.5d) {
                    double abs7 = Math.abs(f6);
                    Double.isNaN(abs7);
                    if (Math.abs(f7) > abs7 * 1.5d) {
                        z3 = true;
                        abs2 = Math.abs(f4);
                        Double.isNaN(abs2);
                        if (Math.abs(f3) > abs2 * 1.5d) {
                            double abs8 = Math.abs(f7);
                            Double.isNaN(abs8);
                            if (Math.abs(f6) > abs8 * 1.5d) {
                                z4 = true;
                                abs3 = Math.abs(f3);
                                abs4 = Math.abs(f4);
                                Double.isNaN(abs4);
                                if (abs3 > abs4 * 1.5d) {
                                    double abs9 = Math.abs(f6);
                                    double abs10 = Math.abs(f7);
                                    Double.isNaN(abs10);
                                    if (abs9 > abs10 * 1.5d) {
                                        z5 = true;
                                        int i2 = ((f3 * f6) > 0.0f ? 1 : ((f3 * f6) == 0.0f ? 0 : -1));
                                        boolean z11 = i2 > 0;
                                        int i3 = ((f4 * f7) > 0.0f ? 1 : ((f4 * f7) == 0.0f ? 0 : -1));
                                        boolean z12 = i3 > 0;
                                        int i4 = this.d;
                                        boolean z13 = ((i4 & 8) != 0 && (i4 & 1) == 0 && (i4 & 4) == 0) ? false : true;
                                        double d5 = z13 ? A : 0.5d;
                                        double max = Math.max(i2 > 0 ? Math.abs(f3 + f6) : Math.max(Math.abs(f3), Math.abs(f6)), i3 > 0 ? Math.abs(f4 + f7) : Math.max(Math.abs(f4), Math.abs(f7)));
                                        boolean z14 = max > d5;
                                        boolean z15 = !z14 && z11 && (z4 || z5) && this.p.get().O();
                                        boolean z16 = !z14 && z12 && (z2 || z3) && this.p.get().f();
                                        PointF pointF5 = this.f17394h;
                                        float f8 = pointF5.x;
                                        PointF pointF6 = this.f17393g;
                                        boolean z17 = z15;
                                        d = f8 - pointF6.x;
                                        d2 = pointF5.y - pointF6.y;
                                        PointF pointF7 = this.f17392f;
                                        float f9 = pointF7.x;
                                        PointF pointF8 = this.f17391e;
                                        boolean z18 = z11;
                                        boolean z19 = z12;
                                        d3 = f9 - pointF8.x;
                                        d4 = pointF7.y - pointF8.y;
                                        Double.isNaN(d);
                                        Double.isNaN(d);
                                        Double.isNaN(d2);
                                        Double.isNaN(d2);
                                        z6 = z16;
                                        double sqrt = Math.sqrt((d * d) + (d2 * d2));
                                        Double.isNaN(d3);
                                        Double.isNaN(d3);
                                        Double.isNaN(d4);
                                        Double.isNaN(d4);
                                        double sqrt2 = Math.sqrt((d3 * d3) + (d4 * d4));
                                        Double.isNaN(d);
                                        Double.isNaN(d3);
                                        Double.isNaN(d2);
                                        Double.isNaN(d4);
                                        double d6 = sqrt * sqrt2;
                                        double d7 = ((d * d3) + (d2 * d4)) / d6;
                                        boolean z20 = z14;
                                        double acos = (Math.acos(d7) * 180.0d) / 3.141592653589793d;
                                        Double.isNaN(d);
                                        Double.isNaN(d4);
                                        Double.isNaN(d2);
                                        Double.isNaN(d3);
                                        if ((d * d4) - (d2 * d3) < 0.0d) {
                                            acos = -acos;
                                        }
                                        boolean z21 = Math.abs(d7) < ((double) H);
                                        double d8 = (this.d & 2) == 0 ? w : 0.5d;
                                        double abs11 = Math.abs(acos);
                                        boolean z22 = d6 <= 0.0d && z21 && Math.abs(acos) > d8 && this.p.get().M();
                                        double d9 = sqrt2 / sqrt;
                                        double d10 = z13 ? z : y;
                                        double d11 = d9 - 1.0d;
                                        double abs12 = Math.abs(d11);
                                        z7 = sqrt <= 0.0d && abs12 > d10 && this.p.get().G();
                                        qa.b g2 = qa.g(la.t);
                                        StringBuilder sb = new StringBuilder();
                                        boolean z23 = z22;
                                        sb.append("value:");
                                        sb.append(max);
                                        sb.append(":");
                                        sb.append(abs12);
                                        sb.append(":");
                                        sb.append(abs11);
                                        g2.a("trace-gesture", "began:" + z20 + ":" + z7 + ":" + z22, sb.toString());
                                        z8 = z23 ? false : z20;
                                        if (z6) {
                                            z10 = false;
                                            z9 = false;
                                            z7 = false;
                                        } else {
                                            z9 = z23;
                                            z10 = z17;
                                        }
                                        qa.g(la.t).a("beganMove:" + z8, "vertical:" + z19, "horizontal:" + z18, "verticalMove:" + z6, "horizontalMove:" + z10);
                                        qa.g(la.t).a("beganRotate:" + z9, "cosValue : " + d7, "cosAngle : " + z21, "angle:" + acos, "rotateJudge : " + d8);
                                        qa.b g3 = qa.g(la.t);
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("beganScale:");
                                        sb2.append(z7);
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append("d1:");
                                        sb3.append(sqrt);
                                        StringBuilder sb4 = new StringBuilder();
                                        sb4.append("scale - 1 = ");
                                        sb4.append(Math.abs(d11));
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append("scaleJudge : ");
                                        sb5.append(d10);
                                        g3.a(sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString());
                                        if (z8) {
                                            vfVar = this;
                                            if (z10) {
                                                vfVar.d |= 8;
                                                qa.g(la.t).a("MT_INTENT_MOVE");
                                                vfVar.f17400n.onScroll((f3 + f6) / 2.0f, (f4 + f7) / 2.0f);
                                            }
                                            if (z6) {
                                                vfVar.d |= 1;
                                                qa.g(la.t).a("MT_INTENT_MOVE_VERTICAL");
                                                PointF pointF9 = vfVar.f17393g;
                                                PointF pointF10 = vfVar.f17391e;
                                                pointF9.set(pointF10.x, pointF10.y);
                                                PointF pointF11 = vfVar.f17394h;
                                                PointF pointF12 = vfVar.f17392f;
                                                pointF11.set(pointF12.x, pointF12.y);
                                                vfVar.f17400n.b(Math.abs(f4) > Math.abs(f7) ? f4 : f7);
                                            }
                                        } else {
                                            vfVar = this;
                                        }
                                        if (z9) {
                                            vfVar.d |= 2;
                                            qa.g(la.t).a("MT_INTENT_ROTATE");
                                            if (z18 && !vfVar.p.get().O()) {
                                                PointF pointF13 = vfVar.f17393g;
                                                PointF pointF14 = vfVar.f17391e;
                                                pointF13.set(pointF14.x, pointF14.y);
                                                PointF pointF15 = vfVar.f17394h;
                                                PointF pointF16 = vfVar.f17392f;
                                                pointF15.set(pointF16.x, pointF16.y);
                                                qa.g(la.t).a("NO_HORIZONAL_ROTATE");
                                                return;
                                            } else if (a()) {
                                                e1 e1Var = vfVar.o;
                                                vfVar.f17397k.set(e1Var == null ? 0 : e1Var.e().width() / 2, vfVar.o == null ? 0 : r5.e().height() / 2);
                                                wf wfVar = vfVar.f17400n;
                                                PointF pointF17 = vfVar.f17397k;
                                                wfVar.a(pointF17, pointF17, (float) acos);
                                            } else {
                                                PointF pointF18 = vfVar.f17395i;
                                                PointF pointF19 = vfVar.f17393g;
                                                float f10 = pointF19.x;
                                                PointF pointF20 = vfVar.f17394h;
                                                pointF18.set((f10 + pointF20.x) / 2.0f, (pointF19.y + pointF20.y) / 2.0f);
                                                PointF pointF21 = vfVar.f17396j;
                                                PointF pointF22 = vfVar.f17391e;
                                                float f11 = pointF22.x;
                                                PointF pointF23 = vfVar.f17392f;
                                                pointF21.set((f11 + pointF23.x) / 2.0f, (pointF22.y + pointF23.y) / 2.0f);
                                                vfVar.f17400n.a(vfVar.f17395i, vfVar.f17396j, (float) acos);
                                            }
                                        }
                                        if (z7) {
                                            vfVar.d |= 4;
                                            qa.g(la.t).a("MT_INTENT_SCALE");
                                            if (z18 && !vfVar.p.get().O()) {
                                                PointF pointF24 = vfVar.f17393g;
                                                PointF pointF25 = vfVar.f17391e;
                                                pointF24.set(pointF25.x, pointF25.y);
                                                PointF pointF26 = vfVar.f17394h;
                                                PointF pointF27 = vfVar.f17392f;
                                                pointF26.set(pointF27.x, pointF27.y);
                                                qa.g(la.t).a("NO_HORIZONAL_SCALE");
                                                return;
                                            }
                                            PointF pointF28 = vfVar.f17395i;
                                            PointF pointF29 = vfVar.f17393g;
                                            float f12 = pointF29.x;
                                            PointF pointF30 = vfVar.f17394h;
                                            pointF28.set((f12 + pointF30.x) / 2.0f, (pointF29.y + pointF30.y) / 2.0f);
                                            PointF pointF31 = vfVar.f17396j;
                                            PointF pointF32 = vfVar.f17391e;
                                            float f13 = pointF32.x;
                                            PointF pointF33 = vfVar.f17392f;
                                            pointF31.set((f13 + pointF33.x) / 2.0f, (pointF32.y + pointF33.y) / 2.0f);
                                            vfVar.f17400n.a(vfVar.f17395i, vfVar.f17396j, sqrt, sqrt2);
                                        }
                                        PointF pointF34 = vfVar.f17393g;
                                        PointF pointF35 = vfVar.f17391e;
                                        pointF34.set(pointF35.x, pointF35.y);
                                        PointF pointF36 = vfVar.f17394h;
                                        PointF pointF37 = vfVar.f17392f;
                                        pointF36.set(pointF37.x, pointF37.y);
                                    }
                                }
                                z5 = false;
                                int i22 = ((f3 * f6) > 0.0f ? 1 : ((f3 * f6) == 0.0f ? 0 : -1));
                                if (i22 > 0) {
                                }
                                int i32 = ((f4 * f7) > 0.0f ? 1 : ((f4 * f7) == 0.0f ? 0 : -1));
                                if (i32 > 0) {
                                }
                                int i42 = this.d;
                                if ((i42 & 8) != 0) {
                                }
                                if (z13) {
                                }
                                double max2 = Math.max(i22 > 0 ? Math.abs(f3 + f6) : Math.max(Math.abs(f3), Math.abs(f6)), i32 > 0 ? Math.abs(f4 + f7) : Math.max(Math.abs(f4), Math.abs(f7)));
                                if (max2 > d5) {
                                }
                                if (z14) {
                                }
                                if (z14) {
                                }
                                PointF pointF52 = this.f17394h;
                                float f82 = pointF52.x;
                                PointF pointF62 = this.f17393g;
                                boolean z172 = z15;
                                d = f82 - pointF62.x;
                                d2 = pointF52.y - pointF62.y;
                                PointF pointF72 = this.f17392f;
                                float f92 = pointF72.x;
                                PointF pointF82 = this.f17391e;
                                boolean z182 = z11;
                                boolean z192 = z12;
                                d3 = f92 - pointF82.x;
                                d4 = pointF72.y - pointF82.y;
                                Double.isNaN(d);
                                Double.isNaN(d);
                                Double.isNaN(d2);
                                Double.isNaN(d2);
                                z6 = z16;
                                double sqrt3 = Math.sqrt((d * d) + (d2 * d2));
                                Double.isNaN(d3);
                                Double.isNaN(d3);
                                Double.isNaN(d4);
                                Double.isNaN(d4);
                                double sqrt22 = Math.sqrt((d3 * d3) + (d4 * d4));
                                Double.isNaN(d);
                                Double.isNaN(d3);
                                Double.isNaN(d2);
                                Double.isNaN(d4);
                                double d62 = sqrt3 * sqrt22;
                                double d72 = ((d * d3) + (d2 * d4)) / d62;
                                boolean z202 = z14;
                                double acos2 = (Math.acos(d72) * 180.0d) / 3.141592653589793d;
                                Double.isNaN(d);
                                Double.isNaN(d4);
                                Double.isNaN(d2);
                                Double.isNaN(d3);
                                if ((d * d4) - (d2 * d3) < 0.0d) {
                                }
                                if (Math.abs(d72) < ((double) H)) {
                                }
                                double d82 = (this.d & 2) == 0 ? w : 0.5d;
                                double abs112 = Math.abs(acos2);
                                if (d62 <= 0.0d) {
                                }
                                double d92 = sqrt22 / sqrt3;
                                double d102 = z13 ? z : y;
                                double d112 = d92 - 1.0d;
                                double abs122 = Math.abs(d112);
                                if (sqrt3 <= 0.0d) {
                                }
                                qa.b g22 = qa.g(la.t);
                                StringBuilder sb6 = new StringBuilder();
                                boolean z232 = z22;
                                sb6.append("value:");
                                sb6.append(max2);
                                sb6.append(":");
                                sb6.append(abs122);
                                sb6.append(":");
                                sb6.append(abs112);
                                g22.a("trace-gesture", "began:" + z202 + ":" + z7 + ":" + z22, sb6.toString());
                                if (z232) {
                                }
                                if (z6) {
                                }
                                qa.g(la.t).a("beganMove:" + z8, "vertical:" + z192, "horizontal:" + z182, "verticalMove:" + z6, "horizontalMove:" + z10);
                                qa.g(la.t).a("beganRotate:" + z9, "cosValue : " + d72, "cosAngle : " + z21, "angle:" + acos2, "rotateJudge : " + d82);
                                qa.b g32 = qa.g(la.t);
                                StringBuilder sb22 = new StringBuilder();
                                sb22.append("beganScale:");
                                sb22.append(z7);
                                StringBuilder sb32 = new StringBuilder();
                                sb32.append("d1:");
                                sb32.append(sqrt3);
                                StringBuilder sb42 = new StringBuilder();
                                sb42.append("scale - 1 = ");
                                sb42.append(Math.abs(d112));
                                StringBuilder sb52 = new StringBuilder();
                                sb52.append("scaleJudge : ");
                                sb52.append(d102);
                                g32.a(sb22.toString(), sb32.toString(), sb42.toString(), sb52.toString());
                                if (z8) {
                                }
                                if (z9) {
                                }
                                if (z7) {
                                }
                                PointF pointF342 = vfVar.f17393g;
                                PointF pointF352 = vfVar.f17391e;
                                pointF342.set(pointF352.x, pointF352.y);
                                PointF pointF362 = vfVar.f17394h;
                                PointF pointF372 = vfVar.f17392f;
                                pointF362.set(pointF372.x, pointF372.y);
                            }
                        }
                        z4 = false;
                        abs3 = Math.abs(f3);
                        abs4 = Math.abs(f4);
                        Double.isNaN(abs4);
                        if (abs3 > abs4 * 1.5d) {
                        }
                        z5 = false;
                        int i222 = ((f3 * f6) > 0.0f ? 1 : ((f3 * f6) == 0.0f ? 0 : -1));
                        if (i222 > 0) {
                        }
                        int i322 = ((f4 * f7) > 0.0f ? 1 : ((f4 * f7) == 0.0f ? 0 : -1));
                        if (i322 > 0) {
                        }
                        int i422 = this.d;
                        if ((i422 & 8) != 0) {
                        }
                        if (z13) {
                        }
                        double max22 = Math.max(i222 > 0 ? Math.abs(f3 + f6) : Math.max(Math.abs(f3), Math.abs(f6)), i322 > 0 ? Math.abs(f4 + f7) : Math.max(Math.abs(f4), Math.abs(f7)));
                        if (max22 > d5) {
                        }
                        if (z14) {
                        }
                        if (z14) {
                        }
                        PointF pointF522 = this.f17394h;
                        float f822 = pointF522.x;
                        PointF pointF622 = this.f17393g;
                        boolean z1722 = z15;
                        d = f822 - pointF622.x;
                        d2 = pointF522.y - pointF622.y;
                        PointF pointF722 = this.f17392f;
                        float f922 = pointF722.x;
                        PointF pointF822 = this.f17391e;
                        boolean z1822 = z11;
                        boolean z1922 = z12;
                        d3 = f922 - pointF822.x;
                        d4 = pointF722.y - pointF822.y;
                        Double.isNaN(d);
                        Double.isNaN(d);
                        Double.isNaN(d2);
                        Double.isNaN(d2);
                        z6 = z16;
                        double sqrt32 = Math.sqrt((d * d) + (d2 * d2));
                        Double.isNaN(d3);
                        Double.isNaN(d3);
                        Double.isNaN(d4);
                        Double.isNaN(d4);
                        double sqrt222 = Math.sqrt((d3 * d3) + (d4 * d4));
                        Double.isNaN(d);
                        Double.isNaN(d3);
                        Double.isNaN(d2);
                        Double.isNaN(d4);
                        double d622 = sqrt32 * sqrt222;
                        double d722 = ((d * d3) + (d2 * d4)) / d622;
                        boolean z2022 = z14;
                        double acos22 = (Math.acos(d722) * 180.0d) / 3.141592653589793d;
                        Double.isNaN(d);
                        Double.isNaN(d4);
                        Double.isNaN(d2);
                        Double.isNaN(d3);
                        if ((d * d4) - (d2 * d3) < 0.0d) {
                        }
                        if (Math.abs(d722) < ((double) H)) {
                        }
                        double d822 = (this.d & 2) == 0 ? w : 0.5d;
                        double abs1122 = Math.abs(acos22);
                        if (d622 <= 0.0d) {
                        }
                        double d922 = sqrt222 / sqrt32;
                        double d1022 = z13 ? z : y;
                        double d1122 = d922 - 1.0d;
                        double abs1222 = Math.abs(d1122);
                        if (sqrt32 <= 0.0d) {
                        }
                        qa.b g222 = qa.g(la.t);
                        StringBuilder sb62 = new StringBuilder();
                        boolean z2322 = z22;
                        sb62.append("value:");
                        sb62.append(max22);
                        sb62.append(":");
                        sb62.append(abs1222);
                        sb62.append(":");
                        sb62.append(abs1122);
                        g222.a("trace-gesture", "began:" + z2022 + ":" + z7 + ":" + z22, sb62.toString());
                        if (z2322) {
                        }
                        if (z6) {
                        }
                        qa.g(la.t).a("beganMove:" + z8, "vertical:" + z1922, "horizontal:" + z1822, "verticalMove:" + z6, "horizontalMove:" + z10);
                        qa.g(la.t).a("beganRotate:" + z9, "cosValue : " + d722, "cosAngle : " + z21, "angle:" + acos22, "rotateJudge : " + d822);
                        qa.b g322 = qa.g(la.t);
                        StringBuilder sb222 = new StringBuilder();
                        sb222.append("beganScale:");
                        sb222.append(z7);
                        StringBuilder sb322 = new StringBuilder();
                        sb322.append("d1:");
                        sb322.append(sqrt32);
                        StringBuilder sb422 = new StringBuilder();
                        sb422.append("scale - 1 = ");
                        sb422.append(Math.abs(d1122));
                        StringBuilder sb522 = new StringBuilder();
                        sb522.append("scaleJudge : ");
                        sb522.append(d1022);
                        g322.a(sb222.toString(), sb322.toString(), sb422.toString(), sb522.toString());
                        if (z8) {
                        }
                        if (z9) {
                        }
                        if (z7) {
                        }
                        PointF pointF3422 = vfVar.f17393g;
                        PointF pointF3522 = vfVar.f17391e;
                        pointF3422.set(pointF3522.x, pointF3522.y);
                        PointF pointF3622 = vfVar.f17394h;
                        PointF pointF3722 = vfVar.f17392f;
                        pointF3622.set(pointF3722.x, pointF3722.y);
                    }
                }
                z3 = false;
                abs2 = Math.abs(f4);
                Double.isNaN(abs2);
                if (Math.abs(f3) > abs2 * 1.5d) {
                }
                z4 = false;
                abs3 = Math.abs(f3);
                abs4 = Math.abs(f4);
                Double.isNaN(abs4);
                if (abs3 > abs4 * 1.5d) {
                }
                z5 = false;
                int i2222 = ((f3 * f6) > 0.0f ? 1 : ((f3 * f6) == 0.0f ? 0 : -1));
                if (i2222 > 0) {
                }
                int i3222 = ((f4 * f7) > 0.0f ? 1 : ((f4 * f7) == 0.0f ? 0 : -1));
                if (i3222 > 0) {
                }
                int i4222 = this.d;
                if ((i4222 & 8) != 0) {
                }
                if (z13) {
                }
                double max222 = Math.max(i2222 > 0 ? Math.abs(f3 + f6) : Math.max(Math.abs(f3), Math.abs(f6)), i3222 > 0 ? Math.abs(f4 + f7) : Math.max(Math.abs(f4), Math.abs(f7)));
                if (max222 > d5) {
                }
                if (z14) {
                }
                if (z14) {
                }
                PointF pointF5222 = this.f17394h;
                float f8222 = pointF5222.x;
                PointF pointF6222 = this.f17393g;
                boolean z17222 = z15;
                d = f8222 - pointF6222.x;
                d2 = pointF5222.y - pointF6222.y;
                PointF pointF7222 = this.f17392f;
                float f9222 = pointF7222.x;
                PointF pointF8222 = this.f17391e;
                boolean z18222 = z11;
                boolean z19222 = z12;
                d3 = f9222 - pointF8222.x;
                d4 = pointF7222.y - pointF8222.y;
                Double.isNaN(d);
                Double.isNaN(d);
                Double.isNaN(d2);
                Double.isNaN(d2);
                z6 = z16;
                double sqrt322 = Math.sqrt((d * d) + (d2 * d2));
                Double.isNaN(d3);
                Double.isNaN(d3);
                Double.isNaN(d4);
                Double.isNaN(d4);
                double sqrt2222 = Math.sqrt((d3 * d3) + (d4 * d4));
                Double.isNaN(d);
                Double.isNaN(d3);
                Double.isNaN(d2);
                Double.isNaN(d4);
                double d6222 = sqrt322 * sqrt2222;
                double d7222 = ((d * d3) + (d2 * d4)) / d6222;
                boolean z20222 = z14;
                double acos222 = (Math.acos(d7222) * 180.0d) / 3.141592653589793d;
                Double.isNaN(d);
                Double.isNaN(d4);
                Double.isNaN(d2);
                Double.isNaN(d3);
                if ((d * d4) - (d2 * d3) < 0.0d) {
                }
                if (Math.abs(d7222) < ((double) H)) {
                }
                double d8222 = (this.d & 2) == 0 ? w : 0.5d;
                double abs11222 = Math.abs(acos222);
                if (d6222 <= 0.0d) {
                }
                double d9222 = sqrt2222 / sqrt322;
                double d10222 = z13 ? z : y;
                double d11222 = d9222 - 1.0d;
                double abs12222 = Math.abs(d11222);
                if (sqrt322 <= 0.0d) {
                }
                qa.b g2222 = qa.g(la.t);
                StringBuilder sb622 = new StringBuilder();
                boolean z23222 = z22;
                sb622.append("value:");
                sb622.append(max222);
                sb622.append(":");
                sb622.append(abs12222);
                sb622.append(":");
                sb622.append(abs11222);
                g2222.a("trace-gesture", "began:" + z20222 + ":" + z7 + ":" + z22, sb622.toString());
                if (z23222) {
                }
                if (z6) {
                }
                qa.g(la.t).a("beganMove:" + z8, "vertical:" + z19222, "horizontal:" + z18222, "verticalMove:" + z6, "horizontalMove:" + z10);
                qa.g(la.t).a("beganRotate:" + z9, "cosValue : " + d7222, "cosAngle : " + z21, "angle:" + acos222, "rotateJudge : " + d8222);
                qa.b g3222 = qa.g(la.t);
                StringBuilder sb2222 = new StringBuilder();
                sb2222.append("beganScale:");
                sb2222.append(z7);
                StringBuilder sb3222 = new StringBuilder();
                sb3222.append("d1:");
                sb3222.append(sqrt322);
                StringBuilder sb4222 = new StringBuilder();
                sb4222.append("scale - 1 = ");
                sb4222.append(Math.abs(d11222));
                StringBuilder sb5222 = new StringBuilder();
                sb5222.append("scaleJudge : ");
                sb5222.append(d10222);
                g3222.a(sb2222.toString(), sb3222.toString(), sb4222.toString(), sb5222.toString());
                if (z8) {
                }
                if (z9) {
                }
                if (z7) {
                }
                PointF pointF34222 = vfVar.f17393g;
                PointF pointF35222 = vfVar.f17391e;
                pointF34222.set(pointF35222.x, pointF35222.y);
                PointF pointF36222 = vfVar.f17394h;
                PointF pointF37222 = vfVar.f17392f;
                pointF36222.set(pointF37222.x, pointF37222.y);
            }
        }
        z2 = false;
        abs = Math.abs(f3);
        Double.isNaN(abs);
        if (Math.abs(f4) > abs * 1.5d) {
        }
        z3 = false;
        abs2 = Math.abs(f4);
        Double.isNaN(abs2);
        if (Math.abs(f3) > abs2 * 1.5d) {
        }
        z4 = false;
        abs3 = Math.abs(f3);
        abs4 = Math.abs(f4);
        Double.isNaN(abs4);
        if (abs3 > abs4 * 1.5d) {
        }
        z5 = false;
        int i22222 = ((f3 * f6) > 0.0f ? 1 : ((f3 * f6) == 0.0f ? 0 : -1));
        if (i22222 > 0) {
        }
        int i32222 = ((f4 * f7) > 0.0f ? 1 : ((f4 * f7) == 0.0f ? 0 : -1));
        if (i32222 > 0) {
        }
        int i42222 = this.d;
        if ((i42222 & 8) != 0) {
        }
        if (z13) {
        }
        double max2222 = Math.max(i22222 > 0 ? Math.abs(f3 + f6) : Math.max(Math.abs(f3), Math.abs(f6)), i32222 > 0 ? Math.abs(f4 + f7) : Math.max(Math.abs(f4), Math.abs(f7)));
        if (max2222 > d5) {
        }
        if (z14) {
        }
        if (z14) {
        }
        PointF pointF52222 = this.f17394h;
        float f82222 = pointF52222.x;
        PointF pointF62222 = this.f17393g;
        boolean z172222 = z15;
        d = f82222 - pointF62222.x;
        d2 = pointF52222.y - pointF62222.y;
        PointF pointF72222 = this.f17392f;
        float f92222 = pointF72222.x;
        PointF pointF82222 = this.f17391e;
        boolean z182222 = z11;
        boolean z192222 = z12;
        d3 = f92222 - pointF82222.x;
        d4 = pointF72222.y - pointF82222.y;
        Double.isNaN(d);
        Double.isNaN(d);
        Double.isNaN(d2);
        Double.isNaN(d2);
        z6 = z16;
        double sqrt3222 = Math.sqrt((d * d) + (d2 * d2));
        Double.isNaN(d3);
        Double.isNaN(d3);
        Double.isNaN(d4);
        Double.isNaN(d4);
        double sqrt22222 = Math.sqrt((d3 * d3) + (d4 * d4));
        Double.isNaN(d);
        Double.isNaN(d3);
        Double.isNaN(d2);
        Double.isNaN(d4);
        double d62222 = sqrt3222 * sqrt22222;
        double d72222 = ((d * d3) + (d2 * d4)) / d62222;
        boolean z202222 = z14;
        double acos2222 = (Math.acos(d72222) * 180.0d) / 3.141592653589793d;
        Double.isNaN(d);
        Double.isNaN(d4);
        Double.isNaN(d2);
        Double.isNaN(d3);
        if ((d * d4) - (d2 * d3) < 0.0d) {
        }
        if (Math.abs(d72222) < ((double) H)) {
        }
        double d82222 = (this.d & 2) == 0 ? w : 0.5d;
        double abs112222 = Math.abs(acos2222);
        if (d62222 <= 0.0d) {
        }
        double d92222 = sqrt22222 / sqrt3222;
        double d102222 = z13 ? z : y;
        double d112222 = d92222 - 1.0d;
        double abs122222 = Math.abs(d112222);
        if (sqrt3222 <= 0.0d) {
        }
        qa.b g22222 = qa.g(la.t);
        StringBuilder sb6222 = new StringBuilder();
        boolean z232222 = z22;
        sb6222.append("value:");
        sb6222.append(max2222);
        sb6222.append(":");
        sb6222.append(abs122222);
        sb6222.append(":");
        sb6222.append(abs112222);
        g22222.a("trace-gesture", "began:" + z202222 + ":" + z7 + ":" + z22, sb6222.toString());
        if (z232222) {
        }
        if (z6) {
        }
        qa.g(la.t).a("beganMove:" + z8, "vertical:" + z192222, "horizontal:" + z182222, "verticalMove:" + z6, "horizontalMove:" + z10);
        qa.g(la.t).a("beganRotate:" + z9, "cosValue : " + d72222, "cosAngle : " + z21, "angle:" + acos2222, "rotateJudge : " + d82222);
        qa.b g32222 = qa.g(la.t);
        StringBuilder sb22222 = new StringBuilder();
        sb22222.append("beganScale:");
        sb22222.append(z7);
        StringBuilder sb32222 = new StringBuilder();
        sb32222.append("d1:");
        sb32222.append(sqrt3222);
        StringBuilder sb42222 = new StringBuilder();
        sb42222.append("scale - 1 = ");
        sb42222.append(Math.abs(d112222));
        StringBuilder sb52222 = new StringBuilder();
        sb52222.append("scaleJudge : ");
        sb52222.append(d102222);
        g32222.a(sb22222.toString(), sb32222.toString(), sb42222.toString(), sb52222.toString());
        if (z8) {
        }
        if (z9) {
        }
        if (z7) {
        }
        PointF pointF342222 = vfVar.f17393g;
        PointF pointF352222 = vfVar.f17391e;
        pointF342222.set(pointF352222.x, pointF352222.y);
        PointF pointF362222 = vfVar.f17394h;
        PointF pointF372222 = vfVar.f17392f;
        pointF362222.set(pointF372222.x, pointF372222.y);
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
