package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.Arrays;

/* loaded from: classes.dex */
public class FadeDrawable extends ArrayDrawable {
    @VisibleForTesting
    public static final int TRANSITION_NONE = 2;
    @VisibleForTesting
    public static final int TRANSITION_RUNNING = 1;
    @VisibleForTesting
    public static final int TRANSITION_STARTING = 0;
    @VisibleForTesting
    int mAlpha;
    @VisibleForTesting
    int[] mAlphas;
    private final int mDefaultLayerAlpha;
    private final boolean mDefaultLayerIsOn;
    @VisibleForTesting
    int mDurationMs;
    @VisibleForTesting
    boolean[] mIsLayerOn;
    private final Drawable[] mLayers;
    @VisibleForTesting
    int mPreventInvalidateCount;
    @VisibleForTesting
    int[] mStartAlphas;
    @VisibleForTesting
    long mStartTimeMs;
    @VisibleForTesting
    int mTransitionState;

    public FadeDrawable(Drawable[] drawableArr) {
        this(drawableArr, false);
    }

    public FadeDrawable(Drawable[] drawableArr, boolean z) {
        super(drawableArr);
        Preconditions.checkState(drawableArr.length >= 1, "At least one layer required!");
        this.mLayers = drawableArr;
        this.mStartAlphas = new int[drawableArr.length];
        this.mAlphas = new int[drawableArr.length];
        this.mAlpha = 255;
        this.mIsLayerOn = new boolean[drawableArr.length];
        this.mPreventInvalidateCount = 0;
        this.mDefaultLayerIsOn = z;
        this.mDefaultLayerAlpha = z ? 255 : 0;
        resetInternal();
    }

    private void drawDrawableWithAlpha(Canvas canvas, Drawable drawable, int i2) {
        if (drawable == null || i2 <= 0) {
            return;
        }
        this.mPreventInvalidateCount++;
        drawable.mutate().setAlpha(i2);
        this.mPreventInvalidateCount--;
        drawable.draw(canvas);
    }

    private void resetInternal() {
        this.mTransitionState = 2;
        Arrays.fill(this.mStartAlphas, this.mDefaultLayerAlpha);
        this.mStartAlphas[0] = 255;
        Arrays.fill(this.mAlphas, this.mDefaultLayerAlpha);
        this.mAlphas[0] = 255;
        Arrays.fill(this.mIsLayerOn, this.mDefaultLayerIsOn);
        this.mIsLayerOn[0] = true;
    }

    private boolean updateAlphas(float f2) {
        boolean z = true;
        for (int i2 = 0; i2 < this.mLayers.length; i2++) {
            boolean[] zArr = this.mIsLayerOn;
            int i3 = zArr[i2] ? 1 : -1;
            int[] iArr = this.mAlphas;
            iArr[i2] = (int) (this.mStartAlphas[i2] + (i3 * 255 * f2));
            if (iArr[i2] < 0) {
                iArr[i2] = 0;
            }
            if (iArr[i2] > 255) {
                iArr[i2] = 255;
            }
            if (zArr[i2] && iArr[i2] < 255) {
                z = false;
            }
            if (!zArr[i2] && iArr[i2] > 0) {
                z = false;
            }
        }
        return z;
    }

    public void beginBatchMode() {
        this.mPreventInvalidateCount++;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r0 != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r0 != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
        r3 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        r3 = r0;
        r1 = 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050 A[LOOP:0: B:22:0x004b->B:24:0x0050, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062 A[EDGE_INSN: B:28:0x0062->B:25:0x0062 BREAK  A[LOOP:0: B:22:0x004b->B:24:0x0050], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r9) {
        /*
            r8 = this;
            int r0 = r8.mTransitionState
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L27
            if (r0 == r3) goto La
            goto L4b
        La:
            int r0 = r8.mDurationMs
            if (r0 <= 0) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            com.facebook.common.internal.Preconditions.checkState(r0)
            long r4 = r8.getCurrentTimeMs()
            long r6 = r8.mStartTimeMs
            long r4 = r4 - r6
            float r0 = (float) r4
            int r4 = r8.mDurationMs
            float r4 = (float) r4
            float r0 = r0 / r4
            boolean r0 = r8.updateAlphas(r0)
            if (r0 == 0) goto L47
            goto L45
        L27:
            int[] r0 = r8.mAlphas
            int[] r4 = r8.mStartAlphas
            android.graphics.drawable.Drawable[] r5 = r8.mLayers
            int r5 = r5.length
            java.lang.System.arraycopy(r0, r2, r4, r2, r5)
            long r4 = r8.getCurrentTimeMs()
            r8.mStartTimeMs = r4
            int r0 = r8.mDurationMs
            if (r0 != 0) goto L3e
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L3f
        L3e:
            r0 = 0
        L3f:
            boolean r0 = r8.updateAlphas(r0)
            if (r0 == 0) goto L47
        L45:
            r3 = r0
            goto L49
        L47:
            r3 = r0
            r1 = 1
        L49:
            r8.mTransitionState = r1
        L4b:
            android.graphics.drawable.Drawable[] r0 = r8.mLayers
            int r1 = r0.length
            if (r2 >= r1) goto L62
            r0 = r0[r2]
            int[] r1 = r8.mAlphas
            r1 = r1[r2]
            int r4 = r8.mAlpha
            int r1 = r1 * r4
            int r1 = r1 / 255
            r8.drawDrawableWithAlpha(r9, r0, r1)
            int r2 = r2 + 1
            goto L4b
        L62:
            if (r3 != 0) goto L67
            r8.invalidateSelf()
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.drawable.FadeDrawable.draw(android.graphics.Canvas):void");
    }

    public void endBatchMode() {
        this.mPreventInvalidateCount--;
        invalidateSelf();
    }

    public void fadeInAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, true);
        invalidateSelf();
    }

    public void fadeInLayer(int i2) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i2] = true;
        invalidateSelf();
    }

    public void fadeOutAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        invalidateSelf();
    }

    public void fadeOutLayer(int i2) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i2] = false;
        invalidateSelf();
    }

    public void fadeToLayer(int i2) {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[i2] = true;
        invalidateSelf();
    }

    public void fadeUpToLayer(int i2) {
        this.mTransitionState = 0;
        int i3 = i2 + 1;
        Arrays.fill(this.mIsLayerOn, 0, i3, true);
        Arrays.fill(this.mIsLayerOn, i3, this.mLayers.length, false);
        invalidateSelf();
    }

    public void finishTransitionImmediately() {
        this.mTransitionState = 2;
        for (int i2 = 0; i2 < this.mLayers.length; i2++) {
            this.mAlphas[i2] = this.mIsLayerOn[i2] ? 255 : 0;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    protected long getCurrentTimeMs() {
        return SystemClock.uptimeMillis();
    }

    public int getTransitionDuration() {
        return this.mDurationMs;
    }

    @VisibleForTesting
    public int getTransitionState() {
        return this.mTransitionState;
    }

    public void hideLayerImmediately(int i2) {
        this.mIsLayerOn[i2] = false;
        this.mAlphas[i2] = 0;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }

    public boolean isDefaultLayerIsOn() {
        return this.mDefaultLayerIsOn;
    }

    public boolean isLayerOn(int i2) {
        return this.mIsLayerOn[i2];
    }

    public void reset() {
        resetInternal();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.ArrayDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mAlpha != i2) {
            this.mAlpha = i2;
            invalidateSelf();
        }
    }

    public void setTransitionDuration(int i2) {
        this.mDurationMs = i2;
        if (this.mTransitionState == 1) {
            this.mTransitionState = 0;
        }
    }

    public void showLayerImmediately(int i2) {
        this.mIsLayerOn[i2] = true;
        this.mAlphas[i2] = 255;
        invalidateSelf();
    }
}
