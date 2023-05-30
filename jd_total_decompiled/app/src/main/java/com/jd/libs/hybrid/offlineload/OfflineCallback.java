package com.jd.libs.hybrid.offlineload;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;

@Keep
/* loaded from: classes16.dex */
public interface OfflineCallback {
    void beforeReload();

    void onFetchPreDownloadFile(int i2, long j2, long j3, Object obj);

    void onOfflineFileHit(String str, String str2, boolean z, LocalFileType localFileType);

    void onOfflinePageStarted(String str);
}
