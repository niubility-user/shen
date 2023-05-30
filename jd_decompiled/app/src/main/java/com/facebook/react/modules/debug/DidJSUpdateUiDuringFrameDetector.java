package com.facebook.react.modules.debug;

import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LongArray;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;

/* loaded from: classes12.dex */
public class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener {
    private final LongArray mTransitionToIdleEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mTransitionToBusyEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mViewHierarchyUpdateEnqueuedEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mViewHierarchyUpdateFinishedEvents = LongArray.createWithInitialCapacity(20);
    private volatile boolean mWasIdleAtEndOfLastFrame = true;

    private static void cleanUp(LongArray longArray, long j2) {
        int size = longArray.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (longArray.get(i3) < j2) {
                i2++;
            }
        }
        if (i2 > 0) {
            for (int i4 = 0; i4 < size - i2; i4++) {
                longArray.set(i4, longArray.get(i4 + i2));
            }
            longArray.dropTail(i2);
        }
    }

    private boolean didEndFrameIdle(long j2, long j3) {
        long lastEventBetweenTimestamps = getLastEventBetweenTimestamps(this.mTransitionToIdleEvents, j2, j3);
        long lastEventBetweenTimestamps2 = getLastEventBetweenTimestamps(this.mTransitionToBusyEvents, j2, j3);
        if (lastEventBetweenTimestamps == -1 && lastEventBetweenTimestamps2 == -1) {
            return this.mWasIdleAtEndOfLastFrame;
        }
        return lastEventBetweenTimestamps > lastEventBetweenTimestamps2;
    }

    private static long getLastEventBetweenTimestamps(LongArray longArray, long j2, long j3) {
        long j4 = -1;
        for (int i2 = 0; i2 < longArray.size(); i2++) {
            long j5 = longArray.get(i2);
            if (j5 >= j2 && j5 < j3) {
                j4 = j5;
            } else if (j5 >= j3) {
                break;
            }
        }
        return j4;
    }

    private static boolean hasEventBetweenTimestamps(LongArray longArray, long j2, long j3) {
        for (int i2 = 0; i2 < longArray.size(); i2++) {
            long j4 = longArray.get(i2);
            if (j4 >= j2 && j4 < j3) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean getDidJSHitFrameAndCleanup(long j2, long j3) {
        boolean z;
        boolean hasEventBetweenTimestamps = hasEventBetweenTimestamps(this.mViewHierarchyUpdateFinishedEvents, j2, j3);
        boolean didEndFrameIdle = didEndFrameIdle(j2, j3);
        z = true;
        if (!hasEventBetweenTimestamps && (!didEndFrameIdle || hasEventBetweenTimestamps(this.mViewHierarchyUpdateEnqueuedEvents, j2, j3))) {
            z = false;
        }
        cleanUp(this.mTransitionToIdleEvents, j3);
        cleanUp(this.mTransitionToBusyEvents, j3);
        cleanUp(this.mViewHierarchyUpdateEnqueuedEvents, j3);
        cleanUp(this.mViewHierarchyUpdateFinishedEvents, j3);
        this.mWasIdleAtEndOfLastFrame = didEndFrameIdle;
        return z;
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeBusy() {
        this.mTransitionToBusyEvents.add(System.nanoTime());
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeIdle() {
        this.mTransitionToIdleEvents.add(System.nanoTime());
    }

    @Override // com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener
    public synchronized void onViewHierarchyUpdateEnqueued() {
        this.mViewHierarchyUpdateEnqueuedEvents.add(System.nanoTime());
    }

    @Override // com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener
    public synchronized void onViewHierarchyUpdateFinished() {
        this.mViewHierarchyUpdateFinishedEvents.add(System.nanoTime());
    }
}
