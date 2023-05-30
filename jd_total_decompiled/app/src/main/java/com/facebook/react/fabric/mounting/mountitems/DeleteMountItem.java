package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class DeleteMountItem implements MountItem {
    private int mReactTag;

    public DeleteMountItem(int i2) {
        this.mReactTag = i2;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.deleteView(this.mReactTag);
    }

    public String toString() {
        return "DeleteMountItem [" + this.mReactTag + "]";
    }
}
