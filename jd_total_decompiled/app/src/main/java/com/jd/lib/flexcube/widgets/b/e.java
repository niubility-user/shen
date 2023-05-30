package com.jd.lib.flexcube.widgets.b;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.common.FrameInfo;

/* loaded from: classes15.dex */
public class e {
    View a;
    float[] d;

    /* renamed from: f  reason: collision with root package name */
    private Paint f4505f;
    int b = 0;

    /* renamed from: c  reason: collision with root package name */
    int f4503c = 0;

    /* renamed from: e  reason: collision with root package name */
    private Path f4504e = new Path();

    /* renamed from: g  reason: collision with root package name */
    private float f4506g = 1.0f;

    /* renamed from: h  reason: collision with root package name */
    private float f4507h = 1.0f;

    public e(View view) {
        this.a = view;
    }

    private void a() {
        View view;
        if ((g().booleanValue() || f().booleanValue()) && (view = this.a) != null) {
            view.setWillNotDraw(false);
        }
    }

    private float[] c() {
        if (this.d == null) {
            this.d = new float[8];
        }
        return this.d;
    }

    private Paint d() {
        if (this.f4505f == null) {
            Paint paint = new Paint();
            this.f4505f = paint;
            paint.setAntiAlias(true);
            this.f4505f.setStyle(Paint.Style.STROKE);
        }
        return this.f4505f;
    }

    private void e() {
        a();
        View view = this.a;
        if (view != null) {
            view.invalidate();
        }
    }

    private Boolean f() {
        return Boolean.valueOf((this.d == null || this.a == null) ? false : true);
    }

    private Boolean g() {
        return Boolean.valueOf((this.b <= 0 || this.a == null || this.f4505f == null) ? false : true);
    }

    public void b(Canvas canvas) {
        float f2;
        if (!f().booleanValue() || canvas == null) {
            return;
        }
        float f3 = 1.0f;
        if (Build.VERSION.SDK_INT < 26) {
            f3 = this.f4506g;
            f2 = this.f4507h;
        } else {
            f2 = 1.0f;
        }
        int width = (int) (this.a.getWidth() * f3);
        int height = (int) (this.a.getHeight() * f2);
        if (width <= 0 || height <= 0) {
            return;
        }
        canvas.save();
        Path path = new Path();
        this.f4504e = path;
        path.addRoundRect(new RectF(0.0f, 0.0f, width, height), this.d, Path.Direction.CCW);
        canvas.clipPath(this.f4504e);
    }

    public void h(Canvas canvas) {
        float f2;
        if (!g().booleanValue() || canvas == null) {
            return;
        }
        if (!f().booleanValue()) {
            float f3 = 1.0f;
            if (Build.VERSION.SDK_INT < 26) {
                f3 = this.f4506g;
                f2 = this.f4507h;
            } else {
                f2 = 1.0f;
            }
            int width = (int) (this.a.getWidth() * f3);
            int height = (int) (this.a.getHeight() * f2);
            if (width <= 0 || height <= 0) {
                return;
            }
            this.f4504e.reset();
            this.f4504e.addRect(new RectF(0.0f, 0.0f, width, height), Path.Direction.CCW);
        }
        canvas.drawPath(this.f4504e, this.f4505f);
    }

    public void i(CfInfo cfInfo, float f2) {
        if (cfInfo != null && !cfInfo.isEmpty()) {
            l(cfInfo.radiusLT * f2, cfInfo.radiusRT * f2, cfInfo.radiusRB * f2, cfInfo.radiusLB * f2);
            return;
        }
        k(0.0f);
    }

    public void j(CfInfo cfInfo, float f2, int i2) {
        if (cfInfo != null && !cfInfo.isEmpty()) {
            cfInfo.setHeightHalf(i2);
            l(cfInfo.radiusLT * f2, cfInfo.radiusRT * f2, cfInfo.radiusRB * f2, cfInfo.radiusLB * f2);
            return;
        }
        k(0.0f);
    }

    public void k(float f2) {
        if (f2 > 0.0f) {
            l(f2, f2, f2, f2);
            return;
        }
        this.d = null;
        e();
    }

    public void l(float f2, float f3, float f4, float f5) {
        if (f2 <= 0.0f && f3 <= 0.0f && f4 <= 0.0f && f5 <= 0.0f) {
            this.d = null;
        } else {
            float[] c2 = c();
            c2[0] = f2;
            c2[1] = f2;
            c2[2] = f3;
            c2[3] = f3;
            c2[4] = f4;
            c2[5] = f4;
            c2[6] = f5;
            c2[7] = f5;
        }
        e();
    }

    public void m(FrameInfo frameInfo, int i2, float f2) {
        if (frameInfo == null) {
            q(0);
            p(0);
            return;
        }
        int f3 = com.jd.lib.flexcube.iwidget.b.b.f(frameInfo.size, 0);
        if (f3 > 0) {
            int d = com.jd.lib.flexcube.iwidget.b.b.d(f3, f2);
            if (d == 0 && f3 > 0) {
                d = 1;
            }
            q(d);
            if ("1".equals(frameInfo.sameTextColor)) {
                p(i2);
                return;
            } else {
                p(com.jd.lib.flexcube.iwidget.b.a.a(frameInfo.color, 0));
                return;
            }
        }
        q(0);
        p(0);
    }

    public void n(float f2) {
        this.f4506g = f2;
        e();
    }

    public void o(float f2) {
        this.f4507h = f2;
        e();
    }

    public void p(int i2) {
        this.f4503c = i2;
        d().setColor(this.f4503c);
        e();
    }

    public void q(int i2) {
        this.b = i2;
        d().setStrokeWidth(this.b);
        e();
    }
}
