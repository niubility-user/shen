package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageFormatChecker {
    private static ImageFormatChecker sInstance;
    @Nullable
    private List<ImageFormat.FormatChecker> mCustomImageFormatCheckers;
    private final ImageFormat.FormatChecker mDefaultFormatChecker = new DefaultImageFormatChecker();
    private int mMaxHeaderLength;

    private ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public static ImageFormat getImageFormat(InputStream inputStream) {
        return getInstance().determineImageFormat(inputStream);
    }

    public static ImageFormat getImageFormat(String str) {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    ImageFormat imageFormat = getImageFormat(fileInputStream2);
                    Closeables.closeQuietly(fileInputStream2);
                    return imageFormat;
                } catch (IOException unused) {
                    fileInputStream = fileInputStream2;
                    ImageFormat imageFormat2 = ImageFormat.UNKNOWN;
                    Closeables.closeQuietly(fileInputStream);
                    return imageFormat2;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    Closeables.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        } catch (IOException e2) {
            throw Throwables.propagate(e2);
        }
    }

    public static synchronized ImageFormatChecker getInstance() {
        ImageFormatChecker imageFormatChecker;
        synchronized (ImageFormatChecker.class) {
            if (sInstance == null) {
                sInstance = new ImageFormatChecker();
            }
            imageFormatChecker = sInstance;
        }
        return imageFormatChecker;
    }

    private static int readHeaderFromStream(int i2, InputStream inputStream, byte[] bArr) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(bArr.length >= i2);
        if (inputStream.markSupported()) {
            try {
                inputStream.mark(i2);
                return ByteStreams.read(inputStream, bArr, 0, i2);
            } finally {
                inputStream.reset();
            }
        }
        return ByteStreams.read(inputStream, bArr, 0, i2);
    }

    private void updateMaxHeaderLength() {
        this.mMaxHeaderLength = this.mDefaultFormatChecker.getHeaderSize();
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            Iterator<ImageFormat.FormatChecker> it = list.iterator();
            while (it.hasNext()) {
                this.mMaxHeaderLength = Math.max(this.mMaxHeaderLength, it.next().getHeaderSize());
            }
        }
    }

    public ImageFormat determineImageFormat(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream);
        int i2 = this.mMaxHeaderLength;
        byte[] bArr = new byte[i2];
        int readHeaderFromStream = readHeaderFromStream(i2, inputStream, bArr);
        ImageFormat determineFormat = this.mDefaultFormatChecker.determineFormat(bArr, readHeaderFromStream);
        if (determineFormat == null || determineFormat == ImageFormat.UNKNOWN) {
            List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
            if (list != null) {
                Iterator<ImageFormat.FormatChecker> it = list.iterator();
                while (it.hasNext()) {
                    ImageFormat determineFormat2 = it.next().determineFormat(bArr, readHeaderFromStream);
                    if (determineFormat2 != null && determineFormat2 != ImageFormat.UNKNOWN) {
                        return determineFormat2;
                    }
                }
            }
            return ImageFormat.UNKNOWN;
        }
        return determineFormat;
    }

    public void setCustomImageFormatCheckers(@Nullable List<ImageFormat.FormatChecker> list) {
        this.mCustomImageFormatCheckers = list;
        updateMaxHeaderLength();
    }
}
