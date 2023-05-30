package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.processor.RequestProcessor;
import com.tencent.map.tools.net.processor.ResponseProcessor;

/* loaded from: classes9.dex */
public class ub implements RequestProcessor, ResponseProcessor {
    private final boolean a;

    public ub(boolean z) {
        this.a = z;
    }

    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        if (this.a) {
            ma.c(la.f16824k, "REQ[" + netRequest.mRequestId + "][" + netRequest.mNetMethod.name() + "]: " + netRequest.toString());
        }
    }

    @Override // com.tencent.map.tools.net.processor.ResponseProcessor
    public void onResponse(NetResponse netResponse) {
        if (this.a) {
            ma.c(la.f16824k, "RESP[" + netResponse.mRequestId + "]: " + netResponse.toHumanString());
        }
    }
}
