package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;
import java.io.Closeable;

/* loaded from: classes.dex */
public abstract class CloseableImage implements Closeable, ImageInfo, HasImageMetadata {
    private static final String TAG = "CloseableImage";
    private OriginalEncodedImageInfo mOriginalEncodedImageInfo;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    protected void finalize() {
        if (isClosed()) {
            return;
        }
        FLog.w(TAG, "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    @Override // com.facebook.imagepipeline.image.HasImageMetadata
    public OriginalEncodedImageInfo getOriginalEncodedImageInfo() {
        return this.mOriginalEncodedImageInfo;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    public abstract int getSizeInBytes();

    public abstract boolean isClosed();

    public boolean isStateful() {
        return false;
    }

    public void setOriginalEncodedImageInfo(OriginalEncodedImageInfo originalEncodedImageInfo) {
        this.mOriginalEncodedImageInfo = originalEncodedImageInfo;
    }
}
