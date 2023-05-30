package com.jingdong.common.widget.shadow.engine;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.view.View;
import com.jingdong.common.pool.bitmappool.GlideBitmapPool;
import com.jingdong.common.widget.shadow.utils.ShadowUtils;

/* loaded from: classes12.dex */
public class AutoFitShadowEngine extends BaseShadowEngine {
    private Bitmap mShadowBitmap;
    private Canvas mShadowCanvas;
    private Paint mShadowClipPaint;
    private Paint mShadowPaint;

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine
    public void initConfig() {
        Paint paint = new Paint(1);
        this.mShadowPaint = paint;
        paint.setColor(this.mShadowColor);
        this.mShadowPaint.setDither(true);
        this.mShadowPaint.setFilterBitmap(true);
        if (this.mShadowRadius > 0) {
            this.mShadowPaint.setMaskFilter(new BlurMaskFilter(this.mShadowRadius, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.mShadowPaint.setMaskFilter(null);
        }
        Paint paint2 = new Paint();
        this.mShadowClipPaint = paint2;
        paint2.setColor(-16777216);
        this.mShadowClipPaint.setStyle(Paint.Style.FILL);
        this.mShadowClipPath = new Path();
        this.mShadowCanvas = new Canvas();
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public boolean onClipChildCanvas(Canvas canvas, View view) {
        Path path = this.mShadowClipPath;
        if (path == null || path.isEmpty()) {
            return true;
        }
        canvas.clipPath(this.mShadowClipPath, Region.Op.REPLACE);
        return true;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.mShadowEnable) {
            if (this.mShadowOriginBounds.width() != 0 && this.mShadowOriginBounds.height() != 0) {
                Bitmap bitmap2 = GlideBitmapPool.getBitmap(this.mShadowOriginBounds.width(), this.mShadowOriginBounds.height(), Bitmap.Config.ARGB_8888);
                this.mShadowBitmap = bitmap2;
                this.mShadowCanvas.setBitmap(bitmap2);
                this.mParent.superDispatchDraw(this.mShadowCanvas);
                Bitmap extractAlpha = this.mShadowBitmap.extractAlpha();
                this.mShadowCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                this.mShadowPaint.setColor(this.mShadowColor);
                Canvas canvas2 = this.mShadowCanvas;
                int i2 = this.mShadowOffsetDx;
                float f2 = i2 == Integer.MAX_VALUE ? 0.0f : i2;
                int i3 = this.mShadowOffsetDy;
                canvas2.drawBitmap(extractAlpha, f2, i3 == Integer.MAX_VALUE ? 0.0f : i3, this.mShadowPaint);
                extractAlpha.recycle();
            } else {
                this.mShadowBitmap = GlideBitmapPool.getBitmap(1, 1, Bitmap.Config.RGB_565);
            }
            this.mShadowPaint.setColor(ShadowUtils.adjustColor(1.0f, this.mShadowColor));
            this.mParent.superDispatchDraw(this.mShadowCanvas);
            if (canvas != null && (bitmap = this.mShadowBitmap) != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(this.mShadowBitmap, 0.0f, 0.0f, this.mShadowPaint);
            }
            GlideBitmapPool.putBitmap(this.mShadowBitmap);
        }
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onDrawOver(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void onLayout(View view, int i2, int i3, int i4, int i5) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.BaseShadowEngine, com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void release() {
        super.release();
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
