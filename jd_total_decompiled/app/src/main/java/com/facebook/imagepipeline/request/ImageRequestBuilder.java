package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageRequestBuilder {
    private boolean isScale;
    @Nullable
    private RequestListener mRequestListener;
    private String referer;
    private Uri mSourceUri = null;
    private ImageRequest.RequestLevel mLowestPermittedRequestLevel = ImageRequest.RequestLevel.FULL_FETCH;
    @Nullable
    private ResizeOptions mResizeOptions = null;
    @Nullable
    private RotationOptions mRotationOptions = null;
    private ImageDecodeOptions mImageDecodeOptions = ImageDecodeOptions.defaults();
    private ImageRequest.CacheChoice mCacheChoice = ImageRequest.CacheChoice.DEFAULT;
    private boolean mProgressiveRenderingEnabled = ImagePipelineConfig.getDefaultImageRequestConfig().isProgressiveRenderingEnabled();
    private boolean mLocalThumbnailPreviewsEnabled = false;
    private Priority mRequestPriority = Priority.HIGH;
    @Nullable
    private Postprocessor mPostprocessor = null;
    private boolean mDiskCacheEnabled = true;
    private boolean mMemoryCacheEnabled = true;
    @Nullable
    private Boolean mDecodePrefetches = null;
    @Nullable
    private BytesRange mBytesRange = null;
    @Nullable
    private Boolean mResizingAllowedOverride = null;
    private boolean isUseThumbnail = true;
    private int inSampleSize = 1;
    private boolean autoPlayAnimations = true;

    /* loaded from: classes.dex */
    public static class BuilderException extends RuntimeException {
        public BuilderException(String str) {
            super("Invalid request builder: " + str);
        }
    }

    private ImageRequestBuilder() {
    }

    public static ImageRequestBuilder fromRequest(ImageRequest imageRequest) {
        return newBuilderWithSource(imageRequest.getSourceUri()).setImageDecodeOptions(imageRequest.getImageDecodeOptions()).setBytesRange(imageRequest.getBytesRange()).setCacheChoice(imageRequest.getCacheChoice()).setLocalThumbnailPreviewsEnabled(imageRequest.getLocalThumbnailPreviewsEnabled()).setLowestPermittedRequestLevel(imageRequest.getLowestPermittedRequestLevel()).setPostprocessor(imageRequest.getPostprocessor()).setProgressiveRenderingEnabled(imageRequest.getProgressiveRenderingEnabled()).setRequestPriority(imageRequest.getPriority()).setResizeOptions(imageRequest.getResizeOptions()).setRequestListener(imageRequest.getRequestListener()).setRotationOptions(imageRequest.getRotationOptions()).setShouldDecodePrefetches(imageRequest.shouldDecodePrefetches()).setIsScale(imageRequest.isScale()).setIsUseThumbnail(imageRequest.isUseThumbnail()).setInSampleSize(imageRequest.getInSampleSize()).setReferer(imageRequest.getReferer()).setAutoPlayAnimations(imageRequest.isAutoPlayAnimations());
    }

    public static ImageRequestBuilder newBuilderWithResourceId(int i2) {
        return newBuilderWithSource(UriUtil.getUriForResourceId(i2));
    }

    public static ImageRequestBuilder newBuilderWithSource(Uri uri) {
        return new ImageRequestBuilder().setSource(uri);
    }

    public ImageRequest build() {
        validate();
        return new ImageRequest(this);
    }

    public ImageRequestBuilder disableDiskCache() {
        this.mDiskCacheEnabled = false;
        return this;
    }

    public ImageRequestBuilder disableMemoryCache() {
        this.mMemoryCacheEnabled = false;
        return this;
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public ImageRequest.CacheChoice getCacheChoice() {
        return this.mCacheChoice;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public int getInSampleSize() {
        return this.inSampleSize;
    }

    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public String getReferer() {
        return this.referer;
    }

    @Nullable
    public RequestListener getRequestListener() {
        return this.mRequestListener;
    }

    public Priority getRequestPriority() {
        return this.mRequestPriority;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    @Nullable
    public Boolean getResizingAllowedOverride() {
        return this.mResizingAllowedOverride;
    }

    @Nullable
    public RotationOptions getRotationOptions() {
        return this.mRotationOptions;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public boolean isAutoPlayAnimations() {
        return this.autoPlayAnimations;
    }

    public boolean isDiskCacheEnabled() {
        return this.mDiskCacheEnabled && UriUtil.isNetworkUri(this.mSourceUri);
    }

    public boolean isLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean isMemoryCacheEnabled() {
        return this.mMemoryCacheEnabled;
    }

    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public boolean isScale() {
        return this.isScale;
    }

    public boolean isUseThumbnail() {
        return this.isUseThumbnail;
    }

    public ImageRequestBuilder setAutoPlayAnimations(boolean z) {
        this.autoPlayAnimations = z;
        return this;
    }

    @Deprecated
    public ImageRequestBuilder setAutoRotateEnabled(boolean z) {
        return setRotationOptions(z ? RotationOptions.autoRotate() : RotationOptions.disableRotation());
    }

    public ImageRequestBuilder setBytesRange(@Nullable BytesRange bytesRange) {
        this.mBytesRange = bytesRange;
        return this;
    }

    public ImageRequestBuilder setCacheChoice(ImageRequest.CacheChoice cacheChoice) {
        this.mCacheChoice = cacheChoice;
        return this;
    }

    public ImageRequestBuilder setImageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
        this.mImageDecodeOptions = imageDecodeOptions;
        return this;
    }

    public ImageRequestBuilder setInSampleSize(int i2) {
        this.inSampleSize = i2;
        return this;
    }

    public ImageRequestBuilder setIsScale(boolean z) {
        this.isScale = z;
        return this;
    }

    public ImageRequestBuilder setIsUseThumbnail(boolean z) {
        this.isUseThumbnail = z;
        return this;
    }

    public ImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean z) {
        this.mLocalThumbnailPreviewsEnabled = z;
        return this;
    }

    public ImageRequestBuilder setLowestPermittedRequestLevel(ImageRequest.RequestLevel requestLevel) {
        this.mLowestPermittedRequestLevel = requestLevel;
        return this;
    }

    public ImageRequestBuilder setPostprocessor(@Nullable Postprocessor postprocessor) {
        this.mPostprocessor = postprocessor;
        return this;
    }

    public ImageRequestBuilder setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
        return this;
    }

    public ImageRequestBuilder setReferer(String str) {
        this.referer = str;
        return this;
    }

    public ImageRequestBuilder setRequestListener(RequestListener requestListener) {
        this.mRequestListener = requestListener;
        return this;
    }

    public ImageRequestBuilder setRequestPriority(Priority priority) {
        this.mRequestPriority = priority;
        return this;
    }

    public ImageRequestBuilder setResizeOptions(@Nullable ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
        return this;
    }

    public ImageRequestBuilder setResizingAllowedOverride(@Nullable Boolean bool) {
        this.mResizingAllowedOverride = bool;
        return this;
    }

    public ImageRequestBuilder setRotationOptions(@Nullable RotationOptions rotationOptions) {
        this.mRotationOptions = rotationOptions;
        return this;
    }

    public ImageRequestBuilder setShouldDecodePrefetches(@Nullable Boolean bool) {
        this.mDecodePrefetches = bool;
        return this;
    }

    public ImageRequestBuilder setSource(Uri uri) {
        Preconditions.checkNotNull(uri);
        this.mSourceUri = uri;
        return this;
    }

    @Nullable
    public Boolean shouldDecodePrefetches() {
        return this.mDecodePrefetches;
    }

    protected void validate() {
        Uri uri = this.mSourceUri;
        if (uri == null) {
            throw new BuilderException("Source must be set!");
        }
        if (UriUtil.isLocalResourceUri(uri)) {
            if (!this.mSourceUri.isAbsolute()) {
                throw new BuilderException("Resource URI path must be absolute.");
            }
            if (this.mSourceUri.getPath().isEmpty()) {
                throw new BuilderException("Resource URI must not be empty");
            }
            try {
                Integer.parseInt(this.mSourceUri.getPath().substring(1));
            } catch (NumberFormatException unused) {
                throw new BuilderException("Resource URI path must be a resource id.");
            }
        }
        if (UriUtil.isLocalAssetUri(this.mSourceUri) && !this.mSourceUri.isAbsolute()) {
            throw new BuilderException("Asset URI path must be absolute.");
        }
    }
}
