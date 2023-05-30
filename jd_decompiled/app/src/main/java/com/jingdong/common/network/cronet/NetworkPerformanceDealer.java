package com.jingdong.common.network.cronet;

import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NetworkPerformanceDealer {
    public static Data cronetPerfData = new Data(2);
    public static Data okhttpPerfData = new Data(1);

    /* loaded from: classes5.dex */
    public static class Data {
        public final int libType;
        public AtomicInteger totalReqCount = new AtomicInteger(0);
        public AtomicInteger h3SucceedCount = new AtomicInteger(0);
        public AtomicInteger h3RttSum = new AtomicInteger(0);
        public AtomicInteger h3HandShakeSum = new AtomicInteger(0);
        public AtomicInteger h3HandShakeCount = new AtomicInteger(0);
        public AtomicInteger h2SucceedCount = new AtomicInteger(0);
        public AtomicInteger h2RttSum = new AtomicInteger(0);
        public AtomicInteger h2HandShakeSum = new AtomicInteger(0);
        public AtomicInteger h2HandShakeCount = new AtomicInteger(0);
        public AtomicInteger h1SucceedCount = new AtomicInteger(0);
        public AtomicInteger h1RttSum = new AtomicInteger(0);
        public AtomicInteger h1HandShakeSum = new AtomicInteger(0);
        public AtomicInteger h1HandShakeCount = new AtomicInteger(0);

        public Data(int i2) {
            this.libType = i2;
        }

        public JSONObject toJsonObject() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("libType", this.libType == 2 ? "quicPro" : "okhttp");
                int i2 = this.totalReqCount.get();
                int i3 = this.h3SucceedCount.get();
                int i4 = this.h2SucceedCount.get();
                int i5 = this.h1SucceedCount.get();
                jSONObject.put("totalReqCount", i2);
                jSONObject.put("reqSucceedCount", i3 + i4 + i5);
                jSONObject.put("h3SucceedCount", i3);
                jSONObject.put("h2SucceedCount", i4);
                jSONObject.put("h1SucceedCount", i5);
                int i6 = 0;
                jSONObject.put("h3RttAvg", String.valueOf(i3 > 0 ? this.h3RttSum.get() / i3 : 0));
                int i7 = this.h3HandShakeSum.get();
                int i8 = this.h3HandShakeCount.get();
                jSONObject.put("h3HandShakeAvg", String.valueOf((i7 <= 0 || i8 <= 0) ? 0 : i7 / i8));
                jSONObject.put("h2RttAvg", String.valueOf(i4 > 0 ? this.h2RttSum.get() / i4 : 0));
                int i9 = this.h2HandShakeSum.get();
                int i10 = this.h2HandShakeCount.get();
                jSONObject.put("h2HandShakeAvg", String.valueOf((i9 <= 0 || i10 <= 0) ? 0 : i9 / i10));
                jSONObject.put("h1RttAvg", String.valueOf(i5 > 0 ? this.h1RttSum.get() / i5 : 0));
                int i11 = this.h1HandShakeSum.get();
                int i12 = this.h1HandShakeCount.get();
                if (i11 > 0 && i12 > 0) {
                    i6 = i11 / i12;
                }
                jSONObject.put("h1HandShakeAvg", String.valueOf(i6));
                return jSONObject;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void reportNetworkPerfData() {
        try {
            CronetLog.debug("\u4e0a\u62a5\u63a2\u6d4b\u7ed3\u679c\u6570\u636e");
            HashMap hashMap = new HashMap();
            hashMap.put("function", "quicPerfData");
            hashMap.put("errCode", "818");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            hashMap.put("reserved1", cronetPerfData.toJsonObject().toString());
            hashMap.put("reserved2", okhttpPerfData.toJsonObject().toString());
            CronetLog.logJson(new JSONObject(hashMap));
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
