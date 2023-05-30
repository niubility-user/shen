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
    */
    public static byte[] yuv2JpegRotaingWithoutMirror(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        Throwable th;
        Bitmap bitmap;
        ByteArrayOutputStream byteArrayOutputStream;
        Bitmap bitmap2;
        byte[] bArr2;
        byte[] bArr3;
        Bitmap bitmap3;
        float f2;
        int i8;
        float f3;
        float f4;
        Bitmap decodeByteArray;
        Bitmap bitmap4 = null;
        try {
            YuvImage yuvImage = new YuvImage(bArr, 17, i2, i3, null);
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
                    bArr2 = byteArrayOutputStream.toByteArray();
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        int i9 = 1;
                        if (i2 > i3) {
                            f3 = i2;
                            f4 = i5;
                            if (f3 > f4) {
                                i8 = (int) (f3 / f4);
                                f2 = f4 / f3;
                                if (i8 > 0) {
                                    i9 = i8;
                                }
                                options.inSampleSize = i9;
                                decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
                                try {
                                    Matrix matrix = new Matrix();
                                    matrix.postScale(-1.0f, 1.0f);
                                    matrix.postRotate(i7);
                                    if (f2 > 0.0f) {
                                        matrix.postScale(f2, f2);
                                    }
                                    bitmap3 = Bitmap.createBitmap(decodeByteArray, 0, 0, i2, i3, matrix, true);
                                } catch (Exception unused) {
                                    bitmap3 = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    bitmap3 = null;
                                }
                                try {
                                    Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
                                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                    bitmap3.compress(compressFormat, i4, byteArrayOutputStream2);
                                    bArr3 = byteArrayOutputStream2.toByteArray();
                                    if (!bitmap3.isRecycled()) {
                                        bitmap3.recycle();
                                    }
                                    if (decodeByteArray != null && !decodeByteArray.isRecycled()) {
                                        decodeByteArray.recycle();
                                    }
                                    try {
                                        byteArrayOutputStream.close();
                                        return bArr3;
                                    } catch (IOException e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        return bArr3;
                                    }
                                } catch (Exception unused2) {
                                    bitmap4 = decodeByteArray;
                                    bitmap2 = bitmap4;
                                    bitmap4 = bitmap3;
                                    if (bitmap4 != null && !bitmap4.isRecycled()) {
                                        bitmap4.recycle();
                                    }
                                    if (bitmap2 != null && !bitmap2.isRecycled()) {
                                        bitmap2.recycle();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (IOException e3) {
                                            e = e3;
                                            bArr3 = bArr2;
                                            e.printStackTrace();
                                            return bArr3;
                                        }
                                    }
                                    return bArr2;
                                } catch (Throwable th3) {
                                    th = th3;
                                    bitmap4 = decodeByteArray;
                                    Bitmap bitmap5 = bitmap3;
                                    th = th;
                                    bitmap = bitmap4;
                                    bitmap4 = bitmap5;
                                    if (bitmap4 != null && !bitmap4.isRecycled()) {
                                        bitmap4.recycle();
                                    }
                                    if (bitmap != null && !bitmap.isRecycled()) {
                                        bitmap.recycle();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (i2 < i3) {
                            f3 = i3;
                            f4 = i6;
                        }
                        if (i2 == i3) {
                            if (i5 > i6) {
                                f3 = i2;
                                f4 = i5;
                            } else {
                                f3 = i3;
                                f4 = i6;
                            }
                            i8 = (int) (f3 / f4);
                            f2 = f4 / f3;
                            if (i8 > 0) {
                            }
                            options.inSampleSize = i9;
                            decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
                            Matrix matrix2 = new Matrix();
                            matrix2.postScale(-1.0f, 1.0f);
                            matrix2.postRotate(i7);
                            if (f2 > 0.0f) {
                            }
                            bitmap3 = Bitmap.createBitmap(decodeByteArray, 0, 0, i2, i3, matrix2, true);
                            Bitmap.CompressFormat compressFormat2 = Bitmap.CompressFormat.JPEG;
                            ByteArrayOutputStream byteArrayOutputStream22 = new ByteArrayOutputStream();
                            bitmap3.compress(compressFormat2, i4, byteArrayOutputStream22);
                            bArr3 = byteArrayOutputStream22.toByteArray();
                            if (!bitmap3.isRecycled()) {
                            }
                            if (decodeByteArray != null) {
                                decodeByteArray.recycle();
                            }
                            byteArrayOutputStream.close();
                            return bArr3;
                        }
                        f2 = 0.0f;
                        i8 = 1;
                        if (i8 > 0) {
                        }
                        options.inSampleSize = i9;
                        decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
                        Matrix matrix22 = new Matrix();
                        matrix22.postScale(-1.0f, 1.0f);
                        matrix22.postRotate(i7);
                        if (f2 > 0.0f) {
                        }
                        bitmap3 = Bitmap.createBitmap(decodeByteArray, 0, 0, i2, i3, matrix22, true);
                        Bitmap.CompressFormat compressFormat22 = Bitmap.CompressFormat.JPEG;
                        ByteArrayOutputStream byteArrayOutputStream222 = new ByteArrayOutputStream();
                        bitmap3.compress(compressFormat22, i4, byteArrayOutputStream222);
                        bArr3 = byteArrayOutputStream222.toByteArray();
                        if (!bitmap3.isRecycled()) {
                        }
                        if (decodeByteArray != null) {
                        }
                        byteArrayOutputStream.close();
                        return bArr3;
                    } catch (Exception unused3) {
                        bitmap3 = null;
                    }
                } catch (Exception unused4) {
                    bitmap3 = null;
                    bArr2 = null;
                }
            } catch (Throwable th4) {
                th = th4;
                bitmap3 = null;
            }
        } catch (Exception unused5) {
            bitmap2 = null;
            bArr2 = null;
            byteArrayOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            bitmap = null;
            byteArrayOutputStream = null;
        }
    }
}
