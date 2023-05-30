package com.jd.libs.hybrid.adapter;

import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import java.util.HashMap;

/* loaded from: classes16.dex */
public abstract class DownloadAdapter implements IAdapter {
    public static final String NAME = "adapter_download";

    public abstract void downloadFile(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, String str3, String str4, a.InterfaceC0173a interfaceC0173a);

    public abstract void downloadStream(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, StreamRequest.StreamListener streamListener);

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }
}
