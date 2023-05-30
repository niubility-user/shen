package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class UpdateLocalDataMountItem implements MountItem {
    private final ReadableMap mNewLocalData;
    private final int mReactTag;

    public UpdateLocalDataMountItem(int i2, ReadableNativeMap readableNativeMap) {
        this.mReactTag = i2;
        this.mNewLocalData = readableNativeMap;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.updateLocalData(this.mReactTag, this.mNewLocalData);
    }

    public ReadableMap getNewLocalData() {
        return this.mNewLocalData;
    }

    public String toString() {
        return "UpdateLocalDataMountItem [" + this.mReactTag + "] - localData: " + this.mNewLocalData;
    }
}
