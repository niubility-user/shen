package com.jd.aips.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes12.dex */
public final class ImageUtils {
    private static Bitmap a(Context context, byte[] bArr, int i2, int i3) {
        RenderScript create = RenderScript.create(context);
        int i4 = Build.VERSION.SDK_INT;
        ScriptIntrinsicYuvToRGB create2 = i4 >= 17 ? ScriptIntrinsicYuvToRGB.create(create, Element.U8_4(create)) : null;
        Allocation createTyped = Allocation.createTyped(create, new Type.Builder(create, Element.U8(create)).setX(bArr.length).create(), 1);
        Allocation createTyped2 = Allocation.createTyped(create, new Type.Builder(create, Element.RGBA_8888(create)).setX(i2).setY(i3).create(), 1);
        createTyped.copyFrom(bArr);
        if (i4 >= 17) {
            create2.setInput(createTyped);
            create2.forEach(createTyped2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        createTyped2.copyTo(createBitmap);
        return createBitmap;
    }

    public static long getAverageLightnessOfNv21PreviewData(byte[] bArr, int i2, int i3) {
        long j2 = i2 * i3;
        long j3 = 0;
        for (int i4 = 0; i4 < j2; i4 += 10) {
            j3 += bArr[i4] & 255;
        }
        return j3 / (j2 / 10);
    }

    public static Bitmap nv21ToBitmap(Context context, byte[] bArr, int i2, int i3) {
        Bitmap bitmap = null;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                return a(context, bArr, i2, i3);
            }
            try {
                YuvImage yuvImage = new YuvImage(bArr, 17, i2, i3, null);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 80, byteArrayOutputStream);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
                try {
                    byteArrayOutputStream.close();
                    return decodeByteArray;
                } catch (IOException unused) {
                    bitmap = decodeByteArray;
                    return bitmap;
                }
            } catch (IOException unused2) {
            }
        } catch (Exception unused3) {
            return null;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postRotate(f2);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
            if (createBitmap.equals(bitmap)) {
                return createBitmap;
            }
            bitmap.recycle();
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r5 > r6) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0079 A[Catch: all -> 0x00b8, Exception -> 0x00bc, TryCatch #11 {Exception -> 0x00bc, all -> 0x00b8, blocks: (B:23:0x0063, B:25:0x0079, B:26:0x007c), top: B:98:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] yuv2JpegRotaingWithoutMirror(byte[] r15, int r16, int r17, int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.utils.ImageUtils.yuv2JpegRotaingWithoutMirror(byte[], int, int, int, int, int, int):byte[]");
    }
}
