package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
class JDPlaceholderDrawable extends Drawable {
    private static final String TAG = JDPlaceholderDrawable.class.getSimpleName();
    private static Map<String, Bitmap> sBitmapHolder = new ConcurrentHashMap();
    private Context mContext;
    private Bitmap mCustomBitmap;
    private int mResId;
    private String mType;
    private Paint paint = new Paint();

    /* loaded from: classes12.dex */
    interface PlaceholderType {
        public static final String GRAY_BANNER = "gray_banner";
        public static final String GRAY_BIG = "gray_big";
        public static final String GRAY_LOGO = "gray_logo";
        public static final String GRAY_MID = "gray_middle";
        public static final String GRAY_SMALL = "gray_small";
        public static final String WHITE_BANNER = "white_banner";
        public static final String WHITE_BIG = "white_big";
        public static final String WHITE_LOGO = "white_logo";
        public static final String WHITE_MID = "white_middle";
        public static final String WHITE_SMALL = "white_small";
    }

    JDPlaceholderDrawable(String str, int i2, Context context) {
        this.mType = filterType(str);
        this.mResId = i2;
        this.mContext = context;
        prepareBitmap();
    }

    private void drawBackground(Canvas canvas, Rect rect) {
        String str = this.mType;
        if (str == null) {
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1654244374:
                if (str.equals(PlaceholderType.WHITE_BIG)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1146462206:
                if (str.equals(PlaceholderType.WHITE_BANNER)) {
                    c2 = 1;
                    break;
                }
                break;
            case -923600377:
                if (str.equals(PlaceholderType.GRAY_LOGO)) {
                    c2 = 2;
                    break;
                }
                break;
            case -824460693:
                if (str.equals(PlaceholderType.WHITE_MID)) {
                    c2 = 3;
                    break;
                }
                break;
            case -575127183:
                if (str.equals(PlaceholderType.WHITE_SMALL)) {
                    c2 = 4;
                    break;
                }
                break;
            case 108743972:
                if (str.equals(PlaceholderType.GRAY_BIG)) {
                    c2 = 5;
                    break;
                }
                break;
            case 258335745:
                if (str.equals(PlaceholderType.WHITE_LOGO)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1179257992:
                if (str.equals(PlaceholderType.GRAY_BANNER)) {
                    c2 = 7;
                    break;
                }
                break;
            case 1439558699:
                if (str.equals(PlaceholderType.GRAY_SMALL)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1501259505:
                if (str.equals(PlaceholderType.GRAY_MID)) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 6:
                canvas.drawColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                return;
            case 2:
            case 5:
            case 7:
            case '\b':
            case '\t':
                canvas.drawColor(Color.parseColor("#f8f8f8"));
                return;
            default:
                return;
        }
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
            int i3 = bounds.bottom;
            rectF = new RectF(((i2 / 2) - (((i2 * width) / height) / 2)) + ((f3 * 8.0f) / 10.0f), ((i3 / 2) - (i2 / 2)) + f2, ((i2 / 2) + (((width * i2) / height) / 2)) - f3, ((i3 / 2) + (i2 / 2)) - ((f2 * 8.0f) / 10.0f));
        } else {
            float f4 = bounds.bottom / 6;
            float f5 = (width * f4) / height;
            int i4 = bounds.right;
            int i5 = bounds.bottom;
            rectF = new RectF(((i4 / 2) - (((i5 * width) / height) / 2)) + ((f5 * 8.0f) / 10.0f), f4, ((i4 / 2) + (((width * i5) / height) / 2)) - f5, ((i5 / 2) + (i5 / 2)) - ((8.0f * f4) / 10.0f));
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
            int i2 = bounds.bottom;
            int i3 = bounds.right;
            rectF = new RectF((f3 * 8.0f) / 10.0f, ((i2 / 2) - (((i3 * height) / width) / 2)) + f2, ((i3 / 2) + (i3 / 2)) - f3, ((i2 / 2) + (((i3 * height) / width) / 2)) - ((f2 * 8.0f) / 10.0f));
        } else {
            float f4 = bounds.bottom / 30;
            float f5 = (width * f4) / height;
            int i4 = bounds.right;
            int i5 = bounds.bottom;
            rectF = new RectF(((i4 / 2) - (((i5 * width) / height) / 2)) + ((f5 * 8.0f) / 10.0f), f4, ((i4 / 2) + (((width * i5) / height) / 2)) - f5, ((i5 / 2) + (i5 / 2)) - ((8.0f * f4) / 10.0f));
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
            int i3 = bounds.bottom;
            rectF = new RectF(((i2 / 2) - (((i2 * width) / height) / 2)) + f3, ((i3 / 2) - (i2 / 2)) + f2, ((i2 / 2) + (((width * i2) / height) / 2)) - f3, ((i3 / 2) + (i2 / 2)) - f2);
        } else {
            float f4 = bounds.bottom / 4;
            float f5 = (width * f4) / height;
            int i4 = bounds.right;
            int i5 = bounds.bottom;
            rectF = new RectF(((i4 / 2) - (((i5 * width) / height) / 2)) + f5, f4, ((i4 / 2) + (((width * i5) / height) / 2)) - f5, ((i5 / 2) + (i5 / 2)) - f4);
        }
        drawBackground(canvas, bounds);
        canvas.drawBitmap(bitmap, rect, rectF, this.paint);
    }

    private void drawJDDrawableOrigin(Canvas canvas, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect bounds = getBounds();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
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

    private String filterType(String str) {
        if (str == null) {
            return null;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1654244374:
                if (str.equals(PlaceholderType.WHITE_BIG)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1146462206:
                if (str.equals(PlaceholderType.WHITE_BANNER)) {
                    c2 = 1;
                    break;
                }
                break;
            case -923600377:
                if (str.equals(PlaceholderType.GRAY_LOGO)) {
                    c2 = 2;
                    break;
                }
                break;
            case -824460693:
                if (str.equals(PlaceholderType.WHITE_MID)) {
                    c2 = 3;
                    break;
                }
                break;
            case -575127183:
                if (str.equals(PlaceholderType.WHITE_SMALL)) {
                    c2 = 4;
                    break;
                }
                break;
            case 108743972:
                if (str.equals(PlaceholderType.GRAY_BIG)) {
                    c2 = 5;
                    break;
                }
                break;
            case 258335745:
                if (str.equals(PlaceholderType.WHITE_LOGO)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1179257992:
                if (str.equals(PlaceholderType.GRAY_BANNER)) {
                    c2 = 7;
                    break;
                }
                break;
            case 1439558699:
                if (str.equals(PlaceholderType.GRAY_SMALL)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1501259505:
                if (str.equals(PlaceholderType.GRAY_MID)) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
                return str;
            default:
                return null;
        }
    }

    private Bitmap getBitmapFromResId(int i2) {
        BitmapDrawable bitmapDrawable;
        if (i2 <= 0) {
            return null;
        }
        try {
            bitmapDrawable = (BitmapDrawable) this.mContext.getResources().getDrawable(i2);
        } catch (Exception e2) {
            FLog.e(TAG, e2.toString());
            bitmapDrawable = null;
        }
        if (bitmapDrawable == null) {
            return null;
        }
        return bitmapDrawable.getBitmap();
    }

    private void prepareBitmap() {
        if (this.mResId <= 0) {
            return;
        }
        if (TextUtils.isEmpty(this.mType)) {
            this.mCustomBitmap = getBitmapFromResId(this.mResId);
            return;
        }
        if (sBitmapHolder == null) {
            sBitmapHolder = new ConcurrentHashMap();
        }
        if (sBitmapHolder.get(this.mType) == null) {
            sBitmapHolder.put(this.mType, getBitmapFromResId(this.mResId));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (TextUtils.isEmpty(this.mType)) {
            drawJDDrawableOrigin(canvas, this.mCustomBitmap);
            return;
        }
        Map<String, Bitmap> map = sBitmapHolder;
        if (map == null) {
            return;
        }
        Bitmap bitmap = map.get(this.mType);
        String str = this.mType;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1654244374:
                if (str.equals(PlaceholderType.WHITE_BIG)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1146462206:
                if (str.equals(PlaceholderType.WHITE_BANNER)) {
                    c2 = 1;
                    break;
                }
                break;
            case -923600377:
                if (str.equals(PlaceholderType.GRAY_LOGO)) {
                    c2 = 2;
                    break;
                }
                break;
            case -824460693:
                if (str.equals(PlaceholderType.WHITE_MID)) {
                    c2 = 3;
                    break;
                }
                break;
            case -575127183:
                if (str.equals(PlaceholderType.WHITE_SMALL)) {
                    c2 = 4;
                    break;
                }
                break;
            case 108743972:
                if (str.equals(PlaceholderType.GRAY_BIG)) {
                    c2 = 5;
                    break;
                }
                break;
            case 258335745:
                if (str.equals(PlaceholderType.WHITE_LOGO)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1179257992:
                if (str.equals(PlaceholderType.GRAY_BANNER)) {
                    c2 = 7;
                    break;
                }
                break;
            case 1439558699:
                if (str.equals(PlaceholderType.GRAY_SMALL)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1501259505:
                if (str.equals(PlaceholderType.GRAY_MID)) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
            case 5:
            case '\t':
                drawJDDrawable(canvas, bitmap);
                return;
            case 1:
            case 2:
            case 6:
            case 7:
                drawJDDrawableBanner(canvas, bitmap);
                return;
            case 4:
            case '\b':
                drawJDDrawable(canvas, bitmap);
                return;
            default:
                return;
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
