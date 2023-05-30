package com.jingdong.common.unification.scenes.custom;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Transformation;
import androidx.annotation.DrawableRes;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.unification.scenes.base.BaseScenes;
import com.jingdong.common.unification.scenes.utils.ScenesConstant;

/* loaded from: classes6.dex */
public class LineScenes extends BaseScenes {
    private Context context;
    private int drawableId;
    private int lineColor;
    private int lineWidth;
    private int padding;
    private Paint paint;

    public LineScenes(View view, Rect rect) {
        super(view, rect);
        this.lineWidth = 16;
        this.lineColor = -16777216;
        this.padding = 0;
        this.drawableId = R.drawable.un_page_num_swith_line;
        if (view != null) {
            this.context = view.getContext();
        }
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
    }

    private void configPaintAndCanvas(Transformation transformation, Paint paint, Canvas canvas) {
        if (transformation != null) {
            paint.setAlpha((int) (transformation.getAlpha() * 255.0f));
            canvas.concat(transformation.getMatrix());
        }
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void drawSelf(Canvas canvas, Transformation transformation) {
        Rect rect = this.rect;
        int i2 = rect.left;
        int i3 = this.padding;
        int i4 = i2 + i3;
        int i5 = rect.right - i3;
        int height = rect.top + (rect.height() / 2);
        configPaintAndCanvas(transformation, this.paint, canvas);
        Context context = this.context;
        if (context != null) {
            canvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(), this.drawableId), (rect.left + (rect.width() / 2)) - (r11.getWidth() / 2), rect.top, this.paint);
            return;
        }
        float f2 = height;
        canvas.drawLine(i4, f2, i5, f2, this.paint);
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void parseAndUpdateData() {
        this.lineWidth = parseIntData(ScenesConstant.Value.SCENES_LINE_WIDTH_INT, this.lineWidth);
        this.lineColor = parseIntData(ScenesConstant.Value.SCENES_LINE_COLOR_INT, this.lineColor);
        this.padding = parseIntData(ScenesConstant.Value.SCENES_PADDING_INT, this.padding);
    }

    public void setDrawableId(@DrawableRes int i2) {
        this.drawableId = i2;
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void updateDrawConfig() {
        this.paint.setColor(this.lineColor);
        this.paint.setStrokeWidth(this.lineWidth);
    }
}
