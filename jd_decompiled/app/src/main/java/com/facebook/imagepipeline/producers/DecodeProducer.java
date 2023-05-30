package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imageutils.BitmapUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_BITMAP_BYTES = "byteCount";
    public static final String EXTRA_BITMAP_SIZE = "bitmapSize";
    public static final String EXTRA_HAS_GOOD_QUALITY = "hasGoodQuality";
    public static final String EXTRA_IMAGE_FORMAT_NAME = "imageFormat";
    public static final String EXTRA_IS_FINAL = "isFinal";
    private static final int MAX_BITMAP_SIZE = 104857600;
    public static final String PRODUCER_NAME = "DecodeProducer";
    public static final String REQUESTED_IMAGE_SIZE = "requestedImageSize";
    public static final String SAMPLE_SIZE = "sampleSize";
    private final ByteArrayPool mByteArrayPool;
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private final boolean mDecodeCancellationEnabled;
    private final boolean mDownsampleEnabled;
    private final boolean mDownsampleEnabledForNetwork;
    private final Executor mExecutor;
    private final ImageDecoder mImageDecoder;
    private final Producer<EncodedImage> mInputProducer;
    private final int mMaxBitmapSize;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;

    /* loaded from: classes.dex */
    private class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        public LocalImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i2) {
            super(consumer, producerContext, z, i2);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return encodedImage.getSize();
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected QualityInfo getQualityInfo() {
            return ImmutableQualityInfo.of(0, false, false);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i2) {
            if (BaseConsumer.isNotLast(i2)) {
                return false;
            }
            return super.updateDecodeJob(encodedImage, i2);
        }
    }

    /* loaded from: classes.dex */
    private class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private int mLastScheduledScanNumber;
        private final ProgressiveJpegConfig mProgressiveJpegConfig;
        private final ProgressiveJpegParser mProgressiveJpegParser;

        public NetworkImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser, ProgressiveJpegConfig progressiveJpegConfig, boolean z, int i2) {
            super(consumer, producerContext, z, i2);
            this.mProgressiveJpegParser = (ProgressiveJpegParser) Preconditions.checkNotNull(progressiveJpegParser);
            this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
            this.mLastScheduledScanNumber = 0;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return this.mProgressiveJpegParser.getBestScanEndOffset();
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected QualityInfo getQualityInfo() {
            return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.getBestScanNumber());
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i2) {
            boolean updateDecodeJob = super.updateDecodeJob(encodedImage, i2);
            if ((BaseConsumer.isNotLast(i2) || BaseConsumer.statusHasFlag(i2, 8)) && !BaseConsumer.statusHasFlag(i2, 4) && EncodedImage.isValid(encodedImage) && encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                if (!this.mProgressiveJpegParser.parseMoreData(encodedImage)) {
                    return false;
                }
                int bestScanNumber = this.mProgressiveJpegParser.getBestScanNumber();
                int i3 = this.mLastScheduledScanNumber;
                if (bestScanNumber <= i3) {
                    return false;
                }
                if (bestScanNumber < this.mProgressiveJpegConfig.getNextScanNumberToDecode(i3) && !this.mProgressiveJpegParser.isEndMarkerRead()) {
                    return false;
                }
                this.mLastScheduledScanNumber = bestScanNumber;
            }
            return updateDecodeJob;
        }
    }

    /* loaded from: classes.dex */
    private abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        private static final int DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES = 10;
        private final String TAG;
        private final ImageDecodeOptions mImageDecodeOptions;
        @GuardedBy("this")
        private boolean mIsFinished;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;
        private final ProducerListener2 mProducerListener;

        public ProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext, final boolean z, final int i2) {
            super(consumer);
            this.TAG = "ProgressiveDecoder";
            this.mProducerContext = producerContext;
            this.mProducerListener = producerContext.getProducerListener();
            ImageDecodeOptions imageDecodeOptions = producerContext.getImageRequest().getImageDecodeOptions();
            this.mImageDecodeOptions = imageDecodeOptions;
            this.mIsFinished = false;
            this.mJobScheduler = new JobScheduler(DecodeProducer.this.mExecutor, new JobScheduler.JobRunnable() { // from class: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.1
                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                public void run(EncodedImage encodedImage, int i3) {
                    if (encodedImage != null) {
                        if (DecodeProducer.this.mDownsampleEnabled || !BaseConsumer.statusHasFlag(i3, 16)) {
                            ImageRequest imageRequest = producerContext.getImageRequest();
                            if (DecodeProducer.this.mDownsampleEnabledForNetwork || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                                encodedImage.setSampleSize(com.facebook.imagepipeline.transcoder.DownsampleUtil.determineSampleSize(imageRequest.getRotationOptions(), imageRequest.getResizeOptions(), encodedImage, i2));
                            }
                        }
                        if (producerContext.getImagePipelineConfig().getExperiments().shouldDownsampleIfLargeBitmap()) {
                            ProgressiveDecoder.this.maybeIncreaseSampleSize(encodedImage);
                        }
                        ProgressiveDecoder.this.doDecode(encodedImage, i3);
                    }
                }
            }, imageDecodeOptions.minDecodeIntervalMs);
            producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.2
                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (z) {
                        ProgressiveDecoder.this.handleCancellation();
                    }
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (ProgressiveDecoder.this.mProducerContext.isIntermediateResultExpected()) {
                        ProgressiveDecoder.this.mJobScheduler.scheduleJob();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00ef A[Catch: all -> 0x016b, TryCatch #1 {all -> 0x016b, blocks: (B:26:0x0091, B:30:0x00aa, B:35:0x00b8, B:37:0x00bf, B:38:0x00c6, B:39:0x00d4, B:44:0x00e2, B:46:0x00ef, B:47:0x011a, B:60:0x0159, B:55:0x0128, B:57:0x0130, B:58:0x0154, B:36:0x00bd, B:31:0x00af), top: B:69:0x0091 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void doDecode(com.facebook.imagepipeline.image.EncodedImage r19, int r20) {
            /*
                Method dump skipped, instructions count: 369
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.doDecode(com.facebook.imagepipeline.image.EncodedImage, int):void");
        }

        @Nullable
        private Map<String, String> getExtraMap(@Nullable CloseableImage closeableImage, long j2, QualityInfo qualityInfo, boolean z, String str, String str2, String str3, String str4) {
            if (this.mProducerListener.requiresExtraMap(this.mProducerContext, DecodeProducer.PRODUCER_NAME)) {
                String valueOf = String.valueOf(j2);
                String valueOf2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
                String valueOf3 = String.valueOf(z);
                if (!(closeableImage instanceof CloseableStaticBitmap)) {
                    HashMap hashMap = new HashMap(7);
                    hashMap.put("queueTime", valueOf);
                    hashMap.put(DecodeProducer.EXTRA_HAS_GOOD_QUALITY, valueOf2);
                    hashMap.put(DecodeProducer.EXTRA_IS_FINAL, valueOf3);
                    hashMap.put("encodedImageSize", str2);
                    hashMap.put(DecodeProducer.EXTRA_IMAGE_FORMAT_NAME, str);
                    hashMap.put(DecodeProducer.REQUESTED_IMAGE_SIZE, str3);
                    hashMap.put(DecodeProducer.SAMPLE_SIZE, str4);
                    return ImmutableMap.copyOf((Map) hashMap);
                }
                Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap();
                String str5 = underlyingBitmap.getWidth() + JshopConst.JSHOP_PROMOTIO_X + underlyingBitmap.getHeight();
                HashMap hashMap2 = new HashMap(8);
                hashMap2.put(DecodeProducer.EXTRA_BITMAP_SIZE, str5);
                hashMap2.put("queueTime", valueOf);
                hashMap2.put(DecodeProducer.EXTRA_HAS_GOOD_QUALITY, valueOf2);
                hashMap2.put(DecodeProducer.EXTRA_IS_FINAL, valueOf3);
                hashMap2.put("encodedImageSize", str2);
                hashMap2.put(DecodeProducer.EXTRA_IMAGE_FORMAT_NAME, str);
                hashMap2.put(DecodeProducer.REQUESTED_IMAGE_SIZE, str3);
                hashMap2.put(DecodeProducer.SAMPLE_SIZE, str4);
                if (Build.VERSION.SDK_INT >= 12) {
                    hashMap2.put("byteCount", underlyingBitmap.getByteCount() + "");
                }
                return ImmutableMap.copyOf((Map) hashMap2);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }

        private void handleError(Throwable th) {
            maybeFinish(true);
            getConsumer().onFailure(th);
        }

        private void handleResult(CloseableImage closeableImage, int i2) {
            CloseableReference<CloseableImage> create = DecodeProducer.this.mCloseableReferenceFactory.create(closeableImage);
            try {
                maybeFinish(BaseConsumer.isLast(i2));
                getConsumer().onNewResult(create, i2);
            } finally {
                CloseableReference.closeSafely(create);
            }
        }

        private synchronized boolean isFinished() {
            return this.mIsFinished;
        }

        private void maybeFinish(boolean z) {
            synchronized (this) {
                if (z) {
                    if (!this.mIsFinished) {
                        getConsumer().onProgressUpdate(1.0f);
                        this.mIsFinished = true;
                        this.mJobScheduler.clearJob();
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeIncreaseSampleSize(EncodedImage encodedImage) {
            if (encodedImage.getImageFormat() != DefaultImageFormats.JPEG) {
                return;
            }
            encodedImage.setSampleSize(com.facebook.imagepipeline.transcoder.DownsampleUtil.determineSampleSizeJPEG(encodedImage, BitmapUtil.getPixelSizeForBitmapConfig(this.mImageDecodeOptions.bitmapConfig), 104857600));
        }

        protected abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        protected abstract QualityInfo getQualityInfo();

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            handleCancellation();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable th) {
            handleError(th);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(EncodedImage encodedImage, int i2) {
            boolean isTracing;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("DecodeProducer#onNewResultImpl");
                }
                boolean isLast = BaseConsumer.isLast(i2);
                if (isLast) {
                    if (encodedImage == null) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                        if (isTracing) {
                            return;
                        }
                        return;
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                }
                if (!updateDecodeJob(encodedImage, i2)) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                        return;
                    }
                    return;
                }
                boolean statusHasFlag = BaseConsumer.statusHasFlag(i2, 4);
                if (isLast || statusHasFlag || this.mProducerContext.isIntermediateResultExpected()) {
                    this.mJobScheduler.scheduleJob();
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } finally {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onProgressUpdateImpl(float f2) {
            super.onProgressUpdateImpl(f2 * 0.99f);
        }

        protected boolean updateDecodeJob(EncodedImage encodedImage, int i2) {
            return this.mJobScheduler.updateJob(encodedImage, i2);
        }
    }

    public DecodeProducer(ByteArrayPool byteArrayPool, Executor executor, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, Producer<EncodedImage> producer, int i2, CloseableReferenceFactory closeableReferenceFactory) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mImageDecoder = (ImageDecoder) Preconditions.checkNotNull(imageDecoder);
        this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
        this.mDownsampleEnabled = z;
        this.mDownsampleEnabledForNetwork = z2;
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mDecodeCancellationEnabled = z3;
        this.mMaxBitmapSize = i2;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("DecodeProducer#produceResults");
            }
            this.mInputProducer.produceResults(!UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri()) ? new LocalImagesProgressiveDecoder(consumer, producerContext, this.mDecodeCancellationEnabled, this.mMaxBitmapSize) : new NetworkImagesProgressiveDecoder(consumer, producerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig, this.mDecodeCancellationEnabled, this.mMaxBitmapSize), producerContext);
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }
}
