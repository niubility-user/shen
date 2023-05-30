package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfControllerListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfMonitor {
    private boolean mEnabled;
    @Nullable
    private ForwardingRequestListener mForwardingRequestListener;
    @Nullable
    private ImageOriginListener mImageOriginListener;
    @Nullable
    private ImageOriginRequestListener mImageOriginRequestListener;
    @Nullable
    private ImagePerfControllerListener mImagePerfControllerListener;
    @Nullable
    private List<ImagePerfDataListener> mImagePerfDataListeners;
    @Nullable
    private ImagePerfRequestListener mImagePerfRequestListener;
    private final ImagePerfState mImagePerfState = new ImagePerfState();
    private final MonotonicClock mMonotonicClock;
    private final PipelineDraweeController mPipelineDraweeController;

    public ImagePerfMonitor(MonotonicClock monotonicClock, PipelineDraweeController pipelineDraweeController) {
        this.mMonotonicClock = monotonicClock;
        this.mPipelineDraweeController = pipelineDraweeController;
    }

    private void setupListeners() {
        if (this.mImagePerfControllerListener == null) {
            this.mImagePerfControllerListener = new ImagePerfControllerListener(this.mMonotonicClock, this.mImagePerfState, this);
        }
        if (this.mImagePerfRequestListener == null) {
            this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
        }
        if (this.mImageOriginListener == null) {
            this.mImageOriginListener = new ImagePerfImageOriginListener(this.mImagePerfState, this);
        }
        ImageOriginRequestListener imageOriginRequestListener = this.mImageOriginRequestListener;
        if (imageOriginRequestListener == null) {
            this.mImageOriginRequestListener = new ImageOriginRequestListener(this.mPipelineDraweeController.getId(), this.mImageOriginListener);
        } else {
            imageOriginRequestListener.init(this.mPipelineDraweeController.getId());
        }
        if (this.mForwardingRequestListener == null) {
            this.mForwardingRequestListener = new ForwardingRequestListener(this.mImagePerfRequestListener, this.mImageOriginRequestListener);
        }
    }

    public void addImagePerfDataListener(@Nullable ImagePerfDataListener imagePerfDataListener) {
        if (imagePerfDataListener == null) {
            return;
        }
        if (this.mImagePerfDataListeners == null) {
            this.mImagePerfDataListeners = new LinkedList();
        }
        this.mImagePerfDataListeners.add(imagePerfDataListener);
    }

    public void addViewportData() {
        DraweeHierarchy hierarchy = this.mPipelineDraweeController.getHierarchy();
        if (hierarchy == null || hierarchy.getTopLevelDrawable() == null) {
            return;
        }
        Rect bounds = hierarchy.getTopLevelDrawable().getBounds();
        this.mImagePerfState.setOnScreenWidth(bounds.width());
        this.mImagePerfState.setOnScreenHeight(bounds.height());
    }

    public void clearImagePerfDataListeners() {
        List<ImagePerfDataListener> list = this.mImagePerfDataListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, int i2) {
        List<ImagePerfDataListener> list;
        if (!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty()) {
            return;
        }
        ImagePerfData snapshot = imagePerfState.snapshot();
        Iterator<ImagePerfDataListener> it = this.mImagePerfDataListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageVisibilityUpdated(snapshot, i2);
        }
    }

    public void notifyStatusUpdated(ImagePerfState imagePerfState, int i2) {
        List<ImagePerfDataListener> list;
        imagePerfState.setImageLoadStatus(i2);
        if (!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty()) {
            return;
        }
        if (i2 == 3) {
            addViewportData();
        }
        ImagePerfData snapshot = imagePerfState.snapshot();
        Iterator<ImagePerfDataListener> it = this.mImagePerfDataListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageLoadStatusUpdated(snapshot, i2);
        }
    }

    public void removeImagePerfDataListener(ImagePerfDataListener imagePerfDataListener) {
        List<ImagePerfDataListener> list = this.mImagePerfDataListeners;
        if (list == null) {
            return;
        }
        list.remove(imagePerfDataListener);
    }

    public void reset() {
        clearImagePerfDataListeners();
        setEnabled(false);
        this.mImagePerfState.reset();
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (!z) {
            ImageOriginListener imageOriginListener = this.mImageOriginListener;
            if (imageOriginListener != null) {
                this.mPipelineDraweeController.removeImageOriginListener(imageOriginListener);
            }
            ImagePerfControllerListener imagePerfControllerListener = this.mImagePerfControllerListener;
            if (imagePerfControllerListener != null) {
                this.mPipelineDraweeController.removeControllerListener(imagePerfControllerListener);
            }
            ForwardingRequestListener forwardingRequestListener = this.mForwardingRequestListener;
            if (forwardingRequestListener != null) {
                this.mPipelineDraweeController.removeRequestListener(forwardingRequestListener);
                return;
            }
            return;
        }
        setupListeners();
        ImageOriginListener imageOriginListener2 = this.mImageOriginListener;
        if (imageOriginListener2 != null) {
            this.mPipelineDraweeController.addImageOriginListener(imageOriginListener2);
        }
        ImagePerfControllerListener imagePerfControllerListener2 = this.mImagePerfControllerListener;
        if (imagePerfControllerListener2 != null) {
            this.mPipelineDraweeController.addControllerListener(imagePerfControllerListener2);
        }
        ForwardingRequestListener forwardingRequestListener2 = this.mForwardingRequestListener;
        if (forwardingRequestListener2 != null) {
            this.mPipelineDraweeController.addRequestListener(forwardingRequestListener2);
        }
    }

    public void updateImageRequestData(AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo> abstractDraweeControllerBuilder) {
        this.mImagePerfState.setControllerImageRequests(abstractDraweeControllerBuilder.getImageRequest(), abstractDraweeControllerBuilder.getLowResImageRequest(), abstractDraweeControllerBuilder.getFirstAvailableImageRequests());
    }
}
