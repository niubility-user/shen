package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<HttpUrlConnectionNetworkFetchState> {
    private static final String FETCH_TIME = "fetch_time";
    public static final int HTTP_DEFAULT_TIMEOUT = 30000;
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private static final String IMAGE_SIZE = "image_size";
    private static final int MAX_REDIRECTS = 5;
    private static final int NUM_NETWORK_THREADS = 3;
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final ExecutorService mExecutorService;
    private int mHttpConnectionTimeout;
    private final MonotonicClock mMonotonicClock;
    @Nullable
    private String mUserAgent;

    /* loaded from: classes.dex */
    public static class HttpUrlConnectionNetworkFetchState extends FetchState {
        private long fetchCompleteTime;
        private long responseTime;
        private long submitTime;

        public HttpUrlConnectionNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public HttpUrlConnectionNetworkFetcher() {
        this((String) null, RealtimeSinceBootClock.get());
    }

    public HttpUrlConnectionNetworkFetcher(int i2) {
        this((String) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i2;
    }

    public HttpUrlConnectionNetworkFetcher(String str, int i2) {
        this(str, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i2;
    }

    @VisibleForTesting
    HttpUrlConnectionNetworkFetcher(@Nullable String str, MonotonicClock monotonicClock) {
        this.mExecutorService = Executors.newFixedThreadPool(3);
        this.mMonotonicClock = monotonicClock;
        this.mUserAgent = str;
    }

    private HttpURLConnection downloadFrom(Uri uri, int i2) {
        HttpURLConnection openConnectionTo = openConnectionTo(uri);
        String str = this.mUserAgent;
        if (str != null) {
            openConnectionTo.setRequestProperty("User-Agent", str);
        }
        openConnectionTo.setConnectTimeout(this.mHttpConnectionTimeout);
        int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (!isHttpRedirect(responseCode)) {
            openConnectionTo.disconnect();
            throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), Integer.valueOf(responseCode)));
        }
        String headerField = openConnectionTo.getHeaderField(HttpHeaders.LOCATION);
        openConnectionTo.disconnect();
        Uri parse = headerField == null ? null : Uri.parse(headerField);
        String scheme = uri.getScheme();
        if (i2 <= 0 || parse == null || parse.getScheme().equals(scheme)) {
            throw new IOException(i2 == 0 ? error("URL %s follows too many redirects", uri.toString()) : error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode)));
        }
        return downloadFrom(parse, i2 - 1);
    }

    private static String error(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    private static boolean isHttpRedirect(int i2) {
        if (i2 == 307 || i2 == 308) {
            return true;
        }
        switch (i2) {
            case 300:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    private static boolean isHttpSuccess(int i2) {
        return i2 >= 200 && i2 < 300;
    }

    @VisibleForTesting
    static HttpURLConnection openConnectionTo(Uri uri) {
        return (HttpURLConnection) UriUtil.uriToUrl(uri).openConnection();
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return createFetchState((Consumer<EncodedImage>) consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public HttpUrlConnectionNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new HttpUrlConnectionNetworkFetchState(consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(final HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, final NetworkFetcher.Callback callback) {
        httpUrlConnectionNetworkFetchState.submitTime = this.mMonotonicClock.now();
        this.mExecutorService.submit(new Runnable() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1
            @Override // java.lang.Runnable
            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(httpUrlConnectionNetworkFetchState, callback);
            }
        });
        httpUrlConnectionNetworkFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: INVOKE 
              (wrap: com.facebook.imagepipeline.producers.ProducerContext : 0x0014: INVOKE 
              (r3v0 'httpUrlConnectionNetworkFetchState' com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState)
             type: VIRTUAL call: com.facebook.imagepipeline.producers.FetchState.getContext():com.facebook.imagepipeline.producers.ProducerContext A[MD:():com.facebook.imagepipeline.producers.ProducerContext (m), WRAPPED])
              (wrap: com.facebook.imagepipeline.producers.BaseProducerContextCallbacks : 0x001a: CONSTRUCTOR 
              (r2v0 'this' com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.concurrent.Future A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'callback' com.facebook.imagepipeline.producers.NetworkFetcher$Callback A[DONT_INLINE])
             A[MD:(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher, java.util.concurrent.Future, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void (m), WRAPPED] call: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.2.<init>(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher, java.util.concurrent.Future, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void type: CONSTRUCTOR)
             type: INTERFACE call: com.facebook.imagepipeline.producers.ProducerContext.addCallbacks(com.facebook.imagepipeline.producers.ProducerContextCallbacks):void A[MD:(com.facebook.imagepipeline.producers.ProducerContextCallbacks):void (m)] in method: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.fetch(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void, file: classes.dex
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
            	... 15 more
            */
        /*
            this = this;
            com.facebook.common.time.MonotonicClock r0 = r2.mMonotonicClock
            long r0 = r0.now()
            com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.HttpUrlConnectionNetworkFetchState.access$002(r3, r0)
            java.util.concurrent.ExecutorService r0 = r2.mExecutorService
            com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$1 r1 = new com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$1
            r1.<init>()
            java.util.concurrent.Future r0 = r0.submit(r1)
            com.facebook.imagepipeline.producers.ProducerContext r3 = r3.getContext()
            com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$2 r1 = new com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$2
            r1.<init>()
            r3.addCallbacks(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.fetch(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void fetchSync(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, NetworkFetcher.Callback callback) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        try {
            httpURLConnection = downloadFrom(httpUrlConnectionNetworkFetchState.getUri(), 5);
        } catch (IOException e2) {
            e = e2;
            httpURLConnection = null;
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
            if (inputStream != null) {
            }
            if (httpURLConnection != null) {
            }
            throw th;
        }
        try {
            try {
                httpUrlConnectionNetworkFetchState.responseTime = this.mMonotonicClock.now();
                if (httpURLConnection != null) {
                    inputStream = httpURLConnection.getInputStream();
                    callback.onResponse(inputStream, -1);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                if (httpURLConnection == null) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            callback.onFailure(e);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            if (httpURLConnection == null) {
                return;
            }
            httpURLConnection.disconnect();
        }
        httpURLConnection.disconnect();
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public Map<String, String> getExtraMap(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(QUEUE_TIME, Long.toString(httpUrlConnectionNetworkFetchState.responseTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put(FETCH_TIME, Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.responseTime));
        hashMap.put(TOTAL_TIME, Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put(IMAGE_SIZE, Integer.toString(i2));
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i2) {
        httpUrlConnectionNetworkFetchState.fetchCompleteTime = this.mMonotonicClock.now();
    }
}
