package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.NetUtil;
import com.tencent.map.tools.net.http.HttpCanceler;
import com.tencent.map.tools.net.http.HttpConnectionInputStream;
import com.tencent.map.tools.net.http.HttpProxy;
import com.tencent.map.tools.net.processor.Processor;
import com.tencent.map.tools.net.processor.RequestProcessor;
import com.tencent.map.tools.net.processor.ResponseProcessor;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class eb extends db {

    /* renamed from: j  reason: collision with root package name */
    private static final String f16465j = "URLNetImpl";

    /* renamed from: k  reason: collision with root package name */
    private static final boolean f16466k = false;

    /* renamed from: i  reason: collision with root package name */
    private HttpURLConnection f16467i;

    /* loaded from: classes9.dex */
    public class a implements Callback<Boolean> {
        public final /* synthetic */ b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(Boolean bool) {
            this.a.a();
            eb.this.f16467i = null;
        }
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: c  reason: collision with root package name */
        private static final int f16468c = 3;
        private int a;
        private boolean b = true;

        public b(int i2) {
            this.a = i2;
            if (i2 > 3) {
                this.a = 3;
            }
            if (this.a <= 0) {
                this.a = 1;
            }
        }

        public void a() {
            this.b = false;
        }

        public void b() {
            this.a--;
        }

        public boolean c() {
            return this.b && this.a > 0;
        }
    }

    private void a() {
        if (Integer.parseInt(Build.VERSION.SDK) < 8) {
            System.setProperty("http.keepAlive", DYConstants.DY_FALSE);
        }
    }

    private void a(NetResponse netResponse) {
        List<Processor> processors = netResponse.getProcessors();
        Collections.reverse(processors);
        for (Processor processor : processors) {
            if (processor instanceof ResponseProcessor) {
                ((ResponseProcessor) processor).onResponse(netResponse);
            }
        }
    }

    private void c(NetRequest netRequest) {
        for (Processor processor : netRequest.processors) {
            if (processor instanceof RequestProcessor) {
                ((RequestProcessor) processor).onRequest(netRequest);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private NetResponse d(NetRequest netRequest) {
        URL url;
        int i2;
        OutputStream outputStream;
        NetRequest netRequest2 = netRequest;
        try {
            c(netRequest);
        } catch (Exception unused) {
        }
        NetResponse netResponse = new NetResponse(netRequest2);
        String str = netRequest2.url;
        int i3 = netRequest2.retryMethod;
        int i4 = netRequest2.timeout;
        byte[] bArr = netRequest2.postData;
        Map<String, String> map = netRequest2.mapHeaders;
        HttpCanceler httpCanceler = netRequest2.canceler;
        HttpProxy httpProxy = netRequest2.proxy;
        Set<String> set = netRequest2.respHeaders;
        try {
            url = new URL(str);
        } catch (MalformedURLException e2) {
            e = e2;
            url = null;
        }
        try {
            URL proxyURL = HttpProxy.getProxyURL(httpProxy);
            if (proxyURL != null) {
                url = proxyURL;
            }
        } catch (MalformedURLException e3) {
            e = e3;
            netResponse.exception(e);
            if (url != null) {
            }
            return netResponse;
        }
        if (url != null) {
            b bVar = new b(i3);
            while (bVar.c()) {
                int i5 = 0;
                try {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(HttpProxy.getForwardProxy(httpProxy));
                        httpURLConnection.setRequestMethod(netRequest2.mNetMethod.name());
                        httpURLConnection.setUseCaches(false);
                        if (i4 > 0) {
                            try {
                                httpURLConnection.setConnectTimeout(i4);
                                httpURLConnection.setReadTimeout(i4);
                            } catch (SocketTimeoutException e4) {
                                e = e4;
                                i2 = i4;
                                outputStream = null;
                                netResponse.exception(e);
                                netResponse.statusCode = i5;
                                bVar.b();
                                a(netResponse);
                            } catch (IOException e5) {
                                e = e5;
                                i2 = i4;
                                outputStream = null;
                                netResponse.exception(e);
                                netResponse.statusCode = i5;
                                bVar.b();
                                a(netResponse);
                            }
                        }
                        if (!map.isEmpty()) {
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                                    httpURLConnection.addRequestProperty(key, value);
                                }
                            }
                        }
                        if (httpCanceler != null) {
                            httpCanceler.setHttpAccessRequest(this, new a(bVar));
                            this.f16467i = httpURLConnection;
                        }
                        if (bArr == null || bArr.length <= 0) {
                            outputStream = null;
                        } else {
                            httpURLConnection.setDoOutput(true);
                            outputStream = httpURLConnection.getOutputStream();
                            try {
                                try {
                                    NetUtil.writeBytesWithoutClose(bArr, outputStream);
                                } catch (SocketTimeoutException e6) {
                                    e = e6;
                                    i2 = i4;
                                    netResponse.exception(e);
                                    netResponse.statusCode = i5;
                                    bVar.b();
                                    a(netResponse);
                                } catch (IOException e7) {
                                    e = e7;
                                    i2 = i4;
                                    netResponse.exception(e);
                                    netResponse.statusCode = i5;
                                    bVar.b();
                                    a(netResponse);
                                }
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    a(netResponse);
                                } catch (Exception unused2) {
                                }
                                NetUtil.safeClose(outputStream);
                                throw th;
                            }
                        }
                        try {
                            httpURLConnection.connect();
                            int responseCode = httpURLConnection.getResponseCode();
                            try {
                                netResponse.statusCode = responseCode;
                                netResponse.charset = db.a(httpURLConnection.getContentType());
                                if (!set.isEmpty()) {
                                    for (String str2 : set) {
                                        i2 = i4;
                                        try {
                                            netResponse.respHeads.put(str2, httpURLConnection.getHeaderField(str2));
                                            i4 = i2;
                                        } catch (SocketTimeoutException e8) {
                                            e = e8;
                                            i5 = responseCode;
                                            netResponse.exception(e);
                                            netResponse.statusCode = i5;
                                            bVar.b();
                                            a(netResponse);
                                        } catch (IOException e9) {
                                            e = e9;
                                            i5 = responseCode;
                                            netResponse.exception(e);
                                            netResponse.statusCode = i5;
                                            bVar.b();
                                            a(netResponse);
                                        }
                                    }
                                }
                                i2 = i4;
                                if (responseCode == 200) {
                                    netResponse.errorCode = 0;
                                    netResponse.contentEncoding = httpURLConnection.getContentEncoding();
                                    netResponse.contentLength = httpURLConnection.getContentLength();
                                    netResponse.dataStream = new HttpConnectionInputStream(httpURLConnection);
                                    bVar.a();
                                } else {
                                    netResponse.errorData = NetUtil.toBytesThrow(httpURLConnection.getErrorStream());
                                    bVar.b();
                                }
                            } catch (SocketTimeoutException e10) {
                                e = e10;
                                i2 = i4;
                            } catch (IOException e11) {
                                e = e11;
                                i2 = i4;
                            }
                        } catch (SocketTimeoutException e12) {
                            e = e12;
                            i2 = i4;
                            i5 = 0;
                        } catch (IOException e13) {
                            e = e13;
                            i2 = i4;
                            i5 = 0;
                        }
                    } catch (SocketTimeoutException e14) {
                        e = e14;
                        i2 = i4;
                        i5 = 0;
                    } catch (IOException e15) {
                        e = e15;
                        i2 = i4;
                        i5 = 0;
                    }
                    try {
                        a(netResponse);
                    } catch (Exception unused3) {
                        NetUtil.safeClose(outputStream);
                        netRequest2 = netRequest;
                        i4 = i2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                }
            }
        }
        return netResponse;
    }

    @Override // com.tencent.mapsdk.internal.db
    public NetResponse a(NetRequest netRequest) {
        netRequest.setNetMethod(NetMethod.GET);
        return doRequest(netRequest);
    }

    @Override // com.tencent.mapsdk.internal.db
    public void a(Context context, Bundle bundle) {
        a();
    }

    @Override // com.tencent.mapsdk.internal.db
    public NetResponse b(NetRequest netRequest) {
        netRequest.setNetMethod(NetMethod.POST);
        return doRequest(netRequest);
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public boolean cancel() {
        HttpURLConnection httpURLConnection = this.f16467i;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            return true;
        }
        return false;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public NetResponse doRequest(NetRequest netRequest) {
        netRequest.addProcessor(new rb());
        return d(netRequest);
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public NetResponse openStream(NetRequest netRequest) {
        netRequest.addProcessor(new xb());
        return d(netRequest);
    }
}
