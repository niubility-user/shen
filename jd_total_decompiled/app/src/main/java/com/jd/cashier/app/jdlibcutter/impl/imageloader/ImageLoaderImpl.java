package com.jd.cashier.app.jdlibcutter.impl.imageloader;

import android.widget.ImageView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.IImageLoader;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes13.dex */
public class ImageLoaderImpl implements IImageLoader {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.IImageLoader
    public void displayImage(final String str, ImageView imageView, ImageLoaderOptions imageLoaderOptions, final ImageLoadingListener imageLoadingListener) {
        JDDisplayImageOptions jDDisplayImageOptions;
        if (imageLoaderOptions != null) {
            jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.setPlaceholder(imageLoaderOptions.placeHolderType);
        } else {
            jDDisplayImageOptions = null;
        }
        if (imageLoadingListener != null) {
            imageLoadingListener.onLoadingStarted(str);
        }
        JDImageLoader.display(str, imageView, jDDisplayImageOptions, new ImageRequestListener<ImageInfo>() { // from class: com.jd.cashier.app.jdlibcutter.impl.imageloader.ImageLoaderImpl.1
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
                ImageLoadingListener imageLoadingListener2 = imageLoadingListener;
                if (imageLoadingListener2 != null) {
                    imageLoadingListener2.onLoadingCancelled(str);
                }
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
                ImageLoadingListener imageLoadingListener2 = imageLoadingListener;
                if (imageLoadingListener2 != null) {
                    imageLoadingListener2.onLoadingFailed(str);
                }
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(ImageInfo imageInfo) {
                ImageLoadingListener imageLoadingListener2 = imageLoadingListener;
                if (imageLoadingListener2 != null) {
                    imageLoadingListener2.onLoadingComplete(str, null);
                }
            }
        });
    }
}
