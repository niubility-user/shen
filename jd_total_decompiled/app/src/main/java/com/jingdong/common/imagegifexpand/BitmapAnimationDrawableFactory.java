package com.jingdong.common.imagegifexpand;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegate;
import com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck;
import com.facebook.fresco.animation.bitmap.BitmapAnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.cache.AnimationFrameCacheKey;
import com.facebook.fresco.animation.bitmap.cache.FrescoFrameCache;
import com.facebook.fresco.animation.bitmap.cache.KeepLastFrameCache;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer;
import com.facebook.fresco.animation.bitmap.preparation.DefaultBitmapFramePreparer;
import com.facebook.fresco.animation.bitmap.preparation.FixedNumberBitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendAnimationInformation;
import com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendFrameRenderer;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedFrameCache;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 :2\u00020\u0001:\u0001:Bu\u0012\u0006\u0010-\u001a\u00020,\u0012\b\u00101\u001a\u0004\u0018\u000100\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0017\u0018\u00010%\u0012\u000e\u00107\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103\u0012\u000e\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103\u0012\u0006\u0010*\u001a\u00020)\u00a2\u0006\u0004\b8\u00109J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R$\u0010'\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0017\u0018\u00010%8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010-\u001a\u00020,8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010/R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R\u001e\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u0001038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u001e\u00107\u001a\n\u0012\u0004\u0012\u000204\u0018\u0001038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00106\u00a8\u0006;"}, d2 = {"Lcom/jingdong/common/imagegifexpand/BitmapAnimationDrawableFactory;", "Lcom/facebook/imagepipeline/drawable/DrawableFactory;", "Lcom/facebook/imagepipeline/animated/base/AnimatedImageResult;", "animatedImageResult", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "createAnimationBackend", "(Lcom/facebook/imagepipeline/animated/base/AnimatedImageResult;)Lcom/facebook/fresco/animation/backend/AnimationBackend;", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "bitmapFrameRenderer", "Ljava/util/concurrent/ExecutorService;", "executorServiceForFramePreparing", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "createBitmapFramePreparer", "(Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Ljava/util/concurrent/ExecutorService;)Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "Lcom/facebook/imagepipeline/animated/base/AnimatedDrawableBackend;", "createAnimatedDrawableBackend", "(Lcom/facebook/imagepipeline/animated/base/AnimatedImageResult;)Lcom/facebook/imagepipeline/animated/base/AnimatedDrawableBackend;", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "createBitmapFrameCache", "(Lcom/facebook/imagepipeline/animated/base/AnimatedImageResult;)Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "Lcom/facebook/imagepipeline/animated/impl/AnimatedFrameCache;", "createAnimatedFrameCache", "(Lcom/facebook/imagepipeline/animated/base/AnimatedImageResult;)Lcom/facebook/imagepipeline/animated/impl/AnimatedFrameCache;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "image", "", "supportsImageType", "(Lcom/facebook/imagepipeline/image/CloseableImage;)Z", "Landroid/graphics/drawable/Drawable;", "createDrawable", "(Lcom/facebook/imagepipeline/image/CloseableImage;)Landroid/graphics/drawable/Drawable;", "Lcom/facebook/common/time/MonotonicClock;", "monotonicClock", "Lcom/facebook/common/time/MonotonicClock;", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "backingCache", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "Landroid/graphics/Bitmap$Config;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;", "animatedDrawableBackendProvider", "Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;", "Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/ScheduledExecutorService;", "scheduledExecutorServiceForUiThread", "Ljava/util/concurrent/ScheduledExecutorService;", "Lcom/facebook/common/internal/Supplier;", "", "numberOfFramesToPrepareSupplier", "Lcom/facebook/common/internal/Supplier;", "cachingStrategySupplier", "<init>", "(Lcom/facebook/imagepipeline/animated/impl/AnimatedDrawableBackendProvider;Ljava/util/concurrent/ScheduledExecutorService;Ljava/util/concurrent/ExecutorService;Lcom/facebook/common/time/MonotonicClock;Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/imagepipeline/cache/CountingMemoryCache;Lcom/facebook/common/internal/Supplier;Lcom/facebook/common/internal/Supplier;Landroid/graphics/Bitmap$Config;)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class BitmapAnimationDrawableFactory implements DrawableFactory {
    public static final int CACHING_STRATEGY_FRESCO_CACHE = 1;
    public static final int CACHING_STRATEGY_FRESCO_CACHE_NO_REUSING = 2;
    public static final int CACHING_STRATEGY_KEEP_LAST_CACHE = 3;
    private final AnimatedDrawableBackendProvider animatedDrawableBackendProvider;
    private final CountingMemoryCache<CacheKey, CloseableImage> backingCache;
    private final Bitmap.Config bitmapConfig;
    private final Supplier<Integer> cachingStrategySupplier;
    private final ExecutorService executorServiceForFramePreparing;
    private final MonotonicClock monotonicClock;
    private final Supplier<Integer> numberOfFramesToPrepareSupplier;
    private final PlatformBitmapFactory platformBitmapFactory;
    private final ScheduledExecutorService scheduledExecutorServiceForUiThread;

    public BitmapAnimationDrawableFactory(@NotNull AnimatedDrawableBackendProvider animatedDrawableBackendProvider, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable ExecutorService executorService, @Nullable MonotonicClock monotonicClock, @Nullable PlatformBitmapFactory platformBitmapFactory, @Nullable CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, @Nullable Supplier<Integer> supplier, @Nullable Supplier<Integer> supplier2, @NotNull Bitmap.Config config) {
        this.animatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.scheduledExecutorServiceForUiThread = scheduledExecutorService;
        this.executorServiceForFramePreparing = executorService;
        this.monotonicClock = monotonicClock;
        this.platformBitmapFactory = platformBitmapFactory;
        this.backingCache = countingMemoryCache;
        this.cachingStrategySupplier = supplier;
        this.numberOfFramesToPrepareSupplier = supplier2;
        this.bitmapConfig = config;
    }

    private final AnimatedDrawableBackend createAnimatedDrawableBackend(AnimatedImageResult animatedImageResult) {
        AnimatedImage animatedImage = animatedImageResult.getImage();
        Intrinsics.checkExpressionValueIsNotNull(animatedImage, "animatedImage");
        AnimatedDrawableBackend animatedDrawableBackend = this.animatedDrawableBackendProvider.get(animatedImageResult, new Rect(0, 0, animatedImage.getWidth(), animatedImage.getHeight()));
        Intrinsics.checkExpressionValueIsNotNull(animatedDrawableBackend, "animatedDrawableBackendP\u2026ageResult, initialBounds)");
        return animatedDrawableBackend;
    }

    private final AnimatedFrameCache createAnimatedFrameCache(AnimatedImageResult animatedImageResult) {
        return new AnimatedFrameCache(new AnimationFrameCacheKey(animatedImageResult.hashCode()), this.backingCache);
    }

    private final AnimationBackend createAnimationBackend(AnimatedImageResult animatedImageResult) {
        FixedNumberBitmapFramePreparationStrategy fixedNumberBitmapFramePreparationStrategy;
        BitmapFramePreparer bitmapFramePreparer;
        Integer num;
        AnimatedDrawableBackend createAnimatedDrawableBackend = createAnimatedDrawableBackend(animatedImageResult);
        BitmapFrameCache createBitmapFrameCache = createBitmapFrameCache(animatedImageResult);
        AnimatedDrawableBackendFrameRenderer animatedDrawableBackendFrameRenderer = new AnimatedDrawableBackendFrameRenderer(createBitmapFrameCache, createAnimatedDrawableBackend);
        Supplier<Integer> supplier = this.numberOfFramesToPrepareSupplier;
        int intValue = (supplier == null || (num = supplier.get()) == null) ? 0 : num.intValue();
        if (intValue <= 0 || this.executorServiceForFramePreparing == null) {
            fixedNumberBitmapFramePreparationStrategy = null;
            bitmapFramePreparer = null;
        } else {
            FixedNumberBitmapFramePreparationStrategy fixedNumberBitmapFramePreparationStrategy2 = new FixedNumberBitmapFramePreparationStrategy(intValue);
            bitmapFramePreparer = createBitmapFramePreparer(animatedDrawableBackendFrameRenderer, this.executorServiceForFramePreparing);
            fixedNumberBitmapFramePreparationStrategy = fixedNumberBitmapFramePreparationStrategy2;
        }
        BitmapAnimationBackend bitmapAnimationBackend = new BitmapAnimationBackend(this.platformBitmapFactory, createBitmapFrameCache, new AnimatedDrawableBackendAnimationInformation(createAnimatedDrawableBackend), animatedDrawableBackendFrameRenderer, fixedNumberBitmapFramePreparationStrategy, bitmapFramePreparer);
        bitmapAnimationBackend.setBitmapConfig(this.bitmapConfig);
        AnimationBackendDelegate createForBackend = AnimationBackendDelegateWithInactivityCheck.createForBackend(bitmapAnimationBackend, this.monotonicClock, this.scheduledExecutorServiceForUiThread);
        Intrinsics.checkExpressionValueIsNotNull(createForBackend, "AnimationBackendDelegate\u2026viceForUiThread\n        )");
        return createForBackend;
    }

    private final BitmapFrameCache createBitmapFrameCache(AnimatedImageResult animatedImageResult) {
        Integer num;
        Supplier<Integer> supplier = this.cachingStrategySupplier;
        int intValue = (supplier == null || (num = supplier.get()) == null) ? 1 : num.intValue();
        if (intValue != 1) {
            if (intValue != 2) {
                if (intValue != 3) {
                    return new FrescoFrameCache(createAnimatedFrameCache(animatedImageResult), true);
                }
                return new KeepLastFrameCache();
            }
            return new FrescoFrameCache(createAnimatedFrameCache(animatedImageResult), false);
        }
        return new FrescoFrameCache(createAnimatedFrameCache(animatedImageResult), true);
    }

    private final BitmapFramePreparer createBitmapFramePreparer(BitmapFrameRenderer bitmapFrameRenderer, ExecutorService executorServiceForFramePreparing) {
        return new DefaultBitmapFramePreparer(this.platformBitmapFactory, bitmapFrameRenderer, this.bitmapConfig, executorServiceForFramePreparing);
    }

    @Override // com.facebook.imagepipeline.drawable.DrawableFactory
    @Nullable
    public Drawable createDrawable(@NotNull CloseableImage image) {
        try {
            Object checkNotNull = Preconditions.checkNotNull(((CloseableAnimatedImage) image).getImageResult());
            Intrinsics.checkExpressionValueIsNotNull(checkNotNull, "Preconditions.checkNotNull(closeable.imageResult)");
            return new AnimatedDrawable2(createAnimationBackend((AnimatedImageResult) checkNotNull));
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.facebook.imagepipeline.drawable.DrawableFactory
    public boolean supportsImageType(@NotNull CloseableImage image) {
        return image instanceof CloseableAnimatedImage;
    }
}
