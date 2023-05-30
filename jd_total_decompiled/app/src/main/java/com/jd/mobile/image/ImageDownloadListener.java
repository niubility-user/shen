package com.jd.mobile.image;

/* loaded from: classes17.dex */
public abstract class ImageDownloadListener implements ImageRequestListener<String> {
    @Override // com.jd.mobile.image.ImageRequestListener
    public abstract void onCancel();

    @Override // com.jd.mobile.image.ImageRequestListener
    public abstract void onFailure(Throwable th);

    @Override // com.jd.mobile.image.ImageRequestListener
    public abstract void onSuccess(String str);
}
