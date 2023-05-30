package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import f.d;
import f.f;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;
    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final ImagePipelineConfig mConfig;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final Supplier<Boolean> mLazyDataSource;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final RequestListener2 mRequestListener2;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$9 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice = iArr;
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Set<RequestListener2> set2, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> supplier2, Supplier<Boolean> supplier3, @Nullable CallerContextVerifier callerContextVerifier, ImagePipelineConfig imagePipelineConfig) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mRequestListener2 = new ForwardingRequestListener2(set2);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mSuppressBitmapPrefetchingSupplier = supplier2;
        this.mLazyDataSource = supplier3;
        this.mCallerContextVerifier = callerContextVerifier;
        this.mConfig = imagePipelineConfig;
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.8
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return cacheKey.containsUri(uri);
            }
        };
    }

    private DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object obj, Priority priority) {
        if (this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            try {
                Boolean shouldDecodePrefetches = imageRequest.shouldDecodePrefetches();
                return submitPrefetchRequest(shouldDecodePrefetches != null ? !shouldDecodePrefetches.booleanValue() : this.mSuppressBitmapPrefetchingSupplier.get().booleanValue() ? this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest) : this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority);
            } catch (Exception e2) {
                return DataSources.immediateFailedDataSource(e2);
            }
        }
        return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        boolean z;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, false);
        }
        try {
            try {
                ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel);
                String generateUniqueFutureId = generateUniqueFutureId();
                if (!imageRequest.getProgressiveRenderingEnabled() && UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                    z = false;
                    DataSource<CloseableReference<T>> create = CloseableProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId, str, internalRequestListener, obj, max, false, z, imageRequest.getPriority(), this.mConfig), internalRequestListener);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return create;
                }
                z = true;
                DataSource<CloseableReference<T>> create2 = CloseableProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId, str, internalRequestListener, obj, max, false, z, imageRequest.getPriority(), this.mConfig), internalRequestListener);
                if (FrescoSystrace.isTracing()) {
                }
                return create2;
            } catch (Exception e2) {
                DataSource<CloseableReference<T>> immediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, Object obj, Priority priority) {
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, null), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, true);
        }
        try {
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), internalRequestListener, obj, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), true, false, priority, this.mConfig), internalRequestListener);
        } catch (Exception e2) {
            return DataSources.immediateFailedDataSource(e2);
        }
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.5
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return true;
            }
        };
        this.mBitmapMemoryCache.removeAll(predicate);
        this.mEncodedMemoryCache.removeAll(predicate);
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache(ImageRequest.fromUri(uri));
    }

    public void evictFromDiskCache(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        this.mMainBufferedDiskCache.remove(encodedCacheKey);
        this.mSmallImageBufferedDiskCache.remove(encodedCacheKey);
    }

    public void evictFromMemoryCache(Uri uri) {
        Predicate<CacheKey> predicateForUri = predicateForUri(uri);
        this.mBitmapMemoryCache.removeAll(predicateForUri);
        this.mEncodedMemoryCache.removeAll(predicateForUri);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str);
        } catch (Exception e2) {
            return DataSources.immediateFailedDataSource(e2);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object obj) {
        return fetchEncodedImage(imageRequest, obj, null);
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object obj, @Nullable RequestListener requestListener) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, requestListener, null);
        } catch (Exception e2) {
            return DataSources.immediateFailedDataSource(e2);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.mBitmapMemoryCache;
    }

    @Nullable
    public CacheKey getCacheKey(@Nullable ImageRequest imageRequest, Object obj) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        }
        CacheKeyFactory cacheKeyFactory = this.mCacheKeyFactory;
        CacheKey cacheKey = null;
        if (cacheKeyFactory != null && imageRequest != null) {
            cacheKey = imageRequest.getPostprocessor() != null ? cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, obj) : cacheKeyFactory.getBitmapCacheKey(imageRequest, obj);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return cacheKey;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    @Nullable
    public CloseableReference<CloseableImage> getCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
            return closeableReference;
        }
        closeableReference.close();
        return null;
    }

    public ImagePipelineConfig getConfig() {
        return this.mConfig;
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object obj, final ImageRequest.RequestLevel requestLevel) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.1
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.2
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener, @Nullable final String str) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.3
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, str);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, final Object obj) {
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.4
            {
                ImagePipeline.this = this;
            }

            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, obj);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
            }
        };
    }

    public ProducerSequenceFactory getProducerSequenceFactory() {
        return this.mProducerSequenceFactory;
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, @Nullable RequestListener requestListener) {
        return requestListener == null ? imageRequest.getRequestListener() == null ? this.mRequestListener : new ForwardingRequestListener(this.mRequestListener, imageRequest.getRequestListener()) : imageRequest.getRequestListener() == null ? new ForwardingRequestListener(this.mRequestListener, requestListener) : new ForwardingRequestListener(this.mRequestListener, requestListener, imageRequest.getRequestListener());
    }

    public long getUsedDiskCacheSize() {
        return this.mMainBufferedDiskCache.getSize() + this.mSmallImageBufferedDiskCache.getSize();
    }

    public boolean hasCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return false;
        }
        return memoryCache.contains((MemoryCache<CacheKey, CloseableImage>) cacheKey);
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache(ImageRequest.fromUri(uri));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource create = SimpleDataSource.create();
        this.mMainBufferedDiskCache.contains(encodedCacheKey).j(new d<Boolean, f<Boolean>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.7
            {
                ImagePipeline.this = this;
            }

            @Override // f.d
            public f<Boolean> then(f<Boolean> fVar) {
                return (fVar.r() || fVar.t() || !fVar.p().booleanValue()) ? ImagePipeline.this.mSmallImageBufferedDiskCache.contains(encodedCacheKey) : f.n(Boolean.TRUE);
            }
        }).g(new d<Boolean, Void>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.6
            {
                ImagePipeline.this = this;
            }

            @Override // f.d
            public Void then(f<Boolean> fVar) {
                create.setResult(Boolean.valueOf((fVar.r() || fVar.t() || !fVar.p().booleanValue()) ? false : true));
                return null;
            }
        });
        return create;
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build());
    }

    public boolean isInDiskCacheSync(ImageRequest imageRequest) {
        BufferedDiskCache bufferedDiskCache;
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        int i2 = AnonymousClass9.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[imageRequest.getCacheChoice().ordinal()];
        if (i2 == 1) {
            bufferedDiskCache = this.mMainBufferedDiskCache;
        } else if (i2 != 2) {
            return false;
        } else {
            bufferedDiskCache = this.mSmallImageBufferedDiskCache;
        }
        return bufferedDiskCache.diskCheckSync(encodedCacheKey);
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public boolean isPaused() {
        return this.mThreadHandoffProducerQueue.isQueueing();
    }

    public void pause() {
        this.mThreadHandoffProducerQueue.startQueueing();
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object obj) {
        return prefetchToBitmapCache(imageRequest, obj, Priority.MEDIUM);
    }

    @Deprecated
    public DataSource<Void> prefetchToBitmapCacheWithHighPriority(ImageRequest imageRequest, Object obj) {
        return prefetchToBitmapCache(imageRequest, obj, Priority.HIGH);
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object obj) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object obj, Priority priority) {
        if (this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            try {
                return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority);
            } catch (Exception e2) {
                return DataSources.immediateFailedDataSource(e2);
            }
        }
        return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj, Priority priority) {
        if (this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            try {
                return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority);
            } catch (Exception e2) {
                return DataSources.immediateFailedDataSource(e2);
            }
        }
        return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
    }

    public void resume() {
        this.mThreadHandoffProducerQueue.stopQueuing();
    }

    public <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        try {
            try {
                DataSource<CloseableReference<T>> create = CloseableProducerToDataSourceAdapter.create(producer, settableProducerContext, new InternalRequestListener(requestListener, this.mRequestListener2));
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return create;
            } catch (Exception e2) {
                DataSource<CloseableReference<T>> immediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }
}
