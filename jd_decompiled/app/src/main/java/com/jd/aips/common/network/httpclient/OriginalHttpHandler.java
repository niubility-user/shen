package com.jd.aips.common.network.httpclient;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.aips.common.network.httpclient.JDCNHttpCaller;
import java.net.SocketTimeoutException;
import java.util.Map;

/* loaded from: classes12.dex */
public class OriginalHttpHandler implements Runnable, IRetryable {
    private JDCNHttpCaller.NetworkRequest a;
    private INetworkCallback b;

    /* renamed from: c */
    private int f1585c;
    private Handler d;

    /* renamed from: e */
    private volatile boolean f1586e;

    /* renamed from: f */
    private OriginalJDCNHttpCaller f1587f;

    public OriginalHttpHandler(JDCNHttpCaller.NetworkRequest networkRequest, INetworkCallback iNetworkCallback, OriginalJDCNHttpCaller originalJDCNHttpCaller, int i2) {
        this.a = networkRequest;
        this.b = iNetworkCallback;
        this.f1585c = i2;
        this.f1587f = originalJDCNHttpCaller;
        if (networkRequest.getThreadStrategy() == 16) {
            this.d = new Handler(Looper.getMainLooper());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:183:0x0116, code lost:
        if (r0 == 0) goto L210;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0128: MOVE (r1 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:196:0x0126 */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0153 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v8 */
    @Override // com.jd.aips.common.network.httpclient.IRetryable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int execute() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.network.httpclient.OriginalHttpHandler.execute():int");
    }

    @Override // java.lang.Runnable
    public void run() {
        Thread.currentThread().setName("OriginalHttpHandler");
        int i2 = -1;
        String str = null;
        for (int i3 = 0; i3 <= this.a.getRetryCount() && !Thread.interrupted() && !this.f1586e; i3++) {
            try {
                i2 = execute();
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
        if (i2 != 1) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            a(i2, str);
        }
        OriginalJDCNHttpCaller originalJDCNHttpCaller = this.f1587f;
        if (originalJDCNHttpCaller != null) {
            originalJDCNHttpCaller.a(this.f1585c);
        }
    }

    private String b() {
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

    public void a() {
        this.f1586e = true;
        this.b = null;
    }

    private void a(final int i2, final String str) {
        if (this.b == null || Thread.interrupted() || this.f1586e) {
            return;
        }
        if (17 == this.a.getThreadStrategy()) {
            this.b.networkError(this.f1585c, i2, str);
        } else {
            this.d.post(new Runnable() { // from class: com.jd.aips.common.network.httpclient.OriginalHttpHandler.1
                {
                    OriginalHttpHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (OriginalHttpHandler.this.b != null) {
                        OriginalHttpHandler.this.b.networkError(OriginalHttpHandler.this.f1585c, i2, str);
                    }
                }
            });
        }
    }

    private void a(final String str) {
        if (this.b == null || Thread.interrupted() || this.f1586e) {
            return;
        }
        if (17 == this.a.getThreadStrategy()) {
            INetworkCallback iNetworkCallback = this.b;
            if (iNetworkCallback instanceof INetworkWithJSONCallback) {
                INetworkWithJSONCallback iNetworkWithJSONCallback = (INetworkWithJSONCallback) iNetworkCallback;
                iNetworkWithJSONCallback.networkResponse(this.f1585c, iNetworkWithJSONCallback.requestParse(str));
                return;
            }
            iNetworkCallback.networkResponse(this.f1585c, str);
            return;
        }
        INetworkCallback iNetworkCallback2 = this.b;
        if (iNetworkCallback2 instanceof INetworkWithJSONCallback) {
            ((INetworkWithJSONCallback) iNetworkCallback2).requestParse(str);
            this.d.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0043: INVOKE 
                  (wrap: android.os.Handler : 0x003c: IGET (r2v0 'this' com.jd.aips.common.network.httpclient.OriginalHttpHandler A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:18) com.jd.aips.common.network.httpclient.OriginalHttpHandler.d android.os.Handler)
                  (wrap: java.lang.Runnable : 0x0040: CONSTRUCTOR 
                  (r2v0 'this' com.jd.aips.common.network.httpclient.OriginalHttpHandler A[IMMUTABLE_TYPE, THIS])
                  (r3 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jd.aips.common.network.httpclient.OriginalHttpHandler, java.lang.Object):void (m), WRAPPED] call: com.jd.aips.common.network.httpclient.OriginalHttpHandler.2.<init>(com.jd.aips.common.network.httpclient.OriginalHttpHandler, java.lang.Object):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:18) in method: com.jd.aips.common.network.httpclient.OriginalHttpHandler.a(java.lang.String):void, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                com.jd.aips.common.network.httpclient.INetworkCallback r0 = r2.b
                if (r0 == 0) goto L51
                boolean r0 = java.lang.Thread.interrupted()
                if (r0 != 0) goto L51
                boolean r0 = r2.f1586e
                if (r0 != 0) goto L51
                com.jd.aips.common.network.httpclient.JDCNHttpCaller$NetworkRequest r0 = r2.a
                int r0 = r0.getThreadStrategy()
                r1 = 17
                if (r1 != r0) goto L30
                com.jd.aips.common.network.httpclient.INetworkCallback r0 = r2.b
                boolean r1 = r0 instanceof com.jd.aips.common.network.httpclient.INetworkWithJSONCallback
                if (r1 == 0) goto L2a
                com.jd.aips.common.network.httpclient.INetworkWithJSONCallback r0 = (com.jd.aips.common.network.httpclient.INetworkWithJSONCallback) r0
                java.lang.Object r3 = r0.requestParse(r3)
                int r1 = r2.f1585c
                r0.networkResponse(r1, r3)
                goto L51
            L2a:
                int r1 = r2.f1585c
                r0.networkResponse(r1, r3)
                goto L51
            L30:
                com.jd.aips.common.network.httpclient.INetworkCallback r0 = r2.b
                boolean r1 = r0 instanceof com.jd.aips.common.network.httpclient.INetworkWithJSONCallback
                if (r1 == 0) goto L47
                com.jd.aips.common.network.httpclient.INetworkWithJSONCallback r0 = (com.jd.aips.common.network.httpclient.INetworkWithJSONCallback) r0
                java.lang.Object r3 = r0.requestParse(r3)
                android.os.Handler r0 = r2.d
                com.jd.aips.common.network.httpclient.OriginalHttpHandler$2 r1 = new com.jd.aips.common.network.httpclient.OriginalHttpHandler$2
                r1.<init>()
                r0.post(r1)
                goto L51
            L47:
                android.os.Handler r0 = r2.d
                com.jd.aips.common.network.httpclient.OriginalHttpHandler$3 r1 = new com.jd.aips.common.network.httpclient.OriginalHttpHandler$3
                r1.<init>()
                r0.post(r1)
            L51:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.network.httpclient.OriginalHttpHandler.a(java.lang.String):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:248:0x0216, code lost:
            if (r0 != 0) goto L279;
         */
        /* JADX WARN: Code restructure failed: missing block: B:262:0x0232, code lost:
            if (r0 != 0) goto L279;
         */
        /* JADX WARN: Code restructure failed: missing block: B:263:0x0234, code lost:
            r0.disconnect();
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:281:0x022a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:290:0x020e A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
        public com.jd.aips.common.network.httpclient.JDCNHttpResponse a(java.util.concurrent.ThreadPoolExecutor r12) {
            /*
                Method dump skipped, instructions count: 586
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.network.httpclient.OriginalHttpHandler.a(java.util.concurrent.ThreadPoolExecutor):com.jd.aips.common.network.httpclient.JDCNHttpResponse");
        }
    }
