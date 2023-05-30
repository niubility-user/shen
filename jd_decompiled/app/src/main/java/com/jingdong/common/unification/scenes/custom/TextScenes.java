package com.jingdong.common.unification.scenes.custom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Transformation;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.unification.scenes.base.BaseScenes;
import com.jingdong.common.unification.scenes.utils.ScenesConstant;

/* loaded from: classes6.dex */
public class TextScenes extends BaseScenes {
    private boolean isElder;
    private boolean isTop;
    private Paint paint;
    private int tempColor;
    private Paint tempPaint;
    private int tempSize;
    private String text;
    private int textColor;
    private int textSize;
    private Object textType;

    public TextScenes(View view, Rect rect, boolean z, boolean z2) {
        super(view, rect);
        this.text = "";
        this.textColor = -16777216;
        this.textSize = 16;
        this.tempSize = 10;
        this.tempColor = -13421773;
        this.paint = new Paint(1);
        this.tempPaint = new Paint(1);
        this.isTop = z;
        this.isElder = z2;
        if (z2) {
            this.textSize = DpiUtil.dip2px(view.getContext(), 18.0f);
            this.tempSize = DpiUtil.dip2px(view.getContext(), 10.0f);
        }
        this.tempPaint.setTextSize(this.tempSize);
        this.tempPaint.setColor(this.tempColor);
    }

    private void configPaintAndCanvas(Transformation transformation, Paint paint, Canvas canvas) {
        if (transformation != null) {
            paint.setAlpha((int) (transformation.getAlpha() * 255.0f));
            canvas.concat(transformation.getMatrix());
        }
    }

    private void configPaintByTextType() {
        Object obj = this.textType;
        if (obj instanceof Typeface) {
            Typeface typeface = (Typeface) obj;
            this.paint.setTypeface(typeface);
            this.tempPaint.setTypeface(typeface);
            return;
        }
        this.paint.setTypeface(null);
        this.tempPaint.setTypeface(null);
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void drawSelf(Canvas canvas, Transformation transformation) {
        int height;
        int height2;
        int height3;
        Rect rect = this.rect;
        Rect rect2 = new Rect();
        Paint paint = this.paint;
        String str = this.text;
        paint.getTextBounds(str, 0, str.length(), rect2);
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        if (this.isElder) {
            this.tempPaint.getTextBounds("\u7b2c ", 0, 2, rect3);
            this.tempPaint.getTextBounds(" \u9875", 0, 2, rect4);
        }
        int width = rect.left + (((rect.width() / 2) - (rect2.width() / 2)) - rect3.width());
        if (this.isElder) {
            height2 = rect.top + (rect.height() / 2);
            height3 = rect2.height() / 2;
        } else if (this.isTop) {
            height2 = rect.top + (rect.height() / 2);
            height3 = (rect2.height() * 3) / 4;
        } else {
            height = ((rect2.height() * 3) / 4) + rect.top;
            configPaintAndCanvas(transformation, this.paint, canvas);
            if (this.isElder && Integer.parseInt(this.text) < 10) {
                float f2 = height;
                canvas.drawText("\u7b2c ", 0, 2, (float) (width - 5), f2, this.tempPaint);
                canvas.drawText(" \u9875", 0, 2, rect3.width() + width + rect2.width(), f2, this.tempPaint);
            }
            String str2 = this.text;
            canvas.drawText(str2, 0, str2.length(), width + rect3.width(), height, this.paint);
        }
        height = height2 + height3;
        configPaintAndCanvas(transformation, this.paint, canvas);
        if (this.isElder) {
            float f22 = height;
            canvas.drawText("\u7b2c ", 0, 2, (float) (width - 5), f22, this.tempPaint);
            canvas.drawText(" \u9875", 0, 2, rect3.width() + width + rect2.width(), f22, this.tempPaint);
        }
        String str22 = this.text;
        canvas.drawText(str22, 0, str22.length(), width + rect3.width(), height, this.paint);
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void parseAndUpdateData() {
        this.text = parseStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.text);
        this.textColor = parseIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.textColor);
        if (!this.isElder) {
            this.textSize = parseIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.textSize);
        }
        this.textType = parseObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.textType);
    }

    @Override // com.jingdong.common.unification.scenes.base.BaseScenes
    protected void updateDrawConfig() {
        this.paint.setTextSize(this.textSize);
        this.paint.setColor(this.textColor);
        configPaintByTextType();
    }
}
