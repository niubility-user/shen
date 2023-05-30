package com.jingdong.common.network.cronet;

import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.h.b.a;
import com.jingdong.common.network.quicpro.QuicProEngine;
import com.jingdong.common.network.quicpro.QuicProException;
import com.jingdong.common.network.quicpro.QuicProRequest;
import com.jingdong.common.network.quicpro.UrlRequest;
import com.jingdong.common.network.quicpro.UrlResponseInfo;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes5.dex */
public class TaskController {
    public static final String TAG = "JdCronet";

    /* loaded from: classes5.dex */
    public static class CronetTask implements Runnable {
        public List<Integer> mParams;
        public int mPeriod;
        public int mTimes;

        public CronetTask(List<Integer> list, int i2) {
            this.mParams = list;
            this.mPeriod = i2;
            this.mTimes = list.size();
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x012d, code lost:
            if (r4 != null) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x013c, code lost:
            if (r4 != null) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x013e, code lost:
            r4.disconnect();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0141, code lost:
            com.jingdong.common.network.cronet.CronetLog.formatLog("POST", "https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196", r16, r5, r6, r7, r8, r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0146, code lost:
            return;
         */
        /* JADX WARN: Removed duplicated region for block: B:44:0x014a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void runTask(int r16) {
            /*
                Method dump skipped, instructions count: 341
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.network.cronet.TaskController.CronetTask.runTask(int):void");
        }

        public String getNegotiatedProtocol(HttpURLConnection httpURLConnection) {
            try {
                Field field = CronetComponentHelper.getField("org.chromium.net.urlconnection.CronetHttpURLConnection", "mResponseInfo");
                field.setAccessible(true);
                return (String) CronetComponentHelper.getMethod("org.chromium.net.UrlResponseInfo", "getNegotiatedProtocol", new Class[0]).invoke(field.get(httpURLConnection), new Object[0]);
            } catch (Throwable th) {
                th.printStackTrace();
                return "unknown";
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            CronetLog.debug("Cronet\u63a2\u6d4b\u4efb\u52a1\u5f00\u59cb");
            while (this.mTimes > 0) {
                try {
                    try {
                        Thread.sleep(this.mPeriod);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    List<Integer> list = this.mParams;
                    runTask(list.get(list.size() - this.mTimes).intValue());
                    this.mTimes--;
                } catch (Throwable unused) {
                }
            }
            CronetLog.debug("Cronet\u63a2\u6d4b\u4efb\u52a1\u7ed3\u675f");
        }
    }

    /* loaded from: classes5.dex */
    public static class OkHttpTask implements Runnable {
        public OkHttpClient mOkHttpClient;
        public List<Integer> mParams;
        public int mPeriod;
        public int mTimes;

        public OkHttpTask(List<Integer> list, int i2) {
            this.mParams = list;
            this.mPeriod = i2;
            this.mTimes = list.size();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.mOkHttpClient = builder.connectTimeout(10000L, timeUnit).readTimeout(10000L, timeUnit).retryOnConnectionFailure(false).addInterceptor(new a()).eventListenerFactory(new OkhttpEventListenerFactory()).build();
        }

        private void runTask(int i2) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.ORIGIN, "https://h5.m.jd.com");
                hashMap.put("UserAgent", "OkHttp");
                HashMap hashMap2 = new HashMap();
                Request.Builder method = new Request.Builder().url("https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196").method("POST", new FormBody.Builder().add("body", String.valueOf(i2)).build());
                for (Map.Entry entry : hashMap.entrySet()) {
                    method.header((String) entry.getKey(), (String) entry.getValue());
                }
                Response execute = this.mOkHttpClient.newCall(method.build()).execute();
                if (execute != null) {
                    Map<String, List<String>> multimap = execute.headers().toMultimap();
                    if (multimap != null && !multimap.isEmpty()) {
                        for (Map.Entry<String, List<String>> entry2 : multimap.entrySet()) {
                            List<String> value = entry2.getValue();
                            StringBuffer stringBuffer = new StringBuffer();
                            if (value != null && !value.isEmpty()) {
                                Iterator<String> it = value.iterator();
                                while (it.hasNext()) {
                                    stringBuffer.append(it.next());
                                    stringBuffer.append(DYConstants.DY_REGEX_COMMA);
                                }
                            }
                            hashMap2.put(entry2.getKey(), stringBuffer.toString());
                        }
                    }
                    CronetLog.formatLog(execute.request().method(), execute.request().url().toString(), i2, hashMap, execute.code(), execute.protocol().toString(), hashMap2, execute.body() != null ? execute.body().string() : "");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                CronetLog.debug("OkHttp\u63a2\u6d4b\u4efb\u52a1\u5f00\u59cb");
                while (this.mTimes > 0) {
                    try {
                        Thread.sleep(this.mPeriod);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    List<Integer> list = this.mParams;
                    runTask(list.get(list.size() - this.mTimes).intValue());
                    this.mTimes--;
                }
                CronetLog.debug("OkHttp\u63a2\u6d4b\u4efb\u52a1\u7ed3\u675f");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class QuicProTask implements Runnable {
        public List<Integer> mParams;
        public int mPeriod;
        public int mTimes;

        /* loaded from: classes5.dex */
        public static class MyCallback extends UrlRequest.Callback {
            public UrlResponseInfo mUrlResponseInfo;
            private final CountDownLatch latch = new CountDownLatch(1);
            private long startRequestTime = System.currentTimeMillis();

            public MyCallback() {
                NetworkPerformanceDealer.cronetPerfData.totalReqCount.incrementAndGet();
            }

            public UrlResponseInfo getUrlResponseInfo() {
                try {
                    this.latch.await();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                return this.mUrlResponseInfo;
            }

            @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
            public void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, QuicProException quicProException) {
                try {
                    boolean z = OKLog.D;
                    this.mUrlResponseInfo = urlResponseInfo;
                    this.latch.countDown();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
            public void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) throws Exception {
                if (OKLog.D) {
                    String str = "response body string is " + new String(urlResponseInfo.getResponseContent());
                }
            }

            @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
            public void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) throws Exception {
            }

            @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
            public void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) throws Exception {
                if (OKLog.D) {
                    String str = "Response statusCode is " + urlResponseInfo.getHttpStatusCode();
                    String str2 = "Response Header are " + urlResponseInfo.getAllHeaders();
                }
                NetworkPerformanceDealer.cronetPerfData.h3RttSum.getAndAdd((int) (System.currentTimeMillis() - this.startRequestTime));
            }

            @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
            public void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
                try {
                    boolean z = OKLog.D;
                    this.mUrlResponseInfo = urlResponseInfo;
                    this.latch.countDown();
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        public QuicProTask(List<Integer> list, int i2) {
            this.mParams = list;
            this.mPeriod = i2;
            this.mTimes = list.size();
        }

        private void runTask(int i2) {
            int i3;
            UrlResponseInfo urlResponseInfo;
            HashMap hashMap = new HashMap();
            hashMap.put(HttpHeaders.ORIGIN, "https://h5.m.jd.com");
            hashMap.put("UserAgent", QuicProRequest.TAG);
            HashMap hashMap2 = new HashMap();
            String str = "";
            String str2 = "";
            int i4 = 0;
            try {
                MyCallback myCallback = new MyCallback();
                UrlRequest.Builder newUrlRequestBuilder = QuicProEngine.getInstance().newUrlRequestBuilder("https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196", myCallback, Executors.newSingleThreadExecutor());
                newUrlRequestBuilder.setServicePort("quic-api.m.jd.com:443");
                for (String str3 : hashMap.keySet()) {
                    newUrlRequestBuilder.addHeader(str3, (String) hashMap.get(str3));
                    if (OKLog.D) {
                        String str4 = str3 + ":" + ((String) hashMap.get(str3));
                    }
                }
                newUrlRequestBuilder.setHttpMethod(UrlRequest.HTTP_METHOD_POST);
                HashMap hashMap3 = new HashMap();
                hashMap3.put("body", String.valueOf(i2));
                newUrlRequestBuilder.setParam(hashMap3);
                newUrlRequestBuilder.build().start();
                urlResponseInfo = myCallback.getUrlResponseInfo();
            } catch (Throwable th) {
                th = th;
            }
            if (urlResponseInfo == null) {
                boolean z = OKLog.D;
                return;
            }
            if (urlResponseInfo.getAllHeaders() != null) {
                String str5 = urlResponseInfo.getAllHeaders().get("negotiatedProtocol");
                try {
                    if (OKLog.D) {
                        String str6 = "Protocol Version : " + str5;
                    }
                    str2 = str5;
                } catch (Throwable th2) {
                    th = th2;
                    str2 = str5;
                    try {
                        th.printStackTrace();
                        i3 = i2;
                        CronetLog.formatLog("POST", "https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196", i3, hashMap, i4, str2, hashMap2, str);
                    } finally {
                        CronetLog.formatLog("POST", "https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196", i2, hashMap, 0, str2, hashMap2, "");
                    }
                }
            }
            i4 = urlResponseInfo.getHttpStatusCode();
            if (i4 == -1) {
                boolean z2 = OKLog.D;
            }
            if (urlResponseInfo.getAllHeaders() != null) {
                hashMap2.putAll(urlResponseInfo.getAllHeaders());
            }
            String str7 = new String(urlResponseInfo.getResponseContent());
            i3 = i2;
            str = str7;
            CronetLog.formatLog("POST", "https://quic-api.m.jd.com/api?appid=netDiag&functionId=netMonitor&t=1511418329196", i3, hashMap, i4, str2, hashMap2, str);
        }

        @Override // java.lang.Runnable
        public void run() {
            CronetLog.debug("QuicPro\u63a2\u6d4b\u4efb\u52a1\u5f00\u59cb");
            while (this.mTimes > 0) {
                try {
                    try {
                        Thread.sleep(this.mPeriod);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    List<Integer> list = this.mParams;
                    runTask(list.get(list.size() - this.mTimes).intValue());
                    this.mTimes--;
                } catch (Throwable unused) {
                }
            }
            CronetLog.debug("QuicPro\u63a2\u6d4b\u4efb\u52a1\u7ed3\u675f");
        }
    }

    /* loaded from: classes5.dex */
    public static class Singleton {
        public static TaskController instance = new TaskController();
    }

    public static TaskController getInstance() {
        return Singleton.instance;
    }

    public void runTasks() {
        try {
            if (RuntimeMobileConfig.isEnable() && RuntimeMobileConfig.isEnableDialingTask()) {
                int runTaskPerDay = RuntimeMobileConfig.getRunTaskPerDay();
                final int runTaskInterval = RuntimeMobileConfig.getRunTaskInterval();
                final List<Integer> runTaskParams = RuntimeMobileConfig.getRunTaskParams();
                if (runTaskInterval > 0 && runTaskPerDay > 0 && !runTaskParams.isEmpty()) {
                    if (OKLog.D) {
                        OKLog.d("JdCronet", "runTaskPerDay " + runTaskPerDay + ",runTaskInterval " + runTaskInterval + ",runTaskParams " + runTaskParams + ",quicEndPoint " + RuntimeMobileConfig.getQuicEndPoint());
                    }
                    long j2 = SharedPreferencesUtil.getLong("cronet_last_exec_time", 0L);
                    int i2 = 0;
                    int i3 = SharedPreferencesUtil.getInt("cronet_exec_num", 0);
                    long currentTimeMillis = System.currentTimeMillis();
                    long j3 = currentTimeMillis - j2;
                    if (j3 < 86400000 && i3 >= runTaskPerDay) {
                        if (OKLog.D) {
                            OKLog.d("JdCronet", "\u4e00\u5929\u4e4b\u5185\u7684\u4efb\u52a1\u5df2\u7ecf\u6267\u884c\u5b8c\u6bd5");
                            return;
                        }
                        return;
                    }
                    if (j3 >= 86400000) {
                        SharedPreferencesUtil.putInt("cronet_exec_num", 0);
                        SharedPreferencesUtil.putLong("cronet_last_exec_time", currentTimeMillis);
                    } else {
                        i2 = i3;
                    }
                    new Thread(new Runnable() { // from class: com.jingdong.common.network.cronet.TaskController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new OkHttpTask(runTaskParams, runTaskInterval * 1000).run();
                            new QuicProTask(runTaskParams, runTaskInterval * 1000).run();
                            NetworkPerformanceDealer.reportNetworkPerfData();
                        }
                    }).start();
                    SharedPreferencesUtil.putInt("cronet_exec_num", i2 + 1);
                    return;
                }
                if (OKLog.D) {
                    OKLog.e("JdCronet", "\u542f\u52a8\u6d4b\u901f\u4efb\u52a1\u5931\u8d25\uff0c\u83b7\u53d6\u7b56\u7565\u53c2\u6570\u6709\u8bef, runTaskPerDay " + runTaskPerDay + ",runTaskInterval " + runTaskInterval + ",runTaskParams " + runTaskParams);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private TaskController() {
    }
}
