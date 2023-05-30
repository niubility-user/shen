package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegate;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.yoga.YogaConstants;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactImageView extends GenericDraweeView {
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    public static final String REMOTE_TRANSPARENT_BITMAP_URI = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABAQMAAAAl21bKAAAAA1BMVEUAAACnej3aAAAAAXRSTlMAQObYZgAAAApJREFUCNdjYAAAAAIAAeIhvDMAAAAASUVORK5CYII=";
    public static final String REMOTE_TRANSPARENT_BITMAP_URI_COMPATIBILITY = "file:///android_asset/spdb_def_null_img.png";
    private AnimatedDrawable2 mAnimatedDrawable2;
    private int mBackgroundColor;
    @Nullable
    private RoundedColorDrawable mBackgroundImageDrawable;
    private Bitmap.Config mBitmapConfig;
    private int mBorderColor;
    @Nullable
    private float[] mBorderCornerRadii;
    private float mBorderRadius;
    private float mBorderWidth;
    @Nullable
    private ImageSource mCachedImageSource;
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private ControllerListener mControllerForAnimation;
    @Nullable
    private ControllerListener mControllerForTesting;
    @Nullable
    private ControllerListener mControllerListener;
    @Nullable
    private Drawable mDefaultImageDrawable;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private int mFadeDurationMs;
    @Nullable
    private GlobalImageLoadListener mGlobalImageLoadListener;
    private ReadableMap mHeaders;
    @Nullable
    private ImageSource mImageSource;
    private boolean mIsDirty;
    @Nullable
    private IterativeBoxBlurPostProcessor mIterativeBoxBlurPostProcessor;
    @Nullable
    private Drawable mLoadingImageDrawable;
    private int mLoopCount;
    private int mOverlayColor;
    private boolean mProgressiveRenderingEnabled;
    private ImageResizeMethod mResizeMethod;
    private final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    private ScalingUtils.ScaleType mScaleType;
    private final List<ImageSource> mSources;
    private Shader.TileMode mTileMode;
    private final TilePostprocessor mTilePostprocessor;
    private static float[] sComputedCornerRadii = new float[4];
    private static final Matrix sMatrix = new Matrix();
    private static final Matrix sInverse = new Matrix();
    private static final Matrix sTileMatrix = new Matrix();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class AnimationController extends BaseControllerListener<ImageInfo> {
        private EventDispatcher mEventDispatcher;

        public AnimationController(EventDispatcher eventDispatcher) {
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFailure(String str, Throwable th) {
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onSubmit(String str, Object obj) {
        }

        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            ReactImageView.this.handleAnimatedDrawable2(animatable, this.mEventDispatcher, imageInfo);
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable, Drawable drawable) {
            ReactImageView.this.handleAnimatedDrawable2(animatable, this.mEventDispatcher, imageInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class LoopCountModifyingBackend extends AnimationBackendDelegate {
        private int mLoopCount;

        public LoopCountModifyingBackend(@Nullable AnimationBackend animationBackend, int i2) {
            super(animationBackend);
            this.mLoopCount = i2;
        }

        @Override // com.facebook.fresco.animation.backend.AnimationBackendDelegate, com.facebook.fresco.animation.backend.AnimationInformation
        public int getLoopCount() {
            return this.mLoopCount;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class RoundedCornerPostprocessor extends BasePostprocessor {
        private RoundedCornerPostprocessor() {
        }

        void getRadii(Bitmap bitmap, float[] fArr, float[] fArr2) {
            ReactImageView.this.mScaleType.getTransform(ReactImageView.sMatrix, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            ReactImageView.sMatrix.invert(ReactImageView.sInverse);
            fArr2[0] = ReactImageView.sInverse.mapRadius(fArr[0]);
            fArr2[1] = fArr2[0];
            fArr2[2] = ReactImageView.sInverse.mapRadius(fArr[1]);
            fArr2[3] = fArr2[2];
            fArr2[4] = ReactImageView.sInverse.mapRadius(fArr[2]);
            fArr2[5] = fArr2[4];
            fArr2[6] = ReactImageView.sInverse.mapRadius(fArr[3]);
            fArr2[7] = fArr2[6];
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor
        public void process(Bitmap bitmap, Bitmap bitmap2) {
            ReactImageView.this.cornerRadii(ReactImageView.sComputedCornerRadii);
            bitmap.setHasAlpha(true);
            if (FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[0], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[1], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[2], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[3], 0.0f)) {
                super.process(bitmap, bitmap2);
                return;
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new BitmapShader(bitmap2, tileMode, tileMode));
            Canvas canvas = new Canvas(bitmap);
            float[] fArr = new float[8];
            getRadii(bitmap2, ReactImageView.sComputedCornerRadii, fArr);
            Path path = new Path();
            path.addRoundRect(new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight()), fArr, Path.Direction.CW);
            canvas.drawPath(path, paint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class TilePostprocessor extends BasePostprocessor {
        private TilePostprocessor() {
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
        public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ReactImageView.this.mScaleType.getTransform(ReactImageView.sTileMatrix, rect, bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            BitmapShader bitmapShader = new BitmapShader(bitmap, ReactImageView.this.mTileMode, ReactImageView.this.mTileMode);
            bitmapShader.setLocalMatrix(ReactImageView.sTileMatrix);
            paint.setShader(bitmapShader);
            CloseableReference<Bitmap> createBitmap = platformBitmapFactory.createBitmap(ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            try {
                new Canvas(createBitmap.get()).drawRect(rect, paint);
                return createBitmap.mo9clone();
            } finally {
                CloseableReference.closeSafely(createBitmap);
            }
        }
    }

    public ReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        super(context, buildHierarchy(context));
        this.mResizeMethod = ImageResizeMethod.AUTO;
        this.mBackgroundColor = 0;
        this.mBorderRadius = Float.NaN;
        this.mTileMode = ImageResizeMode.defaultTileMode();
        this.mFadeDurationMs = -1;
        this.mLoopCount = 0;
        this.mBitmapConfig = Bitmap.Config.ARGB_8888;
        this.mScaleType = ImageResizeMode.defaultValue();
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mRoundedCornerPostprocessor = new RoundedCornerPostprocessor();
        this.mTilePostprocessor = new TilePostprocessor();
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContext = obj;
        this.mSources = new LinkedList();
    }

    private static GenericDraweeHierarchy buildHierarchy(Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cornerRadii(float[] fArr) {
        float f2 = !YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
        float[] fArr2 = this.mBorderCornerRadii;
        fArr[0] = (fArr2 == null || YogaConstants.isUndefined(fArr2[0])) ? f2 : this.mBorderCornerRadii[0];
        float[] fArr3 = this.mBorderCornerRadii;
        fArr[1] = (fArr3 == null || YogaConstants.isUndefined(fArr3[1])) ? f2 : this.mBorderCornerRadii[1];
        float[] fArr4 = this.mBorderCornerRadii;
        fArr[2] = (fArr4 == null || YogaConstants.isUndefined(fArr4[2])) ? f2 : this.mBorderCornerRadii[2];
        float[] fArr5 = this.mBorderCornerRadii;
        if (fArr5 != null && !YogaConstants.isUndefined(fArr5[3])) {
            f2 = this.mBorderCornerRadii[3];
        }
        fArr[3] = f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAnimatedDrawable2(Animatable animatable, final EventDispatcher eventDispatcher, final ImageInfo imageInfo) {
        if (animatable == null || !(animatable instanceof AnimatedDrawable2)) {
            return;
        }
        this.mAnimatedDrawable2 = (AnimatedDrawable2) animatable;
        eventDispatcher.dispatchEvent(new ImageLoadEvent(getId(), 9, this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight(), this.mAnimatedDrawable2.getLoopDurationMs()));
        if (this.mLoopCount != -1) {
            AnimatedDrawable2 animatedDrawable2 = this.mAnimatedDrawable2;
            animatedDrawable2.setAnimationBackend(new LoopCountModifyingBackend(animatedDrawable2.getAnimationBackend(), this.mLoopCount));
        }
        this.mAnimatedDrawable2.setAnimationListener(new AnimationListener() { // from class: com.facebook.react.views.image.ReactImageView.2
            @Override // com.facebook.fresco.animation.drawable.AnimationListener
            public void onAnimationFrame(AnimatedDrawable2 animatedDrawable22, int i2) {
            }

            @Override // com.facebook.fresco.animation.drawable.AnimationListener
            public void onAnimationRepeat(AnimatedDrawable2 animatedDrawable22) {
                eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 8, ReactImageView.this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
            }

            @Override // com.facebook.fresco.animation.drawable.AnimationListener
            public void onAnimationReset(AnimatedDrawable2 animatedDrawable22) {
            }

            @Override // com.facebook.fresco.animation.drawable.AnimationListener
            public void onAnimationStart(AnimatedDrawable2 animatedDrawable22) {
                eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 6, ReactImageView.this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
            }

            @Override // com.facebook.fresco.animation.drawable.AnimationListener
            public void onAnimationStop(AnimatedDrawable2 animatedDrawable22) {
                eventDispatcher.dispatchEvent(new ImageLoadEvent(ReactImageView.this.getId(), 7, ReactImageView.this.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
            }
        });
    }

    private boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }

    private boolean isAnimationSource() {
        try {
            ImageSource imageSource = this.mImageSource;
            if (imageSource == null || imageSource.getUri() == null || this.mImageSource.getUri().getLastPathSegment() == null) {
                return false;
            }
            if (!this.mImageSource.getUri().getLastPathSegment().endsWith(".webp")) {
                if (!this.mImageSource.getUri().getLastPathSegment().endsWith(".gif")) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isFileExists(String str) {
        try {
            String[] list = getContext().getAssets().list("");
            if (list != null) {
                for (String str2 : list) {
                    if (str2.equals(str.trim())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean isTiled() {
        return this.mTileMode != Shader.TileMode.CLAMP;
    }

    private String replaceCommonPath(String str) {
        JSBundleLoader jSBundleLoader;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str) && !str.startsWith("https://") && !str.startsWith("http://")) {
            if (new File(str.startsWith("file://") ? str.substring(7) : str).exists() || (jSBundleLoader = ((ThemedReactContext) getContext()).getCatalystInstance().getJSBundleLoader()) == null) {
                return str;
            }
            String str2 = jSBundleLoader.jsBundle;
            String str3 = jSBundleLoader.jsCommonBundle;
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                if (str2.startsWith("assets://") && str3.startsWith("assets://")) {
                    return str;
                }
                if (str2.startsWith("/data/") && str3.startsWith("/data/")) {
                    String substring = str2.substring(str2.indexOf("app_reactnative"), str2.lastIndexOf("/"));
                    String substring2 = str3.substring(str3.indexOf("app_reactnative"), str3.lastIndexOf("/"));
                    if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(substring2)) {
                        String replace = str.replace(substring, substring2);
                        return TextUtils.isEmpty(replace) ? str : replace;
                    }
                    return str;
                }
                if (str2.startsWith("/data/") && str3.startsWith("assets://")) {
                    String substring3 = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
                    return TextUtils.isEmpty(substring3) ? str : substring3;
                }
                return str;
            }
        }
        return str;
    }

    private void setSourceImage() {
        ImageSource imageSource;
        this.mImageSource = null;
        if (this.mSources.isEmpty()) {
            if (Build.VERSION.SDK_INT == 21 && BaseInfo.getDeviceBrand() != null && BaseInfo.getDeviceBrand().toLowerCase().equals("oppo")) {
                URI create = URI.create(REMOTE_TRANSPARENT_BITMAP_URI_COMPATIBILITY);
                File file = new File(create);
                if (create != null && isFileExists(file.getName())) {
                    imageSource = new ImageSource(getContext(), create.toString());
                } else {
                    imageSource = new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI);
                }
            } else {
                imageSource = new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI);
            }
            this.mSources.add(imageSource);
        } else if (hasMultipleSources()) {
            MultiSourceHelper.MultiSourceResult bestSourceForSize = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.mSources);
            this.mImageSource = bestSourceForSize.getBestResult();
            this.mCachedImageSource = bestSourceForSize.getBestResultInCache();
            return;
        }
        this.mImageSource = this.mSources.get(0);
    }

    private boolean shouldResize(ImageSource imageSource) {
        ImageResizeMethod imageResizeMethod = this.mResizeMethod;
        return imageResizeMethod == ImageResizeMethod.AUTO ? UriUtil.isLocalContentUri(imageSource.getUri()) || UriUtil.isLocalFileUri(imageSource.getUri()) : imageResizeMethod == ImageResizeMethod.RESIZE;
    }

    private void warnImageSource(String str) {
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void maybeUpdateView() {
        if (this.mIsDirty) {
            if (!hasMultipleSources() || (getWidth() > 0 && getHeight() > 0)) {
                setSourceImage();
                ImageSource imageSource = this.mImageSource;
                if (imageSource == null) {
                    return;
                }
                boolean shouldResize = shouldResize(imageSource);
                if (!shouldResize || (getWidth() > 0 && getHeight() > 0)) {
                    if (!isTiled() || (getWidth() > 0 && getHeight() > 0)) {
                        GenericDraweeHierarchy hierarchy = getHierarchy();
                        hierarchy.setActualImageScaleType(this.mScaleType);
                        Drawable drawable = this.mDefaultImageDrawable;
                        if (drawable != null) {
                            hierarchy.setPlaceholderImage(drawable, this.mScaleType);
                        }
                        Drawable drawable2 = this.mLoadingImageDrawable;
                        if (drawable2 != null) {
                            hierarchy.setPlaceholderImage(drawable2, ScalingUtils.ScaleType.CENTER);
                        }
                        ScalingUtils.ScaleType scaleType = this.mScaleType;
                        boolean z = (scaleType == ScalingUtils.ScaleType.CENTER_CROP || scaleType == ScalingUtils.ScaleType.FOCUS_CROP) ? false : true;
                        RoundingParams roundingParams = hierarchy.getRoundingParams();
                        cornerRadii(sComputedCornerRadii);
                        float[] fArr = sComputedCornerRadii;
                        roundingParams.setCornersRadii(fArr[0], fArr[1], fArr[2], fArr[3]);
                        RoundedColorDrawable roundedColorDrawable = this.mBackgroundImageDrawable;
                        if (roundedColorDrawable != null) {
                            roundedColorDrawable.setBorder(this.mBorderColor, this.mBorderWidth);
                            this.mBackgroundImageDrawable.setRadii(roundingParams.getCornersRadii());
                            hierarchy.setBackgroundImage(this.mBackgroundImageDrawable);
                        }
                        if (z) {
                            roundingParams.setCornersRadius(0.0f);
                        }
                        roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
                        int i2 = this.mOverlayColor;
                        if (i2 != 0) {
                            roundingParams.setOverlayColor(i2);
                        } else {
                            roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
                        }
                        hierarchy.setRoundingParams(roundingParams);
                        int i3 = this.mFadeDurationMs;
                        if (i3 < 0) {
                            i3 = this.mImageSource.isResource() ? 0 : 300;
                        }
                        hierarchy.setFadeDuration(i3);
                        LinkedList linkedList = new LinkedList();
                        if (z) {
                            linkedList.add(this.mRoundedCornerPostprocessor);
                        }
                        IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor = this.mIterativeBoxBlurPostProcessor;
                        if (iterativeBoxBlurPostProcessor != null) {
                            linkedList.add(iterativeBoxBlurPostProcessor);
                        }
                        if (isTiled()) {
                            linkedList.add(this.mTilePostprocessor);
                        }
                        Postprocessor from = MultiPostprocessor.from(linkedList);
                        ResizeOptions resizeOptions = shouldResize ? new ResizeOptions(getWidth(), getHeight()) : null;
                        Bitmap.Config config = this.mBitmapConfig;
                        if (config == null) {
                            config = Bitmap.Config.ARGB_8888;
                        }
                        ImageDecodeOptionsBuilder newBuilder = ImageDecodeOptions.newBuilder();
                        newBuilder.setBitmapConfig(config);
                        ReactNetworkImageRequest fromBuilderWithHeaders = ReactNetworkImageRequest.fromBuilderWithHeaders(ImageRequestBuilder.newBuilderWithSource(this.mImageSource.getUri()).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setImageDecodeOptions(newBuilder.build()).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled), this.mHeaders);
                        GlobalImageLoadListener globalImageLoadListener = this.mGlobalImageLoadListener;
                        if (globalImageLoadListener != null) {
                            globalImageLoadListener.onLoadAttempt(this.mImageSource.getUri());
                        }
                        this.mDraweeControllerBuilder.reset();
                        this.mDraweeControllerBuilder.setAutoPlayAnimations(!this.mImageSource.isDisableAutoPlay()).setCallerContext(this.mCallerContext).setOldController(getController()).setImageRequest(fromBuilderWithHeaders);
                        if (this.mCachedImageSource != null) {
                            ImageDecodeOptionsBuilder newBuilder2 = ImageDecodeOptions.newBuilder();
                            newBuilder2.setBitmapConfig(config);
                            this.mDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mCachedImageSource.getUri()).setPostprocessor(from).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).setImageDecodeOptions(newBuilder2.build()).build());
                        }
                        if (isAnimationSource()) {
                            this.mControllerForAnimation = new AnimationController(((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher());
                        }
                        ForwardingControllerListener forwardingControllerListener = new ForwardingControllerListener();
                        ControllerListener controllerListener = this.mControllerListener;
                        if (controllerListener != null) {
                            forwardingControllerListener.addListener(controllerListener);
                        }
                        ControllerListener controllerListener2 = this.mControllerForTesting;
                        if (controllerListener2 != null) {
                            forwardingControllerListener.addListener(controllerListener2);
                        }
                        ControllerListener controllerListener3 = this.mControllerForAnimation;
                        if (controllerListener3 != null) {
                            forwardingControllerListener.addListener(controllerListener3);
                        }
                        this.mDraweeControllerBuilder.setControllerListener(forwardingControllerListener);
                        setController(this.mDraweeControllerBuilder.build());
                        this.mIsDirty = false;
                        this.mDraweeControllerBuilder.reset();
                    }
                }
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.mIsDirty = this.mIsDirty || hasMultipleSources() || isTiled();
        maybeUpdateView();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        if (this.mBackgroundColor != i2) {
            this.mBackgroundColor = i2;
            this.mBackgroundImageDrawable = new RoundedColorDrawable(i2);
            this.mIsDirty = true;
        }
    }

    public void setBitmapConfig(@Nullable String str) {
        if (str == null) {
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -189895305:
                if (str.equals("ALPHA_8")) {
                    c2 = 0;
                    break;
                }
                break;
            case 223214739:
                if (str.equals("ARGB_4444")) {
                    c2 = 1;
                    break;
                }
                break;
            case 223337875:
                if (str.equals("ARGB_8888")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1857362722:
                if (str.equals("RGB_565")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.mBitmapConfig = Bitmap.Config.ALPHA_8;
                break;
            case 1:
                this.mBitmapConfig = Bitmap.Config.ARGB_4444;
                break;
            case 2:
                this.mBitmapConfig = Bitmap.Config.ARGB_8888;
                break;
            case 3:
                this.mBitmapConfig = Bitmap.Config.RGB_565;
                break;
            default:
                return;
        }
        this.mIsDirty = true;
    }

    public void setBlurRadius(float f2) {
        int pixelFromDIP = (int) PixelUtil.toPixelFromDIP(f2);
        if (pixelFromDIP == 0) {
            this.mIterativeBoxBlurPostProcessor = null;
        } else {
            this.mIterativeBoxBlurPostProcessor = new IterativeBoxBlurPostProcessor(pixelFromDIP);
        }
        this.mIsDirty = true;
    }

    public void setBorderColor(int i2) {
        this.mBorderColor = i2;
        this.mIsDirty = true;
    }

    public void setBorderRadius(float f2) {
        if (FloatUtil.floatsEqual(this.mBorderRadius, f2)) {
            return;
        }
        this.mBorderRadius = f2;
        this.mIsDirty = true;
    }

    public void setBorderWidth(float f2) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(f2);
        this.mIsDirty = true;
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.mControllerForTesting = controllerListener;
        this.mIsDirty = true;
        maybeUpdateView();
    }

    public void setDefaultSource(@Nullable String str) {
        int lastIndexOf;
        int lastIndexOf2;
        if (str != null && str.startsWith("file:") && (lastIndexOf = str.lastIndexOf("/") + 1) < (lastIndexOf2 = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL))) {
            str = str.substring(lastIndexOf, lastIndexOf2);
        }
        this.mDefaultImageDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), str);
        this.mIsDirty = true;
    }

    public void setFadeDuration(int i2) {
        this.mFadeDurationMs = i2;
    }

    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    public void setLoadingIndicatorSource(@Nullable String str) {
        this.mLoadingImageDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), str);
        this.mIsDirty = true;
    }

    public void setLoopCount(int i2) {
        this.mLoopCount = i2;
    }

    public void setOverlayColor(int i2) {
        this.mOverlayColor = i2;
        this.mIsDirty = true;
    }

    public void setPlaceholder(@Nullable ReadableMap readableMap) {
        Drawable resourceDrawable;
        if (readableMap == null) {
            return;
        }
        if (readableMap.hasKey("type")) {
            readableMap.getString("type");
        }
        String string = readableMap.hasKey("resourceName") ? readableMap.getString("resourceName") : null;
        if (string != null) {
            if (string.startsWith("file:")) {
                int lastIndexOf = string.lastIndexOf("/") + 1;
                int lastIndexOf2 = string.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
                if (lastIndexOf < lastIndexOf2) {
                    string = string.substring(lastIndexOf, lastIndexOf2);
                }
            } else if (string.startsWith("http://localhost")) {
                Matcher matcher = Pattern.compile("http.+?assets/(.+)\\.[a-zA-Z]+\\?platform=").matcher(string);
                try {
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        if (!TextUtils.isEmpty(group)) {
                            string = group.toLowerCase().replaceAll(DYConstants.DY_REGEX_AT, "").replaceAll("-", "").replaceAll("/", CartConstant.KEY_YB_INFO_LINK);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (getContext() != null && (resourceDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), string)) != null) {
            this.mLoadingImageDrawable = resourceDrawable;
        }
        this.mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
    }

    public void setResizeMethod(ImageResizeMethod imageResizeMethod) {
        this.mResizeMethod = imageResizeMethod;
        this.mIsDirty = true;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mIsDirty = true;
    }

    public void setShouldNotifyLoadEvents(boolean z) {
        if (!z) {
            this.mControllerListener = null;
        } else {
            ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.mControllerListener = new BaseControllerListener<ImageInfo>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: IPUT 
                  (wrap: com.facebook.drawee.controller.BaseControllerListener<com.facebook.imagepipeline.image.ImageInfo> : 0x001a: CONSTRUCTOR 
                  (r1v0 'this' com.facebook.react.views.image.ReactImageView A[IMMUTABLE_TYPE, THIS])
                  (r2 I:com.facebook.react.uimanager.events.EventDispatcher A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.facebook.react.views.image.ReactImageView, com.facebook.react.uimanager.events.EventDispatcher):void (m), WRAPPED] (LINE:3) call: com.facebook.react.views.image.ReactImageView.1.<init>(com.facebook.react.views.image.ReactImageView, com.facebook.react.uimanager.events.EventDispatcher):void type: CONSTRUCTOR)
                  (r1v0 'this' com.facebook.react.views.image.ReactImageView A[IMMUTABLE_TYPE, THIS])
                 (LINE:3) com.facebook.react.views.image.ReactImageView.mControllerListener com.facebook.drawee.controller.ControllerListener in method: com.facebook.react.views.image.ReactImageView.setShouldNotifyLoadEvents(boolean):void, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:486)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 21 more
                */
            /*
                this = this;
                if (r2 != 0) goto L6
                r2 = 0
                r1.mControllerListener = r2
                goto L1f
            L6:
                android.content.Context r2 = r1.getContext()
                com.facebook.react.bridge.ReactContext r2 = (com.facebook.react.bridge.ReactContext) r2
                java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r0 = com.facebook.react.uimanager.UIManagerModule.class
                com.facebook.react.bridge.NativeModule r2 = r2.getNativeModule(r0)
                com.facebook.react.uimanager.UIManagerModule r2 = (com.facebook.react.uimanager.UIManagerModule) r2
                com.facebook.react.uimanager.events.EventDispatcher r2 = r2.getEventDispatcher()
                com.facebook.react.views.image.ReactImageView$1 r0 = new com.facebook.react.views.image.ReactImageView$1
                r0.<init>()
                r1.mControllerListener = r0
            L1f:
                r2 = 1
                r1.mIsDirty = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageView.setShouldNotifyLoadEvents(boolean):void");
        }

        public void setSource(@Nullable ReadableArray readableArray) {
            ImageSource imageSource;
            this.mSources.clear();
            if (readableArray != null && readableArray.size() != 0) {
                if (readableArray.size() == 1) {
                    ReadableMap map = readableArray.getMap(0);
                    String replaceCommonPath = replaceCommonPath(map.getString("uri"));
                    Boolean bool = Boolean.FALSE;
                    if (map.hasKey("disableAutoPlay")) {
                        bool = Boolean.valueOf(map.getBoolean("disableAutoPlay"));
                    }
                    ImageSource imageSource2 = new ImageSource(getContext(), replaceCommonPath);
                    imageSource2.setDisableAutoPlay(bool.booleanValue());
                    this.mSources.add(imageSource2);
                    if (Uri.EMPTY.equals(imageSource2.getUri())) {
                        warnImageSource(replaceCommonPath);
                    }
                } else {
                    for (int i2 = 0; i2 < readableArray.size(); i2++) {
                        ReadableMap map2 = readableArray.getMap(i2);
                        String replaceCommonPath2 = replaceCommonPath(map2.getString("uri"));
                        ImageSource imageSource3 = new ImageSource(getContext(), replaceCommonPath2, map2.getDouble("width"), map2.getDouble("height"));
                        this.mSources.add(imageSource3);
                        if (Uri.EMPTY.equals(imageSource3.getUri())) {
                            warnImageSource(replaceCommonPath2);
                        }
                    }
                }
            } else {
                if (Build.VERSION.SDK_INT == 21 && BaseInfo.getDeviceBrand() != null && BaseInfo.getDeviceBrand().toLowerCase().equals("oppo")) {
                    URI create = URI.create(REMOTE_TRANSPARENT_BITMAP_URI_COMPATIBILITY);
                    File file = new File(create);
                    if (create != null && isFileExists(file.getName())) {
                        imageSource = new ImageSource(getContext(), create.toString());
                    } else {
                        imageSource = new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI);
                    }
                } else {
                    imageSource = new ImageSource(getContext(), REMOTE_TRANSPARENT_BITMAP_URI);
                }
                this.mSources.add(imageSource);
            }
            this.mIsDirty = true;
        }

        public void setTileMode(Shader.TileMode tileMode) {
            this.mTileMode = tileMode;
            this.mIsDirty = true;
        }

        public void start() {
            AnimatedDrawable2 animatedDrawable2 = this.mAnimatedDrawable2;
            if (animatedDrawable2 != null) {
                animatedDrawable2.start();
            }
        }

        public void stop() {
            AnimatedDrawable2 animatedDrawable2 = this.mAnimatedDrawable2;
            if (animatedDrawable2 != null) {
                animatedDrawable2.stop();
            }
        }

        public void setBorderRadius(float f2, int i2) {
            if (this.mBorderCornerRadii == null) {
                float[] fArr = new float[4];
                this.mBorderCornerRadii = fArr;
                Arrays.fill(fArr, Float.NaN);
            }
            if (FloatUtil.floatsEqual(this.mBorderCornerRadii[i2], f2)) {
                return;
            }
            this.mBorderCornerRadii[i2] = f2;
            this.mIsDirty = true;
        }
    }
