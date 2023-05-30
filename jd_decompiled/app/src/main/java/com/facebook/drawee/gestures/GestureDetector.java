package com.facebook.drawee.gestures;

import android.content.Context;
import android.view.ViewConfiguration;
import com.facebook.common.internal.VisibleForTesting;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GestureDetector {
    @VisibleForTesting
    long mActionDownTime;
    @VisibleForTesting
    float mActionDownX;
    @VisibleForTesting
    float mActionDownY;
    @VisibleForTesting
    @Nullable
    ClickListener mClickListener;
    @VisibleForTesting
    boolean mIsCapturingGesture;
    @VisibleForTesting
    boolean mIsClickCandidate;
    @VisibleForTesting
    final float mSingleTapSlopPx;

    /* loaded from: classes.dex */
    public interface ClickListener {
        boolean onClick();
    }

    public GestureDetector(Context context) {
        this.mSingleTapSlopPx = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public static GestureDetector newInstance(Context context) {
        return new GestureDetector(context);
    }

    public void init() {
        this.mClickListener = null;
        reset();
    }

    public boolean isCapturingGesture() {
        return this.mIsCapturingGesture;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (java.lang.Math.abs(r8.getY() - r7.mActionDownY) <= r7.mSingleTapSlopPx) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            int r0 = r8.getAction()
            r1 = 1
            if (r0 == 0) goto L7d
            r2 = 0
            if (r0 == r1) goto L3b
            r3 = 2
            if (r0 == r3) goto L18
            r8 = 3
            if (r0 == r8) goto L12
            goto L93
        L12:
            r7.mIsCapturingGesture = r2
        L14:
            r7.mIsClickCandidate = r2
            goto L93
        L18:
            float r0 = r8.getX()
            float r3 = r7.mActionDownX
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            float r3 = r7.mSingleTapSlopPx
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L14
            float r8 = r8.getY()
            float r0 = r7.mActionDownY
            float r8 = r8 - r0
            float r8 = java.lang.Math.abs(r8)
            float r0 = r7.mSingleTapSlopPx
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 <= 0) goto L93
            goto L14
        L3b:
            r7.mIsCapturingGesture = r2
            float r0 = r8.getX()
            float r3 = r7.mActionDownX
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            float r3 = r7.mSingleTapSlopPx
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L5f
            float r0 = r8.getY()
            float r3 = r7.mActionDownY
            float r0 = r0 - r3
            float r0 = java.lang.Math.abs(r0)
            float r3 = r7.mSingleTapSlopPx
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L61
        L5f:
            r7.mIsClickCandidate = r2
        L61:
            boolean r0 = r7.mIsClickCandidate
            if (r0 == 0) goto L14
            long r3 = r8.getEventTime()
            long r5 = r7.mActionDownTime
            long r3 = r3 - r5
            int r8 = android.view.ViewConfiguration.getLongPressTimeout()
            long r5 = (long) r8
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 > 0) goto L14
            com.facebook.drawee.gestures.GestureDetector$ClickListener r8 = r7.mClickListener
            if (r8 == 0) goto L14
            r8.onClick()
            goto L14
        L7d:
            r7.mIsCapturingGesture = r1
            r7.mIsClickCandidate = r1
            long r2 = r8.getEventTime()
            r7.mActionDownTime = r2
            float r0 = r8.getX()
            r7.mActionDownX = r0
            float r8 = r8.getY()
            r7.mActionDownY = r8
        L93:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.gestures.GestureDetector.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void reset() {
        this.mIsCapturingGesture = false;
        this.mIsClickCandidate = false;
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }
}
