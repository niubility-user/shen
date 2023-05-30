package com.jdjr.risk.jdcn.common.network.httpclient;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller;
import com.jdjr.risk.jdcn.common.utils.JDCNLogUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

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

    /* JADX WARN: Code restructure failed: missing block: B:351:0x017c, code lost:
        if (r0 != 0) goto L372;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x01a1, code lost:
        if (r0 != 0) goto L372;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x01a3, code lost:
        r0.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x01a6, code lost:
        return 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
    */
    private int b() {
        int responseCode;
        ?? a = a();
        JDCNLogUtils.d("gggl", a);
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                try {
                    try {
                        try {
                            a = (HttpURLConnection) new URL(a).openConnection();
                            try {
                                if (this.a.isPost()) {
                                    a.setRequestMethod("POST");
                                    a.setDoOutput(true);
                                } else {
                                    a.setRequestMethod("GET");
                                }
                                a.setConnectTimeout(this.a.getConnectionTimeout());
                                a.setReadTimeout(this.a.getReadTimeout());
                                a.addRequestProperty("Connection", "Keep-Alive");
                                if (this.a.getHeaders() != null) {
                                    for (Map.Entry<String, String> entry : this.a.getHeaders().entrySet()) {
                                        a.addRequestProperty(entry.getKey(), entry.getValue());
                                    }
                                }
                                if (this.a.isPost()) {
                                    a.setUseCaches(false);
                                }
                                a.setDoInput(true);
                                a.connect();
                                if (this.a.isPost()) {
                                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(a.getOutputStream());
                                    bufferedOutputStream.write(this.a.getPostContent().getBytes());
                                    bufferedOutputStream.close();
                                }
                                responseCode = a.getResponseCode();
                            } catch (SocketTimeoutException e2) {
                                throw e2;
                            } catch (IOException e3) {
                                throw e3;
                            } catch (SecurityException e4) {
                                e = e4;
                            }
                        } catch (IOException e5) {
                            throw e5;
                        } catch (SecurityException e6) {
                            e = e6;
                            a = 0;
                        } catch (SocketTimeoutException e7) {
                            throw e7;
                        }
                        if (200 != responseCode) {
                            if (a != 0) {
                                a.disconnect();
                            }
                            return responseCode;
                        }
                        byte[] bArr = new byte[4096];
                        StringBuilder sb = new StringBuilder();
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(a.getInputStream());
                        while (true) {
                            try {
                                int read = bufferedInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                } else if (Thread.interrupted()) {
                                    Thread.currentThread().interrupt();
                                    break;
                                } else if (this.d) {
                                    JDCNLogUtils.d("gggl", "break  by user cancel active");
                                    break;
                                } else {
                                    sb.append(new String(bArr, 0, read));
                                }
                            } catch (SocketTimeoutException e8) {
                                throw e8;
                            } catch (IOException e9) {
                                throw e9;
                            } catch (SecurityException e10) {
                                e = e10;
                                bufferedInputStream = bufferedInputStream2;
                                e.printStackTrace();
                                a(-3, e.getMessage());
                                bufferedInputStream2 = bufferedInputStream;
                                a = a;
                                if (bufferedInputStream2 != null) {
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedInputStream = bufferedInputStream2;
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e11) {
                                        e11.printStackTrace();
                                    }
                                }
                                if (a != 0) {
                                    a.disconnect();
                                }
                                throw th;
                            }
                        }
                        final String sb2 = sb.toString();
                        if (this.b != null && !Thread.interrupted() && !this.d) {
                            if (17 == this.a.getThreadStrategy()) {
                                INetworkCallback iNetworkCallback = this.b;
                                if (iNetworkCallback instanceof INetworkWithJSONCallback) {
                                    INetworkWithJSONCallback iNetworkWithJSONCallback = (INetworkWithJSONCallback) iNetworkCallback;
                                    iNetworkWithJSONCallback.networkResponse(this.f7487c, iNetworkWithJSONCallback.requestParse(sb2));
                                } else {
                                    iNetworkCallback.networkResponse(this.f7487c, sb2);
                                }
                            } else {
                                INetworkCallback iNetworkCallback2 = this.b;
                                if (iNetworkCallback2 instanceof INetworkWithJSONCallback) {
                                    ((INetworkWithJSONCallback) iNetworkCallback2).requestParse(sb2);
                                    this.f7488e.post(new Runnable
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0133: INVOKE 
                                          (wrap: android.os.Handler : 0x012c: IGET (r9v0 'this' com.jdjr.risk.jdcn.common.network.httpclient.a A[IMMUTABLE_TYPE, THIS]) A[Catch: all -> 0x014b, SecurityException -> 0x014f, IOException -> 0x0152, SocketTimeoutException -> 0x0155, WRAPPED] (LINE:44) com.jdjr.risk.jdcn.common.network.httpclient.a.e android.os.Handler)
                                          (wrap: java.lang.Runnable : 0x0130: CONSTRUCTOR 
                                          (r9v0 'this' com.jdjr.risk.jdcn.common.network.httpclient.a A[IMMUTABLE_TYPE, THIS])
                                          (r2 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                         A[Catch: all -> 0x014b, SecurityException -> 0x014f, IOException -> 0x0152, SocketTimeoutException -> 0x0155, MD:(com.jdjr.risk.jdcn.common.network.httpclient.a, java.lang.Object):void (m), WRAPPED] call: com.jdjr.risk.jdcn.common.network.httpclient.a.2.<init>(com.jdjr.risk.jdcn.common.network.httpclient.a, java.lang.Object):void type: CONSTRUCTOR)
                                         type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: all -> 0x014b, SecurityException -> 0x014f, IOException -> 0x0152, SocketTimeoutException -> 0x0155, MD:(java.lang.Runnable):boolean (c)] (LINE:44) in method: com.jdjr.risk.jdcn.common.network.httpclient.a.b():int, file: classes18.dex
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                                        	... 61 more
                                        */
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

                                /* JADX WARN: Code restructure failed: missing block: B:402:0x023a, code lost:
                                    if (r0 != 0) goto L441;
                                 */
                                /* JADX WARN: Code restructure failed: missing block: B:403:0x023c, code lost:
                                    r0.disconnect();
                                 */
                                /* JADX WARN: Code restructure failed: missing block: B:417:0x0259, code lost:
                                    if (r0 != 0) goto L441;
                                 */
                                /* JADX WARN: Code restructure failed: missing block: B:419:0x025c, code lost:
                                    return r3;
                                 */
                                /* JADX WARN: Multi-variable type inference failed */
                                /* JADX WARN: Removed duplicated region for block: B:437:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                                /* JADX WARN: Removed duplicated region for block: B:445:0x0251 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
                                public final JDCNHttpResponse a(ThreadPoolExecutor threadPoolExecutor) {
                                    int responseCode;
                                    ?? a = a();
                                    JDCNLogUtils.d("gggl", a);
                                    long currentTimeMillis = System.currentTimeMillis();
                                    JDCNHttpResponse jDCNHttpResponse = new JDCNHttpResponse();
                                    BufferedInputStream bufferedInputStream = null;
                                    try {
                                        try {
                                            try {
                                                try {
                                                    try {
                                                        a = (HttpURLConnection) new URL(a).openConnection();
                                                        try {
                                                            if (this.a.isPost()) {
                                                                a.setRequestMethod("POST");
                                                                a.setDoOutput(true);
                                                            } else {
                                                                a.setRequestMethod("GET");
                                                            }
                                                            a.setConnectTimeout(this.a.getConnectionTimeout());
                                                            a.setReadTimeout(this.a.getReadTimeout());
                                                            a.addRequestProperty("Connection", "Keep-Alive");
                                                            if (this.a.getHeaders() != null) {
                                                                for (Map.Entry<String, String> entry : this.a.getHeaders().entrySet()) {
                                                                    a.addRequestProperty(entry.getKey(), entry.getValue());
                                                                }
                                                            }
                                                            if (this.a.isPost()) {
                                                                a.setUseCaches(false);
                                                            }
                                                            a.setDoInput(true);
                                                            JDCNLogUtils.d("gggl", "start connection! ");
                                                            a.connect();
                                                            JDCNLogUtils.d("gggl", "end connection! " + (System.currentTimeMillis() - currentTimeMillis));
                                                            long currentTimeMillis2 = System.currentTimeMillis();
                                                            final c cVar = new c();
                                                            if (this.a.isPost() && this.a.getWriteTimeout() > 0) {
                                                                cVar.b = false;
                                                                cVar.a = a;
                                                                if (threadPoolExecutor != null) {
                                                                    try {
                                                                        threadPoolExecutor.submit(new Runnable() { // from class: com.jdjr.risk.jdcn.common.network.httpclient.a.4
                                                                            {
                                                                                a.this = this;
                                                                            }

                                                                            @Override // java.lang.Runnable
                                                                            public final void run() {
                                                                                HttpURLConnection httpURLConnection;
                                                                                try {
                                                                                    synchronized (cVar) {
                                                                                        JDCNLogUtils.d("gggl", "start watchFlag ! ");
                                                                                        long currentTimeMillis3 = System.currentTimeMillis();
                                                                                        cVar.wait(a.this.a.getWriteTimeout());
                                                                                        JDCNLogUtils.d("gggl", "end watchFlag ! " + (System.currentTimeMillis() - currentTimeMillis3));
                                                                                    }
                                                                                    c cVar2 = cVar;
                                                                                    if (!cVar2.b && (httpURLConnection = cVar2.a) != null) {
                                                                                        try {
                                                                                            httpURLConnection.disconnect();
                                                                                        } catch (Exception unused) {
                                                                                        }
                                                                                    }
                                                                                    cVar.a = null;
                                                                                } catch (InterruptedException unused2) {
                                                                                    cVar.a = null;
                                                                                }
                                                                            }
                                                                        });
                                                                    } catch (Throwable unused) {
                                                                    }
                                                                }
                                                            }
                                                            if (this.a.isPost()) {
                                                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(a.getOutputStream());
                                                                bufferedOutputStream.write(this.a.getPostContent().getBytes());
                                                                bufferedOutputStream.close();
                                                                JDCNLogUtils.d("gggl", "end output write! " + (System.currentTimeMillis() - currentTimeMillis2));
                                                                currentTimeMillis2 = System.currentTimeMillis();
                                                            }
                                                            JDCNLogUtils.d("gggl", "start getResponseCode() ");
                                                            responseCode = a.getResponseCode();
                                                            cVar.b = true;
                                                            if (this.a.isPost()) {
                                                                synchronized (cVar) {
                                                                    cVar.notifyAll();
                                                                }
                                                            }
                                                            JDCNLogUtils.d("gggl", "end getResponseCode! " + (System.currentTimeMillis() - currentTimeMillis2));
                                                            currentTimeMillis = System.currentTimeMillis();
                                                            jDCNHttpResponse.code = responseCode;
                                                        } catch (IllegalStateException e2) {
                                                            e = e2;
                                                        } catch (SecurityException e3) {
                                                            e = e3;
                                                        } catch (SocketTimeoutException e4) {
                                                            e = e4;
                                                        } catch (IOException e5) {
                                                            e = e5;
                                                        } catch (Exception e6) {
                                                            e = e6;
                                                        }
                                                    } catch (IOException e7) {
                                                        e = e7;
                                                        a = 0;
                                                    } catch (IllegalStateException e8) {
                                                        e = e8;
                                                        a = 0;
                                                    } catch (SecurityException e9) {
                                                        e = e9;
                                                        a = 0;
                                                    } catch (SocketTimeoutException e10) {
                                                        e = e10;
                                                        a = 0;
                                                    } catch (Exception e11) {
                                                        e = e11;
                                                        a = 0;
                                                    }
                                                    if (responseCode < 200 || responseCode >= 300) {
                                                        if (a != 0) {
                                                            try {
                                                                a.disconnect();
                                                            } catch (Exception unused2) {
                                                            }
                                                        }
                                                        return jDCNHttpResponse;
                                                    }
                                                    byte[] bArr = new byte[4096];
                                                    StringBuilder sb = new StringBuilder();
                                                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(a.getInputStream());
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
                                                            jDCNHttpResponse.code = 400;
                                                            jDCNHttpResponse.message = e.getMessage();
                                                            a = a;
                                                            bufferedInputStream2 = bufferedInputStream;
                                                            a = a;
                                                            if (bufferedInputStream2 != null) {
                                                            }
                                                        } catch (SocketTimeoutException e13) {
                                                            e = e13;
                                                            bufferedInputStream = bufferedInputStream2;
                                                            jDCNHttpResponse.code = 400;
                                                            jDCNHttpResponse.message = e.getMessage();
                                                            JDCNLogUtils.d("gggl", "SocketTimeoutException " + (System.currentTimeMillis() - currentTimeMillis));
                                                            a = a;
                                                            bufferedInputStream2 = bufferedInputStream;
                                                            a = a;
                                                            if (bufferedInputStream2 != null) {
                                                            }
                                                        } catch (IOException e14) {
                                                            e = e14;
                                                            bufferedInputStream = bufferedInputStream2;
                                                            jDCNHttpResponse.code = 400;
                                                            jDCNHttpResponse.message = e.getMessage();
                                                            JDCNLogUtils.d("gggl", "IOException " + (System.currentTimeMillis() - currentTimeMillis));
                                                            a = a;
                                                            bufferedInputStream2 = bufferedInputStream;
                                                            a = a;
                                                            if (bufferedInputStream2 != null) {
                                                            }
                                                        } catch (SecurityException e15) {
                                                            e = e15;
                                                            bufferedInputStream = bufferedInputStream2;
                                                            jDCNHttpResponse.code = 400;
                                                            jDCNHttpResponse.message = e.getMessage();
                                                            a = a;
                                                            bufferedInputStream2 = bufferedInputStream;
                                                            a = a;
                                                            if (bufferedInputStream2 != null) {
                                                            }
                                                        } catch (Exception e16) {
                                                            e = e16;
                                                            bufferedInputStream = bufferedInputStream2;
                                                            jDCNHttpResponse.code = 400;
                                                            jDCNHttpResponse.message = e.getMessage();
                                                            a = a;
                                                            bufferedInputStream2 = bufferedInputStream;
                                                            a = a;
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
                                                            if (a != 0) {
                                                                try {
                                                                    a.disconnect();
                                                                } catch (Exception unused3) {
                                                                }
                                                            }
                                                            throw th;
                                                        }
                                                    }
                                                    JDCNLogUtils.d("gggl", "end read! " + (System.currentTimeMillis() - currentTimeMillis));
                                                    currentTimeMillis = System.currentTimeMillis();
                                                    jDCNHttpResponse.body = sb.toString();
                                                    jDCNHttpResponse.message = "success";
                                                    a = a;
                                                    if (bufferedInputStream2 != null) {
                                                        try {
                                                            bufferedInputStream2.close();
                                                        } catch (IOException e18) {
                                                            e18.printStackTrace();
                                                        }
                                                    }
                                                } catch (Exception e19) {
                                                    e = e19;
                                                    a = 0;
                                                    jDCNHttpResponse.code = 400;
                                                    jDCNHttpResponse.message = e.getMessage();
                                                    if (bufferedInputStream != null) {
                                                    }
                                                }
                                            } catch (Exception e20) {
                                                e = e20;
                                                jDCNHttpResponse.code = 400;
                                                jDCNHttpResponse.message = e.getMessage();
                                                if (bufferedInputStream != null) {
                                                    try {
                                                        bufferedInputStream.close();
                                                    } catch (IOException e21) {
                                                        e21.printStackTrace();
                                                    }
                                                }
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            a = 0;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                }
                            }
