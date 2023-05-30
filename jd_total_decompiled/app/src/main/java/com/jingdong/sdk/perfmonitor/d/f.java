package com.jingdong.sdk.perfmonitor.d;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class f extends com.jingdong.sdk.perfmonitor.d.b {

    /* loaded from: classes12.dex */
    public static class a {
        public boolean a = false;
        public long b;

        /* renamed from: c  reason: collision with root package name */
        public long f15418c;

        a(long j2, long j3) {
            this.b = 0L;
            this.f15418c = 0L;
            this.b = j2;
            this.f15418c = j3;
        }

        void a(JSONObject jSONObject) throws JSONException {
            boolean equals = "1".equals(jSONObject.getString("switch"));
            this.a = equals;
            if (equals) {
                this.f15418c = jSONObject.getLong("delay");
                this.b = jSONObject.getLong("interval");
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface b {
        void a(a aVar, a aVar2, a aVar3);
    }

    public f(@NonNull Context context, @Nullable b bVar) {
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(context.getApplicationContext(), "11", "3");
        if (stategyEntitiy == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("PerfMonitor", String.format("metrics: %s, params: %s", stategyEntitiy.ret, stategyEntitiy.param));
        }
        a aVar = new a(Final.FIVE_SECOND, 0L);
        a aVar2 = new a(Final.FIVE_SECOND, 1000L);
        a aVar3 = new a(Final.FIVE_SECOND, 1000L);
        try {
            if (!TextUtils.isEmpty(stategyEntitiy.param) && "1".equals(stategyEntitiy.ret)) {
                JSONObject jSONObject = new JSONObject(stategyEntitiy.param);
                this.a = "1".equals(jSONObject.getString("type"));
                if (jSONObject.has("module")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("module");
                    if (jSONObject2.length() > 0) {
                        this.b = new ArrayMap();
                        Iterator<String> keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if ("1".equals(jSONObject2.getJSONObject(next).getString("switch"))) {
                                this.b.put(next, next);
                            }
                        }
                    }
                }
                aVar.a(jSONObject.getJSONObject(PerformanceManager.CUP));
                aVar2.a(jSONObject.getJSONObject(WebPerfManager.MEMORY));
                aVar3.a(jSONObject.getJSONObject("thread"));
            }
        } catch (JSONException e2) {
            OKLog.e("PerfMonitor", "JSON\u89e3\u6790\u9519\u8bef", e2);
        }
        if (bVar != null) {
            bVar.a(aVar, aVar2, aVar3);
        }
    }
}
