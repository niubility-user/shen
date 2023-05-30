package com.facebook.webpsupport;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes12.dex */
public class WebpBitmapFactoryImpl implements WebpBitmapFactory {
    private static final int HEADER_SIZE = 20;
    public static final boolean IN_BITMAP_SUPPORTED;
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static BitmapCreator mBitmapCreator;
    private static WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;

    static {
        IN_BITMAP_SUPPORTED = Build.VERSION.SDK_INT >= 11;
    }

    @DoNotStrip
    private static Bitmap createBitmap(int i2, int i3, BitmapFactory.Options options) {
        Bitmap bitmap;
        return (!IN_BITMAP_SUPPORTED || options == null || (bitmap = options.inBitmap) == null || !bitmap.isMutable()) ? mBitmapCreator.createNakedBitmap(i2, i3, Bitmap.Config.ARGB_8888) : options.inBitmap;
    }

    @DoNotStrip
    private static byte[] getInTempStorageFromOptions(@Nullable BitmapFactory.Options options) {
        byte[] bArr;
        return (options == null || (bArr = options.inTempStorage) == null) ? new byte[8192] : bArr;
    }

    @DoNotStrip
    private static float getScaleFromOptions(BitmapFactory.Options options) {
        if (options != null) {
            int i2 = options.inSampleSize;
            float f2 = i2 > 1 ? 1.0f / i2 : 1.0f;
            if (options.inScaled) {
                int i3 = options.inDensity;
                int i4 = options.inTargetDensity;
                return (i3 == 0 || i4 == 0 || i3 == options.inScreenDensity) ? f2 : i4 / i3;
            }
            return f2;
        }
        return 1.0f;
    }

    @Nullable
    private static byte[] getWebpHeader(InputStream inputStream, BitmapFactory.Options options) {
        byte[] bArr;
        inputStream.mark(20);
        if (options == null || (bArr = options.inTempStorage) == null || bArr.length < 20) {
            bArr = new byte[20];
        }
        try {
            inputStream.read(bArr, 0, 20);
            inputStream.reset();
            return bArr;
        } catch (IOException unused) {
            return null;
        }
    }

    @DoNotStrip
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i2, int i3) {
        return hookDecodeByteArray(bArr, i2, i3, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeByteArray(byte[] bArr, int i2, int i3, BitmapFactory.Options options) {
        Bitmap originalDecodeByteArray;
        StaticWebpNativeLoader.ensure();
        if (WebpSupportStatus.sIsWebpSupportRequired && WebpSupportStatus.isWebpHeader(bArr, i2, i3)) {
            originalDecodeByteArray = nativeDecodeByteArray(bArr, i2, i3, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (originalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array");
            }
            setWebpBitmapOptions(originalDecodeByteArray, options);
        } else {
            originalDecodeByteArray = originalDecodeByteArray(bArr, i2, i3, options);
            if (originalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
            }
        }
        return originalDecodeByteArray;
    }

    @DoNotStrip
    public static Bitmap hookDecodeFile(String str) {
        return hookDecodeFile(str, null);
    }

    @DoNotStrip
    @Nullable
    public static Bitmap hookDecodeFile(String str, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            bitmap = hookDecodeStream(fileInputStream, null, options);
            fileInputStream.close();
        } catch (Exception unused) {
        }
        return bitmap;
    }

    @DoNotStrip
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return hookDecodeFileDescriptor(fileDescriptor, null, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) {
        Bitmap originalDecodeFileDescriptor;
        StaticWebpNativeLoader.ensure();
        long nativeSeek = nativeSeek(fileDescriptor, 0L, false);
        if (nativeSeek == -1) {
            Bitmap hookDecodeStream = hookDecodeStream(new FileInputStream(fileDescriptor), rect, options);
            setPaddingDefaultValues(rect);
            return hookDecodeStream;
        }
        InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(new FileInputStream(fileDescriptor));
        try {
            byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
            if (WebpSupportStatus.sIsWebpSupportRequired && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
                originalDecodeFileDescriptor = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
                if (originalDecodeFileDescriptor == null) {
                    sendWebpErrorLog("webp_direct_decode_fd");
                }
                setPaddingDefaultValues(rect);
                setWebpBitmapOptions(originalDecodeFileDescriptor, options);
            } else {
                nativeSeek(fileDescriptor, nativeSeek, true);
                originalDecodeFileDescriptor = originalDecodeFileDescriptor(fileDescriptor, rect, options);
                if (originalDecodeFileDescriptor == null) {
                    sendWebpErrorLog("webp_direct_decode_fd_failed_on_no_webp");
                }
            }
            try {
            } catch (Throwable unused) {
                return originalDecodeFileDescriptor;
            }
        } finally {
            try {
                wrapToMarkSupportedStream.close();
            } catch (Throwable unused2) {
            }
        }
    }

    @DoNotStrip
    public static Bitmap hookDecodeResource(Resources resources, int i2) {
        return hookDecodeResource(resources, i2, null);
    }

    @DoNotStrip
    @Nullable
    public static Bitmap hookDecodeResource(Resources resources, int i2, BitmapFactory.Options options) {
        TypedValue typedValue = new TypedValue();
        Bitmap bitmap = null;
        try {
            InputStream openRawResource = resources.openRawResource(i2, typedValue);
            bitmap = hookDecodeResourceStream(resources, typedValue, openRawResource, null, options);
            if (openRawResource != null) {
                openRawResource.close();
            }
        } catch (Exception unused) {
        }
        if (!IN_BITMAP_SUPPORTED || bitmap != null || options == null || options.inBitmap == null) {
            return bitmap;
        }
        throw new IllegalArgumentException("Problem decoding into existing bitmap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
        if (r2 != 65535) goto L10;
     */
    @com.facebook.common.internal.DoNotStrip
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap hookDecodeResourceStream(android.content.res.Resources r1, android.util.TypedValue r2, java.io.InputStream r3, android.graphics.Rect r4, android.graphics.BitmapFactory.Options r5) {
        /*
            if (r5 != 0) goto L7
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options
            r5.<init>()
        L7:
            int r0 = r5.inDensity
            if (r0 != 0) goto L1c
            if (r2 == 0) goto L1c
            int r2 = r2.density
            if (r2 != 0) goto L16
            r2 = 160(0xa0, float:2.24E-43)
        L13:
            r5.inDensity = r2
            goto L1c
        L16:
            r0 = 65535(0xffff, float:9.1834E-41)
            if (r2 == r0) goto L1c
            goto L13
        L1c:
            int r2 = r5.inTargetDensity
            if (r2 != 0) goto L2a
            if (r1 == 0) goto L2a
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r1 = r1.densityDpi
            r5.inTargetDensity = r1
        L2a:
            android.graphics.Bitmap r1 = hookDecodeStream(r3, r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.webpsupport.WebpBitmapFactoryImpl.hookDecodeResourceStream(android.content.res.Resources, android.util.TypedValue, java.io.InputStream, android.graphics.Rect, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    @DoNotStrip
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, null, null);
    }

    @DoNotStrip
    public static Bitmap hookDecodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        Bitmap originalDecodeStream;
        StaticWebpNativeLoader.ensure();
        InputStream wrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        byte[] webpHeader = getWebpHeader(wrapToMarkSupportedStream, options);
        if (WebpSupportStatus.sIsWebpSupportRequired && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
            originalDecodeStream = nativeDecodeStream(wrapToMarkSupportedStream, options, getScaleFromOptions(options), getInTempStorageFromOptions(options));
            if (originalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream");
            }
            setWebpBitmapOptions(originalDecodeStream, options);
            setPaddingDefaultValues(rect);
        } else {
            originalDecodeStream = originalDecodeStream(wrapToMarkSupportedStream, rect, options);
            if (originalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
            }
        }
        return originalDecodeStream;
    }

    @DoNotStrip
    private static native Bitmap nativeDecodeByteArray(byte[] bArr, int i2, int i3, BitmapFactory.Options options, float f2, byte[] bArr2);

    @DoNotStrip
    private static native Bitmap nativeDecodeStream(InputStream inputStream, BitmapFactory.Options options, float f2, byte[] bArr);

    @DoNotStrip
    private static native long nativeSeek(FileDescriptor fileDescriptor, long j2, boolean z);

    @DoNotStrip
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i2, int i3) {
        return BitmapFactory.decodeByteArray(bArr, i2, i3);
    }

    @DoNotStrip
    private static Bitmap originalDecodeByteArray(byte[] bArr, int i2, int i3, BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFile(String str) {
        return BitmapFactory.decodeFile(str);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFile(String str, BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(str, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    @DoNotStrip
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResource(Resources resources, int i2) {
        return BitmapFactory.decodeResource(resources, i2);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResource(Resources resources, int i2, BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, i2, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        return BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
    }

    @DoNotStrip
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @DoNotStrip
    private static Bitmap originalDecodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    private static void sendWebpErrorLog(String str) {
        WebpBitmapFactory.WebpErrorLogger webpErrorLogger = mWebpErrorLogger;
        if (webpErrorLogger != null) {
            webpErrorLogger.onWebpErrorLog(str, "decoding_failure");
        }
    }

    @DoNotStrip
    private static void setBitmapSize(@Nullable BitmapFactory.Options options, int i2, int i3) {
        if (options != null) {
            options.outWidth = i2;
            options.outHeight = i3;
        }
    }

    private static void setDensityFromOptions(Bitmap bitmap, BitmapFactory.Options options) {
        if (bitmap == null || options == null) {
            return;
        }
        int i2 = options.inDensity;
        if (i2 == 0) {
            if (!IN_BITMAP_SUPPORTED || options.inBitmap == null) {
                return;
            }
            bitmap.setDensity(160);
            return;
        }
        bitmap.setDensity(i2);
        int i3 = options.inTargetDensity;
        if (i3 == 0 || i2 == i3 || i2 == options.inScreenDensity || !options.inScaled) {
            return;
        }
        bitmap.setDensity(i3);
    }

    @DoNotStrip
    private static boolean setOutDimensions(BitmapFactory.Options options, int i2, int i3) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = i2;
        options.outHeight = i3;
        return true;
    }

    @DoNotStrip
    private static void setPaddingDefaultValues(@Nullable Rect rect) {
        if (rect != null) {
            rect.top = -1;
            rect.left = -1;
            rect.bottom = -1;
            rect.right = -1;
        }
    }

    private static void setWebpBitmapOptions(Bitmap bitmap, BitmapFactory.Options options) {
        setDensityFromOptions(bitmap, options);
        if (options != null) {
            options.outMimeType = "image/webp";
        }
    }

    @DoNotStrip
    @SuppressLint({"NewApi"})
    private static boolean shouldPremultiply(BitmapFactory.Options options) {
        if (Build.VERSION.SDK_INT < 19 || options == null) {
            return true;
        }
        return options.inPremultiplied;
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 20) : inputStream;
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public Bitmap decodeByteArray(byte[] bArr, int i2, int i3, BitmapFactory.Options options) {
        return hookDecodeByteArray(bArr, i2, i3, options);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        return hookDecodeFile(str, options);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) {
        return hookDecodeFileDescriptor(fileDescriptor, rect, options);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public Bitmap decodeStream(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        return hookDecodeStream(inputStream, rect, options);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setBitmapCreator(BitmapCreator bitmapCreator) {
        mBitmapCreator = bitmapCreator;
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
        mWebpErrorLogger = webpErrorLogger;
    }
}
