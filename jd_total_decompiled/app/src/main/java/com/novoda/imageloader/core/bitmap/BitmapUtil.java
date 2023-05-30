package com.novoda.imageloader.core.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.logging.FLog;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* loaded from: classes14.dex */
public class BitmapUtil {
    public static final String BIG_BITMAP_ERRCODE = "903";
    public static final String BITMAP_AVIF_ERRCODE = "827";
    public static final String BITMAP_DPG_ERRCODE = "911";
    public static final String BITMAP_IO_ERRCODE = "910";
    public static final String BITMAP_NETWORK_ERRCODE = "808";
    public static final String BITMAP_URL_ERRCODE = "909";
    private static final int BUFFER_SIZE = 65536;
    public static final String DPG_REPORT_ERRCODE = "957";
    public static final String DUPLICATE_PIC_ERRCODE = "801";
    public static final int IMAGE_MAX_HEIGHT = 666;
    public static final int IMAGE_MAX_WIDTH = 666;
    private static final String TAG = "BitmapUtil";
    private static float mDensity = 160.0f;

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    private Bitmap decodeFile(File file, int i2) {
        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        try {
            int evaluateScale = evaluateScale(file, i2);
            FLog.d(TAG, "decodeFile() scale=" + evaluateScale + " -->> ");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = evaluateScale;
            options.inTempStorage = new byte[65536];
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inPurgeable = true;
            fileInputStream = new FileInputStream(file);
            try {
                bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
        closeSilently(fileInputStream);
        return bitmap;
    }

    private Bitmap decodeFileAndScale2(File file, int i2, int i3, boolean z) {
        float f2;
        float f3;
        Bitmap decodeFile = decodeFile(file, i2, i3);
        Bitmap bitmap = null;
        if (decodeFile == null) {
            return null;
        }
        int height = decodeFile.getHeight();
        int width = decodeFile.getWidth();
        if (z || height > i3 || width > i2) {
            float min = (height <= 0 || width <= 0) ? 0.0f : Math.min(i3 / height, i2 / width);
            if (min == 0.0f) {
                if (height > width) {
                    f2 = i3;
                    f3 = height;
                } else {
                    f2 = i2;
                    f3 = width;
                }
                min = f2 / f3;
            }
            try {
                bitmap = Bitmap.createScaledBitmap(decodeFile, new Float(width * min).intValue(), new Float(height * min).intValue(), true);
            } catch (Throwable unused) {
            }
            if (bitmap != decodeFile) {
                recycle(decodeFile);
            }
            return bitmap;
        }
        return decodeFile;
    }

    private void decodeFileToPopulateOptions(File file, BitmapFactory.Options options) {
        FileInputStream fileInputStream;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable unused) {
        }
        try {
            BitmapFactory.decodeStream(fileInputStream, null, options);
            closeSilently(fileInputStream);
            closeSilently(fileInputStream);
        } catch (Throwable unused2) {
            closeable = fileInputStream;
            closeSilently(closeable);
        }
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * mDensity) + 0.5f);
    }

    private int evaluateScale(File file, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeFileToPopulateOptions(file, options);
        return calculateScale(i2, options.outWidth, options.outHeight);
    }

    private void recycle(Bitmap bitmap) {
        try {
            bitmap.recycle();
        } catch (Exception unused) {
        }
    }

    private void updateLastModifiedForCache(File file) {
        file.setLastModified(System.currentTimeMillis());
    }

    int calculateScale(int i2, int i3, int i4) {
        int i5 = 1;
        while (true) {
            i3 /= 2;
            if (i3 < i2 || (i4 = i4 / 2) < i2) {
                break;
            }
            i5 *= 2;
        }
        return i5;
    }

    public Bitmap createBitmapWithClean(File file, int i2, int i3, boolean z) {
        Bitmap bitmap = null;
        for (int i4 = 0; i4 < 2 && ((bitmap = decodeFileAndScale2(file, i2, i3, z)) == null || bitmap.isRecycled()); i4++) {
            GlobalImageCache.getLruBitmapCache().cleanMost();
        }
        return bitmap;
    }

    public Bitmap decodeFile(File file, int i2, int i3) {
        updateLastModifiedForCache(file);
        if (i2 <= i3) {
            i2 = i3;
        }
        Bitmap decodeFile = decodeFile(file, i2);
        if (decodeFile == null) {
            return null;
        }
        return decodeFile;
    }

    @Deprecated
    public Bitmap decodeFileAndScale(File file, int i2, int i3) {
        return decodeFileAndScale(file, i2, i3, false);
    }

    public Bitmap decodeFileAndScale(File file, int i2, int i3, boolean z) {
        Bitmap decodeFile = decodeFile(file, i2, i3);
        if (decodeFile == null) {
            return null;
        }
        return scaleBitmap(decodeFile, i2, i3, z);
    }

    public Bitmap decodeInputStream(InputStream inputStream) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(inputStream, null, null);
        } catch (Throwable unused) {
        }
        closeSilently(inputStream);
        return bitmap;
    }

    public Bitmap decodeResourceBitmap(Context context, int i2, int i3, int i4) {
        try {
            return BitmapFactory.decodeResource(context.getResources(), i4);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Bitmap decodeResourceBitmapAndScale(Context context, int i2, int i3, int i4, boolean z) {
        try {
            return scaleBitmap(BitmapFactory.decodeResource(context.getResources(), i4), i2, i3, z);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Bitmap scaleBitmap(Bitmap bitmap, int i2, int i3) {
        return scaleBitmap(bitmap, i2, i3, false);
    }

    public Bitmap scaleBitmap(Bitmap bitmap, int i2, int i3, boolean z) {
        float f2;
        float f3;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        FLog.d(TAG, "scaleBitmap() width=" + i2 + " height=" + i3 + " imageWidth=" + width + " imageHeight=" + height + " upsampling=" + z + " -->> ");
        if ((width > dip2px(666.0f) || height > dip2px(666.0f)) && i2 == 0 && i3 == 0) {
            i2 = dip2px(666.0f);
            i3 = dip2px(666.0f);
        }
        if (!(i2 == 0 && i3 == 0) && (z || height > i3 || width > i2)) {
            if (i3 != 0 && (i2 == 0 || height > width)) {
                f2 = i3;
                f3 = height;
            } else {
                f2 = i2;
                f3 = width;
            }
            float f4 = f2 / f3;
            if (z || f4 <= 1.0f) {
                Bitmap bitmap2 = null;
                try {
                    bitmap2 = Bitmap.createScaledBitmap(bitmap, new Float(width * f4).intValue(), new Float(height * f4).intValue(), true);
                } catch (Throwable unused) {
                }
                if (bitmap != bitmap2) {
                    recycle(bitmap);
                }
                if (bitmap2 != null) {
                    FLog.d(TAG, "scaleBitmap() return width=" + bitmap2.getWidth() + " height=" + bitmap2.getHeight() + " -->> ");
                }
                return bitmap2;
            }
            return bitmap;
        }
        return bitmap;
    }

    @Deprecated
    public Bitmap scaleResourceBitmap(Context context, int i2, int i3, int i4) {
        return decodeResourceBitmapAndScale(context, i2, i3, i4, false);
    }
}
