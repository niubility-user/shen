package com.facebook.drawee.backends.pipeline.debug;

import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;

/* loaded from: classes.dex */
public class DebugOverlayImageOriginListener implements ImageOriginListener {
    private int mImageOrigin = 1;

    public int getImageOrigin() {
        return this.mImageOrigin;
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImageOriginListener
    public void onImageLoaded(String str, int i2, boolean z, String str2) {
        this.mImageOrigin = i2;
    }
}
