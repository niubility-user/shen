package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(11)
/* loaded from: classes.dex */
public abstract class DefaultDecoder implements PlatformDecoder {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private final BitmapPool mBitmapPool;
    @VisibleForTesting
    final Pools.SynchronizedPool<ByteBuffer> mDecodeBuffers;
    @Nullable
    private final PreverificationHelper mPreverificationHelper;
    private static final Class<?> TAG = DefaultDecoder.class;
    private static final byte[] EOI_TAIL = {-1, -39};

    public DefaultDecoder(BitmapPool bitmapPool, int i2, Pools.SynchronizedPool synchronizedPool) {
        this.mPreverificationHelper = Build.VERSION.SDK_INT >= 26 ? new PreverificationHelper() : null;
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = synchronizedPool;
        for (int i3 = 0; i3 < i2; i3++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
        if (r0 != null) goto L47;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a9 A[Catch: all -> 0x00cc, RuntimeException -> 0x00ce, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x00ce, blocks: (B:30:0x006b, B:55:0x00a9, B:47:0x009a, B:51:0x00a2, B:52:0x00a5), top: B:75:0x006b, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b4 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.graphics.BitmapRegionDecoder] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.facebook.imagepipeline.platform.DefaultDecoder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private CloseableReference<Bitmap> decodeFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        Bitmap bitmap;
        Bitmap bitmap2;
        BitmapRegionDecoder bitmapRegionDecoder;
        PreverificationHelper preverificationHelper;
        Preconditions.checkNotNull(inputStream);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        ?? r0 = i2;
        if (rect != null) {
            int width = rect.width() / options.inSampleSize;
            i3 = rect.height() / options.inSampleSize;
            r0 = width;
        }
        int i4 = Build.VERSION.SDK_INT;
        boolean z = i4 >= 26 && (preverificationHelper = this.mPreverificationHelper) != null && preverificationHelper.shouldUseHardwareBitmapConfig(options.inPreferredConfig);
        BitmapRegionDecoder bitmapRegionDecoder2 = null;
        if (rect == null && z) {
            options.inMutable = false;
            bitmap = null;
        } else {
            if (rect != null && z) {
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            }
            bitmap = this.mBitmapPool.get(getBitmapSize(r0, i3, options));
            if (bitmap == null) {
                throw new NullPointerException("BitmapPool.get returned null");
            }
        }
        options.inBitmap = bitmap;
        if (i4 >= 26) {
            if (colorSpace == null) {
                colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            }
            options.inPreferredColorSpace = colorSpace;
        }
        ByteBuffer acquire = this.mDecodeBuffers.acquire();
        if (acquire == null) {
            acquire = ByteBuffer.allocate(16384);
        }
        try {
            try {
                options.inTempStorage = acquire.array();
                if (i4 >= 19 && rect != null) {
                    try {
                        if (bitmap != null) {
                            try {
                                bitmap.reconfigure(r0, i3, options.inPreferredConfig);
                                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, true);
                            } catch (IOException unused) {
                                bitmapRegionDecoder = null;
                            } catch (Throwable th) {
                                th = th;
                                if (bitmapRegionDecoder2 != null) {
                                    bitmapRegionDecoder2.recycle();
                                }
                                throw th;
                            }
                            try {
                                bitmap2 = bitmapRegionDecoder.decodeRegion(rect, options);
                                r0 = bitmapRegionDecoder;
                            } catch (IOException unused2) {
                                FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", rect);
                                if (bitmapRegionDecoder != null) {
                                    bitmap2 = null;
                                    r0 = bitmapRegionDecoder;
                                    r0.recycle();
                                    if (bitmap2 == null) {
                                    }
                                    if (bitmap != null) {
                                    }
                                    return CloseableReference.of(bitmap2, this.mBitmapPool);
                                }
                                bitmap2 = null;
                                if (bitmap2 == null) {
                                }
                                if (bitmap != null) {
                                }
                                return CloseableReference.of(bitmap2, this.mBitmapPool);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bitmapRegionDecoder2 = r0;
                    }
                }
                bitmap2 = null;
                if (bitmap2 == null) {
                    bitmap2 = BitmapFactory.decodeStream(inputStream, null, options);
                }
                if (bitmap != null || bitmap == bitmap2) {
                    return CloseableReference.of(bitmap2, this.mBitmapPool);
                }
                this.mBitmapPool.release(bitmap);
                bitmap2.recycle();
                throw new IllegalStateException();
            } catch (RuntimeException e2) {
                if (bitmap != null) {
                    this.mBitmapPool.release(bitmap);
                }
                throw e2;
            }
        } finally {
            this.mDecodeBuffers.release(acquire);
        }
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, config, rect, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream(encodedImage.getInputStream(), decodeOptionsForStream, rect, colorSpace);
        } catch (RuntimeException e2) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e2;
        }
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i2) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, config, rect, i2, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i2, @Nullable ColorSpace colorSpace) {
        boolean isCompleteAt = encodedImage.isCompleteAt(i2);
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        TailAppendingInputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > i2) {
            inputStream = new LimitedInputStream(inputStream, i2);
        }
        if (!isCompleteAt) {
            inputStream = new TailAppendingInputStream(inputStream, EOI_TAIL);
        }
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream(inputStream, decodeOptionsForStream, rect, colorSpace);
        } catch (RuntimeException e2) {
            if (z) {
                return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, i2, colorSpace);
            }
            throw e2;
        }
    }

    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect rect) {
        return decodeFromStream(inputStream, options, rect, null);
    }

    public abstract int getBitmapSize(int i2, int i3, BitmapFactory.Options options);
}
