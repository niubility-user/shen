package com.jingdong.common.imagegifexpand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.DefaultSerialExecutorService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR$\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010%\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&\u00a8\u0006)"}, d2 = {"Lcom/jingdong/common/imagegifexpand/DefaultAnimateFactoryImpl;", "Lcom/facebook/imagepipeline/animated/factory/AnimatedFactory;", "Lcom/jingdong/common/imagegifexpand/BitmapAnimationDrawableFactory;", "createNewDrawableFactory", "()Lcom/jingdong/common/imagegifexpand/BitmapAnimationDrawableFactory;", "Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;", "getNewAnimatedDrawableBackendProvider", "()Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;", "Lcom/facebook/imagepipeline/animated/util/AnimatedDrawableUtil;", "getNewAnimatedDrawableUtil", "()Lcom/facebook/imagepipeline/animated/util/AnimatedDrawableUtil;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/facebook/imagepipeline/drawable/DrawableFactory;", "getAnimatedDrawableFactory", "(Landroid/content/Context;)Lcom/facebook/imagepipeline/drawable/DrawableFactory;", "Landroid/graphics/Bitmap$Config;", "p0", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getGifDecoder", "(Landroid/graphics/Bitmap$Config;)Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getWebPDecoder", "Lcom/jingdong/common/imagegifexpand/AnimateConfig;", "animateConfig", "Lcom/jingdong/common/imagegifexpand/AnimateConfig;", "mAnimatedDrawableUtil", "Lcom/facebook/imagepipeline/animated/util/AnimatedDrawableUtil;", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "backingCache", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "mAnimatedDrawableFactory", "Lcom/facebook/imagepipeline/drawable/DrawableFactory;", "mAnimatedDrawableBackendProvider", "Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;", "<init>", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/imagepipeline/cache/CountingMemoryCache;Lcom/jingdong/common/imagegifexpand/AnimateConfig;)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class DefaultAnimateFactoryImpl implements AnimatedFactory {
    private final AnimateConfig animateConfig;
    private final CountingMemoryCache<CacheKey, CloseableImage> backingCache;
    private AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private DrawableFactory mAnimatedDrawableFactory;
    private AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final PlatformBitmapFactory platformBitmapFactory;

    public DefaultAnimateFactoryImpl(@Nullable PlatformBitmapFactory platformBitmapFactory, @Nullable CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, @NotNull AnimateConfig animateConfig) {
        this.platformBitmapFactory = platformBitmapFactory;
        this.backingCache = countingMemoryCache;
        this.animateConfig = animateConfig;
    }

    private final BitmapAnimationDrawableFactory createNewDrawableFactory() {
        DefaultSerialExecutorService defaultSerialExecutorService;
        Supplier<Integer> supplier = new Supplier<Integer>() { // from class: com.jingdong.common.imagegifexpand.DefaultAnimateFactoryImpl$createNewDrawableFactory$cachingStrategySupplier$1
            @Override // com.facebook.common.internal.Supplier
            public /* bridge */ /* synthetic */ Integer get() {
                return Integer.valueOf(get2());
            }

            /* renamed from: get  reason: avoid collision after fix types in other method */
            public final int get2() {
                AnimateConfig animateConfig;
                animateConfig = DefaultAnimateFactoryImpl.this.animateConfig;
                return animateConfig.getCachingStrategyType();
            }
        };
        FramePreparingExecutor framePreparingExecutor = this.animateConfig.getFramePreparingExecutor();
        if (framePreparingExecutor != null) {
            defaultSerialExecutorService = new DefaultSerialExecutorService(framePreparingExecutor.get());
        } else {
            defaultSerialExecutorService = this.animateConfig.getNumberOfFramesToPrepare() > 0 ? new DefaultSerialExecutorService(DefaultAnimateExecutor.INSTANCE.getInstance().get()) : null;
        }
        return new BitmapAnimationDrawableFactory(getNewAnimatedDrawableBackendProvider(), UiThreadImmediateExecutorService.getInstance(), defaultSerialExecutorService, RealtimeSinceBootClock.get(), this.platformBitmapFactory, this.backingCache, supplier, new Supplier<Integer>() { // from class: com.jingdong.common.imagegifexpand.DefaultAnimateFactoryImpl$createNewDrawableFactory$numberOfFramesToPrepareSupplier$1
            @Override // com.facebook.common.internal.Supplier
            public /* bridge */ /* synthetic */ Integer get() {
                return Integer.valueOf(get2());
            }

            /* renamed from: get  reason: avoid collision after fix types in other method */
            public final int get2() {
                AnimateConfig animateConfig;
                animateConfig = DefaultAnimateFactoryImpl.this.animateConfig;
                int numberOfFramesToPrepare = animateConfig.getNumberOfFramesToPrepare();
                if (numberOfFramesToPrepare > 3) {
                    return 3;
                }
                return numberOfFramesToPrepare;
            }
        }, this.animateConfig.getBitmapConfig565Support() ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888);
    }

    private final AnimatedDrawableBackendProvider getNewAnimatedDrawableBackendProvider() {
        AnimatedDrawableBackendProvider animatedDrawableBackendProvider = this.mAnimatedDrawableBackendProvider;
        if (animatedDrawableBackendProvider != null) {
            return animatedDrawableBackendProvider;
        }
        AnimatedDrawableBackendProvider animatedDrawableBackendProvider2 = new AnimatedDrawableBackendProvider() { // from class: com.jingdong.common.imagegifexpand.DefaultAnimateFactoryImpl$getNewAnimatedDrawableBackendProvider$animatedDrawableBackendProvider$1
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider
            @NotNull
            public final AnimatedDrawableBackendImpl get(AnimatedImageResult animatedImageResult, Rect rect) {
                AnimatedDrawableUtil newAnimatedDrawableUtil;
                newAnimatedDrawableUtil = DefaultAnimateFactoryImpl.this.getNewAnimatedDrawableUtil();
                return new AnimatedDrawableBackendImpl(newAnimatedDrawableUtil, animatedImageResult, rect, false);
            }
        };
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider2;
        return animatedDrawableBackendProvider2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnimatedDrawableUtil getNewAnimatedDrawableUtil() {
        AnimatedDrawableUtil animatedDrawableUtil = this.mAnimatedDrawableUtil;
        if (animatedDrawableUtil != null) {
            return animatedDrawableUtil;
        }
        AnimatedDrawableUtil animatedDrawableUtil2 = new AnimatedDrawableUtil();
        this.mAnimatedDrawableUtil = animatedDrawableUtil2;
        return animatedDrawableUtil2;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    @NotNull
    public DrawableFactory getAnimatedDrawableFactory(@Nullable Context context) {
        DrawableFactory drawableFactory = this.mAnimatedDrawableFactory;
        if (drawableFactory != null) {
            return drawableFactory;
        }
        BitmapAnimationDrawableFactory createNewDrawableFactory = createNewDrawableFactory();
        this.mAnimatedDrawableFactory = createNewDrawableFactory;
        return createNewDrawableFactory;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    @Nullable
    public ImageDecoder getGifDecoder(@Nullable Bitmap.Config p0) {
        return null;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedFactory
    @Nullable
    public ImageDecoder getWebPDecoder(@Nullable Bitmap.Config p0) {
        return null;
    }
}
