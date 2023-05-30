package com.tencent.mapsdk.internal;

import android.net.Uri;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.http.HttpProxy;
import com.tencent.map.tools.net.http.HttpProxyRule;
import com.tencent.map.tools.net.processor.RequestProcessor;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

/* loaded from: classes9.dex */
public class tb implements RequestProcessor {
    private final List<HttpProxyRule> a;
    private final boolean b;

    private tb(List<HttpProxyRule> list, boolean z) {
        this.a = list;
        this.b = z;
    }

    public static tb a(List<HttpProxyRule> list) {
        return a(list, false);
    }

    public static tb a(List<HttpProxyRule> list, boolean z) {
        return new tb(list, z);
    }

    public HttpProxy a(String str, boolean z) {
        List<HttpProxyRule> list = this.a;
        if (list != null && !list.isEmpty()) {
            String str2 = str;
            boolean z2 = false;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                HttpProxyRule httpProxyRule = this.a.get(i2);
                if (httpProxyRule.match(str)) {
                    str2 = httpProxyRule.replaceHost(str);
                    z2 = true;
                }
            }
            if (z2) {
                Uri parse = Uri.parse(str2);
                String host = parse.getHost();
                int port = parse.getPort();
                if (port < 0 || port > 65535) {
                    port = "https".equals(parse.getScheme()) ? 443 : 80;
                }
                return new HttpProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)), str2, z);
            }
        }
        return null;
    }

    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        netRequest.proxy = a(netRequest.url, this.b);
    }
}
