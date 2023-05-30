package com.jd.aips.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes12.dex */
public class JDCNImageUtils {
    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void bitmap2File(Bitmap bitmap, String str, int i2) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|2|3|(2:5|(8:7|8|(1:10)(1:33)|11|12|13|(1:15)|18))|(1:35)|(2:(1:39)(1:41)|40)(1:42)|(0)(0)|11|12|13|(0)|18|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r3 > r4) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
        if (r9.isRecycled() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
        r10 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
        r0 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
        if (r0 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        r0.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
        throw r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
        if (r9 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
        if (r9.isRecycled() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        r9.recycle();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] jpegRotaing(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        Bitmap bitmap;
        BitmapFactory.Options options;
        int i8;
        int i9;
        float f2;
        float f3;
        float f4;
        float f5;
        Bitmap bitmap2 = null;
        r0 = null;
        r0 = null;
        byte[] bArr2 = null;
        try {
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            i8 = 1;
        } catch (Exception unused) {
            bitmap = null;
        } catch (Throwable th) {
            th = th;
        }
        if (i2 > i3) {
            f4 = i2;
            f5 = i5;
            if (f4 > f5) {
                i9 = (int) (f4 / f5);
                if (i9 <= 0) {
                    i8 = i9;
                }
                options.inSampleSize = i8;
                bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                Matrix matrix = new Matrix();
                matrix.postRotate(i7);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bArr2 = bitmap2Bytes(bitmap, Bitmap.CompressFormat.JPEG, i4);
                if (bitmap != null) {
                }
                return bArr2;
            }
        }
        if (i2 < i3) {
            f4 = i3;
            f5 = i6;
        }
        if (i2 == i3) {
            if (i5 > i6) {
                f2 = i2;
                f3 = i5;
            } else {
                f2 = i3;
                f3 = i6;
            }
            i9 = (int) (f2 / f3);
        } else {
            i9 = 1;
        }
        if (i9 <= 0) {
        }
        options.inSampleSize = i8;
        bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Matrix matrix2 = new Matrix();
        matrix2.postRotate(i7);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
        bArr2 = bitmap2Bytes(bitmap, Bitmap.CompressFormat.JPEG, i4);
        if (bitmap != null) {
        }
        return bArr2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(3:4|5|(2:6|7))|(2:9|(10:11|12|(1:14)(1:50)|15|17|18|(1:22)|23|24|25))|(1:52)|(2:(1:56)(1:58)|57)(1:59)|(0)(0)|15|17|18|(2:20|22)|23|24|25) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r6 > r11) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0099, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009a, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009c, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a1, code lost:
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ac, code lost:
        if (r10 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b4, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b7, code lost:
        if (r0 != null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b9, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bd, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00be, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c2, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c5, code lost:
        if (r10 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cd, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d0, code lost:
        if (r1 != null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d2, code lost:
        r1.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00d6 -> B:64:0x00d9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] yuv2JpegRotaing(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr2;
        YuvImage yuvImage;
        BitmapFactory.Options options;
        int i8;
        int i9;
        float f2;
        float f3;
        float f4;
        float f5;
        Bitmap decodeByteArray;
        Bitmap bitmap = null;
        try {
            try {
                yuvImage = new YuvImage(bArr, 17, i2, i3, null);
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception unused) {
                byteArrayOutputStream = null;
                bArr2 = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                ByteArrayOutputStream byteArrayOutputStream2 = null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
                bArr2 = byteArrayOutputStream.toByteArray();
                try {
                    options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    i8 = 1;
                } catch (Exception unused2) {
                }
            } catch (Exception unused3) {
                bArr2 = null;
            }
            if (i2 > i3) {
                f4 = i2;
                f5 = i5;
                if (f4 > f5) {
                    i9 = (int) (f4 / f5);
                    if (i9 <= 0) {
                        i8 = i9;
                    }
                    options.inSampleSize = i8;
                    decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(i7);
                    decodeByteArray = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
                    bArr2 = bitmap2Bytes(decodeByteArray, Bitmap.CompressFormat.JPEG, i4);
                    if (decodeByteArray != null && !decodeByteArray.isRecycled()) {
                        decodeByteArray.recycle();
                    }
                    byteArrayOutputStream.close();
                    return bArr2;
                }
            }
            if (i2 < i3) {
                f4 = i3;
                f5 = i6;
            }
            if (i2 == i3) {
                if (i5 > i6) {
                    f2 = i2;
                    f3 = i5;
                } else {
                    f2 = i3;
                    f3 = i6;
                }
                i9 = (int) (f2 / f3);
            } else {
                i9 = 1;
            }
            if (i9 <= 0) {
            }
            options.inSampleSize = i8;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
            Matrix matrix2 = new Matrix();
            matrix2.postRotate(i7);
            decodeByteArray = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix2, true);
            bArr2 = bitmap2Bytes(decodeByteArray, Bitmap.CompressFormat.JPEG, i4);
            if (decodeByteArray != null) {
                decodeByteArray.recycle();
            }
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(3:2|3|(4:4|5|7|8))|(2:10|(10:12|13|(1:15)(1:51)|16|18|19|(1:23)|24|25|26))|(1:53)|(2:(1:57)(1:59)|58)(1:60)|(0)(0)|16|18|19|(2:21|23)|24|25|26|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r6 > r11) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a0, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a1, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a3, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a8, code lost:
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b3, code lost:
        if (r10 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bb, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00be, code lost:
        if (r0 != null) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c0, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c5, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c9, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00cc, code lost:
        if (r10 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d4, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d7, code lost:
        if (r1 != null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d9, code lost:
        r1.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00dd -> B:68:0x00e0). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] yuv2JpegRotaingWithoutMirror(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr2;
        BitmapFactory.Options options;
        int i8;
        int i9;
        float f2;
        float f3;
        float f4;
        float f5;
        Bitmap decodeByteArray;
        Bitmap bitmap = null;
        try {
            try {
                YuvImage yuvImage = new YuvImage(bArr, 17, i2, i3, null);
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    try {
                        yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
                        bArr2 = byteArrayOutputStream.toByteArray();
                        try {
                            options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.RGB_565;
                            i8 = 1;
                        } catch (Exception unused) {
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception unused2) {
                    bArr2 = null;
                }
            } catch (Exception unused3) {
                byteArrayOutputStream = null;
                bArr2 = null;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                ByteArrayOutputStream byteArrayOutputStream2 = null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (i2 > i3) {
            f4 = i2;
            f5 = i5;
            if (f4 > f5) {
                i9 = (int) (f4 / f5);
                if (i9 <= 0) {
                    i8 = i9;
                }
                options.inSampleSize = i8;
                decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
                Matrix matrix = new Matrix();
                matrix.postScale(-1.0f, 1.0f);
                matrix.postRotate(i7);
                decodeByteArray = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
                bArr2 = bitmap2Bytes(decodeByteArray, Bitmap.CompressFormat.JPEG, i4);
                if (decodeByteArray != null && !decodeByteArray.isRecycled()) {
                    decodeByteArray.recycle();
                }
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
        if (i2 < i3) {
            f4 = i3;
            f5 = i6;
        }
        if (i2 == i3) {
            if (i5 > i6) {
                f2 = i2;
                f3 = i5;
            } else {
                f2 = i3;
                f3 = i6;
            }
            i9 = (int) (f2 / f3);
        } else {
            i9 = 1;
        }
        if (i9 <= 0) {
        }
        options.inSampleSize = i8;
        decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
        Matrix matrix2 = new Matrix();
        matrix2.postScale(-1.0f, 1.0f);
        matrix2.postRotate(i7);
        decodeByteArray = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix2, true);
        bArr2 = bitmap2Bytes(decodeByteArray, Bitmap.CompressFormat.JPEG, i4);
        if (decodeByteArray != null) {
            decodeByteArray.recycle();
        }
        byteArrayOutputStream.close();
        return bArr2;
    }
}
