package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.widget.LinearLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class SkuLabelSpecialLayout extends LinearLayout {
    private Paint labelPaint;
    private Path labelPath;
    private GradientTextView mLabelText;
    private int mPreWidth;

    public SkuLabelSpecialLayout(Context context) {
        super(context);
        this.labelPaint = new Paint(1);
        this.labelPath = new Path();
        GradientTextView gradientTextView = new GradientTextView(context);
        this.mLabelText = gradientTextView;
        gradientTextView.setTextColor(-1);
        this.mLabelText.setGravity(1);
        this.mLabelText.setMaxLines(1);
        setGravity(17);
        addView(this.mLabelText, new LinearLayout.LayoutParams(-2, -2));
    }

    private void drawBgColor(Canvas canvas) {
        int width = getWidth();
        if (this.labelPath.isEmpty() || this.mPreWidth != width) {
            this.mPreWidth = width;
            int height = getHeight();
            int d = d.d(24);
            int d2 = d.d(8);
            this.labelPath.reset();
            float f2 = d2;
            this.labelPath.moveTo(0.0f, f2);
            this.labelPath.quadTo(0.0f, 0.0f, f2, 0.0f);
            this.labelPath.lineTo(width - d2, 0.0f);
            float f3 = width;
            this.labelPath.quadTo(f3, 0.0f, f3, f2);
            float f4 = d - d2;
            this.labelPath.lineTo(f3, f4);
            float f5 = d + d2;
            float f6 = height;
            this.labelPath.cubicTo(f3, f5, r1 + d2, f6, width >> 1, f6);
            this.labelPath.cubicTo(r1 - d2, f6, 0.0f, f5, 0.0f, f4);
            this.labelPath.lineTo(0.0f, f2);
            this.labelPath.close();
        }
        canvas.drawPath(this.labelPath, this.labelPaint);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            drawBgColor(canvas);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.dispatchDraw(canvas);
    }

    public void setBgColor(int[] iArr, int i2) {
        if (iArr.length == 1) {
            this.labelPaint.setColor(iArr[0]);
            this.labelPaint.setShader(null);
        } else if (iArr.length > 1) {
            this.labelPaint.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, iArr, (float[]) null, Shader.TileMode.CLAMP));
        }
    }

    public void setText(String str, int i2) {
        this.mLabelText.setText(str);
        g.o(this.mLabelText, i2);
    }

    public void setTextGradient(GradientTextView.GradientType gradientType, int[] iArr) {
        this.mLabelText.setTextGradient(gradientType, iArr);
    }
}
