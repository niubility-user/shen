package com.jdjr.risk.jdcn.common.network.httpclient;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller;
import com.jdjr.risk.jdcn.common.utils.JDCNLogUtils;
import java.net.SocketTimeoutException;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes18.dex */
public final class a implements Runnable {
    JDCNHttpCaller.NetworkRequest a;
    INetworkCallback b;

    /* renamed from: c */
    int f7487c;
    volatile boolean d;

    /* renamed from: e */
    private Handler f7488e;

    /* renamed from: f */
    private b f7489f;

    public a(JDCNHttpCaller.NetworkRequest networkRequest, INetworkCallback iNetworkCallback, b bVar, int i2) {
        this.a = networkRequest;
        this.b = iNetworkCallback;
        this.f7487c = i2;
        this.f7489f = bVar;
        if (networkRequest.getThreadStrategy() == 16) {
            this.f7488e = new Handler(Looper.getMainLooper());
        }
    }

    private void a(final int i2, final String str) {
        if (this.b != null && !Thread.interrupted() && !this.d) {
            if (17 == this.a.getThreadStrategy()) {
                this.b.networkError(this.f7487c, i2, str);
            } else {
                this.f7488e.post(new Runnable() { // from class: com.jdjr.risk.jdcn.common.network.httpclient.a.1
                    {
                        a.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        a aVar = a.this;
                        INetworkCallback iNetworkCallback = aVar.b;
                        if (iNetworkCallback != null) {
                            iNetworkCallback.networkError(aVar.f7487c, i2, str);
                        }
                    }
                });
            }
        }
        if (this.d) {
            JDCNLogUtils.d("gggl", "faildCallback cancel by user cancel active");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x017c, code lost:
        if (r0 != 0) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x01a1, code lost:
        if (r0 != 0) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x01a3, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x01a6, code lost:
        return 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int b() {
        /*
            Method dump skipped, instructions count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.jdcn.common.network.httpclient.a.b():int");
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread.currentThread().setName("OriginalHttpHandler");
        int i2 = -1;
        String str = null;
        for (int i3 = 0; i3 <= this.a.getRetryCount() && !Thread.interrupted() && !this.d; i3++) {
            try {
                i2 = b();
            } catch (SocketTimeoutException e2) {
                str = e2.getMessage();
                i2 = -2;
            } catch (Exception e3) {
                str = e3.getMessage();
                i2 = -4;
            }
            if (i2 > 0) {
                break;
            }
        }
        if (this.d) {
            JDCNLogUtils.d("gggl", "cancel retry by user cancel active");
        }
        if (i2 != 1) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            a(i2, str);
        }
        b bVar = this.f7489f;
        if (bVar != null) {
            bVar.a.remove(Integer.valueOf(this.f7487c));
        }
    }

    private String a() {
        if (this.a.getRequestParams() != null) {
            StringBuilder sb = new StringBuilder(this.a.getUrl());
            sb.append("?");
            for (Map.Entry<String, String> entry : this.a.getRequestParams().entrySet()) {
                sb.append(entry.getKey());
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(Uri.encode(entry.getValue()));
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            if (sb.toString().endsWith(ContainerUtils.FIELD_DELIMITER)) {
                return sb.substring(0, sb.length() - 1);
            }
            return sb.toString();
        }
        return this.a.getUrl();
    }

    /* JADX WARN: Code restructure failed: missing block: B:248:0x023a, code lost:
        if (r0 != 0) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x023c, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x0259, code lost:
        if (r0 != 0) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x025c, code lost:
        return r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0251 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpResponse a(java.util.concurrent.ThreadPoolExecutor r13) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.jdcn.common.network.httpclient.a.a(java.util.concurrent.ThreadPoolExecutor):com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpResponse");
    }
}
