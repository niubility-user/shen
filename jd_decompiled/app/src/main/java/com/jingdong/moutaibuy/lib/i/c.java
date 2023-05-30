package com.jingdong.moutaibuy.lib.i;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

/* loaded from: classes16.dex */
public class c {
    private static boolean a;

    public static float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public static Rect b(float f2, float f3, float f4, int i2, int i3, int i4, int i5) {
        int i6 = (int) ((i2 * f2) / 2.0f);
        int i7 = (int) ((i3 * f2) / 2.0f);
        int i8 = (int) (((f3 / i4) * 2000.0f) - 1000.0f);
        int i9 = (int) (((f4 / i5) * 2000.0f) - 1000.0f);
        RectF rectF = new RectF(c(i8 - i6, -1000, 1000), c(i9 - i7, -1000, 1000), c(i8 + i6, -1000, 1000), c(i9 + i7, -1000, 1000));
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    static int c(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    public static void d(String str) {
        e("QRCode", str);
    }

    public static void e(String str, String str2) {
        boolean z = a;
    }

    public static int f(Context context, float f2) {
        return (int) TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    public static void g(String str) {
        boolean z = a;
    }

    public static Bitmap h(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i3 = options.outHeight / 400;
            if (i3 > 0) {
                i2 = i3;
            }
            options.inSampleSize = i2;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Point i(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point2 = new Point();
        defaultDisplay.getSize(point2);
        return point2;
    }

    public static boolean j() {
        return a;
    }

    public static boolean k(Context context) {
        Point i2 = i(context);
        return i2.y > i2.x;
    }

    public static void l(String str, Rect rect) {
        e("QRCodeFocusArea", str + " centerX\uff1a" + rect.centerX() + " centerY\uff1a" + rect.centerY() + " width\uff1a" + rect.width() + " height\uff1a" + rect.height() + " rectHalfWidth\uff1a" + (rect.width() / 2) + " rectHalfHeight\uff1a" + (rect.height() / 2) + " left\uff1a" + rect.left + " top\uff1a" + rect.top + " right\uff1a" + rect.right + " bottom\uff1a" + rect.bottom);
    }
}
