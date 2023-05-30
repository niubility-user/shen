package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "VideoThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int calculateKind(ImageRequest imageRequest) {
        return (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) ? 1 : 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static Bitmap createThumbnailFromContentProvider(ContentResolver contentResolver, Uri uri) {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openFileDescriptor.getFileDescriptor());
                return mediaMetadataRetriever.getFrameAtTime(-1L);
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String getLocalFilePath(ImageRequest imageRequest) {
        Uri uri;
        String str;
        String[] strArr;
        Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtil.isLocalFileUri(sourceUri)) {
            return imageRequest.getSourceFile().getPath();
        }
        if (UriUtil.isLocalContentUri(sourceUri)) {
            if (Build.VERSION.SDK_INT < 19 || !"com.android.providers.media.documents".equals(sourceUri.getAuthority())) {
                uri = sourceUri;
                str = null;
                strArr = null;
            } else {
                String documentId = DocumentsContract.getDocumentId(sourceUri);
                str = "_id=?";
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                strArr = new String[]{documentId.split(":")[1]};
            }
            Cursor query = this.mContentResolver.query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        return query.getString(query.getColumnIndexOrThrow("_data"));
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        }
        return null;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        producerContext.getImageRequest();
        final StatefulProducerRunnable<CloseableReference<CloseableImage>> statefulProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR (r9v0 'statefulProducerRunnable' com.facebook.imagepipeline.producers.StatefulProducerRunnable<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>) = 
              (r10v0 'this' com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer A[IMMUTABLE_TYPE, THIS])
              (r11v0 'consumer' com.facebook.imagepipeline.producers.Consumer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>)
              (r6v0 'producerListener' com.facebook.imagepipeline.producers.ProducerListener2)
              (r12v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext)
              (wrap: java.lang.String : SGET  A[WRAPPED] com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.PRODUCER_NAME java.lang.String)
              (r6v0 'producerListener' com.facebook.imagepipeline.producers.ProducerListener2 A[DONT_INLINE])
              (r12v0 'producerContext' com.facebook.imagepipeline.producers.ProducerContext A[DONT_INLINE])
              (r8 I:com.facebook.imagepipeline.request.ImageRequest A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.request.ImageRequest):void (m)] call: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.1.<init>(com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer, com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, java.lang.String, com.facebook.imagepipeline.producers.ProducerListener2, com.facebook.imagepipeline.producers.ProducerContext, com.facebook.imagepipeline.request.ImageRequest):void type: CONSTRUCTOR in method: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.produceResults(com.facebook.imagepipeline.producers.Consumer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>, com.facebook.imagepipeline.producers.ProducerContext):void, file: classes.dex
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
            com.facebook.imagepipeline.producers.ProducerListener2 r6 = r12.getProducerListener()
            com.facebook.imagepipeline.request.ImageRequest r8 = r12.getImageRequest()
            com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$1 r9 = new com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$1
            java.lang.String r5 = "VideoThumbnailProducer"
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r6
            r4 = r12
            r7 = r12
            r0.<init>(r2, r3, r4, r5)
            com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$2 r11 = new com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$2
            r11.<init>()
            r12.addCallbacks(r11)
            java.util.concurrent.Executor r11 = r10.mExecutor
            r11.execute(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.produceResults(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }
}
