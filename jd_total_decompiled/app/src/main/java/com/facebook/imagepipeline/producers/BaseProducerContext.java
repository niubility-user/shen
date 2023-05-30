package com.facebook.imagepipeline.producers;

import android.util.SparseArray;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.EncodedImageOrigin;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class BaseProducerContext implements ProducerContext {
    @GuardedBy("this")
    private final List<ProducerContextCallbacks> mCallbacks;
    private final Object mCallerContext;
    private EncodedImageOrigin mEncodedImageOrigin;
    private final SparseArray<String> mExtras;
    private final String mId;
    private final ImagePipelineConfig mImagePipelineConfig;
    private final ImageRequest mImageRequest;
    @GuardedBy("this")
    private boolean mIsCancelled;
    @GuardedBy("this")
    private boolean mIsIntermediateResultExpected;
    @GuardedBy("this")
    private boolean mIsPrefetch;
    private final ImageRequest.RequestLevel mLowestPermittedRequestLevel;
    @GuardedBy("this")
    private Priority mPriority;
    private final ProducerListener2 mProducerListener;
    @Nullable
    private final String mUiComponentId;

    public BaseProducerContext(ImageRequest imageRequest, String str, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfig imagePipelineConfig) {
        this(imageRequest, str, null, producerListener2, obj, requestLevel, z, z2, priority, imagePipelineConfig);
    }

    public BaseProducerContext(ImageRequest imageRequest, String str, @Nullable String str2, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfig imagePipelineConfig) {
        this.mExtras = new SparseArray<>();
        this.mEncodedImageOrigin = EncodedImageOrigin.NOT_SET;
        this.mImageRequest = imageRequest;
        this.mId = str;
        this.mUiComponentId = str2;
        this.mProducerListener = producerListener2;
        this.mCallerContext = obj;
        this.mLowestPermittedRequestLevel = requestLevel;
        this.mIsPrefetch = z;
        this.mPriority = priority;
        this.mIsIntermediateResultExpected = z2;
        this.mIsCancelled = false;
        this.mCallbacks = new ArrayList();
        this.mImagePipelineConfig = imagePipelineConfig;
    }

    public static void callOnCancellationRequested(@Nullable List<ProducerContextCallbacks> list) {
        if (list == null) {
            return;
        }
        Iterator<ProducerContextCallbacks> it = list.iterator();
        while (it.hasNext()) {
            it.next().onCancellationRequested();
        }
    }

    public static void callOnIsIntermediateResultExpectedChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list == null) {
            return;
        }
        Iterator<ProducerContextCallbacks> it = list.iterator();
        while (it.hasNext()) {
            it.next().onIsIntermediateResultExpectedChanged();
        }
    }

    public static void callOnIsPrefetchChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list == null) {
            return;
        }
        Iterator<ProducerContextCallbacks> it = list.iterator();
        while (it.hasNext()) {
            it.next().onIsPrefetchChanged();
        }
    }

    public static void callOnPriorityChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list == null) {
            return;
        }
        Iterator<ProducerContextCallbacks> it = list.iterator();
        while (it.hasNext()) {
            it.next().onPriorityChanged();
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void addCallbacks(ProducerContextCallbacks producerContextCallbacks) {
        boolean z;
        synchronized (this) {
            this.mCallbacks.add(producerContextCallbacks);
            z = this.mIsCancelled;
        }
        if (z) {
            producerContextCallbacks.onCancellationRequested();
        }
    }

    public void cancel() {
        callOnCancellationRequested(cancelNoCallbacks());
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> cancelNoCallbacks() {
        if (this.mIsCancelled) {
            return null;
        }
        this.mIsCancelled = true;
        return new ArrayList(this.mCallbacks);
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public EncodedImageOrigin getEncodedImageOrigin() {
        return this.mEncodedImageOrigin;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public String getExtra(int i2) {
        return this.mExtras.get(i2, "");
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public String getId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImagePipelineConfig getImagePipelineConfig() {
        return this.mImagePipelineConfig;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized Priority getPriority() {
        return this.mPriority;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ProducerListener2 getProducerListener() {
        return this.mProducerListener;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    @Nullable
    public String getUiComponentId() {
        return this.mUiComponentId;
    }

    public synchronized boolean isCancelled() {
        return this.mIsCancelled;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isIntermediateResultExpected() {
        return this.mIsIntermediateResultExpected;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isPrefetch() {
        return this.mIsPrefetch;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void setEncodedImageOrigin(EncodedImageOrigin encodedImageOrigin) {
        this.mEncodedImageOrigin = encodedImageOrigin;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void setExtra(@ProducerContext.ExtraKeys int i2, String str) {
        this.mExtras.put(i2, str);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setIsIntermediateResultExpectedNoCallbacks(boolean z) {
        if (z == this.mIsIntermediateResultExpected) {
            return null;
        }
        this.mIsIntermediateResultExpected = z;
        return new ArrayList(this.mCallbacks);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setIsPrefetchNoCallbacks(boolean z) {
        if (z == this.mIsPrefetch) {
            return null;
        }
        this.mIsPrefetch = z;
        return new ArrayList(this.mCallbacks);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setPriorityNoCallbacks(Priority priority) {
        if (priority == this.mPriority) {
            return null;
        }
        this.mPriority = priority;
        return new ArrayList(this.mCallbacks);
    }
}
