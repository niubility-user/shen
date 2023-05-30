package com.facebook.drawee.backends.pipeline.info;

import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfState {
    @Nullable
    private Object mCallerContext;
    @Nullable
    private String mComponentTag;
    @Nullable
    private ImageRequest[] mControllerFirstAvailableImageRequests;
    @Nullable
    private String mControllerId;
    @Nullable
    private ImageRequest mControllerImageRequest;
    @Nullable
    private ImageRequest mControllerLowResImageRequest;
    @Nullable
    private DimensionsInfo mDimensionsInfo;
    @Nullable
    private Throwable mErrorThrowable;
    @Nullable
    private ImageInfo mImageInfo;
    @Nullable
    private ImageRequest mImageRequest;
    private boolean mIsPrefetch;
    @Nullable
    private String mRequestId;
    @Nullable
    private String mUltimateProducerName;
    private long mControllerSubmitTimeMs = -1;
    private long mControllerIntermediateImageSetTimeMs = -1;
    private long mControllerFinalImageSetTimeMs = -1;
    private long mControllerFailureTimeMs = -1;
    private long mControllerCancelTimeMs = -1;
    private long mImageRequestStartTimeMs = -1;
    private long mImageRequestEndTimeMs = -1;
    private int mImageOrigin = 1;
    private int mOnScreenWidthPx = -1;
    private int mOnScreenHeightPx = -1;
    private int mImageLoadStatus = -1;
    private int mVisibilityState = -1;
    private long mVisibilityEventTimeMs = -1;
    private long mInvisibilityEventTimeMs = -1;
    private long mImageDrawTimeMs = -1;

    @Nullable
    public DimensionsInfo getDimensionsInfo() {
        return this.mDimensionsInfo;
    }

    public long getImageDrawTimeMs() {
        return this.mImageDrawTimeMs;
    }

    public int getImageLoadStatus() {
        return this.mImageLoadStatus;
    }

    public void reset() {
        this.mRequestId = null;
        this.mImageRequest = null;
        this.mCallerContext = null;
        this.mImageInfo = null;
        this.mControllerImageRequest = null;
        this.mControllerLowResImageRequest = null;
        this.mControllerFirstAvailableImageRequests = null;
        this.mImageOrigin = 1;
        this.mUltimateProducerName = null;
        this.mIsPrefetch = false;
        this.mOnScreenWidthPx = -1;
        this.mOnScreenHeightPx = -1;
        this.mErrorThrowable = null;
        this.mImageLoadStatus = -1;
        this.mVisibilityState = -1;
        this.mComponentTag = null;
        this.mDimensionsInfo = null;
        resetPointsTimestamps();
    }

    public void resetPointsTimestamps() {
        this.mImageRequestStartTimeMs = -1L;
        this.mImageRequestEndTimeMs = -1L;
        this.mControllerSubmitTimeMs = -1L;
        this.mControllerFinalImageSetTimeMs = -1L;
        this.mControllerFailureTimeMs = -1L;
        this.mControllerCancelTimeMs = -1L;
        this.mVisibilityEventTimeMs = -1L;
        this.mInvisibilityEventTimeMs = -1L;
        this.mImageDrawTimeMs = -1L;
    }

    public void setCallerContext(@Nullable Object obj) {
        this.mCallerContext = obj;
    }

    public void setComponentTag(@Nullable String str) {
        this.mComponentTag = str;
    }

    public void setControllerCancelTimeMs(long j2) {
        this.mControllerCancelTimeMs = j2;
    }

    public void setControllerFailureTimeMs(long j2) {
        this.mControllerFailureTimeMs = j2;
    }

    public void setControllerFinalImageSetTimeMs(long j2) {
        this.mControllerFinalImageSetTimeMs = j2;
    }

    public void setControllerId(@Nullable String str) {
        this.mControllerId = str;
    }

    public void setControllerImageRequests(@Nullable ImageRequest imageRequest, @Nullable ImageRequest imageRequest2, @Nullable ImageRequest[] imageRequestArr) {
        this.mControllerImageRequest = imageRequest;
        this.mControllerLowResImageRequest = imageRequest2;
        this.mControllerFirstAvailableImageRequests = imageRequestArr;
    }

    public void setControllerIntermediateImageSetTimeMs(long j2) {
        this.mControllerIntermediateImageSetTimeMs = j2;
    }

    public void setControllerSubmitTimeMs(long j2) {
        this.mControllerSubmitTimeMs = j2;
    }

    public void setDimensionsInfo(DimensionsInfo dimensionsInfo) {
        this.mDimensionsInfo = dimensionsInfo;
    }

    public void setErrorThrowable(@Nullable Throwable th) {
        this.mErrorThrowable = th;
    }

    public void setImageDrawTimeMs(long j2) {
        this.mImageDrawTimeMs = j2;
    }

    public void setImageInfo(@Nullable ImageInfo imageInfo) {
        this.mImageInfo = imageInfo;
    }

    public void setImageLoadStatus(int i2) {
        this.mImageLoadStatus = i2;
    }

    public void setImageOrigin(int i2) {
        this.mImageOrigin = i2;
    }

    public void setImageRequest(@Nullable ImageRequest imageRequest) {
        this.mImageRequest = imageRequest;
    }

    public void setImageRequestEndTimeMs(long j2) {
        this.mImageRequestEndTimeMs = j2;
    }

    public void setImageRequestStartTimeMs(long j2) {
        this.mImageRequestStartTimeMs = j2;
    }

    public void setInvisibilityEventTimeMs(long j2) {
        this.mInvisibilityEventTimeMs = j2;
    }

    public void setOnScreenHeight(int i2) {
        this.mOnScreenHeightPx = i2;
    }

    public void setOnScreenWidth(int i2) {
        this.mOnScreenWidthPx = i2;
    }

    public void setPrefetch(boolean z) {
        this.mIsPrefetch = z;
    }

    public void setRequestId(@Nullable String str) {
        this.mRequestId = str;
    }

    public void setUltimateProducerName(@Nullable String str) {
        this.mUltimateProducerName = str;
    }

    public void setVisibilityEventTimeMs(long j2) {
        this.mVisibilityEventTimeMs = j2;
    }

    public void setVisible(boolean z) {
        this.mVisibilityState = z ? 1 : 2;
    }

    public ImagePerfData snapshot() {
        return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerImageRequest, this.mControllerLowResImageRequest, this.mControllerFirstAvailableImageRequests, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mImageOrigin, this.mUltimateProducerName, this.mIsPrefetch, this.mOnScreenWidthPx, this.mOnScreenHeightPx, this.mErrorThrowable, this.mVisibilityState, this.mVisibilityEventTimeMs, this.mInvisibilityEventTimeMs, this.mComponentTag, this.mImageDrawTimeMs, this.mDimensionsInfo);
    }
}
