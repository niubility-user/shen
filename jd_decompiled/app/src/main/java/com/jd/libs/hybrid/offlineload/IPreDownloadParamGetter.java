package com.jd.libs.hybrid.offlineload;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;

@Keep
/* loaded from: classes16.dex */
public interface IPreDownloadParamGetter {

    @Keep
    /* loaded from: classes16.dex */
    public static class PreDownloadParamGetter implements IPreDownloadParamGetter {
        @Override // com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter
        public String getDownloadUrl(OfflineFiles offlineFiles, String str) {
            return str;
        }
    }

    String getDownloadUrl(OfflineFiles offlineFiles, String str);
}
