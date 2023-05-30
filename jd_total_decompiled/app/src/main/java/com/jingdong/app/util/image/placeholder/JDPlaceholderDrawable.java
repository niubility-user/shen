package com.jingdong.app.util.image.placeholder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.R;
import com.facebook.drawee.generic.RoundingParams;
import com.jingdong.JdImageToolKit;

/* loaded from: classes4.dex */
public class JDPlaceholderDrawable extends Drawable {
    private static Bitmap default_bitmap;
    private static Bitmap gray_banner_bitmap;
    private static Bitmap gray_big_bitmap;
    private static Bitmap gray_middle_bitmap;
    private static Bitmap gray_middle_raw_bitmap;
    private static Bitmap gray_small_bitmap;
    private static Bitmap white_banner_bitmap;
    private static Bitmap white_big_bitmap;
    private static Bitmap white_middle_bitmap;
    private static Bitmap white_middle_raw_bitmap;
    private static Bitmap white_small_bitmap;
    private float[] mCornersRadii;
    private boolean mIsCircle;
    private int mTypeNumber;
    protected Paint paint;

    public JDPlaceholderDrawable(int i2) {
        this.mTypeNumber = 0;
        this.mCornersRadii = null;
        this.mIsCircle = false;
        this.paint = new Paint();
        this.mTypeNumber = i2;
        if (i2 > 0) {
            comparator();
        } else if (default_bitmap == null) {
            default_bitmap = getJDBitmap();
        }
    }

    public JDPlaceholderDrawable(int i2, RoundingParams roundingParams) {
        this.mTypeNumber = 0;
        this.mCornersRadii = null;
        this.mIsCircle = false;
        this.paint = new Paint();
        this.mTypeNumber = i2;
        if (roundingParams != null) {
            this.mIsCircle = roundingParams.getRoundAsCircle();
            if (roundingParams.getCornersRadii() != null) {
                System.arraycopy(roundingParams.getCornersRadii(), 0, getOrCreateRoundedCornersRadii(), 0, 8);
            }
        }
        if (i2 > 0) {
            comparator();
        } else if (default_bitmap == null) {
            default_bitmap = getJDBitmap();
        }
    }

    private void comparator() {
        int i2 = this.mTypeNumber;
        switch (i2) {
            case 1:
            case 5:
                if (white_big_bitmap == null) {
                    white_big_bitmap = getJDBitmap();
                    return;
                }
                return;
            case 2:
                if (white_middle_bitmap == null) {
                    white_middle_bitmap = getJDBitmap();
                    return;
                }
                return;
            case 3:
            case 8:
                if (white_small_bitmap == null) {
                    white_small_bitmap = getJDBitmap();
                    return;
                }
                return;
            case 4:
            case 6:
                if (white_banner_bitmap == null) {
                    white_banner_bitmap = getJDBitmap();
                    return;
                }
                return;
            case 7:
                if (white_middle_raw_bitmap == null) {
                    white_middle_raw_bitmap = getJDBitmap();
                    return;
                }
                return;
            default:
                switch (i2) {
                    case 16:
                    case 20:
                        if (gray_big_bitmap == null) {
                            gray_big_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                    case 17:
                        if (gray_middle_bitmap == null) {
                            gray_middle_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                    case 18:
                    case 23:
                        if (gray_small_bitmap == null) {
                            gray_small_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                    case 19:
                    case 21:
                        if (gray_banner_bitmap == null) {
                            gray_banner_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                    case 22:
                        if (gray_middle_raw_bitmap == null) {
                            gray_middle_raw_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                    default:
                        if (default_bitmap == null) {
                            default_bitmap = getJDBitmap();
                            return;
                        }
                        return;
                }
        }
    }

    private void comparatorDrawBitmap(Canvas canvas) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        int i2 = this.mTypeNumber;
        switch (i2) {
            case 1:
                bitmap = white_big_bitmap;
                drawJDDrawable(canvas, bitmap);
                return;
            case 2:
                bitmap = white_middle_bitmap;
                drawJDDrawable(canvas, bitmap);
                return;
            case 3:
                bitmap2 = white_small_bitmap;
                drawJDDrawableRaw(canvas, bitmap2);
                return;
            case 4:
                bitmap3 = white_banner_bitmap;
                drawJDDrawableBanner(canvas, bitmap3);
                return;
            case 5:
                bitmap2 = white_big_bitmap;
                drawJDDrawableRaw(canvas, bitmap2);
                return;
            case 6:
                bitmap2 = white_banner_bitmap;
                drawJDDrawableRaw(canvas, bitmap2);
                return;
            case 7:
                bitmap2 = white_middle_raw_bitmap;
                drawJDDrawableRaw(canvas, bitmap2);
                return;
            case 8:
                bitmap4 = white_small_bitmap;
                drawJDDrawableMini(canvas, bitmap4);
                return;
            default:
                switch (i2) {
                    case 16:
                        bitmap = gray_big_bitmap;
                        drawJDDrawable(canvas, bitmap);
                        return;
                    case 17:
                        bitmap = gray_middle_bitmap;
                        drawJDDrawable(canvas, bitmap);
                        return;
                    case 18:
                        bitmap2 = gray_small_bitmap;
                        drawJDDrawableRaw(canvas, bitmap2);
                        return;
                    case 19:
                        bitmap3 = gray_banner_bitmap;
                        drawJDDrawableBanner(canvas, bitmap3);
                        return;
                    case 20:
                        bitmap2 = gray_big_bitmap;
                        drawJDDrawableRaw(canvas, bitmap2);
                        return;
                    case 21:
                        bitmap2 = gray_banner_bitmap;
                        drawJDDrawableRaw(canvas, bitmap2);
                        return;
                    case 22:
                        bitmap2 = gray_middle_raw_bitmap;
                        drawJDDrawableRaw(canvas, bitmap2);
                        return;
                    case 23:
                        bitmap4 = gray_small_bitmap;
                        drawJDDrawableMini(canvas, bitmap4);
                        return;
                    default:
                        bitmap = default_bitmap;
                        drawJDDrawable(canvas, bitmap);
                        return;
                }
        }
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * 160.0f) + 0.5f);
    }

    private void drawBackground(Canvas canvas, Rect rect) {
        Paint paint;
        int i2;
        Resources resources = JdImageToolKit.getContext().getResources();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        RectF rectF = new RectF(rect.left, rect.top, rect.right, rect.bottom);
        Path path = new Path();
        if (this.mTypeNumber < 9) {
            paint = this.paint;
            i2 = R.color.placeholder_white_bg;
        } else {
            paint = this.paint;
            i2 = R.color.placeholder_gray_bg;
        }
        paint.setColor(resources.getColor(i2));
        if (this.mIsCircle) {
            path.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
        } else {
            float[] fArr = this.mCornersRadii;
            if (fArr == null) {
                canvas.drawRect(rectF, this.paint);
                return;
            }
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
        }
        canvas.drawPath(path, this.paint);
    }

    private void drawJDDrawable(Canvas canvas, Bitmap bitmap) {
        RectF rectF;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect bounds = getBounds();
        Rect rect = new Rect(0, 0, width, height);
        if (bounds.width() < bounds.height()) {
            float f2 = bounds.right / 6;
            float f3 = (width * f2) / height;
            int i2 = bounds.right;
            int i3 = i2 / 2;
            int i4 = ((i2 * width) / height) / 2;
            int i5 = bounds.bottom / 2;
            rectF = new RectF((i3 - i4) + ((f3 * 8.0f) / 10.0f), (i5 - i3) + f2, (i4 + i3) - f3, (i5 + i3) - ((f2 * 8.0f) / 10.0f));
        } else {
            float f4 = bounds.bottom / 6;
            float f5 = (width * f4) / height;
            int i6 = bounds.right / 2;
            int i7 = bounds.bottom;
            int i8 = ((width * i7) / height) / 2;
            int i9 = i7 / 2;
            rectF = new RectF((i6 - i8) + ((f5 * 8.0f) / 10.0f), (i9 - i9) + f4, (i6 + i8) - f5, (i9 + i9) - ((f4 * 8.0f) / 10.0f));
        }
        drawBackground(canvas, bounds);
        canvas.drawBitmap(bitmap, rect, rectF, this.paint);
    }

    private void drawJDDrawableBanner(Canvas canvas, Bitmap bitmap) {
        RectF rectF;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect bounds = getBounds();
        Rect rect = new Rect(0, 0, width, height);
        if (bounds.width() < (bounds.height() * width) / height) {
            float f2 = bounds.right / 30;
            float f3 = (width * f2) / height;
            int i2 = bounds.right;
            int i3 = i2 / 2;
            int i4 = bounds.bottom / 2;
            int i5 = ((i2 * height) / width) / 2;
            rectF = new RectF((i3 - i3) + ((f3 * 8.0f) / 10.0f), (i4 - i5) + f2, (i3 + i3) - f3, (i4 + i5) - ((f2 * 8.0f) / 10.0f));
        } else {
            float f4 = bounds.bottom / 30;
            float f5 = (width * f4) / height;
            int i6 = bounds.right / 2;
            int i7 = bounds.bottom;
            int i8 = ((width * i7) / height) / 2;
            int i9 = i7 / 2;
            rectF = new RectF((i6 - i8) + ((f5 * 8.0f) / 10.0f), (i9 - i9) + f4, (i6 + i8) - f5, (i9 + i9) - ((f4 * 8.0f) / 10.0f));
        }
        drawBackground(canvas, bounds);
        canvas.drawBitmap(bitmap, rect, rectF, this.paint);
    }

    private void drawJDDrawableMini(Canvas canvas, Bitmap bitmap) {
        RectF rectF;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect bounds = getBounds();
        Rect rect = new Rect(0, 0, width, height);
        if (bounds.width() < bounds.height()) {
            float f2 = bounds.right / 4;
            float f3 = (width * f2) / height;
            int i2 = bounds.right;
            int i3 = i2 / 2;
            int i4 = ((i2 * width) / height) / 2;
            int i5 = bounds.bottom / 2;
            rectF = new RectF((i3 - i4) + f3, (i5 - i3) + f2, (i4 + i3) - f3, (i5 + i3) - f2);
        } else {
            float f4 = bounds.bottom / 4;
            float f5 = (width * f4) / height;
            int i6 = bounds.right / 2;
            int i7 = bounds.bottom;
            int i8 = ((width * i7) / height) / 2;
            int i9 = i7 / 2;
            rectF = new RectF((i6 - i8) + f5, (i9 - i9) + f4, (i6 + i8) - f5, (i9 + i9) - f4);
        }
        drawBackground(canvas, bounds);
        canvas.drawBitmap(bitmap, rect, rectF, this.paint);
    }

    private void drawJDDrawableRaw(Canvas canvas, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        drawBackground(canvas, getBounds());
        canvas.drawBitmap(bitmap, (r2.right - (r2.width() / 2)) - (width / 2), (r2.bottom - (r2.height() / 2)) - (height / 2), this.paint);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Bitmap getJDBitmap() {
        int i2;
        try {
            Resources resources = JdImageToolKit.getContext().getResources();
            int i3 = this.mTypeNumber;
            switch (i3) {
                case 1:
                case 5:
                    i2 = R.drawable.pl_white_big;
                    break;
                case 2:
                    i2 = R.drawable.pl_white_mid;
                    break;
                case 3:
                case 8:
                    i2 = R.drawable.pl_white_min;
                    break;
                case 4:
                case 6:
                    i2 = R.drawable.pl_white_banner;
                    break;
                case 7:
                    i2 = R.drawable.pl_white_mid_raw;
                    break;
                default:
                    switch (i3) {
                        case 16:
                        case 20:
                            i2 = R.drawable.pl_gray_big;
                            break;
                        case 17:
                            i2 = R.drawable.pl_gray_mid;
                            break;
                        case 18:
                        case 23:
                            i2 = R.drawable.pl_gray_min;
                            break;
                        case 19:
                        case 21:
                            i2 = R.drawable.pl_gray_banner;
                            break;
                        case 22:
                            i2 = R.drawable.pl_gray_mid_raw;
                            break;
                        default:
                            i2 = R.drawable.pl_gray_mid;
                            break;
                    }
            }
            Drawable drawable = resources.getDrawable(i2);
            if (drawable == null) {
                return null;
            }
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap == null) {
                return null;
            }
            return bitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        comparatorDrawBitmap(canvas);
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
