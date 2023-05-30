package com.jd.mobile.image.a;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.facebook.animated.giflite.GifDecoder;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.decoder.DecodeException;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jd.mobile.image.ImageDownloadListener;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.listener.AnimatedImageInfo;
import com.jd.mobile.image.listener.BaseImageRequestListener;
import com.jd.mobile.image.utils.AvifDecoderUtil;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.assist.JDFailType;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes17.dex */
public class a {

    /* renamed from: com.jd.mobile.image.a.a$a */
    /* loaded from: classes17.dex */
    public class C0206a extends BaseImageRequestListener {
        final /* synthetic */ ImageRequestListener a;
        final /* synthetic */ Uri b;

        /* renamed from: c */
        final /* synthetic */ View f6829c;
        final /* synthetic */ JDDisplayImageOptions d;

        C0206a(ImageRequestListener imageRequestListener, Uri uri, View view, JDDisplayImageOptions jDDisplayImageOptions) {
            this.a = imageRequestListener;
            this.b = uri;
            this.f6829c = view;
            this.d = jDDisplayImageOptions;
        }

        @Override // com.jd.mobile.image.listener.BaseImageRequestListener
        public void onAnimationStop() {
            super.onAnimationStop();
            ((BaseImageRequestListener) this.a).onAnimationStop();
        }

        @Override // com.jd.mobile.image.listener.BaseImageRequestListener, com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
            super.onCancel();
            FLog.i("FrescoEngine", "onCancel = " + this.b);
            this.a.onCancel();
        }

        @Override // com.jd.mobile.image.listener.BaseImageRequestListener, com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            super.onFailure(th);
            FLog.e("FrescoEngine", "onFailure = " + this.b);
            FLog.e("FrescoEngine", "onFailure = " + th);
            a.k(this.b, this.f6829c, this.d, th, this.a);
        }

        @Override // com.jd.mobile.image.listener.BaseImageRequestListener, com.jd.mobile.image.ImageRequestListener
        public void onSuccess(ImageInfo imageInfo) {
            super.onSuccess(imageInfo);
            FLog.i("FrescoEngine", "onSuccess = " + this.b);
            this.a.onSuccess(imageInfo);
        }

        @Override // com.jd.mobile.image.listener.BaseImageRequestListener
        public void onSuccess(AnimatedImageInfo animatedImageInfo) {
            super.onSuccess(animatedImageInfo);
            ((BaseImageRequestListener) this.a).onSuccess(animatedImageInfo);
        }
    }

    /* loaded from: classes17.dex */
    public class b implements ImageRequestListener<ImageInfo> {
        final /* synthetic */ Uri a;
        final /* synthetic */ ImageRequestListener b;

        /* renamed from: c */
        final /* synthetic */ View f6830c;
        final /* synthetic */ JDDisplayImageOptions d;

        b(Uri uri, ImageRequestListener imageRequestListener, View view, JDDisplayImageOptions jDDisplayImageOptions) {
            this.a = uri;
            this.b = imageRequestListener;
            this.f6830c = view;
            this.d = jDDisplayImageOptions;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a */
        public void onSuccess(ImageInfo imageInfo) {
            FLog.i("FrescoEngine", "onSuccess = " + this.a);
            ImageRequestListener imageRequestListener = this.b;
            if (imageRequestListener != null) {
                imageRequestListener.onSuccess(imageInfo);
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
            FLog.i("FrescoEngine", "onCancel = " + this.a);
            ImageRequestListener imageRequestListener = this.b;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            FLog.e("FrescoEngine", "onFailure = " + this.a);
            FLog.e("FrescoEngine", "onFailure = " + th);
            a.k(this.a, this.f6830c, this.d, th, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class c extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
        final /* synthetic */ ImageRequestListener a;

        c(ImageRequestListener imageRequestListener) {
            this.a = imageRequestListener;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(@Nonnull DataSource<CloseableReference<CloseableImage>> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(dataSource.getFailureCause());
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onNewResultImpl(@NonNull DataSource<CloseableReference<CloseableImage>> dataSource) {
            CloseableReference<CloseableImage> result;
            Bitmap underlyingBitmap;
            if (dataSource.isFinished() && (result = dataSource.getResult()) != null) {
                try {
                    CloseableImage closeableImage = result.mo9clone().get();
                    if (closeableImage instanceof CloseableAnimatedImage) {
                        AnimatedImageResult imageResult = ((CloseableAnimatedImage) closeableImage).getImageResult();
                        if (imageResult != null && imageResult.getImage() != null) {
                            int width = imageResult.getImage().getWidth();
                            int height = imageResult.getImage().getHeight();
                            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                            imageResult.getImage().getFrame(0).renderFrame(width, height, createBitmap);
                            ImageRequestListener imageRequestListener = this.a;
                            if (imageRequestListener != null) {
                                imageRequestListener.onSuccess(createBitmap);
                            }
                        }
                    } else if ((closeableImage instanceof CloseableBitmap) && (underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap()) != null && !underlyingBitmap.isRecycled()) {
                        Bitmap.Config config = underlyingBitmap.getConfig();
                        if (config == null) {
                            config = Bitmap.Config.ARGB_8888;
                        }
                        Bitmap copy = underlyingBitmap.copy(config, false);
                        ImageRequestListener imageRequestListener2 = this.a;
                        if (imageRequestListener2 != null) {
                            imageRequestListener2.onSuccess(copy);
                        }
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class d extends BaseDataSubscriber<CloseableReference<PooledByteBuffer>> {
        final /* synthetic */ ImageRequestListener a;

        d(ImageRequestListener imageRequestListener) {
            this.a = imageRequestListener;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(@Nonnull DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(@Nonnull DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(dataSource.getFailureCause());
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onNewResultImpl(@Nonnull DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
            CloseableReference<PooledByteBuffer> result;
            if (dataSource.isFinished() && (result = dataSource.getResult()) != null) {
                try {
                    EncodedImage encodedImage = new EncodedImage(result.mo9clone());
                    ImageRequestListener imageRequestListener = this.a;
                    if (imageRequestListener != null) {
                        imageRequestListener.onSuccess(encodedImage);
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    public class e implements ImageRequestListener<EncodedImage> {
        final /* synthetic */ String a;
        final /* synthetic */ ImageDownloadListener b;

        e(String str, ImageDownloadListener imageDownloadListener) {
            this.a = str;
            this.b = imageDownloadListener;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a */
        public void onSuccess(EncodedImage encodedImage) {
            if (encodedImage != null) {
                try {
                    if (encodedImage.getInputStream() != null) {
                        com.jd.mobile.image.utils.d.a(this.a, com.jd.mobile.image.utils.d.b(encodedImage.getInputStream()));
                        ImageDownloadListener imageDownloadListener = this.b;
                        if (imageDownloadListener != null) {
                            imageDownloadListener.onSuccess(this.a);
                        }
                    }
                } catch (Throwable th) {
                    ImageDownloadListener imageDownloadListener2 = this.b;
                    if (imageDownloadListener2 != null) {
                        imageDownloadListener2.onFailure(th);
                    }
                }
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
            ImageDownloadListener imageDownloadListener = this.b;
            if (imageDownloadListener != null) {
                imageDownloadListener.onCancel();
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            ImageDownloadListener imageDownloadListener = this.b;
            if (imageDownloadListener != null) {
                imageDownloadListener.onFailure(th);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f extends BaseDataSubscriber<Void> {
        final /* synthetic */ ImageRequestListener a;
        final /* synthetic */ String b;

        f(ImageRequestListener imageRequestListener, String str) {
            this.a = imageRequestListener;
            this.b = str;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(@Nonnull DataSource<Void> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onFailureImpl(@Nonnull DataSource<Void> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(dataSource.getFailureCause());
            }
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onNewResultImpl(@Nonnull DataSource<Void> dataSource) {
            ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onSuccess(this.b);
            }
        }
    }

    /* loaded from: classes17.dex */
    public class g extends BaseControllerListener<ImageInfo> {
        final /* synthetic */ View a;
        final /* synthetic */ JDDisplayImageOptions b;

        /* renamed from: c */
        final /* synthetic */ ImageRequestListener f6831c;

        /* renamed from: com.jd.mobile.image.a.a$g$a */
        /* loaded from: classes17.dex */
        public class C0207a implements AnimatedImageInfo {
            final /* synthetic */ AnimatedDrawable2 a;

            C0207a(g gVar, AnimatedDrawable2 animatedDrawable2) {
                this.a = animatedDrawable2;
            }

            @Override // com.jd.mobile.image.listener.AnimatedImageInfo
            public int getFrameCount() {
                return this.a.getFrameCount();
            }

            @Override // com.jd.mobile.image.listener.AnimatedImageInfo
            public long getLoopDurationMs() {
                return this.a.getLoopDurationMs();
            }
        }

        g(View view, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener imageRequestListener) {
            this.a = view;
            this.b = jDDisplayImageOptions;
            this.f6831c = imageRequestListener;
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onCancelled() {
            super.onCancelled();
            ImageRequestListener imageRequestListener = this.f6831c;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFailure(String str, Throwable th) {
            super.onFailure(str, th);
            ImageRequestListener imageRequestListener = this.f6831c;
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(th);
            }
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
            super.onFinalImageSet(str, (String) imageInfo, animatable, drawable);
            if (imageInfo == null) {
                return;
            }
            com.jd.mobile.image.utils.a.d(imageInfo, this.a);
            if (animatable != null) {
                try {
                    if (this.b.isTapToControlAnimationEnabled()) {
                        com.jd.mobile.image.utils.a.c(this.a, animatable);
                    }
                    AnimatedDrawable2 animatedDrawable2 = (AnimatedDrawable2) animatable;
                    ImageRequestListener imageRequestListener = this.f6831c;
                    if (imageRequestListener instanceof BaseImageRequestListener) {
                        BaseImageRequestListener baseImageRequestListener = (BaseImageRequestListener) imageRequestListener;
                        animatedDrawable2.setAnimationListener(new com.jd.mobile.image.a.d.a(this.b.getLoopCountOfAnimation(), baseImageRequestListener));
                        baseImageRequestListener.onSuccess((AnimatedImageInfo) new C0207a(this, animatedDrawable2));
                    } else {
                        animatedDrawable2.setAnimationListener(new com.jd.mobile.image.a.d.a(this.b.getLoopCountOfAnimation()));
                    }
                } catch (Exception unused) {
                }
            }
            ImageRequestListener imageRequestListener2 = this.f6831c;
            if (imageRequestListener2 != null) {
                imageRequestListener2.onSuccess(imageInfo);
            }
        }
    }

    public static GenericDraweeHierarchy a(View view, JDDisplayImageOptions jDDisplayImageOptions) {
        Drawable drawable;
        Drawable drawable2;
        boolean z = view instanceof SimpleDraweeView;
        GenericDraweeHierarchyBuilder builder = z ? ((SimpleDraweeView) view).getHierarchy().getBuilder() : new GenericDraweeHierarchyBuilder(view.getResources());
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
        Resources resources = JdImageToolKit.getContext().getResources();
        int placeholder = jDDisplayImageOptions.getPlaceholder();
        Drawable drawable3 = (placeholder <= 0 || placeholder > 23) ? placeholder != 0 ? resources.getDrawable(placeholder) : null : new JDPlaceholderDrawable(placeholder, builder.getRoundingParams());
        try {
            drawable = jDDisplayImageOptions.getImageOnLoading(resources);
            try {
                drawable = com.jd.mobile.image.utils.b.c(drawable);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            drawable = null;
        }
        try {
            drawable2 = jDDisplayImageOptions.getImageOnFail(resources);
            try {
                drawable2 = com.jd.mobile.image.utils.b.c(drawable2);
            } catch (Throwable unused3) {
            }
        } catch (Throwable unused4) {
            drawable2 = null;
        }
        if (drawable3 == null) {
            drawable3 = new JDPlaceholderDrawable(17, builder.getRoundingParams());
        }
        ScalingUtils.ScaleType a = view instanceof ImageView ? com.jd.mobile.image.utils.a.a(((ImageView) view).getScaleType()) : null;
        if (a == null) {
            a = ScalingUtils.ScaleType.FIT_CENTER;
        }
        if (jDDisplayImageOptions.getActualImageScaleType() != null) {
            builder.setActualImageScaleType(jDDisplayImageOptions.getActualImageScaleType());
            if (jDDisplayImageOptions.getActualImageFocusPoint() != null) {
                builder.setActualImageFocusPoint(jDDisplayImageOptions.getActualImageFocusPoint());
            }
        } else {
            builder.setActualImageScaleType(a);
        }
        if (drawable2 != null) {
            builder.setFailureImage(drawable2, a);
        } else if (jDDisplayImageOptions.isUsingDefaultPlaceholder()) {
            builder.setFailureImage(drawable3, a);
        }
        if (JdImageToolKit.getEngine().getImageControllerImpl().needNoImage()) {
            String string = JdImageToolKit.getContext().getString(R.string.image_need_long_click);
            if (!TextUtils.isEmpty(jDDisplayImageOptions.getNoImageText())) {
                string = jDDisplayImageOptions.getNoImageText();
            }
            builder.setFailureImage(new ExceptionDrawable(string, jDDisplayImageOptions.getNoImageTextSize(), jDDisplayImageOptions.getNoImageTextGap()), a);
        } else if (drawable != null) {
            builder.setPlaceholderImage(drawable, a);
        } else if (jDDisplayImageOptions.isUsingDefaultPlaceholder()) {
            builder.setPlaceholderImage(drawable3, a);
        }
        GenericDraweeHierarchy build = builder.build();
        if (z) {
            build.setChangeImageListener(((SimpleDraweeView) view).getChangeImageListener());
        }
        return build;
    }

    public static DraweeController b(View view, ImageRequest imageRequest, DraweeController draweeController, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<ImageInfo> imageRequestListener) {
        return Fresco.newDraweeControllerBuilder().setOldController(draweeController).setImageRequest(imageRequest).setControllerListener(new g(view, jDDisplayImageOptions, imageRequestListener)).setTapToRetryEnabled(false).setAutoPlayAnimations(jDDisplayImageOptions.isAutoPlayAnimations()).build();
    }

    private static ImageRequest c(@NonNull Uri uri, JDDisplayImageOptions jDDisplayImageOptions) {
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = JDDisplayImageOptions.createSimple();
        }
        return n(uri, jDDisplayImageOptions).build();
    }

    public static ImageRequest d(String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (TextUtils.isEmpty(str)) {
            str = JDImageUtils.FAKE_URI_EMPTY;
        }
        return c(Uri.parse(str), jDDisplayImageOptions);
    }

    public static void f(Uri uri, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<Bitmap> imageRequestListener, Executor executor) {
        if (uri == null) {
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = JDDisplayImageOptions.createSimple();
        }
        Fresco.getImagePipeline().fetchDecodedImage(j(uri, jDDisplayImageOptions), (Object) null, jDDisplayImageOptions.getImageRequestLevel()).subscribe(new c(imageRequestListener), executor);
    }

    public static void g(Uri uri, String str, ImageDownloadListener imageDownloadListener) {
        l(uri, null, new e(str, imageDownloadListener), CallerThreadExecutor.getInstance());
    }

    public static void h(View view, Uri uri, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<ImageInfo> imageRequestListener) {
        if (uri == null) {
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = JDDisplayImageOptions.createSimple();
        }
        Uri parse = Uri.parse(com.jd.mobile.image.utils.c.a(AvifDecoderUtil.getTransformImageUri(uri.toString(), jDDisplayImageOptions.isInShowRetry(), jDDisplayImageOptions.getReferer()), jDDisplayImageOptions.isInShowRetry(), jDDisplayImageOptions.getReferer()));
        if (jDDisplayImageOptions.isScale()) {
            jDDisplayImageOptions.setResizeOptions(com.jd.mobile.image.utils.a.b(view));
        }
        if (AvifDecoderUtil.isAVIFRetry() || com.jd.mobile.image.utils.c.d()) {
            imageRequestListener = imageRequestListener instanceof BaseImageRequestListener ? new C0206a(imageRequestListener, parse, view, jDDisplayImageOptions) : new b(parse, imageRequestListener, view, jDDisplayImageOptions);
        }
        ImageRequest j2 = j(parse, jDDisplayImageOptions);
        GenericDraweeHierarchy a = a(view, jDDisplayImageOptions);
        if (view instanceof SimpleDraweeView) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view;
            simpleDraweeView.setHierarchy(a);
            simpleDraweeView.setController(b(view, j2, simpleDraweeView.getController(), jDDisplayImageOptions, imageRequestListener));
            return;
        }
        int i2 = R.id.fresco_drawee;
        DraweeHolder draweeHolder = (DraweeHolder) view.getTag(i2);
        if (draweeHolder == null) {
            draweeHolder = DraweeHolder.create(a, view.getContext());
        }
        draweeHolder.setController(b(view, j2, draweeHolder.getController(), jDDisplayImageOptions, imageRequestListener));
        if (ViewCompat.isAttachedToWindow(view)) {
            draweeHolder.onAttach();
        }
        int i3 = R.id.attach_change_listener;
        View.OnAttachStateChangeListener onAttachStateChangeListener = (com.jd.mobile.image.a.d.c) view.getTag(i3);
        if (onAttachStateChangeListener == null) {
            onAttachStateChangeListener = new com.jd.mobile.image.a.d.c(draweeHolder);
            view.addOnAttachStateChangeListener(onAttachStateChangeListener);
        }
        view.setTag(i2, draweeHolder);
        view.setTag(i3, onAttachStateChangeListener);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(draweeHolder.getTopLevelDrawable());
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(draweeHolder.getTopLevelDrawable());
        } else {
            view.setBackgroundDrawable(draweeHolder.getTopLevelDrawable());
        }
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void i(String str, ImageRequestListener<String> imageRequestListener) {
        Fresco.getImagePipeline().prefetchToDiskCache(d(str, null), null).subscribe(new f(imageRequestListener, str), UiThreadImmediateExecutorService.getInstance());
    }

    private static ImageRequest j(@NonNull Uri uri, JDDisplayImageOptions jDDisplayImageOptions) {
        ImageRequestBuilder m2 = m(uri, jDDisplayImageOptions);
        if (m2 != null) {
            return m2.build();
        }
        return null;
    }

    public static void k(Uri uri, View view, JDDisplayImageOptions jDDisplayImageOptions, Throwable th, ImageRequestListener<ImageInfo> imageRequestListener) {
        int lastIndexOf;
        String uri2 = uri.toString();
        if (AvifDecoderUtil.isAVIFRetry() && !TextUtils.isEmpty(uri2) && uri2.endsWith(".avif") && (th instanceof DecodeException)) {
            JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(uri2, new JDFailReason(JDFailType.DECODING_ERROR, th), jDDisplayImageOptions.getReferer(), -1);
            lastIndexOf = uri2.lastIndexOf(".avif");
        } else if (!com.jd.mobile.image.utils.c.d() || TextUtils.isEmpty(uri2) || !uri2.endsWith(".gif.webp") || !(th instanceof Exception)) {
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(th);
                return;
            }
            return;
        } else {
            JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(uri2, new JDFailReason(JDFailType.DECODING_ERROR, th), jDDisplayImageOptions.getReferer(), -1);
            lastIndexOf = uri2.lastIndexOf(".webp");
        }
        String substring = uri2.substring(0, lastIndexOf);
        jDDisplayImageOptions.setInShowRetry(true);
        h(view, Uri.parse(substring), jDDisplayImageOptions, imageRequestListener);
    }

    public static void l(Uri uri, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<EncodedImage> imageRequestListener, Executor executor) {
        if (uri == null) {
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = JDDisplayImageOptions.createSimple();
        }
        Fresco.getImagePipeline().fetchEncodedImage(c(uri, jDDisplayImageOptions), null).subscribe(new d(imageRequestListener), executor);
    }

    private static ImageRequestBuilder m(@NonNull Uri uri, JDDisplayImageOptions jDDisplayImageOptions) {
        ImageRequestBuilder n2 = n(uri, jDDisplayImageOptions);
        n2.setRotationOptions(jDDisplayImageOptions.isConsiderExifParams() ? RotationOptions.autoRotate() : RotationOptions.disableRotation());
        n2.setIsUseThumbnail(jDDisplayImageOptions.isUseThumbnail());
        if (!jDDisplayImageOptions.isCacheOnDisk()) {
            n2.disableDiskCache();
        }
        if (!jDDisplayImageOptions.isCacheInMemory()) {
            n2.disableMemoryCache();
        }
        n2.setResizeOptions(jDDisplayImageOptions.getResizeOptions());
        n2.setPostprocessor(jDDisplayImageOptions.getPostProcessor());
        n2.setReferer(jDDisplayImageOptions.getReferer());
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
        n2.setImageDecodeOptions(bitmapConfig.build());
        if (jDDisplayImageOptions.getNetworkImageRequestListener() != null) {
            n2.setRequestListener(jDDisplayImageOptions.getNetworkImageRequestListener());
        }
        return n2;
    }

    private static ImageRequestBuilder n(@NonNull Uri uri, JDDisplayImageOptions jDDisplayImageOptions) {
        return ImageRequestBuilder.newBuilderWithSource(uri).setLowestPermittedRequestLevel(jDDisplayImageOptions.getImageRequestLevel());
    }
}
