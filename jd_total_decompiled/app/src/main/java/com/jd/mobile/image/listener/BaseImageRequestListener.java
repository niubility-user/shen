package com.jd.mobile.image.listener;

import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;

/* loaded from: classes17.dex */
public class BaseImageRequestListener implements ImageRequestListener<ImageInfo> {
    public void onAnimationStop() {
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onCancel() {
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onFailure(Throwable th) {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.mobile.image.ImageRequestListener
    public void onSuccess(ImageInfo imageInfo) {
    }

    public void onSuccess(AnimatedImageInfo animatedImageInfo) {
    }
}
