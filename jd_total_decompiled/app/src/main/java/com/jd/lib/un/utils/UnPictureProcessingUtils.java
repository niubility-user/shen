package com.jd.lib.un.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes16.dex */
public class UnPictureProcessingUtils {
    public static Bitmap clip(Bitmap bitmap, int i2, int i3, int i4, int i5) {
        return clip(bitmap, i2, i3, i4, i5, false);
    }

    private static File getFileByPath(String str) {
        if (UnStringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static String getFileExtension(String str) {
        if (UnStringUtils.isSpace(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String getImageType(String str) {
        return getImageType(getFileByPath(str));
    }

    public static int getRotateDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private static boolean isBMP(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77;
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    private static boolean isGIF(byte[] bArr) {
        return bArr.length >= 6 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70 && bArr[3] == 56 && (bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97;
    }

    public static boolean isImage(File file) {
        return file != null && isImage(file.getPath());
    }

    private static boolean isJPEG(byte[] bArr) {
        return bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40;
    }

    private static boolean isPNG(byte[] bArr) {
        return bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10;
    }

    public static Bitmap rotate(Bitmap bitmap, int i2, float f2, float f3) {
        return rotate(bitmap, i2, f2, f3, false);
    }

    public static Bitmap scale(Bitmap bitmap, int i2, int i3) {
        return scale(bitmap, i2, i3, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f2, float f3) {
        return skew(bitmap, f2, f3, 0.0f, 0.0f, false);
    }

    public static Bitmap clip(Bitmap bitmap, int i2, int i3, int i4, int i5, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i2, i3, i4, i5);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getImageType(File file) {
        Throwable th;
        FileInputStream fileInputStream;
        IOException e2;
        String imageType;
        if (file == null) {
            return "";
        }
        FileInputStream fileInputStream2 = null;
        try {
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException e4) {
                fileInputStream = null;
                e2 = e4;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                }
                throw th;
            }
            try {
                imageType = getImageType(fileInputStream);
            } catch (IOException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                String fileExtension = getFileExtension(file.getAbsolutePath());
                if (!TextUtils.isEmpty(fileExtension)) {
                }
            }
            if (imageType != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                return imageType;
            }
            fileInputStream.close();
            String fileExtension2 = getFileExtension(file.getAbsolutePath());
            return !TextUtils.isEmpty(fileExtension2) ? fileExtension2 : fileExtension2.toUpperCase();
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isImage(String str) {
        String upperCase = str.toUpperCase();
        return upperCase.endsWith(".PNG") || upperCase.endsWith(".JPG") || upperCase.endsWith(".JPEG") || upperCase.endsWith(".BMP");
    }

    public static Bitmap rotate(Bitmap bitmap, int i2, float f2, float f3, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        if (i2 == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i2, f2, f3);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap scale(Bitmap bitmap, int i2, int i3, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i3, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public static Bitmap skew(Bitmap bitmap, float f2, float f3, boolean z) {
        return skew(bitmap, f2, f3, 0.0f, 0.0f, z);
    }

    public static Bitmap skew(Bitmap bitmap, float f2, float f3, float f4, float f5) {
        return skew(bitmap, f2, f3, f4, f5, false);
    }

    public static Bitmap skew(Bitmap bitmap, float f2, float f3, float f4, float f5, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setSkew(f2, f3, f4, f5);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static String getImageType(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[8];
            if (inputStream.read(bArr, 0, 8) != -1) {
                return getImageType(bArr);
            }
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String getImageType(byte[] bArr) {
        if (isJPEG(bArr)) {
            return "JPEG";
        }
        if (isGIF(bArr)) {
            return "GIF";
        }
        if (isPNG(bArr)) {
            return "PNG";
        }
        if (isBMP(bArr)) {
            return "BMP";
        }
        return null;
    }
}
