package com.jingdong.app.util.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.jd.dynamic.DYConstants;
import com.jd.mobile.image.listener.NetworkImageRequestListener;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.util.image.assist.JDBitmapProcessor;
import com.jingdong.app.util.image.display.JDBitmapDisplayer;
import com.jingdong.app.util.image.display.JDFadeInBitmapDisplayer;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.app.util.image.listener.JDImageReportListener;

/* loaded from: classes4.dex */
public final class JDDisplayImageOptions {
    private boolean autoPlayAnimations;
    private boolean cacheInMemory;
    private boolean cacheOnDisk;
    private ColorFilter colorFilter;
    private boolean considerExifParams;
    private BitmapFactory.Options decodingOptions;
    private int fadeDurationMs;
    private Drawable failureImage;
    private int failureImageResId;
    private boolean forceStaticImage;
    private Drawable imageForEmptyUri;
    private int imageResForEmptyUri;
    private int inSampleSize;
    private boolean isInShowRetry;
    private boolean isScale;
    private boolean isUseThumbnail;
    private boolean isUsingJavaGifDecoder;
    private Drawable loadingImage;
    private int loadingImageResId;
    private int loopCountOfAnimation;
    private PointF mActualImageFocusPoint;
    private ScalingUtils.ScaleType mActualImageScaleType;
    private ImageRequest.RequestLevel mRequestLevel;
    private ResizeOptions mResizeOptions;
    private NetworkImageRequestListener networkImageRequestListener;
    private String noImageText;
    private int noImageTextGap;
    private int noImageTextSize;
    private int placeholder;
    private Postprocessor postProcessor;
    private String referer;
    private RoundingParams roundingParams;
    private boolean tapToControlAnimation;
    private boolean useDefaultPlaceholder;

    public JDDisplayImageOptions() {
        this.placeholder = 0;
        this.loadingImageResId = 0;
        this.imageResForEmptyUri = 0;
        this.failureImageResId = 0;
        this.loadingImage = null;
        this.imageForEmptyUri = null;
        this.failureImage = null;
        this.cacheInMemory = true;
        this.cacheOnDisk = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        this.decodingOptions = options;
        this.considerExifParams = false;
        this.postProcessor = null;
        this.isScale = true;
        this.isUseThumbnail = true;
        this.inSampleSize = 1;
        this.noImageText = null;
        this.noImageTextSize = -1;
        this.noImageTextGap = -1;
        this.loopCountOfAnimation = 0;
        this.autoPlayAnimations = true;
        this.fadeDurationMs = 0;
        this.colorFilter = null;
        this.useDefaultPlaceholder = true;
        this.mRequestLevel = ImageRequest.RequestLevel.FULL_FETCH;
        this.isInShowRetry = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    public JDDisplayImageOptions(JDDisplayImageOptions jDDisplayImageOptions) {
        this.placeholder = 0;
        this.loadingImageResId = 0;
        this.imageResForEmptyUri = 0;
        this.failureImageResId = 0;
        this.loadingImage = null;
        this.imageForEmptyUri = null;
        this.failureImage = null;
        this.cacheInMemory = true;
        this.cacheOnDisk = true;
        this.decodingOptions = new BitmapFactory.Options();
        this.considerExifParams = false;
        this.postProcessor = null;
        this.isScale = true;
        this.isUseThumbnail = true;
        this.inSampleSize = 1;
        this.noImageText = null;
        this.noImageTextSize = -1;
        this.noImageTextGap = -1;
        this.loopCountOfAnimation = 0;
        this.autoPlayAnimations = true;
        this.fadeDurationMs = 0;
        this.colorFilter = null;
        this.useDefaultPlaceholder = true;
        this.mRequestLevel = ImageRequest.RequestLevel.FULL_FETCH;
        this.isInShowRetry = false;
        cloneFrom(jDDisplayImageOptions);
    }

    public static JDDisplayImageOptions createSimple() {
        return new JDDisplayImageOptions();
    }

    private void parseJDBitmapDisplayer(JDBitmapDisplayer jDBitmapDisplayer) {
        if (jDBitmapDisplayer instanceof JDRoundedBitmapDisplayer) {
            this.roundingParams = RoundingParams.fromCornersRadii(((JDRoundedBitmapDisplayer) jDBitmapDisplayer).getCornersRadii());
        } else if (jDBitmapDisplayer instanceof JDFadeInBitmapDisplayer) {
            this.fadeDurationMs = ((JDFadeInBitmapDisplayer) jDBitmapDisplayer).durationMillis;
        }
    }

    @Deprecated
    public JDDisplayImageOptions bitmapConfig(Bitmap.Config config) {
        return setBitmapConfig(config);
    }

    public JDDisplayImageOptions cacheInMemory(boolean z) {
        this.cacheInMemory = z;
        return this;
    }

    public JDDisplayImageOptions cacheOnDisk(boolean z) {
        this.cacheOnDisk = z;
        return this;
    }

    public JDDisplayImageOptions cloneFrom(JDDisplayImageOptions jDDisplayImageOptions) {
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = createSimple();
        }
        this.loadingImageResId = jDDisplayImageOptions.loadingImageResId;
        this.imageResForEmptyUri = jDDisplayImageOptions.imageResForEmptyUri;
        this.failureImageResId = jDDisplayImageOptions.failureImageResId;
        this.imageForEmptyUri = jDDisplayImageOptions.imageForEmptyUri;
        this.loadingImage = jDDisplayImageOptions.loadingImage;
        this.failureImage = jDDisplayImageOptions.failureImage;
        this.cacheInMemory = jDDisplayImageOptions.cacheInMemory;
        this.cacheOnDisk = jDDisplayImageOptions.cacheOnDisk;
        this.decodingOptions = jDDisplayImageOptions.decodingOptions;
        this.considerExifParams = jDDisplayImageOptions.considerExifParams;
        this.postProcessor = jDDisplayImageOptions.postProcessor;
        this.placeholder = jDDisplayImageOptions.placeholder;
        this.isScale = jDDisplayImageOptions.isScale;
        this.isUseThumbnail = jDDisplayImageOptions.isUseThumbnail;
        this.inSampleSize = jDDisplayImageOptions.inSampleSize;
        this.networkImageRequestListener = jDDisplayImageOptions.networkImageRequestListener;
        this.noImageText = jDDisplayImageOptions.noImageText;
        this.noImageTextSize = jDDisplayImageOptions.noImageTextSize;
        this.noImageTextGap = jDDisplayImageOptions.noImageTextGap;
        this.mResizeOptions = jDDisplayImageOptions.mResizeOptions;
        this.isUsingJavaGifDecoder = jDDisplayImageOptions.isUsingJavaGifDecoder;
        this.useDefaultPlaceholder = jDDisplayImageOptions.useDefaultPlaceholder;
        this.forceStaticImage = jDDisplayImageOptions.forceStaticImage;
        this.tapToControlAnimation = jDDisplayImageOptions.tapToControlAnimation;
        this.loopCountOfAnimation = jDDisplayImageOptions.loopCountOfAnimation;
        this.fadeDurationMs = jDDisplayImageOptions.fadeDurationMs;
        this.roundingParams = jDDisplayImageOptions.roundingParams;
        this.colorFilter = jDDisplayImageOptions.colorFilter;
        this.mActualImageScaleType = jDDisplayImageOptions.mActualImageScaleType;
        this.mActualImageFocusPoint = jDDisplayImageOptions.mActualImageFocusPoint;
        this.mRequestLevel = jDDisplayImageOptions.mRequestLevel;
        this.referer = jDDisplayImageOptions.getReferer();
        this.autoPlayAnimations = jDDisplayImageOptions.isAutoPlayAnimations();
        this.isInShowRetry = jDDisplayImageOptions.isInShowRetry();
        return this;
    }

    public JDDisplayImageOptions considerExifParams(boolean z) {
        this.considerExifParams = z;
        return this;
    }

    public JDDisplayImageOptions decodingOptions(BitmapFactory.Options options) {
        if (options != null) {
            this.decodingOptions = options;
            return this;
        }
        throw new IllegalArgumentException("decodingOptions can't be null");
    }

    @Deprecated
    public JDDisplayImageOptions delayBeforeLoading(int i2) {
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions displayer(JDBitmapDisplayer jDBitmapDisplayer) {
        if (jDBitmapDisplayer != null) {
            parseJDBitmapDisplayer(jDBitmapDisplayer);
            return this;
        }
        throw new IllegalArgumentException("displayer can't be null");
    }

    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    public ScalingUtils.ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    public Bitmap.Config getBitmapConfig() {
        Bitmap.Config config = this.decodingOptions.inPreferredConfig;
        if (config != Bitmap.Config.RGB_565) {
            return config;
        }
        return null;
    }

    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public int getFadeDurationMs() {
        return this.fadeDurationMs;
    }

    public Drawable getImageForEmptyUri(Resources resources) {
        int i2 = this.imageResForEmptyUri;
        return i2 != 0 ? resources.getDrawable(i2) : this.imageForEmptyUri;
    }

    @Deprecated
    public Drawable getImageForEmptyUriDefaultFrame() {
        return this.failureImage;
    }

    public Drawable getImageOnFail(Resources resources) {
        int i2 = this.failureImageResId;
        return i2 != 0 ? resources.getDrawable(i2) : this.failureImage;
    }

    public Drawable getImageOnLoading(Resources resources) {
        int i2 = this.loadingImageResId;
        return i2 != 0 ? resources.getDrawable(i2) : this.loadingImage;
    }

    public ImageRequest.RequestLevel getImageRequestLevel() {
        return this.mRequestLevel;
    }

    public int getLoopCountOfAnimation() {
        return this.loopCountOfAnimation;
    }

    public NetworkImageRequestListener getNetworkImageRequestListener() {
        return this.networkImageRequestListener;
    }

    public String getNoImageText() {
        return this.noImageText;
    }

    public int getNoImageTextGap() {
        return this.noImageTextGap;
    }

    public int getNoImageTextSize() {
        return this.noImageTextSize;
    }

    public int getPlaceholder() {
        return this.placeholder;
    }

    public Postprocessor getPostProcessor() {
        return this.postProcessor;
    }

    public String getReferer() {
        if (TextUtils.isEmpty(this.referer)) {
            String thisPageInfo = JdImageToolKit.getEngine().getImageControllerImpl().getThisPageInfo();
            if (thisPageInfo.contains(DYConstants.DY_REGEX_AT)) {
                thisPageInfo = thisPageInfo.substring(0, thisPageInfo.indexOf(DYConstants.DY_REGEX_AT));
            }
            this.referer = "Image_" + thisPageInfo;
        }
        return this.referer;
    }

    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public RoundingParams getRoundingParams() {
        return this.roundingParams;
    }

    @Deprecated
    public JDDisplayImageOptions inSampleSize(int i2) {
        this.inSampleSize = i2;
        return this;
    }

    public boolean isAutoPlayAnimations() {
        return this.autoPlayAnimations;
    }

    public boolean isCacheInMemory() {
        return this.cacheInMemory;
    }

    public boolean isCacheOnDisk() {
        return this.cacheOnDisk;
    }

    public boolean isConsiderExifParams() {
        return this.considerExifParams;
    }

    public boolean isForceStaticImage() {
        return this.forceStaticImage;
    }

    public boolean isInShowRetry() {
        return this.isInShowRetry;
    }

    @Deprecated
    public JDDisplayImageOptions isLoadFromNetworkAnyTime(boolean z) {
        return this;
    }

    public JDDisplayImageOptions isScale(boolean z) {
        this.isScale = z;
        if (!z) {
            setResizeOptions(null);
        }
        return this;
    }

    public boolean isScale() {
        return this.isScale;
    }

    public boolean isTapToControlAnimationEnabled() {
        return this.tapToControlAnimation;
    }

    public JDDisplayImageOptions isUseThumbnail(boolean z) {
        this.isUseThumbnail = z;
        return this;
    }

    public boolean isUseThumbnail() {
        return this.isUseThumbnail;
    }

    public boolean isUsingDefaultPlaceholder() {
        return this.useDefaultPlaceholder;
    }

    public boolean isUsingJavaGifDecoder() {
        return this.isUsingJavaGifDecoder;
    }

    @Deprecated
    public JDDisplayImageOptions onlyCache(boolean z) {
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions postProcessor(JDBitmapProcessor jDBitmapProcessor) {
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions resetViewBeforeLoading(boolean z) {
        return this;
    }

    public JDDisplayImageOptions setActualImageFocusPoint(PointF pointF) {
        this.mActualImageFocusPoint = pointF;
        return this;
    }

    public JDDisplayImageOptions setActualImageScaleType(ScalingUtils.ScaleType scaleType) {
        this.mActualImageScaleType = scaleType;
        return this;
    }

    public void setAutoPlayAnimations(boolean z) {
        this.autoPlayAnimations = z;
    }

    public JDDisplayImageOptions setBitmapConfig(Bitmap.Config config) {
        if (config != null) {
            this.decodingOptions.inPreferredConfig = config;
            return this;
        }
        throw new IllegalArgumentException("bitmapConfig can't be null");
    }

    @Deprecated
    public void setCheckDefault(boolean z) {
    }

    public JDDisplayImageOptions setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions setDisplayer(JDBitmapDisplayer... jDBitmapDisplayerArr) {
        if (jDBitmapDisplayerArr != null) {
            for (JDBitmapDisplayer jDBitmapDisplayer : jDBitmapDisplayerArr) {
                parseJDBitmapDisplayer(jDBitmapDisplayer);
            }
            return this;
        }
        throw new IllegalArgumentException("displayer can't be null!");
    }

    public JDDisplayImageOptions setFadeDurationMs(int i2) {
        this.fadeDurationMs = i2;
        return this;
    }

    public JDDisplayImageOptions setForceStaticImage(boolean z) {
        this.forceStaticImage = z;
        return this;
    }

    public JDDisplayImageOptions setImageRequestLevel(ImageRequest.RequestLevel requestLevel) {
        this.mRequestLevel = requestLevel;
        return this;
    }

    public JDDisplayImageOptions setInShowRetry(boolean z) {
        this.isInShowRetry = z;
        return this;
    }

    public JDDisplayImageOptions setLoopCountOfAnimation(int i2) {
        this.loopCountOfAnimation = i2;
        return this;
    }

    public JDDisplayImageOptions setNetworkImageRequestListener(NetworkImageRequestListener networkImageRequestListener) {
        this.networkImageRequestListener = networkImageRequestListener;
        return this;
    }

    public JDDisplayImageOptions setNoImageText(String str) {
        this.noImageText = str;
        return this;
    }

    public JDDisplayImageOptions setNoImageTextGap(int i2) {
        this.noImageTextGap = i2;
        return this;
    }

    public JDDisplayImageOptions setNoImageTextSize(int i2) {
        this.noImageTextSize = i2;
        return this;
    }

    public JDDisplayImageOptions setPlaceholder(int i2) {
        this.placeholder = i2;
        return this;
    }

    public JDDisplayImageOptions setPostProcessor(Postprocessor postprocessor) {
        this.postProcessor = postprocessor;
        return this;
    }

    public JDDisplayImageOptions setReferer(String str) {
        this.referer = str;
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions setReportListener(final JDImageReportListener jDImageReportListener) {
        if (jDImageReportListener != null) {
            this.networkImageRequestListener = new NetworkImageRequestListener() { // from class: com.jingdong.app.util.image.JDDisplayImageOptions.1
                {
                    JDDisplayImageOptions.this = this;
                }

                @Override // com.jd.mobile.image.listener.NetworkImageRequestListener
                public void onSuccess(String str, long j2, long j3, int i2) {
                    jDImageReportListener.report(str, i2, -1, -1, j2, j3);
                }
            };
        }
        return this;
    }

    public JDDisplayImageOptions setResizeOptions(ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
        return this;
    }

    public JDDisplayImageOptions setRoundingParams(RoundingParams roundingParams) {
        this.roundingParams = roundingParams;
        return this;
    }

    public JDDisplayImageOptions setTapToControlAnimationEnabled(boolean z) {
        this.tapToControlAnimation = z;
        return this;
    }

    public JDDisplayImageOptions setUsingJavaGifDecoder(boolean z) {
        this.isUsingJavaGifDecoder = z;
        return this;
    }

    public JDDisplayImageOptions showImageForEmptyUri(int i2) {
        this.imageResForEmptyUri = i2;
        return this;
    }

    public JDDisplayImageOptions showImageForEmptyUri(Drawable drawable) {
        this.imageForEmptyUri = drawable;
        return this;
    }

    public JDDisplayImageOptions showImageOnFail(int i2) {
        this.failureImageResId = i2;
        return this;
    }

    public JDDisplayImageOptions showImageOnFail(Drawable drawable) {
        this.failureImage = drawable;
        return this;
    }

    public JDDisplayImageOptions showImageOnLoading(int i2) {
        this.loadingImageResId = i2;
        return this;
    }

    public JDDisplayImageOptions showImageOnLoading(Drawable drawable) {
        this.loadingImage = drawable;
        return this;
    }

    @Deprecated
    JDDisplayImageOptions syncLoading(boolean z) {
        return this;
    }

    public JDDisplayImageOptions useDefaultPlaceholder(boolean z) {
        this.useDefaultPlaceholder = z;
        return this;
    }

    @Deprecated
    public JDDisplayImageOptions useJavaGifDecoder(boolean z) {
        return setUsingJavaGifDecoder(z);
    }
}
