package com.jingdong.jdreact.plugin.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class ShadowView extends FrameLayout {
    private float mCornerRadius;
    private Paint mPaint;
    private RectF mRectF;
    private String mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private float mShadowWidth;

    public ShadowView(Context context) {
        super(context);
        this.mPaint = new Paint(1);
        this.mRectF = new RectF();
        this.mShadowColor = JDDarkUtil.COLOR_33000000;
        this.mShadowRadius = 3.0f;
        this.mCornerRadius = 3.0f;
        init();
    }

    private void drawShadow(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        int i2 = 0;
        try {
            i2 = Color.parseColor(this.mShadowColor);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (i2 > 0) {
            float f2 = this.mShadowRadius;
            if (f2 <= 0.0f) {
                return;
            }
            this.mPaint.setShadowLayer(f2, this.mShadowDx, this.mShadowDy, i2);
            this.mRectF.set(0.0f, 0.0f, (getWidth() - this.mShadowWidth) - this.mShadowDx, (getHeight() - this.mShadowWidth) - this.mShadowDy);
            RectF rectF = this.mRectF;
            float f3 = this.mCornerRadius;
            canvas.drawRoundRect(rectF, f3, f3, this.mPaint);
        }
    }

    private void init() {
        this.mShadowWidth = this.mShadowRadius;
        setLayerType(1, null);
        setWillNotDraw(false);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(0);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShadow(canvas);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public void setCornerRadius(float f2) {
        this.mCornerRadius = f2;
    }

    public void setOffset(float f2, float f3) {
        this.mShadowDx = f2;
        this.mShadowDy = f3;
    }

    public void setShadowColor(String str) {
        this.mShadowColor = str;
    }

    public void setShadowRadius(float f2) {
        this.mShadowRadius = f2;
        this.mShadowWidth = f2;
    }

    public void updateShow() {
        float f2 = this.mShadowWidth;
        setPadding(0, 0, ((int) this.mShadowDx) + ((int) f2), ((int) this.mShadowDy) + ((int) f2));
    }

    public ShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint(1);
        this.mRectF = new RectF();
        this.mShadowColor = JDDarkUtil.COLOR_33000000;
        this.mShadowRadius = 3.0f;
        this.mCornerRadius = 3.0f;
        init();
    }

    public ShadowView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPaint = new Paint(1);
        this.mRectF = new RectF();
        this.mShadowColor = JDDarkUtil.COLOR_33000000;
        this.mShadowRadius = 3.0f;
        this.mCornerRadius = 3.0f;
        init();
    }
}
