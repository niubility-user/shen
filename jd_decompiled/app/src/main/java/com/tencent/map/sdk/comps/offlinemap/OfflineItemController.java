package com.tencent.map.sdk.comps.offlinemap;

/* loaded from: classes9.dex */
public interface OfflineItemController {
    boolean checkInvalidate();

    boolean close();

    boolean open();

    boolean removeCache();

    void startDownload();

    void stopDownload();
}
