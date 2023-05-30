package com.jd.lib.un.basewidget.widget.watermark;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.DpiUtil;

/* loaded from: classes16.dex */
public class WatermarkDrawable extends Drawable {
    private Context context;
    private Paint mPaint = new Paint();
    private float mRotation;
    private String mText;
    private int mTextColor;
    private float mTextSize;

    public WatermarkDrawable(Context context) {
        this.context = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        int i2 = getBounds().right;
        int i3 = getBounds().bottom;
        int sqrt = (int) Math.sqrt((i2 * i2) + (i3 * i3));
        this.mPaint.setColor(this.mTextColor);
        this.mPaint.setTextSize(DpiUtil.dip2px(this.context, this.mTextSize));
        this.mPaint.setAntiAlias(true);
        float measureText = this.mPaint.measureText(this.mText);
        int i4 = 0;
        canvas.drawColor(0);
        canvas.rotate(-30.0f);
        int dip2px = DpiUtil.dip2px(this.context, 80.0f);
        int dip2px2 = DpiUtil.dip2px(this.context, 60.0f);
        int dip2px3 = DpiUtil.dip2px(this.context, 20.0f);
        double tan = Math.tan(Math.toRadians(Math.abs(this.mRotation)));
        String str = Math.cos(Math.toRadians(Math.abs(this.mRotation))) + " cos";
        while (true) {
            float f2 = dip2px3;
            if (f2 < i2 + measureText) {
                double d = i4;
                float f3 = dip2px2 + measureText;
                float f4 = measureText;
                int i5 = i4;
                double d2 = f3;
                Double.isNaN(d2);
                Double.isNaN(d);
                String str2 = "startY:" + ((int) (d * d2 * tan));
                int i6 = -dip2px;
                while (i6 <= sqrt) {
                    String str3 = this.mText;
                    int i7 = i2;
                    int i8 = dip2px2;
                    Double.isNaN(i6);
                    Double.isNaN(dip2px3);
                    canvas.drawText(str3, (int) (r2 - (r7 * tan)), i6 + r4, this.mPaint);
                    i6 += dip2px;
                    i2 = i7;
                    sqrt = sqrt;
                    dip2px2 = i8;
                    dip2px3 = dip2px3;
                }
                i4 = i5 + 1;
                dip2px3 = (int) (f2 + f3);
                measureText = f4;
            } else {
                canvas.save();
                canvas.restore();
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public WatermarkDrawable setmRotation(float f2) {
        this.mRotation = f2;
        return this;
    }

    public WatermarkDrawable setmText(String str) {
        this.mText = str;
        return this;
    }

    public WatermarkDrawable setmTextColor(int i2) {
        this.mTextColor = i2;
        return this;
    }

    public WatermarkDrawable setmTextSize(float f2) {
        this.mTextSize = f2;
        return this;
    }
}
