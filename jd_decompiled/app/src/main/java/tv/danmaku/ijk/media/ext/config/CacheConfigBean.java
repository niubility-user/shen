package tv.danmaku.ijk.media.ext.config;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class CacheConfigBean implements Serializable {
    private boolean forceClosePreload;
    private boolean foreClose;
    private int ioThreadCount;
    private long maxCacheSize;
    private long maxCacheTime;
    private long preloadSize;

    public int getIoThreadCount() {
        return this.ioThreadCount;
    }

    public long getMaxCacheSize() {
        return this.maxCacheSize;
    }

    public long getMaxCacheTime() {
        return this.maxCacheTime;
    }

    public long getPreloadSize() {
        return this.preloadSize;
    }

    public boolean isForceClosePreload() {
        return this.forceClosePreload;
    }

    public boolean isForeClose() {
        return this.foreClose;
    }

    public void setForceClosePreload(boolean z) {
        this.forceClosePreload = z;
    }

    public void setForeClose(boolean z) {
        this.foreClose = z;
    }

    public void setIoThreadCount(int i2) {
        this.ioThreadCount = i2;
    }

    public void setMaxCacheSize(long j2) {
        this.maxCacheSize = j2;
    }

    public void setMaxCacheTime(long j2) {
        this.maxCacheTime = j2;
    }

    public void setPreloadSize(long j2) {
        this.preloadSize = j2;
    }
}
