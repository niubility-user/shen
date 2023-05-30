package com.jingdong.common.pool.bitmappool.internal;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes5.dex */
public class Util {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.pool.bitmappool.internal.Util$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private Util() {
    }

    public static boolean bothNullOrEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i2, int i3) {
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        int i6 = 1;
        if (i4 > i3 || i5 > i2) {
            int i7 = i4 / 2;
            int i8 = i5 / 2;
            while (i7 / i6 > i3 && i8 / i6 > i2) {
                i6 *= 2;
            }
        }
        return i6;
    }

    public static boolean canUseForInBitmap(Bitmap bitmap, BitmapFactory.Options options) {
        if (Build.VERSION.SDK_INT < 19) {
            return bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight && options.inSampleSize == 1;
        }
        int i2 = options.outWidth;
        int i3 = options.inSampleSize;
        int bytesPerPixel = (i2 / i3) * (options.outHeight / i3) * getBytesPerPixel(bitmap.getConfig());
        try {
            return bytesPerPixel <= bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bytesPerPixel <= bitmap.getHeight() * bitmap.getRowBytes();
        }
    }

    public static <T> Queue<T> createQueue(int i2) {
        return new ArrayDeque(i2);
    }

    @TargetApi(19)
    public static int getBitmapByteSize(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    return bitmap.getAllocationByteCount();
                } catch (NullPointerException unused) {
                }
            }
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
        throw new IllegalStateException("Cannot obtain size for recycled Bitmap: " + bitmap + "[" + bitmap.getWidth() + JshopConst.JSHOP_PROMOTIO_X + bitmap.getHeight() + "] " + bitmap.getConfig());
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i2 = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        if (i2 != 1) {
            return (i2 == 2 || i2 == 3) ? 2 : 4;
        }
        return 1;
    }

    public static int getBitmapByteSize(int i2, int i3, Bitmap.Config config) {
        return i2 * i3 * getBytesPerPixel(config);
    }
}
