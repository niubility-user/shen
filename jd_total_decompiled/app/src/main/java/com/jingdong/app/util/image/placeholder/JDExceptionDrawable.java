package com.jingdong.app.util.image.placeholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.R;
import com.jingdong.JdImageToolKit;

/* loaded from: classes4.dex */
public class JDExceptionDrawable extends Drawable {
    private static Bitmap bitmap;
    private static int height;
    private static int width;
    protected Paint paint;
    private final String text;

    public JDExceptionDrawable(Context context, String str) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-7829368);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextSize(12.0f);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setAntiAlias(true);
        this.text = str;
        if (bitmap == null) {
            try {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) JdImageToolKit.getContext().getResources().getDrawable(R.drawable.image_logo);
                if (bitmapDrawable == null) {
                    return;
                }
                Bitmap bitmap2 = bitmapDrawable.getBitmap();
                bitmap = bitmap2;
                if (bitmap2 == null) {
                    return;
                }
                width = bitmap2.getWidth();
                height = bitmap.getHeight();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public JDExceptionDrawable(String str) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-7829368);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextSize(12.0f);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setAntiAlias(true);
        this.text = str;
        if (bitmap == null) {
            try {
                Bitmap bitmap2 = ((BitmapDrawable) JdImageToolKit.getContext().getResources().getDrawable(R.drawable.image_logo)).getBitmap();
                bitmap = bitmap2;
                width = bitmap2.getWidth();
                height = bitmap.getHeight();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * 160.0f) + 0.5f);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        FLog.d(JDExceptionDrawable.class.getName(), "draw() -->> ");
        Rect bounds = getBounds();
        float width2 = bounds.right - (bounds.width() / 2);
        float height2 = bounds.bottom - (bounds.height() / 2);
        canvas.drawText(this.text, width2, height2, this.paint);
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null) {
            canvas.drawBitmap(bitmap2, width2 - (width / 2), (height2 - (height / 2)) + 10.0f, this.paint);
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
