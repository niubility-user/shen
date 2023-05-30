package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.nativecode.WebpTranscoderFactory;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpTranscodeProducer implements Producer<EncodedImage> {
    private static final int DEFAULT_JPEG_QUALITY = 80;
    public static final String PRODUCER_NAME = "WebpTranscodeProducer";
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* loaded from: classes.dex */
    private class WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mContext;
        private TriState mShouldTranscodeWhenFinished;

        public WebpTranscodeConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mContext = producerContext;
            this.mShouldTranscodeWhenFinished = TriState.UNSET;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage encodedImage, int i2) {
            if (this.mShouldTranscodeWhenFinished == TriState.UNSET && encodedImage != null) {
                this.mShouldTranscodeWhenFinished = WebpTranscodeProducer.shouldTranscode(encodedImage);
            }
            if (this.mShouldTranscodeWhenFinished == TriState.NO) {
                getConsumer().onNewResult(encodedImage, i2);
            } else if (BaseConsumer.isLast(i2)) {
                if (this.mShouldTranscodeWhenFinished != TriState.YES || encodedImage == null) {
                    getConsumer().onNewResult(encodedImage, i2);
                } else {
                    WebpTranscodeProducer.this.transcodeLastResult(encodedImage, getConsumer(), this.mContext);
                }
            }
        }
    }

    public WebpTranscodeProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> producer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doTranscode(EncodedImage encodedImage, PooledByteBufferOutputStream pooledByteBufferOutputStream) {
        ImageFormat imageFormat;
        InputStream inputStream = encodedImage.getInputStream();
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(inputStream);
        if (imageFormat_WrapIOException == DefaultImageFormats.WEBP_SIMPLE || imageFormat_WrapIOException == DefaultImageFormats.WEBP_EXTENDED) {
            WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(inputStream, pooledByteBufferOutputStream, 80);
            imageFormat = DefaultImageFormats.JPEG;
        } else if (imageFormat_WrapIOException != DefaultImageFormats.WEBP_LOSSLESS && imageFormat_WrapIOException != DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
            throw new IllegalArgumentException("Wrong image format");
        } else {
            WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(inputStream, pooledByteBufferOutputStream);
            imageFormat = DefaultImageFormats.PNG;
        }
        encodedImage.setImageFormat(imageFormat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TriState shouldTranscode(EncodedImage encodedImage) {
        Preconditions.checkNotNull(encodedImage);
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        if (!DefaultImageFormats.isStaticWebpFormat(imageFormat_WrapIOException)) {
            return imageFormat_WrapIOException == ImageFormat.UNKNOWN ? TriState.UNSET : TriState.NO;
        }
        return WebpTranscoderFactory.getWebpTranscoder() == null ? TriState.NO : TriState.valueOf(!r0.isWebpNativelySupported(imageFormat_WrapIOException));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transcodeLastResult(EncodedImage encodedImage, Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Preconditions.checkNotNull(encodedImage);
        EncodedImage.cloneOrNull(encodedImage);
        this.mExecutor.execute(new StatefulProducerRunnable<EncodedImage>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0018: INVOKE 
              (wrap: java.util.concurrent.Executor : 0x0016: IGET (r7v0 'this' com.facebook.imagepipeline.producers.WebpTranscodeProducer A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] com.facebook.imagepipeline.producers.WebpTranscodeProducer.mExecutor java.util.concurrent.Executor)
              (wrap: com.facebook.imagepipeline.producers.StatefulProducerRunnable<com.facebook.imagepipeline.image.EncodedImage> : 0x0013: CONSTRUCTOR 
              (r7v0 'this' com.facebook.imagepipeline.producers.WebpTranscodeProducer A[IMMUTABLE_TYPE, THIS])
              (r9v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>)
              (wrap: com.facebook.imagepipeline.producers.ProducerListener2 : 0x0009: INVOKE (r10v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext) type: INTERFACE call: com.facebook.imagepipeline.producers.ProducerContext.getProducerListener():com.facebook.imagepipeline.producers.ProducerListener2 A[MD:():com.facebook.imagepipeline.producers.ProducerListener2 (m), WRAPPED])
              (r10v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext)
              (wrap: java.lang.String : SGET  A[WRAPPED] com.facebook.imagepipeline.producers.WebpTranscodeProducer.PRODUCER_NAME java.lang.String)
              (r6 I:com.facebook.imagepipeline.image.EncodedImage A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.facebook.imagepipeline.producers.WebpTranscodeProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.image.EncodedImage):void (m), WRAPPED] call: com.facebook.imagepipeline.producers.WebpTranscodeProducer.1.<init>(com.facebook.imagepipeline.producers.WebpTranscodeProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.image.EncodedImage):void type: CONSTRUCTOR)
             type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] in method: com.facebook.imagepipeline.producers.WebpTranscodeProducer.transcodeLastResult(com.facebook.imagepipeline.image.EncodedImage, com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>, com.facebook.imagepipeline.producers.ProducerContext):void, file: classes.dex
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
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            com.facebook.imagepipeline.image.EncodedImage r6 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r8)
            com.facebook.imagepipeline.producers.WebpTranscodeProducer$1 r8 = new com.facebook.imagepipeline.producers.WebpTranscodeProducer$1
            com.facebook.imagepipeline.producers.ProducerListener2 r3 = r10.getProducerListener()
            java.lang.String r5 = "WebpTranscodeProducer"
            r0 = r8
            r1 = r7
            r2 = r9
            r4 = r10
            r0.<init>(r2, r3, r4, r5)
            java.util.concurrent.Executor r9 = r7.mExecutor
            r9.execute(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.WebpTranscodeProducer.transcodeLastResult(com.facebook.imagepipeline.image.EncodedImage, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new WebpTranscodeConsumer(consumer, producerContext), producerContext);
    }
}
