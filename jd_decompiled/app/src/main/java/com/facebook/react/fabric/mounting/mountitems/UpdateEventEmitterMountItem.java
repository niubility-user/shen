package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class UpdateEventEmitterMountItem implements MountItem {
    private final EventEmitterWrapper mEventHandler;
    private final int mReactTag;

    public UpdateEventEmitterMountItem(int i2, EventEmitterWrapper eventEmitterWrapper) {
        this.mReactTag = i2;
        this.mEventHandler = eventEmitterWrapper;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.updateEventEmitter(this.mReactTag, this.mEventHandler);
    }

    public String toString() {
        return "UpdateEventEmitterMountItem [" + this.mReactTag + "]";
    }
}
