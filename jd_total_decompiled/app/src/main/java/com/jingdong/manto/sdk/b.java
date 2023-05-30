package com.jingdong.manto.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
    */
    private static Uri a(String str) {
        String path;
        int lastIndexOf;
        Uri.Builder builder = new Uri.Builder();
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            builder.scheme(str.substring(0, indexOf));
            int length = str.length();
            int i2 = indexOf + 2;
            if (length > i2 && str.charAt(indexOf + 1) == '/' && str.charAt(i2) == '/') {
                int i3 = indexOf + 3;
                int i4 = i3;
                while (i4 < length) {
                    char charAt = str.charAt(i4);
                    if (charAt != '#' && charAt != '/' && charAt != '?') {
                        i4++;
                    }
                    builder.authority(str.substring(i3, i4));
                    if (i4 < length) {
                        builder.path(str.substring(i4 + 1));
                    }
                }
                builder.authority(str.substring(i3, i4));
                if (i4 < length) {
                    str = str.substring(i4 + 1);
                }
                Uri build = builder.build();
                path = build.getPath();
                if (!(path.length() <= 0 && path.charAt(0) == '/')) {
                    path = build.getPath();
                    String property = System.getProperty("user.dir");
                    if (!path.isEmpty()) {
                        property = property + '/' + path;
                    }
                    build = build.buildUpon().path(property).build();
                }
                String path2 = build.getPath();
                int length2 = path2.length();
                lastIndexOf = path2.lastIndexOf(47);
                if (lastIndexOf == -1 && path.charAt(length2 - 1) != '/') {
                    if (path.indexOf(47) == lastIndexOf && path.charAt(0) == '/') {
                        lastIndexOf++;
                    }
                    return build.buildUpon().path(path.substring(0, lastIndexOf)).build();
                }
            }
            str = str.substring(indexOf + 1);
        }
        builder.path(str);
        Uri build2 = builder.build();
        path = build2.getPath();
        if (!(path.length() <= 0 && path.charAt(0) == '/')) {
        }
        String path22 = build2.getPath();
        int length22 = path22.length();
        lastIndexOf = path22.lastIndexOf(47);
        return lastIndexOf == -1 ? null : null;
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
