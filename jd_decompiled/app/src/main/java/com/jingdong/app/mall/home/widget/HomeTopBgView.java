package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.o.a.f;

/* loaded from: classes4.dex */
public class HomeTopBgView extends View {

    /* renamed from: g */
    private Bitmap f11042g;

    /* renamed from: h */
    private final Paint f11043h;

    /* renamed from: i */
    private final Paint f11044i;

    /* renamed from: j */
    private final Paint f11045j;

    /* renamed from: k */
    private final Path f11046k;

    /* renamed from: l */
    private Matrix f11047l;

    /* renamed from: m */
    private boolean f11048m;

    /* renamed from: n */
    private boolean f11049n;
    private int o;
    private int p;
    private boolean q;

    public HomeTopBgView(Context context) {
        super(context);
        this.f11043h = new Paint(1);
        this.f11044i = new Paint(1);
        this.f11045j = new Paint(1);
        this.f11046k = new Path();
        this.f11047l = new Matrix();
    }

    private void b(Canvas canvas) {
        int d = d();
        if (!this.q && !com.jingdong.app.mall.home.state.dark.a.h() && this.f11049n && com.jingdong.app.mall.home.a.w != com.jingdong.app.mall.home.a.x) {
            int d2 = d - d.d(20);
            int j2 = g.j();
            if (this.f11045j.getShader() == null || this.p != j2) {
                this.p = j2;
                this.f11045j.setShader(new LinearGradient(0.0f, d2, 0.0f, this.p + d2, com.jingdong.app.mall.home.a.w, com.jingdong.app.mall.home.a.x, Shader.TileMode.CLAMP));
            }
            canvas.drawRect(0.0f, d2, getWidth(), d2 + d.f9279g, this.f11045j);
        }
        canvas.save();
        canvas.clipPath(this.f11046k);
        canvas.drawBitmap(this.f11042g, this.f11047l, null);
        canvas.restore();
    }

    private int d() {
        int height = this.q ? getHeight() - this.o : h.T() + d.d(20);
        int i2 = this.q ? 0 : -height;
        int d = d.d(20);
        float f2 = d;
        float f3 = 0.5522848f * f2;
        int i3 = height - d;
        this.f11046k.reset();
        if (h()) {
            float f4 = i2;
            this.f11046k.moveTo(0.0f, f4);
            this.f11046k.lineTo(0.0f, height);
            float f5 = i3;
            com.jingdong.app.mall.home.n.h.g.c(0.0f, f5, f2, f3, this.f11046k);
            this.f11046k.lineTo(getWidth() - d, f5);
            com.jingdong.app.mall.home.n.h.g.f(f5, getWidth(), f2, f3, this.f11046k);
            this.f11046k.lineTo(getWidth(), f4);
            this.f11046k.lineTo(0.0f, f4);
        } else {
            float f6 = i2;
            this.f11046k.moveTo(0.0f, f6);
            float f7 = i3;
            this.f11046k.lineTo(0.0f, f7);
            this.f11046k.lineTo(getWidth(), f7);
            this.f11046k.lineTo(getWidth(), f6);
            this.f11046k.lineTo(0.0f, f6);
        }
        this.f11046k.close();
        return height;
    }

    private void e(Canvas canvas) {
        if (this.q) {
            return;
        }
        try {
            d();
            this.f11043h.setColor(this.f11048m ? -6430 : -1957094);
            canvas.drawPath(this.f11046k, this.f11043h);
        } catch (Throwable th) {
            f.s0(this, th);
        }
    }

    private boolean h() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public void a() {
        if (this.f11049n) {
            return;
        }
        this.f11049n = true;
        if (!h.C || this.o <= 0) {
            return;
        }
        postInvalidate();
    }

    public Bitmap c() {
        Bitmap bitmap = this.f11042g;
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return this.f11042g;
    }

    public void f(int i2, boolean z) {
        this.f11048m = z;
        if (i2 == -1) {
            i2 = 0;
        }
        if (i2 <= 0 && com.jingdong.app.mall.home.a.x != -855310) {
            i2 = IconFloorEntity.BGCOLOR_DEFAULT;
        }
        this.f11044i.setColor(i2);
        this.f11045j.setShader(null);
    }

    public void g(Bitmap bitmap, Matrix matrix, int i2) {
        com.jingdong.app.mall.home.v.c.a.a(this);
        this.f11042g = bitmap;
        this.f11047l = matrix;
        this.o = i2;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.f11042g;
        if (bitmap == null || this.f11047l == null) {
            return;
        }
        if (bitmap.isRecycled()) {
            e(canvas);
            return;
        }
        try {
            if (!this.q && com.jingdong.app.mall.home.a.x != this.f11044i.getColor()) {
                canvas.drawRect(0.0f, -d.f9278f, getWidth(), 0.0f, this.f11044i);
            }
            if (h.C && this.o > 0) {
                b(canvas);
            } else {
                canvas.drawBitmap(this.f11042g, this.f11047l, null);
            }
        } catch (Throwable th) {
            e(canvas);
            f.s0(this, th);
        }
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        if (f2 > 0.0f) {
            f2 = 0.0f;
        }
        if (f2 < (-getHeight())) {
            f2 = -getHeight();
        }
        if (this.q) {
            f.r0(this, "translationY = " + f2 + "");
        }
        super.setTranslationY(f2);
    }

    public HomeTopBgView(Context context, boolean z) {
        super(context);
        this.f11043h = new Paint(1);
        this.f11044i = new Paint(1);
        this.f11045j = new Paint(1);
        this.f11046k = new Path();
        this.f11047l = new Matrix();
        this.q = z;
    }
}
