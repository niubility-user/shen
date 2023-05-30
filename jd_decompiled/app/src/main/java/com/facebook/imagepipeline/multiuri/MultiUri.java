package com.facebook.imagepipeline.multiuri;

import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class MultiUri {
    private static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    @Nullable
    private ImageRequest mLowResImageRequest;
    @Nullable
    private ImageRequest[] mMultiImageRequests;

    /* loaded from: classes.dex */
    public static class Builder {
        @Nullable
        private ImageRequest mLowResImageRequest;
        @Nullable
        private ImageRequest[] mMultiImageRequests;

        private Builder() {
        }

        public MultiUri build() {
            return new MultiUri(this);
        }

        public Builder setImageRequests(@Nullable ImageRequest... imageRequestArr) {
            this.mMultiImageRequests = imageRequestArr;
            return this;
        }

        public Builder setLowResImageRequest(@Nullable ImageRequest imageRequest) {
            this.mLowResImageRequest = imageRequest;
            return this;
        }
    }

    private MultiUri(Builder builder) {
        this.mLowResImageRequest = builder.mLowResImageRequest;
        this.mMultiImageRequests = builder.mMultiImageRequests;
    }

    public static Builder create() {
        return new Builder();
    }

    private static Supplier<DataSource<CloseableReference<CloseableImage>>> getFirstAvailableDataSourceSupplier(ImagePipeline imagePipeline, Object obj, @Nullable RequestListener requestListener, ImageRequest[] imageRequestArr, boolean z, @Nullable String str) {
        ArrayList arrayList = new ArrayList(imageRequestArr.length * 2);
        if (z) {
            for (ImageRequest imageRequest : imageRequestArr) {
                arrayList.add(getImageRequestDataSourceSupplier(imagePipeline, imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE, requestListener, str));
            }
        }
        for (ImageRequest imageRequest2 : imageRequestArr) {
            arrayList.add(getImageRequestDataSourceSupplier(imagePipeline, imageRequest2, obj, requestListener, str));
        }
        return FirstAvailableDataSourceSupplier.create(arrayList);
    }

    public static DataSource<CloseableReference<CloseableImage>> getImageRequestDataSource(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        return imagePipeline.fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener, str);
    }

    private static Supplier<DataSource<CloseableReference<CloseableImage>>> getImageRequestDataSourceSupplier(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, RequestListener requestListener, @Nullable String str) {
        return getImageRequestDataSourceSupplier(imagePipeline, imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener, str);
    }

    private static Supplier<DataSource<CloseableReference<CloseableImage>>> getImageRequestDataSourceSupplier(final ImagePipeline imagePipeline, final ImageRequest imageRequest, final Object obj, ImageRequest.RequestLevel requestLevel, final RequestListener requestListener, @Nullable final String str) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.multiuri.MultiUri.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return MultiUri.getImageRequestDataSource(ImagePipeline.this, imageRequest, obj, requestListener, str);
            }
        };
    }

    public static Supplier<DataSource<CloseableReference<CloseableImage>>> getMultiUriDatasource(ImagePipeline imagePipeline, ImageRequest imageRequest, ImageRequest imageRequest2, Object obj) {
        return getMultiUriDatasourceSupplier(imagePipeline, create().setLowResImageRequest(imageRequest).setImageRequests(imageRequest2).build(), null, obj, null, null);
    }

    public static Supplier<DataSource<CloseableReference<CloseableImage>>> getMultiUriDatasourceSupplier(ImagePipeline imagePipeline, MultiUri multiUri, @Nullable ImageRequest imageRequest, Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        Supplier<DataSource<CloseableReference<CloseableImage>>> imageRequestDataSourceSupplier = imageRequest != null ? getImageRequestDataSourceSupplier(imagePipeline, imageRequest, obj, requestListener, str) : multiUri.getMultiImageRequests() != null ? getFirstAvailableDataSourceSupplier(imagePipeline, obj, requestListener, multiUri.getMultiImageRequests(), true, str) : null;
        if (imageRequestDataSourceSupplier != null && multiUri.getLowResImageRequest() != null) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(imageRequestDataSourceSupplier);
            linkedList.add(getImageRequestDataSourceSupplier(imagePipeline, multiUri.getLowResImageRequest(), obj, requestListener, str));
            imageRequestDataSourceSupplier = IncreasingQualityDataSourceSupplier.create(linkedList, false);
        }
        return imageRequestDataSourceSupplier == null ? DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION) : imageRequestDataSourceSupplier;
    }

    @Nullable
    public ImageRequest getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public ImageRequest[] getMultiImageRequests() {
        return this.mMultiImageRequests;
    }
}
