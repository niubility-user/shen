package com.facebook.animated.giflite.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class MovieDrawer {
    private final Canvas mCanvas = new Canvas();
    private final Movie mMovie;
    @Nullable
    private Bitmap mPreviousBitmap;
    private final MovieScaleHolder mScaleHolder;

    public MovieDrawer(Movie movie) {
        this.mMovie = movie;
        this.mScaleHolder = new MovieScaleHolder(movie.width(), movie.height());
    }

    public synchronized void drawFrame(int i2, int i3, int i4, Bitmap bitmap) {
        this.mMovie.setTime(i2);
        Bitmap bitmap2 = this.mPreviousBitmap;
        if (bitmap2 != null && bitmap2.isRecycled()) {
            this.mPreviousBitmap = null;
        }
        if (this.mPreviousBitmap != bitmap) {
            this.mPreviousBitmap = bitmap;
            this.mCanvas.setBitmap(bitmap);
        }
        this.mScaleHolder.updateViewPort(i3, i4);
        this.mCanvas.save();
        this.mCanvas.scale(this.mScaleHolder.getScale(), this.mScaleHolder.getScale());
        this.mMovie.draw(this.mCanvas, this.mScaleHolder.getLeft(), this.mScaleHolder.getTop());
        this.mCanvas.restore();
    }
}
