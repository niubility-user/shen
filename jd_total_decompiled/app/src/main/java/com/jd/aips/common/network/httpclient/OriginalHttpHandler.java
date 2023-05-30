package com.jd.aips.common.network.httpclient;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.aips.common.network.httpclient.JDCNHttpCaller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

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

    /* JADX WARN: Code restructure failed: missing block: B:306:0x0116, code lost:
        if (r0 == 0) goto L333;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0128: MOVE (r1 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:319:0x0126 */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0153 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:355:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
    */
    public int execute() throws IOException {
        String str;
        Throwable th;
        ?? r0;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        SecurityException e2;
        int responseCode;
        String b = b();
        BufferedInputStream bufferedInputStream3 = null;
        try {
            try {
                try {
                    try {
                        try {
                            b = (HttpURLConnection) new URL(b).openConnection();
                            try {
                                if (this.a.isPost()) {
                                    b.setRequestMethod("POST");
                                    b.setDoOutput(true);
                                } else {
                                    b.setRequestMethod("GET");
                                }
                                b.setConnectTimeout(this.a.getConnectionTimeout());
                                b.setReadTimeout(this.a.getReadTimeout());
                                b.addRequestProperty("Connection", "Keep-Alive");
                                if (this.a.getHeaders() != null) {
                                    for (Map.Entry<String, String> entry : this.a.getHeaders().entrySet()) {
                                        b.addRequestProperty(entry.getKey(), entry.getValue());
                                    }
                                }
                                if (this.a.isPost()) {
                                    b.setUseCaches(false);
                                }
                                b.setDoInput(true);
                                b.connect();
                                if (this.a.isPost()) {
                                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.getOutputStream());
                                    bufferedOutputStream.write(this.a.getPostContent().getBytes());
                                    bufferedOutputStream.close();
                                }
                                responseCode = b.getResponseCode();
                            } catch (IOException e3) {
                                e = e3;
                                throw e;
                            } catch (SecurityException e4) {
                                e = e4;
                                bufferedInputStream2 = null;
                                e2 = e;
                                e2.printStackTrace();
                                a(-3, e2.getMessage());
                                r0 = b;
                                if (bufferedInputStream2 != null) {
                                }
                            } catch (SocketTimeoutException e5) {
                                e = e5;
                                throw e;
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedInputStream3 != null) {
                                }
                                if (b != 0) {
                                }
                                throw th;
                            }
                        } catch (SecurityException e6) {
                            e = e6;
                            b = 0;
                        } catch (SocketTimeoutException e7) {
                            e = e7;
                        } catch (IOException e8) {
                            e = e8;
                        }
                        if (200 == responseCode) {
                            byte[] bArr = new byte[4096];
                            StringBuilder sb = new StringBuilder();
                            bufferedInputStream2 = new BufferedInputStream(b.getInputStream());
                            while (true) {
                                try {
                                    int read = bufferedInputStream2.read(bArr);
                                    if (read == -1) {
                                        break;
                                    } else if (Thread.interrupted()) {
                                        Thread.currentThread().interrupt();
                                        break;
                                    } else if (this.f1586e) {
                                        break;
                                    } else {
                                        sb.append(new String(bArr, 0, read));
                                    }
                                } catch (IOException e9) {
                                    throw e9;
                                } catch (SecurityException e10) {
                                    e2 = e10;
                                    e2.printStackTrace();
                                    a(-3, e2.getMessage());
                                    r0 = b;
                                    if (bufferedInputStream2 != null) {
                                    }
                                } catch (SocketTimeoutException e11) {
                                    throw e11;
                                }
                            }
                            a(sb.toString());
                            r0 = b;
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (IOException e12) {
                                    e12.printStackTrace();
                                }
                            }
                        } else {
                            b.disconnect();
                            return responseCode;
                        }
                    } catch (MalformedURLException e13) {
                        e = e13;
                        str = null;
                        try {
                            e.printStackTrace();
                            a(-4, e.getMessage());
                            if (bufferedInputStream3 != null) {
                            }
                            if (str != null) {
                            }
                            return 1;
                        } catch (Throwable th3) {
                            BufferedInputStream bufferedInputStream4 = bufferedInputStream3;
                            th = th3;
                            b = str;
                            th = th;
                            bufferedInputStream3 = bufferedInputStream4;
                            if (bufferedInputStream3 != null) {
                            }
                            if (b != 0) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    b = 0;
                }
            } catch (MalformedURLException e14) {
                str = b;
                e = e14;
                bufferedInputStream3 = bufferedInputStream;
                e.printStackTrace();
                a(-4, e.getMessage());
                if (bufferedInputStream3 != null) {
                    try {
                        bufferedInputStream3.close();
                    } catch (IOException e15) {
                        e15.printStackTrace();
                    }
                }
                if (str != null) {
                    r0 = str;
                    r0.disconnect();
                }
                return 1;
            }
        } catch (Throwable th5) {
            th = th5;
            th = th;
            bufferedInputStream3 = bufferedInputStream4;
            if (bufferedInputStream3 != null) {
                try {
                    bufferedInputStream3.close();
                } catch (IOException e16) {
                    e16.printStackTrace();
                }
            }
            if (b != 0) {
                b.disconnect();
            }
            throw th;
        }
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
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 31 more
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

        /* JADX WARN: Code restructure failed: missing block: B:401:0x0216, code lost:
            if (r0 != 0) goto L432;
         */
        /* JADX WARN: Code restructure failed: missing block: B:415:0x0232, code lost:
            if (r0 != 0) goto L432;
         */
        /* JADX WARN: Code restructure failed: missing block: B:416:0x0234, code lost:
            r0.disconnect();
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:434:0x022a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:443:0x020e A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
        */
        public JDCNHttpResponse a(ThreadPoolExecutor threadPoolExecutor) {
            int responseCode;
            ?? b = b();
            long currentTimeMillis = System.currentTimeMillis();
            JDCNHttpResponse jDCNHttpResponse = new JDCNHttpResponse();
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    try {
                        try {
                            try {
                                b = (HttpURLConnection) new URL(b).openConnection();
                                try {
                                    if (this.a.isPost()) {
                                        b.setRequestMethod("POST");
                                        b.setDoOutput(true);
                                    } else {
                                        b.setRequestMethod("GET");
                                    }
                                    b.setConnectTimeout(this.a.getConnectionTimeout());
                                    b.setReadTimeout(this.a.getReadTimeout());
                                    b.addRequestProperty("Connection", "Keep-Alive");
                                    if (this.a.getHeaders() != null) {
                                        for (Map.Entry<String, String> entry : this.a.getHeaders().entrySet()) {
                                            b.addRequestProperty(entry.getKey(), entry.getValue());
                                        }
                                    }
                                    if (this.a.isPost()) {
                                        b.setUseCaches(false);
                                    }
                                    b.setDoInput(true);
                                    b.connect();
                                    String str = "end connection! " + (System.currentTimeMillis() - currentTimeMillis);
                                    long currentTimeMillis2 = System.currentTimeMillis();
                                    final WatchFlag watchFlag = new WatchFlag();
                                    if (this.a.isPost() && this.a.getWriteTimeout() > 0) {
                                        watchFlag.b = false;
                                        watchFlag.a = b;
                                        if (threadPoolExecutor != null) {
                                            try {
                                                threadPoolExecutor.submit(new Runnable() { // from class: com.jd.aips.common.network.httpclient.OriginalHttpHandler.4
                                                    {
                                                        OriginalHttpHandler.this = this;
                                                    }

                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        HttpURLConnection httpURLConnection;
                                                        try {
                                                            synchronized (watchFlag) {
                                                                long currentTimeMillis3 = System.currentTimeMillis();
                                                                watchFlag.wait(OriginalHttpHandler.this.a.getWriteTimeout());
                                                                String str2 = "end watchFlag ! " + (System.currentTimeMillis() - currentTimeMillis3);
                                                            }
                                                            WatchFlag watchFlag2 = watchFlag;
                                                            if (!watchFlag2.b && (httpURLConnection = watchFlag2.a) != null) {
                                                                try {
                                                                    httpURLConnection.disconnect();
                                                                } catch (Exception unused) {
                                                                }
                                                            }
                                                            watchFlag.a = null;
                                                        } catch (InterruptedException unused2) {
                                                            watchFlag.a = null;
                                                        }
                                                    }
                                                });
                                            } catch (Throwable unused) {
                                            }
                                        }
                                    }
                                    if (this.a.isPost()) {
                                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.getOutputStream());
                                        bufferedOutputStream.write(this.a.getPostContent().getBytes());
                                        bufferedOutputStream.close();
                                        String str2 = "end output write! " + (System.currentTimeMillis() - currentTimeMillis2);
                                        currentTimeMillis2 = System.currentTimeMillis();
                                    }
                                    responseCode = b.getResponseCode();
                                    watchFlag.b = true;
                                    if (this.a.isPost()) {
                                        synchronized (watchFlag) {
                                            watchFlag.notifyAll();
                                        }
                                    }
                                    String str3 = "end getResponseCode! " + (System.currentTimeMillis() - currentTimeMillis2);
                                    currentTimeMillis = System.currentTimeMillis();
                                    jDCNHttpResponse.a = responseCode;
                                } catch (IllegalStateException e2) {
                                    e = e2;
                                } catch (SocketTimeoutException e3) {
                                    e = e3;
                                } catch (IOException e4) {
                                    e = e4;
                                } catch (SecurityException e5) {
                                    e = e5;
                                } catch (Exception e6) {
                                    e = e6;
                                }
                            } catch (IOException e7) {
                                e = e7;
                                b = 0;
                            } catch (IllegalStateException e8) {
                                e = e8;
                                b = 0;
                            } catch (SecurityException e9) {
                                e = e9;
                                b = 0;
                            } catch (SocketTimeoutException e10) {
                                e = e10;
                                b = 0;
                            } catch (Exception e11) {
                                e = e11;
                                b = 0;
                            }
                            if (responseCode >= 200 && responseCode < 300) {
                                byte[] bArr = new byte[4096];
                                StringBuilder sb = new StringBuilder();
                                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(b.getInputStream());
                                while (true) {
                                    try {
                                        int read = bufferedInputStream2.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        sb.append(new String(bArr, 0, read));
                                    } catch (IllegalStateException e12) {
                                        e = e12;
                                        bufferedInputStream = bufferedInputStream2;
                                        jDCNHttpResponse.a = 400;
                                        jDCNHttpResponse.b = e.getMessage();
                                        b = b;
                                        bufferedInputStream2 = bufferedInputStream;
                                        b = b;
                                        if (bufferedInputStream2 != null) {
                                        }
                                    } catch (SecurityException e13) {
                                        e = e13;
                                        bufferedInputStream = bufferedInputStream2;
                                        jDCNHttpResponse.a = 400;
                                        jDCNHttpResponse.b = e.getMessage();
                                        b = b;
                                        bufferedInputStream2 = bufferedInputStream;
                                        b = b;
                                        if (bufferedInputStream2 != null) {
                                        }
                                    } catch (SocketTimeoutException e14) {
                                        e = e14;
                                        bufferedInputStream = bufferedInputStream2;
                                        jDCNHttpResponse.a = 400;
                                        jDCNHttpResponse.b = e.getMessage();
                                        String str4 = "SocketTimeoutException " + (System.currentTimeMillis() - currentTimeMillis);
                                        b = b;
                                        bufferedInputStream2 = bufferedInputStream;
                                        b = b;
                                        if (bufferedInputStream2 != null) {
                                        }
                                    } catch (IOException e15) {
                                        e = e15;
                                        bufferedInputStream = bufferedInputStream2;
                                        jDCNHttpResponse.a = 400;
                                        jDCNHttpResponse.b = e.getMessage();
                                        String str5 = "IOException " + (System.currentTimeMillis() - currentTimeMillis);
                                        b = b;
                                        bufferedInputStream2 = bufferedInputStream;
                                        b = b;
                                        if (bufferedInputStream2 != null) {
                                        }
                                    } catch (Exception e16) {
                                        e = e16;
                                        bufferedInputStream = bufferedInputStream2;
                                        jDCNHttpResponse.a = 400;
                                        jDCNHttpResponse.b = e.getMessage();
                                        b = b;
                                        bufferedInputStream2 = bufferedInputStream;
                                        b = b;
                                        if (bufferedInputStream2 != null) {
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        bufferedInputStream = bufferedInputStream2;
                                        if (bufferedInputStream != null) {
                                            try {
                                                bufferedInputStream.close();
                                            } catch (IOException e17) {
                                                e17.printStackTrace();
                                            }
                                        }
                                        if (b != 0) {
                                            try {
                                                b.disconnect();
                                            } catch (Exception unused2) {
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                String str6 = "end read! " + (System.currentTimeMillis() - currentTimeMillis);
                                currentTimeMillis = System.currentTimeMillis();
                                jDCNHttpResponse.f1584c = sb.toString();
                                jDCNHttpResponse.b = "success";
                                b = b;
                                if (bufferedInputStream2 != null) {
                                    try {
                                        bufferedInputStream2.close();
                                    } catch (IOException e18) {
                                        e18.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    b.disconnect();
                                } catch (Exception unused3) {
                                }
                                return jDCNHttpResponse;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            b = 0;
                        }
                    } catch (Exception e19) {
                        e = e19;
                        jDCNHttpResponse.a = 400;
                        jDCNHttpResponse.b = e.getMessage();
                        if (bufferedInputStream != null) {
                        }
                    }
                } catch (Exception e20) {
                    e = e20;
                    b = 0;
                    jDCNHttpResponse.a = 400;
                    jDCNHttpResponse.b = e.getMessage();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e21) {
                            e21.printStackTrace();
                        }
                    }
                }
                return jDCNHttpResponse;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }
