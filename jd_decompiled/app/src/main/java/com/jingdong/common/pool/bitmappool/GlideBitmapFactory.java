package com.jingdong.common.pool.bitmappool;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import com.jingdong.common.pool.bitmappool.internal.Util;
import java.io.FileDescriptor;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class GlideBitmapFactory {
    public static Bitmap decodeByteArray(byte[] bArr, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        }
    }

    public static Bitmap decodeFile(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFile(str, options);
        }
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        }
    }

    public static Bitmap decodeResource(Resources resources, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i2, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResource(resources, i2, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeResource(resources, i2, options);
        }
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        }
    }

    public static Bitmap decodeStream(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeStream(inputStream, null, options);
        }
    }

    public static Bitmap decodeByteArray(byte[] bArr, int i2, int i3, int i4, int i5) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i4, i5);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
        }
    }

    public static Bitmap decodeFile(String str, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFile(str, options);
        }
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        }
    }

    public static Bitmap decodeResource(Resources resources, int i2, int i3, int i4) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i2, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i3, i4);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResource(resources, i2, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeResource(resources, i2, options);
        }
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        }
    }

    public static Bitmap decodeStream(InputStream inputStream, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeStream(inputStream, null, options);
        }
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        }
    }

    public static Bitmap decodeStream(InputStream inputStream, Rect rect) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, rect, options);
        options.inSampleSize = 1;
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeStream(inputStream, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeStream(inputStream, rect, options);
        }
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        }
    }

    public static Bitmap decodeStream(InputStream inputStream, Rect rect, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, rect, options);
        options.inSampleSize = Util.calculateInSampleSize(options, i2, i3);
        if (Build.VERSION.SDK_INT > 11) {
            options.inMutable = true;
            Bitmap bitmap = GlideBitmapPool.getBitmap(options.outWidth, options.outHeight, options.inPreferredConfig);
            if (bitmap != null && Util.canUseForInBitmap(bitmap, options)) {
                options.inBitmap = bitmap;
            }
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeStream(inputStream, rect, options);
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT > 11) {
                options.inBitmap = null;
            }
            return BitmapFactory.decodeStream(inputStream, rect, options);
        }
    }
}
