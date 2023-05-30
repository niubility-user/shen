package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class RecordProgressView extends View {
    private int backColor;
    private int barHeight;
    private Paint mPaint;
    private float mProgress;
    private Drawable mThumb;
    private int progressColor;

    public RecordProgressView(Context context) {
        super(context);
        this.mProgress = 0.0f;
        this.barHeight = 4;
        this.backColor = 871099371;
        this.progressColor = -905168;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.barHeight = DPIUtil.dip2px(2.5f);
        this.mThumb = getResources().getDrawable(R.drawable.jdreact_video_record_progress_bar);
        post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.video.RecordProgressView.1
            @Override // java.lang.Runnable
            public void run() {
                int height = RecordProgressView.this.getHeight();
                RecordProgressView.this.mThumb.setBounds(0, 0, height, height);
            }
        });
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0);
        int height = getHeight();
        int width = getWidth();
        int i2 = (height - this.barHeight) / 2;
        this.mPaint.setColor(this.backColor);
        float f2 = i2;
        float f3 = width;
        canvas.drawRect(0.0f, f2, f3, this.barHeight + i2, this.mPaint);
        this.mPaint.setColor(this.progressColor);
        canvas.drawRect(0.0f, f2, f3 * this.mProgress, i2 + this.barHeight, this.mPaint);
        int save = canvas.save();
        canvas.translate((getWidth() * this.mProgress) - (height / 2), 0.0f);
        this.mThumb.draw(canvas);
        canvas.restoreToCount(save);
    }

    public void setProgress(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.mProgress = f2;
        invalidate();
    }

    public RecordProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgress = 0.0f;
        this.barHeight = 4;
        this.backColor = 871099371;
        this.progressColor = -905168;
        init();
    }

    public RecordProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mProgress = 0.0f;
        this.barHeight = 4;
        this.backColor = 871099371;
        this.progressColor = -905168;
        init();
    }
}
