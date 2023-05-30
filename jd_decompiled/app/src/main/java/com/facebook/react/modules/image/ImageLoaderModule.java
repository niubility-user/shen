package com.facebook.react.modules.image;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.jingdong.common.web.managers.WebPerfManager;
import javax.annotation.Nullable;

@ReactModule(name = ImageLoaderModule.NAME)
/* loaded from: classes12.dex */
public class ImageLoaderModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> mEnqueuedRequests;

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = this;
    }

    private void registerRequest(int i2, DataSource<Void> dataSource) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(i2, dataSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public DataSource<Void> removeRequest(int i2) {
        DataSource<Void> dataSource;
        synchronized (this.mEnqueuedRequestMonitor) {
            dataSource = this.mEnqueuedRequests.get(i2);
            this.mEnqueuedRequests.remove(i2);
        }
        return dataSource;
    }

    @ReactMethod
    public void abortRequest(int i2) {
        DataSource<Void> removeRequest = removeRequest(i2);
        if (removeRequest != null) {
            removeRequest.close();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getSize(String str, final Promise promise) {
        if (str != null && !str.isEmpty()) {
            Fresco.getImagePipeline().fetchEncodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), this.mCallerContext).subscribe(new BaseDataSubscriber<CloseableReference<PooledByteBuffer>>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.1
                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onCancelImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onFailureImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onNewResultImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                    if (dataSource.isFinished()) {
                        CloseableReference<PooledByteBuffer> result = dataSource.getResult();
                        try {
                            if (result != null) {
                                try {
                                    EncodedImage encodedImage = new EncodedImage(result);
                                    WritableMap createMap = Arguments.createMap();
                                    createMap.putInt("width", encodedImage.getWidth());
                                    createMap.putInt("height", encodedImage.getHeight());
                                    promise.resolve(createMap);
                                } catch (Exception e2) {
                                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e2);
                                }
                                return;
                            }
                            promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                        } finally {
                            CloseableReference.closeSafely(result);
                        }
                    }
                }
            }, CallerThreadExecutor.getInstance());
            return;
        }
        promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i2 = 0; i2 < size; i2++) {
                DataSource<Void> valueAt = this.mEnqueuedRequests.valueAt(i2);
                if (valueAt != null) {
                    valueAt.close();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    @ReactMethod
    public void prefetchImage(String str, final int i2, final Promise promise) {
        if (str != null && !str.isEmpty()) {
            DataSource<Void> prefetchToDiskCache = Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), this.mCallerContext);
            BaseDataSubscriber<Void> baseDataSubscriber = new BaseDataSubscriber<Void>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.2
                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onCancelImpl(DataSource<Void> dataSource) {
                    promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onFailureImpl(DataSource<Void> dataSource) {
                    try {
                        ImageLoaderModule.this.removeRequest(i2);
                        promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, dataSource.getFailureCause());
                    } finally {
                        dataSource.close();
                    }
                }

                @Override // com.facebook.datasource.BaseDataSubscriber
                protected void onNewResultImpl(DataSource<Void> dataSource) {
                    if (dataSource.isFinished()) {
                        try {
                            ImageLoaderModule.this.removeRequest(i2);
                            promise.resolve(Boolean.TRUE);
                        } finally {
                            dataSource.close();
                        }
                    }
                }
            };
            registerRequest(i2, prefetchToDiskCache);
            prefetchToDiskCache.subscribe(baseDataSubscriber, CallerThreadExecutor.getInstance());
            return;
        }
        promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
    }

    @ReactMethod
    public void queryCache(final ReadableArray readableArray, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.image.ImageLoaderModule.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap createMap = Arguments.createMap();
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                for (int i2 = 0; i2 < readableArray.size(); i2++) {
                    String string = readableArray.getString(i2);
                    Uri parse = Uri.parse(string);
                    if (imagePipeline.isInBitmapMemoryCache(parse)) {
                        createMap.putString(string, WebPerfManager.MEMORY);
                    } else if (imagePipeline.isInDiskCacheSync(parse)) {
                        createMap.putString(string, "disk");
                    }
                }
                promise.resolve(createMap);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, Object obj) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = obj;
    }
}
