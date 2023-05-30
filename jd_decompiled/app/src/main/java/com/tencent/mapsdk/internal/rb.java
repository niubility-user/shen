package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.NetUtil;
import com.tencent.map.tools.net.processor.ResponseProcessor;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes9.dex */
public class rb implements ResponseProcessor {
    @Override // com.tencent.map.tools.net.processor.ResponseProcessor
    public void onResponse(NetResponse netResponse) {
        try {
            try {
                if (netResponse.available()) {
                    netResponse.data = NetUtil.toBytesThrow(netResponse.dataStream);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } finally {
            ga.a((Closeable) netResponse.dataStream);
            netResponse.dataStream = null;
        }
    }
}
