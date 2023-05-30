package com.jingdong.app.util.image.placeholder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.R;
import com.jingdong.JdImageToolKit;

/* loaded from: classes4.dex */
public class PlaceholderDrawable extends Drawable {
    private static Bitmap bitmap;
    private int imageViewBackground;
    protected Paint paint;

    /* loaded from: classes4.dex */
    private static class PlaceDrawableHolder {
        private static final PlaceholderDrawable INSTANCE = new PlaceholderDrawable();

        private PlaceDrawableHolder() {
        }
    }

    private PlaceholderDrawable() {
        this.imageViewBackground = Color.parseColor("#f8f8f8");
        this.paint = new Paint();
        if (bitmap == null) {
            try {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) JdImageToolKit.getContext().getResources().getDrawable(R.drawable.jd_place_image);
                if (bitmapDrawable == null) {
                    return;
                }
                Bitmap bitmap2 = bitmapDrawable.getBitmap();
                bitmap = bitmap2;
                if (bitmap2 == null) {
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static final PlaceholderDrawable getInstance() {
        return PlaceDrawableHolder.INSTANCE;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        RectF rectF;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect bounds = getBounds();
        Rect rect = new Rect(bounds.left, bounds.top, bounds.right + width, bounds.bottom + height);
        if (bounds.width() < bounds.height()) {
            float f2 = bounds.right / 6.0f;
            float f3 = (width * f2) / height;
            int i2 = bounds.right;
            int i3 = i2 / 2;
            int i4 = ((i2 * width) / height) / 2;
            int i5 = bounds.bottom / 2;
            rectF = new RectF((i3 - i4) + ((f3 * 8.0f) / 10.0f), (i5 - i3) + f2, (i4 + i3) - f3, (i5 + i3) - ((f2 * 8.0f) / 10.0f));
        } else {
            float f4 = bounds.bottom / 6.0f;
            float f5 = (width * f4) / height;
            int i6 = bounds.right / 2;
            int i7 = bounds.bottom;
            int i8 = ((width * i7) / height) / 2;
            int i9 = i7 / 2;
            rectF = new RectF((i6 - i8) + ((f5 * 8.0f) / 10.0f), (i9 - i9) + f4, (i6 + i8) - f5, (i9 + i9) - ((f4 * 8.0f) / 10.0f));
        }
        if (bitmap != null) {
            canvas.drawColor(this.imageViewBackground);
            canvas.drawBitmap(bitmap, rect, rectF, this.paint);
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
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
