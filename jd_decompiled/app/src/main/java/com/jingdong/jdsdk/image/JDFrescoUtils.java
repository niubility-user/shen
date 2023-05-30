package com.jingdong.jdsdk.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.facebook.animated.giflite.GifDecoder;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.assist.JDFailType;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import java.io.File;
import java.util.List;

/* loaded from: classes14.dex */
public class JDFrescoUtils {
    private static String TAG = "JDFrescoUtils";

    /* loaded from: classes14.dex */
    public class a extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ JDImageLoadingListener a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ SimpleDraweeView f12885c;

        a(JDImageLoadingListener jDImageLoadingListener, String str, SimpleDraweeView simpleDraweeView) {
            this.a = jDImageLoadingListener;
            this.b = str;
            this.f12885c = simpleDraweeView;
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onCancelled() {
            FLog.d(JDFrescoUtils.TAG, "load cancelled:" + this.b);
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingCancelled(this.b, this.f12885c);
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFailure(String str, Throwable th) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(this.b, this.f12885c, new JDFailReason(JDFailType.UNKNOWN, th));
            }
            FLog.d(JDFrescoUtils.TAG, "load failure:" + this.b);
            th.printStackTrace();
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
            if (imageInfo == null) {
                JDImageLoadingListener jDImageLoadingListener = this.a;
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingFailed(this.b, this.f12885c, new JDFailReason(JDFailType.UNKNOWN, null));
                    return;
                }
                return;
            }
            FLog.d(JDFrescoUtils.TAG, "load success:" + this.b + ",width:" + imageInfo.getWidth() + ",height:" + imageInfo.getHeight());
            JDImageLoadingListener jDImageLoadingListener2 = this.a;
            if (jDImageLoadingListener2 != null) {
                jDImageLoadingListener2.onLoadingComplete(this.b, this.f12885c, com.jd.mobile.image.utils.b.b(drawable, imageInfo.getWidth(), imageInfo.getHeight()));
            }
            if ((this.f12885c.getLayoutParams().width == -2 || this.f12885c.getLayoutParams().height == -2) && this.f12885c.getAspectRatio() == 0.0f) {
                JDFrescoUtils.resetWidthOrHeight(imageInfo, this.f12885c);
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onSubmit(String str, Object obj) {
            FLog.d(JDFrescoUtils.TAG, "load start:" + this.b);
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingStarted(this.b, this.f12885c);
            }
        }
    }

    /* loaded from: classes14.dex */
    public class b implements JDImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ JDImageLoadingListener f12886g;

        /* renamed from: h */
        final /* synthetic */ SimpleDraweeView f12887h;

        /* renamed from: i */
        final /* synthetic */ String f12888i;

        /* renamed from: j */
        final /* synthetic */ JDDisplayImageOptions f12889j;

        /* renamed from: k */
        final /* synthetic */ ControllerListener f12890k;

        /* loaded from: classes14.dex */
        class a implements View.OnLongClickListener {
            a() {
                b.this = r1;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                b bVar = b.this;
                ImageRequest createRequestByOption = JDFrescoUtils.createRequestByOption(bVar.f12888i, bVar.f12889j, ImageRequest.RequestLevel.FULL_FETCH, bVar.f12887h);
                b bVar2 = b.this;
                b.this.f12887h.setController(JDFrescoUtils.getDraweeController(createRequestByOption, bVar2.f12887h, bVar2.f12890k));
                return true;
            }
        }

        b(JDImageLoadingListener jDImageLoadingListener, SimpleDraweeView simpleDraweeView, String str, JDDisplayImageOptions jDDisplayImageOptions, ControllerListener controllerListener) {
            this.f12886g = jDImageLoadingListener;
            this.f12887h = simpleDraweeView;
            this.f12888i = str;
            this.f12889j = jDDisplayImageOptions;
            this.f12890k = controllerListener;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
            JDImageLoadingListener jDImageLoadingListener = this.f12886g;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingCancelled(str, view);
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            JDImageLoadingListener jDImageLoadingListener = this.f12886g;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingComplete(str, view, bitmap);
            }
            this.f12887h.setOnLongClickListener(null);
            this.f12887h.setLongClickable(false);
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            this.f12887h.setOnLongClickListener(new a());
            JDImageLoadingListener jDImageLoadingListener = this.f12886g;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(this.f12888i, this.f12887h, new JDFailReason(JDFailType.UNKNOWN, null));
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            JDImageLoadingListener jDImageLoadingListener = this.f12886g;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingStarted(str, view);
            }
        }
    }

    /* loaded from: classes14.dex */
    public class c extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ JDImageLoadingListener a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ SimpleDraweeView f12892c;

        c(JDImageLoadingListener jDImageLoadingListener, String str, SimpleDraweeView simpleDraweeView) {
            this.a = jDImageLoadingListener;
            this.b = str;
            this.f12892c = simpleDraweeView;
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onCancelled() {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingCancelled(this.b, this.f12892c);
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFailure(String str, Throwable th) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(this.b, this.f12892c, new JDFailReason(JDFailType.UNKNOWN, th));
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (imageInfo == null) {
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingFailed(this.b, this.f12892c, new JDFailReason(JDFailType.UNKNOWN, null));
                    return;
                }
                return;
            }
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingComplete(this.b, this.f12892c, com.jd.mobile.image.utils.b.b(drawable, imageInfo.getWidth(), imageInfo.getHeight()));
            }
            if ((this.f12892c.getLayoutParams().width == -2 || this.f12892c.getLayoutParams().height == -2) && this.f12892c.getAspectRatio() == 0.0f) {
                JDFrescoUtils.resetWidthOrHeight(imageInfo, this.f12892c);
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onSubmit(String str, Object obj) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingStarted(this.b, this.f12892c);
            }
        }
    }

    /* loaded from: classes14.dex */
    public class d extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
        final /* synthetic */ JDImageLoadingListener a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ ImageView f12893c;

        d(JDImageLoadingListener jDImageLoadingListener, String str, ImageView imageView) {
            this.a = jDImageLoadingListener;
            this.b = str;
            this.f12893c = imageView;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingCancelled(this.b, this.f12893c);
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            Throwable failureCause = dataSource.getFailureCause();
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(this.b, this.f12893c, new JDFailReason(JDFailType.UNKNOWN, failureCause));
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            dataSource.isFinished();
            CloseableReference<CloseableImage> result = dataSource.getResult();
            if (result != null) {
                try {
                    CloseableImage closeableImage = result.get();
                    Bitmap bitmap = null;
                    if (!(closeableImage instanceof CloseableAnimatedImage)) {
                        bitmap = Bitmap.createBitmap(((CloseableBitmap) closeableImage).getUnderlyingBitmap());
                    }
                    JDImageLoadingListener jDImageLoadingListener = this.a;
                    if (jDImageLoadingListener != null) {
                        jDImageLoadingListener.onLoadingComplete(this.b, this.f12893c, bitmap);
                    }
                } finally {
                    result.close();
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    public class e extends BaseDataSubscriber<Void> {
        final /* synthetic */ JDImageLoadingListener a;
        final /* synthetic */ String b;

        e(JDImageLoadingListener jDImageLoadingListener, String str) {
            this.a = jDImageLoadingListener;
            this.b = str;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(DataSource<Void> dataSource) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingCancelled(this.b, null);
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(DataSource<Void> dataSource) {
            Throwable failureCause = dataSource.getFailureCause();
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(this.b, null, new JDFailReason(JDFailType.UNKNOWN, failureCause));
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onNewResultImpl(DataSource<Void> dataSource) {
            JDImageLoadingListener jDImageLoadingListener = this.a;
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingComplete(this.b, null, null);
            }
        }
    }

    private static GenericDraweeHierarchy OptionToGenericDraweeHierarchy(String str, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        int i2;
        GenericDraweeHierarchyBuilder builder = simpleDraweeView.getHierarchy().getBuilder();
        if (jDDisplayImageOptions != null) {
            if (jDDisplayImageOptions.getColorFilter() != null) {
                builder.setActualImageColorFilter(jDDisplayImageOptions.getColorFilter());
            }
            builder.setFadeDuration(jDDisplayImageOptions.getFadeDurationMs());
            if (jDDisplayImageOptions.getRoundingParams() != null) {
                builder.setRoundingParams(jDDisplayImageOptions.getRoundingParams());
            }
            if (builder.getRoundingParams() != null) {
                builder.getRoundingParams().setPaintFilterBitmap(true);
            }
            Resources resources = Fresco.getContext().getResources();
            int placeholder = jDDisplayImageOptions.getPlaceholder();
            drawable3 = (placeholder <= 0 || placeholder > 23) ? placeholder != 0 ? resources.getDrawable(placeholder) : null : new JDPlaceholderDrawable(placeholder, builder.getRoundingParams());
            try {
                drawable2 = jDDisplayImageOptions.getImageOnLoading(resources);
                try {
                    drawable2 = com.jd.mobile.image.utils.b.c(drawable2);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                drawable2 = null;
            }
            try {
                drawable = jDDisplayImageOptions.getImageOnFail(resources);
                try {
                    drawable = com.jd.mobile.image.utils.b.c(drawable);
                } catch (Throwable unused3) {
                }
            } catch (Throwable unused4) {
                drawable = null;
            }
        } else {
            drawable = null;
            drawable2 = null;
            drawable3 = null;
        }
        if (drawable3 == null) {
            drawable3 = new JDPlaceholderDrawable(17, builder.getRoundingParams());
        }
        ScalingUtils.ScaleType a2 = simpleDraweeView.getScaleType() != null ? com.jd.mobile.image.utils.a.a(simpleDraweeView.getScaleType()) : null;
        if (a2 != null) {
            builder.setActualImageScaleType(a2);
        }
        if (drawable != null) {
            builder.setFailureImage(drawable, a2);
        } else if (z) {
            builder.setFailureImage(drawable3, a2);
        }
        if (JdImageToolKit.getEngine().getImageControllerImpl().needNoImage()) {
            String string = JdImageToolKit.getEngine().getApplicationContext().getString(R.string.image_need_long_click);
            int i3 = -1;
            if (jDDisplayImageOptions != null) {
                if (!TextUtils.isEmpty(jDDisplayImageOptions.getNoImageText())) {
                    string = jDDisplayImageOptions.getNoImageText();
                }
                i3 = jDDisplayImageOptions.getNoImageTextSize();
                i2 = jDDisplayImageOptions.getNoImageTextGap();
            } else {
                i2 = -1;
            }
            builder.setFailureImage(new ExceptionDrawable(string, i3, i2), a2);
        } else if (drawable2 != null) {
            builder.setPlaceholderImage(drawable2, a2);
        } else if (z) {
            builder.setPlaceholderImage(drawable3, a2);
        }
        GenericDraweeHierarchy build = builder.build();
        build.setChangeImageListener(simpleDraweeView.getChangeImageListener());
        return build;
    }

    public static void cancelDisplayTask(ImageView imageView) {
        if (imageView instanceof SimpleDraweeView) {
            ((SimpleDraweeView) imageView).setController(null);
        }
    }

    public static void clearDiskCache() {
        Fresco.clearDiskCache();
    }

    public static void clearMemoryCache() {
        Fresco.clearMemoryCache();
    }

    public static void clearMemoryCache(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Fresco.removeMemoryCache(Uri.parse(list.get(i2)));
        }
    }

    public static ImageRequest createRequestByOption(String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequest.RequestLevel requestLevel, ImageView imageView) {
        boolean isCacheOnDisk = jDDisplayImageOptions.isCacheOnDisk();
        boolean isCacheInMemory = jDDisplayImageOptions.isCacheInMemory();
        FLog.d(TAG, "cacheOnDisk:" + isCacheOnDisk + ",cacheInMemory:" + isCacheInMemory + ",uri:" + str);
        if (TextUtils.isEmpty(str)) {
            str = JDImageUtils.FAKE_URI_EMPTY;
        }
        ImageRequestBuilder referer = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setLowestPermittedRequestLevel(requestLevel).setAutoRotateEnabled(jDDisplayImageOptions.isConsiderExifParams()).setPostprocessor(jDDisplayImageOptions.getPostProcessor()).setIsUseThumbnail(jDDisplayImageOptions.isUseThumbnail()).setReferer(jDDisplayImageOptions.getReferer());
        if (!jDDisplayImageOptions.isCacheOnDisk()) {
            referer.disableDiskCache();
        }
        if (!jDDisplayImageOptions.isCacheInMemory()) {
            referer.disableMemoryCache();
        }
        if (jDDisplayImageOptions.isScale()) {
            jDDisplayImageOptions.setResizeOptions(com.jd.mobile.image.utils.a.b(imageView));
        }
        referer.setResizeOptions(jDDisplayImageOptions.getResizeOptions());
        ImageDecodeOptionsBuilder bitmapConfig = ImageDecodeOptions.newBuilder().setBitmapConfig(Bitmap.Config.RGB_565);
        if (jDDisplayImageOptions.getBitmapConfig() != null) {
            bitmapConfig.setBitmapConfig(jDDisplayImageOptions.getBitmapConfig());
        }
        if (jDDisplayImageOptions.isUsingJavaGifDecoder()) {
            bitmapConfig.setCustomImageDecoder(new GifDecoder());
        }
        if (jDDisplayImageOptions.isForceStaticImage()) {
            bitmapConfig.setForceStaticImage(true);
        }
        referer.setImageDecodeOptions(bitmapConfig.build());
        referer.setAutoPlayAnimations(jDDisplayImageOptions.isAutoPlayAnimations());
        if (jDDisplayImageOptions.getNetworkImageRequestListener() != null) {
            referer.setRequestListener(jDDisplayImageOptions.getNetworkImageRequestListener());
        }
        return referer.build();
    }

    public static void destory() {
        Fresco.destory();
    }

    public static void disPlayImage(String str, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, JDImageLoadingListener jDImageLoadingListener) {
        if (simpleDraweeView == null) {
            return;
        }
        a aVar = new a(jDImageLoadingListener, str, simpleDraweeView);
        if (JdImageToolKit.getEngine().getImageControllerImpl().needNoImage()) {
            displayImageOnlyCache(str, simpleDraweeView, jDDisplayImageOptions, new b(jDImageLoadingListener, simpleDraweeView, str, jDDisplayImageOptions, aVar));
            return;
        }
        DraweeController draweeController = getDraweeController(createRequestByOption(str, jDDisplayImageOptions, ImageRequest.RequestLevel.FULL_FETCH, simpleDraweeView), simpleDraweeView, aVar);
        simpleDraweeView.setHierarchy(OptionToGenericDraweeHierarchy(str, simpleDraweeView, jDDisplayImageOptions, z));
        simpleDraweeView.setController(draweeController);
    }

    public static void displayImageOnlyCache(String str, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        DraweeController draweeController = getDraweeController(createRequestByOption(str, jDDisplayImageOptions, isLocalPicture(str) ? ImageRequest.RequestLevel.FULL_FETCH : ImageRequest.RequestLevel.DISK_CACHE, simpleDraweeView), simpleDraweeView, new c(jDImageLoadingListener, str, simpleDraweeView));
        simpleDraweeView.setHierarchy(OptionToGenericDraweeHierarchy(str, simpleDraweeView, jDDisplayImageOptions, true));
        simpleDraweeView.setController(draweeController);
    }

    public static File getDiskCacheDir() {
        return Fresco.getDiskCacheDir();
    }

    public static DraweeController getDraweeController(ImageRequest imageRequest, SimpleDraweeView simpleDraweeView, ControllerListener controllerListener) {
        return Fresco.newDraweeControllerBuilder().setImageRequest(imageRequest).setOldController(simpleDraweeView.getController()).setControllerListener(controllerListener).setAutoPlayAnimations(imageRequest.isAutoPlayAnimations()).build();
    }

    public static File getImageDirCacheFile(String str) {
        FileBinaryResource fileBinaryResource = (FileBinaryResource) ImagePipelineFactory.getInstance().getMainFileCache().getResource(new SimpleCacheKey(str));
        if (fileBinaryResource != null) {
            return fileBinaryResource.getFile();
        }
        return null;
    }

    public static boolean isLocalPicture(String str) {
        if (str == null || TextUtils.isEmpty(str) || str.length() < 7) {
            return false;
        }
        return str.substring(0, 7).equals("file://");
    }

    public static void loadImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        FLog.d(TAG, "loadImage:" + str);
        Fresco.getImagePipeline().fetchDecodedImage(createRequestByOption(str, jDDisplayImageOptions, ImageRequest.RequestLevel.FULL_FETCH, null), JdImageToolKit.getEngine().getApplicationContext()).subscribe(new d(jDImageLoadingListener, str, imageView), UiThreadImmediateExecutorService.getInstance());
    }

    public static void loadImageToDiskCache(String str, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        FLog.d(TAG, "loadImage:" + str);
        Fresco.getImagePipeline().prefetchToDiskCache(createRequestByOption(str, jDDisplayImageOptions, ImageRequest.RequestLevel.FULL_FETCH, null), new Object()).subscribe(new e(jDImageLoadingListener, str), UiThreadImmediateExecutorService.getInstance());
    }

    public static boolean needNoImage() {
        return JdImageToolKit.getEngine().getImageControllerImpl().needNoImage();
    }

    public static void resetWidthOrHeight(@Nullable ImageInfo imageInfo, SimpleDraweeView simpleDraweeView) {
        if (imageInfo == null) {
            return;
        }
        int height = imageInfo.getHeight();
        int width = imageInfo.getWidth();
        if (height <= 0 || width <= 0) {
            return;
        }
        int i2 = simpleDraweeView.getLayoutParams().width;
        int i3 = simpleDraweeView.getLayoutParams().height;
        if (i2 != -2 || i3 != -2) {
            if (i3 > 0) {
                simpleDraweeView.getLayoutParams().width = (i3 * width) / height;
            } else if (i2 > 0) {
                simpleDraweeView.getLayoutParams().height = (i2 * height) / width;
            }
            simpleDraweeView.requestLayout();
        }
        simpleDraweeView.getLayoutParams().width = width;
        simpleDraweeView.getLayoutParams().height = height;
        simpleDraweeView.requestLayout();
    }
}
