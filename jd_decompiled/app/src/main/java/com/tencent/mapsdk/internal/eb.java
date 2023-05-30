package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.processor.Processor;
import com.tencent.map.tools.net.processor.RequestProcessor;
import com.tencent.map.tools.net.processor.ResponseProcessor;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tencent.map.tools.net.NetResponse d(com.tencent.map.tools.net.NetRequest r18) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.eb.d(com.tencent.map.tools.net.NetRequest):com.tencent.map.tools.net.NetResponse");
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
