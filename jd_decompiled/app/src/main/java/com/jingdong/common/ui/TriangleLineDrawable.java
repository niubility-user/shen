package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class TriangleLineDrawable extends Drawable {
    private Context mContext;

    public TriangleLineDrawable(Context context) {
        this.mContext = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(-1);
        Rect bounds = getBounds();
        int width = bounds.right - (bounds.width() / 2);
        int dip2px = bounds.bottom - DpiUtil.dip2px(this.mContext, 1.0f);
        int dip2px2 = bounds.top + DpiUtil.dip2px(this.mContext, 1.0f);
        Point point2 = new Point(width - DpiUtil.dip2px(this.mContext, 5.0f), dip2px);
        Point point3 = new Point(width, dip2px - DpiUtil.dip2px(this.mContext, 5.0f));
        Point point4 = new Point(width + DpiUtil.dip2px(this.mContext, 5.0f), dip2px);
        Paint paint = new Paint();
        paint.setColor(-7829368);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DpiUtil.dip2px(this.mContext, 1.0f));
        Paint paint2 = new Paint();
        paint2.setColor(-657931);
        paint2.setStyle(Paint.Style.FILL);
        Path path = new Path();
        float f2 = dip2px;
        path.moveTo(bounds.left, f2);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(bounds.right, f2);
        Path path2 = new Path();
        float f3 = dip2px2;
        path2.moveTo(bounds.left, f3);
        path2.lineTo(bounds.right, f3);
        canvas.drawPath(path, paint2);
        canvas.drawRect(new Rect(bounds.left, dip2px, bounds.right, bounds.bottom), paint2);
        canvas.drawRect(new Rect(bounds.left, bounds.top, bounds.right, dip2px2), paint2);
        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
