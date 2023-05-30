package com.jingdong.manto.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.annotation.NonNull;
import com.jingdong.manto.utils.MantoLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes16.dex */
public class b {
    public static boolean a;

    public static Bitmap a(Bitmap bitmap, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int ceil;
        int i6;
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null && i2 > 0 && i3 > 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (Build.VERSION.SDK_INT < 14) {
                try {
                    BitmapFactory.Options.class.getField("inNativeAlloc").setBoolean(options, true);
                } catch (Throwable unused) {
                }
            }
            options.outHeight = bitmap.getHeight();
            int width = bitmap.getWidth();
            options.outWidth = width;
            double d = options.outHeight;
            Double.isNaN(d);
            double d2 = i2;
            Double.isNaN(d2);
            double d3 = (d * 1.0d) / d2;
            double d4 = width;
            Double.isNaN(d4);
            double d5 = i3;
            Double.isNaN(d5);
            double d6 = (d4 * 1.0d) / d5;
            int i7 = (int) ((!z ? d3 < d6 : d3 > d6) ? d3 : d6);
            options.inSampleSize = i7;
            if (i7 <= 1) {
                options.inSampleSize = 1;
            }
            while (true) {
                i4 = options.outHeight;
                i5 = options.outWidth;
                int i8 = options.inSampleSize;
                if (((i4 * i5) / i8) / i8 <= 2764800) {
                    break;
                }
                options.inSampleSize = i8 + 1;
            }
            if (!z ? d3 < d6 : d3 > d6) {
                Double.isNaN(d2);
                double d7 = i5;
                Double.isNaN(d7);
                double d8 = d2 * 1.0d * d7;
                double d9 = i4;
                Double.isNaN(d9);
                i6 = (int) Math.ceil(d8 / d9);
                ceil = i2;
            } else {
                Double.isNaN(d5);
                double d10 = i4;
                Double.isNaN(d10);
                double d11 = d5 * 1.0d * d10;
                double d12 = i5;
                Double.isNaN(d12);
                ceil = (int) Math.ceil(d11 / d12);
                i6 = i3;
            }
            options.inJustDecodeBounds = false;
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i6, ceil, true);
            if (createScaledBitmap != null) {
                if (z2 && bitmap2 != createScaledBitmap) {
                    bitmap.recycle();
                }
                bitmap2 = createScaledBitmap;
            }
            if (z) {
                int width2 = bitmap2.getWidth() < i3 ? bitmap2.getWidth() : i3;
                int height = bitmap2.getHeight() < i2 ? bitmap2.getHeight() : i2;
                Bitmap createBitmap = Bitmap.createBitmap(bitmap2, (bitmap2.getWidth() - width2) >> 1, (bitmap2.getHeight() - height) >> 1, width2, height);
                if (createBitmap == null) {
                    return bitmap2;
                }
                if (z2 && bitmap2 != createBitmap) {
                    bitmap2.recycle();
                }
                return createBitmap;
            }
            return bitmap2;
        }
        return null;
    }

    public static Bitmap a(InputStream inputStream) {
        return a(inputStream, 0.0f, 0, 0);
    }

    public static Bitmap a(InputStream inputStream, float f2, int i2, int i3) {
        InputStream bufferedInputStream;
        int i4;
        int i5;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (f2 != 0.0f) {
            options.inDensity = (int) (f2 * 160.0f);
        }
        if (i2 != 0 || i3 != 0) {
            if (i2 == 0) {
                i2 = Integer.MAX_VALUE;
            }
            if (i3 == 0) {
                i3 = Integer.MAX_VALUE;
            }
            try {
                if (inputStream instanceof FileInputStream) {
                    bufferedInputStream = new com.jingdong.manto.sdk.e.b((FileInputStream) inputStream);
                } else {
                    if (!inputStream.markSupported()) {
                        bufferedInputStream = new BufferedInputStream(inputStream, 65536);
                    }
                    inputStream.mark(25165824);
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(inputStream, null, options);
                    i4 = options.outWidth;
                    i5 = options.outHeight;
                    if (i4 <= i2 || i5 > i3) {
                        options.inSampleSize = (int) Math.max(i4 / i2, i5 / i3);
                    }
                    options.inJustDecodeBounds = false;
                    inputStream.reset();
                }
                inputStream.reset();
            } catch (Throwable unused) {
            }
            inputStream = bufferedInputStream;
            inputStream.mark(25165824);
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            i4 = options.outWidth;
            i5 = options.outHeight;
            if (i4 <= i2) {
            }
            options.inSampleSize = (int) Math.max(i4 / i2, i5 / i3);
            options.inJustDecodeBounds = false;
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        a(options);
        try {
            return BitmapFactory.decodeStream(inputStream, null, options);
        } catch (OutOfMemoryError unused2) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            a(options);
            try {
                return BitmapFactory.decodeStream(inputStream, null, options);
            } catch (OutOfMemoryError unused3) {
                MantoLog.e("BitmapUtil", "decodeStream OutOfMemoryError return null");
                return null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.net.Uri a(java.lang.String r9) {
        /*
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            r1 = 58
            int r1 = r9.indexOf(r1)
            r2 = 0
            r3 = 1
            r4 = 47
            if (r1 >= 0) goto L15
        L11:
            r0.path(r9)
            goto L6f
        L15:
            java.lang.String r5 = r9.substring(r2, r1)
            r0.scheme(r5)
            int r5 = r9.length()
            int r6 = r1 + 2
            if (r5 <= r6) goto L69
            int r7 = r1 + 1
            char r7 = r9.charAt(r7)
            if (r7 != r4) goto L69
            char r6 = r9.charAt(r6)
            if (r6 != r4) goto L69
            int r1 = r1 + 3
            r6 = r1
        L35:
            if (r6 >= r5) goto L5a
            char r7 = r9.charAt(r6)
            r8 = 35
            if (r7 == r8) goto L47
            if (r7 == r4) goto L47
            r8 = 63
            if (r7 == r8) goto L47
            int r6 = r6 + 1
        L47:
            java.lang.String r7 = r9.substring(r1, r6)
            r0.authority(r7)
            if (r6 >= r5) goto L35
            int r7 = r6 + 1
            java.lang.String r7 = r9.substring(r7)
            r0.path(r7)
            goto L35
        L5a:
            java.lang.String r1 = r9.substring(r1, r6)
            r0.authority(r1)
            if (r6 >= r5) goto L6f
            int r6 = r6 + r3
            java.lang.String r9 = r9.substring(r6)
            goto L11
        L69:
            int r1 = r1 + r3
            java.lang.String r9 = r9.substring(r1)
            goto L11
        L6f:
            android.net.Uri r9 = r0.build()
            java.lang.String r0 = r9.getPath()
            int r1 = r0.length()
            if (r1 <= 0) goto L85
            char r1 = r0.charAt(r2)
            if (r1 != r4) goto L85
            r1 = 1
            goto L86
        L85:
            r1 = 0
        L86:
            if (r1 == 0) goto L89
            goto Lb7
        L89:
            java.lang.String r0 = r9.getPath()
            java.lang.String r1 = "user.dir"
            java.lang.String r1 = java.lang.System.getProperty(r1)
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto Lab
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r1)
            r5.append(r4)
            r5.append(r0)
            java.lang.String r1 = r5.toString()
        Lab:
            android.net.Uri$Builder r9 = r9.buildUpon()
            android.net.Uri$Builder r9 = r9.path(r1)
            android.net.Uri r9 = r9.build()
        Lb7:
            java.lang.String r1 = r9.getPath()
            int r5 = r1.length()
            int r1 = r1.lastIndexOf(r4)
            r6 = -1
            if (r1 == r6) goto Lec
            int r5 = r5 - r3
            char r5 = r0.charAt(r5)
            if (r5 != r4) goto Lce
            goto Lec
        Lce:
            int r5 = r0.indexOf(r4)
            if (r5 != r1) goto Ldb
            char r5 = r0.charAt(r2)
            if (r5 != r4) goto Ldb
            int r1 = r1 + r3
        Ldb:
            java.lang.String r0 = r0.substring(r2, r1)
            android.net.Uri$Builder r9 = r9.buildUpon()
            android.net.Uri$Builder r9 = r9.path(r0)
            android.net.Uri r9 = r9.build()
            return r9
        Lec:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.sdk.b.a(java.lang.String):android.net.Uri");
    }

    public static void a(Bitmap bitmap, int i2, Bitmap.CompressFormat compressFormat, @NonNull String str, boolean z) {
        FileOutputStream fileOutputStream;
        a(str);
        File file = new File(str);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                MantoLog.e("bitmapUtils", e2.toString());
            }
        }
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Throwable unused) {
            fileOutputStream = null;
        }
        try {
            bitmap.compress(compressFormat, i2, fileOutputStream);
            if (z) {
                bitmap.recycle();
            }
            try {
                fileOutputStream.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable unused3) {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public static void a(BitmapFactory.Options options) {
        if (Build.VERSION.SDK_INT >= 14 || a) {
            return;
        }
        try {
            BitmapFactory.Options.class.getField("inNativeAlloc").setBoolean(options, true);
        } catch (Throwable th) {
            MantoLog.e("BitmapUtil", th.getMessage());
            a = true;
        }
    }
}
