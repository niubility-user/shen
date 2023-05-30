package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class DispatchCommandMountItem implements MountItem {
    @Nullable
    private final ReadableArray mCommandArgs;
    private final int mCommandId;
    private final int mReactTag;

    public DispatchCommandMountItem(int i2, int i3, @Nullable ReadableArray readableArray) {
        this.mReactTag = i2;
        this.mCommandId = i3;
        this.mCommandArgs = readableArray;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        UiThreadUtil.assertOnUiThread();
        mountingManager.receiveCommand(this.mReactTag, this.mCommandId, this.mCommandArgs);
    }

    public String toString() {
        return "DispatchCommandMountItem [" + this.mReactTag + "] " + this.mCommandId;
    }
}
