package com.jingdong.manto.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* loaded from: classes16.dex */
public final class d {
    public static Bitmap a(Bitmap bitmap, float f2, float f3) {
        return a(bitmap, f2, f3, false);
    }

    public static Bitmap a(Bitmap bitmap, float f2, float f3, boolean z) {
        if (a(bitmap)) {
            return null;
        }
        try {
            Matrix matrix = new Matrix();
            matrix.setScale(f2, f3);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (z && !bitmap.isRecycled() && createBitmap != bitmap) {
                bitmap.recycle();
            }
            return createBitmap;
        } catch (Throwable unused) {
            System.gc();
            try {
                Matrix matrix2 = new Matrix();
                matrix2.setScale(f2, f3);
                Bitmap createBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
                if (z && !bitmap.isRecycled() && createBitmap2 != bitmap) {
                    bitmap.recycle();
                }
                return createBitmap2;
            } catch (Throwable unused2) {
                return bitmap;
            }
        }
    }

    private static boolean a(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }
}
