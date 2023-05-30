package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;

/* loaded from: classes9.dex */
public final class TileOverlayOptions {
    private volatile String mDiskCacheDir;
    private int mMaxMemoryCacheSize;
    private TileProvider mTileProvider;
    private volatile String mVersionInfo;
    private int mZIndex = 1;
    private boolean mBetterQuality = true;

    public TileOverlayOptions betterQuality(boolean z) {
        this.mBetterQuality = z;
        return this;
    }

    public TileOverlayOptions diskCacheDir(String str) {
        this.mDiskCacheDir = str;
        return this;
    }

    public String getDiskCacheDir() {
        return this.mDiskCacheDir;
    }

    public int getMaxMemoryCacheSize(TencentMapContext tencentMapContext) {
        int i2 = this.mMaxMemoryCacheSize;
        return i2 == 0 ? tencentMapContext.getScreenPixels() * 4 : i2;
    }

    public TileProvider getTileProvider() {
        return this.mTileProvider;
    }

    public String getVersionInfo() {
        return this.mVersionInfo;
    }

    public int getZIndex() {
        return this.mZIndex;
    }

    public boolean isBetterQuality() {
        return this.mBetterQuality;
    }

    public TileOverlayOptions maxMemoryCacheSize(int i2) {
        this.mMaxMemoryCacheSize = i2;
        return this;
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.mTileProvider = tileProvider;
        return this;
    }

    public String toString() {
        return "{mDiskCacheDir='" + this.mDiskCacheDir + "', mVersionInfo='" + this.mVersionInfo + "', mZIndex=" + this.mZIndex + ", mBetterQuality=" + this.mBetterQuality + ", mMaxMemoryCacheSize=" + this.mMaxMemoryCacheSize + '}';
    }

    public TileOverlayOptions versionInfo(String str) {
        this.mVersionInfo = str;
        return this;
    }

    public TileOverlayOptions zIndex(int i2) {
        this.mZIndex = i2;
        return this;
    }
}
