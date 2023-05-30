package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import f.d;
import f.f;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class DiskCacheReadProducer implements Producer<EncodedImage> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "DiskCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public DiskCacheReadProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    @VisibleForTesting
    @Nullable
    static Map<String, String> getExtraMap(ProducerListener2 producerListener2, ProducerContext producerContext, boolean z, int i2) {
        if (producerListener2.requiresExtraMap(producerContext, PRODUCER_NAME)) {
            String valueOf = String.valueOf(z);
            return z ? ImmutableMap.of("cached_value_found", valueOf, "encodedImageSize", String.valueOf(i2)) : ImmutableMap.of("cached_value_found", valueOf);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isTaskCancelled(f<?> fVar) {
        return fVar.r() || (fVar.t() && (fVar.o() instanceof CancellationException));
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
            consumer.onNewResult(null, 1);
        } else {
            this.mInputProducer.produceResults(consumer, producerContext);
        }
    }

    private d<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        producerContext.getProducerListener();
        return new d<EncodedImage, Void>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0009: RETURN 
              (wrap: f.d<com.facebook.imagepipeline.image.EncodedImage, java.lang.Void> : 0x0006: CONSTRUCTOR 
              (r2v0 'this' com.facebook.imagepipeline.producers.DiskCacheReadProducer A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.facebook.imagepipeline.producers.ProducerListener2 A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext A[DONT_INLINE])
              (r3v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage> A[DONT_INLINE])
             A[MD:(com.facebook.imagepipeline.producers.DiskCacheReadProducer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.producers.Consumer):void (m), WRAPPED] call: com.facebook.imagepipeline.producers.DiskCacheReadProducer.1.<init>(com.facebook.imagepipeline.producers.DiskCacheReadProducer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.producers.Consumer):void type: CONSTRUCTOR)
             in method: com.facebook.imagepipeline.producers.DiskCacheReadProducer.onFinishDiskReads(com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>, com.facebook.imagepipeline.producers.ProducerContext):f.d<com.facebook.imagepipeline.image.EncodedImage, java.lang.Void>, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            com.facebook.imagepipeline.producers.ProducerListener2 r0 = r4.getProducerListener()
            com.facebook.imagepipeline.producers.DiskCacheReadProducer$1 r1 = new com.facebook.imagepipeline.producers.DiskCacheReadProducer$1
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DiskCacheReadProducer.onFinishDiskReads(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):f.d");
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.DiskCacheReadProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                atomicBoolean.set(true);
            }
        });
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        if (!imageRequest.isDiskCacheEnabled()) {
            maybeStartInputProducer(consumer, producerContext);
            return;
        }
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
        BufferedDiskCache bufferedDiskCache = imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        bufferedDiskCache.get(encodedCacheKey, atomicBoolean).g(onFinishDiskReads(consumer, producerContext));
        subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
    }
}
