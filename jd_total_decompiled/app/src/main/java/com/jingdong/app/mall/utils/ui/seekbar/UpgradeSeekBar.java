package com.jingdong.app.mall.utils.ui.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class UpgradeSeekBar extends View {

    /* renamed from: g  reason: collision with root package name */
    private int f11937g;

    /* renamed from: h  reason: collision with root package name */
    private float f11938h;

    /* renamed from: i  reason: collision with root package name */
    private float f11939i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f11940j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f11941k;

    /* renamed from: l  reason: collision with root package name */
    private int f11942l;

    /* renamed from: m  reason: collision with root package name */
    private a f11943m;

    /* loaded from: classes4.dex */
    public interface a {
        void a(float f2);
    }

    public UpgradeSeekBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        this.f11942l = DPIUtil.dip2px(6.0f);
        this.f11937g = DPIUtil.dip2px(2.5f);
        Paint paint = new Paint();
        this.f11940j = paint;
        paint.setColor(Color.parseColor("#192E2D2D"));
        this.f11940j.setStyle(Paint.Style.STROKE);
        this.f11940j.setAntiAlias(true);
        this.f11940j.setStrokeWidth(this.f11937g);
        Paint paint2 = new Paint();
        this.f11941k = paint2;
        paint2.setAntiAlias(true);
        this.f11941k.setShader(new LinearGradient(0.0f, 0.0f, 100.0f, 100.0f, Color.parseColor("#FF4F18"), Color.parseColor("#F10000"), Shader.TileMode.CLAMP));
        this.f11941k.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void b(float f2, float f3) {
        this.f11938h = f2;
        this.f11939i = f3;
        invalidate();
        a aVar = this.f11943m;
        if (aVar != null) {
            float f4 = this.f11938h;
            if (f4 > 0.0f) {
                float f5 = this.f11939i / f4;
                aVar.a(f5 > 0.0f ? f5 > 1.0f ? 1.0f : f5 : 0.0f);
                return;
            }
            aVar.a(0.0f);
        }
    }

    public void c(a aVar) {
        this.f11943m = aVar;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getMeasuredHeight() <= 0 || getMeasuredWidth() <= 0) {
            return;
        }
        int i2 = this.f11937g;
        canvas.drawRoundRect(new RectF(i2 / 2, i2 / 2, getMeasuredWidth() - (this.f11937g / 2), getMeasuredHeight() - (this.f11937g / 2)), getMeasuredHeight() / 2, getMeasuredHeight() / 2, this.f11940j);
        float f2 = this.f11939i / this.f11938h;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        int i3 = this.f11942l;
        canvas.drawRoundRect(new RectF(i3, i3, i3 + ((getMeasuredWidth() - (this.f11942l * 2.0f)) * f2), getMeasuredHeight() - this.f11942l), (getMeasuredHeight() - (this.f11942l * 2)) / 2, (getMeasuredHeight() - (this.f11942l * 2)) / 2, this.f11941k);
    }

    public UpgradeSeekBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
