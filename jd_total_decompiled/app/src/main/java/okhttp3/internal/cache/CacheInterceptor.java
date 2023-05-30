package okhttp3.internal.cache;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* loaded from: classes11.dex */
public final class CacheInterceptor implements Interceptor {
    final InternalCache cache;

    public CacheInterceptor(InternalCache internalCache) {
        this.cache = internalCache;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        Sink body;
        if (cacheRequest == null || (body = cacheRequest.body()) == null) {
            return response;
        }
        response.body().source();
        Okio.buffer(body);
        return response.newBuilder().body(new RealResponseBody(response.header(HttpHeaders.CONTENT_TYPE), response.body().contentLength(), Okio.buffer(new Source
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003e: RETURN 
              (wrap: okhttp3.Response : 0x003a: INVOKE 
              (wrap: okhttp3.Response$Builder : 0x0036: INVOKE 
              (wrap: okhttp3.Response$Builder : 0x0029: INVOKE (r6v0 'response' okhttp3.Response) type: VIRTUAL call: okhttp3.Response.newBuilder():okhttp3.Response$Builder A[MD:():okhttp3.Response$Builder (m), WRAPPED] (LINE:7))
              (wrap: okhttp3.internal.http.RealResponseBody : 0x0033: CONSTRUCTOR 
              (wrap: java.lang.String : 0x001d: INVOKE 
              (r6v0 'response' okhttp3.Response)
              (wrap: java.lang.String : SGET  A[WRAPPED] com.google.common.net.HttpHeaders.CONTENT_TYPE java.lang.String)
             type: VIRTUAL call: okhttp3.Response.header(java.lang.String):java.lang.String A[MD:(java.lang.String):java.lang.String (m), WRAPPED] (LINE:5))
              (wrap: long : 0x0025: INVOKE 
              (wrap: okhttp3.ResponseBody : 0x0021: INVOKE (r6v0 'response' okhttp3.Response) type: VIRTUAL call: okhttp3.Response.body():okhttp3.ResponseBody A[MD:():okhttp3.ResponseBody (m), WRAPPED] (LINE:6))
             type: VIRTUAL call: okhttp3.ResponseBody.contentLength():long A[MD:():long (m), WRAPPED] (LINE:6))
              (wrap: okio.BufferedSource : 0x002f: INVOKE 
              (wrap: okio.Source : 0x0018: CONSTRUCTOR 
              (r4v0 'this' okhttp3.internal.cache.CacheInterceptor A[IMMUTABLE_TYPE, THIS])
              (r1 I:okio.BufferedSource A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v0 'cacheRequest' okhttp3.internal.cache.CacheRequest A[DONT_INLINE])
              (r0 I:okio.BufferedSink A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(okhttp3.internal.cache.CacheInterceptor, okio.BufferedSource, okhttp3.internal.cache.CacheRequest, okio.BufferedSink):void (m), WRAPPED] (LINE:4) call: okhttp3.internal.cache.CacheInterceptor.1.<init>(okhttp3.internal.cache.CacheInterceptor, okio.BufferedSource, okhttp3.internal.cache.CacheRequest, okio.BufferedSink):void type: CONSTRUCTOR)
             type: STATIC call: okio.Okio.buffer(okio.Source):okio.BufferedSource A[MD:(okio.Source):okio.BufferedSource (m), WRAPPED] (LINE:8))
             A[MD:(java.lang.String, long, okio.BufferedSource):void (m), WRAPPED] (LINE:8) call: okhttp3.internal.http.RealResponseBody.<init>(java.lang.String, long, okio.BufferedSource):void type: CONSTRUCTOR)
             type: VIRTUAL call: okhttp3.Response.Builder.body(okhttp3.ResponseBody):okhttp3.Response$Builder A[MD:(okhttp3.ResponseBody):okhttp3.Response$Builder (m), WRAPPED] (LINE:8))
             type: VIRTUAL call: okhttp3.Response.Builder.build():okhttp3.Response A[MD:():okhttp3.Response (m), WRAPPED] (LINE:9))
             (LINE:9) in method: okhttp3.internal.cache.CacheInterceptor.cacheWritingResponse(okhttp3.internal.cache.CacheRequest, okhttp3.Response):okhttp3.Response, file: classes11.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            if (r5 != 0) goto L3
            return r6
        L3:
            okio.Sink r0 = r5.body()
            if (r0 != 0) goto La
            return r6
        La:
            okhttp3.ResponseBody r1 = r6.body()
            okio.BufferedSource r1 = r1.source()
            okio.BufferedSink r0 = okio.Okio.buffer(r0)
            okhttp3.internal.cache.CacheInterceptor$1 r2 = new okhttp3.internal.cache.CacheInterceptor$1
            r2.<init>()
            java.lang.String r5 = "Content-Type"
            java.lang.String r5 = r6.header(r5)
            okhttp3.ResponseBody r0 = r6.body()
            long r0 = r0.contentLength()
            okhttp3.Response$Builder r6 = r6.newBuilder()
            okhttp3.internal.http.RealResponseBody r3 = new okhttp3.internal.http.RealResponseBody
            okio.BufferedSource r2 = okio.Okio.buffer(r2)
            r3.<init>(r5, r0, r2)
            okhttp3.Response$Builder r5 = r6.body(r3)
            okhttp3.Response r5 = r5.build()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheInterceptor.cacheWritingResponse(okhttp3.internal.cache.CacheRequest, okhttp3.Response):okhttp3.Response");
    }

    private static Headers combine(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i2 = 0; i2 < size; i2++) {
            String name = headers.name(i2);
            String value = headers.value(i2);
            if ((!HttpHeaders.WARNING.equalsIgnoreCase(name) || !value.startsWith("1")) && (isContentSpecificHeader(name) || !isEndToEnd(name) || headers2.get(name) == null)) {
                Internal.instance.addLenient(builder, name, value);
            }
        }
        int size2 = headers2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            String name2 = headers2.name(i3);
            if (!isContentSpecificHeader(name2) && isEndToEnd(name2)) {
                Internal.instance.addLenient(builder, name2, headers2.value(i3));
            }
        }
        return builder.build();
    }

    static boolean isContentSpecificHeader(String str) {
        return HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(str);
    }

    static boolean isEndToEnd(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || HttpHeaders.PROXY_AUTHENTICATE.equalsIgnoreCase(str) || HttpHeaders.PROXY_AUTHORIZATION.equalsIgnoreCase(str) || HttpHeaders.TE.equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(str) || HttpHeaders.UPGRADE.equalsIgnoreCase(str)) ? false : true;
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        InternalCache internalCache = this.cache;
        Response response = internalCache != null ? internalCache.get(chain.request()) : null;
        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).get();
        Request request = cacheStrategy.networkRequest;
        Response response2 = cacheStrategy.cacheResponse;
        InternalCache internalCache2 = this.cache;
        if (internalCache2 != null) {
            internalCache2.trackResponse(cacheStrategy);
        }
        if (response != null && response2 == null) {
            Util.closeQuietly(response.body());
        }
        if (request == null && response2 == null) {
            return new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (request == null) {
            return response2.newBuilder().cacheResponse(stripBody(response2)).build();
        }
        try {
            Response proceed = chain.proceed(request);
            if (proceed == null && response != null) {
            }
            if (response2 != null) {
                if (proceed.code() == 304) {
                    Response build = response2.newBuilder().headers(combine(response2.headers(), proceed.headers())).sentRequestAtMillis(proceed.sentRequestAtMillis()).receivedResponseAtMillis(proceed.receivedResponseAtMillis()).cacheResponse(stripBody(response2)).networkResponse(stripBody(proceed)).build();
                    proceed.body().close();
                    this.cache.trackConditionalCacheHit();
                    this.cache.update(response2, build);
                    return build;
                }
                Util.closeQuietly(response2.body());
            }
            Response build2 = proceed.newBuilder().cacheResponse(stripBody(response2)).networkResponse(stripBody(proceed)).build();
            if (this.cache != null) {
                if (okhttp3.internal.http.HttpHeaders.hasBody(build2) && CacheStrategy.isCacheable(build2, request)) {
                    return cacheWritingResponse(this.cache.put(build2), build2);
                }
                if (HttpMethod.invalidatesCache(request.method())) {
                    try {
                        this.cache.remove(request);
                    } catch (IOException unused) {
                    }
                }
            }
            return build2;
        } finally {
            if (response != null) {
                Util.closeQuietly(response.body());
            }
        }
    }
}
