package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

/* loaded from: classes.dex */
public class DiskCacheWriteProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "DiskCacheWriteProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DiskCacheWriteConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final CacheKeyFactory mCacheKeyFactory;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        private final ProducerContext mProducerContext;
        private final BufferedDiskCache mSmallImageBufferedDiskCache;

        private DiskCacheWriteConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDefaultBufferedDiskCache = bufferedDiskCache;
            this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
            this.mCacheKeyFactory = cacheKeyFactory;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(EncodedImage encodedImage, int i2) {
            this.mProducerContext.getProducerListener().onProducerStart(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME);
            if (!BaseConsumer.isNotLast(i2) && encodedImage != null && !BaseConsumer.statusHasAnyFlag(i2, 10) && encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
                ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                (imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache).put(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext()), encodedImage);
            }
            this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME, null);
            getConsumer().onNewResult(encodedImage, i2);
        }
    }

    public DiskCacheWriteProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
            consumer.onNewResult(null, 1);
            return;
        }
        if (producerContext.getImageRequest().isDiskCacheEnabled()) {
            consumer = new DiskCacheWriteConsumer(consumer, producerContext, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory);
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        maybeStartInputProducer(consumer, producerContext);
    }
}
