package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.exception.FileUploadResetException;
import com.tencent.map.tools.net.processor.RequestProcessor;

/* loaded from: classes9.dex */
public class vb extends rb implements RequestProcessor {
    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        netRequest.setRespHeaders("User-ReturnCode");
    }

    @Override // com.tencent.mapsdk.internal.rb, com.tencent.map.tools.net.processor.ResponseProcessor
    public void onResponse(NetResponse netResponse) {
        String headerField = netResponse.getHeaderField("User-ReturnCode");
        int parseInt = Integer.parseInt(headerField);
        if (parseInt != 0) {
            if (parseInt == -2) {
                netResponse.exception(new FileUploadResetException());
            }
            netResponse.exception(new Exception("FileUploader user error:" + headerField));
        }
    }
}
