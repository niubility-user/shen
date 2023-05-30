package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int i2) {
        Throwable th;
        CloseableReference closeableReference;
        try {
            closeableReference = CloseableReference.of(i2 <= 0 ? this.mPooledByteBufferFactory.newByteBuffer(inputStream) : this.mPooledByteBufferFactory.newByteBuffer(inputStream, i2));
            try {
                EncodedImage encodedImage = new EncodedImage(closeableReference);
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely(closeableReference);
                return encodedImage;
            } catch (Throwable th2) {
                th = th2;
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely(closeableReference);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            closeableReference = null;
        }
    }

    protected abstract EncodedImage getEncodedImage(ImageRequest imageRequest);

    /* JADX INFO: Access modifiers changed from: protected */
    public EncodedImage getEncodedImage(InputStream inputStream, int i2) {
        return getByteBufferBackedEncodedImage(inputStream, i2);
    }

    protected abstract String getProducerName();

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        producerContext.getImageRequest();
        final StatefulProducerRunnable<EncodedImage> statefulProducerRunnable = new StatefulProducerRunnable<EncodedImage>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: CONSTRUCTOR (r9v0 'statefulProducerRunnable' com.facebook.imagepipeline.producers.StatefulProducerRunnable<com.facebook.imagepipeline.image.EncodedImage>) = 
              (r10v0 'this' com.facebook.imagepipeline.producers.LocalFetchProducer A[IMMUTABLE_TYPE, THIS])
              (r11v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>)
              (r7v0 'producerListener' com.facebook.imagepipeline.producers.ProducerListener2)
              (r12v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext)
              (wrap: java.lang.String : 0x000a: INVOKE (r10v0 'this' com.facebook.imagepipeline.producers.LocalFetchProducer A[IMMUTABLE_TYPE, THIS]) type: VIRTUAL call: com.facebook.imagepipeline.producers.LocalFetchProducer.getProducerName():java.lang.String A[MD:():java.lang.String (m), WRAPPED])
              (r6 I:com.facebook.imagepipeline.request.ImageRequest A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r7v0 'producerListener' com.facebook.imagepipeline.producers.ProducerListener2 A[DONT_INLINE])
              (r12v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext A[DONT_INLINE])
             A[DECLARE_VAR, MD:(com.facebook.imagepipeline.producers.LocalFetchProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext):void (m)] call: com.facebook.imagepipeline.producers.LocalFetchProducer.1.<init>(com.facebook.imagepipeline.producers.LocalFetchProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext):void type: CONSTRUCTOR in method: com.facebook.imagepipeline.producers.LocalFetchProducer.produceResults(com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>, com.facebook.imagepipeline.producers.ProducerContext):void, file: classes.dex
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.facebook.imagepipeline.producers.ProducerListener2 r7 = r12.getProducerListener()
            com.facebook.imagepipeline.request.ImageRequest r6 = r12.getImageRequest()
            com.facebook.imagepipeline.producers.LocalFetchProducer$1 r9 = new com.facebook.imagepipeline.producers.LocalFetchProducer$1
            java.lang.String r5 = r10.getProducerName()
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r7
            r4 = r12
            r8 = r12
            r0.<init>(r2, r3, r4, r5)
            com.facebook.imagepipeline.producers.LocalFetchProducer$2 r11 = new com.facebook.imagepipeline.producers.LocalFetchProducer$2
            r11.<init>()
            r12.addCallbacks(r11)
            java.util.concurrent.Executor r11 = r10.mExecutor
            r11.execute(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalFetchProducer.produceResults(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }
}
