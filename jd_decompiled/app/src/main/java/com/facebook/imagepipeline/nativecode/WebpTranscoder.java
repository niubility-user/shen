package com.facebook.imagepipeline.nativecode;

import com.facebook.imageformat.ImageFormat;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public interface WebpTranscoder {
    boolean isWebpNativelySupported(ImageFormat imageFormat);

    void transcodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i2);

    void transcodeWebpToPng(InputStream inputStream, OutputStream outputStream);
}
