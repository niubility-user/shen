package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes12.dex */
public class UpdateLayoutMountItem implements MountItem {
    private final int mHeight;
    private final int mReactTag;
    private final int mWidth;
    private final int mX;
    private final int mY;

    public UpdateLayoutMountItem(int i2, int i3, int i4, int i5, int i6) {
        this.mReactTag = i2;
        this.mX = i3;
        this.mY = i4;
        this.mWidth = i5;
        this.mHeight = i6;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.updateLayout(this.mReactTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public String toString() {
        return "UpdateLayoutMountItem [" + this.mReactTag + "] - x: " + this.mX + " - y: " + this.mY + " - height: " + this.mHeight + " - width: " + this.mWidth;
    }
}
