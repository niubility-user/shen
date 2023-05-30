package com.jingdong.jdreact.plugin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/* loaded from: classes14.dex */
public class FrescoUtil {

    /* loaded from: classes14.dex */
    public interface ImageLoadListener {
        void onLoadFailed(String str, Throwable th);

        void onLoadSuccess(String str, Bitmap bitmap);
    }

    public static void displayImage(SimpleDraweeView simpleDraweeView, String str) {
        displayImage(simpleDraweeView, str, 0);
    }

    public static void displayImageByPath(SimpleDraweeView simpleDraweeView, String str) {
        displayImage(simpleDraweeView, "file://" + str, 0);
    }

    public static void displayImageWithListener(SimpleDraweeView simpleDraweeView, final String str, final ImageLoadListener imageLoadListener) {
        if (simpleDraweeView == null || str == null) {
            return;
        }
        Uri parse = Uri.parse(str);
        simpleDraweeView.setController(Fresco.getDraweeControllerBuilderSupplier().get().setUri(parse).setOldController(simpleDraweeView.getController()).setImageRequest(ImageRequestBuilder.newBuilderWithSource(parse).build()).setControllerListener(new ControllerListener<ImageInfo>() { // from class: com.jingdong.jdreact.plugin.utils.FrescoUtil.1
            @Override // com.facebook.drawee.controller.ControllerListener
            public void onFailure(String str2, Throwable th) {
                ImageLoadListener imageLoadListener2 = imageLoadListener;
                if (imageLoadListener2 != null) {
                    imageLoadListener2.onLoadFailed(str, th);
                }
            }

            @Override // com.facebook.drawee.controller.ControllerListener
            public void onIntermediateImageFailed(String str2, Throwable th) {
            }

            @Override // com.facebook.drawee.controller.ControllerListener
            public void onIntermediateImageSet(String str2, @Nullable ImageInfo imageInfo) {
            }

            @Override // com.facebook.drawee.controller.ControllerListener
            public void onRelease(String str2) {
            }

            @Override // com.facebook.drawee.controller.ControllerListener
            public void onSubmit(String str2, Object obj) {
            }

            public void onFinalImageSet(String str2, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                Bitmap underlyingBitmap = imageInfo instanceof CloseableBitmap ? ((CloseableBitmap) imageInfo).getUnderlyingBitmap() : null;
                ImageLoadListener imageLoadListener2 = imageLoadListener;
                if (imageLoadListener2 != null) {
                    imageLoadListener2.onLoadSuccess(str, underlyingBitmap);
                }
            }
        }).build());
    }

    public static void loadImage(Context context, final String str, final ImageLoadListener imageLoadListener) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), context).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: com.jingdong.jdreact.plugin.utils.FrescoUtil.2
            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                ImageLoadListener imageLoadListener2 = imageLoadListener;
                if (imageLoadListener2 != null) {
                    imageLoadListener2.onLoadFailed(str, dataSource.getFailureCause());
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                CloseableReference<CloseableImage> result;
                if (dataSource.isFinished() && (result = dataSource.getResult()) != null) {
                    try {
                        CloseableImage closeableImage = result.get();
                        Bitmap createBitmap = closeableImage instanceof CloseableBitmap ? Bitmap.createBitmap(((CloseableBitmap) closeableImage).getUnderlyingBitmap()) : null;
                        ImageLoadListener imageLoadListener2 = imageLoadListener;
                        if (imageLoadListener2 != null) {
                            imageLoadListener2.onLoadSuccess(str, createBitmap);
                        }
                    } finally {
                        CloseableReference.closeSafely(result);
                    }
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    public static void setImageByResId(SimpleDraweeView simpleDraweeView, int i2) {
        if (i2 <= 0) {
            return;
        }
        displayImage(simpleDraweeView, "res:///" + i2, 0);
    }

    public static void setPlaceHolder(SimpleDraweeView simpleDraweeView, int i2) {
        if (simpleDraweeView == null || i2 <= 0) {
            return;
        }
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        if (hierarchy == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).build());
            hierarchy = simpleDraweeView.getHierarchy();
        }
        hierarchy.setPlaceholderImage(i2);
    }

    public static void displayImage(SimpleDraweeView simpleDraweeView, String str, int i2) {
        if (simpleDraweeView == null || str == null) {
            return;
        }
        Uri parse = Uri.parse(str);
        setPlaceHolder(simpleDraweeView, i2);
        simpleDraweeView.setController(Fresco.getDraweeControllerBuilderSupplier().get().setUri(parse).setOldController(simpleDraweeView.getController()).setImageRequest(ImageRequestBuilder.newBuilderWithSource(parse).build()).build());
    }
}
