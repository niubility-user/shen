package com.jd.framework.advertise;

import com.jd.framework.advertise.adapter.OkHttpAdapter;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.request.JDRequest;
import com.jd.framework.retrofit.interceptor.LoggingInterceptor;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/* loaded from: classes13.dex */
public class OkHttpNetworkFetcher {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    public static final String TAG = "OkHttpNetworkFetcher";
    private OkHttpAdapter mAdapter;
    private OkHttpClient mOkHttpClient;

    public OkHttpNetworkFetcher() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mOkHttpClient = builder.connectTimeout(10000L, timeUnit).readTimeout(15000L, timeUnit).retryOnConnectionFailure(false).addInterceptor(LoggingInterceptor.getLoggingInterceptor()).build();
        this.mAdapter = new OkHttpAdapter();
    }

    public void cancelAll() {
        this.mOkHttpClient.dispatcher().cancelAll();
    }

    public void cancelByTag(String str) {
        if (str != null) {
            Dispatcher dispatcher = this.mOkHttpClient.dispatcher();
            Iterator<Call> it = dispatcher.queuedCalls().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Call next = it.next();
                if (next.request().tag().equals(str)) {
                    next.cancel();
                    break;
                }
            }
            for (Call call : dispatcher.runningCalls()) {
                if (call.request().tag().equals(str)) {
                    call.cancel();
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    public void fetch(JDRequest jDRequest) {
        try {
            JDResponseListener responseListener = jDRequest.getResponseListener();
            if (responseListener != null) {
                responseListener.onStart();
            }
            performRequest(jDRequest, false);
        } catch (Throwable unused) {
        }
    }

    public void performRequest(final JDRequest jDRequest, final boolean z) {
        jDRequest.getResponseListener();
        this.mOkHttpClient.newCall(this.mAdapter.toRequest2((JDRequest<?>) jDRequest)).enqueue(new Callback
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE 
              (wrap: okhttp3.Call : 0x000c: INVOKE 
              (wrap: okhttp3.OkHttpClient : 0x000a: IGET (r3v0 'this' com.jd.framework.advertise.OkHttpNetworkFetcher A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:3) com.jd.framework.advertise.OkHttpNetworkFetcher.mOkHttpClient okhttp3.OkHttpClient)
              (wrap: okhttp3.Request : 0x0006: INVOKE 
              (wrap: com.jd.framework.advertise.adapter.OkHttpAdapter : 0x0004: IGET (r3v0 'this' com.jd.framework.advertise.OkHttpNetworkFetcher A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:2) com.jd.framework.advertise.OkHttpNetworkFetcher.mAdapter com.jd.framework.advertise.adapter.OkHttpAdapter)
              (wrap: com.jd.framework.network.request.JDRequest<?> : ?: CAST (com.jd.framework.network.request.JDRequest<?>) (r4v0 'jDRequest' com.jd.framework.network.request.JDRequest))
             type: VIRTUAL call: com.jd.framework.advertise.adapter.OkHttpAdapter.toRequest(com.jd.framework.network.request.JDRequest):okhttp3.Request A[MD:(com.jd.framework.network.request.JDRequest<?>):okhttp3.Request (m), WRAPPED] (LINE:2))
             type: VIRTUAL call: okhttp3.OkHttpClient.newCall(okhttp3.Request):okhttp3.Call A[MD:(okhttp3.Request):okhttp3.Call (m), WRAPPED] (LINE:3))
              (wrap: okhttp3.Callback : 0x0012: CONSTRUCTOR 
              (r3v0 'this' com.jd.framework.advertise.OkHttpNetworkFetcher A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.jd.framework.network.JDResponseListener A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v0 'z' boolean A[DONT_INLINE])
              (r4v0 'jDRequest' com.jd.framework.network.request.JDRequest A[DONT_INLINE])
             A[MD:(com.jd.framework.advertise.OkHttpNetworkFetcher, com.jd.framework.network.JDResponseListener, boolean, com.jd.framework.network.request.JDRequest):void (m), WRAPPED] call: com.jd.framework.advertise.OkHttpNetworkFetcher.1.<init>(com.jd.framework.advertise.OkHttpNetworkFetcher, com.jd.framework.network.JDResponseListener, boolean, com.jd.framework.network.request.JDRequest):void type: CONSTRUCTOR)
             type: INTERFACE call: okhttp3.Call.enqueue(okhttp3.Callback):void A[MD:(okhttp3.Callback):void (m)] (LINE:3) in method: com.jd.framework.advertise.OkHttpNetworkFetcher.performRequest(com.jd.framework.network.request.JDRequest, boolean):void, file: classes13.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            com.jd.framework.network.JDResponseListener r0 = r4.getResponseListener()
            com.jd.framework.advertise.adapter.OkHttpAdapter r1 = r3.mAdapter
            okhttp3.Request r1 = r1.toRequest2(r4)
            okhttp3.OkHttpClient r2 = r3.mOkHttpClient
            okhttp3.Call r1 = r2.newCall(r1)
            com.jd.framework.advertise.OkHttpNetworkFetcher$1 r2 = new com.jd.framework.advertise.OkHttpNetworkFetcher$1
            r2.<init>()
            r1.enqueue(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.framework.advertise.OkHttpNetworkFetcher.performRequest(com.jd.framework.network.request.JDRequest, boolean):void");
    }
}
