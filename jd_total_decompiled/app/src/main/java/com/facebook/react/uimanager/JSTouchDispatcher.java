package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import com.facebook.react.uimanager.events.TouchEventType;

/* loaded from: classes12.dex */
public class JSTouchDispatcher {
    private final ViewGroup mRootViewGroup;
    private int mTargetTag = -1;
    private final float[] mTargetCoordinates = new float[2];
    private boolean mChildIsHandlingNativeGesture = false;
    private long mGestureStartTime = Long.MIN_VALUE;
    private final TouchEventCoalescingKeyHelper mTouchEventCoalescingKeyHelper = new TouchEventCoalescingKeyHelper();

    public JSTouchDispatcher(ViewGroup viewGroup) {
        this.mRootViewGroup = viewGroup;
    }

    private void dispatchCancelEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (this.mTargetTag == -1) {
            FLog.w(ReactConstants.TAG, "Can't cancel already finished gesture. Is a child View trying to start a gesture from an UP/CANCEL event?");
            return;
        }
        Assertions.assertCondition(!this.mChildIsHandlingNativeGesture, "Expected to not have already sent a cancel for this gesture");
        int i2 = this.mTargetTag;
        TouchEventType touchEventType = TouchEventType.CANCEL;
        long j2 = this.mGestureStartTime;
        float[] fArr = this.mTargetCoordinates;
        ((EventDispatcher) Assertions.assertNotNull(eventDispatcher)).dispatchEvent(TouchEvent.obtain(i2, touchEventType, motionEvent, j2, fArr[0], fArr[1], this.mTouchEventCoalescingKeyHelper));
    }

    private int findTargetTagAndSetCoordinates(MotionEvent motionEvent) {
        return TouchTargetHelper.findTargetTagAndCoordinatesForTouch(motionEvent.getX(), motionEvent.getY(), this.mRootViewGroup, this.mTargetCoordinates, null);
    }

    public void handleTouchEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.mTargetTag != -1) {
                FLog.e(ReactConstants.TAG, "Got DOWN touch before receiving UP or CANCEL from last gesture");
            }
            this.mChildIsHandlingNativeGesture = false;
            this.mGestureStartTime = motionEvent.getEventTime();
            int findTargetTagAndSetCoordinates = findTargetTagAndSetCoordinates(motionEvent);
            this.mTargetTag = findTargetTagAndSetCoordinates;
            TouchEventType touchEventType = TouchEventType.START;
            long j2 = this.mGestureStartTime;
            float[] fArr = this.mTargetCoordinates;
            eventDispatcher.dispatchEvent(TouchEvent.obtain(findTargetTagAndSetCoordinates, touchEventType, motionEvent, j2, fArr[0], fArr[1], this.mTouchEventCoalescingKeyHelper));
        } else if (this.mChildIsHandlingNativeGesture) {
        } else {
            int i2 = this.mTargetTag;
            if (i2 == -1) {
                FLog.e(ReactConstants.TAG, "Unexpected state: received touch event but didn't get starting ACTION_DOWN for this gesture before");
            } else if (action == 1) {
                findTargetTagAndSetCoordinates(motionEvent);
                int i3 = this.mTargetTag;
                TouchEventType touchEventType2 = TouchEventType.END;
                long j3 = this.mGestureStartTime;
                float[] fArr2 = this.mTargetCoordinates;
                eventDispatcher.dispatchEvent(TouchEvent.obtain(i3, touchEventType2, motionEvent, j3, fArr2[0], fArr2[1], this.mTouchEventCoalescingKeyHelper));
                this.mTargetTag = -1;
                this.mGestureStartTime = Long.MIN_VALUE;
            } else if (action == 2) {
                findTargetTagAndSetCoordinates(motionEvent);
                int i4 = this.mTargetTag;
                TouchEventType touchEventType3 = TouchEventType.MOVE;
                long j4 = this.mGestureStartTime;
                float[] fArr3 = this.mTargetCoordinates;
                eventDispatcher.dispatchEvent(TouchEvent.obtain(i4, touchEventType3, motionEvent, j4, fArr3[0], fArr3[1], this.mTouchEventCoalescingKeyHelper));
            } else if (action == 5) {
                TouchEventType touchEventType4 = TouchEventType.START;
                long j5 = this.mGestureStartTime;
                float[] fArr4 = this.mTargetCoordinates;
                eventDispatcher.dispatchEvent(TouchEvent.obtain(i2, touchEventType4, motionEvent, j5, fArr4[0], fArr4[1], this.mTouchEventCoalescingKeyHelper));
            } else if (action == 6) {
                TouchEventType touchEventType5 = TouchEventType.END;
                long j6 = this.mGestureStartTime;
                float[] fArr5 = this.mTargetCoordinates;
                eventDispatcher.dispatchEvent(TouchEvent.obtain(i2, touchEventType5, motionEvent, j6, fArr5[0], fArr5[1], this.mTouchEventCoalescingKeyHelper));
            } else if (action == 3) {
                if (this.mTouchEventCoalescingKeyHelper.hasCoalescingKey(motionEvent.getDownTime())) {
                    dispatchCancelEvent(motionEvent, eventDispatcher);
                } else {
                    FLog.e(ReactConstants.TAG, "Received an ACTION_CANCEL touch event for which we have no corresponding ACTION_DOWN");
                }
                this.mTargetTag = -1;
                this.mGestureStartTime = Long.MIN_VALUE;
            } else {
                FLog.w(ReactConstants.TAG, "Warning : touch event was ignored. Action=" + action + " Target=" + this.mTargetTag);
            }
        }
    }

    public void onChildStartedNativeGesture(MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (this.mChildIsHandlingNativeGesture) {
            return;
        }
        dispatchCancelEvent(motionEvent, eventDispatcher);
        this.mChildIsHandlingNativeGesture = true;
        this.mTargetTag = -1;
    }
}
