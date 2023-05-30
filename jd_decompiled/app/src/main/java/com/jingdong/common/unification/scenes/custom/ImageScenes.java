package com.jingdong.common.unification.scenes.custom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Transformation;
import com.jingdong.common.unification.scenes.base.BaseScenes;
import com.jingdong.common.unification.scenes.utils.ScenesConstant;

/* loaded from: classes6.dex */
public class ImageScenes extends BaseScenes {
    private Bitmap bitmap;
    private Paint paint;

    public ImageScenes(View view, Rect rect) {
        super(view, rect);
        this.paint = new Paint(1);
    }

    private void configPaintAndCanvas(Transformation transformation, Paint paint, Canvas canvas) {
        if (transformation != null) {
            paint.setAlpha((int) (transformation.getAlpha() * 255.0f));
            canvas.concat(transformation.getMatrix());
        }
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void drawSelf(Canvas canvas, Transformation transformation) {
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        Rect rect = this.rect;
        int width = this.bitmap.getWidth();
        int height = this.bitmap.getHeight();
        int width2 = (rect.left + (rect.width() / 2)) - (width / 2);
        configPaintAndCanvas(transformation, this.paint, canvas);
        canvas.drawBitmap(this.bitmap, width2, (rect.top + (rect.height() / 2)) - (height / 2), this.paint);
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void parseAndUpdateData() {
        Object parseObjData = parseObjData(ScenesConstant.Value.SCENES_BITMAP_OBJ);
        if (parseObjData instanceof Bitmap) {
            this.bitmap = (Bitmap) parseObjData;
            return;
        }
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        this.bitmap = null;
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    public void release() {
        super.release();
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        this.bitmap = null;
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void updateDrawConfig() {
    }
}
