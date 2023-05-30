package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.systrace.Systrace;

@DoNotStrip
/* loaded from: classes12.dex */
public class BatchMountItem implements MountItem {
    private final MountItem[] mMountItems;
    private final int mSize;

    public BatchMountItem(MountItem[] mountItemArr, int i2) {
        mountItemArr.getClass();
        if (i2 >= 0 && i2 <= mountItemArr.length) {
            this.mMountItems = mountItemArr;
            this.mSize = i2;
            return;
        }
        throw new IllegalArgumentException("Invalid size received by parameter size: " + i2 + " items.size = " + mountItemArr.length);
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        Systrace.beginSection(0L, "FabricUIManager::mountViews (" + this.mSize + " items)");
        for (int i2 = 0; i2 < this.mSize; i2++) {
            this.mMountItems[i2].execute(mountingManager);
        }
        Systrace.endSection(0L);
    }
}
