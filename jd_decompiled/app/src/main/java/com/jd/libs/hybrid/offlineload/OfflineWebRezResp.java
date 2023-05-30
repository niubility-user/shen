package com.jd.libs.hybrid.offlineload;

import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import java.io.InputStream;

@Keep
/* loaded from: classes16.dex */
public class OfflineWebRezResp extends WebResourceResponse {
    private boolean isMainRez;
    private String localFilePath;

    public OfflineWebRezResp(String str, String str2, InputStream inputStream, String str3, boolean z) {
        super(str, str2, inputStream);
        this.isMainRez = false;
        this.localFilePath = null;
        this.localFilePath = str3;
        this.isMainRez = z;
    }

    public String getLocalFilePath() {
        return this.localFilePath;
    }

    public boolean isMainRez() {
        return this.isMainRez;
    }
}
