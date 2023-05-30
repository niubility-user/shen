package com.facebook.react.uimanager.events;

import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes12.dex */
public abstract class Event<T extends Event> {
    private static int sUniqueID;
    private boolean mInitialized;
    private long mTimestampMs;
    private int mUniqueID;
    private int mViewTag;

    /* JADX INFO: Access modifiers changed from: protected */
    public Event() {
        int i2 = sUniqueID;
        sUniqueID = i2 + 1;
        this.mUniqueID = i2;
    }

    public boolean canCoalesce() {
        return true;
    }

    public T coalesce(T t) {
        return getTimestampMs() >= t.getTimestampMs() ? this : t;
    }

    public abstract void dispatch(RCTEventEmitter rCTEventEmitter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispose() {
        this.mInitialized = false;
        onDispose();
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public abstract String getEventName();

    public final long getTimestampMs() {
        return this.mTimestampMs;
    }

    public int getUniqueID() {
        return this.mUniqueID;
    }

    public final int getViewTag() {
        return this.mViewTag;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int i2) {
        this.mViewTag = i2;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void onDispose() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event(int i2) {
        int i3 = sUniqueID;
        sUniqueID = i3 + 1;
        this.mUniqueID = i3;
        init(i2);
    }
}
