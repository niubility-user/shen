package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class RoundProgressBar extends View implements IThemeChange {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    private GlobalThemeController controller;
    private volatile int max;
    private Paint paint;
    private volatile int progress;
    public int roundColor;
    public int roundProgressColor;
    public float roundWidth;
    private int style;
    public int textColor;
    private boolean textIsDisplayable;
    public float textSize;

    public RoundProgressBar(Context context) {
        this(context, null);
        initTheme();
    }

    private void initTheme() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
    }

    public synchronized int getMax() {
        return this.max;
    }

    public synchronized int getProgress() {
        return this.progress;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        float f2 = width;
        int i2 = ((int) (f2 - (this.roundWidth / 2.0f))) - 2;
        int progress = getProgress();
        int max = getMax();
        this.paint.setColor(this.roundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setAntiAlias(true);
        canvas.drawCircle(f2, f2, i2, this.paint);
        this.paint.setStrokeWidth(4.0f);
        canvas.drawCircle(f2, f2, (float) (i2 - 36), this.paint);
        this.paint.setStrokeWidth(0.0f);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        int i3 = (int) ((progress / max) * 100.0f);
        float measureText = this.paint.measureText(i3 + "%");
        if (this.textIsDisplayable && this.style == 0) {
            canvas.drawText(i3 + "%", f2 - (measureText / 2.0f), f2 + (this.textSize / 2.0f), this.paint);
        }
        this.paint.setStrokeWidth(this.roundWidth * 2.0f);
        this.paint.setColor(this.roundProgressColor);
        float f3 = (width - i2) + 4;
        float f4 = (float) ((width + i2) - 4);
        RectF rectF = new RectF(f3, f3, f4, f4);
        int i4 = this.style;
        if (i4 == 0) {
            this.paint.setStyle(Paint.Style.STROKE);
            float f5 = (progress * R2.attr.additionBottom) / (max * 2);
            canvas.drawArc(rectF, 0.0f, f5, false, this.paint);
            canvas.drawArc(rectF, 180.0f, f5, false, this.paint);
        } else if (i4 != 1) {
        } else {
            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
            if (progress != 0) {
                float f6 = (progress * R2.attr.additionBottom) / (max * 2);
                canvas.drawArc(rectF, 0.0f, f6, false, this.paint);
                canvas.drawArc(rectF, 180.0f, f6, false, this.paint);
            }
        }
    }

    public synchronized void setMax(int i2) {
        if (i2 >= 0) {
            this.max = i2;
        } else {
            throw new IllegalArgumentException("max not less than 0");
        }
    }

    public synchronized void setProgress(int i2) {
        if (i2 >= 0) {
            if (i2 > this.max) {
                i2 = this.max;
            }
            if (i2 <= this.max) {
                this.progress = i2;
                postInvalidate();
            }
        } else {
            throw new IllegalArgumentException("progress not less than 0");
        }
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        initTheme();
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.paint = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundProgressBar);
        this.roundColor = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_roundColor, getResources().getColor(R.color.c_FBC0C0));
        int i3 = R.styleable.RoundProgressBar_roundProgressColor;
        Resources resources = getResources();
        int i4 = R.color.c_F23030;
        this.roundProgressColor = obtainStyledAttributes.getColor(i3, resources.getColor(i4));
        this.textColor = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_textColor, getResources().getColor(i4));
        this.textSize = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_textSize, 40.0f);
        this.roundWidth = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_roundWidth, 4.0f);
        this.max = obtainStyledAttributes.getInteger(R.styleable.RoundProgressBar_max, 100);
        this.progress = obtainStyledAttributes.getInteger(R.styleable.RoundProgressBar_progress, 0);
        this.textIsDisplayable = obtainStyledAttributes.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        this.style = obtainStyledAttributes.getInt(R.styleable.RoundProgressBar_style, 0);
        obtainStyledAttributes.recycle();
        initTheme();
    }
}
