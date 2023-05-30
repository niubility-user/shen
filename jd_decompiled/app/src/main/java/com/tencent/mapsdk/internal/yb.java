package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/* loaded from: classes9.dex */
public class yb {
    private static final int a = 7;
    private static Canvas b;

    /* renamed from: c  reason: collision with root package name */
    private static int f17505c;
    private static Bitmap[] d;

    /* renamed from: e  reason: collision with root package name */
    private static Bitmap f17506e;

    static {
        Bitmap[] bitmapArr = new Bitmap[7];
        d = bitmapArr;
        bitmapArr[0] = Bitmap.createBitmap(64, 32, Bitmap.Config.ARGB_8888);
        d[1] = Bitmap.createBitmap(128, 32, Bitmap.Config.ARGB_8888);
        d[2] = Bitmap.createBitmap(128, 64, Bitmap.Config.ARGB_8888);
        d[3] = Bitmap.createBitmap(256, 32, Bitmap.Config.ARGB_8888);
        d[4] = Bitmap.createBitmap(256, 128, Bitmap.Config.ARGB_8888);
        d[5] = Bitmap.createBitmap(32, 128, Bitmap.Config.ARGB_8888);
        d[6] = Bitmap.createBitmap(32, 256, Bitmap.Config.ARGB_8888);
        b = new Canvas(d[1]);
        f17505c = 1;
        f17506e = null;
    }

    public static Bitmap a() {
        int i2 = f17505c;
        return i2 < 7 ? d[i2] : f17506e;
    }

    public static Canvas a(float f2, float f3) {
        Bitmap bitmap;
        int i2 = 0;
        while (i2 < 7 && (d[i2].getWidth() < f2 || d[i2].getHeight() < f3)) {
            i2++;
        }
        if (i2 < 7) {
            f17505c = i2;
            b.setBitmap(d[i2]);
            bitmap = d[i2];
        } else {
            f17505c = d.length;
            int i3 = 1;
            int i4 = 1;
            while (i4 < f2) {
                i4 <<= 1;
            }
            while (i3 < f3) {
                i3 <<= 1;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i4, i3, Bitmap.Config.ARGB_8888);
            f17506e = createBitmap;
            b.setBitmap(createBitmap);
            bitmap = f17506e;
        }
        bitmap.eraseColor(0);
        return b;
    }

    public static void a(float f2, float f3, Point point2) {
        for (int i2 = 0; i2 < 7; i2++) {
            if (d[i2].getWidth() >= f2 && d[i2].getHeight() >= f3) {
                point2.set(d[i2].getWidth(), d[i2].getHeight());
                return;
            }
        }
        int i3 = 1;
        int i4 = 1;
        while (i4 < f2) {
            i4 <<= 1;
        }
        while (i3 < f3) {
            i3 <<= 1;
        }
        point2.set(i4, i3);
    }

    public static void b() {
        Bitmap bitmap = f17506e;
        if (bitmap != null) {
            bitmap.recycle();
            f17506e = null;
        }
    }
}
