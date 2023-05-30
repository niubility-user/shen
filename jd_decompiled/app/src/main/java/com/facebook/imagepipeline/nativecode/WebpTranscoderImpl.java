package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import java.io.InputStream;
import java.io.OutputStream;

@DoNotStrip
/* loaded from: classes.dex */
public class WebpTranscoderImpl implements WebpTranscoder {
    @DoNotStrip
    private static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i2);

    @DoNotStrip
    private static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream);

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public boolean isWebpNativelySupported(ImageFormat imageFormat) {
        if (imageFormat == DefaultImageFormats.WEBP_SIMPLE) {
            return Build.VERSION.SDK_INT >= 14;
        } else if (imageFormat == DefaultImageFormats.WEBP_LOSSLESS || imageFormat == DefaultImageFormats.WEBP_EXTENDED || imageFormat == DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
            return WebpSupportStatus.sIsExtendedWebpSupported;
        } else {
            if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                return false;
            }
            throw new IllegalArgumentException("Image format is not a WebP.");
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public void transcodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i2) {
        StaticWebpNativeLoader.ensure();
        nativeTranscodeWebpToJpeg((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream), i2);
    }

    @Override // com.facebook.imagepipeline.nativecode.WebpTranscoder
    public void transcodeWebpToPng(InputStream inputStream, OutputStream outputStream) {
        StaticWebpNativeLoader.ensure();
        nativeTranscodeWebpToPng((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream));
    }
}
