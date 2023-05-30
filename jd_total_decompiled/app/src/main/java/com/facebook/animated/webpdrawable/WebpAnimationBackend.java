package com.facebook.animated.webpdrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.animated.webp.WebPFrame;
import com.facebook.animated.webp.WebPImage;
import com.facebook.fresco.animation.backend.AnimationBackend;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class WebpAnimationBackend implements AnimationBackend {
    private Rect mBounds;
    private final Rect mRenderDstRect = new Rect();
    private final Rect mRenderSrcRect = new Rect();
    @GuardedBy("this")
    @Nullable
    private Bitmap mTempBitmap;
    private final WebPImage mWebPImage;

    private WebpAnimationBackend(WebPImage webPImage) {
        this.mWebPImage = webPImage;
    }

    private synchronized void clearTempBitmap() {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mTempBitmap = null;
        }
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

    public static WebpAnimationBackend create(String str) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
        }
        try {
            bufferedInputStream.mark(Integer.MAX_VALUE);
            byte[] bArr = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bArr);
            WebPImage createFromByteArray = WebPImage.createFromByteArray(bArr);
            bufferedInputStream.reset();
            WebpAnimationBackend webpAnimationBackend = new WebpAnimationBackend(createFromByteArray);
            closeSilently(bufferedInputStream);
            return webpAnimationBackend;
        } catch (Throwable th2) {
            th = th2;
            closeSilently(bufferedInputStream);
            throw th;
        }
    }

    private synchronized void prepareTempBitmapForThisSize(int i2, int i3) {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null && (bitmap.getWidth() < i2 || this.mTempBitmap.getHeight() < i3)) {
            clearTempBitmap();
        }
        if (this.mTempBitmap == null) {
            this.mTempBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        }
        this.mTempBitmap.eraseColor(0);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void clear() {
        this.mWebPImage.dispose();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public boolean drawFrame(Drawable drawable, Canvas canvas, int i2) {
        WebPFrame frame = this.mWebPImage.getFrame(i2);
        double width = this.mBounds.width();
        double intrinsicWidth = drawable.getIntrinsicWidth();
        Double.isNaN(width);
        Double.isNaN(intrinsicWidth);
        double d = width / intrinsicWidth;
        double height = this.mBounds.height();
        double intrinsicHeight = drawable.getIntrinsicHeight();
        Double.isNaN(height);
        Double.isNaN(intrinsicHeight);
        double d2 = height / intrinsicHeight;
        double width2 = frame.getWidth();
        Double.isNaN(width2);
        int round = (int) Math.round(width2 * d);
        double height2 = frame.getHeight();
        Double.isNaN(height2);
        int round2 = (int) Math.round(height2 * d2);
        double xOffset = frame.getXOffset();
        Double.isNaN(xOffset);
        int i3 = (int) (xOffset * d);
        double yOffset = frame.getYOffset();
        Double.isNaN(yOffset);
        int i4 = (int) (yOffset * d2);
        synchronized (this) {
            int width3 = this.mBounds.width();
            int height3 = this.mBounds.height();
            prepareTempBitmapForThisSize(width3, height3);
            Bitmap bitmap = this.mTempBitmap;
            if (bitmap == null) {
                return false;
            }
            frame.renderFrame(round, round2, bitmap);
            this.mRenderSrcRect.set(0, 0, width3, height3);
            this.mRenderDstRect.set(i3, i4, width3 + i3, height3 + i4);
            canvas.drawBitmap(this.mTempBitmap, this.mRenderSrcRect, this.mRenderDstRect, (Paint) null);
            return true;
        }
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameCount() {
        return this.mWebPImage.getFrameCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameDurationMs(int i2) {
        return this.mWebPImage.getFrameDurations()[i2];
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicHeight() {
        return this.mWebPImage.getHeight();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicWidth() {
        return this.mWebPImage.getWidth();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getLoopCount() {
        return this.mWebPImage.getLoopCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getSizeInBytes() {
        return 0;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setAlpha(int i2) {
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public synchronized void setBounds(Rect rect) {
        this.mBounds = rect;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
