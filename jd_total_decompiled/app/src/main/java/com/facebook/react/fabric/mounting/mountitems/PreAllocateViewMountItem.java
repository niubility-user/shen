package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.uimanager.ThemedReactContext;

/* loaded from: classes12.dex */
public class PreAllocateViewMountItem implements MountItem {
    private final String mComponent;
    private final ThemedReactContext mContext;
    private final int mRootTag;

    public PreAllocateViewMountItem(ThemedReactContext themedReactContext, int i2, String str) {
        this.mContext = themedReactContext;
        this.mComponent = str;
        this.mRootTag = i2;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.preallocateView(this.mContext, this.mComponent);
    }

    public String toString() {
        return "[" + this.mRootTag + "] - Preallocate " + this.mComponent;
    }
}
