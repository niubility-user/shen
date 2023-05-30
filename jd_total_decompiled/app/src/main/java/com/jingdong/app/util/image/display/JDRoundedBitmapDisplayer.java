package com.jingdong.app.util.image.display;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

@Deprecated
/* loaded from: classes4.dex */
public class JDRoundedBitmapDisplayer implements JDBitmapDisplayer {
    private float[] mCornersRadii;

    /* loaded from: classes4.dex */
    public static class RoundedDrawable extends Drawable {
        protected final BitmapShader bitmapShader;
        protected final float cornerRadius;
        protected final RectF mBitmapRect;
        protected final RectF mRect = new RectF();
        protected final int margin;
        protected final Paint paint;

        public RoundedDrawable(Bitmap bitmap, int i2, int i3) {
            this.cornerRadius = i2;
            this.margin = i3;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
            this.bitmapShader = bitmapShader;
            float f2 = i3;
            this.mBitmapRect = new RectF(f2, f2, bitmap.getWidth() - i3, bitmap.getHeight() - i3);
            Paint paint = new Paint();
            this.paint = paint;
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            RectF rectF = this.mRect;
            float f2 = this.cornerRadius;
            canvas.drawRoundRect(rectF, f2, f2, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            RectF rectF = this.mRect;
            float f2 = this.margin;
            rectF.set(f2, f2, rect.width() - this.margin, rect.height() - this.margin);
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.mBitmapRect, this.mRect, Matrix.ScaleToFit.FILL);
            this.bitmapShader.setLocalMatrix(matrix);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            this.paint.setAlpha(i2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.paint.setColorFilter(colorFilter);
        }
    }

    public JDRoundedBitmapDisplayer(float f2, float f3, float f4, float f5) {
        this.mCornersRadii = null;
        float[] orCreateRoundedCornersRadii = getOrCreateRoundedCornersRadii();
        orCreateRoundedCornersRadii[1] = f2;
        orCreateRoundedCornersRadii[0] = f2;
        orCreateRoundedCornersRadii[3] = f3;
        orCreateRoundedCornersRadii[2] = f3;
        orCreateRoundedCornersRadii[5] = f4;
        orCreateRoundedCornersRadii[4] = f4;
        orCreateRoundedCornersRadii[7] = f5;
        orCreateRoundedCornersRadii[6] = f5;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDRoundedBitmapDisplayer(int i2) {
        this(r1, r1, r1, r1);
        float f2 = i2;
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    @Override // com.jingdong.app.util.image.display.JDBitmapDisplayer
    public void display(Bitmap bitmap, ImageView imageView, int i2) {
    }

    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }
}
