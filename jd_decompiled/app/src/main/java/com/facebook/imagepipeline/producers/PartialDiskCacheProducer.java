package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jd.dynamic.DYConstants;
import f.d;
import f.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class PartialDiskCacheProducer implements Producer<EncodedImage> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "PartialDiskCacheProducer";
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PartialDiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private static final int READ_SIZE = 16384;
        private final ByteArrayPool mByteArrayPool;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        @Nullable
        private final EncodedImage mPartialEncodedImageFromCache;
        private final CacheKey mPartialImageCacheKey;
        private final PooledByteBufferFactory mPooledByteBufferFactory;

        private PartialDiskCacheConsumer(Consumer<EncodedImage> consumer, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, @Nullable EncodedImage encodedImage) {
            super(consumer);
            this.mDefaultBufferedDiskCache = bufferedDiskCache;
            this.mPartialImageCacheKey = cacheKey;
            this.mPooledByteBufferFactory = pooledByteBufferFactory;
            this.mByteArrayPool = byteArrayPool;
            this.mPartialEncodedImageFromCache = encodedImage;
        }

        private void copy(InputStream inputStream, OutputStream outputStream, int i2) {
            byte[] bArr = this.mByteArrayPool.get(16384);
            int i3 = i2;
            while (i3 > 0) {
                try {
                    int read = inputStream.read(bArr, 0, Math.min(16384, i3));
                    if (read < 0) {
                        break;
                    } else if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i3 -= read;
                    }
                } finally {
                    this.mByteArrayPool.release(bArr);
                }
            }
            if (i3 > 0) {
                throw new IOException(String.format(null, "Failed to read %d bytes - finished %d short", Integer.valueOf(i2), Integer.valueOf(i3)));
            }
        }

        private PooledByteBufferOutputStream merge(EncodedImage encodedImage, EncodedImage encodedImage2) {
            PooledByteBufferOutputStream newOutputStream = this.mPooledByteBufferFactory.newOutputStream(encodedImage2.getSize() + encodedImage2.getBytesRange().from);
            copy(encodedImage.getInputStream(), newOutputStream, encodedImage2.getBytesRange().from);
            copy(encodedImage2.getInputStream(), newOutputStream, encodedImage2.getSize());
            return newOutputStream;
        }

        private void sendFinalResultToConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream) {
            Throwable th;
            EncodedImage encodedImage;
            CloseableReference of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
            try {
                encodedImage = new EncodedImage(of);
                try {
                    encodedImage.parseMetaData();
                    getConsumer().onNewResult(encodedImage, 1);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(of);
                } catch (Throwable th2) {
                    th = th2;
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(of);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                encodedImage = null;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [com.facebook.imagepipeline.image.EncodedImage, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v1, types: [com.facebook.imagepipeline.image.EncodedImage] */
        /* JADX WARN: Type inference failed for: r3v4, types: [com.facebook.imagepipeline.cache.BufferedDiskCache] */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(EncodedImage encodedImage, int i2) {
            if (BaseConsumer.isNotLast(i2)) {
                return;
            }
            if (this.mPartialEncodedImageFromCache != null) {
                try {
                    if (encodedImage.getBytesRange() != null) {
                        try {
                            sendFinalResultToConsumer(merge(this.mPartialEncodedImageFromCache, encodedImage));
                        } catch (IOException e2) {
                            FLog.e(PartialDiskCacheProducer.PRODUCER_NAME, "Error while merging image data", e2);
                            getConsumer().onFailure(e2);
                        }
                        encodedImage.close();
                        this.mPartialEncodedImageFromCache.close();
                        encodedImage = this.mDefaultBufferedDiskCache;
                        encodedImage.remove(this.mPartialImageCacheKey);
                        return;
                    }
                } catch (Throwable th) {
                    encodedImage.close();
                    this.mPartialEncodedImageFromCache.close();
                    throw th;
                }
            }
            if (BaseConsumer.statusHasFlag(i2, 8) && BaseConsumer.isLast(i2) && encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
                this.mDefaultBufferedDiskCache.put(this.mPartialImageCacheKey, encodedImage);
            }
            getConsumer().onNewResult(encodedImage, i2);
        }
    }

    public PartialDiskCacheProducer(BufferedDiskCache bufferedDiskCache, CacheKeyFactory cacheKeyFactory, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mInputProducer = producer;
    }

    private static Uri createUriForPartialCacheKey(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial", DYConstants.DY_TRUE).build();
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

    private d<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> consumer, final ProducerContext producerContext, final CacheKey cacheKey) {
        producerContext.getProducerListener();
        return new d<EncodedImage, Void>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: RETURN 
              (wrap: f.d<com.facebook.imagepipeline.image.EncodedImage, java.lang.Void> : 0x000b: CONSTRUCTOR 
              (r7v0 'this' com.facebook.imagepipeline.producers.PartialDiskCacheProducer A[IMMUTABLE_TYPE, THIS])
              (r2 I:com.facebook.imagepipeline.producers.ProducerListener2 A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r9v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext A[DONT_INLINE])
              (r8v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage> A[DONT_INLINE])
              (r10v0 'cacheKey' com.facebook.cache.common.CacheKey A[DONT_INLINE])
             A[MD:(com.facebook.imagepipeline.producers.PartialDiskCacheProducer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.producers.Consumer, com.facebook.cache.common.CacheKey):void (m), WRAPPED] call: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.1.<init>(com.facebook.imagepipeline.producers.PartialDiskCacheProducer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.producers.Consumer, com.facebook.cache.common.CacheKey):void type: CONSTRUCTOR)
             in method: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.onFinishDiskReads(com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.cache.common.CacheKey):f.d<com.facebook.imagepipeline.image.EncodedImage, java.lang.Void>, file: classes.dex
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
            com.facebook.imagepipeline.producers.ProducerListener2 r2 = r9.getProducerListener()
            com.facebook.imagepipeline.producers.PartialDiskCacheProducer$1 r6 = new com.facebook.imagepipeline.producers.PartialDiskCacheProducer$1
            r0 = r6
            r1 = r7
            r3 = r9
            r4 = r8
            r5 = r10
            r0.<init>()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.onFinishDiskReads(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.cache.common.CacheKey):f.d");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInputProducer(Consumer<EncodedImage> consumer, ProducerContext producerContext, CacheKey cacheKey, @Nullable EncodedImage encodedImage) {
        this.mInputProducer.produceResults(new PartialDiskCacheConsumer(consumer, this.mDefaultBufferedDiskCache, cacheKey, this.mPooledByteBufferFactory, this.mByteArrayPool, encodedImage), producerContext);
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.2
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
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, createUriForPartialCacheKey(imageRequest), producerContext.getCallerContext());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.mDefaultBufferedDiskCache.get(encodedCacheKey, atomicBoolean).g(onFinishDiskReads(consumer, producerContext, encodedCacheKey));
        subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
    }
}
