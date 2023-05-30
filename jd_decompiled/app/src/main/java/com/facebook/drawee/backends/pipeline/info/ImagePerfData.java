package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.internal.Objects;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.common.net.HttpHeaders;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfData {
    public static final int UNSET = -1;
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private final String mComponentTag;
    private final long mControllerCancelTimeMs;
    private final long mControllerFailureTimeMs;
    private final long mControllerFinalImageSetTimeMs;
    @Nullable
    private final ImageRequest[] mControllerFirstAvailableImageRequests;
    @Nullable
    private final String mControllerId;
    @Nullable
    private final ImageRequest mControllerImageRequest;
    private final long mControllerIntermediateImageSetTimeMs;
    @Nullable
    private final ImageRequest mControllerLowResImageRequest;
    private final long mControllerSubmitTimeMs;
    @Nullable
    private final DimensionsInfo mDimensionsInfo;
    @Nullable
    private final Throwable mErrorThrowable;
    private final long mImageDrawTimeMs;
    @Nullable
    private final ImageInfo mImageInfo;
    private final int mImageOrigin;
    @Nullable
    private final ImageRequest mImageRequest;
    private final long mImageRequestEndTimeMs;
    private final long mImageRequestStartTimeMs;
    private final long mInvisibilityEventTimeMs;
    private final boolean mIsPrefetch;
    private final int mOnScreenHeightPx;
    private final int mOnScreenWidthPx;
    @Nullable
    private final String mRequestId;
    @Nullable
    private final String mUltimateProducerName;
    private final long mVisibilityEventTimeMs;
    private final int mVisibilityState;

    public ImagePerfData(@Nullable String str, @Nullable String str2, @Nullable ImageRequest imageRequest, @Nullable Object obj, @Nullable ImageInfo imageInfo, @Nullable ImageRequest imageRequest2, @Nullable ImageRequest imageRequest3, @Nullable ImageRequest[] imageRequestArr, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i2, @Nullable String str3, boolean z, int i3, int i4, @Nullable Throwable th, int i5, long j9, long j10, @Nullable String str4, long j11, @Nullable DimensionsInfo dimensionsInfo) {
        this.mControllerId = str;
        this.mRequestId = str2;
        this.mImageRequest = imageRequest;
        this.mCallerContext = obj;
        this.mImageInfo = imageInfo;
        this.mControllerImageRequest = imageRequest2;
        this.mControllerLowResImageRequest = imageRequest3;
        this.mControllerFirstAvailableImageRequests = imageRequestArr;
        this.mControllerSubmitTimeMs = j2;
        this.mControllerIntermediateImageSetTimeMs = j3;
        this.mControllerFinalImageSetTimeMs = j4;
        this.mControllerFailureTimeMs = j5;
        this.mControllerCancelTimeMs = j6;
        this.mImageRequestStartTimeMs = j7;
        this.mImageRequestEndTimeMs = j8;
        this.mImageOrigin = i2;
        this.mUltimateProducerName = str3;
        this.mIsPrefetch = z;
        this.mOnScreenWidthPx = i3;
        this.mOnScreenHeightPx = i4;
        this.mErrorThrowable = th;
        this.mVisibilityState = i5;
        this.mVisibilityEventTimeMs = j9;
        this.mInvisibilityEventTimeMs = j10;
        this.mComponentTag = str4;
        this.mImageDrawTimeMs = j11;
        this.mDimensionsInfo = dimensionsInfo;
    }

    public String createDebugString() {
        return Objects.toStringHelper(this).add("controller ID", this.mControllerId).add("request ID", this.mRequestId).add("controller image request", this.mControllerImageRequest).add("controller low res image request", this.mControllerLowResImageRequest).add("controller first available image requests", this.mControllerFirstAvailableImageRequests).add("controller submit", this.mControllerSubmitTimeMs).add("controller final image", this.mControllerFinalImageSetTimeMs).add("controller failure", this.mControllerFailureTimeMs).add("controller cancel", this.mControllerCancelTimeMs).add("start time", this.mImageRequestStartTimeMs).add("end time", this.mImageRequestEndTimeMs).add(HttpHeaders.ReferrerPolicyValues.ORIGIN, ImageOriginUtils.toString(this.mImageOrigin)).add("ultimateProducerName", this.mUltimateProducerName).add("prefetch", this.mIsPrefetch).add("caller context", this.mCallerContext).add("image request", this.mImageRequest).add("image info", this.mImageInfo).add("on-screen width", this.mOnScreenWidthPx).add("on-screen height", this.mOnScreenHeightPx).add("visibility state", this.mVisibilityState).add("component tag", this.mComponentTag).add("visibility event", this.mVisibilityEventTimeMs).add("invisibility event", this.mInvisibilityEventTimeMs).add("image draw event", this.mImageDrawTimeMs).add("dimensions info", this.mDimensionsInfo).toString();
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Nullable
    public String getComponentTag() {
        return this.mComponentTag;
    }

    public long getControllerFailureTimeMs() {
        return this.mControllerFailureTimeMs;
    }

    public long getControllerFinalImageSetTimeMs() {
        return this.mControllerFinalImageSetTimeMs;
    }

    @Nullable
    public ImageRequest[] getControllerFirstAvailableImageRequests() {
        return this.mControllerFirstAvailableImageRequests;
    }

    @Nullable
    public String getControllerId() {
        return this.mControllerId;
    }

    @Nullable
    public ImageRequest getControllerImageRequest() {
        return this.mControllerImageRequest;
    }

    public long getControllerIntermediateImageSetTimeMs() {
        return this.mControllerIntermediateImageSetTimeMs;
    }

    @Nullable
    public ImageRequest getControllerLowResImageRequest() {
        return this.mControllerLowResImageRequest;
    }

    public long getControllerSubmitTimeMs() {
        return this.mControllerSubmitTimeMs;
    }

    @Nullable
    public DimensionsInfo getDimensionsInfo() {
        return this.mDimensionsInfo;
    }

    @Nullable
    public Throwable getErrorThrowable() {
        return this.mErrorThrowable;
    }

    public long getFinalImageLoadTimeMs() {
        if (getImageRequestEndTimeMs() == -1 || getImageRequestStartTimeMs() == -1) {
            return -1L;
        }
        return getImageRequestEndTimeMs() - getImageRequestStartTimeMs();
    }

    public long getImageDrawTimeMs() {
        return this.mImageDrawTimeMs;
    }

    @Nullable
    public ImageInfo getImageInfo() {
        return this.mImageInfo;
    }

    public int getImageOrigin() {
        return this.mImageOrigin;
    }

    @Nullable
    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }

    public long getImageRequestEndTimeMs() {
        return this.mImageRequestEndTimeMs;
    }

    public long getImageRequestStartTimeMs() {
        return this.mImageRequestStartTimeMs;
    }

    public long getIntermediateImageLoadTimeMs() {
        if (getControllerIntermediateImageSetTimeMs() == -1 || getControllerSubmitTimeMs() == -1) {
            return -1L;
        }
        return getControllerIntermediateImageSetTimeMs() - getControllerSubmitTimeMs();
    }

    public long getInvisibilityEventTimeMs() {
        return this.mInvisibilityEventTimeMs;
    }

    public int getOnScreenHeightPx() {
        return this.mOnScreenHeightPx;
    }

    public int getOnScreenWidthPx() {
        return this.mOnScreenWidthPx;
    }

    @Nullable
    public String getRequestId() {
        return this.mRequestId;
    }

    @Nullable
    public String getUltimateProducerName() {
        return this.mUltimateProducerName;
    }

    public long getVisibilityEventTimeMs() {
        return this.mVisibilityEventTimeMs;
    }

    public int getVisibilityState() {
        return this.mVisibilityState;
    }

    public boolean isPrefetch() {
        return this.mIsPrefetch;
    }
}
