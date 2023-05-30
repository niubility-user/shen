package com.jd.libs.hybrid.base.engine;

import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import java.util.HashMap;

/* loaded from: classes16.dex */
public interface DownloadEngine {
    void downloadFile(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, String str3, String str4, a.InterfaceC0173a interfaceC0173a);

    void downloadStream(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, StreamRequest.StreamListener streamListener);
}
