package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.processor.RequestProcessor;

/* loaded from: classes9.dex */
public class sb implements RequestProcessor {
    private final boolean a;

    private sb(boolean z) {
        this.a = z;
    }

    public static sb a(boolean z) {
        return new sb(z);
    }

    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        if (this.a) {
            String str = netRequest.url;
            if (str.startsWith("http://")) {
                str = str.replaceFirst("http://", "https://");
            }
            netRequest.url = str;
        }
    }
}
