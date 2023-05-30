package com.facebook.animated.giflite.draw;

/* loaded from: classes.dex */
class MovieScaleHolder {
    private final int mMovieHeight;
    private final int mMovieWidth;
    private int mViewPortHeight;
    private int mViewPortWidth;
    private float mScale = 1.0f;
    private float mLeft = 0.0f;
    private float mTop = 0.0f;

    public MovieScaleHolder(int i2, int i3) {
        this.mMovieWidth = i2;
        this.mMovieHeight = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026 A[Catch: all -> 0x004a, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:12:0x0026, B:16:0x0038, B:14:0x002e, B:15:0x0034, B:8:0x001c), top: B:22:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void determineScaleAndPosition() {
        int i2;
        int i3;
        int i4 = this.mMovieWidth;
        float f2 = i4 / this.mMovieHeight;
        int i5 = this.mViewPortWidth;
        int i6 = this.mViewPortHeight;
        float f3 = i5 / i6;
        if (f3 > f2) {
            i2 = (int) (i6 * f2);
        } else if (f3 < f2) {
            i3 = (int) (i5 / f2);
            i2 = i5;
            if (i5 <= i4) {
                this.mScale = i4 / i5;
            } else if (i4 > i5) {
                this.mScale = i5 / i4;
            } else {
                this.mScale = 1.0f;
            }
            float f4 = this.mScale;
            this.mLeft = ((i5 - i2) / 2.0f) / f4;
            this.mTop = ((i6 - i3) / 2.0f) / f4;
        } else {
            i2 = i5;
        }
        i3 = i6;
        if (i5 <= i4) {
        }
        float f42 = this.mScale;
        this.mLeft = ((i5 - i2) / 2.0f) / f42;
        this.mTop = ((i6 - i3) / 2.0f) / f42;
    }

    public synchronized float getLeft() {
        return this.mLeft;
    }

    public synchronized float getScale() {
        return this.mScale;
    }

    public synchronized float getTop() {
        return this.mTop;
    }

    public synchronized void updateViewPort(int i2, int i3) {
        if (this.mViewPortWidth == i2 && this.mViewPortHeight == i3) {
            return;
        }
        this.mViewPortWidth = i2;
        this.mViewPortHeight = i3;
        determineScaleAndPosition();
    }
}
