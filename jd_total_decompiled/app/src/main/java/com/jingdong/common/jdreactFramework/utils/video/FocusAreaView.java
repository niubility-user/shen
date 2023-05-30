package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes5.dex */
public class FocusAreaView extends View {
    private boolean drawRect;
    private Rect mArea;
    private Paint mPaint;

    public FocusAreaView(Context context) {
        super(context);
        this.drawRect = false;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-1);
        this.mPaint.setStrokeWidth(5.0f);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Rect rect;
        super.onDraw(canvas);
        if (!this.drawRect || (rect = this.mArea) == null) {
            return;
        }
        canvas.drawRect(rect, this.mPaint);
        postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.video.FocusAreaView.1
            @Override // java.lang.Runnable
            public void run() {
                FocusAreaView.this.drawRect = false;
                FocusAreaView.this.invalidate();
            }
        }, 1200L);
    }

    public void showFocusArea(Rect rect) {
        this.mArea = rect;
        this.drawRect = true;
        invalidate();
    }

    public FocusAreaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawRect = false;
        init();
    }

    public FocusAreaView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.drawRect = false;
        init();
    }
}
