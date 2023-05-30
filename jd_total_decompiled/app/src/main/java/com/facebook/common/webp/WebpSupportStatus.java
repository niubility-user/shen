package com.facebook.common.webp;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpSupportStatus {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final byte[] WEBP_NAME_BYTES;
    private static final byte[] WEBP_RIFF_BYTES;
    private static final byte[] WEBP_VP8L_BYTES;
    private static final byte[] WEBP_VP8X_BYTES;
    private static final byte[] WEBP_VP8_BYTES;
    public static final boolean sIsExtendedWebpSupported;
    public static final boolean sIsSimpleWebpSupported;
    public static final boolean sIsWebpSupportRequired;
    @Nullable
    public static WebpBitmapFactory sWebpBitmapFactory;
    private static boolean sWebpLibraryChecked;

    static {
        int i2 = Build.VERSION.SDK_INT;
        sIsWebpSupportRequired = i2 <= 17;
        sIsSimpleWebpSupported = i2 >= 14;
        sIsExtendedWebpSupported = isExtendedWebpSupported();
        sWebpBitmapFactory = null;
        sWebpLibraryChecked = false;
        WEBP_RIFF_BYTES = asciiBytes("RIFF");
        WEBP_NAME_BYTES = asciiBytes("WEBP");
        WEBP_VP8_BYTES = asciiBytes("VP8 ");
        WEBP_VP8L_BYTES = asciiBytes("VP8L");
        WEBP_VP8X_BYTES = asciiBytes("VP8X");
    }

    private static byte[] asciiBytes(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("ASCII not found!", e2);
        }
    }

    public static boolean isAnimatedWebpHeader(byte[] bArr, int i2) {
        return matchBytePattern(bArr, i2 + 12, WEBP_VP8X_BYTES) && ((bArr[i2 + 20] & 2) == 2);
    }

    public static boolean isExtendedWebpHeader(byte[] bArr, int i2, int i3) {
        return i3 >= 21 && matchBytePattern(bArr, i2 + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(byte[] bArr, int i2) {
        return matchBytePattern(bArr, i2 + 12, WEBP_VP8X_BYTES) && ((bArr[i2 + 20] & 16) == 16);
    }

    private static boolean isExtendedWebpSupported() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 17) {
            return false;
        }
        if (i2 == 17) {
            byte[] decode = Base64.decode(VP8X_WEBP_BASE64, 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            if (options.outHeight != 1 || options.outWidth != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLosslessWebpHeader(byte[] bArr, int i2) {
        return matchBytePattern(bArr, i2 + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isSimpleWebpHeader(byte[] bArr, int i2) {
        return matchBytePattern(bArr, i2 + 12, WEBP_VP8_BYTES);
    }

    public static boolean isWebpHeader(byte[] bArr, int i2, int i3) {
        return i3 >= 20 && matchBytePattern(bArr, i2, WEBP_RIFF_BYTES) && matchBytePattern(bArr, i2 + 8, WEBP_NAME_BYTES);
    }

    public static boolean isWebpSupportedByPlatform(byte[] bArr, int i2, int i3) {
        if (isSimpleWebpHeader(bArr, i2)) {
            return sIsSimpleWebpSupported;
        }
        if (isLosslessWebpHeader(bArr, i2)) {
            return sIsExtendedWebpSupported;
        }
        if (!isExtendedWebpHeader(bArr, i2, i3) || isAnimatedWebpHeader(bArr, i2)) {
            return false;
        }
        return sIsExtendedWebpSupported;
    }

    @Nullable
    public static WebpBitmapFactory loadWebpBitmapFactoryIfExists() {
        if (sWebpLibraryChecked) {
            return sWebpBitmapFactory;
        }
        WebpBitmapFactory webpBitmapFactory = null;
        try {
            webpBitmapFactory = (WebpBitmapFactory) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
        } catch (Throwable unused) {
        }
        sWebpLibraryChecked = true;
        return webpBitmapFactory;
    }

    private static boolean matchBytePattern(byte[] bArr, int i2, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length + i2 > bArr.length) {
            return false;
        }
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            if (bArr[i3 + i2] != bArr2[i3]) {
                return false;
            }
        }
        return true;
    }
}
