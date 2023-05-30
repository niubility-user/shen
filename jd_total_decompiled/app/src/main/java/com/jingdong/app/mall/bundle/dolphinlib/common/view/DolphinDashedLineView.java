package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.jd.lib.babel.ifloor.utils.ColorUtil;

/* loaded from: classes19.dex */
public class DolphinDashedLineView extends View {
    private String mColor;
    private int mStrokeWidth;
    private int mX;
    private int mY;

    public DolphinDashedLineView(Context context, int i2, int i3) {
        super(context);
        this.mX = i2;
        this.mY = i3;
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        int i2 = this.mStrokeWidth;
        paint.setStrokeWidth(i2 > 0 ? i2 : 1.0f);
        boolean isEmpty = TextUtils.isEmpty(this.mColor);
        int i3 = ViewCompat.MEASURED_SIZE_MASK;
        if (!isEmpty) {
            i3 = ColorUtil.parseColor(this.mColor, ViewCompat.MEASURED_SIZE_MASK);
        }
        paint.setColor(i3);
        paint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(this.mX, this.mY);
        canvas.drawPath(path, paint);
    }

    public void setStyle(int i2, String str) {
        this.mStrokeWidth = i2;
        this.mColor = str;
    }
}
