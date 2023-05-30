package com.jingdong.common.impl.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.common.protocol.imageloader.ImageLoaderListener;
import com.jingdong.common.protocol.imageloader.ImageLoaderProtocol;
import com.jingdong.common.protocol.imageloader.ImageLoadingProgressListener;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes5.dex */
public class JDImageLoader implements ImageLoaderProtocol<JDImageOptionsHolder> {
    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void cancelDisplayTask(ImageView imageView) {
        JDImageUtils.cancelDisplayTask(imageView);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public Bitmap getBitmap(Context context, int i2) {
        return JDImageUtils.getBitmap(context, i2);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public Bitmap getBitmapWithJpg16(Context context, int i2) {
        return JDImageUtils.getBitmapWithJpg16(context, i2);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView) {
        JDImageUtils.displayImage(str, imageView);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, ImageLoaderListener imageLoaderListener) {
        displayImage(str, imageView, (JDImageOptionsHolder) null, imageLoaderListener);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, JDImageOptionsHolder jDImageOptionsHolder) {
        JDImageUtils.displayImage(str, imageView, jDImageOptionsHolder != null ? jDImageOptionsHolder.getOptions() : null);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, JDImageOptionsHolder jDImageOptionsHolder, boolean z) {
        JDImageUtils.displayImage(str, imageView, jDImageOptionsHolder != null ? jDImageOptionsHolder.getOptions() : null, z);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, JDImageOptionsHolder jDImageOptionsHolder, ImageLoaderListener imageLoaderListener) {
        displayImage(str, imageView, jDImageOptionsHolder, imageLoaderListener, (ImageLoadingProgressListener) null);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, JDImageOptionsHolder jDImageOptionsHolder, ImageLoaderListener imageLoaderListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        displayImage(str, imageView, jDImageOptionsHolder, true, imageLoaderListener, imageLoadingProgressListener);
    }

    @Override // com.jingdong.common.protocol.imageloader.ImageLoaderProtocol
    public void displayImage(String str, ImageView imageView, JDImageOptionsHolder jDImageOptionsHolder, boolean z, final ImageLoaderListener imageLoaderListener, final ImageLoadingProgressListener imageLoadingProgressListener) {
        JDImageUtils.displayImage(str, imageView, jDImageOptionsHolder != null ? jDImageOptionsHolder.getOptions() : null, z, new JDImageLoadingListener() { // from class: com.jingdong.common.impl.imageloader.JDImageLoader.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                ImageLoaderListener imageLoaderListener2 = imageLoaderListener;
                if (imageLoaderListener2 != null) {
                    imageLoaderListener2.onLoadingCancelled(str2, view);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                ImageLoaderListener imageLoaderListener2 = imageLoaderListener;
                if (imageLoaderListener2 != null) {
                    imageLoaderListener2.onLoadingComplete(str2, view, bitmap);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                ImageLoaderListener imageLoaderListener2 = imageLoaderListener;
                if (imageLoaderListener2 != null) {
                    imageLoaderListener2.onLoadingFailed(str2, view, jDFailReason == null ? "" : jDFailReason.toString());
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                ImageLoaderListener imageLoaderListener2 = imageLoaderListener;
                if (imageLoaderListener2 != null) {
                    imageLoaderListener2.onLoadingStarted(str2, view);
                }
            }
        }, new JDImageLoadingProgressListener() { // from class: com.jingdong.common.impl.imageloader.JDImageLoader.2
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingProgressListener
            public void onProgressUpdate(String str2, View view, int i2, int i3) {
                ImageLoadingProgressListener imageLoadingProgressListener2 = imageLoadingProgressListener;
                if (imageLoadingProgressListener2 != null) {
                    imageLoadingProgressListener2.onProgressUpdate(str2, view, i2, i3);
                }
            }
        });
    }
}
