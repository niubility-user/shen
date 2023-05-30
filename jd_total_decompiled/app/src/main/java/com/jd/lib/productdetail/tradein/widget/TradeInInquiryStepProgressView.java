package com.jd.lib.productdetail.tradein.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;

/* loaded from: classes16.dex */
public class TradeInInquiryStepProgressView extends View {

    /* renamed from: g  reason: collision with root package name */
    public final Paint f5629g;

    /* renamed from: h  reason: collision with root package name */
    public final RectF f5630h;

    /* renamed from: i  reason: collision with root package name */
    public final float f5631i;

    /* renamed from: j  reason: collision with root package name */
    public final float f5632j;

    /* renamed from: k  reason: collision with root package name */
    public int f5633k;

    /* renamed from: l  reason: collision with root package name */
    public int f5634l;

    public TradeInInquiryStepProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5629g = new Paint(1);
        this.f5630h = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.f5631i = TypedValue.applyDimension(2, 10.0f, getResources().getDisplayMetrics());
        this.f5632j = TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        this.f5630h.right = getWidth() * 1.0f;
        this.f5630h.bottom = getHeight() * 1.0f;
        this.f5629g.setColor(IconFloorEntity.BGCOLOR_DEFAULT);
        RectF rectF = this.f5630h;
        float f2 = rectF.bottom / 2.0f;
        canvas.drawRoundRect(rectF, f2, f2, this.f5629g);
        if (this.f5633k == 0) {
            return;
        }
        this.f5629g.setTextSize(this.f5631i);
        this.f5629g.setTypeface(Typeface.DEFAULT_BOLD);
        String format = String.format("%d/%d", Integer.valueOf(this.f5634l), Integer.valueOf(this.f5633k));
        if (this.f5634l == 0) {
            this.f5629g.setColor(-381927);
            Rect rect = new Rect();
            this.f5629g.getTextBounds(format, 0, format.length(), rect);
            canvas.drawText(format, this.f5632j, this.f5630h.centerY() - rect.exactCenterY(), this.f5629g);
            return;
        }
        float measureText = this.f5629g.measureText(format) + this.f5632j;
        this.f5630h.right = Math.max(Math.max(measureText, this.f5630h.right * ((this.f5634l * 1.0f) / this.f5633k)), this.f5632j + measureText);
        Paint paint = this.f5629g;
        RectF rectF2 = this.f5630h;
        paint.setShader(new LinearGradient(0.0f, 0.0f, rectF2.right, rectF2.bottom, -52172, -33536, Shader.TileMode.CLAMP));
        RectF rectF3 = this.f5630h;
        float f3 = rectF3.bottom / 2.0f;
        canvas.drawRoundRect(rectF3, f3, f3, this.f5629g);
        this.f5629g.setShader(null);
        this.f5629g.setColor(-1);
        Rect rect2 = new Rect();
        this.f5629g.getTextBounds(format, 0, format.length(), rect2);
        RectF rectF4 = this.f5630h;
        canvas.drawText(format, rectF4.right - measureText, rectF4.centerY() - rect2.exactCenterY(), this.f5629g);
    }

    public TradeInInquiryStepProgressView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5629g = new Paint(1);
        this.f5630h = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.f5631i = TypedValue.applyDimension(2, 10.0f, getResources().getDisplayMetrics());
        this.f5632j = TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
    }
}
