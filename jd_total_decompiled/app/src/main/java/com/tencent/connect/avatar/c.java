package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/* loaded from: classes9.dex */
public class c extends ImageView {
    final String a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    private Matrix f16139c;
    private Matrix d;

    /* renamed from: e  reason: collision with root package name */
    private int f16140e;

    /* renamed from: f  reason: collision with root package name */
    private float f16141f;

    /* renamed from: g  reason: collision with root package name */
    private float f16142g;

    /* renamed from: h  reason: collision with root package name */
    private Bitmap f16143h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f16144i;

    /* renamed from: j  reason: collision with root package name */
    private float f16145j;

    /* renamed from: k  reason: collision with root package name */
    private float f16146k;

    /* renamed from: l  reason: collision with root package name */
    private PointF f16147l;

    /* renamed from: m  reason: collision with root package name */
    private PointF f16148m;

    /* renamed from: n  reason: collision with root package name */
    private float f16149n;
    private float o;
    private Rect p;

    public c(Context context) {
        super(context);
        this.f16139c = new Matrix();
        this.d = new Matrix();
        this.f16140e = 0;
        this.f16141f = 1.0f;
        this.f16142g = 1.0f;
        this.f16144i = false;
        this.a = "TouchView";
        this.f16147l = new PointF();
        this.f16148m = new PointF();
        this.f16149n = 1.0f;
        this.o = 0.0f;
        this.b = false;
        Rect rect = new Rect();
        this.p = rect;
        getDrawingRect(rect);
        a();
    }

    private void a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        boolean z;
        ScaleAnimation scaleAnimation;
        if (this.f16143h == null) {
            return;
        }
        float width = this.p.width();
        float height = this.p.height();
        float[] fArr = new float[9];
        this.f16139c.getValues(fArr);
        float f2 = fArr[2];
        float f3 = fArr[5];
        float f4 = fArr[0];
        float f5 = this.f16141f;
        if (f4 > f5) {
            float f6 = f5 / f4;
            this.o = f6;
            Matrix matrix = this.f16139c;
            PointF pointF = this.f16148m;
            matrix.postScale(f6, f6, pointF.x, pointF.y);
            setImageMatrix(this.f16139c);
            float f7 = this.o;
            float f8 = 1.0f / f7;
            float f9 = 1.0f / f7;
            PointF pointF2 = this.f16148m;
            scaleAnimation = new ScaleAnimation(f8, 1.0f, f9, 1.0f, pointF2.x, pointF2.y);
        } else {
            float f10 = this.f16142g;
            if (f4 < f10) {
                float f11 = f10 / f4;
                this.o = f11;
                Matrix matrix2 = this.f16139c;
                PointF pointF3 = this.f16148m;
                matrix2.postScale(f11, f11, pointF3.x, pointF3.y);
                float f12 = this.o;
                PointF pointF4 = this.f16148m;
                scaleAnimation = new ScaleAnimation(1.0f, f12, 1.0f, f12, pointF4.x, pointF4.y);
            } else {
                float width2 = this.f16143h.getWidth() * f4;
                float height2 = this.f16143h.getHeight() * f4;
                Rect rect = this.p;
                int i2 = rect.left;
                float f13 = i2 - f2;
                int i3 = rect.top;
                float f14 = i3 - f3;
                if (f13 < 0.0f) {
                    f2 = i2;
                    z = true;
                } else {
                    z = false;
                }
                if (f14 < 0.0f) {
                    f3 = i3;
                    z = true;
                }
                float f15 = height2 - f14;
                if (width2 - f13 < width) {
                    f2 = i2 - (width2 - width);
                    z = true;
                }
                if (f15 < height) {
                    f3 = i3 - (height2 - height);
                    z = true;
                }
                if (z) {
                    fArr[2] = f2;
                    fArr[5] = f3;
                    this.f16139c.setValues(fArr);
                    setImageMatrix(this.f16139c);
                    scaleAnimation = new TranslateAnimation(fArr[2] - f2, 0.0f, fArr[5] - f3, 0.0f);
                } else {
                    setImageMatrix(this.f16139c);
                    scaleAnimation = null;
                }
            }
        }
        if (scaleAnimation != null) {
            this.f16144i = true;
            scaleAnimation.setDuration(300L);
            startAnimation(scaleAnimation);
            new Thread(new Runnable() { // from class: com.tencent.connect.avatar.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    c.this.post(new Runnable() { // from class: com.tencent.connect.avatar.c.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            c.this.clearAnimation();
                            c.this.b();
                        }
                    });
                    c.this.f16144i = false;
                }
            }).start();
        }
    }

    private void c() {
        if (this.f16143h == null) {
            return;
        }
        this.f16139c.getValues(r0);
        float max = Math.max(this.p.width() / this.f16143h.getWidth(), this.p.height() / this.f16143h.getHeight());
        this.f16145j = this.p.left - (((this.f16143h.getWidth() * max) - this.p.width()) / 2.0f);
        float height = this.p.top - (((this.f16143h.getHeight() * max) - this.p.height()) / 2.0f);
        this.f16146k = height;
        float[] fArr = {max, 0.0f, this.f16145j, 0.0f, max, height};
        this.f16139c.setValues(fArr);
        float min = Math.min(2048.0f / this.f16143h.getWidth(), 2048.0f / this.f16143h.getHeight());
        this.f16141f = min;
        this.f16142g = max;
        if (min < max) {
            this.f16141f = max;
        }
        setImageMatrix(this.f16139c);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (r0 != 6) goto L28;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f16144i) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    int i2 = this.f16140e;
                    if (i2 == 1) {
                        this.f16139c.set(this.d);
                        this.f16139c.postTranslate(motionEvent.getX() - this.f16147l.x, motionEvent.getY() - this.f16147l.y);
                        setImageMatrix(this.f16139c);
                    } else if (i2 == 2) {
                        Matrix matrix = this.f16139c;
                        matrix.set(matrix);
                        float a = a(motionEvent);
                        if (a > 10.0f) {
                            this.f16139c.set(this.d);
                            float f2 = a / this.f16149n;
                            Matrix matrix2 = this.f16139c;
                            PointF pointF = this.f16148m;
                            matrix2.postScale(f2, f2, pointF.x, pointF.y);
                        }
                        setImageMatrix(this.f16139c);
                    }
                } else if (action == 5) {
                    float a2 = a(motionEvent);
                    this.f16149n = a2;
                    if (a2 > 10.0f) {
                        this.d.set(this.f16139c);
                        a(this.f16148m);
                        this.f16140e = 2;
                    }
                }
            }
            b();
            this.f16140e = 0;
        } else {
            this.f16139c.set(getImageMatrix());
            this.d.set(this.f16139c);
            this.f16147l.set(motionEvent.getX(), motionEvent.getY());
            this.f16140e = 1;
        }
        this.b = true;
        return true;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f16143h = bitmap;
        if (bitmap != null) {
            this.f16143h = bitmap;
        }
    }

    private float a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void a(Rect rect) {
        this.p = rect;
        if (this.f16143h != null) {
            c();
        }
    }

    private void a(PointF pointF) {
        if (this.f16143h == null) {
            return;
        }
        float[] fArr = new float[9];
        this.f16139c.getValues(fArr);
        float f2 = fArr[2];
        float f3 = fArr[5];
        float f4 = fArr[0];
        float width = this.f16143h.getWidth() * f4;
        float height = this.f16143h.getHeight() * f4;
        Rect rect = this.p;
        float f5 = rect.left - f2;
        if (f5 <= 1.0f) {
            f5 = 1.0f;
        }
        float f6 = (f2 + width) - rect.right;
        if (f6 <= 1.0f) {
            f6 = 1.0f;
        }
        float width2 = (rect.width() * f5) / (f6 + f5);
        Rect rect2 = this.p;
        float f7 = width2 + rect2.left;
        float f8 = rect2.top - f3;
        float f9 = (f3 + height) - rect2.bottom;
        if (f8 <= 1.0f) {
            f8 = 1.0f;
        }
        pointF.set(f7, ((rect2.height() * f8) / ((f9 > 1.0f ? f9 : 1.0f) + f8)) + this.p.top);
    }
}
