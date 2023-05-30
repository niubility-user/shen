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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void determineScaleAndPosition() {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r6.mMovieWidth     // Catch: java.lang.Throwable -> L4a
            int r1 = r6.mMovieHeight     // Catch: java.lang.Throwable -> L4a
            int r1 = r0 / r1
            float r1 = (float) r1     // Catch: java.lang.Throwable -> L4a
            int r2 = r6.mViewPortWidth     // Catch: java.lang.Throwable -> L4a
            int r3 = r6.mViewPortHeight     // Catch: java.lang.Throwable -> L4a
            int r4 = r2 / r3
            float r4 = (float) r4     // Catch: java.lang.Throwable -> L4a
            int r5 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r5 <= 0) goto L18
            float r4 = (float) r3     // Catch: java.lang.Throwable -> L4a
            float r4 = r4 * r1
            int r1 = (int) r4     // Catch: java.lang.Throwable -> L4a
            goto L23
        L18:
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 >= 0) goto L22
            float r4 = (float) r2     // Catch: java.lang.Throwable -> L4a
            float r4 = r4 / r1
            int r1 = (int) r4     // Catch: java.lang.Throwable -> L4a
            r4 = r1
            r1 = r2
            goto L24
        L22:
            r1 = r2
        L23:
            r4 = r3
        L24:
            if (r2 <= r0) goto L2c
            float r0 = (float) r0     // Catch: java.lang.Throwable -> L4a
            float r5 = (float) r2     // Catch: java.lang.Throwable -> L4a
            float r0 = r0 / r5
            r6.mScale = r0     // Catch: java.lang.Throwable -> L4a
            goto L38
        L2c:
            if (r0 <= r2) goto L34
            float r5 = (float) r2     // Catch: java.lang.Throwable -> L4a
            float r0 = (float) r0     // Catch: java.lang.Throwable -> L4a
            float r5 = r5 / r0
            r6.mScale = r5     // Catch: java.lang.Throwable -> L4a
            goto L38
        L34:
            r0 = 1065353216(0x3f800000, float:1.0)
            r6.mScale = r0     // Catch: java.lang.Throwable -> L4a
        L38:
            int r2 = r2 - r1
            float r0 = (float) r2     // Catch: java.lang.Throwable -> L4a
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            float r2 = r6.mScale     // Catch: java.lang.Throwable -> L4a
            float r0 = r0 / r2
            r6.mLeft = r0     // Catch: java.lang.Throwable -> L4a
            int r3 = r3 - r4
            float r0 = (float) r3     // Catch: java.lang.Throwable -> L4a
            float r0 = r0 / r1
            float r0 = r0 / r2
            r6.mTop = r0     // Catch: java.lang.Throwable -> L4a
            monitor-exit(r6)
            return
        L4a:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.animated.giflite.draw.MovieScaleHolder.determineScaleAndPosition():void");
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
