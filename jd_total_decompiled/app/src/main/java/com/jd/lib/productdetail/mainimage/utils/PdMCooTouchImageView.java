package com.jd.lib.productdetail.mainimage.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RootDrawable;
import com.facebook.drawee.view.SimpleDraweeView;

/* loaded from: classes15.dex */
public class PdMCooTouchImageView extends SimpleDraweeView implements GenericDraweeHierarchy.ChangeImageListener {
    public static final /* synthetic */ int J = 0;
    public float A;
    public float B;
    public float C;
    public ScaleGestureDetector D;
    public GestureDetector E;
    public GestureDetector.OnDoubleTapListener F;
    public View.OnTouchListener G;
    public f H;
    public g I;

    /* renamed from: g  reason: collision with root package name */
    public float f5214g;

    /* renamed from: h  reason: collision with root package name */
    public Matrix f5215h;

    /* renamed from: i  reason: collision with root package name */
    public Matrix f5216i;

    /* renamed from: j  reason: collision with root package name */
    public j f5217j;

    /* renamed from: k  reason: collision with root package name */
    public float f5218k;

    /* renamed from: l  reason: collision with root package name */
    public float f5219l;

    /* renamed from: m  reason: collision with root package name */
    public float f5220m;

    /* renamed from: n  reason: collision with root package name */
    public float f5221n;
    public float[] o;
    public Context p;
    public d q;
    public ImageView.ScaleType r;
    public boolean s;
    public boolean t;
    public k u;
    public int v;
    public int w;
    public int x;
    public int y;
    public float z;

    /* loaded from: classes15.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @TargetApi(9)
    /* loaded from: classes15.dex */
    public class b {
        public Scroller a;
        public OverScroller b;

        /* renamed from: c  reason: collision with root package name */
        public boolean f5222c;

        public b(PdMCooTouchImageView pdMCooTouchImageView, Context context) {
            if (Build.VERSION.SDK_INT < 9) {
                this.f5222c = true;
                this.a = new Scroller(context);
                return;
            }
            this.f5222c = false;
            this.b = new OverScroller(context);
        }

        public void a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (this.f5222c) {
                this.a.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            } else {
                this.b.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public long f5223g;

        /* renamed from: h  reason: collision with root package name */
        public float f5224h;

        /* renamed from: i  reason: collision with root package name */
        public float f5225i;

        /* renamed from: j  reason: collision with root package name */
        public float f5226j;

        /* renamed from: k  reason: collision with root package name */
        public float f5227k;

        /* renamed from: l  reason: collision with root package name */
        public boolean f5228l;

        /* renamed from: m  reason: collision with root package name */
        public AccelerateDecelerateInterpolator f5229m = new AccelerateDecelerateInterpolator();

        /* renamed from: n  reason: collision with root package name */
        public PointF f5230n;
        public PointF o;

        public c(float f2, float f3, float f4, boolean z) {
            PdMCooTouchImageView.this.w(j.ANIMATE_ZOOM);
            this.f5223g = System.currentTimeMillis();
            this.f5224h = PdMCooTouchImageView.this.f5214g;
            this.f5225i = f2;
            this.f5228l = z;
            PointF c2 = PdMCooTouchImageView.this.c(f3, f4, false);
            float f5 = c2.x;
            this.f5226j = f5;
            float f6 = c2.y;
            this.f5227k = f6;
            this.f5230n = PdMCooTouchImageView.d(PdMCooTouchImageView.this, f5, f6);
            this.o = new PointF(PdMCooTouchImageView.this.v / 2, PdMCooTouchImageView.this.w / 2);
        }

        @Override // java.lang.Runnable
        public void run() {
            float interpolation = this.f5229m.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f5223g)) / 500.0f));
            float f2 = this.f5224h;
            double d = f2 + ((this.f5225i - f2) * interpolation);
            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
            double d2 = pdMCooTouchImageView.f5214g;
            Double.isNaN(d);
            Double.isNaN(d2);
            pdMCooTouchImageView.g(d / d2, this.f5226j, this.f5227k, this.f5228l);
            PointF pointF = this.f5230n;
            float f3 = pointF.x;
            PointF pointF2 = this.o;
            float f4 = f3 + ((pointF2.x - f3) * interpolation);
            float f5 = pointF.y;
            float f6 = f5 + ((pointF2.y - f5) * interpolation);
            PointF d3 = PdMCooTouchImageView.d(PdMCooTouchImageView.this, this.f5226j, this.f5227k);
            PdMCooTouchImageView.this.f5215h.postTranslate(f4 - d3.x, f6 - d3.y);
            PdMCooTouchImageView.this.n();
            PdMCooTouchImageView pdMCooTouchImageView2 = PdMCooTouchImageView.this;
            pdMCooTouchImageView2.setImageMatrix(pdMCooTouchImageView2.f5215h);
            f fVar = PdMCooTouchImageView.this.H;
            if (fVar != null) {
                fVar.a();
            }
            if (interpolation >= 1.0f) {
                PdMCooTouchImageView.this.w(j.NONE);
            } else {
                PdMCooTouchImageView.l(PdMCooTouchImageView.this, this);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public b f5231g;

        /* renamed from: h  reason: collision with root package name */
        public int f5232h;

        /* renamed from: i  reason: collision with root package name */
        public int f5233i;

        public d(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            PdMCooTouchImageView.this.w(j.FLING);
            this.f5231g = new b(PdMCooTouchImageView.this, PdMCooTouchImageView.this.p);
            PdMCooTouchImageView.this.f5215h.getValues(PdMCooTouchImageView.this.o);
            int i8 = (int) PdMCooTouchImageView.this.o[2];
            int i9 = (int) PdMCooTouchImageView.this.o[5];
            if (PdMCooTouchImageView.this.getImageWidth() > PdMCooTouchImageView.this.v) {
                i4 = PdMCooTouchImageView.this.v - ((int) PdMCooTouchImageView.this.getImageWidth());
                i5 = 0;
            } else {
                i4 = i8;
                i5 = i4;
            }
            if (PdMCooTouchImageView.this.getImageHeight() > PdMCooTouchImageView.this.w) {
                i6 = PdMCooTouchImageView.this.w - ((int) PdMCooTouchImageView.this.getImageHeight());
                i7 = 0;
            } else {
                i6 = i9;
                i7 = i6;
            }
            this.f5231g.a(i8, i9, i2, i3, i4, i5, i6, i7);
            this.f5232h = i8;
            this.f5233i = i9;
        }

        public void a() {
            if (this.f5231g != null) {
                PdMCooTouchImageView.this.w(j.NONE);
                b bVar = this.f5231g;
                if (bVar.f5222c) {
                    bVar.a.forceFinished(true);
                } else {
                    bVar.b.forceFinished(true);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean isFinished;
            boolean computeScrollOffset;
            int currX;
            int currY;
            f fVar = PdMCooTouchImageView.this.H;
            if (fVar != null) {
                fVar.a();
            }
            b bVar = this.f5231g;
            if (bVar.f5222c) {
                isFinished = bVar.a.isFinished();
            } else {
                isFinished = bVar.b.isFinished();
            }
            if (isFinished) {
                this.f5231g = null;
                return;
            }
            b bVar2 = this.f5231g;
            if (bVar2.f5222c) {
                computeScrollOffset = bVar2.a.computeScrollOffset();
            } else {
                bVar2.b.computeScrollOffset();
                computeScrollOffset = bVar2.b.computeScrollOffset();
            }
            if (computeScrollOffset) {
                b bVar3 = this.f5231g;
                if (bVar3.f5222c) {
                    currX = bVar3.a.getCurrX();
                } else {
                    currX = bVar3.b.getCurrX();
                }
                b bVar4 = this.f5231g;
                if (bVar4.f5222c) {
                    currY = bVar4.a.getCurrY();
                } else {
                    currY = bVar4.b.getCurrY();
                }
                int i2 = currX - this.f5232h;
                int i3 = currY - this.f5233i;
                this.f5232h = currX;
                this.f5233i = currY;
                PdMCooTouchImageView.this.f5215h.postTranslate(i2, i3);
                PdMCooTouchImageView.this.p();
                PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
                pdMCooTouchImageView.setImageMatrix(pdMCooTouchImageView.f5215h);
                PdMCooTouchImageView.l(PdMCooTouchImageView.this, this);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class e extends GestureDetector.SimpleOnGestureListener {
        public e() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            GestureDetector.OnDoubleTapListener onDoubleTapListener = PdMCooTouchImageView.this.F;
            boolean onDoubleTap = onDoubleTapListener != null ? onDoubleTapListener.onDoubleTap(motionEvent) : false;
            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
            if (pdMCooTouchImageView.f5217j == j.NONE) {
                float f2 = pdMCooTouchImageView.f5214g;
                float f3 = pdMCooTouchImageView.f5218k;
                PdMCooTouchImageView.l(PdMCooTouchImageView.this, new c(f2 == f3 ? pdMCooTouchImageView.f5219l : f3, motionEvent.getX(), motionEvent.getY(), false));
                return true;
            }
            return onDoubleTap;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            GestureDetector.OnDoubleTapListener onDoubleTapListener = PdMCooTouchImageView.this.F;
            if (onDoubleTapListener != null) {
                return onDoubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            d dVar = PdMCooTouchImageView.this.q;
            if (dVar != null) {
                dVar.a();
            }
            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
            pdMCooTouchImageView.q = new d((int) f2, (int) f3);
            PdMCooTouchImageView pdMCooTouchImageView2 = PdMCooTouchImageView.this;
            PdMCooTouchImageView.l(pdMCooTouchImageView2, pdMCooTouchImageView2.q);
            return super.onFling(motionEvent, motionEvent2, f2, f3);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            PdMCooTouchImageView.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
            GestureDetector.OnDoubleTapListener onDoubleTapListener = pdMCooTouchImageView.F;
            if (onDoubleTapListener != null) {
                return onDoubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return pdMCooTouchImageView.performClick();
        }
    }

    /* loaded from: classes15.dex */
    public interface f {
        void a();
    }

    /* loaded from: classes15.dex */
    public interface g {
        void a(j jVar);
    }

    /* loaded from: classes15.dex */
    public class h implements View.OnTouchListener {

        /* renamed from: g  reason: collision with root package name */
        public PointF f5236g = new PointF();

        public h() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
            if (r1 != 6) goto L30;
         */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PdMCooTouchImageView.this.D.onTouchEvent(motionEvent);
            PdMCooTouchImageView.this.E.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            j jVar = PdMCooTouchImageView.this.f5217j;
            j jVar2 = j.NONE;
            if (jVar == jVar2 || jVar == j.DRAG || jVar == j.FLING) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action == 2) {
                            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
                            if (pdMCooTouchImageView.f5217j == j.DRAG) {
                                float f2 = pointF.x;
                                PointF pointF2 = this.f5236g;
                                float f3 = f2 - pointF2.x;
                                float f4 = pointF.y - pointF2.y;
                                if (pdMCooTouchImageView.getImageWidth() <= pdMCooTouchImageView.v) {
                                    f3 = 0.0f;
                                }
                                if (PdMCooTouchImageView.this.getImageHeight() <= r1.w) {
                                    f4 = 0.0f;
                                }
                                PdMCooTouchImageView.this.f5215h.postTranslate(f3, f4);
                                PdMCooTouchImageView.this.p();
                                this.f5236g.set(pointF.x, pointF.y);
                            }
                        }
                    }
                    PdMCooTouchImageView.this.w(jVar2);
                } else {
                    this.f5236g.set(pointF);
                    d dVar = PdMCooTouchImageView.this.q;
                    if (dVar != null) {
                        dVar.a();
                    }
                    PdMCooTouchImageView.this.w(j.DRAG);
                }
            }
            PdMCooTouchImageView pdMCooTouchImageView2 = PdMCooTouchImageView.this;
            pdMCooTouchImageView2.setImageMatrix(pdMCooTouchImageView2.f5215h);
            View.OnTouchListener onTouchListener = PdMCooTouchImageView.this.G;
            if (onTouchListener != null) {
                onTouchListener.onTouch(view, motionEvent);
            }
            f fVar = PdMCooTouchImageView.this.H;
            if (fVar != null) {
                fVar.a();
            }
            return true;
        }
    }

    /* loaded from: classes15.dex */
    public class i extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public i() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float focusX = scaleGestureDetector.getFocusX();
            float focusY = scaleGestureDetector.getFocusY();
            int i2 = PdMCooTouchImageView.J;
            PdMCooTouchImageView.this.g(scaleGestureDetector.getScaleFactor(), focusX, focusY, true);
            f fVar = PdMCooTouchImageView.this.H;
            if (fVar != null) {
                fVar.a();
                return true;
            }
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            PdMCooTouchImageView.this.w(j.ZOOM);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            boolean z;
            float f2;
            super.onScaleEnd(scaleGestureDetector);
            PdMCooTouchImageView.this.w(j.NONE);
            PdMCooTouchImageView pdMCooTouchImageView = PdMCooTouchImageView.this;
            float f3 = pdMCooTouchImageView.f5214g;
            float f4 = pdMCooTouchImageView.f5219l;
            if (f3 <= f4) {
                f4 = pdMCooTouchImageView.f5218k;
                if (f3 >= f4) {
                    f2 = f3;
                    z = false;
                    if (z) {
                        return;
                    }
                    PdMCooTouchImageView.l(PdMCooTouchImageView.this, new c(f2, pdMCooTouchImageView.v / 2, pdMCooTouchImageView.w / 2, true));
                    return;
                }
            }
            z = true;
            f2 = f4;
            if (z) {
            }
        }
    }

    /* loaded from: classes15.dex */
    public enum j {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    /* loaded from: classes15.dex */
    public class k {
        public float a;
        public float b;

        /* renamed from: c  reason: collision with root package name */
        public float f5241c;
        public ImageView.ScaleType d;

        public k(PdMCooTouchImageView pdMCooTouchImageView, float f2, float f3, float f4, ImageView.ScaleType scaleType) {
            this.a = f2;
            this.b = f3;
            this.f5241c = f4;
            this.d = scaleType;
        }
    }

    public PdMCooTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = null;
        this.G = null;
        this.H = null;
        setChangeImageListener(this);
        j(context);
    }

    public static PointF d(PdMCooTouchImageView pdMCooTouchImageView, float f2, float f3) {
        float f4;
        pdMCooTouchImageView.f5215h.getValues(pdMCooTouchImageView.o);
        Drawable drawable = pdMCooTouchImageView.getDrawable();
        float f5 = 1.0f;
        if (drawable != null) {
            float intrinsicWidth = drawable.getIntrinsicWidth();
            f5 = drawable.getIntrinsicHeight();
            f4 = intrinsicWidth;
        } else {
            f4 = 1.0f;
        }
        return new PointF(pdMCooTouchImageView.o[2] + (pdMCooTouchImageView.getImageWidth() * (f2 / f4)), pdMCooTouchImageView.o[5] + (pdMCooTouchImageView.getImageHeight() * (f3 / f5)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageHeight() {
        return this.A * this.f5214g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageWidth() {
        return this.z * this.f5214g;
    }

    public static void l(PdMCooTouchImageView pdMCooTouchImageView, Runnable runnable) {
        pdMCooTouchImageView.getClass();
        if (Build.VERSION.SDK_INT >= 16) {
            pdMCooTouchImageView.postOnAnimation(runnable);
        } else {
            pdMCooTouchImageView.postDelayed(runnable, 16L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(j jVar) {
        this.f5217j = jVar;
        g gVar = this.I;
        if (gVar != null) {
            gVar.a(jVar);
        }
    }

    public final float a(float f2, float f3, float f4) {
        float f5;
        int i2 = (f4 > f3 ? 1 : (f4 == f3 ? 0 : -1));
        float f6 = f3 - f4;
        if (i2 <= 0) {
            f5 = f6;
            f6 = 0.0f;
        } else {
            f5 = 0.0f;
        }
        if (f2 < f6) {
            return (-f2) + f6;
        }
        if (f2 > f5) {
            return (-f2) + f5;
        }
        return 0.0f;
    }

    public final PointF c(float f2, float f3, boolean z) {
        float f4;
        float f5;
        this.f5215h.getValues(this.o);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            f5 = drawable.getIntrinsicWidth();
            f4 = drawable.getIntrinsicHeight();
        } else {
            f4 = 0.0f;
            f5 = 0.0f;
        }
        float[] fArr = this.o;
        float f6 = fArr[2];
        float f7 = fArr[5];
        float imageWidth = ((f2 - f6) * f5) / getImageWidth();
        float imageHeight = ((f3 - f7) * f4) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), f5);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), f4);
        }
        return new PointF(imageWidth, imageHeight);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        this.f5215h.getValues(this.o);
        float f2 = this.o[2];
        if (getImageWidth() < this.v) {
            return false;
        }
        if (f2 < -1.0f || i2 >= 0) {
            return (Math.abs(f2) + ((float) this.v)) + 1.0f < getImageWidth() || i2 <= 0;
        }
        return false;
    }

    public final void f() {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.f5215h == null || this.f5216i == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f2 = intrinsicWidth;
        float f3 = this.v / f2;
        float f4 = intrinsicHeight;
        float f5 = this.w / f4;
        int i2 = a.a[this.r.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                f3 = Math.max(f3, f5);
            } else if (i2 == 3) {
                f3 = Math.min(1.0f, Math.min(f3, f5));
            } else if (i2 != 4) {
                if (i2 != 5) {
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
                }
            }
            f5 = f3;
        } else {
            f3 = 1.0f;
            f5 = 1.0f;
        }
        float f6 = this.v;
        float f7 = f6 - (f3 * f2);
        float f8 = this.w;
        float f9 = f8 - (f5 * f4);
        this.z = f6 - f7;
        this.A = f8 - f9;
        if (!(this.f5214g != 1.0f) && !this.s) {
            this.f5215h.setScale(f3, f5);
            this.f5215h.postTranslate(f7 / 2.0f, f9 / 2.0f);
            this.f5214g = 1.0f;
        } else {
            if (this.B == 0.0f || this.C == 0.0f) {
                r();
            }
            this.f5216i.getValues(this.o);
            float[] fArr = this.o;
            float f10 = this.z / f2;
            float f11 = this.f5214g;
            fArr[0] = f10 * f11;
            fArr[4] = (this.A / f4) * f11;
            float f12 = fArr[2];
            float f13 = fArr[5];
            i(2, f12, this.B * f11, getImageWidth(), this.x, this.v, intrinsicWidth);
            i(5, f13, this.C * this.f5214g, getImageHeight(), this.y, this.w, intrinsicHeight);
            this.f5215h.setValues(this.o);
        }
        p();
        setImageMatrix(this.f5215h);
    }

    public final void g(double d2, float f2, float f3, boolean z) {
        float f4;
        float f5;
        if (z) {
            f4 = this.f5220m;
            f5 = this.f5221n;
        } else {
            f4 = this.f5218k;
            f5 = this.f5219l;
        }
        float f6 = this.f5214g;
        double d3 = f6;
        Double.isNaN(d3);
        float f7 = (float) (d3 * d2);
        this.f5214g = f7;
        if (f7 > f5) {
            this.f5214g = f5;
            d2 = f5 / f6;
        } else if (f7 < f4) {
            this.f5214g = f4;
            d2 = f4 / f6;
        }
        float f8 = (float) d2;
        this.f5215h.postScale(f8, f8, f2, f3);
        n();
    }

    public float getCurrentZoom() {
        return this.f5214g;
    }

    @Override // android.widget.ImageView
    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        if (drawable instanceof RootDrawable) {
            Drawable actualImageDrawable = getHierarchy().getActualImageDrawable();
            return (actualImageDrawable == null || !(actualImageDrawable instanceof ForwardingDrawable)) ? actualImageDrawable : ((ForwardingDrawable) actualImageDrawable).getCurrent();
        }
        return drawable;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.r;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF c2 = c(this.v / 2, this.w / 2, true);
        c2.x /= intrinsicWidth;
        c2.y /= intrinsicHeight;
        return c2;
    }

    public void h(float f2, float f3, float f4, ImageView.ScaleType scaleType) {
        if (!this.t) {
            this.u = new k(this, f2, f3, f4, scaleType);
            return;
        }
        if (scaleType != this.r) {
            setScaleType(scaleType);
        }
        this.f5214g = 1.0f;
        f();
        g(f2, this.v / 2, this.w / 2, true);
        this.f5215h.getValues(this.o);
        this.o[2] = -((f3 * getImageWidth()) - (this.v * 0.5f));
        this.o[5] = -((f4 * getImageHeight()) - (this.w * 0.5f));
        this.f5215h.setValues(this.o);
        p();
        setImageMatrix(this.f5215h);
    }

    public final void i(int i2, float f2, float f3, float f4, int i3, int i4, int i5) {
        float f5 = i4;
        if (f4 < f5) {
            float[] fArr = this.o;
            fArr[i2] = (f5 - (i5 * fArr[0])) * 0.5f;
        } else if (f2 > 0.0f) {
            this.o[i2] = -((f4 - f5) * 0.5f);
        } else {
            this.o[i2] = -((((Math.abs(f2) + (i3 * 0.5f)) / f3) * f4) - (f5 * 0.5f));
        }
    }

    public final void j(Context context) {
        super.setClickable(true);
        this.p = context;
        this.D = new ScaleGestureDetector(context, new i());
        this.E = new GestureDetector(context, new e());
        this.f5215h = new Matrix();
        this.f5216i = new Matrix();
        this.o = new float[9];
        this.f5214g = 1.0f;
        if (this.r == null) {
            this.r = ImageView.ScaleType.FIT_CENTER;
        }
        this.f5218k = 1.0f;
        this.f5219l = 3.0f;
        this.f5220m = 0.75f;
        this.f5221n = 3.75f;
        setImageMatrix(this.f5215h);
        setScaleType(ImageView.ScaleType.MATRIX);
        w(j.NONE);
        this.t = false;
        super.setOnTouchListener(new h());
    }

    public final void n() {
        p();
        this.f5215h.getValues(this.o);
        float imageWidth = getImageWidth();
        float f2 = this.v;
        if (imageWidth < f2) {
            this.o[2] = (f2 - getImageWidth()) / 2.0f;
        }
        float imageHeight = getImageHeight();
        float f3 = this.w;
        if (imageHeight < f3) {
            this.o[5] = (f3 - getImageHeight()) / 2.0f;
        }
        this.f5215h.setValues(this.o);
    }

    @Override // com.facebook.drawee.generic.GenericDraweeHierarchy.ChangeImageListener
    public void onChangeImage() {
        f();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        r();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        this.t = true;
        this.s = true;
        k kVar = this.u;
        if (kVar != null) {
            h(kVar.a, kVar.b, kVar.f5241c, kVar.d);
            this.u = null;
        }
        try {
            super.onDraw(canvas);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            if (mode == Integer.MIN_VALUE) {
                intrinsicWidth = Math.min(intrinsicWidth, size);
            } else if (mode != 0) {
                intrinsicWidth = size;
            }
            this.v = intrinsicWidth;
            if (mode2 == Integer.MIN_VALUE) {
                intrinsicHeight = Math.min(intrinsicHeight, size2);
            } else if (mode2 != 0) {
                intrinsicHeight = size2;
            }
            this.w = intrinsicHeight;
            setMeasuredDimension(this.v, intrinsicHeight);
            f();
            return;
        }
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f5214g = bundle.getFloat("saveScale");
            float[] floatArray = bundle.getFloatArray("matrix");
            this.o = floatArray;
            this.f5216i.setValues(floatArray);
            this.C = bundle.getFloat("matchViewHeight");
            this.B = bundle.getFloat("matchViewWidth");
            this.y = bundle.getInt("viewHeight");
            this.x = bundle.getInt("viewWidth");
            this.s = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.f5214g);
        bundle.putFloat("matchViewHeight", this.A);
        bundle.putFloat("matchViewWidth", this.z);
        bundle.putInt("viewWidth", this.v);
        bundle.putInt("viewHeight", this.w);
        this.f5215h.getValues(this.o);
        bundle.putFloatArray("matrix", this.o);
        bundle.putBoolean("imageRendered", this.s);
        return bundle;
    }

    public final void p() {
        this.f5215h.getValues(this.o);
        float[] fArr = this.o;
        float f2 = fArr[2];
        float f3 = fArr[5];
        float a2 = a(f2, this.v, getImageWidth());
        float a3 = a(f3, this.w, getImageHeight());
        if (a2 == 0.0f && a3 == 0.0f) {
            return;
        }
        this.f5215h.postTranslate(a2, a3);
    }

    public final void r() {
        Matrix matrix = this.f5215h;
        if (matrix == null || this.w == 0 || this.v == 0) {
            return;
        }
        matrix.getValues(this.o);
        this.f5216i.setValues(this.o);
        this.C = this.A;
        this.B = this.z;
        this.y = this.w;
        this.x = this.v;
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        r();
        f();
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        r();
        f();
    }

    @Override // com.facebook.drawee.view.SimpleDraweeView, com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int i2) {
        super.setImageResource(i2);
        r();
        f();
    }

    @Override // com.facebook.drawee.view.SimpleDraweeView, com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        r();
        f();
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.G = onTouchListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.FIT_START && scaleType != ImageView.ScaleType.FIT_END) {
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                super.setScaleType(scaleType2);
                return;
            }
            this.r = scaleType;
            if (this.t) {
                x(this);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
    }

    public void x(PdMCooTouchImageView pdMCooTouchImageView) {
        PointF scrollPosition = pdMCooTouchImageView.getScrollPosition();
        h(pdMCooTouchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, pdMCooTouchImageView.getScaleType());
    }

    public PdMCooTouchImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.F = null;
        this.G = null;
        this.H = null;
        setChangeImageListener(this);
        j(context);
    }
}
