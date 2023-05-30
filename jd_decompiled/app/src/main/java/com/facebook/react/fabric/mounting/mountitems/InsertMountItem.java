package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class InsertMountItem implements MountItem {
    private int mIndex;
    private int mParentReactTag;
    private int mReactTag;

    public InsertMountItem(int i2, int i3, int i4) {
        this.mReactTag = i2;
        this.mParentReactTag = i3;
        this.mIndex = i4;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.addViewAt(this.mParentReactTag, this.mReactTag, this.mIndex);
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getParentReactTag() {
        return this.mParentReactTag;
    }

    public String toString() {
        return "InsertMountItem [" + this.mReactTag + "] - parentTag: " + this.mParentReactTag + " - index: " + this.mIndex;
    }
}
