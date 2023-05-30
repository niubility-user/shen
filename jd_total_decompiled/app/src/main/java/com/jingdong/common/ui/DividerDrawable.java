package com.jingdong.common.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* loaded from: classes6.dex */
public class DividerDrawable extends Drawable {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int orientation;

    public DividerDrawable(int i2) {
        this.orientation = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();
        Paint paint = new Paint();
        if (this.orientation == 1) {
            paint.setShader(new LinearGradient(clipBounds.left, clipBounds.top, clipBounds.right, r3 + 1, new int[]{592137, -16185079, 592137}, (float[]) null, Shader.TileMode.REPEAT));
            canvas.drawRect(clipBounds.left, clipBounds.top, clipBounds.right, r1 + 1, paint);
            float f2 = clipBounds.left;
            int i2 = clipBounds.top;
            paint.setShader(new LinearGradient(f2, i2 + 1, clipBounds.right, i2 + 2, new int[]{8026746, -8750470, 8026746}, (float[]) null, Shader.TileMode.REPEAT));
            float f3 = clipBounds.left;
            int i3 = clipBounds.top;
            canvas.drawRect(f3, i3 + 1, clipBounds.right, i3 + 2, paint);
            return;
        }
        paint.setShader(new LinearGradient(clipBounds.left, clipBounds.top, r3 + 1, clipBounds.bottom, new int[]{592137, -16185079, 592137}, (float[]) null, Shader.TileMode.REPEAT));
        canvas.drawRect(clipBounds.left, clipBounds.top, r1 + 1, clipBounds.bottom, paint);
        int i4 = clipBounds.left;
        paint.setShader(new LinearGradient(i4 + 1, clipBounds.top, i4 + 2, clipBounds.bottom, new int[]{8026746, -8750470, 8026746}, (float[]) null, Shader.TileMode.REPEAT));
        int i5 = clipBounds.left;
        canvas.drawRect(i5 + 1, clipBounds.top, i5 + 2, clipBounds.bottom, paint);
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
