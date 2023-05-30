package com.jingdong.common.widget.shadow.engine;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import com.jingdong.common.pool.bitmappool.GlideBitmapPool;

/* loaded from: classes12.dex */
public class RectShadowEngine extends BaseShadowEngine {
    private Paint mShadowClipPaint;
    private Path mShadowDrawingPath;
    private Paint mShadowPaint;
    private float[] mShadowRectRoundRadius;
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
        Paint paint2 = new Paint(1);
        this.mShadowClipPaint = paint2;
        paint2.setColor(-16777216);
        this.mShadowClipPaint.setStyle(Paint.Style.FILL);
        this.mShadowClipPaint.setFilterBitmap(true);
        this.mShadowClipPaint.setDither(true);
        this.mShadowRoundRect = new RectF();
        this.mShadowRoundClipRect = new RectF();
        this.mShadowClipPath = new Path();
        this.mShadowDrawingPath = new Path();
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public boolean onClipChildCanvas(Canvas canvas, View view) {
        if (this.mShadowRectRoundRadius == null) {
            return true;
        }
        if (!this.isSettingClipPath || this.mShadowClipPath == null) {
            if (this.mShadowClipPath == null) {
                this.mShadowClipPath = new Path();
            }
            this.mShadowClipPath.reset();
            this.mShadowRoundClipRect.left = view.getLeft();
            this.mShadowRoundClipRect.top = view.getTop();
            this.mShadowRoundClipRect.right = view.getRight();
            this.mShadowRoundClipRect.bottom = view.getBottom();
            this.mShadowClipPath.addRoundRect(this.mShadowRoundClipRect, this.mShadowRectRoundRadius, Path.Direction.CW);
        }
        Bitmap bitmap = GlideBitmapPool.getBitmap(this.mParent.getWidth(), this.mParent.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(bitmap).drawPath(this.mShadowClipPath, this.mShadowClipPaint);
        this.mShadowClipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mShadowClipPaint);
        this.mShadowClipPaint.setXfermode(null);
        GlideBitmapPool.putBitmap(bitmap);
        return true;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDraw(Canvas canvas) {
        if (this.mShadowEnable) {
            if (this.mShadowRectRoundRadius == null) {
                canvas.drawRect(this.mShadowBounds, this.mShadowPaint);
                return;
            }
            RectF rectF = this.mShadowRoundRect;
            Rect rect = this.mShadowBounds;
            rectF.left = rect.left;
            rectF.top = rect.top;
            rectF.right = rect.right;
            rectF.bottom = rect.bottom;
            this.mShadowDrawingPath.reset();
            this.mShadowDrawingPath.addRoundRect(this.mShadowRoundRect, this.mShadowRectRoundRadius, Path.Direction.CW);
            canvas.drawPath(this.mShadowDrawingPath, this.mShadowPaint);
        }
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDrawOver(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onLayout(View view, int i2, int i3, int i4, int i5) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine, com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setCornerRadii(float[] fArr) {
        this.mShadowRectRoundRadius = fArr;
    }

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine, com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowColor(int i2) {
        super.setShadowColor(i2);
        Paint paint = this.mShadowPaint;
        if (paint != null) {
            paint.setColor(i2);
        }
    }

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine, com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowRadius(int i2) {
        super.setShadowRadius(i2);
        if (i2 > 0) {
            this.mShadowPaint.setMaskFilter(new BlurMaskFilter(i2, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.mShadowPaint.setMaskFilter(null);
        }
    }
}
