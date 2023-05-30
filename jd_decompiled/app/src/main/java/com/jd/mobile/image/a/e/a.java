package com.jd.mobile.image.a.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.filter.IterativeBoxBlurFilter;
import com.facebook.imagepipeline.filter.RenderScriptBlurFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import javax.annotation.Nullable;

/* loaded from: classes17.dex */
public class a extends BasePostprocessor {

    /* renamed from: g  reason: collision with root package name */
    private static final boolean f6844g = RenderScriptBlurFilter.canUseRenderScript();
    private final int a;
    private final Context b;

    /* renamed from: c  reason: collision with root package name */
    private final int f6845c;
    private final int d;

    /* renamed from: e  reason: collision with root package name */
    private CacheKey f6846e;

    /* renamed from: f  reason: collision with root package name */
    private final Paint f6847f;

    public a(Context context, int i2, int i3) {
        this(context, i2, i3, 3);
    }

    public a(Context context, int i2, int i3, int i4) {
        this.f6847f = new Paint();
        Preconditions.checkArgument(i2 > 0 && i2 <= 25);
        Preconditions.checkArgument(i4 > 0);
        Preconditions.checkNotNull(context);
        this.a = i4;
        this.f6845c = i2;
        this.d = i3;
        this.b = context;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        if (this.f6846e == null) {
            this.f6846e = new SimpleCacheKey(f6844g ? String.format(null, "IntrinsicBlur;%d;%d", Integer.valueOf(this.f6845c), Integer.valueOf(this.d)) : String.format(null, "IterativeBoxBlur;%d;%d;%d", Integer.valueOf(this.a), Integer.valueOf(this.f6845c), Integer.valueOf(this.d)));
        }
        return this.f6846e;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth() / this.d;
        int height = bitmap.getHeight() / this.d;
        if (config == null) {
            config = BasePostprocessor.FALLBACK_BITMAP_CONFIGURATION;
        }
        CloseableReference<Bitmap> createBitmapInternal = platformBitmapFactory.createBitmapInternal(width, height, config);
        try {
            Bitmap bitmap2 = createBitmapInternal.get();
            new Canvas(bitmap2).drawBitmap(bitmap, (Rect) null, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), this.f6847f);
            process(bitmap2, bitmap2);
            return CloseableReference.cloneOrNull(createBitmapInternal);
        } finally {
            CloseableReference.closeSafely(createBitmapInternal);
        }
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(Bitmap bitmap) {
        IterativeBoxBlurFilter.boxBlurBitmapInPlace(bitmap, this.a, this.f6845c);
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(Bitmap bitmap, Bitmap bitmap2) {
        if (f6844g) {
            RenderScriptBlurFilter.blurBitmap(bitmap, bitmap2, this.b, this.f6845c);
        } else {
            super.process(bitmap, bitmap2);
        }
    }
}
