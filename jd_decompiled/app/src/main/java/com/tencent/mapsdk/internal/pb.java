package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class pb extends hb {
    private NetResponse b;

    public InputStream a(InputStream inputStream) {
        return inputStream;
    }

    @Override // com.tencent.mapsdk.internal.hb
    public void a() {
        super.a();
        NetResponse netResponse = this.b;
        if (netResponse != null) {
            ga.a((Closeable) netResponse.getInputStream());
        }
    }

    public void a(NetRequest.NetRequestBuilder netRequestBuilder) {
    }

    public void a(NetResponse netResponse) {
    }

    @Override // com.tencent.mapsdk.internal.hb
    public InputStream f(String str) {
        InputStream inputStream;
        NetRequest.NetRequestBuilder url = NetManager.getInstance().builder().url(str);
        a(url);
        NetResponse doStream = url.doStream();
        this.b = doStream;
        if (doStream != null) {
            inputStream = doStream.getInputStream();
            a(this.b);
        } else {
            a(new NetResponse(url.getNetRequest()));
            inputStream = null;
        }
        return a(inputStream);
    }
}
