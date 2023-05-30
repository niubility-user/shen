package com.jingdong.common.widget.shadow.engine;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;

/* loaded from: classes12.dex */
public class OvalShadowEngine extends BaseShadowEngine {
    private Path mShadowClipPath;
    private Paint mShadowPaint;
    private RectF mShadowRoundClipRect;
    private RectF mShadowRoundRect;

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine
    public void initConfig() {
        Paint paint = new Paint(1);
        this.mShadowPaint = paint;
        paint.setColor(this.mShadowColor);
        if (this.mShadowRadius > 0) {
            this.mShadowPaint.setMaskFilter(new BlurMaskFilter(this.mShadowRadius, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.mShadowPaint.setMaskFilter(null);
        }
        this.mShadowRoundRect = new RectF();
        this.mShadowRoundClipRect = new RectF();
        this.mShadowClipPath = new Path();
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public boolean onClipChildCanvas(Canvas canvas, View view) {
        this.mShadowRoundClipRect.left = view.getLeft();
        this.mShadowRoundClipRect.top = view.getTop();
        this.mShadowRoundClipRect.right = view.getRight();
        this.mShadowRoundClipRect.bottom = view.getBottom();
        this.mShadowClipPath.reset();
        this.mShadowClipPath.addOval(this.mShadowRoundClipRect, Path.Direction.CW);
        canvas.clipPath(this.mShadowClipPath, Region.Op.REPLACE);
        return true;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDraw(Canvas canvas) {
        if (this.mShadowEnable) {
            RectF rectF = this.mShadowRoundRect;
            Rect rect = this.mShadowBounds;
            rectF.left = rect.left;
            rectF.top = rect.top;
            rectF.right = rect.right;
            rectF.bottom = rect.bottom;
            canvas.drawOval(rectF, this.mShadowPaint);
        }
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDrawOver(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onLayout(View view, int i2, int i3, int i4, int i5) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine, com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowColor(int i2) {
        super.setShadowColor(i2);
        Paint paint = this.mShadowPaint;
        if (paint != null) {
            paint.setColor(i2);
        }
    }
}
