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
    */
    public void draw(Canvas canvas) {
        boolean updateAlphas;
        Drawable[] drawableArr;
        int i2 = this.mTransitionState;
        int i3 = 2;
        int i4 = 0;
        boolean z = true;
        if (i2 != 0) {
            if (i2 == 1) {
                Preconditions.checkState(this.mDurationMs > 0);
                updateAlphas = updateAlphas(((float) (getCurrentTimeMs() - this.mStartTimeMs)) / this.mDurationMs);
            }
            while (true) {
                drawableArr = this.mLayers;
                if (i4 < drawableArr.length) {
                    break;
                }
                drawDrawableWithAlpha(canvas, drawableArr[i4], (this.mAlphas[i4] * this.mAlpha) / 255);
                i4++;
            }
            if (z) {
                invalidateSelf();
                return;
            }
            return;
        }
        System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
        this.mStartTimeMs = getCurrentTimeMs();
        updateAlphas = updateAlphas(this.mDurationMs == 0 ? 1.0f : 0.0f);
        this.mTransitionState = i3;
        while (true) {
            drawableArr = this.mLayers;
            if (i4 < drawableArr.length) {
            }
            drawDrawableWithAlpha(canvas, drawableArr[i4], (this.mAlphas[i4] * this.mAlpha) / 255);
            i4++;
        }
        if (z) {
        }
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
