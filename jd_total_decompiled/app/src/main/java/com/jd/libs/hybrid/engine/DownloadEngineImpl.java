package com.jd.libs.hybrid.engine;

import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.DownloadAdapter;
import com.jd.libs.hybrid.base.engine.DownloadEngine;
import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class DownloadEngineImpl implements DownloadEngine {
    @Override // com.jd.libs.hybrid.base.engine.DownloadEngine
    public void downloadFile(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, String str3, String str4, a.InterfaceC0173a interfaceC0173a) {
        DownloadAdapter downloadAdapter = (DownloadAdapter) HybridSDK.getAdapter(DownloadAdapter.NAME);
        if (downloadAdapter != null) {
            downloadAdapter.downloadFile(str, str2, hashMap, z, z2, str3, str4, interfaceC0173a);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.DownloadEngine
    public void downloadStream(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, StreamRequest.StreamListener streamListener) {
        DownloadAdapter downloadAdapter = (DownloadAdapter) HybridSDK.getAdapter(DownloadAdapter.NAME);
        if (downloadAdapter != null) {
            downloadAdapter.downloadStream(str, str2, hashMap, z, z2, streamListener);
        }
    }
}
