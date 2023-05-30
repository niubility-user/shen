package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class EncodedMemoryCacheProducer implements Producer<EncodedImage> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "EncodedMemoryCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<EncodedImage> mInputProducer;
    private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;

    /* loaded from: classes.dex */
    private static class EncodedMemoryCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final boolean mEncodedCacheEnabled;
        private final boolean mIsMemoryCacheEnabled;
        private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;
        private final CacheKey mRequestedCacheKey;

        public EncodedMemoryCacheConsumer(Consumer<EncodedImage> consumer, MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKey cacheKey, boolean z, boolean z2) {
            super(consumer);
            this.mMemoryCache = memoryCache;
            this.mRequestedCacheKey = cacheKey;
            this.mIsMemoryCacheEnabled = z;
            this.mEncodedCacheEnabled = z2;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(EncodedImage encodedImage, int i2) {
            boolean isTracing;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedMemoryCacheProducer#onNewResultImpl");
                }
                if (!BaseConsumer.isNotLast(i2) && encodedImage != null && !BaseConsumer.statusHasAnyFlag(i2, 10) && encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
                    CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
                    if (byteBufferRef != null) {
                        CloseableReference<PooledByteBuffer> closeableReference = null;
                        if (this.mEncodedCacheEnabled && this.mIsMemoryCacheEnabled) {
                            closeableReference = this.mMemoryCache.cache(this.mRequestedCacheKey, byteBufferRef);
                        }
                        CloseableReference.closeSafely(byteBufferRef);
                        if (closeableReference != null) {
                            EncodedImage encodedImage2 = new EncodedImage(closeableReference);
                            encodedImage2.copyMetaDataFrom(encodedImage);
                            CloseableReference.closeSafely(closeableReference);
                            getConsumer().onProgressUpdate(1.0f);
                            getConsumer().onNewResult(encodedImage2, i2);
                            EncodedImage.closeSafely(encodedImage2);
                            if (isTracing) {
                                return;
                            }
                            return;
                        }
                    }
                    getConsumer().onNewResult(encodedImage, i2);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                        return;
                    }
                    return;
                }
                getConsumer().onNewResult(encodedImage, i2);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } finally {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        }
    }

    public EncodedMemoryCacheProducer(MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("EncodedMemoryCacheProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, PRODUCER_NAME);
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            CloseableReference<PooledByteBuffer> closeableReference = this.mMemoryCache.get(encodedCacheKey);
            if (closeableReference != null) {
                EncodedImage encodedImage = new EncodedImage(closeableReference);
                try {
                    producerListener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, producerListener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", DYConstants.DY_TRUE) : null);
                    producerListener.onUltimateProducerReached(producerContext, PRODUCER_NAME, true);
                    producerContext.setExtra(1, "memory_encoded");
                    consumer.onProgressUpdate(1.0f);
                    consumer.onNewResult(encodedImage, 1);
                    CloseableReference.closeSafely(closeableReference);
                } finally {
                    EncodedImage.closeSafely(encodedImage);
                }
            } else if (producerContext.getLowestPermittedRequestLevel().getValue() < ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
                EncodedMemoryCacheConsumer encodedMemoryCacheConsumer = new EncodedMemoryCacheConsumer(consumer, this.mMemoryCache, encodedCacheKey, producerContext.getImageRequest().isMemoryCacheEnabled(), producerContext.getImagePipelineConfig().getExperiments().isEncodedCacheEnabled());
                producerListener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, producerListener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", DYConstants.DY_FALSE) : null);
                this.mInputProducer.produceResults(encodedMemoryCacheConsumer, producerContext);
                CloseableReference.closeSafely(closeableReference);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } else {
                producerListener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, producerListener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", DYConstants.DY_FALSE) : null);
                producerListener.onUltimateProducerReached(producerContext, PRODUCER_NAME, false);
                producerContext.setExtra(1, "memory_encoded");
                consumer.onNewResult(null, 1);
                CloseableReference.closeSafely(closeableReference);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }
}
