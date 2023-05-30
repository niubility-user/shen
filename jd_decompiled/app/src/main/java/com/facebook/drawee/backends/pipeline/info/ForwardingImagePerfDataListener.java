package com.facebook.drawee.backends.pipeline.info;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ForwardingImagePerfDataListener implements ImagePerfDataListener {
    private final Collection<ImagePerfDataListener> mListeners;

    public ForwardingImagePerfDataListener(Collection<ImagePerfDataListener> collection) {
        this.mListeners = collection;
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener
    public void onImageLoadStatusUpdated(ImagePerfData imagePerfData, int i2) {
        Iterator<ImagePerfDataListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageLoadStatusUpdated(imagePerfData, i2);
        }
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener
    public void onImageVisibilityUpdated(ImagePerfData imagePerfData, int i2) {
        Iterator<ImagePerfDataListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageVisibilityUpdated(imagePerfData, i2);
        }
    }
}
