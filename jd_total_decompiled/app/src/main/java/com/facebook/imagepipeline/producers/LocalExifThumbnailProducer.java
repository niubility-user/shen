package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import com.facebook.soloader.DoNotOptimize;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage> {
    private static final int COMMON_EXIF_THUMBNAIL_MAX_DIMENSION = 512;
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* JADX INFO: Access modifiers changed from: private */
    @DoNotOptimize
    /* loaded from: classes.dex */
    public class Api24Utils {
        private Api24Utils() {
        }

        ExifInterface getExifInterface(FileDescriptor fileDescriptor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return new ExifInterface(fileDescriptor);
            }
            return null;
        }
    }

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EncodedImage buildEncodedImage(PooledByteBuffer pooledByteBuffer, ExifInterface exifInterface) {
        Pair<Integer, Integer> decodeDimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(pooledByteBuffer));
        int rotationAngle = getRotationAngle(exifInterface);
        int intValue = decodeDimensions != null ? ((Integer) decodeDimensions.first).intValue() : -1;
        int intValue2 = decodeDimensions != null ? ((Integer) decodeDimensions.second).intValue() : -1;
        CloseableReference of = CloseableReference.of(pooledByteBuffer);
        try {
            EncodedImage encodedImage = new EncodedImage(of);
            CloseableReference.closeSafely(of);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            encodedImage.setRotationAngle(rotationAngle);
            encodedImage.setWidth(intValue);
            encodedImage.setHeight(intValue2);
            return encodedImage;
        } catch (Throwable th) {
            CloseableReference.closeSafely(of);
            throw th;
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }

    @Override // com.facebook.imagepipeline.producers.ThumbnailProducer
    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }

    @VisibleForTesting
    boolean canReadAsFile(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.canRead();
    }

    @VisibleForTesting
    @Nullable
    ExifInterface getExifInterface(Uri uri) {
        String realPathFromUri = UriUtil.getRealPathFromUri(this.mContentResolver, uri);
        try {
        } catch (IOException unused) {
        } catch (StackOverflowError unused2) {
            FLog.e(LocalExifThumbnailProducer.class, "StackOverflowError in ExifInterface constructor");
        }
        if (canReadAsFile(realPathFromUri)) {
            return new ExifInterface(realPathFromUri);
        }
        AssetFileDescriptor assetFileDescriptor = UriUtil.getAssetFileDescriptor(this.mContentResolver, uri);
        if (assetFileDescriptor != null && Build.VERSION.SDK_INT >= 24) {
            ExifInterface exifInterface = new Api24Utils().getExifInterface(assetFileDescriptor.getFileDescriptor());
            assetFileDescriptor.close();
            return exifInterface;
        }
        return null;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        producerContext.getImageRequest();
        final StatefulProducerRunnable<EncodedImage> statefulProducerRunnable = new StatefulProducerRunnable<EncodedImage>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: CONSTRUCTOR (r7v0 'statefulProducerRunnable' com.facebook.imagepipeline.producers.StatefulProducerRunnable<com.facebook.imagepipeline.image.EncodedImage>) = 
              (r8v0 'this' com.facebook.imagepipeline.producers.LocalExifThumbnailProducer A[IMMUTABLE_TYPE, THIS])
              (r9v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>)
              (r3v0 'producerListener' com.facebook.imagepipeline.producers.ProducerListener2)
              (r10v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext)
              (wrap: java.lang.String : SGET  A[WRAPPED] com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.PRODUCER_NAME java.lang.String)
              (r6 I:com.facebook.imagepipeline.request.ImageRequest A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.facebook.imagepipeline.producers.LocalExifThumbnailProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.request.ImageRequest):void (m)] call: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.1.<init>(com.facebook.imagepipeline.producers.LocalExifThumbnailProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.request.ImageRequest):void type: CONSTRUCTOR in method: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.produceResults(com.facebook.imagepipeline.producers.Consumer<com.facebook.imagepipeline.image.EncodedImage>, com.facebook.imagepipeline.producers.ProducerContext):void, file: classes.dex
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
            com.facebook.imagepipeline.producers.ProducerListener2 r3 = r10.getProducerListener()
            com.facebook.imagepipeline.request.ImageRequest r6 = r10.getImageRequest()
            com.facebook.imagepipeline.producers.LocalExifThumbnailProducer$1 r7 = new com.facebook.imagepipeline.producers.LocalExifThumbnailProducer$1
            java.lang.String r5 = "LocalExifThumbnailProducer"
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r0.<init>(r2, r3, r4, r5)
            com.facebook.imagepipeline.producers.LocalExifThumbnailProducer$2 r9 = new com.facebook.imagepipeline.producers.LocalExifThumbnailProducer$2
            r9.<init>()
            r10.addCallbacks(r9)
            java.util.concurrent.Executor r9 = r8.mExecutor
            r9.execute(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.produceResults(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }
}
