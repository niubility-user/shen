package com.facebook.animated.giflite.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.animated.giflite.decoder.GifMetadataDecoder;
import com.facebook.fresco.animation.backend.AnimationBackend;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GifAnimationBackend implements AnimationBackend {
    private final int[] mFrameStartTimes;
    private final GifMetadataDecoder mGifDecoder;
    private float mMidX;
    private float mMidY;
    private final Movie mMovie;

    private GifAnimationBackend(GifMetadataDecoder gifMetadataDecoder, Movie movie) {
        this.mGifDecoder = gifMetadataDecoder;
        this.mMovie = movie;
        this.mFrameStartTimes = new int[gifMetadataDecoder.getFrameCount()];
    }

    private static void closeSilently(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static GifAnimationBackend create(String str) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedInputStream.mark(Integer.MAX_VALUE);
            GifMetadataDecoder create = GifMetadataDecoder.create(bufferedInputStream, null);
            bufferedInputStream.reset();
            GifAnimationBackend gifAnimationBackend = new GifAnimationBackend(create, Movie.decodeStream(bufferedInputStream));
            closeSilently(bufferedInputStream);
            return gifAnimationBackend;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            closeSilently(bufferedInputStream2);
            throw th;
        }
    }

    private int getFrameStartTime(int i2) {
        if (i2 != 0) {
            int[] iArr = this.mFrameStartTimes;
            if (i2 < iArr.length) {
                if (iArr[i2] != 0) {
                    return iArr[i2];
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    int[] iArr2 = this.mFrameStartTimes;
                    iArr2[i2] = iArr2[i2] + this.mGifDecoder.getFrameDurationMs(i3);
                }
                return this.mFrameStartTimes[i2];
            }
        }
        return 0;
    }

    private void scale(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        float f2 = i4;
        float f3 = f2 / i5;
        float f4 = i2;
        float f5 = i3;
        float f6 = f4 / f5;
        if (f6 > f3) {
            i6 = (int) (f5 * f3);
        } else if (f6 < f3) {
            i7 = (int) (f4 / f3);
            i6 = i2;
            float f7 = i6 / f2;
            this.mMidX = ((i2 - i6) / 2.0f) / f7;
            this.mMidY = ((i3 - i7) / 2.0f) / f7;
        } else {
            i6 = i2;
        }
        i7 = i3;
        float f72 = i6 / f2;
        this.mMidX = ((i2 - i6) / 2.0f) / f72;
        this.mMidY = ((i3 - i7) / 2.0f) / f72;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void clear() {
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public boolean drawFrame(Drawable drawable, Canvas canvas, int i2) {
        this.mMovie.setTime(getFrameStartTime(i2));
        this.mMovie.draw(canvas, this.mMidX, this.mMidY);
        return true;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameCount() {
        return this.mGifDecoder.getFrameCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameDurationMs(int i2) {
        return this.mGifDecoder.getFrameDurationMs(i2);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicHeight() {
        return this.mMovie.height();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicWidth() {
        return this.mMovie.width();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getLoopCount() {
        return this.mGifDecoder.getLoopCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getSizeInBytes() {
        return 0;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setAlpha(int i2) {
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setBounds(Rect rect) {
        scale(rect.right - rect.left, rect.bottom - rect.top, this.mMovie.width(), this.mMovie.height());
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
