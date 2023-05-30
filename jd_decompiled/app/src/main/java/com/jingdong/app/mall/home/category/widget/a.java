package com.jingdong.app.mall.home.category.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.floor.common.d;

/* loaded from: classes4.dex */
public class a extends Drawable {
    private Paint a = new Paint(1);
    private Path b = new Path();

    /* renamed from: c  reason: collision with root package name */
    private int f8869c;
    private int[] d;

    /* renamed from: e  reason: collision with root package name */
    private int f8870e;

    /* renamed from: f  reason: collision with root package name */
    private int f8871f;

    /* renamed from: g  reason: collision with root package name */
    private int f8872g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f8873h;

    public a(int[] iArr, int i2, int i3, int i4) {
        this.d = iArr;
        this.f8870e = i2;
        this.f8871f = i3;
        this.f8872g = i4;
        a();
    }

    private void a() {
        if (this.f8869c == d.f9279g) {
            return;
        }
        this.f8869c = d.f9279g;
        this.b.reset();
        int d = d.d(this.f8870e);
        int d2 = d.d(this.f8871f);
        int d3 = d.d(this.f8872g);
        int i2 = d2 >> 1;
        int i3 = (d3 - d) >> 1;
        if (!this.f8873h) {
            float f2 = (float) (i2 - 2);
            this.b.moveTo(f2, 2.0f);
            this.b.cubicTo(f2, 2.0f, i2, 0.0f, i2 + 2, 2.0f);
            float f3 = d;
            this.b.lineTo(i2 + d, f3);
            this.b.lineTo(i2 - d, f3);
            this.b.close();
        }
        RectF rectF = new RectF(0.0f, d, d2, d3);
        float f4 = i3;
        this.b.addRoundRect(rectF, new float[]{f4, f4, f4, f4, f4, f4, f4, f4}, Path.Direction.CCW);
        c(this.d);
    }

    public void b(boolean z) {
        if (this.f8873h != z) {
            this.f8869c = 0;
        }
        this.f8873h = z;
        invalidateSelf();
    }

    public void c(int[] iArr) {
        this.d = iArr;
        this.a.setShader(new LinearGradient(0.0f, 0.0f, d.d(this.f8871f), 0.0f, this.d, (float[]) null, Shader.TileMode.CLAMP));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        a();
        canvas.drawPath(this.b, this.a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
