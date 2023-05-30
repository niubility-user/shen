package com.jd.mobile.image.a.b;

import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.backends.okhttp3.ImgNetStatisticTool;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jd.dynamic.DYConstants;
import com.jd.mobile.image.exception.JDImageNetworkException;
import com.jd.mobile.image.listener.NetworkImageRequestListener;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.assist.JDFailType;
import com.jingdong.common.httpdns.DnsResolver;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.CDNRouteSelector;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes17.dex */
public class a extends BaseNetworkFetcher<c> {
    public static final String[] d = {"m.360buyimg.com", "img10.360buyimg.com", "img11.360buyimg.com", "img12.360buyimg.com", "img13.360buyimg.com", "img14.360buyimg.com", "img20.360buyimg.com", "img30.360buyimg.com"};
    private final OkHttpClient a;
    private Executor b;

    /* renamed from: c  reason: collision with root package name */
    private HashSet<String> f6832c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.mobile.image.a.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0208a extends BaseProducerContextCallbacks {
        final /* synthetic */ Call a;

        /* renamed from: com.jd.mobile.image.a.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class RunnableC0209a implements Runnable {
            RunnableC0209a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0208a.this.a.cancel();
            }
        }

        C0208a(Call call) {
            this.a = call;
        }

        @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
        public void onCancellationRequested() {
            if (a.this.a.dispatcher().queuedCalls().contains(this.a)) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    this.a.cancel();
                } else {
                    a.this.b.execute(new RunnableC0209a());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Callback {
        final /* synthetic */ c a;
        final /* synthetic */ d b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f6834c;
        final /* synthetic */ boolean d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f6835e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ NetworkFetcher.Callback f6836f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ImageRequest f6837g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f6838h;

        b(c cVar, d dVar, String str, boolean z, String str2, NetworkFetcher.Callback callback, ImageRequest imageRequest, long j2) {
            this.a = cVar;
            this.b = dVar;
            this.f6834c = str;
            this.d = z;
            this.f6835e = str2;
            this.f6836f = callback;
            this.f6837g = imageRequest;
            this.f6838h = j2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            try {
                d dVar = this.b;
                if (dVar.a) {
                    CDNRouteSelector.HealthVIP healthVIP = dVar.f6841c;
                    if (healthVIP != null && !TextUtils.isEmpty(healthVIP.vip)) {
                        JdImageToolKit.getEngine().getCdnRouteSelector().addFailedIP(this.b.f6841c.vip, iOException);
                    }
                } else if (!TextUtils.isEmpty(dVar.b)) {
                    DnsResolver.getInstance().add2IPFailList(this.f6834c, this.b.b, iOException);
                }
                if (this.b.a() && call != null && !call.isCanceled()) {
                    a.this.m(this.f6835e, DYConstants.DY_FALSE, this.b.f6841c.domain, false, 1, this.f6834c);
                }
            } catch (Throwable unused) {
            }
            if (call != null && call.isCanceled()) {
                FLog.d("JDNetworkFetcher", ">>>>>>>>>>>>>>>current total count : after cancel " + ImgNetStatisticTool.decreaseTotalCountAndGet());
            }
            if (call != null) {
                try {
                    if (!call.isCanceled() && !this.d) {
                        a.this.h(this.a, this.f6835e, this.b.d, iOException, this.f6836f, -1);
                    }
                } finally {
                    a.p();
                }
            }
            a.this.n(call, iOException, this.f6836f);
            FLog.e("JDNetworkFetcher", "image request " + call.request().url().toString() + " failed: " + iOException.getMessage());
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            this.a.b = SystemClock.elapsedRealtime();
            ResponseBody body = response.body();
            try {
                d dVar = this.b;
                if (!dVar.d && !TextUtils.isEmpty(dVar.b)) {
                    DnsResolver.getInstance().removeFromFailList(this.f6834c, this.b.b);
                }
            } catch (Throwable unused) {
            }
            try {
                try {
                } catch (Exception e2) {
                    a.this.n(call, e2, this.f6836f);
                    try {
                        try {
                            body.close();
                        } catch (Exception e3) {
                            FLog.w("JDNetworkFetcher", "Exception when closing response body", e3);
                        }
                    } finally {
                    }
                }
                if (!response.isSuccessful()) {
                    if (this.d) {
                        a.this.n(call, new IOException("Unexpected HTTP code " + response), this.f6836f);
                        ImgNetStatisticTool.decreaseTotalCountAndGet();
                        FLog.e("JDNetworkFetcher", "image request " + call.request().url() + " failed!");
                    } else {
                        a.this.h(this.a, this.f6835e, this.b.d, new IOException("Unexpected HTTP code : " + response), this.f6836f, response.code());
                    }
                    try {
                        try {
                            body.close();
                        } catch (Exception e4) {
                            FLog.w("JDNetworkFetcher", "Exception when closing response body", e4);
                        }
                        return;
                    } finally {
                    }
                }
                try {
                    ImgNetStatisticTool.incrementSuccessCountAndGet();
                    d dVar2 = this.b;
                    if (dVar2.d) {
                        ImgNetStatisticTool.incrementDomainSuccessCountAndGet();
                    } else if (dVar2.a()) {
                        ImgNetStatisticTool.incrBakDomainSuccCnt();
                        a.this.m(this.f6835e, DYConstants.DY_TRUE, this.b.f6841c.domain, false, 1, this.f6834c);
                    } else if (this.b.b()) {
                        ImgNetStatisticTool.incrementBakIpSuccessCountAndGet();
                    }
                } catch (Throwable unused2) {
                }
                FLog.d("JDNetworkFetcher", "Protocol is " + response.protocol() + ", Final Url is " + this.f6835e);
                int contentLength = (int) body.contentLength();
                int i2 = contentLength < 0 ? 0 : contentLength;
                long currentTimeMillis = System.currentTimeMillis();
                this.f6836f.onResponse(body.byteStream(), i2);
                RequestListener requestListener = this.f6837g.getRequestListener();
                if (requestListener instanceof NetworkImageRequestListener) {
                    ((NetworkImageRequestListener) requestListener).onSuccess(this.f6835e, this.f6838h, currentTimeMillis, i2);
                }
                try {
                    try {
                        body.close();
                    } finally {
                    }
                } catch (Exception e5) {
                    FLog.w("JDNetworkFetcher", "Exception when closing response body", e5);
                }
            } catch (Throwable th) {
                try {
                    try {
                        body.close();
                    } catch (Exception e6) {
                        FLog.w("JDNetworkFetcher", "Exception when closing response body", e6);
                    }
                    throw th;
                } finally {
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    public static class c extends FetchState {
        public long a;
        public long b;

        /* renamed from: c  reason: collision with root package name */
        public long f6840c;

        public c(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    /* loaded from: classes17.dex */
    public class d {
        public boolean a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public CDNRouteSelector.HealthVIP f6841c;
        public boolean d;

        public d(a aVar) {
        }

        public boolean a() {
            CDNRouteSelector.HealthVIP healthVIP = this.f6841c;
            if (healthVIP != null) {
                return healthVIP.isBackDomainHealthVip();
            }
            return false;
        }

        public boolean b() {
            CDNRouteSelector.HealthVIP healthVIP = this.f6841c;
            if (healthVIP != null) {
                return healthVIP.isBackHttpDnsHealthVip();
            }
            return false;
        }
    }

    public a() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient build = builder.connectTimeout(15000L, timeUnit).readTimeout(20000L, timeUnit).addInterceptor(new com.jingdong.b.a.a(false)).pingInterval(Final.HALF_MINUTE, timeUnit).build();
        this.a = build;
        this.b = build.dispatcher().executorService();
        this.f6832c = new HashSet<>(Arrays.asList(d));
    }

    private static String b(String str) {
        try {
            String host = new URL(str).getHost();
            if (TextUtils.isEmpty(host)) {
                throw new MalformedURLException(str);
            }
            return host;
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    private String c(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !this.f6832c.contains(str2)) {
            return str;
        }
        String str4 = "https";
        if (JdImageToolKit.getEngine().getNetworkParamSupplier().isUseHttps()) {
            if (str.startsWith("https")) {
                return str;
            }
            str3 = "(?i)http";
        } else if (!str.startsWith("https")) {
            return str;
        } else {
            str3 = "(?i)https";
            str4 = "http";
        }
        return str.replaceFirst(str3, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(c cVar, String str, boolean z, Throwable th, NetworkFetcher.Callback callback, int i2) {
        try {
            ImageRequest imageRequest = cVar.getContext().getImageRequest();
            if (!z) {
                JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(imageRequest.getSourceUri().toString(), new JDFailReason(JDFailType.UNKNOWN, new JDImageNetworkException(th, false, str, b(str))), imageRequest.getReferer(), i2);
            }
            l(str, cVar, callback, z, true);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0132 A[Catch: Exception -> 0x015e, TRY_LEAVE, TryCatch #0 {Exception -> 0x015e, blocks: (B:40:0x0103, B:42:0x0107, B:44:0x010f, B:46:0x0119, B:48:0x011f, B:49:0x0124, B:51:0x0132, B:54:0x013e, B:55:0x0145, B:57:0x014f, B:59:0x0156), top: B:68:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014f A[Catch: Exception -> 0x015e, TryCatch #0 {Exception -> 0x015e, blocks: (B:40:0x0103, B:42:0x0107, B:44:0x010f, B:46:0x0119, B:48:0x011f, B:49:0x0124, B:51:0x0132, B:54:0x013e, B:55:0x0145, B:57:0x014f, B:59:0x0156), top: B:68:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0156 A[Catch: Exception -> 0x015e, TRY_LEAVE, TryCatch #0 {Exception -> 0x015e, blocks: (B:40:0x0103, B:42:0x0107, B:44:0x010f, B:46:0x0119, B:48:0x011f, B:49:0x0124, B:51:0x0132, B:54:0x013e, B:55:0x0145, B:57:0x014f, B:59:0x0156), top: B:68:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void l(java.lang.String r17, com.jd.mobile.image.a.b.a.c r18, com.facebook.imagepipeline.producers.NetworkFetcher.Callback r19, boolean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.mobile.image.a.b.a.l(java.lang.String, com.jd.mobile.image.a.b.a$c, com.facebook.imagepipeline.producers.NetworkFetcher$Callback, boolean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(String str, String str2, String str3, boolean z, int i2, String str4) {
        JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportCDNDowngradeData(str, str2, str3, z, i2, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(Call call, Throwable th, NetworkFetcher.Callback callback) {
        if (call == null || !call.isCanceled()) {
            callback.onFailure(th);
        } else {
            callback.onCancellation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p() {
        if (ImgNetStatisticTool.isSendLastResult() && JdImageToolKit.getEngine().getNetStatReporter() != null) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(ImgNetStatisticTool.KEY_TOTAL_IMG_REQUEST_COUNT, Integer.valueOf(ImgNetStatisticTool.getTotalCount()));
            hashMap.put(ImgNetStatisticTool.KEY_SUCCEED_IMG_REQUEST_COUNT, Integer.valueOf(ImgNetStatisticTool.getSuccessCount()));
            hashMap.put(ImgNetStatisticTool.KEY_SUCCEED_IMG_DOMAIN_REQUEST_COUNT, Integer.valueOf(ImgNetStatisticTool.getDomainSuccessCount()));
            hashMap.put(ImgNetStatisticTool.KEY_SUCCEED_IMG_BAK_IP_REQUEST_COUNT, Integer.valueOf(ImgNetStatisticTool.getSuccImgBakIpRequestCount()));
            hashMap.put(ImgNetStatisticTool.KEY_SUCCEED_IMG_BAK_DOMAIN_REQUEST_COUNT, Integer.valueOf(ImgNetStatisticTool.getSuccImgBakDomainReqCnt()));
            JdImageToolKit.getEngine().getNetStatReporter().saveStatisticData(hashMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public c createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new c(consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public Map<String, String> getExtraMap(c cVar, int i2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(cVar.b - cVar.a));
        hashMap.put("fetch_time", Long.toString(cVar.f6840c - cVar.b));
        hashMap.put("total_time", Long.toString(cVar.f6840c - cVar.a));
        hashMap.put("image_size", Integer.toString(i2));
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void fetch(c cVar, NetworkFetcher.Callback callback) {
        try {
            cVar.a = SystemClock.elapsedRealtime();
            FLog.d("JDNetworkFetcher", ">>>>>>>>>>>>>>>current total count : " + ImgNetStatisticTool.incrementTotalCountAndGet());
            l(cVar.getUri().toString(), cVar, callback, JdImageToolKit.getEngine().getNetworkParamSupplier().isUseDomainFlag(), false);
        } catch (Exception e2) {
            n(null, e2, callback);
        }
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void onFetchCompletion(c cVar, int i2) {
        cVar.f6840c = SystemClock.elapsedRealtime();
    }
}
