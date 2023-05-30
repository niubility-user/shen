package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;

/* loaded from: classes.dex */
public class ImagePipelineExperiments {
    private final int mBitmapCloseableRefType;
    private boolean mBitmapPrepareToDrawForPrefetch;
    private final int mBitmapPrepareToDrawMaxSizeBytes;
    private final int mBitmapPrepareToDrawMinSizeBytes;
    private final boolean mDecodeCancellationEnabled;
    private boolean mDownsampleIfLargeBitmap;
    private final boolean mDownscaleFrameToDrawableDimensions;
    private boolean mEncodedCacheEnabled;
    private final boolean mExperimentalThreadHandoffQueueEnabled;
    private final boolean mGingerbreadDecoderEnabled;
    private boolean mKeepCancelledFetchAsLowPriority;
    private final Supplier<Boolean> mLazyDataSource;
    private final int mMaxBitmapSize;
    private final long mMemoryType;
    private final boolean mNativeCodeDisabled;
    private final boolean mPartialImageCachingEnabled;
    private final ProducerFactoryMethod mProducerFactoryMethod;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final boolean mUseBitmapPrepareToDraw;
    private final boolean mUseDownsamplingRatioForResizing;
    private final WebpBitmapFactory mWebpBitmapFactory;
    private final WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
    private final boolean mWebpSupportEnabled;

    /* loaded from: classes.dex */
    public static class Builder {
        public int mBitmapCloseableRefType;
        private final ImagePipelineConfig.Builder mConfigBuilder;
        public boolean mDownsampleIfLargeBitmap;
        public boolean mDownscaleFrameToDrawableDimensions;
        public boolean mExperimentalThreadHandoffQueueEnabled;
        public boolean mGingerbreadDecoderEnabled;
        private boolean mKeepCancelledFetchAsLowPriority;
        public Supplier<Boolean> mLazyDataSource;
        private ProducerFactoryMethod mProducerFactoryMethod;
        private WebpBitmapFactory mWebpBitmapFactory;
        private WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
        private boolean mWebpSupportEnabled = false;
        private boolean mDecodeCancellationEnabled = false;
        private boolean mUseDownsamplingRatioForResizing = false;
        private boolean mUseBitmapPrepareToDraw = false;
        private int mBitmapPrepareToDrawMinSizeBytes = 0;
        private int mBitmapPrepareToDrawMaxSizeBytes = 0;
        public boolean mBitmapPrepareToDrawForPrefetch = false;
        private int mMaxBitmapSize = 2048;
        private boolean mNativeCodeDisabled = false;
        private boolean mPartialImageCachingEnabled = false;
        public Supplier<Boolean> mSuppressBitmapPrefetchingSupplier = Suppliers.of(Boolean.FALSE);
        public long mMemoryType = 0;
        public boolean mEncodedCacheEnabled = true;

        public Builder(ImagePipelineConfig.Builder builder) {
            this.mConfigBuilder = builder;
        }

        public ImagePipelineExperiments build() {
            return new ImagePipelineExperiments(this);
        }

        public boolean isPartialImageCachingEnabled() {
            return this.mPartialImageCachingEnabled;
        }

        public ImagePipelineConfig.Builder setBitmapCloseableRefType(int i2) {
            this.mBitmapCloseableRefType = i2;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setBitmapPrepareToDraw(boolean z, int i2, int i3, boolean z2) {
            this.mUseBitmapPrepareToDraw = z;
            this.mBitmapPrepareToDrawMinSizeBytes = i2;
            this.mBitmapPrepareToDrawMaxSizeBytes = i3;
            this.mBitmapPrepareToDrawForPrefetch = z2;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setDecodeCancellationEnabled(boolean z) {
            this.mDecodeCancellationEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setDownsampleIfLargeBitmap(boolean z) {
            this.mDownsampleIfLargeBitmap = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setEncodedCacheEnabled(boolean z) {
            this.mEncodedCacheEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setExperimentalMemoryType(long j2) {
            this.mMemoryType = j2;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setExperimentalThreadHandoffQueueEnabled(boolean z) {
            this.mExperimentalThreadHandoffQueueEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setGingerbreadDecoderEnabled(boolean z) {
            this.mGingerbreadDecoderEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setKeepCancelledFetchAsLowPriority(boolean z) {
            this.mKeepCancelledFetchAsLowPriority = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setLazyDataSource(Supplier<Boolean> supplier) {
            this.mLazyDataSource = supplier;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setMaxBitmapSize(int i2) {
            this.mMaxBitmapSize = i2;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setNativeCodeDisabled(boolean z) {
            this.mNativeCodeDisabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setPartialImageCachingEnabled(boolean z) {
            this.mPartialImageCachingEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setProducerFactoryMethod(ProducerFactoryMethod producerFactoryMethod) {
            this.mProducerFactoryMethod = producerFactoryMethod;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setShouldDownscaleFrameToDrawableDimensions(boolean z) {
            this.mDownscaleFrameToDrawableDimensions = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setSuppressBitmapPrefetchingSupplier(Supplier<Boolean> supplier) {
            this.mSuppressBitmapPrefetchingSupplier = supplier;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setUseDownsampligRatioForResizing(boolean z) {
            this.mUseDownsamplingRatioForResizing = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory) {
            this.mWebpBitmapFactory = webpBitmapFactory;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
            this.mWebpErrorLogger = webpErrorLogger;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setWebpSupportEnabled(boolean z) {
            this.mWebpSupportEnabled = z;
            return this.mConfigBuilder;
        }
    }

    /* loaded from: classes.dex */
    public static class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
        @Override // com.facebook.imagepipeline.core.ImagePipelineExperiments.ProducerFactoryMethod
        public ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i2, int i3, boolean z4, int i4, CloseableReferenceFactory closeableReferenceFactory, boolean z5) {
            return new ProducerFactory(context, byteArrayPool, imageDecoder, progressiveJpegConfig, z, z2, z3, executorSupplier, pooledByteBufferFactory, memoryCache, memoryCache2, bufferedDiskCache, bufferedDiskCache2, cacheKeyFactory, platformBitmapFactory, i2, i3, z4, i4, closeableReferenceFactory, z5);
        }
    }

    /* loaded from: classes.dex */
    public interface ProducerFactoryMethod {
        ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i2, int i3, boolean z4, int i4, CloseableReferenceFactory closeableReferenceFactory, boolean z5);
    }

    private ImagePipelineExperiments(Builder builder) {
        this.mWebpSupportEnabled = builder.mWebpSupportEnabled;
        this.mWebpErrorLogger = builder.mWebpErrorLogger;
        this.mDecodeCancellationEnabled = builder.mDecodeCancellationEnabled;
        this.mWebpBitmapFactory = builder.mWebpBitmapFactory;
        this.mUseDownsamplingRatioForResizing = builder.mUseDownsamplingRatioForResizing;
        this.mUseBitmapPrepareToDraw = builder.mUseBitmapPrepareToDraw;
        this.mBitmapPrepareToDrawMinSizeBytes = builder.mBitmapPrepareToDrawMinSizeBytes;
        this.mBitmapPrepareToDrawMaxSizeBytes = builder.mBitmapPrepareToDrawMaxSizeBytes;
        this.mBitmapPrepareToDrawForPrefetch = builder.mBitmapPrepareToDrawForPrefetch;
        this.mMaxBitmapSize = builder.mMaxBitmapSize;
        this.mNativeCodeDisabled = builder.mNativeCodeDisabled;
        this.mPartialImageCachingEnabled = builder.mPartialImageCachingEnabled;
        this.mProducerFactoryMethod = builder.mProducerFactoryMethod == null ? new DefaultProducerFactoryMethod() : builder.mProducerFactoryMethod;
        this.mLazyDataSource = builder.mLazyDataSource;
        this.mGingerbreadDecoderEnabled = builder.mGingerbreadDecoderEnabled;
        this.mDownscaleFrameToDrawableDimensions = builder.mDownscaleFrameToDrawableDimensions;
        this.mBitmapCloseableRefType = builder.mBitmapCloseableRefType;
        this.mSuppressBitmapPrefetchingSupplier = builder.mSuppressBitmapPrefetchingSupplier;
        this.mExperimentalThreadHandoffQueueEnabled = builder.mExperimentalThreadHandoffQueueEnabled;
        this.mMemoryType = builder.mMemoryType;
        this.mKeepCancelledFetchAsLowPriority = builder.mKeepCancelledFetchAsLowPriority;
        this.mDownsampleIfLargeBitmap = builder.mDownsampleIfLargeBitmap;
        this.mEncodedCacheEnabled = builder.mEncodedCacheEnabled;
    }

    public static Builder newBuilder(ImagePipelineConfig.Builder builder) {
        return new Builder(builder);
    }

    public int getBitmapCloseableRefType() {
        return this.mBitmapCloseableRefType;
    }

    public boolean getBitmapPrepareToDrawForPrefetch() {
        return this.mBitmapPrepareToDrawForPrefetch;
    }

    public int getBitmapPrepareToDrawMaxSizeBytes() {
        return this.mBitmapPrepareToDrawMaxSizeBytes;
    }

    public int getBitmapPrepareToDrawMinSizeBytes() {
        return this.mBitmapPrepareToDrawMinSizeBytes;
    }

    public int getMaxBitmapSize() {
        return this.mMaxBitmapSize;
    }

    public long getMemoryType() {
        return this.mMemoryType;
    }

    public ProducerFactoryMethod getProducerFactoryMethod() {
        return this.mProducerFactoryMethod;
    }

    public Supplier<Boolean> getSuppressBitmapPrefetchingSupplier() {
        return this.mSuppressBitmapPrefetchingSupplier;
    }

    public boolean getUseBitmapPrepareToDraw() {
        return this.mUseBitmapPrepareToDraw;
    }

    public boolean getUseDownsamplingRatioForResizing() {
        return this.mUseDownsamplingRatioForResizing;
    }

    public WebpBitmapFactory getWebpBitmapFactory() {
        return this.mWebpBitmapFactory;
    }

    public WebpBitmapFactory.WebpErrorLogger getWebpErrorLogger() {
        return this.mWebpErrorLogger;
    }

    public boolean isDecodeCancellationEnabled() {
        return this.mDecodeCancellationEnabled;
    }

    public boolean isEncodedCacheEnabled() {
        return this.mEncodedCacheEnabled;
    }

    public boolean isExperimentalThreadHandoffQueueEnabled() {
        return this.mExperimentalThreadHandoffQueueEnabled;
    }

    public boolean isGingerbreadDecoderEnabled() {
        return this.mGingerbreadDecoderEnabled;
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public boolean isNativeCodeDisabled() {
        return this.mNativeCodeDisabled;
    }

    public boolean isPartialImageCachingEnabled() {
        return this.mPartialImageCachingEnabled;
    }

    public boolean isWebpSupportEnabled() {
        return this.mWebpSupportEnabled;
    }

    public boolean shouldDownsampleIfLargeBitmap() {
        return this.mDownsampleIfLargeBitmap;
    }

    public boolean shouldDownscaleFrameToDrawableDimensions() {
        return this.mDownscaleFrameToDrawableDimensions;
    }

    public boolean shouldKeepCancelledFetchAsLowPriority() {
        return this.mKeepCancelledFetchAsLowPriority;
    }
}
